package sw.app2d2.characters.service;

import sw.app2d2.characters.Character;

/**
 * Implements a contract to handle a character service success and a failure.
 */
public interface CharacterServiceCallback {

    void serviceSuccess(Character character);

    void serviceFailure(Exception error);

}