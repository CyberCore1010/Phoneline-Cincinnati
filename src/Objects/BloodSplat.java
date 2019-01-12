package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BloodSplat extends GameObject{
    private Point location;
    private BufferedImage splatImage;

    /**This is the constructor for each individual blood splat. It works by choosing a random sprite, and then a random
     * x and y position within a certain range of the player.
     *
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     * @param handler - Object handler used to access images needed
     */
    BloodSplat(int x, int y, int width, int height, int size, ObjectID id, boolean blocking, ObjectHandler handler) {
        super(x, y, width, height, size, id, blocking);
        int type = (int)Math.ceil(Math.random() * 8);
        switch(type) {
            case 1:
                splatImage = handler.bloodSplat1;
                break;
            case 2:
                splatImage = handler.bloodSplat2;
                break;
            case 3:
                splatImage = handler.bloodSplat3;
                break;
            case 4:
                splatImage = handler.bloodSplat4;
                break;
            case 5:
                splatImage = handler.bloodSplat5;
                break;
            case 6:
                splatImage = handler.bloodSplat6;
                break;
            case 7:
                splatImage = handler.bloodSplat7;
                break;
            case 8:
                splatImage = handler.bloodSplat8;
                break;
        }
        int bloodX, bloodY;
        bloodX = (int)(Math.random() * (((x+size) - (x-size)) + 1) + x-size);
        bloodY = (int)(Math.random() * (((y+size) - (y-size)) + 1) + y-size);
        location = new Point(bloodX, bloodY);
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
        g.drawImage(splatImage, location.x, location.y, 50, 50, null);
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
