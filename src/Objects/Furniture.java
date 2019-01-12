package Objects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Furniture extends GameObject{

    private BufferedImage furnitureType;

    private double rotation;

    private int centerX = x+(width/2);
    private int centerY = y+(height/2);

    /**
     * @param x - x location of the object
     * @param y - y location of the object
     * @param size - size of the object (Used if you want square)
     * @param width - width of the object
     * @param height - height of the object
     * @param rotation - Sets the rotation of the furniture object
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     * @param furnitureType - the sprite to be used as furniture
     */
    public Furniture(int x, int y, int size, int width, int height, double rotation, ObjectID id, boolean blocking, BufferedImage furnitureType) {
        super(x, y, width, height, size, id, blocking);
        this.rotation = rotation;
        this.furnitureType = furnitureType;
    }

    @Override
    public void update() {
    }

    /**This method just paints the image at a position.
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(centerX, centerY);
        g2d.rotate(rotation);
        g2d.drawImage(furnitureType, 0, 0, width, height, null);
        g2d.translate(x, y);
        g2d.setTransform(oldAT);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void die() {

    }

    @Override
    public void reset() {

    }
}
