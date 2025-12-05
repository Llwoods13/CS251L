import javax.swing.*;
import java.util.*;

/**
 * Hex class
 * Holds the coordinates and information about each hexagonal cell.
 * @author Lane Woods
 */
public class Hex extends JButton {
    /*
    +q is going in the -z direction
    +r is going in the +x direction
    +s is going in the -y direction

    -q is going in the +z direction
    -r is going in the -x direction
    -s is going in the +y direction
     */

    /**
     * Constructor that creates a new Hex object.
     * @param q
     * @param r
     * @param s
     */
    public Hex(int q, int r, int s){
        this.q = q;
        this.r = r;
        this.s = s;

        if (q + r + s != 0) {
            throw new IllegalArgumentException("s + q + r must be 0");
        }
    }
    public final int q;
    public final int r;
    public final int s;
    private int minesNearby;
    private boolean isMine;
    private boolean flag;
    private boolean uncovered;

    /**
     * Compares one Hex object to another.
     * @param obj the Hex object to compare to
     * @return true if the Hex objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Hex && equalsHex((Hex) obj);
    }

    /**
     * Compares two Hexes to see if they are equal.
     * @param b the Hex to compare to
     * @return true if the Hexes are equal, false otherwise
     */
    public boolean equalsHex(Hex b){
        return q == b.q && r == b.r && s == b.s;
    }

    /**
     * Adds two Hexes together.
     * @param b Hex to add to this Hex
     * @return the sum of the two Hexes
     */
    public Hex Add(Hex b){
        return new Hex(q + b.q, r + b.r, s + b.s);
    }

    /**
     * All the directions that a hex can be in relation to its neighbors.
     */
    public static ArrayList<Hex> direction = new ArrayList<>() {{
        add(new Hex(1, 0, -1));
        add(new Hex(1, -1, 0));
        add(new Hex(0, -1, 1));
        add(new Hex(-1, 0, 1));
        add(new Hex(-1, 1, 0));
        add(new Hex(0, 1, -1));
    }};

    /**
     * Returns the direction in the direction array at the given index.
     * @param direction the index of the direction
     * @return the direction at the given index
     */
    public static Hex direction(int direction) {
        return Hex.direction.get(direction);
    }

    /**
     * Returns the neighbor of this hex in the given direction.
     * @param direction the direction of the neighbor
     * @return the neighbor of this hex
     */
    Hex hexNeighbor(int direction){
        return Add(Hex.direction(direction));
    }

    /**
     * Returns a hash code for this Hex.
     * @return a hash code for this Hex
     */
    @Override
    public int hashCode() {
        return Objects.hash(q, r, s);
    }

    /**
     * Resembles if a hex is flagged.
     * @return true if the hex is flagged, false otherwise
     */
    public boolean isFlagged(){
        return flag;
    }

    /**
     * Toggles the Hex flagged
     */
    public void toggleFlag(){
        this.flag = !this.flag;
    }

    /**
     * Checks if the hex is a mine.
     * @return true if the hex is a mine, false otherwise
     */
    public Boolean isMine(){
        return isMine;
    }

    /**
     * Sets the hex to be a mine
     * @param isMine true if the hex is a mine, false otherwise
     */
    public void setMine(boolean isMine){
        this.isMine = isMine;
    }

    /**
     * Gets the number of mines nearby.
     * @return number of mines nearby
     */
    public int getMinesNearby(){
        return minesNearby;
    }

    /**
     * Gets the number of mines nearby.
     * @param count number of mines nearby
     */
    public void getMinesNearby(int count){
        this.minesNearby = count;
    }

    /**
     * Checks if the hex is uncovered.
     * @return true if the hex is uncovered, false otherwise
     */
    public boolean isUncovered(){
        return uncovered;
    }

    /**
     * Sets the hex to be uncovered.
     * @param uncovered true if the hex is uncovered, false otherwise
     */
    public void setUncovered(boolean uncovered){
        this.uncovered = uncovered;
    }
}

