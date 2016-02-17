package sw.app2d2.characters.characterhandlers;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

// JUnit4
public class OnItemSelectedHandlerTest {

    OnItemSelectedHandler onItemSelectedHandler;

    @Before
    public void setUp() throws Exception {
        onItemSelectedHandler = new OnItemSelectedHandler();
    }

    @Test
    public void testGetStringCharacter() throws Exception {
        Assert.assertEquals("R2-D2", onItemSelectedHandler.getStringCharacter(4));
        Assert.assertEquals(null, onItemSelectedHandler.getStringCharacter(0));
        Assert.assertEquals(null, onItemSelectedHandler.getStringCharacter(221344));
    }

}