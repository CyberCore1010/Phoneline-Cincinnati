package Objects;

import java.awt.*;

public class HorizontalWall extends Wall {

    /**This is the constructor is used for making the horizontal walls. It creates three rectangles, one for the main
     * wall, one for the left raised section, and one for the right rectangle. This is then used to make walls that
     * look nice.
     *
     * @param x - x location of the cursor
     * @param y - y location of the cursor
     * @param width - width of the cursor
     * @param height - height of the cursor
     * @param size - size of the cursor (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     */
    public HorizontalWall(int x, int y, int width, int height, int size, ObjectID id, boolean blocking) {
        super(x, y, width, height, size, id, blocking);
        this.width = size;
        this.height = wallSize;
        boundingBox = new Rectangle(x, y, size, wallSize);
        wallL = new Rectangle(x, y, size, wallSize/3);
        wallR = new Rectangle(x, y+(wallSize-(wallSize/3)), size, wallSize/3);
        wall = new Rectangle(x, y, size, wallSize);

    }
}
