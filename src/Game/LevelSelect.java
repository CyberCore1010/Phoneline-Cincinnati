package Game;

import Objects.ObjectHandler;

public class LevelSelect {
    /**This method simply runs the set up method in the classes of different levels. It's written in such a way that
     * it the game can easily be expanded to include more levels.
     *
     * @param level - Takes an int which denotes which level to select
     * @param handler - Passes the handler in so it can pass it on to the selected level to be used for adding objects
     */
    public static void selectLevel(int level, ObjectHandler handler) {
        switch(level) {
            case 1:
                LevelOne.setUp(handler);
                break;
        }
    }
}
