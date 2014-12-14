/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Bat;
import Game.SideName;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import static org.junit.Assert.*;
import org.junit.*;
/**
 *
 * @author Joep Kerste
 */
public class BatTesting {

    @Before
    public void setUp() {}

    @Test
    public void testConstructor() {
        Bat bat = new Bat(Color.RED, 1, 1, 5, SideName.BATBOTTOM);
        assertNotNull("Should be created", bat);
        assertEquals("Xpos incorrect", 1, bat.getXpos(),0.1);
        assertEquals("Ypos incorrect", 1, bat.getYpos(),0.1);
        assertEquals("Diameter incorrect", 5, bat.getDiameter(),0.1);
        assertEquals("Sidename incorrect", SideName.BATBOTTOM, bat.getSideName());
        assertEquals("Radius incorrect", (float)5/2, bat.getRadius(),0.1);
        Point2D p = new Point2D(1,1);
        assertEquals("Position incorrect", p, bat.getPosition());
    }

    @Test
    public void testDisplay() {
        //TODO
    }

    @Test
    public void testMove() {
        Bat bat = new Bat(Color.RED, 1, 1, 5, SideName.BATBOTTOM);
        Float xposStart = bat.getXpos();
        bat.move("1right");
        assertEquals("Should be the same", xposStart + bat.getXvelocity(), bat.getXpos(),1);
        bat.move("1left");
        bat.move("1left");
        assertEquals("Should be the same", xposStart - bat.getXvelocity(), bat.getXpos(),1);       
    }
    
    @Test
    public void testSetXpos()
    {
        Bat bat = new Bat(Color.RED, 1, 1, 5, SideName.BATBOTTOM);
        bat.setXpos(10);
        assertEquals("Xpos not set correctly", 10, bat.getXpos(), 0.1);
    }
    
    @Test
    public void testSetXvel()
    {
        Bat bat = new Bat(Color.RED, 1, 1, 5, SideName.BATBOTTOM);
        bat.setXvelocity(5);
        assertEquals("X velocity not set correctly", 5, bat.getXvelocity(), 0.1);
    }
}
