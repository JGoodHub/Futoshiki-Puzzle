/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package futoshikipuzzle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class ConstraintTest {
       
    @Test
    public void MoreThanComparisionTest() {
        FutoshikiSquare square1 = new FutoshikiSquare(1, 2);
        FutoshikiSquare square2 = new FutoshikiSquare(1, 3);           
        MoreThan moreThanCon = new MoreThan(square1, square2);
        
        square1.setValue(5);
        square2.setValue(1);
        assertEquals(moreThanCon.CompareSquares(), true);
        
        square1.setValue(1);
        square2.setValue(5);
        assertEquals(moreThanCon.CompareSquares(), false);                
    
    }
    
    @Test
    public void LessThanComparisionTest() {
        FutoshikiSquare square1 = new FutoshikiSquare(1, 2);
        FutoshikiSquare square2 = new FutoshikiSquare(1, 3);           
        LessThan lessThanCon = new LessThan(square1, square2);
        
        square1.setValue(1);
        square2.setValue(5);
        assertEquals(lessThanCon.CompareSquares(), true);
        
        square1.setValue(5);
        square2.setValue(1);
        assertEquals(lessThanCon.CompareSquares(), false);  
        
    }
    
    @Test
    public void MoreThanHorizontalToStringTest() {
        FutoshikiSquare square1 = new FutoshikiSquare(2, 1);
        FutoshikiSquare square2 = new FutoshikiSquare(3, 1);           
        MoreThan moreThanCon = new MoreThan(square1, square2);        
        assertEquals(moreThanCon.toString(), ">");    
    }
    
    @Test
    
    public void MoreThanVerticalToStringTest() {
        FutoshikiSquare square1 = new FutoshikiSquare(1, 2);
        FutoshikiSquare square2 = new FutoshikiSquare(1, 3);           
        MoreThan moreThanCon = new MoreThan(square1, square2);        
        assertEquals(moreThanCon.toString(), "v");
    }
    
    @Test
    public void LessThanhorizontalToStringTest() {
        FutoshikiSquare square1 = new FutoshikiSquare(2, 1);
        FutoshikiSquare square2 = new FutoshikiSquare(3, 1);           
        LessThan lessThanCon = new LessThan(square1, square2);        
        assertEquals(lessThanCon.toString(), "<");
    }
    
    @Test
    public void LessThanVerticalToStringTest() {
        FutoshikiSquare square1 = new FutoshikiSquare(1, 2);
        FutoshikiSquare square2 = new FutoshikiSquare(1, 3);           
        LessThan lessThanCon = new LessThan(square1, square2);        
        assertEquals(lessThanCon.toString(), "^");
    }
    
    
    
    
    
}
