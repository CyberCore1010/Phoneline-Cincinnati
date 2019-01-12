package Game;

import Backend.SFXplayer;
import Backend.ScoreIO;
import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static Backend.Window.gameHeight;
import static Backend.Window.gameWidth;

public class GameView extends JComponent {
    //This is the Object handler which is set in the constructor. It's passed throughout the entire program, and is used to manipulate the objects in the game
    public static ObjectHandler handler;
    //This is the camera which is an object of type Camera from my Camera class. It's used for updating the camera each tick of the game
    private Camera camera;

    //These variables are self explanatory. They're fonts to be used in the game, and are set in the
    //GameView constructor.
    private Font NormalFont;
    private Font LargeFont;
    private Font XLargeFont;
    private float fontSize = 50f;

    //These variables are used to change through different game states. For example when the level is cleared "clear"
    //is set to true, which causes "clearMusic" to be set to true.
    public static boolean clearMusic = false;
    public static boolean clear = false;
    public static boolean gameOver = false;
    public static boolean win = false;
    public static boolean winMusic = true;

    //These variables set up the scoreBoard, starting as false. The String is used to set the name to an empty string
    //by default, so the player can enter their own when they win or lose.
    public static String name = "";
    public static boolean noName = false;
    public static boolean nameSet = false;
    public static boolean setScore = false;

    //These variables are just used to dictate which menus will be shown.
    public static boolean helpMenu = false;
    public static boolean mainMenu = true;
    public static boolean mainMenuMusic = true;

    //These variables are simply used to tell the program that it is running. It also creates a new Thread object which
    //Is used for separating the game time from the game logic
    private static boolean isRunning = true;
    private Thread thread;

    //Finally this variable is just for me, as it allows me to enable debug mode to help me fix or test issues.
    public static boolean debug = false;

    /**This is the constructor for the GameView object. It is the main core of my game, and houses the update and
     * render methods for my game, as well as the timer that runs it all.
     *
     * The constructor will first set the fonts to be used in the menus, followed by creating the Camera object, which
     * starts at the position 0,0 however immediately changes to the players location. The constructor will then create
     * a new thread which contains the "start" method below.
     *
     * @param handler - Passes the object handler in so this class can manipulate objects
     */
    GameView(ObjectHandler handler) {
        GameView.handler = handler;
        try {
            NormalFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/Fonts/ScoreFont.ttf")).deriveFont(fontSize);
            LargeFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/Fonts/ScoreFont.ttf")).deriveFont(fontSize*2);
            XLargeFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/Fonts/ScoreFont.ttf")).deriveFont(fontSize*4);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(LargeFont);
            ge.registerFont(NormalFont);
            ge.registerFont(XLargeFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        camera = new Camera(0, 0);
        thread = new Thread(this::start);
        thread.start();
    }

    /**This method is the method that controls the time used in the game. It basically works on the basis that each
     * time the game loops it will check the difference between the current system time and the last system time
     * and then runs the update method on those game objects for the time difference between the two. Finally it will
     * render the objects, showing them on the screen. It also has a timer which increases each time, which just shows
     * how much time has passed since the game has started. as of now it doesn't really do anything however it can be
     * used with System.out for debugging purposes.
     */
    private void start() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                delta--;
            }
            repaint();

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    /**This is the update method which is called from the game timer in the "start" method. It basically will first
     * find the player in the handler and send it to the camera so that it can change it's position to the cameras
     * players current position. Afterwords it runs the update method in the object handler
     */
    private void update() {
        for(GameObject g : handler.object) {
            if(g.getId() == ObjectID.player) {
                camera.update(g);
            }
        }
        handler.update();
    }

    /**This method is called from the start method when the game is no longer running and simply joins the two threads
     * together to prevent system problems.
     */
    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**This method simply just loops through the handler and displays all the objects in the handler. It uses
     * g2d.translate so that they line up properly with the cameras current position. The menu drawing under this is
     * simply used to overlay the menus when they are currently active. The only real noteworthy code is the score menu
     * which loops through the highScores list from ScoreIO and prints 5 of them.
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        ////////DRAWING AREA////////

        g2d.translate(-camera.getX(), -camera.getY());
        handler.render(g); //displays objects passed from handler
        g2d.translate(0, 0);

        ////////MENU DRAWING////////
        g.setColor(Color.white);
        g.setFont(NormalFont);
        int fixToCameraX = (int)-camera.getX();
        int fixToCameraY = (int)-camera.getY();
        if(clear) {
            g.drawString("Go to the car", 15-fixToCameraX, (int)fontSize-fixToCameraY);
        } else {
            g.drawString("Score: "+ Player.currentScore, 15-fixToCameraX, (int)fontSize-fixToCameraY);
        }
        g.drawString("Press H for help", 1400-fixToCameraX, (int)fontSize-fixToCameraY);
        if(mainMenu) {
            if(mainMenuMusic) {
                SFXplayer.changeMusic(-1);
                SFXplayer.playMusic();
                mainMenuMusic = false;
            }
            g.drawImage(handler.Menu, -fixToCameraX, -fixToCameraY, gameWidth, gameHeight, null);
            g.setColor(Color.WHITE);
            g.setFont(XLargeFont);
            g.drawString("Phoneline", 380-fixToCameraX, 200-fixToCameraY);
            g.drawString("Cincinnati", 340-fixToCameraX, 350-fixToCameraY);
            g.setFont(LargeFont);
            g.drawString("Press ENTER to play", 340-fixToCameraX, 600-fixToCameraY);
            g.drawString("Press H to view the help menu", 140-fixToCameraX, 700-fixToCameraY);
            g.drawString("Press ESC to exit the game", 190-fixToCameraX, 800-fixToCameraY);
        }
        if(helpMenu) {
            if(mainMenu) {
                g.setColor(new Color(0, 0, 0, 0.8f));
            } else {
                g.setColor(new Color(0, 0, 0, 0.5f));
            }
            int helpMenuHeight = 700;
            int helpMenuWidth = 800;
            int menuX = ((gameWidth/2)-(helpMenuWidth/2))-fixToCameraX;
            int menuY = ((gameHeight/2)-(helpMenuHeight/2))-fixToCameraY;
            g.fillRect(menuX, menuY, helpMenuWidth, helpMenuHeight);
            g.setColor(Color.WHITE);
            g.drawRect(menuX, menuY, helpMenuWidth, helpMenuHeight);
            g.setFont(LargeFont);
            g.drawString("Help:", menuX+20, menuY+90);
            g.setFont(NormalFont);
            g.drawString("Telephone Cincinnati is a game", menuX+10, menuY+130);
            g.drawString("about killing, simply put. Your", menuX+10, menuY+165);
            g.drawString("aim is to kill all the enemies", menuX+10, menuY+200);
            g.drawString("in the level.", menuX+10, menuY+235);
            g.setFont(LargeFont);
            g.drawString("Controls:", menuX+10, menuY+320);
            g.setFont(NormalFont);
            g.drawString("W,A,S,D = Move up, down, left", menuX+10, menuY+360);
            g.drawString("                      and right", menuX+10, menuY+395);
            g.drawString("Left mouse button = Attack", menuX+10, menuY+430);
            g.setFont(LargeFont);
            g.drawString("Tips: ", menuX+10, menuY + 515);
            g.setFont(NormalFont);
            g.drawString("The Mafia are predictable...", menuX+10, menuY+555);
            g.drawString("try to plan your attacks", menuX+10, menuY+590);
            g.drawString("around that.", menuX+10, menuY+625);
            g.drawString("Timing and speed are crucial.", menuX+10, menuY+665);
        }
        if(gameOver) {
            int menuX = (((gameWidth/2)-(int)fontSize*2)-170)-fixToCameraX;
            int menuY = (((gameHeight/2)-(int)fontSize*2)-200)-fixToCameraY;
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillRect(0-fixToCameraX, 0-fixToCameraY, gameWidth, gameHeight);
            g.setColor(Color.WHITE);
            g.setFont(LargeFont);
            if(win) {
                g.drawImage(handler.Win, -140, (gameHeight/2)+100, gameWidth, gameHeight, null);
                g.drawString("You win", menuX+50, menuY);
            } else {
                g.drawString("Game Over", menuX, menuY);
            }
            g.setFont(NormalFont);
            g.drawString("Your score: "+Player.currentScore, menuX, menuY+50);
            g.drawString("HighScores:", menuX+120, menuY+100);
            int scoreY = menuY+150;
            for(String s : ScoreIO.highScores) {
                g.drawString(s, menuX, scoreY);
                scoreY += 50;
            }
            g.drawString("To retry, press 'R'...", menuX+10, menuY+450);
            g.drawString("You can do this anytime to", menuX-80, menuY+500);
            g.drawString("restart the current level", menuX-80, menuY+550);
            g.drawString("Or press 'ESC' to go back to the menu", menuX-180, menuY+650);
            if(!nameSet) {
                noName = true;
            }
            if(noName) {
                int nameBoxWidth = 500, nameBoxHeight = 100;
                g.setColor(new Color(0, 0, 0, 0.5f));
                g.fillRect(((gameWidth/2)-(nameBoxWidth/2))-fixToCameraX, ((gameHeight/2)-(nameBoxHeight/2))-fixToCameraY, nameBoxWidth, nameBoxHeight);
                g.setColor(Color.WHITE);
                g.drawRect(((gameWidth/2)-(nameBoxWidth/2))-fixToCameraX, ((gameHeight/2)-(nameBoxHeight/2))-fixToCameraY, nameBoxWidth, nameBoxHeight);
                g.drawString("Your Initials: "+name, (((gameWidth/2)-(nameBoxWidth/2))+20)-fixToCameraX, (((gameHeight/2)-(nameBoxHeight/2))+65)-fixToCameraY);
                if(name.length() >= 3) {
                    noName = false;
                    if(!setScore) {
                        ScoreIO.addScore(Player.currentScore, name);
                    }
                }
            }
            if(nameSet&&!noName&&setScore) {
                setScore = false;
                ScoreIO.addScore(Player.currentScore, name);
            }
        }
        g.dispose();
    }
}
