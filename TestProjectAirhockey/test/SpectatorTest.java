/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Game.Spectator;
import java.rmi.RemoteException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Roy
 */
public class SpectatorTest {
    Spectator s;
    public SpectatorTest() {
    }
    
    @Before
    public void setUp() throws RemoteException {
        s = new Spectator("Henk");
    }
    
    /**
     * Test for registering an user.
     */
    @Test
    public void testSpectator() throws RemoteException
    {
        assertNotNull("Should be created",new Spectator("theo"));
        assertNotNull("Should be created", s);
    }
}
