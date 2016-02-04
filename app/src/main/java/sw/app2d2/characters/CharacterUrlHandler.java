package sw.app2d2.characters;

public class CharacterUrlHandler {

    public String getCharacterUrl(String character) {
        switch (character) {
            case "Luke Skywalker":
                return "http://swapi.co/api/people/1/";
            case "Darth Vader":
                return "http://swapi.co/api/people/4/";
            case "Anakin Skywalker":
                return "http://swapi.co/api/people/11/";
            case "R2-D2":
                return "http://swapi.co/api/people/3/";
            case "C-3PO":
                return "http://swapi.co/api/people/2/";
            case "Leia Organa":
                return "http://swapi.co/api/people/5/";
            case "Obi-Wan Kenobi":
                return "http://swapi.co/api/people/10/";
            case "Chewbacca":
                return "http://swapi.co/api/people/13/";
            case "Han Solo":
                return "http://swapi.co/api/people/14/";
            case "Yoda":
                return "http://swapi.co/api/people/20/";
            case "Palpatine":
                return "http://swapi.co/api/people/21/";
            case "Boba Fett":
                return "http://swapi.co/api/people/22/";
            default:
                return null;
        }
    }

}