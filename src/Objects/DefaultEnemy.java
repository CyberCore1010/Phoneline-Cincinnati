package Objects;

import Backend.SFXplayer;
import Game.GameView;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultEnemy extends Character{

    private GameObject player;
    private Rectangle detectionBox;
    private Line2D line;
    private boolean playerDetected = false;
    private int attackInterval = 25;
    private int attackIntervalStart = attackInterval;

    private boolean noMove = false;

    /**The constructor of the DefaultEnemy objects first sets the super variables, and then sets each sprite to the
     * correct one. It also creates a line to be used in raycasting, and uses a creates a Rectangle to be used for the
     * bounding box for the directions the enemies are facing.
     *
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     * @param handler - handler is used for getting images and modifying order
     * @param rotation - The default rotation of the object
     */
    public DefaultEnemy(int x, int y, int width, int height, int size, ObjectID id, boolean blocking, ObjectHandler handler, double rotation) {
        super(x, y, width, height, size, id, blocking, handler, rotation);

        currentSprite = handler.defaultSpriteEnemy;
        attackSprite = handler.attackSpriteEnemy;
        deadSprite1 = handler.deadSprite1Enemy;
        deadSprite2 = handler.deadSprite2Enemy;
        deadSprite3 = handler.deadSprite3Enemy;
        deadSprite4 = handler.deadSprite4Enemy;
        defaultSprite = currentSprite;

        line = new Line2D.Float(0, 0, 0, 0);

        detectionBox = new Rectangle(x-(size*2), y-(size*2), size*4, size*4);
    }

    /**
     * This is the main update method. It basically works by saying that first if the game is in debug mode or if they
     * are dead return. If it passes these checks it will then set the current instance of the player as the player
     * reference, to be used for tracking. It then runs each seperate function, such as collision, detectPlayers
     * (Only if there is a player to detect), the movement method, the rotation method, and finally the attack method
     */
    @Override
    public void update() {
        if(GameView.debug) {
            return;
        }
        if(isDead) {
            return;
        }
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.player) {
                player = tempObject;
            }
        }
        collision();
        if(player!=null) {
            detectPlayers();
        }
        movement();
        rotation();
        attack();
    }

    /**
     * The detectPlayers class works by finding which angle the enemy is facing, and setting the detectionBox there.
     * If there are enemies inside of this box, then check if there is a wall in the way by drawing lines between the
     * enemy and player and checking if it intersects with a wall. If it does, don't detect the player, if it doesn't
     * do.
     */
    private void detectPlayers() {
        if(angle >= -2.25 && angle <= -0.75) { //top
            detectionBox.x = (x-(size/4)-250); detectionBox.y = (y-(size/4)-1060); detectionBox.width = size+500; detectionBox.height = size+1000;
        } else if (angle > -0.75 && angle < 0.75) { //right
            detectionBox.x = (x-(size/4))+20; detectionBox.y = (y-(size/4))-250; detectionBox.width = size+1000; detectionBox.height = size+500;
        } else if (angle >= 0.75 && angle <= 2.25) { //bottom
            detectionBox.x = (x-(size/4))-250; detectionBox.y = (y-(size/4))+20; detectionBox.width = size+500; detectionBox.height = size+1000;
        } else { //left
            detectionBox.x = (x-(size/4))-1060; detectionBox.y = (y-(size/4))-250; detectionBox.width = size+1000; detectionBox.height = size+500;
        }
        playerDetected = detectionBox.getBounds().intersects(player.getBounds());
        if(detectionBox.getBounds().intersects(player.getBounds())) {
            line.setLine(this.x, this.y, player.x, player.y);
            for(int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if(tempObject.getId() == ObjectID.wall) {
                    if(line.intersects(tempObject.getBounds())) playerDetected = false;
                }
            }
        } else {
            line.setLine(0,0,0,0);
        }
    }

    /**
     * The movement method works by checking first if the the player is not detected. If it isn't it will leave the
     * method. If the player is dead, the enemy will move back a little bit, and then stop movement. If it does detect
     * the player, and the player is not dead, it will move towards the players x and y position.
     */
    @SuppressWarnings("Duplicates")
    private void movement() {
        if(!noMove) {
            if(!playerDetected) {
                return;
            }
            if(player.isDead) {
                if(player.x != x) {
                    if(player.x < x) velX = 10;
                    else velX = -10;
                }
                if(player.y != y) {
                    if(player.y < y) velY = 10;
                    else velY = -10;
                }
                x += velX;
                y += velY;
                velX = 0;
                velY = 0;
                noMove = true;
            }
            int moveSpeed = 5;
            if(player.x != x) {
                if(player.x < x) velX = -moveSpeed;
                else velX = moveSpeed;
            } else velX = 0;
            if(player.y != y) {
                if(player.y < y) velY = -moveSpeed;
                else velY = moveSpeed;
            } else velY = 0;
            x += velX;
            y += velY;
        }
    }

    /**
     * The rotation method works in a similar way to the movement method in as much as it first checks if the player is
     * detected, and only if it is will it execute the code. The code basically just finds the x and y position of the
     * player and finds the angle between the enemy and them.
     */
    @SuppressWarnings("Duplicates")
    private void rotation() {
        if(!playerDetected) {
            return;
        }
        double playerX = 0;
        double playerY = 0;
        try {
            playerX = (player.getX()-x);
            playerY = (player.getY()-y);
        } catch (Exception ignored) {}
        angle = Math.atan2(playerY, playerX);
    }

    /**
     * The attack method works by simply checking if first the player is actually detected. and then checking if the
     * bounds intersect with the player. And if they do, it will play a random attack sound, and then run the die
     * method on the player object. Afterwords it will set the attack to false and the sprite back to the default
     * sprite.
     */
    @SuppressWarnings("Duplicates")
    private void attack() {
        if(playerDetected) {
            if(getBounds().intersects(player.getBounds())) {
                if(!player.isDead) {
                    int attackSound = (int) Math.ceil(Math.random() * 2);
                    if(attackSound == 1) {
                        SFXplayer.playSound("Cut1.wav");
                    } else {
                        SFXplayer.playSound("Cut2.wav");
                    }
                    currentSprite = attackSprite;
                    player.die();
                }

            }
            attackInterval--;
            if(attackInterval < 0) {
                handler.setAttack(false);
                currentSprite = defaultSprite;
                attackInterval = attackIntervalStart;
            }
        }
    }

    /**
     * This method works by checking if the enemy is already dead. If it is it won't die again. It then will set the
     * sprite to a death sprite, passing in if the player is detected as this changes the sprite from facing up to down
     * depending on the boolean's value. It then does a series of for loops and additions to the list, re-layering them
     * and adding new objects such as the blood pools and blood splats.
     */
    @Override
    public void die() {
        if(isDead) {
            return;
        }
        Player.currentScore+=1500;
        deathSprite(playerDetected);
        size += 30;
        SFXplayer.playSound("Hit.wav");
        isDead = true;
        CopyOnWriteArrayList<GameObject> temp = new CopyOnWriteArrayList<>();
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.floor || tempObject.getId() == ObjectID.bloodPool) {
                temp.add(tempObject);
            }
        }
        if(playerDetected) {
            temp.add(new BloodPool(x, y, width, height, size, angle, ObjectID.bloodPool, false, handler, 2, false));
        } else {
            temp.add(new BloodPool(x, y, width, height, size, angle, ObjectID.bloodPool, false, handler, 1, false));
        }
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.furniture) {
                temp.add(tempObject);
            }
        }
        for(int i = 0; i < 20; i++) {
            temp.add(new BloodSplat(x+(width/2), y+(height/2), 0, 0, size, ObjectID.bloodSplat, false, handler));
        }
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.enemy) {
                if(tempObject.isDead) {
                    temp.add(tempObject);
                }
            }
        }
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.enemy) {
                if(!tempObject.isDead) {
                    if(tempObject != this) {
                        temp.add(tempObject);
                    }
                }
            }
        }
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() != ObjectID.floor && tempObject.getId() != ObjectID.bloodPool && tempObject.getId() != ObjectID.enemy && tempObject.getId() != ObjectID.furniture) {
                temp.add(tempObject);
            }
        }
        handler.object = temp;
    }

    /**
     * The reset method simply resets all of the objects properties to the default properties
     */
    @Override
    public void reset() {
        currentSprite = defaultSprite;
        x = defaultPosX;
        y = defaultPosY;
        angle = defaultAngle;
        isDead = false;
        noMove = false;
        size = defaultSize;
    }

    /**The render method simply draws the character sprite by sending the variables to the drawCharacter method inside
     * of the Character GameObject (Which this extends from)
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void render(Graphics g) {
        drawCharacter(g, x, y, size, angle, currentSprite);

        g.setColor(Color.RED);
        //g.drawLine((int)line.getX1(), (int)line.getY1(), (int)line.getX2(), (int)line.getY2());

        //Draw detection box
        //g.drawRect(detectionBox.x, detectionBox.y, detectionBox.width, detectionBox.height);
    }
}

