package sw.app2d2.characters;

public class OnItemSelectedHandler {

    /**
     * Handles the choice from the dropdown profile menu.
     *
     * @param profileMenuPos the index from the menu.
     * @return the character string to help identify what character the user want to display.
     */
    public String getStringCharacter(int profileMenuPos) {
        switch (profileMenuPos) {
            case 1:
                return "Luke Skywalker";
            case 2:
                return "Darth Vader";
            case 3:
                return "Anakin Skywalker";
            case 4:
                return "R2-D2";
            case 5:
                return "C-3PO";
            case 6:
                return "Leia Organa";
            case 7:
                return "Obi-Wan Kenobi";
            case 8:
                return "Chewbacca";
            case 9:
                return "Han Solo";
            case 10:
                return "Yoda";
            case 11:
                return "Palpatine";
            case 12:
                return "Boba Fett";
            case 13:
                return "Qui-Gon Jinn";
            case 14:
                return "Padmé Amidala";
            case 15:
                return "Mace Windu";
            case 16:
                return "Dooku";
            case 17:
                return "Rey";
            case 18:
                return "BB8";
            case 19:
                return "Finn";
            case 20:
                return "Jango Fett";
            default:
                return null;
        }
    }

}
