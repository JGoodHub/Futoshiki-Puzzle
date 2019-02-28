package futoshikipuzzle;


import futoshikipuzzle.FutoshikiPuzzle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FutoshikiPuzzleTest {

    @Test
    public void valueFillTest() {
        FutoshikiPuzzle futo = new FutoshikiPuzzle(5);
        futo.setSquare(0, 0, 1);
        futo.setSquare(0, 4, 2);
        futo.setSquare(4, 0, 3);
        futo.setSquare(4, 4, 4);
        assertEquals(futo.grid[0][0].getValue(), 1);
        assertEquals(futo.grid[0][4].getValue(), 2);
        assertEquals(futo.grid[4][0].getValue(), 3);
        assertEquals(futo.grid[4][4].getValue(), 4);   
    }
    
    @Test
    public void constraintFillTest() {
        FutoshikiPuzzle futo = new FutoshikiPuzzle(5);
        futo.setSquare(0, 0, 1);
        futo.setSquare(0, 1, 2);
        futo.setRowConstraint(0, 0, new LessThan(futo.grid[0][0], futo.grid[0][1]));
        assertEquals(futo.rowConstraints[0][0].CompareSquares(), true);
        futo.setSquare(3, 3, 4);
        futo.setSquare(4, 3, 3);
        futo.setColumnConstraint(3, 3, new MoreThan(futo.grid[3][3], futo.grid[4][3]));
        assertEquals(futo.columnConstraints[3][3].CompareSquares(), true);
        
    }
    
    @Test
    public void IsLegalTest() {
        FutoshikiPuzzle futo = new FutoshikiPuzzle(5);
        futo.setSquare(0, 0, 1);
        futo.setSquare(0, 1, 1);
        assertEquals(futo.isLegal(), false);
        futo.setSquare(0, 1, 2);
        futo.setRowConstraint(0, 0, new LessThan(futo.grid[0][0], futo.grid[0][1]));
        assertEquals(futo.isLegal(), true);        
    }
    
    @Test
    public void RandomFillTest() {
        FutoshikiPuzzle futo = new FutoshikiPuzzle(5);
        
        //Method should fill a new puzzle with a max of 5 random values and 5 
        //constraints, does not always produce a legal puzzle
        //futo.fillPuzzle();  
                
    }
    
    @Test
    public void GetProblemsTest() {
        FutoshikiPuzzle futo = new FutoshikiPuzzle(5);
        futo.setSquare(0, 0, 1);
        futo.setSquare(1, 0, 1);
        futo.setSquare(2, 2, 5);
        futo.setSquare(2, 3, 4);
        futo.setColumnConstraint(2, 2, new LessThan(futo.grid[2][2], futo.grid[2][3]));
        assertEquals(futo.isLegal(), false);
        
        System.out.println(futo.toString());
        //Method should states a duplicate issue on row 1 and a broken constraint 
        //in column 3
        System.out.println(futo.getProblems());
        
    }
    
     
    
}
