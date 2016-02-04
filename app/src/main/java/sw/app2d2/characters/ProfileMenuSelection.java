package sw.app2d2.characters;

public class ProfileMenuSelection {

    /**
     * Handles the choice from the dropdown profile menu.
     *
     * @param profileMenuPos the index from the menu.
     * @return the character string to help identify what character the user want to display.
     */
    public String getCharacterString(int profileMenuPos) {
        switch (profileMenuPos) {
            case 1:
                return "luke";
            case 2:
                return "vader";
            case 3:
                return "anakin";
            case 4:
                return "r2d2";
            case 5:
                return "c3po";
            case 6:
                return "leia";
            case 7:
                return "obiwan";
            case 8:
                return "chewbacca";
            case 9:
                return "hansolo";
            case 10:
                return "yoda";
            case 11:
                return null;
            case 12:
                return null;
            case 13:
                return null;
            case 14:
                return null;
            case 15:
                return null;
            case 16:
                return null;
            case 17:
                return null;
            case 18:
                return null;
            case 19:
                return null;
            case 20:
                return null;
            default:
                return null;
        }
    }

}
