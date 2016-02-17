package sw.app2d2.characters.characterhandlers;

public class CharacterUrlHandler {

    /**
     * Handles the url endpoint to the Swapi API.
     *
     * @param character the character to present information about.
     * @return the url endpoint to the specific character database.
     */
    public String getCharacterUrl(String character) {
        if (character == null) {
            return null;
        }

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
            case "Qui-Gon Jinn":
                return "http://swapi.co/api/people/32/";
            case "Padmé Amidala":
                return "http://swapi.co/api/people/35/";
            case "Mace Windu":
                return "http://swapi.co/api/people/51/";
            case "Dooku":
                return "http://swapi.co/api/people/67/";
            case "Rey":
                return "http://swapi.co/api/people/85/";
            case "BB8":
                return "http://swapi.co/api/people/87/";
            case "Finn":
                return "http://swapi.co/api/people/84/";
            case "Jango Fett":
                return "http://swapi.co/api/people/69/";
            default:
                return null;
        }
    }

}