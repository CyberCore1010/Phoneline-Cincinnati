package Objects;

import Game.GameView;

import java.awt.*;

public class Wall extends GameObject {

    Rectangle wallL;
    Rectangle wallR;
    Rectangle wall;
    static int wallSize = 20;

    /**
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     */
    Wall(int x, int y, int width, int height, int size, ObjectID id, boolean blocking) {
        super(x, y, width, height, size, id, blocking);
    }

    @Override
    public void update() {
    }

    /**This render method paints the wall. It paints three rectangles in such a way that
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(wall.x, wall.y, wall.width, wall.height);
        g.setColor(Color.GRAY);
        g.fill3DRect(wallL.x, wallL.y, wallL.width, wallL.height, true);
        g.fill3DRect(wallR.x, wallR.y, wallR.width, wallR.height, true);
        g.fill3DRect(wall.x, wall.y, wallSize, wallSize, true);
        g.fill3DRect(wall.x+(wall.width-wallSize), wall.y, wallSize, wallSize, true);

        //Draw wall bounding box
        if(GameView.debug) {
            g.setColor(Color.red);
            g.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }
    }

    /**
     * @return - returns a bounding box of the current wall's dimensions
     */
    @Override
    public Rectangle getBounds() {
        boundingBox.x = x;
        boundingBox.y = y;
        return boundingBox;
    }

    @Override
    public void die() {
    }

    @Override
    public void reset() {

    }
}
