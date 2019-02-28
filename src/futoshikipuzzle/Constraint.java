package futoshikipuzzle;

/**
 * Super class for both types of constraints
 * 
 * @author James Goodman 
 * @version 1.0 (3/04/2016)
 */
public abstract class Constraint {
    FutoshikiSquare square1;
    FutoshikiSquare square2;
    
    /**
     * Constructor for Constraint, stores the two square the constraint is
     * responsible for
     * 
     * @param square1
     * @param square2 
     */
    public Constraint (FutoshikiSquare square1, FutoshikiSquare square2) {
        this.square1 = square1;
        this.square2 = square2;
        
    }
    
    /**
     * Abstract definition for comparing the two squares
     * 
     * @return boolean is the comparison valid
     */
    public abstract boolean CompareSquares();
    
    
}











