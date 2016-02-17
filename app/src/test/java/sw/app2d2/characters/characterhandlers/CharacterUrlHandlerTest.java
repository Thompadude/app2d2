package sw.app2d2.characters.characterhandlers;

import junit.framework.TestCase;

// JUnit3
public class CharacterUrlHandlerTest extends TestCase {

    CharacterUrlHandler characterUrlHandler;

    public void setUp() throws Exception {
        super.setUp();
        characterUrlHandler = new CharacterUrlHandler();
    }

    public void testGetCharacterUrl() throws Exception {
        assertEquals("http://swapi.co/api/people/1/", characterUrlHandler.getCharacterUrl("Luke Skywalker"));
        assertEquals(null, characterUrlHandler.getCharacterUrl("Darth Väder"));
        assertEquals(null, characterUrlHandler.getCharacterUrl(null));
        assertEquals(null, characterUrlHandler.getCharacterUrl("Fredagsmys"));
    }

}