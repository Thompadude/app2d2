package sw.app2d2.characters.handlers;

import sw.app2d2.R;

public class ProfilePicHandler {

    /**
     * Handles the picture to be displayed depending on what character has been choosen.
     *
     * @param character the character to display a picture of.
     * @return the drawable to display.
     */
    public int getCharacterProfilePic(String character) {
        switch (character) {
            case "Luke Skywalker":
                return R.drawable.icon_lukeskywalker;
            case "Darth Vader":
                return R.drawable.icon_vader;
            case "Anakin Skywalker":
                return R.drawable.icon_anakin;
            case "R2-D2":
                return R.drawable.icon_r2d2;
            case "C-3PO":
                return R.drawable.icon_c3po;
            case "Leia Organa":
                return R.drawable.icon_leia;
            case "Obi-Wan Kenobi":
                return R.drawable.icon_obiwan;
            case "Chewbacca":
                return R.drawable.icon_chewbacca;
            case "Han Solo":
                return R.drawable.icon_hansolo;
            case "Yoda":
                return R.drawable.icon_yoda;
            case "Palpatine":
                return R.drawable.icon_palpatine;
            case "Boba Fett":
                return R.drawable.icon_bobafett;
            case "Qui-Gon Jinn":
                return R.drawable.icon_quigon;
            case "Padmé Amidala":
                return R.drawable.icon_padme;
            case "Mace Windu":
                return R.drawable.icon_mace;
            case "Dooku":
                return R.drawable.icon_dooku;
            case "Rey":
                return R.drawable.icon_rey;
            case "BB8":
                return R.drawable.icon_bb8;
            case "Finn":
                return R.drawable.icon_finn;
            case "Jango Fett":
                return R.drawable.icon_jango;
            default:
                return R.drawable.icon_profilemissing;
        }
    }

}