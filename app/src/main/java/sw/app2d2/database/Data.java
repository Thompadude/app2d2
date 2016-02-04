package sw.app2d2.database;

import sw.app2d2.characters.CharacterUrlHandler;
import sw.app2d2.forcemeter.ForceValue;

/**
 * Database class for certain values.
 */
public class Data {

    private static ForceValue forceValue = new ForceValue();
    private static CharacterUrlHandler characterUrlHandler = new CharacterUrlHandler();

    public static ForceValue getForceValueData() {
        return forceValue;
    }

}