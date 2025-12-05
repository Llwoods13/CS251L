/**
 * TestHexMines class
 * Tester that tests the logic from HexMineManager.
 * @author Lane Woods
 */
public class TestHexMines {

    /**
     * Main method that runs the tests.
     * @param args not used
     */
    public static void main(String[] args) {
        String hexSymbols = """
            * : Covered Hex
            M : Mine
            F : Flagged Hex
            
            _ : Uncovered Hex
            Number : Mines Near Hex""";
        System.out.println(hexSymbols);
        String coordNote = "Note: q + r + s = 0\n";
        System.out.println(coordNote);
        new TestHexMines().testGame1();
        new TestHexMines().testGame2();
    }

    /**
     * Creates a game with radius 2 and 3 mines and tests the logic of the game.
     */
    public void testGame1(){
        System.out.println("Testing Game 1");
        System.out.println("Initializing game with radius of 2 and 3 mines\n");
        HexMineManager game1 = new HexMineManager(2, 3);
        System.out.println("I will provide the coordinates of the first hex in the "
                + "game so you get a feel of how my coordinate system works:");
//        TestingUncoverGame1(0, 0, 0);
//        TestingUncoverGame1(1, -1, 0);
//        TestingFlagGame1(0, 1, -1);
//        TestingUncoverGame1(-1, 1, 0);
        System.out.println(game1.getCoordinateString());
        game1.updateBoard();

        System.out.println("Uncover (0,0,0)");
        game1.uncoverCell(0, 0, 0);
        game1.updateBoard();

        System.out.println("Uncover (-1,-1,0)");
        game1.uncoverCell(1, -1, 0);
        game1.updateBoard();

        System.out.println("Flag (0,1,-1)");
        game1.flagCell(0, 1, -1);
        game1.updateBoard();

        System.out.println("Uncover (-1,1,0");
        game1.uncoverCell(-1, 1, 0);
        game1.updateBoard();
    }

    /**
     * Creates a game with radius 30 and 400 mines. I'm losing on purpose to
     * display everything should be working accordingly with a huge board.
     */
    public void testGame2(){
//        TestingUncoverGame2(0, 0, 0);
//        TestingUncoverGame2(-15, 6, 9);
//        TestingFlagGame2(-15,6,9);
//        TestingUncoverGame2(1,1,-2);
//        TestingUncoverGame2(15,6,-21);
//        TestingUncoverGame2(-15,5,10);
//        TestingUncoverGame2(-5,-5,10);
//        TestingUncoverGame2(-1,0,1);
        System.out.println("Testing Game 2");
        System.out.println("Initializing game with radius of 30 and 400 mines\n");
        HexMineManager game2 = new HexMineManager(30, 400);
        game2.updateBoard();

        System.out.println("Uncover (0,0,0)");
        game2.uncoverCell(0, 0, 0);
        game2.updateBoard();

        System.out.println("Flag (-15,6,9)");
        game2.flagCell(-15, 6, 9);
        game2.updateBoard();

        System.out.println("Uncover (1,1,-2)");
        game2.uncoverCell(1, 1, -2);
        game2.updateBoard();

        System.out.println("Uncover (15,6,-21)");
        game2.uncoverCell(15,6,-21);
        game2.updateBoard();

        //Currently Trying to lose
        System.out.println("Uncover (-15,5,10)");
        game2.uncoverCell(-15,5,10);
        game2.updateBoard();

        System.out.println("Uncover (-5,-5,10)");
        game2.uncoverCell(-5,-5,10);
        game2.updateBoard();

        //Finally a losing result
        System.out.println("Uncover (-1,0,1)");
        game2.uncoverCell(-1,0,1);
        game2.updateBoard();
    }

    public void TestingUncoverGame1(int q, int r, int s){
        HexMineManager game1 = new HexMineManager(2, 3);
        System.out.printf("Uncover (%d, %d, %d)\n", q, r, s);
        game1.uncoverCell(q, r, s);
        game1.updateBoard();
    }

    public void TestingFlagGame1(int q, int r, int s){
        HexMineManager game1 = new HexMineManager(2, 3);
        System.out.printf("Flag (%d,%d,%d)\n", q, r, s);
        game1.flagCell(q, r, s);
        game1.updateBoard();
    }

    public void TestingUncoverGame2(int q, int r, int s){
        HexMineManager game2 = new HexMineManager(30, 400);
        System.out.printf("Uncover (%d,%d,%d)\n", q, r, s);
        game2.uncoverCell(q, r, s);
        game2.updateBoard();
    }

    public void TestingFlagGame2(int q, int r, int s){
        HexMineManager game2 = new HexMineManager(30, 400);
        System.out.printf("Flag (%d,%d,%d)\n", q, r, s);
        game2.flagCell(q, r, s);
        game2.updateBoard();
    }
}
