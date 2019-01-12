package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Floor extends GameObject{

    private BufferedImage floorImage;

    private Rectangle bounds;

    /**The floor is just an image of a certain size.
     *
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     * @param floorType - The sprite to be used
     */
    public Floor(int x, int y, int size, int width, int height, ObjectID id, boolean blocking, int floorType) {
        super(x, y, width, height, size, id, blocking);
        this.width = width;
        this.height = height;
        String imagePath = "/Sprites/Floor/DefaultFloor.png";
        switch(floorType) {
            case 1:
                imagePath = "/Sprites/Floor/BlackAndWhiteTile.png";
                break;
            case 2:
                imagePath = "/Sprites/Floor/BlackCarpet.png";
                break;
            case 3:
                imagePath = "/Sprites/Floor/DefaultFloor.png";
                break;
            case 4:
                imagePath = "/Sprites/Floor/GreenSquareTile.png";
                break;
            case 5:
                imagePath = "/Sprites/Floor/RedBrick.png";
                break;
            case 6:
                imagePath = "/Sprites/Floor/WhiteTile.png";
                break;
            //temp
        }
        floorImage = loader.loadImage(imagePath);
        bounds = new Rectangle(x, y, width, height);
    }

    @Override
    public void update() {

    }

    /**Creates a texture and fills a rectangle with that texture
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        TexturePaint texture = new TexturePaint(floorImage, new Rectangle(0,0,size, size));
        g2d.setPaint(texture);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.fillRect(x, y, width, height);
    }

    /**
     * @return - returns the bounding box for the object
     */
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
