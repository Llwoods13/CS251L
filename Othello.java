import cs251.project2.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project 2 Othello
 * @author Lane Woods
 * Creating Othello by implementing a jar file containing our interface and GUI.
 * If passing an invalid argument to configureOpponent it WILL default to human.
 * Thanks to everyone that helped me with this project. Ben from ESS,
 * Brooke/Ms.Chenoweth(Prof), Micah(T/A), Robbie(T/A), Greg, Chris and his wife
 * Verna.
 */
public class Othello implements OthelloInterface {
    //Initialization of our variables
    private Piece[][] gameBoard;
    private String opponent;
    private int turns;

    /**
     * Our main method that initializes the game
     *
     * @param args sets to either human or computer based on the prompt provided
     */
    public static void main(String[] args) {
        Othello game = new Othello();
        if (args.length > 0) {
            game.configureOpponent(args[0]);
        }
        System.out.println();
        OthelloGUI.showGUI(game);
    }

    /**
     * Gets the size of the board/specifies the rows and cols
     *
     * @return Default size so we get the games original 8x8 board but can be
     * changed for testing purposes
     */
    @Override
    public int getSize() {
        return DEFAULT_SIZE;
    }

    /**
     * Initiates the game by making everything an empty piece then places the
     * correct pieces on the board. The pieces are able to adapt to any changes
     * to getSize()
     */
    @Override
    public void initGame() {
        //Clears board and sets all to empty
        gameBoard = new Piece[getSize()][getSize()];
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                gameBoard[i][j] = Piece.EMPTY;
            }
        }
        //initializes the starting pieces
        gameBoard[getSize() / 2][getSize() / 2] = Piece.LIGHT;
        gameBoard[getSize() / 2 - 1][getSize() / 2 - 1] = Piece.LIGHT;
        gameBoard[getSize() / 2 - 1][getSize() / 2] = Piece.DARK;
        gameBoard[getSize() / 2][getSize() / 2 - 1] = Piece.DARK;
        //resets the turn counter so dark always starts
        turns = 0;
    }

    @Override
    /**
     * Turns the board into a string by using the toChar method provided by the
     * interface
     * @return the string representation of the board
     */
    public String getBoardString() {
        String boardString = "";
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                boardString += gameBoard[i][j].toChar();
            }
            boardString += "\n";
        }
        return boardString;
    }

    /**
     * Initializes the current player as either Dark or Light depending on the
     * turn
     *
     * @return current player
     */
    @Override
    public Piece getCurrentPlayer() {
        //Ternary operator determines who's the current player
        if (turns % 2 == 0) {
            return Piece.DARK;
        } else {
            return Piece.LIGHT;
        }
    }

    /**
     * potentXY takes in 4 values that help determine if the move at that
     * location is a valid move for my pieces to jump. It essentially maps out a
     * line in all 8 directions thanks to the enum to determine if my piece is
     * found at the end of one or more directions.
     *
     * @param myColor Takes in the current player/color
     * @param x1      Takes in the x value or deltaX
     * @param y1      Takes in the y value or deltaY
     * @param allDir  Takes in my enum to check all directions
     * @return
     */
    public lineInfo potentXY(Piece myColor, int x1, int y1, Directions allDir) {
        int length = 0;
        int x = x1;
        int y = y1;
        //Adds first to ensure we are checking the pieces around and not at my
        //current xy
        x += allDir.getX();
        y += allDir.getY();
        while (inBoard(x, y)) {
            //Checks if the next location is my color
            if (length == 0 && (gameBoard[x][y] == myColor ||
                    gameBoard[x][y] == Piece.EMPTY)) {
                return lineInfo.NOLINE;
            }//Checks if the next location after next i.e n+1 is my color
            //and if so it's a legal move
            else if (length > 0 && gameBoard[x][y] == myColor) {
                length++;
                return new lineInfo(x, y, length, allDir);
            } else if (gameBoard[x][y] != myColor) {
                //adds to the length
                length++;
            }
            x += allDir.getX();
            y += allDir.getY();
            //adds the enum params which helps in finding a legal move/legal
            //direction
        }
        return lineInfo.NOLINE;
    }

    /**
     * Checks to see if my current x and y are within the board
     *
     * @param x current x
     * @param y current y
     * @return true if in board and false otherwise
     */
    public boolean inBoard(int x, int y) {
        return (x >= 0 && x < getSize() && y >= 0 && y < getSize());
    }

    /**
     * Calls my potentXY method to access the directions that are legal moves and
     * adds them to a list. I.e if all but 3 locations have a validLine then it
     * will show only 3 valid places on the board.
     *
     * @param x current x
     * @param y current y
     * @return True if a direction has a validLine and false otherwise.
     */
    @Override
    public boolean isLegal(int x, int y) {
        boolean validLine = false;
        if (!inBoard(x, y) || (gameBoard[x][y] != Piece.EMPTY)) return false;
        //iterates through all directions provided by the enum
        for (int i = 0; i < Directions.values().length; i++) {
            Directions dir = Directions.values()[i];
            lineInfo valid = potentXY(getCurrentPlayer(), x, y, dir);
            //uses my potentXY method to display only the legal moves that are
            //possible
            if (!valid.equals(lineInfo.NOLINE)) {
                validLine = true;
                break;
            }
        }
        //returns if in the board, if the slot selected in empty, and if there
        //is in fact a legal move
        return (inBoard(x, y) && gameBoard[x][y] == Piece.EMPTY && validLine);
    }

    /**
     * Handles the clicking feature on the board. It will flip the pieces while
     * checking if the directions are valid, if the move is legal, and will
     * check who the current player is.
     *
     * @param x current x
     * @param y current y
     * @return The flipped state of where the player/computer has clicked.
     */
    @Override
    //Make another helper function to help with finding out if there is more
    //than one piece depending on the player that you can jump
    public Result handleClickAt(int x, int y) {
        //List that contains valid directions
        List<lineInfo> lines = new ArrayList<>();
        //If not a legal move the game is still not over
        if (!isLegal(x, y)) {
            return Result.GAME_NOT_OVER;
        }

        //loops through my enum and only adds the directions that are valid to
        //my list
        for (int i = 0; i < Directions.values().length; i++) {
            Directions dir = Directions.values()[i];
            lines.add(potentXY(getCurrentPlayer(), x, y, dir));
        }

        //Loops through my valid list and flips all pieces in that direction
        for (lineInfo allLine : lines) {
            int xtemp = x;
            int ytemp = y;
            if (allLine.getLength() > 0) {
                for (int i = 0; i < allLine.length; i++) {
                    gameBoard[xtemp][ytemp] = getCurrentPlayer();

                    xtemp += allLine.direction.getX();
                    ytemp += allLine.direction.getY();
                }
            }
        }
        //Ensures after all pieces in a direction(s) are flipped then flips
        //current player
        turns++;

        System.out.println(getCurrentPlayer());
        if (opponent.equals("COMPUTER") && getCurrentPlayer() == Piece.LIGHT) {
            compMove();

        }
        //Checks if all the spots are not filled or if there is still a legal move
        if (allFilled() || !hasLegalMove()) {
            int dark = tallyDark();
            int light = tallyLight();
            if (dark > light) {
                return Result.DARK_WINS;
            } else if (dark < light){
                return Result.LIGHT_WINS;
            } else {
                return Result.DRAW;
            }
        }
        return Result.GAME_NOT_OVER;
    }

    /**
     * Tallies up all the light pieces on the board
     * @return the total number of light pieces on the board
     */
    private int tallyLight(){
        int lightSpot = 0;
        for (int i = 0; i < getSize(); i++){
            for (int j = 0; j < getSize(); j++){
                if (gameBoard[i][j] == Piece.LIGHT){
                    lightSpot++;
                    }
            }
        }
        System.out.println("lightspots = " + lightSpot);
        return lightSpot;
    }

    /**
     * Tallies all the dark pieces on the board
     * @return all dark pieces on the board
     */
    private int tallyDark(){
        int darkSpot = 0;
        for (int i = 0; i < getSize(); i++){
            for (int j = 0; j < getSize(); j++){
                if (gameBoard[i][j] == Piece.DARK){
                    darkSpot++;
                }
            }
        }
        System.out.println("darkspots = " + darkSpot);
        return darkSpot;
    }

    /**
     * Checks if all the spots in the grid are filled
     * @return True if all the spots are filled or False if there are still more
     * empty spots
     */
    public boolean allFilled() {
        boolean filled = false;
        int filledSpots = 0;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (gameBoard[i][j] != Piece.EMPTY) {
                    filledSpots++;
                }
            }
        }
        if (filledSpots == Math.pow(getSize(), 2)) {
            filled = true;
        }
        return filled;
    }

    /**
     * Checks if there are any legal moves left on the board
     * @return True if there is still a legal move and False if there is no
     * legal move
     */
    public boolean hasLegalMove() {
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                //Checks for a legal move at that index
                if (isLegal(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This helps move the computer player to the first spot
     * @return The result so the computer can continue to play
     */
    public void compMove() {
//        int randX = (int) (Math.random() * getSize());
//        int randY = (int) (Math.random() * getSize());
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (isLegal(i, j)) {
                    handleClickAt(i, j);
                    return;
                }
            }
        }
    }

    /**
     * This configures my opponent as light
     * @param s takes in Computer or Human depending on who you want to
     *                 play against.
     */
    @Override
    public void configureOpponent(String s) {
        this.opponent = s;
    }

    /**
     * Enum of all valid directions
     */
    private enum Directions {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1),
        UPLEFT(-1, -1),
        UPRIGHT(-1, 1),
        DOWNLEFT(1, -1),
        DOWNRIGHT(1, 1);

        private final int x;
        private final int y;

        Directions(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    /**
     * Just learned this Friday Sept 12th and incorporated it. Essentially
     * it has a final NOLINE lineInfo record type that acts as a full constructor.
     * This will only display NOLINE if there is no valid x, y, a length of 0, or
     * if there is no valid direction to go into
     * @param x current x
     * @param y current y
     * @param length length of line
     * @param direction enum directions
     */
    public record lineInfo(int x, int y, int length, Directions direction) {
        public static final lineInfo NOLINE = new lineInfo(-1, -1, 0, null);
        public int getLength(){
            return this.length;
        }
    }
}
