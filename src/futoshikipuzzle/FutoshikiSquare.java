package futoshikipuzzle;

/**
 * The FutoShikiSquare class holds information about the values stored in each 
 * of the grid cells of the futoshiki puzzle
 * 
 * @author James Goodman 
 * @version 1.0 (3/04/2016)
 */
public class FutoshikiSquare {
    
    private int column;
    private int row;
    
    private int value;
    private int pencilValue;
    
    /**
     * Constructor for the FutoshikiSquare class, sets the squares row and 
     * column coordinates
     * 
     * @param column squares column coordinate
     * @param row  squares row coordinate
     */
    public FutoshikiSquare(int column, int row) {
        this.column = column;
        this.row = row;
    }
    
    /**
     * Set the real value of the square 
     * 
     * @param value new real value
     */
    public void setValue (int value) {
        this.value = value;
    }
    
    /**
     * Set the pencil mark value of the square
     * 
     * @param pencilValue new pencil mark value 
     */
    public void setPencilMark (int pencilValue) {
        this.pencilValue = pencilValue;
    }
    
    /**
     * Returns the real value of the square
     * 
     * @return int real value of square
     */
    public int getValue () {
        return value;
    }
    
    public String getValueAsString () {
        if (value > 0) {
            return value + "";
        } else {
            return "";
        }
    }
    
    /**
     * Returns the pencil mark value of the square
     * 
     * @return int pencil mark value of square
     */
    public int getPencilMark () {
        return pencilValue;
    }
    
    public String getPencilMarkAsString() {
        if (pencilValue > 0) {
            return pencilValue + "";
        } else {
            return "";
        }       
    }
    
    /**
     * Checks to see if the squares final value is empty
     * 
     * @return boolean true if the square is empty
     */
    public boolean isEmpty () {
        return value == 0;
    }
    
    /**
     * Checks to see if the value stored in only one digit
     * 
     * @return boolean true if a single digit
     */
    public boolean isSingleDigit() {
        return value < 10;
    }
    
    /**
     * Get the column index of the square
     * 
     * @return int column index
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * Get the row index of the square
     * 
     * @return int row index
     */
    public int getRow() {
        return row;
    }
       
    
    
    
    
}







