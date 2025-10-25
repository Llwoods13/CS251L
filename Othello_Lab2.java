import cs251.project2.OthelloGUI;
import cs251.project2.OthelloInterface;

public class Othello_Lab2 implements OthelloInterface {
    public static void main ( String [] args ) {
        Othello_Lab2 game = new Othello_Lab2();
        if ( args . length > 0) {
            game . configureOpponent ( args [0]);
        }
        OthelloGUI . showGUI ( game );
    }

    /**
     * @return The size of the game board.
     */
    @Override
    public int getSize() {
        return DEFAULT_SIZE;
    }

    // Here's an array that holds all the game board state (❁´◡`❁)
    private Piece[][] boardState;

    /**
     * Sets up the board state.
     */
    @Override
    public void initGame() {
        boardState = new Piece[getSize()][getSize()];

        // Fill the board with empty spots.
        for ( int i = 0 ; i < getSize() ; i++) {
            for ( int j = 0 ; j < getSize() ; j++) {
                boardState[i][j] = Piece.EMPTY;
            }
        }

        // Place pieces in the center (corners are different colors.)
        boardState[4][4] = Piece.LIGHT;
        boardState[3][3] = Piece.LIGHT;
        boardState[4][3] = Piece.DARK;
        boardState[3][4] = Piece.DARK;

        // Set the current player to dark.
        currentPlayer = false; // False = dark.
    }

    /**
     * @return A string representation of the board state. 🙂‍↕️🐀
     */
    @Override
    public String getBoardString() {
        StringBuilder boardString = new StringBuilder();
        // Return the character type at each spot.
        for ( int i = 0 ; i < getSize() ; i++) {
            for ( int j = 0 ; j < getSize() ; j++) {
                boardString.append(boardState[i][j].toChar());
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    // The current player, as a boolean.
    // True = Light, False = Dark
    private boolean currentPlayer;

    /**
     * @return
     */
    @Override
    public Piece getCurrentPlayer() {
        return currentPlayer ? Piece.LIGHT : Piece.DARK;
    }

    /**
     * @param x X-coordinate of the click
     * @param y Y-coordinate of the click
     * @return Result of the move.
     */
    @Override
    public Result handleClickAt(int x, int y) {
        isLegal(x, y);
        return Result.GAME_NOT_OVER;
    }

    /**
     * @param x X-coordinate of the location to check.
     * @param y Y-coordinate of the location to check.
     * @return
     */
    @Override
    public boolean isLegal(int x, int y) {
        boolean hasLine = false;
        //TODO: Write logic for checking all the directions.
        for (int i = 0; i < Directions.values().length; i++) {
            Directions d = Directions.values()[i];
            if (lineInDirection(x, y, d, getCurrentPlayer())) {
                hasLine = true;
                break;
            }
        }

        return inGrid(x, y) && boardState[x][y] == Piece.EMPTY && hasLine;
    }

    // Checks if a point is within the bounds of the grid.
    private boolean inGrid(int x, int y) {
        return x >= 0 && x <= getSize() && y >= 0 && y <= getSize();
    }

    // Checks if there is a line in a given direction from a given point, of the specified piece color.
    private boolean lineInDirection(int startX, int startY, Directions directionToCheck, Piece color) {
        int length = 0;
        while (inGrid(startX, startY)) {
            if (length == 1 && boardState[startX][startY] == color) {
                return false; // If the first piece checked is the same color, not legal.
            } else if (length > 1 && boardState[startX][startY] == color) {
                return true; // If we get here, we know we've already gone past pieces of different color, and now we've hit one of the same color, so it's legal.
            } else if (boardState[startX][startY] != color) {
                length++;
            }

            startX += directionToCheck.dx;
            startY += directionToCheck.dy;
        }

        return false;
    }

    /**
     * An enum which holds all the directions
     */
    private enum Directions {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP_LEFT(-1, -1),
        UP_RIGHT(1, 1),
        DOWN_LEFT(-1, 1),
        DOWN_RIGHT(1, 1);

        /**
         * Change in the X coordinate for this direction.
         */
        public final int dx;

        /**
         * Change in the Y coordinate for this direction.
         */
        public final int dy;

        Directions(int deltaX, int deltaY) {
            this.dx = deltaX;
            this.dy = deltaY;
        }
    }

    /**
     * @param s
     */
    @Override
    public void configureOpponent(String s) {

    }
}
