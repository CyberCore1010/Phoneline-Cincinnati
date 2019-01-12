package Backend;

import Game.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static Objects.GameObject.mousePoint;

public class Window {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static int gameWidth = (int)screenSize.getWidth()-50;
    public static int gameHeight = (int)screenSize.getHeight()-100;

    /**This is a simple constructor which simply creates the window and sets the properties such as location, default
     * close operation, the size, and the color of the background. It also adds the Key and Mouse input classes from
     * earlier as a KeyListener and a MouseListener. It also adds a MouseMotionListener so that each time the mouse
     * moves it gets the point of the mouse and assigns it to the variable mousePoint in the GameObject class.
     *
     * It also changes the default cursor to a png image.
     *
     * @param comp - The component to be added to the window, this is the game itself
     * @param title - The title of the window
     */
    public Window(Component comp, String title) {
        JFrame frame = new JFrame(title);
        frame.setLocation((int)((screenSize.getWidth()/2)-(gameWidth/2)), (int)((screenSize.getHeight()/2)-(gameHeight/2)));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, comp);
        frame.getContentPane().setBackground(new Color(247,101,184));
        frame.setSize(gameWidth, gameHeight);
        frame.setVisible(true);

        frame.addKeyListener(new KeyInput(GameView.handler));
        frame.addMouseListener(new MouseInput(GameView.handler));
        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mousePoint = e.getPoint();
            }
        });
        //Change mouse image
        Backend.BufferedImageLoader loader = new Backend.BufferedImageLoader();
        BufferedImage cursor = loader.loadImage("/res/Sprites/Cursor.png");

        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0, 0), "cursor");

        frame.getContentPane().setCursor(blankCursor);
    }
}
