package sw.app2d2.characters.characterhandlers;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import sw.app2d2.R;

// JUnit4
public class ProfilePicHandlerTest extends TestCase {

    ProfilePicHandler profilePicHandler;

    @Before
    public void setUp() throws Exception {
        profilePicHandler = new ProfilePicHandler();
    }

    @Test
    public void testGetCharacterProfilePic() throws Exception {
        Assert.assertEquals(R.drawable.icon_obiwan, profilePicHandler.getCharacterProfilePic("Obi-Wan Kenobi"));
        Assert.assertEquals(R.drawable.icon_profilemissing, profilePicHandler.getCharacterProfilePic("Obi-Wan Knubbig"));
        Assert.assertEquals(R.drawable.icon_profilemissing, profilePicHandler.getCharacterProfilePic(null));
    }

}