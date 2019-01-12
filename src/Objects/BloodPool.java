package Objects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class BloodPool extends GameObject {

    private ObjectHandler handler; //handler used for getting the images
    private double angle; //The angular rotation of the blood pool
    private int bloodTime, pos; //The position of the blood pool and the incremental timer
    private boolean player; //Whether the blood pool belongs to a player
    private BufferedImage bloodPool; //The blood pool sprite

    /**
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     * @param handler - the handler is used for getting the images needed
     * @param pos - the position of the blood. 0 means a body hit, 1 means a head hit when facing towards the source
     *            and anything higher for a head hit when facing away from the source
     * @param player - passes in a boolean stating whether the blood is from the player or an enemy. This is needed as
     *               the player has a death type where the blood needs to be centered
     */
    BloodPool(int x, int y, int width, int height, int size, double rotation, ObjectID id, boolean blocking, ObjectHandler handler, int pos , boolean player) {
        super(x, y, width, height, size, id, blocking);
        this.angle = rotation;
        this.handler = handler;
        bloodTime = 0;
        this.pos = pos;
        this.player = player;
    }

    /**
     * The update method for the blood pool is used to update the sprite to a larger one as time progresses. Once it's
     * reached it's final size it no longer re-sizes.
     */
    @Override
    public void update() {
        if(bloodTime <= 100) {
            if(bloodTime == 20) {
                bloodPool = handler.blood1;
            } else if(bloodTime == 40) {
                bloodPool = handler.blood2;
            } else if(bloodTime == 60) {
                bloodPool = handler.blood3;
            } else if(bloodTime == 80) {
                bloodPool = handler.blood4;
            } else if(bloodTime == 100) {
                bloodPool = handler.blood5;
            }
            bloodTime++;
        }
    }

    /**The render method renders the blood pool in the right location by using AffineTransform to rotate the blood
     * along with the character. It then uses a series of if statements to check if it needs to increase, decrease or
     * do nothing with the x location.
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int characterX = (size/2);
        int characterY = (size/2);
        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(characterX+(x-(size/2)), (characterY+(y-size/2)));
        g2d.rotate(angle);
        g2d.translate(-characterX, -characterY);
        if(pos >= 1) {
            if(player) {
                g2d.drawImage(bloodPool, -25, 0, size, size, null);
            } else {
                if( pos == 1) {
                    g2d.drawImage(bloodPool, 25, 0, size, size, null);
                } else {
                    g2d.drawImage(bloodPool, -25, 0, size, size, null);
                }
            }
        } else {
            g2d.drawImage(bloodPool, 0, 0, size, size, null);
        }
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
