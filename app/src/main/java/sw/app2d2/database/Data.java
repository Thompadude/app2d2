package sw.app2d2.database;

import sw.app2d2.characters.CharacterUrl;
import sw.app2d2.forcemeter.ForceValue;

/**
 * Database class for certain values.
 */
public class Data {

    private static ForceValue forceValue = new ForceValue();
    private static CharacterUrl characterUrl = new CharacterUrl();

    public static ForceValue getForceValueData() {
        return forceValue;
    }

    public static CharacterUrl getCharacterUrlData() {
        return characterUrl;
    }

}