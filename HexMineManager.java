import java.util.*;

/**
 * HexMineManager class
 * Handles the logic of the game and manages the Hexagonal map creation.
 * @author Lane Woods
 */
public class HexMineManager {
    //A set seed for the random number generator
//    private final long seed = 12895L;
//    private final Random randSeed = new Random(seed);
    private final Map<String, Hex> hexCells;
    private final int radius;
    private int Flags;
    private final StringBuilder HexCoordinates = new StringBuilder();

    /**
     * Constructor for HexMineManager class.
     * @param radius of the hexagonal map
     * @param mines to place on the map
     */
    public HexMineManager(int radius, int mines) {
        this.radius = radius;
        this.hexCells = new HashMap<>();
        createHexMap();
        placeMines(mines);
        calculateNeighbors();
    }

    /**
     * Creates a hexagonal map with coordinates q,r,s.
     */
    public void createHexMap() {
        for (int q = -radius; q <= radius; q++) {
            int r1 = Math.max(-radius, -q - radius);
            int r2 = Math.min(radius, -q + radius);
            for (int r = r1; r <= r2; r++) {
                String coordKey = q + "," + r + "," + (-q - r);
                hexCells.put(coordKey, new Hex(q, r, -q - r));
            }
        }
    }

    /**
     * Prints the hexagonal map using the Map values
     */
    public void StringifyHexCells() {
        StringBuilder HexString = new StringBuilder();
        for (int q = -radius; q <= radius; q++) {
            for (int i = 0; i < Math.abs(q); i++) {
                HexString.append(" ".repeat(1));
            }
            int r1 = Math.max(-radius, -q - radius);
            int r2 = Math.min(radius, -q + radius);
            for (int r = r1; r <= r2; r++) {
                Hex cell = getCell(q, r, -q - r);
                //Could use a switch. Might/Will replace it later on
                if (cell == null) {
                    HexString.append("X ");
                } else if (cell.isFlagged()) {
                    HexString.append("F ");
                } else if (!cell.isUncovered()) {
                    HexString.append("* ");
                } else if (cell.isMine()) {
                    HexString.append("M ");
                } else if (cell.getMinesNearby() == 0){
                    HexString.append("_ ");
                }else {
                    HexString.append(cell.getMinesNearby()).append(" ");
                }
            }
            HexString.append("\n");
        }
        System.out.println(HexString);
    }

    /**
     * Returns a string of the coordinates of the hexagonal map. Used to help
     * with debugging and ending my game.
     * @return String of the coordinates
     */
    public String getCoordinateString(){
        for (int q = -radius; q <= radius; q++) {
            for (int i = 0; i < Math.abs(q); i++) {
                HexCoordinates.append(" ".repeat(4));
            }
            int r1 = Math.max(-radius, -q - radius);
            int r2 = Math.min(radius, -q + radius);
            for (int r = r1; r <= r2; r++) {
                Hex cell = getCell(q, r, -q - r);
                HexCoordinates.append("(").append(cell.q).append(",").append
                        (cell.r).append(",").append(cell.s).append(") ");
            }
            HexCoordinates.append("\n");
        }
        return HexCoordinates.toString();
    }

    /**
     * Places mines on the map. Shuffle avoids placing mines in the same spot.
     */
    public void placeMines(int mines) {
        List<String> HexKeys = new ArrayList<>(hexCells.keySet());
        Collections.shuffle(HexKeys);
        for (int i = 0; i < mines && i < HexKeys.size(); i++) {
            hexCells.get(HexKeys.get(i)).setMine(true);
        }
        calculateNeighbors();
    }

    /**
     * Flags a cell if it is not a mine and is not yet uncovered.
     * @param q q value of the cell
     * @param r r value of the cell
     * @param s s value of the cell
     */
    public void flagCell(int q, int r, int s) {
        Hex cell = getCell(q, r, s);
        if (cell != null || !cell.isUncovered()) {
            cell.toggleFlag();
            Flags--;
        }
    }

    /**
     * Calculates the number of mines around each cell and stores it in the cell
     * to later be displayed on the board.
     */
    public void calculateNeighbors() {
        for (Hex cell : hexCells.values()) {
            int count = 0;
            for (int dir = 0; dir < 6; dir++) {
                Hex neighbor = cell.hexNeighbor(dir);
                String coordKey = neighbor.q + "," + neighbor.r + "," + neighbor.s;
                Hex neighborCell = hexCells.get(coordKey);
                if (neighborCell != null && neighborCell.isMine()) {
                    count++;
                }
            }
            cell.getMinesNearby(count);
        }
    }

    /**
     * Returns the Hex at the given coordinates.
     * @param q q value of the cell
     * @param r r value of the cell
     * @param s s value of the cell
     * @return Hex at the given coordinates
     */
    public Hex getCell(int q, int r, int s) {
        String coordKey = q + "," + r + "," + s;
        return hexCells.get(coordKey);
    }

    /**
     * Uncovers a cell if it is not a mine and is not yet uncovered. It also
     * handles revealing all cells if a mine is uncovered.
     * @param q q value of the cell
     * @param r r value of the cell
     * @param s s value of the cell
     */
    public void uncoverCell(int q, int r, int s) {
        Hex cell = getCell(q, r, s);
        if (cell == null || cell.isUncovered() || cell.isFlagged()) {
            return;
        }
        cell.setUncovered(true);
        //Checks if a mine was uncovered and ends the game if it was
        if (cell.isMine()) {
//            revealAll();
            revealAllMines();
            isGameOver();
        }
        //Reveals all cells around the uncovered cell if it is empty
        if (cell.getMinesNearby() == 0) {
            for (int dir = 0; dir < 6; dir++) {
                Hex neighbor = cell.hexNeighbor(dir);
                uncoverCell(neighbor.q, neighbor.r, neighbor.s);
            }
        }
    }

    /**
     * Reveals all cells on the map.
     */
    public void revealAll() {
        for (Hex cell : hexCells.values()) {
            cell.setUncovered(true);
        }
    }

    public void revealAllMines(){
        for (Hex cell : hexCells.values()) {
            if (cell.isMine()){
                cell.setUncovered(true);
            }
        }
    }

    public int getRadius(){
        return radius;
    }

    public int getFlagCount(){
        int count = 0;
        for (Hex cell :hexCells.values()){
            if (cell.isFlagged()){
                count++;
            }
        }
        return count;
    }

    public int getMineCount(){
        int count = 0;
        for (Hex cell : hexCells.values()){
            if (cell.isMine()){
                count++;
            }
        }
        return count;
    }

    /**
     * Just updates the board.
     * Note: I could literally just call StringifyHexCells().
     * Note2: Will remove.
     */
    public void updateBoard(){
        StringifyHexCells();
    }

    /**
     * Prints Game Over.
     * Note: Could've just called System.out.println("Game Over") in uncoverCell.
     */
    public void isGameOver() {
        System.out.println("Game Over");
    }

    public void GameWon(){
        System.out.println("You Win!");
    }
}
