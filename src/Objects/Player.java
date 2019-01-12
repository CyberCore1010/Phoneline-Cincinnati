package Objects;

import Backend.SFXplayer;
import Game.GameView;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player extends Character {

    private GameObject cursor; //GameObject reference to the cursor
    private double angle; //the current angle the player is facing

    private Timer attackTimer; //Defines the timer used for disabling the attacks.
    private boolean attackStart = true; //boolean used to say if the attack has started
    private int attackSound = 1; //variable that chooses which attack sound to play, alternates between 1 and 2
    private int attackX = (x-(size/4))+30; private int attackY = (y-(size/4))-20; //The x and y of the attack box
    private int attackWidth = size/2; private int attackHeight = size; //The width and height of the attack box
    private Rectangle attackBox = new Rectangle(attackX, attackY, attackWidth, attackHeight); //The attack box Rectangle object

    public static int currentScore = 0; //The players current score
    public static int levelScore = 0; //The level score(Not fully implemented, was meant for use if the game exceeded more than one level however due to time constraints it only has one

    /**This constructor will, first set the basic parameters such as x, y, width, and height. And then it will
     * initiate the sprites with the images needed for them. And then initiates the timer.
     *
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     * @param handler - handler is used for getting images and modifying order
     * @param rotation - The default rotation of the object
     */
    public Player(int x, int y, int width, int height, int size, ObjectID id, boolean blocking, ObjectHandler handler, double rotation) {
        super(x, y, width, height, size, id, blocking, handler, rotation);

        currentSprite = handler.defaultSpritePlayer;
        attackSprite = handler.attackSpritePlayer;
        deadSprite1 = handler.deadSprite1Player;
        deadSprite2 = handler.deadSprite2Player;
        deadSprite3 = handler.deadSprite3Player;
        deadSprite4 = handler.deadSprite4Player;
        defaultSprite = currentSprite;

        attackTimer = new Timer();
    }

    /**
     * This is the main update method. It basically works by saying that first if the game is in the menu or if they
     * are dead return. If it passes these checks it will then check if the game is in debug mode, and if it is will
     * System.out the location for debugging. It then runs each seperate function, such as collision, detectPlayers
     * (Only if there is a player to detect), the movement method, the rotation method, and finally the attack method
     */
    @Override
    public void update() {
        if(isDead || GameView.mainMenu) {
            return;
        }
        if(GameView.debug) {
            System.out.println(x+" "+y);
        }
        x += velX;
        y += velY;

        collision();
        movement();
        rotation();
        attack();
    }

    /**
     * THe movement is quite simple, it takes the boolean values from the handler and checks them with if statements.
     * It then uses this to know what to increase the velocity by.
     */
    @SuppressWarnings("Duplicates")
    private void movement() {
        int playerSpeed = 5;
        if(handler.isUp()) velY = -playerSpeed;
        else if (!handler.isDown()) velY = 0;

        if(handler.isDown()) velY = playerSpeed;
        else if (!handler.isUp()) velY = 0;

        if(handler.isRight()) velX = playerSpeed;
        else if (!handler.isLeft()) velX = 0;

        if(handler.isLeft()) velX = -playerSpeed;
        else if (!handler.isRight()) velX = 0;
    }

    /**
     * The rotation works by setting the cursor to the current instance of it, and then finding it's center x and y. It
     * then finds the rotation between the mouse x an y and the players x and y, and sets angle to that value, allowing
     * it to rotate to that amount.
     */
    @SuppressWarnings("Duplicates")
    private void rotation() {
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.cursor) {
                cursor = tempObject;
            }
        }
        double mouseX = 0;
        double mouseY = 0;
        try {
            mouseX = (cursor.getX()-x)+(cursor.getSize()/2);
            mouseY = (cursor.getY()-y)+(cursor.getSize()/2);
        } catch (Exception ignored) {}
        angle = Math.atan2(mouseY, mouseX);
    }

    /**
     * The attack method simply works by asking if the handler attack has been set to true. It will then change the
     * sprite to the attacking sprite, and play the swinging sound effect. Then it will check the players angle, and
     * depending on which direction they face, a different bounding box is drawn. It then checks if any enemy in the
     * list collides with the current attack box, and if it does, will activate the die method on that enemy.
     * It then creates a timer that's set for 200 milliseconds, which will set the attack variable to false;
     */
    @SuppressWarnings("Duplicates")
    private void attack() {
        if(handler.isAttack()) {
            currentSprite = attackSprite;
            if(attackStart) {
                attackStart = false;
                switch(attackSound) {
                    case 1:
                        Backend.SFXplayer.playSound("Swing1.wav");
                        attackSound = 2;
                        break;
                    case 2:
                        Backend.SFXplayer.playSound("Swing2.wav");
                        attackSound = 1;
                        break;
                }
                if(angle >= -2.25 && angle <= -0.75) { //top
                    attackX = (x-(size/4))-20; attackY = (y-(size/4))-30; attackWidth = size; attackHeight = (size/2)+10;
                } else if (angle > -0.75 && angle < 0.75) { //right
                    attackX = (x-(size/4))+20; attackY = (y-(size/4))-20; attackWidth = (size/2)+10; attackHeight = size;
                } else if (angle >= 0.75 && angle <= 2.25) { //bottom
                    attackX = (x-(size/4))-20; attackY = (y-(size/4))+20; attackWidth = size; attackHeight = (size/2)+10;
                } else { //left
                    attackX = (x-(size/4))-30; attackY = (y-(size/4))-20; attackWidth = (size/2)+10; attackHeight = size;
                }
                attackBox.x = attackX;
                attackBox.y = attackY;
                attackBox.width = attackWidth;
                attackBox.height = attackHeight;
                for(int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);
                    if(tempObject.getId() == ObjectID.enemy) {
                        if(attackBox.getBounds().intersects(tempObject.getBounds())) {
                            tempObject.die();
                        }
                    }
                }
                TimerTask attackStop = new TimerTask() {
                    @Override
                    public void run() {
                        handler.setAttack(false);
                        currentSprite = defaultSprite;
                        attackStart = true;
                    }
                };
                attackTimer.schedule(attackStop, 200);
            }
        }
    }

    /**
     * This method works by checking if the player is already dead. If it is it won't die again. It then will set the
     * sprite to a death sprite, passing in true to show only sprites that are facing up instead of down it then does
     * a series of for loops and additions to the list, re-layering them and adding new objects such as the blood
     * pools and blood splats.
     */
    @Override
    public void die() {
        if(isDead) {
            return;
        }
        int pos = deathSprite(true);
        CopyOnWriteArrayList<GameObject> temp = new CopyOnWriteArrayList<>();
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.floor || tempObject.getId() == ObjectID.bloodPool) {
                temp.add(tempObject);
            }
        }
        temp.add(new BloodPool(x, y, width, height, size, angle, ObjectID.bloodPool, false, handler, pos, true));
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() != ObjectID.floor && tempObject.getId() != ObjectID.bloodPool) {
                if(tempObject.getId() != ObjectID.enemy && tempObject.getId() != ObjectID.wall && tempObject.getId() != ObjectID.furniture) {
                    temp.add(tempObject);
                }
            }
        }
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.furniture) {
                temp.add(tempObject);
            }
        }
        for(int i = 0; i < 20; i++) {
            temp.add(new BloodSplat(x+(width/2), y+(height/2), 0, 0, size, ObjectID.bloodSplat, false, handler));
        }
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.enemy) {
                temp.add(tempObject);
            }
        }
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.wall) {
                temp.add(tempObject);
            }
        }
        handler.object = temp;

        size += 30;
        SFXplayer.playSound("Hit.wav");
        isDead = true;

        if(!GameView.nameSet) {
            GameView.noName = true;
            GameView.nameSet = true;
        }
        GameView.setScore = true;
        GameView.gameOver = true;
    }

    /**
     * The reset method simply resets all of the objects properties to the default properties
     */
    @Override
    public void reset() {
        currentSprite = defaultSprite;
        x = defaultPosX;
        y = defaultPosY;
        angle = defaultAngle;
        isDead = false;
        size = defaultSize;
    }

    /**This method just paints the image at a position.
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void render(Graphics g) {
        drawCharacter(g, x, y, size, angle, currentSprite);

        //Draw attack box
        //g.setColor(Color.RED);
        //g.drawRect(attackX, attackY, attackWidth, attackHeight);
    }
}
