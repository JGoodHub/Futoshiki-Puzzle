package futoshikipuzzle;

/**
 * One of the two types of constraints, sub class of the Constraint parent and
 * responsible for comparing squares
 * 
 * @author James Goodman 
 * @version 1.0 (3/04/2016)
 */
public class LessThan extends Constraint {    
    
    /**
     * Constructor for the LessThan class, sets the square variables for the
     * super class
     * 
     * @param square1 the left or above square
     * @param square2 the right or below square
     */
    public LessThan(FutoshikiSquare square1, FutoshikiSquare square2) {
        super(square1, square2);
    }
    
    /**
     * If the squares are both valid then compared them and return the result
     * 
     * @return boolean if valid returns true if square one is less than square two
     */
    @Override
    public boolean CompareSquares () {       
        if (square1.getValue() > 0 && square2.getValue() > 0) {
            return square1.getValue() < square2.getValue();
        } else {
            return true;
        }
    }
    
    /**
    * returns the appropriate character based on the constraints position/rotation
    * in the grid
    * 
    * @return String vertical or horizontal more than symbol
    */
    @Override
    public String toString () {
        if (square1.getColumn() == square2.getColumn()) {
            return "^";
        } else if (square1.getRow() == square2.getRow()) {
            return "<";
        } else {
            return "Error";
        }
            
            
    }
    
}






