import java.util.*;

/**
 * HexMap class that creates the String representation of the hexagonal map and
 * places the mines.
 */
public class HexMap{

    static void main() {
        HexMap hexMap = new HexMap(2, 3);
    }

    public HexMap(int Radius, int mines){
        ArrayList<Hex> hexCells = new ArrayList<>();
        ArrayList<Hex> mineCells = new ArrayList<>();
        StringBuilder HexMap = new StringBuilder();
        String HexMapWithMines = "";
        StringBuilder HexCoordinates = new StringBuilder();
        for (int q = -Radius; q <= Radius; q++){
            //Want random mine placement and will store our mines in an arraylist
            //First for loop handles the centering of the string representation
            int rowPadding = Math.abs(q);
            for (int i = 0; i < rowPadding; i++){
                HexMap.append(" ".repeat(1));
                HexCoordinates.append(" ".repeat(5));
            }
            //This handles the printing of the hexagonal map
            int r1 = Math.max(-Radius, -q - Radius);
            int r2 = Math.min(Radius, -q + Radius);
            for (int r = r1; r <= r2; r++) {
                hexCells.add(new Hex(q, r, -q - r));
                HexMap.append("* ");
                HexCoordinates.append("(").append(q).append(", ").
                        append(r).append(", ").append(-q - r).append(") ");
            }
            //Since im dealing with a 1d array, it's probably best to do a random

            HexMap.append("\n");
            HexCoordinates.append("\n");
        }
        for (int i = 0; i < mines; i++){
            int mineIndex = (int) (Math.random() * hexCells.size());
            mineCells.add(hexCells.get(mineIndex));
            hexCells.remove(mineIndex);
            HexMapWithMines += "M ";
        }

        System.out.println(HexMap);
        System.out.println(HexCoordinates);
        System.out.println(hexCells.hashCode() + " " + mineCells.hashCode());
        System.out.println(hexCells.size());
        System.out.println(mineCells.size());;
        System.out.println(HexMapWithMines);
    }


    public void checkNeighbors(int q, int r, int s){
        for (int i = 0; i < 6; i++){
//            if (hexNeighbor(i).equals(new Hex(q,r,s))){
//                revealNeighbor();
//            }
        }
    }

    /**
     *
     */
    public void revealNeighbor() {

    }

    /**
     *
     */
    public void revealAll() {

    }
}
