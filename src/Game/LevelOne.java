package Game;

import Objects.*;

public class LevelOne {
    /**This is the method that simply just adds all the objects needed for the game to run to the handler. It's called
     * from levelSelect.
     *
     * @param handler handler is passed in so that it can add objects to it.
     */
    public static void setUp(ObjectHandler handler) {
        /////FLOORS/////
        GameView.handler.addObject(new Floor(40, 80, 30, 700, 380, ObjectID.floor, false, 3));
        GameView.handler.addObject(new Floor(40, 80, 30, 300, 180, ObjectID.floor, false, 4));

        GameView.handler.addObject(new Floor(25, 460, 100, 675, 435, ObjectID.floor, false, 2));
        GameView.handler.addObject(new Floor(696, 80, 40, 200, 825, ObjectID.floor, false, 1));
        GameView.handler.addObject(new Floor(880, 405, 40, 940, 160, ObjectID.floor, false, 1));
        GameView.handler.addObject(new Floor(900, 60, 50, 920, 345, ObjectID.floor, false, 6));
        GameView.handler.addObject(new Floor(894, 580, 30, 925, 325, ObjectID.floor, false, 3));

        /////FURNITURE SPAWN///
        //Main room 1
        GameView.handler.addObject(new Furniture(-10, 745, 0, 100, 100, 0, ObjectID.furniture, false, handler.Couch));
        GameView.handler.addObject(new Furniture(-40, 390, 0, 150, 150, 0, ObjectID.furniture, false, handler.LargeCouch));
        GameView.handler.addObject(new Furniture(150, 550, 0, 100, 50, 0, ObjectID.furniture, false, handler.Table1));
        GameView.handler.addObject(new Blocker(200, 575, 100, 50, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Furniture(400, 550, 0, 100, 50, 0, ObjectID.furniture, false, handler.Table2));
        GameView.handler.addObject(new Blocker(450, 575, 100, 50, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Furniture(150, 750, 0, 100, 50, 0, ObjectID.furniture, false, handler.Table3));
        GameView.handler.addObject(new Blocker(200, 775, 100, 50, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Furniture(400, 750, 0, 100, 50, 0, ObjectID.furniture, false, handler.Table4));
        GameView.handler.addObject(new Blocker(450, 775, 100, 50, 0, ObjectID.blocker, true));
        //Toilet (top left)
        GameView.handler.addObject(new Furniture(40, 55, 0, 100, 50, 1.575, ObjectID.furniture, false, handler.Bath));
        GameView.handler.addObject(new Blocker(40, 75, 50, 105, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Furniture(320, 70, 0, 30, 50, 1.575, ObjectID.furniture, false, handler.Toilet));
        GameView.handler.addObject(new Furniture(285, 110, 0, 30, 50, 0.025, ObjectID.furniture, false, handler.Sink));
        //Game room (right of toilet)
        GameView.handler.addObject(new Furniture(-10, 300, 0, 100, 100, 0, ObjectID.furniture, false, handler.YellowCouch));
        GameView.handler.addObject(new Furniture(615, 230, 0, 150, 150, 1.575, ObjectID.furniture, false, handler.Booth));
        GameView.handler.addObject(new Furniture(590, 45, 0, 50, 75, 0, ObjectID.furniture, false, handler.Game1));
        GameView.handler.addObject(new Furniture(530, 45, 0, 50, 75, 0, ObjectID.furniture, false, handler.Game2));
        GameView.handler.addObject(new Furniture(470, 45, 0, 50, 75, 0, ObjectID.furniture, false, handler.Game3));
        GameView.handler.addObject(new Furniture(410, 45, 0, 50, 75, 0, ObjectID.furniture, false, handler.Game4));
        GameView.handler.addObject(new Furniture(350, 45, 0, 50, 75, 0, ObjectID.furniture, false, handler.Game5));
        GameView.handler.addObject(new Blocker(333, 63, 360, 80, 0, ObjectID.blocker, true));
        //Main room 2 (right of spawn)
        GameView.handler.addObject(new Furniture(950, 650, 0, 100, 200, -1.575, ObjectID.furniture, false, handler.PoolTable));
        GameView.handler.addObject(new Blocker(1000, 650, 200, 100, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Furniture(1650, 810, 0, 100, 50, 0, ObjectID.furniture, false, handler.CocaineCrate));
        GameView.handler.addObject(new Blocker(1700, 835, 100, 50, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Furniture(1145, 600, 0, 450, 75, 0, ObjectID.furniture, false, handler.Bar));
        GameView.handler.addObject(new Blocker(1380, 660, 125, 50, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Blocker(1505, 640, 300, 70, 0, ObjectID.blocker, true));
        //Kitchen (End of hallway to the right)
        GameView.handler.addObject(new Furniture(930, 55, 0, 50, 50, 0, ObjectID.furniture, false, handler.KitchenCounter));
        GameView.handler.addObject(new Furniture(980, 55, 0, 50, 50, 0, ObjectID.furniture, false, handler.KitchenCounter));
        GameView.handler.addObject(new Furniture(1030, 55, 0, 50, 45, 0, ObjectID.furniture, false, handler.KitchenSink));
        GameView.handler.addObject(new Furniture(1080, 55, 0, 50, 50, 0, ObjectID.furniture, false, handler.KitchenCounter));
        GameView.handler.addObject(new Furniture(1130, 55, 0, 50, 50, 0, ObjectID.furniture, false, handler.KitchenCounter));
        GameView.handler.addObject(new Furniture(1180, 55, 0, 50, 45, 0, ObjectID.furniture, false, handler.KitchenSink));
        GameView.handler.addObject(new Furniture(1230, 55, 0, 50, 50, 0, ObjectID.furniture, false, handler.KitchenCounter));
        GameView.handler.addObject(new Furniture(1275, 45, 0, 50, 70, 0, ObjectID.furniture, false, handler.Fridge));
        GameView.handler.addObject(new Blocker(955, 80, 400, 40, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Furniture(1255, 170, 0, 200, 50, 1.575, ObjectID.furniture, false, handler.Buffe));
        GameView.handler.addObject(new Blocker(1305, 210, 50, 200, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Furniture(870, 360, 0, 60, 60, -1.575, ObjectID.furniture, false, handler.BuffePlates));
        GameView.handler.addObject(new Furniture(870, 290, 0, 60, 60, -1.575, ObjectID.furniture, false, handler.BuffePlates));
        GameView.handler.addObject(new Furniture(870, 220, 0, 60, 60, -1.575, ObjectID.furniture, false, handler.BuffePlates));
        GameView.handler.addObject(new Furniture(870, 150, 0, 60, 60, -1.575, ObjectID.furniture, false, handler.BuffePlates));
        GameView.handler.addObject(new Blocker(915, 120, 30, 280, 0, ObjectID.blocker, true));
        //Final Room (Top right)
        GameView.handler.addObject(new Furniture(1490, 150, 0, 100, 100, 0, ObjectID.furniture, false, handler.Fountain));
        GameView.handler.addObject(new Blocker(1540, 205, 100, 90, 0, ObjectID.blocker, true));

        /////ENEMY SPAWN/////
        //Hallway
        GameView.handler.addObject(new DefaultEnemy(795, 500, 0, 0,  75, ObjectID.enemy, false, GameView.handler, -1.5));
        //Main room 1 (left of spawn)
        GameView.handler.addObject(new DefaultEnemy(630, 525, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 2.30));
        GameView.handler.addObject(new DefaultEnemy(495, 750, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 0));
        GameView.handler.addObject(new DefaultEnemy(250, 645, 0, 0,  75, ObjectID.enemy, false, GameView.handler, -1.5));
        //Toilet (top left)
        GameView.handler.addObject(new DefaultEnemy(265, 165, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 0));
        //Game room (right of toilet)
        GameView.handler.addObject(new DefaultEnemy(475, 395, 0, 0,  75, ObjectID.enemy, false, GameView.handler, -2.25));
        GameView.handler.addObject(new DefaultEnemy(115, 350, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 0));
        GameView.handler.addObject(new DefaultEnemy(415, 215, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 0));
        //Main room 2 (Right of spawn)
        GameView.handler.addObject(new DefaultEnemy(950, 600, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 1.5));
        GameView.handler.addObject(new DefaultEnemy(1160, 785, 0, 0,  75, ObjectID.enemy, false, GameView.handler, -1.5));
        GameView.handler.addObject(new DefaultEnemy(1685, 605, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 1.5));
        GameView.handler.addObject(new DefaultEnemy(1750, 810, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 1.5));
        //Kitchen (End of hallway to the right)
        GameView.handler.addObject(new DefaultEnemy(1305, 155, 0, 0,  75, ObjectID.enemy, false, GameView.handler, -1.4));
        GameView.handler.addObject(new DefaultEnemy(1260, 325, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 0));
        GameView.handler.addObject(new DefaultEnemy(1005, 180, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 1));
        //Final Room (Top right)
        GameView.handler.addObject(new DefaultEnemy(1460, 190, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 1.5));
        GameView.handler.addObject(new DefaultEnemy(1735, 190, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 1.5));
        GameView.handler.addObject(new DefaultEnemy(1750, 485, 0, 0,  75, ObjectID.enemy, false, GameView.handler, 3));

        /////PLAYER SPAWN/////
        GameView.handler.addObject(new Player(800, 1040, 0, 0, 75, ObjectID.player, false, GameView.handler, 0));
        GameView.handler.addObject(new Cursor(0, 0, 0, 0, 20, ObjectID.cursor, false));

        /////EXIT LEVEL/////
        GameView.handler.addObject(new Car(690, 1045, 200, 100, 0, ObjectID.exit, true, GameView.handler));
        GameView.handler.addObject(new Blocker(680, 910, 10, 235, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Blocker(690, 1065, 80, 80, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Blocker(840, 1065, 80, 80, 0, ObjectID.blocker, true));
        GameView.handler.addObject(new Blocker(920, 910, 10, 235, 0, ObjectID.blocker, true));

        /////WALL CREATION/////
        //Exterior walls
        GameView.handler.addObject(new VerticalWall(20, 60, 0, 0,855, ObjectID.wall, true));
        GameView.handler.addObject(new VerticalWall(1810, 60, 0, 0, 855, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(20, 60, 0, 0,  1810, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(20, 895, 0, 0, 740, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(840, 895, 0, 0, 990, ObjectID.wall, true));

        //Toilet (top left)
        GameView.handler.addObject(new VerticalWall(328, 60, 0, 0,  210, ObjectID.wall, true));
        //
        GameView.handler.addObject(new VerticalWall(688, 60, 0, 0,  120, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(688, 160, 0, 0,  20, ObjectID.wall, true));
        GameView.handler.addObject(new VerticalWall(688, 260, 0, 0,  440, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(688, 690, 0, 0,  20, ObjectID.wall, true));
        GameView.handler.addObject(new VerticalWall(688, 790, 0, 0,  120, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(688, 896, 0, 0,  20, ObjectID.wall, true));
        GameView.handler.addObject(new VerticalWall(888, 60, 0, 0,  355, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(888, 395, 0, 0,  20, ObjectID.wall, true));
        GameView.handler.addObject(new VerticalWall(1348, 60, 0, 0,  355, ObjectID.wall, true));
        GameView.handler.addObject(new VerticalWall(888, 560, 0, 0,  200, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(888, 740, 0, 0,  20, ObjectID.wall, true));
        GameView.handler.addObject(new VerticalWall(888, 840, 0, 0,  60, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(888, 895, 0, 0,  20, ObjectID.wall, true));

        //Toilet (top left)
        GameView.handler.addObject(new HorizontalWall(20, 250, 0, 0,  50, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(160, 250, 0, 0,  188, ObjectID.wall, true));
        //Game room (right of toilet)
        GameView.handler.addObject(new HorizontalWall(20, 450, 0, 0, 400, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(528, 450, 0, 0,  180, ObjectID.wall, true));
        //Kitchen (End of hallway to the right)
        GameView.handler.addObject(new HorizontalWall(888, 395, 0, 0,  180, ObjectID.wall, true));
        GameView.handler.addObject(new HorizontalWall(1168, 395, 0, 0,  200, ObjectID.wall, true));
        //Main room 2 (Right of spawn)
        GameView.handler.addObject(new HorizontalWall(888, 560, 0, 0,  942, ObjectID.wall, true));
    }
}