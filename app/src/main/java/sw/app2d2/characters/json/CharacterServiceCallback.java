package sw.app2d2.characters.json;

import sw.app2d2.characters.Character;

public interface CharacterServiceCallback {

    void serviceSuccess(Character character);

    void serviceFailure();

}
