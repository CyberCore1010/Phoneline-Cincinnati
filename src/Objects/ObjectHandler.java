package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectHandler {
    //This is the collection that stores each game object, made public, allowing the game to access them easily
    //throughout the code
    public CopyOnWriteArrayList<GameObject> object = new CopyOnWriteArrayList<>();

    //These are the values used for the player. The player can access these as they have an instance of the
    //ObjectHandler they can reference.
    private boolean up = false, down = false, right = false, left = false, attack = false;

    //These BufferedImages are used for the players sprites in it's different states. For example it has images for the
    //player in his default position, a image for the player in it's attack position, and four images for the player's
    //various death states.
    BufferedImage defaultSpritePlayer;
    BufferedImage attackSpritePlayer;
    BufferedImage deadSprite1Player;
    BufferedImage deadSprite2Player;
    BufferedImage deadSprite3Player;
    BufferedImage deadSprite4Player;

    //These BufferedImages are used for the Enemies sprites. It also has the default and attack sprites, however it has
    //eight death sprites, 4 for forward facing deaths, and 4 for backward facing ones
    BufferedImage defaultSpriteEnemy;
    BufferedImage attackSpriteEnemy;
    BufferedImage deadSprite1Enemy;
    BufferedImage deadSprite2Enemy;
    BufferedImage deadSprite3Enemy;
    BufferedImage deadSprite4Enemy;
    BufferedImage deadSprite5Enemy;
    BufferedImage deadSprite6Enemy;
    BufferedImage deadSprite7Enemy;
    BufferedImage deadSprite8Enemy;

    //These BufferedImages are used for the blood and gore in the game. It holds 5 states of the blood pool, each one
    //larger than the previous. It also holds 8 different kinds of blood splats
    BufferedImage blood1;
    BufferedImage blood2;
    BufferedImage blood3;
    BufferedImage blood4;
    BufferedImage blood5;
    BufferedImage bloodSplat1;
    BufferedImage bloodSplat2;
    BufferedImage bloodSplat3;
    BufferedImage bloodSplat4;
    BufferedImage bloodSplat5;
    BufferedImage bloodSplat6;
    BufferedImage bloodSplat7;
    BufferedImage bloodSplat8;

    //These are simply the BufferedImages used for the furniture in the game level.
    public BufferedImage PoolTable;
    public BufferedImage CocaineCrate;
    public BufferedImage Bath;
    public BufferedImage Toilet;
    public BufferedImage Sink;
    public BufferedImage Bar;
    public BufferedImage Couch;
    public BufferedImage LargeCouch;
    public BufferedImage YellowCouch;
    public BufferedImage Table1;
    public BufferedImage Table2;
    public BufferedImage Table3;
    public BufferedImage Table4;
    public BufferedImage Fountain;
    public BufferedImage Booth;
    public BufferedImage Game1;
    public BufferedImage Game2;
    public BufferedImage Game3;
    public BufferedImage Game4;
    public BufferedImage Game5;
    public BufferedImage KitchenCounter;
    public BufferedImage KitchenSink;
    public BufferedImage Fridge;
    public BufferedImage Buffe;
    public BufferedImage BuffePlates;

    //These are the BufferedImages used for the Car, or the exit. It allows for a short three frame animation when the
    //player approaches or leaves the car area.
    BufferedImage Car;
    BufferedImage CarOpen1;
    BufferedImage CarOpen2;

    //These two BufferedImages are used for the win and menu backgrounds, both are similar however slightly different
    public BufferedImage Win;
    public BufferedImage Menu;

    /**
     * The ObjectHandler constructor simply loads the images in to their respective objects
     */
    public ObjectHandler() {
        /////PlayerSprites/////
        Backend.BufferedImageLoader loader = new Backend.BufferedImageLoader();

        defaultSpritePlayer = loader.loadImage("/Sprites/Player/DefaultPlayer.png");
        attackSpritePlayer = loader.loadImage("/Sprites/Player/AttackPlayer.png");
        deadSprite1Player = loader.loadImage("/Sprites/Player/DeadPlayer1.png");
        deadSprite2Player = loader.loadImage("/Sprites/Player/DeadPlayer2.png");
        deadSprite3Player = loader.loadImage("/Sprites/Player/DeadPlayer3.png");
        deadSprite4Player = loader.loadImage("/Sprites/Player/DeadPlayer4.png");
        /////EnemySprites/////
        defaultSpriteEnemy = loader.loadImage("/Sprites/DefaultEnemy/DefaultEnemy.png");
        attackSpriteEnemy = loader.loadImage("/Sprites/DefaultEnemy/AttackEnemy.png");
        deadSprite1Enemy = loader.loadImage("/Sprites/DefaultEnemy/DeadEnemy1Up.png");
        deadSprite2Enemy = loader.loadImage("/Sprites/DefaultEnemy/DeadEnemy2Up.png");
        deadSprite3Enemy = loader.loadImage("/Sprites/DefaultEnemy/DeadEnemy3Up.png");
        deadSprite4Enemy = loader.loadImage("/Sprites/DefaultEnemy/DeadEnemy4Up.png");
        deadSprite5Enemy = loader.loadImage("/Sprites/DefaultEnemy/DeadEnemy1Down.png");
        deadSprite6Enemy = loader.loadImage("/Sprites/DefaultEnemy/DeadEnemy2Down.png");
        deadSprite7Enemy = loader.loadImage("/Sprites/DefaultEnemy/DeadEnemy3Down.png");
        deadSprite8Enemy = loader.loadImage("/Sprites/DefaultEnemy/DeadEnemy4Down.png");
        /////BloodSprites/////
        blood1 = loader.loadImage("/Sprites/Gore/blood1.png");
        blood1 = loader.loadImage("/Sprites/Gore/blood2.png");
        blood2 = loader.loadImage("/Sprites/Gore/blood3.png");
        blood3 = loader.loadImage("/Sprites/Gore/blood4.png");
        blood4 = loader.loadImage("/Sprites/Gore/blood5.png");
        blood5 = loader.loadImage("/Sprites/Gore/blood6.png");
        bloodSplat1 = loader.loadImage("/Sprites/Gore/bloodSplat1.png");
        bloodSplat2 = loader.loadImage("/Sprites/Gore/bloodSplat2.png");
        bloodSplat3 = loader.loadImage("/Sprites/Gore/bloodSplat3.png");
        bloodSplat4 = loader.loadImage("/Sprites/Gore/bloodSplat4.png");
        bloodSplat5 = loader.loadImage("/Sprites/Gore/bloodSplat5.png");
        bloodSplat6 = loader.loadImage("/Sprites/Gore/bloodSplat6.png");
        bloodSplat7 = loader.loadImage("/Sprites/Gore/bloodSplat7.png");
        bloodSplat8 = loader.loadImage("/Sprites/Gore/bloodSplat8.png");

        /////FurnitureSprites/////
        PoolTable = loader.loadImage("/Sprites/Furniture/PoolTable.png");
        CocaineCrate = loader.loadImage("/Sprites/Furniture/CocaineCrate.png");
        Bath = loader.loadImage("/Sprites/Furniture/Bath.png");
        Toilet = loader.loadImage("/Sprites/Furniture/Toilet.png");
        Couch = loader.loadImage("/Sprites/Furniture/Couch.png");
        LargeCouch = loader.loadImage("/Sprites/Furniture/LargeCouch.png");
        YellowCouch = loader.loadImage("/Sprites/Furniture/YellowCouch.png");
        Sink = loader.loadImage("/Sprites/Furniture/Sink.png");
        Bar = loader.loadImage("/Sprites/Furniture/Bar.png");
        Fountain = loader.loadImage("/Sprites/Furniture/Fountain.png");
        Table1 = loader.loadImage("/Sprites/Furniture/Table1.png");
        Table2 = loader.loadImage("/Sprites/Furniture/Table2.png");
        Table3 = loader.loadImage("/Sprites/Furniture/Table3.png");
        Table4 = loader.loadImage("/Sprites/Furniture/Table4.png");
        Booth = loader.loadImage("/Sprites/Furniture/Booth.png");
        Game1 = loader.loadImage("/Sprites/Furniture/Game1.png");
        Game2 = loader.loadImage("/Sprites/Furniture/Game2.png");
        Game3 = loader.loadImage("/Sprites/Furniture/Game3.png");
        Game4 = loader.loadImage("/Sprites/Furniture/Game4.png");
        Game5 = loader.loadImage("/Sprites/Furniture/Game5.png");
        KitchenCounter = loader.loadImage("/Sprites/Furniture/KitchenCounter.png");
        KitchenSink = loader.loadImage("/Sprites/Furniture/KitchenSink.png");
        Fridge = loader.loadImage("/Sprites/Furniture/Fridge.png");
        Buffe = loader.loadImage("/Sprites/Furniture/Buffe.png");
        BuffePlates = loader.loadImage("/Sprites/Furniture/BuffePlates.png");
        /////CarSprites/////
        Car = loader.loadImage("/Sprites/Car.png");
        CarOpen1 = loader.loadImage("/Sprites/CarOpen1.png");
        CarOpen2 = loader.loadImage("/Sprites/CarOpen2.png");
        /////Menus/////
        Win = loader.loadImage("/Sprites/WinBack.png");
        Menu = loader.loadImage("/Sprites/Menu.png");
    }

    /**
     * The update method will just loop through all objects in the handler and update them
     */
    public void update() {
        for (GameObject tempObject : object) {
            tempObject.update();
        }
    }

    /**The render method will just loop through all objects in the handler and render them
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    public void render(Graphics g) {
        for (GameObject tempObject : object) {
            tempObject.render(g);
        }
    }

    /**
     * @param tempObject - The object to add to the list
     */
    public void addObject(GameObject tempObject) {
        //Adds object to list
        object.add(tempObject);
    }

    /// Getters and Setters start ///

    boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    boolean isAttack() {
        return attack;
    }

    public void setAttack(boolean attack) {
        this.attack = attack;
    }

    /// Getters and Setters end ///
}
