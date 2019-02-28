package futoshikipuzzle;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The FutoshikiPuzzle class is the main class responsible for the creation,
 * maintenance and displaying of the futoshiki puzzle
 * 
 * @author James Goodman 
 * @version 1.0 (3/04/2016)
 */
public class FutoshikiPuzzle {
    
    FutoshikiSquare[][] grid;
    Constraint[][] rowConstraints;
    Constraint[][] columnConstraints;
    private int gridSize;    
    
    /**
     * Constructor for the FutoshikiPuzzle class, creates a grid of FutoshikiSquare
     * objects and separate grids for both row and column constraints
     * 
     * @param gridSize the width and height of the futoshiki puzzles grid
     */
    public FutoshikiPuzzle(int gridSize) {
        this.gridSize = gridSize;
        grid = new FutoshikiSquare[gridSize][gridSize];
        
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                grid[x][y] = new FutoshikiSquare(x, y);
            }
        }
                
        rowConstraints = new Constraint[gridSize - 1][gridSize];
        columnConstraints = new Constraint[gridSize][gridSize - 1];
        
    }
        
    /**
     * Sets the real value of the square at the coordinates given
     * 
     * @param row row or y coordinate of the square
     * @param column column or x coordinate of the square
     * @param value new value to be stored in the square at the coordinates
     */
    public void setSquare(int row, int column, int value) {
        if (row < gridSize && column < gridSize && value <= gridSize) {
            grid[row][column].setValue(value);
        }        
    }
    
    /**
     * Sets the pencil mark value of the square at the coordinates given 
     * 
     * @param row row or y coordinate of the square
     * @param column column or x coordinate of the square
     * @param pencilValue new pencil mark value to be stored in the square at 
     * the coordinate
     */
    public void setPencilMark(int row, int column, int pencilValue) {
        if (row < gridSize && column < gridSize && pencilValue <= gridSize) {
            grid[row][column].setPencilMark(pencilValue);
        }        
    }
    
    /**
     * Sets the constraint at the coordinates given in the rowConstraints grid
     * 
     * @param row row or y coordinate of the constraint
     * @param column column or x coordinate of the constraint
     * @param constraint  the new constraint to be set
     */
    public void setRowConstraint(int row, int column, Constraint constraint) {
        if (row < gridSize - 1 && column < gridSize) {
            rowConstraints[row][column] = constraint;
        }        
    }
    
    /**
     * Sets the constraint at the coordinates given in the columnConstraints grid
     * 
     * @param row row or y coordinate of the constraint
     * @param column column or x coordinate of the constraint
     * @param constraint  the new constraint to be set
     */
    public void setColumnConstraint(int row, int column, Constraint constraint) {
        if (row < gridSize && column < gridSize - 1) {
            columnConstraints[row][column] = constraint;
        }        
    }
    
    /**
     * Responsibly for filling the puzzle with values between 1 and gridSize and 
     * constraints, these are randomly generated which can lead to issues and 
     * overlapping
     * 
     * The method then prints out if the puzzle is legal and if not what the 
     * problems are with it
     */
    public FutoshikiPuzzle fillPuzzle (int minSize, int maxSize) {
        Random randomGen = new Random();
        int size = randomGen.nextInt(maxSize - minSize) + minSize;
        FutoshikiPuzzle futoPuzzle = new FutoshikiPuzzle(size);

        for (int i = 0; i < gridSize; i++) {
            int x = randomGen.nextInt(gridSize);
            int y = randomGen.nextInt(gridSize);
            int value = randomGen.nextInt(gridSize) + 1;
            futoPuzzle.setSquare(x, y, value);
            
            switch (randomGen.nextInt(4)) {
                case 0:{
                    int conCol = randomGen.nextInt(gridSize - 1);
                    int conRow = randomGen.nextInt(gridSize);
                    futoPuzzle.setRowConstraint(conCol, conRow,
                                     new MoreThan(grid[conCol][conRow], grid[conCol + 1][conRow]));
                    break;
                } case 1:{
                    int conCol = randomGen.nextInt(gridSize - 1);
                    int conRow = randomGen.nextInt(gridSize);
                    futoPuzzle.setRowConstraint(conCol, conRow,
                                     new LessThan(grid[conCol][conRow], grid[conCol + 1][conRow]));
                    
                    break;            
                } case 2: {
                    int conCol = randomGen.nextInt(gridSize);
                    int conRow = randomGen.nextInt(gridSize - 1);
                    futoPuzzle.setColumnConstraint(conCol, conCol, 
                                        new MoreThan(grid[conCol][conRow], grid[conCol][conRow + 1]));
                    
                    break;
                } case 3: {
                    int conCol = randomGen.nextInt(gridSize);
                    int conRow = randomGen.nextInt(gridSize - 1);
                    futoPuzzle.setColumnConstraint(conCol, conRow,
                                        new LessThan(grid[conCol][conRow], grid[conCol][conRow + 1]));
                    
                    break;
                }
                
            }
            
        }
        
        if (futoPuzzle.isLegal()) {
            return futoPuzzle;
        } else {
            return fillPuzzle(minSize, maxSize);
        }

        
    }
        
      
    /**
     * Generates the visual representation of the grid using various characters
     * and numbers, the gird is built up line by line by printing each row of 
     * boxes over the course of four for loops, the final string is then output
     * 
     * @return String the graphical representation of the grid
     */
    @Override
    public String toString () {
        String output = "";
        for (int y = 0; y < gridSize; y++) {
            //Print the top edge for each box, then drop down one line
            for (int a = 0; a < gridSize; a++) {
                output += "-----     ";
            }          
            
            output += "\n";
            
            //Print the outer edges for each box with the grid values and 
            //constraints inbetween each, then drop down one line
            for (int b = 0; b < gridSize; b++) {
                output += "|";
                
                if (grid[b][y].isEmpty()) {                            
                    output += "   |  ";
                } else {
                    if (grid[b][y].isSingleDigit()) {
                        output += " " + grid[b][y].getValue() + " |  ";
                    } else {
                        output += " " + grid[b][y].getValue() + "|  ";                                
                    } 
                }
                                
                if (b < gridSize - 1 && rowConstraints[b][y] != null) {
                    output += rowConstraints[b][y].toString() + "  ";
                } else {
                    output += "   ";
                }
                
            }
            
            output += "\n";            
            
            //Print the bottom edge for each box, then drop down one line
            for (int c = 0; c < gridSize; c++) {
                output += "-----     ";
            }          
            
            output += "\n";
            
            //Print the constraints for each column, if any and then drop 
            //down one line
            for (int d = 0; d < gridSize; d++) {
                if (y < gridSize - 1 && columnConstraints[d][y] != null) {
                    output += "  " + columnConstraints[d][y].toString() + "       ";
                } else {
                    output += "          ";
                }

            }
            
            output += "\n";
                        
        }
        
        return output;
                
    }
        
    /**
     * Checks to see if the current puzzles configuration is legal as per the 
     * rules of Futoshiki puzzles
     * 
     * @return boolean is the puzzle legal 
     */
    public boolean isLegal () {
        for (int i = 0; i < gridSize; i++) {
            if (checkRowForDuplicate(i) || checkColumnForDuplicate(i) ||
                checkRowConstraints(i) || checkColumnConstraints(i)) {
                
                return false;
            }            
        }       
        
        return true;
        
    }
    
    /**
     * Checks the given row so see if any of the constraints are broken
     * 
     * @param row the row of constraints to be checked
     * @return boolean returns true if there are broken constraints
     */
    private boolean checkRowConstraints(int row) {
        for (int i = 0; i < gridSize - 1; i++) {
            if (rowConstraints[i][row] != null && !rowConstraints[i][row].CompareSquares()) {
                return true;
            } 
            
        }
        
        return false;        
    }
    
    /**
     * Checks the given column so see if any of the constraints are broken
     * 
     * @param column the column of constraints to be checked
     * @return boolean returns true if there are broken constraints
     */
    private boolean checkColumnConstraints(int column) {   
        for (int i = 0; i < gridSize - 1; i++) {
            if (columnConstraints[column][i] != null && !columnConstraints[column][i].CompareSquares()) {
                return true;
            }            
                        
        }       
        
        return false;        
    }
        
    /**
     * Checks the given row for any duplicated values
     * 
     * @param row the row of the grid to be checked
     * @return boolean returns true if there are duplicates
     */
    private boolean checkRowForDuplicate(int row) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[i][row].getValue() == grid[j][row].getValue() &&
                    grid[i][row].getValue() != 0 && i != j){
                    return true;
                }                
            }            
        }        
        return false;        
    }
    
    /**
     * Checks the given column for any duplicated values
     * 
     * @param column the column of the grid to be checked
     * @return boolean returns true if there are duplicates
     */
    private boolean checkColumnForDuplicate(int column) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (grid[column][i].getValue() == grid[column][j].getValue() &&
                    grid[column][i].getValue() != 0 && i != j){
                    return true;
                }                
            }            
        }        
        return false;        
    }
    
    /**
     * Generates and returns a string describing what the problems are with 
     * the current puzzle if any, this includes both duplicate values and broken 
     * constraints
     * 
     * @return String describing the problems and which rows/columns they're in
     */
    public String getProblems() {
        String output = "";
        String rowDupIndexs = "";
        String colDupIndexs = "";
        String rowConIndexs = "";
        String colConIndexs = "";
        
        for (int i = 0; i < gridSize; i++) {
            if (checkRowForDuplicate(i)) {
                rowDupIndexs += ", " + (i + 1);
            } 
            
            if (checkColumnForDuplicate(i)) {
                colDupIndexs += ", " + (i + 1);
            }
            
            if (checkRowConstraints(i)) {
                rowConIndexs += ", " + (i + 1);
            }
            
            if (checkColumnConstraints(i)) {
                colConIndexs += ", " + (i + 1);                
            }
            
        }
        
        if (!rowDupIndexs.equals("")) {
            output += "\nThere are duplicates on row(s) " + rowDupIndexs + "."; 
        }
        
        if (!colDupIndexs.equals("")) {
            output += "\nThere are duplicates on columns(s) " + colDupIndexs + "."; 
        }
        
        if (!rowConIndexs.equals("")) {
            output += "\nThere are broken constraints on row(s) " + rowConIndexs + ".";            
        }
        
        if (!colConIndexs.equals("")) {
            output += "\nThere are broken constraints on column(s) " + colConIndexs + ".";
        }
        
        return output;
        
    }
    
    //-------------------------------------------------------------------------------------------------
    
    public FutoshikiSquare getSquare(int row, int column) {
        return grid[row][column];        
    }
    
    public Constraint getRowConstraint(int row, int column) {
        return rowConstraints[row][column];
    }
        
    public Constraint getColumnConstraint(int row, int column) {
        return columnConstraints[row][column];
    }
    
    public int getGridSize() {
        return gridSize;        
    }
    
    private FutoshikiSquare getEmptySquare (FutoshikiPuzzle futo) {
        for (int i = 0; i < futo.getGridSize(); i++) {
            for (int j = 0; j < futo.getGridSize(); j++) {
                if (futo.getSquare(i, j).getValue() == 0) {
                    return futo.getSquare(i, j);
                }
            }
        }
        
        return null;    
    }
    
    public boolean solve (FutoshikiPuzzle futo) {
        if (futo.isLegal()) {
            FutoshikiSquare emptySquare = getEmptySquare(futo);
            if (emptySquare == null) {
                return true;
            } else {
                for (int j = 1; j <= gridSize; j++) {
                    emptySquare.setValue(j);                    
                    if (solve(futo)) {
                        return true;
                    }
                }
            }
            
            emptySquare.setValue(0);
            
        }
            
        return false;           
        
    }
    
    
}












