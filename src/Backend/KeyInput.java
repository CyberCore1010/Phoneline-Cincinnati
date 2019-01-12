package Backend;

import Game.GameHandler;
import Game.GameView;
import Game.LevelSelect;
import Objects.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public class KeyInput extends KeyAdapter{

    private ObjectHandler handler;

    /**
     * @param handler - takes the game handler as a parameter so it can set variables in it.
     */
    KeyInput(ObjectHandler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        /*
        This code is used to set the players movement boolean values. Used in unison with the keyReleased to make the
        player move when the key is pressed and stop when released. This is a better way of moving the player as it
        allows for smoother movement.
         */
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectID.player) {
                if(key == KeyEvent.VK_W) handler.setUp(true);
                if(key == KeyEvent.VK_A) handler.setLeft(true);
                if(key == KeyEvent.VK_S) handler.setDown(true);
                if(key == KeyEvent.VK_D) handler.setRight(true);
            }
        }

        /*
        These buttons are used to reset the game, R always resetting it, and escape only resetting it when the game is
        over (Escape will otherwise just be used to pause the game). The for loops you can see are just used to reset
        the layers of the objects as they change throughout the game.
         */
        if(key == KeyEvent.VK_R || key == KeyEvent.VK_ESCAPE) {
            if(!GameView.noName) {
                if(key == KeyEvent.VK_ESCAPE) {
                    if(GameView.mainMenu) {
                        System.exit(0);
                    }
                    GameView.mainMenu = !GameView.mainMenu;
                    if(GameView.mainMenu) {
                        GameView.mainMenuMusic = true;
                    }
                    if(!GameView.gameOver) {
                        return;
                    }
                    GameView.noName = false;
                    GameView.nameSet = false;
                    GameView.name = "";
                }
                if(GameView.gameOver) {
                    if(GameView.win) {
                        SFXplayer.changeMusic(1);
                        SFXplayer.playMusic();
                    }
                    Player.currentScore = 0;
                    Player.levelScore = 0;
                    GameView.gameOver = false;
                    GameView.win = false;
                    GameView.setScore = false;
                    GameView.clear = false;
                    GameView.clearMusic = false;
                    for(GameObject object: handler.object) {
                        if(object.getId() == ObjectID.enemy) {
                            object.isDead = false;
                        }
                    }
                    if(GameHandler.level != 1) {
                        GameHandler.level = 1;
                        handler.object.clear();
                        LevelSelect.selectLevel(GameHandler.level, handler);
                    }
                } else {
                    Player.currentScore = Player.levelScore;
                }
                for(int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);
                    if(tempObject.getId() == ObjectID.player || tempObject.getId() == ObjectID.enemy || tempObject.getId() == ObjectID.exit) {
                        tempObject.reset();
                    }
                }
                CopyOnWriteArrayList<GameObject> temp = new CopyOnWriteArrayList<>();
                for(int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);
                    if(tempObject.getId() != ObjectID.player && tempObject.getId() != ObjectID.wall && tempObject.getId() != ObjectID.bloodPool && tempObject.getId() != ObjectID.bloodSplat && tempObject.getId() != ObjectID.exit && tempObject.getId() != ObjectID.enemy)
                    {
                        temp.add(tempObject);
                    }
                }
                for(int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);
                    if(tempObject.getId() == ObjectID.enemy) {
                        temp.add(tempObject);
                    }
                }
                for(int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);
                    if(tempObject.getId() == ObjectID.player) {
                        temp.add(tempObject);
                    }
                }
                for(int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);
                    if(tempObject.getId() == ObjectID.exit) {
                        temp.add(tempObject);
                    }
                }
                for(int i = 0; i < handler.object.size(); i++) {
                    GameObject tempObject = handler.object.get(i);
                    if(tempObject.getId() == ObjectID.wall) {
                        temp.add(tempObject);
                    }
                }
                if(GameView.clearMusic) {
                    SFXplayer.changeMusic(1);
                    SFXplayer.playMusic();
                }
                handler.object = temp;
            }
        }

        //Used to display the help menu
        if(key == KeyEvent.VK_H&&!GameView.gameOver) {
            if(!GameView.noName) {
                GameView.helpMenu = !GameView.helpMenu;
            }
        }

        //Used to start the game if in the main menu
        if(key == KeyEvent.VK_ENTER) {
            if(!GameView.noName) {
                if(GameView.mainMenu) {
                    GameView.mainMenu = false;
                    SFXplayer.changeMusic(1);
                    SFXplayer.playMusic();
                }
            }
        }
        //enables debug mode
        if(key == KeyEvent.VK_NUMPAD5) {
            GameView.debug = !GameView.debug;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        //See similar installment in "keyPressed"
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ObjectID.player) {
                if(key == KeyEvent.VK_W) handler.setUp(false);
                if(key == KeyEvent.VK_A) handler.setLeft(false);
                if(key == KeyEvent.VK_S) handler.setDown(false);
                if(key == KeyEvent.VK_D) handler.setRight(false);
            }
        }

        //This just allows the player to type their name if they're in the score screen and haven't set their name
        if(GameView.noName) {
            if(key == KeyEvent.VK_BACK_SPACE) {
                GameView.name = GameView.name.substring(0, GameView.name.length()-1);
                return;
            }
            if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_CAPS_LOCK || key == KeyEvent.VK_CONTROL || key == KeyEvent.VK_SHIFT || key == KeyEvent.VK_ESCAPE) {
                return;
            }
            GameView.name += KeyEvent.getKeyText(e.getKeyCode());
        }
    }
}
