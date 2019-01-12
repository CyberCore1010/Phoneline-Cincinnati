package Objects;

import Game.GameView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Character extends GameObject {

    BufferedImage currentSprite;
    BufferedImage attackSprite;
    BufferedImage deadSprite1;
    BufferedImage deadSprite2;
    BufferedImage deadSprite3;
    BufferedImage deadSprite4;
    BufferedImage defaultSprite;

    int defaultPosX, defaultPosY, defaultSize;
    double angle, defaultAngle;

    ObjectHandler handler;

    /**
     *
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     * @param handler - used for locating other game objects and changing sprites
     * @param rotation - the default rotation  of the character
     */
    Character(int x, int y, int width, int height, int size, ObjectID id, boolean blocking, ObjectHandler handler, double rotation) {
        super(x, y, width, height, size, id, blocking);
        this.handler = handler;
        this.defaultPosX = x;
        this.defaultPosY = y;
        this.angle = rotation;
        this.defaultAngle = angle;
        this.defaultSize = size;
        boundingBox = new Rectangle(x-(size/4), y-(size/4), size/2, size/2);
    }

    /**This method uses AffineTransform to find the correct rotation to draw the character at. It works by setting the
     * center x and y to the current x and y with the addition of the size of the character divided by two. It then
     * rotates the object around this axis, and returns it to it's original position. If the game is in debug mode it
     * will draw a small red box where the bounds are.
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     * @param x - x location to draw to
     * @param y - y location to draw to
     * @param size - size of the sprite
     * @param rotation - rotation to draw the sprite at
     * @param sprite - the sprite to draw
     */
    void drawCharacter(Graphics g, int x, int y, int size, double rotation, BufferedImage sprite) {
        Graphics2D g2d = (Graphics2D) g.create();

        int characterX = (size / 2);
        int characterY = (size / 2);
        AffineTransform oldAT = g2d.getTransform();
        g2d.translate(characterX+(x-(size/2)), characterY+(y-size/2));
        g2d.rotate(rotation);
        g2d.translate(-characterX, -characterY);
        g2d.drawImage(sprite, 0, 0, size, size, null);
        g2d.setTransform(oldAT);

        if(GameView.debug) {
            g.setColor(Color.RED);
            g.drawRect(x-(size/4), y-(size/4), size/2, size/2);
        }
    }

    /**This method will check if a character is colliding with a object that can block. If it is it will check which
     * side it is colliding with, and reverse the characters velocity in that direction
     */
    void collision() {
        if(GameView.debug) {
            return;
        }
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getBlocking()) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    boolean xBlock = false;
                    boolean yBlock = false;
                    if(x <= tempObject.getBounds().x || x >= tempObject.getBounds().x+tempObject.getBounds().width) {
                        xBlock = true;
                    }
                    if(y <= tempObject.getBounds().y || y >= tempObject.getBounds().y+tempObject.getBounds().height) {
                        yBlock = true;
                    }
                    if(xBlock && !yBlock) {
                        x += velX * -1;
                    }
                    if(!xBlock && yBlock) {
                        y += velY * -1;
                    }
                }
            }
        }
    }

    /**This method is used to set the sprite of a character when they die. It works by first taking a boolean and
     * using this to know which set of sprites to display. It then chooses a random number between 1 and 4, and then
     * sets the current characters sprite to that death sprite.
     *
     * @param faceUp - used to know whether to get a face up or face down sprite
     * @return - returns an int 0 if the hit is a body hit and 1 if the hit is a face hit
     */
    @SuppressWarnings("Duplicates")
    int deathSprite(Boolean faceUp) {
        int deathType = (int) Math.ceil(Math.random() * 4);
        if (faceUp) {
            switch(deathType) {
                case 1:
                    currentSprite = deadSprite1;
                    break;
                case 2:
                    currentSprite = deadSprite2;
                    break;
                case 3:
                    currentSprite = deadSprite3;
                    return 0;
                case 4:
                    currentSprite = deadSprite4;
                    break;
            }
        } else {
            switch(deathType) {
                case 1:
                    currentSprite = handler.deadSprite5Enemy;
                    break;
                case 2:
                    currentSprite = handler.deadSprite6Enemy;
                    break;
                case 3:
                    currentSprite = handler.deadSprite7Enemy;
                    break;
                case 4:
                    currentSprite = handler.deadSprite8Enemy;
                    break;
            }
        }
        return 1;
    }

    /**@return - returns a bounding box used for collisions and damage
     */
    @Override
    public Rectangle getBounds() {
        boundingBox.x = x-(size/4);
        boundingBox.y = y-(size/4);
        return boundingBox;
    }
}
