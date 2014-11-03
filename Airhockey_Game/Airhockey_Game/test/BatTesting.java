/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import airhockey.domain.*;
import airhockey_game.MySketch;
import java.awt.Color;
import static org.junit.Assert.*;
import org.junit.*;
import processing.core.PVector;

/**
 *
 * @author Joep Kerste
 */
public class BatTesting {

    MySketch ms;

    @Before
    public void setUp() {
        ms = new MySketch();
        ms.init();
    }

    @Test
    public void testConstructor() {
        Bat bat = new Bat(Color.black, ms, 1, 1, 5, SideName.BATBOTTOM);
        assertNotNull("Should be created", bat);
        assertEquals(1, bat.getXpos(),0.1);
        assertEquals(1, bat.getYpos(),0.1);
        assertEquals(5, bat.getDiameter(),0.1);
        assertEquals(SideName.BATBOTTOM, bat.getSideName());
        assertEquals(new PVector(1,1), bat.getVector());
        assertEquals((float)5/2, bat.getRadius(),0.1);
    }

    @Test
    public void testDisplay() {
        //TODO
    }

    @Test
    public void testMove() {
        Bat bat = new Bat(Color.black, ms, 1, 1, 5, SideName.BATBOTTOM);
        Float xposStart = bat.getXpos();
        float yposStart = bat.getYpos();
        bat.move("1right");
        assertEquals("Should be the same", xposStart + bat.getXvelocity(), bat.getXpos(),1);
        bat.move("1left");
        bat.move("1left");
        assertEquals("Should be the same", xposStart - bat.getXvelocity(), bat.getXpos(),1);
        
    }
}
