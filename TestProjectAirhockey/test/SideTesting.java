/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.HockeyField;
import Game.HumanPlayer;
import Game.IPlayer;
import Game.Puck;
import Game.Side;
import Game.SideColor;
import Game.SideName;
import java.rmi.RemoteException;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import org.junit.*;

/**
 *
 * @author Joep Kerste
 */
public class SideTesting {

    Side side;
    Side side2;
    IPlayer newPlayer;

    @Before
    public void setUp() throws RemoteException {
        newPlayer = new HumanPlayer("Henk");
        side = new Side(100, 200, 200, 100, SideColor.GREEN, SideName.RIGHT, newPlayer);
        side2 = new Side(0, 485, 560, 485, SideColor.BLUE, SideName.BOTTOM, newPlayer);
    }

    @Test
    public void testConstructor() {
        Assert.assertEquals("Binded player incorrect", newPlayer, side.getBoundPlayer());
        Assert.assertEquals("LineX1 incorrect", 100, side.getLineX1(), 0);
        Assert.assertEquals("LineX2 incorrect", 200, side.getLineX2(), 0);
        Assert.assertEquals("LineY1 incorrect", 200, side.getLineY1(), 0);
        Assert.assertEquals("LineY2 incorrect", 100, side.getLineY2(), 0);
        Assert.assertEquals("Sidename incorrect ", SideName.RIGHT, side.getSideName());
        Assert.assertEquals("Color incorrect ", Color.GREEN, side.getColor());
        Assert.assertNotNull(side.getBat());
    }

    @Test
    public void testGetStartPoint() {
        Point2D p = new Point2D(100, 200);
        Assert.assertEquals("Startpoint incorrect", p, side.getStartPoint());
    }

    @Test
    public void testGetEndPoint() {
        Point2D p = new Point2D(200, 100);
        Assert.assertEquals("Endpoint incorrect", p, side.getEndPoint());
    }

    @Test
    public void testHasCollided() {
        Puck puckc = new Puck();
        Puck puckn = new Puck();

        puckc.setPosition(new Point2D((float) 192, (float) 218.7));

//        Assert.assertNotNull(puckc, side.hasCollided(puckc));
//        Assert.assertNull(puckn, side.hasCollided(puckc));
    }

    @Test
    public void testBallHitsWall() {
        Puck puck1 = new Puck();
        Puck puck2 = new Puck();

        puck1.setPosition(new Point2D((float) 485, (float) 560));
        Assert.assertNotNull(side2.ballHitsWall(puck1.getPosition().getX(), puck1.getPosition().getX(), 1, 1));
        Assert.assertNull(side2.ballHitsWall(puck2.getPosition().getX(), puck2.getPosition().getX(), 1, 1));
    }

    @Test
    public void testCheckGoalLine() {
        Puck puck1 = new Puck();
        Puck puck2 = new Puck();

        puck1.setPosition(new Point2D(300, 560));
        Assert.assertNotNull(side2.checkGoalLine(puck1.getPosition().getX(), puck1.getPosition().getY(), 1, 1, puck1.getRadius()));
        Assert.assertNotNull(side2.checkGoalLine(puck2.getPosition().getX(), puck2.getPosition().getY(), 1, 1, puck2.getRadius()));

    }
}
