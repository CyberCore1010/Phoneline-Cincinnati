package Objects;

import java.awt.*;

public class VerticalWall extends Wall {

    /**This is the constructor is used for making the vertical walls. It creates three rectangles, one for the main
     * wall, one for the left raised section, and one for the right rectangle. This is then used to make walls that
     * look nice.
     *
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     */
    public VerticalWall(int x, int y, int width, int height, int size, ObjectID id, boolean blocking) {
        super(x, y, width, height, size, id, blocking);
        this.width = wallSize;
        this.height = size;
        boundingBox = new Rectangle(x, y, wallSize, size);
        wallL = new Rectangle(x, y, wallSize/3, size);
        wallR = new Rectangle(x+(wallSize-(wallSize/3)), y, wallSize/3, size);
        wall = new Rectangle(x, y, wallSize, size);
    }
}
