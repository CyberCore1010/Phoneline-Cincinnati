package Objects;

import Backend.SFXplayer;
import Game.GameView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Car extends GameObject {
    private boolean canLeave = false, doorOpening, doorOpen = false;
    private ObjectHandler handler;
    private BufferedImage exitImage;

    private Timer doorTimer;
    private boolean soundStart = false, soundEnd = false;

    /**This is the constructor for the level exit, it creates a bounding box for collisions and sets the default car
     * sprite. It also creates a new timer for use with animations.
     *
     * @param x - x location of the object
     * @param y - y location of the object
     * @param width - width of the object
     * @param height - height of the object
     * @param size - size of the object (Used if you want square)
     * @param id - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     * @param handler - used to get images
     */
    public Car(int x, int y, int width, int height, int size, ObjectID id, boolean blocking, ObjectHandler handler) {
        super(x, y, width, height, size, id, blocking);
        this.handler = handler;
        boundingBox = new Rectangle(x, y+20, width, height-20);
        exitImage = handler.Car;
        doorTimer = new Timer();
    }

    /**
     * This is the update method for the exit. It uses works by checking if all the enemies are dead. If they are, it
     * will set the exit to active, meaning the player can leave. From that point on it will check if the player
     * is close to the car, and if it is an animation will play showing the door opening. When the door is open, the
     * car has no collisions in the area of the door. If the player enters the car and reaches the middle, the level
     * will end, displaying a modified game over screen showing "You win" instead of game over.
     */
    @Override
    public void update() {
        if(doorOpen) {
            setBlocking(false);
        } else {
            setBlocking(true);
        }
        canLeave = true;
        for(GameObject object: handler.object) {
            if(object.getId() == ObjectID.enemy) {
                if(!object.isDead) {
                    canLeave = false;
                }
            }
        }
        if(canLeave) {
            for(GameObject object: handler.object) {
                if(object.getId() == ObjectID.player) {
                    doorOpening = !GameView.win && (object.y > (y - 30) && object.y < (y+(height/2)));
                }
            }
            if(!GameView.clearMusic) {
                GameView.clear = true;
                GameView.clearMusic = true;
                SFXplayer.playSound("Complete.wav");
                SFXplayer.changeMusic(2);
                SFXplayer.playMusic();
            }
            for(GameObject object: handler.object) {
                if(object.getId() == ObjectID.player) {
                    if(getBounds().intersects(object.getBounds())) {
                        if(object.y > y+(height/2)) {
                            if(GameView.winMusic) {
                                GameView.winMusic = false;
                                SFXplayer.playSound("CarEngine.wav");
                                SFXplayer.changeMusic(3);
                                SFXplayer.playMusic();
                                GameView.mainMenuMusic = false;
                            }
                            if(!GameView.gameOver) {
                                if(!GameView.nameSet) {
                                    GameView.noName = true;
                                    GameView.nameSet = true;
                                }
                                object.isDead = true;
                                GameView.win = true;
                                GameView.gameOver = true;
                                GameView.setScore = true;
                            }
                        }
                    }
                }
            }
            if(doorOpening && !doorOpen) {
                TimerTask open1 = new TimerTask() {
                    @Override
                    public void run() {
                        SFXplayer.playSound("CarOpen.wav");
                        exitImage = handler.CarOpen1;
                    }
                };
                TimerTask open2 = new TimerTask() {
                    @Override
                    public void run() {
                        exitImage = handler.CarOpen2;
                        doorOpen = true;
                    }
                };
                if(!soundStart) {
                    doorTimer.schedule(open1, 0);
                    soundStart = true;
                }
                if(!soundEnd) {
                    doorTimer.schedule(open2, 200);
                    doorOpen = true;
                    soundStart = false;
                    soundEnd = false;
                }
            } else if (!doorOpening && doorOpen) {
                TimerTask close1 = new TimerTask() {
                    @Override
                    public void run() {
                        SFXplayer.playSound("CarClose.wav");
                        exitImage = handler.CarOpen1;
                    }
                };
                TimerTask close2 = new TimerTask() {
                    @Override
                    public void run() {
                        exitImage = handler.Car;
                        doorOpen = false;
                        soundStart = false;
                        soundEnd = false;
                    }
                };
                if(!soundStart) {
                    doorTimer.schedule(close1, 0);
                    soundStart = true;
                }
                if(!soundEnd) {
                    doorTimer.schedule(close2, 200);
                    soundEnd = true;
                }
            }
        }
    }

    /**Draws the sprite, and if in debug mode draws a red bounding box
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(exitImage, x, y+10, width, height, null);
        if(GameView.debug) {
            g.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }
    }

    @Override
    public Rectangle getBounds() {
        return boundingBox;
    }

    @Override
    public void die() {
    }

    @Override
    public void reset() {
        canLeave = false;
    }
}
