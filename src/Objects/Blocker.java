package Objects;

import Game.GameView;

import java.awt.*;

public class Blocker extends GameObject {
    private Rectangle bounds;

    /**Blocker is just a invisible (asside from a red rectangle in debug mode) object that the player can't move through
     *
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     */
    public Blocker(int x, int y, int width, int height, int size, ObjectID id, boolean blocking) {
        super(x, y, width, height, size, id, blocking);
        bounds = new Rectangle(x, y, width, height);

    }

    @Override
    public void update() {
    }

    /**This method will render the a red outline of the blocker if debug mode is enabled
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void render(Graphics g) {
        if(GameView.debug) {
            g.setColor(Color.RED);
            g.drawRect(x, y, width, height);
        }
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public void die() {
    }

    @Override
    public void reset() {
    }
}
