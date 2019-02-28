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
public class FutoshikiSquareTest {

    @Test
    public void TestGetAndSet() {
        FutoshikiSquare futoSquare = new FutoshikiSquare(3, 3);
        futoSquare.setValue(1);
        futoSquare.setPencilMark(2);
        assertEquals(futoSquare.getValue(), 1);
        assertEquals(futoSquare.getPencilMark(), 2);        
    }
    
    @Test
    public void TestEmpty() {
        FutoshikiSquare futoSquare = new FutoshikiSquare(3, 3);
        assertEquals(futoSquare.isEmpty(), true);
        futoSquare.setPencilMark(2);
        assertEquals(futoSquare.isEmpty(), true);
        futoSquare.setValue(1);
        assertEquals(futoSquare.isEmpty(), false);
    }
    
    @Test
    public void TestDigitCount() {
        FutoshikiSquare futoSquare = new FutoshikiSquare(3, 3);
        futoSquare.setValue(1);
        assertEquals(futoSquare.isSingleDigit(), true);
        futoSquare.setValue(10);
        assertEquals(futoSquare.isSingleDigit(), false);
        
    }
    
    @Test
    public void GetCoordinates() {
        FutoshikiSquare futoSquare = new FutoshikiSquare(3, 6);
        assertEquals(futoSquare.getRow(), 6);
        assertEquals(futoSquare.getColumn(), 3);
        
    }    
    
    
}
