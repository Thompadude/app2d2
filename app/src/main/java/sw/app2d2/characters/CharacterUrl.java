package sw.app2d2.characters;

public class CharacterUrl {

    private String characterUrl;

    public String getCharacterUrl() {
        return characterUrl;
    }

    /**
     * Set the correct URL based on what character the user want to display.
     *
     * @param characterUrl is the string ID to identify what character the user want info about.
     * @return the URL to the Swapi database.
     */
    public void setCharacterUrl(String characterUrl) {
        switch (characterUrl) {
            case "luke":
                this.characterUrl = "http://swapi.co/api/people/1/";
                break;
            case "vader":
                this.characterUrl = "http://swapi.co/api/people/4/";
                break;
            case "anakin":
                this.characterUrl = "http://swapi.co/api/people/11/";
                break;
            case "r2d2":
                this.characterUrl = "http://swapi.co/api/people/3/";
                break;
            case "c3po":
                this.characterUrl = "http://swapi.co/api/people/2/";
                break;
            case "leia":
                this.characterUrl = "http://swapi.co/api/people/5/";
                break;
            case "obiwan":
                this.characterUrl = "http://swapi.co/api/people/10/";
                break;
            case "chewbacca":
                this.characterUrl = "http://swapi.co/api/people/13/";
                break;
            case "hansolo":
                this.characterUrl = "http://swapi.co/api/people/14/";
                break;
            case "yoda":
                this.characterUrl = "http://swapi.co/api/people/20/";
                break;

        }
    }

}