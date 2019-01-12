package Objects;

import java.awt.*;

public class Cursor extends GameObject {
    private Rectangle bounds; //Used for the bounding box for the object for use with collisions.

    /**The Cursor constructor sets the location of the cursor by default. It also sets the size of it.
     *
     * @param x - x location of the cursor
     * @param y - y location of the cursor
     * @param width - width of the cursor
     * @param height - height of the cursor
     * @param size - size of the cursor (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     */
    public Cursor(int x, int y, int width, int height, int size, ObjectID id, boolean blocking) {
        super(x, y, width, height, size, id, blocking);
        bounds = new Rectangle(x-(size/2), y-(size/2), size*2, size*2);
    }

    /**
     * This method is called when the GameObject's update method is ran. It sets the x and y position to the current
     * mouse position, adding the current camera x and y to it's own to prevent it from having problems when the
     * player moves.
     */
    @Override
    public void update() {
        if(mousePoint != null) {
            x = (mousePoint.x)+(int)Camera.x;
            y = (mousePoint.y-25)+(int)Camera.y;
        }
    }

    /**
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void render(Graphics g) {
        //Draw bounds
        //g.setColor(Color.RED);
        //g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);

        //Draw cursor
        //g.fillRect(x-((size/2)-8), y-((size/2)-8), size, size);
    }

    /**
     * @return - returns the bounding box of the object.
     */
    @Override
    public Rectangle getBounds() {
        bounds.x = x-(size/2);
        bounds.y = y-(size/2);
        return bounds;
    }

    @Override
    public void die() {
    }

    @Override
    public void reset() {
    }

}
