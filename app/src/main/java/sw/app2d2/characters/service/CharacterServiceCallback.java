package sw.app2d2.characters.service;

import sw.app2d2.characters.Character;

public interface CharacterServiceCallback {

    void serviceSuccess(Character character);

    void serviceFailure(Exception error);

}
