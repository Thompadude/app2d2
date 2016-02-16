package sw.app2d2.characters;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import sw.app2d2.MainActivity;
import sw.app2d2.R;
import sw.app2d2.characters.handlers.OnItemSelectedHandler;
import sw.app2d2.characters.handlers.ProfilePicHandler;
import sw.app2d2.characters.json.CharacterService;
import sw.app2d2.characters.json.CharacterServiceCallback;

/**
 * Present various characters from the Star Wars universe.
 */
public class CharacterActivity extends MainActivity implements CharacterServiceCallback {

    private ImageView ivProfilePic;
    private ProgressDialog progressDialog;
    private String characterName;
    private TextView tvProfileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_characters);

        ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);
        tvProfileContent = (TextView) findViewById(R.id.tvProfileContent);

        // Create a spinner to handle the user's character choice.
        Spinner spinnerCharacters = (Spinner) findViewById(R.id.spinnerCharacters);
        ArrayAdapter<CharSequence> spinnerCharAdapter = ArrayAdapter.createFromResource(this, R.array.array_characters, R.layout.spinner);
        spinnerCharacters.setAdapter(spinnerCharAdapter);
        spinnerCharacters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                OnItemSelectedHandler onItemSelectedHandler = new OnItemSelectedHandler();
                characterName = onItemSelectedHandler.getStringCharacter(position);
                if (characterName != null) {
                    setCharacter();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setCharacter() {
        toggleProgressDialog(true);
        CharacterService characterService = new CharacterService(this);
        characterService.fetchCharacter(characterName);
    }

    /**
     * Display the character information in the TextView.
     *
     * @param character is the character to be displayed.
     */
    private void setTvProfileContent(Character character) {
        String profileContent = String.format(getResources().getString(R.string.content_profile_html),
                character.getHeight(),
                character.getMass(),
                character.getHairColor(),
                character.getSkinColor(),
                character.getEyeColor(),
                character.getBirthYear(),
                character.getGender(),
                character.getHomeWorld());
        CharSequence profileContentHtml = Html.fromHtml(profileContent);
        tvProfileContent.setText(profileContentHtml);
        setIvProfilePic();
    }

    /**
     * Set character profile pic depending on the character name.
     * Adds an animation effect.
     */
    private void setIvProfilePic() {
        setFadeDurations(2000);
        ivProfilePic.startAnimation(getFadeInAnimation());

        ProfilePicHandler profilePicHandler = new ProfilePicHandler();
        ivProfilePic.setImageResource(profilePicHandler.getCharacterProfilePic(characterName));
    }

    private void toggleProgressDialog(boolean show) {
        if (show) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Fetching data from SWAPI");
            progressDialog.show();
        } else {
            if (progressDialog != null) {
                progressDialog.hide();
            }
        }
    }

    @Override
    public void serviceSuccess(Character character) {
        setTvProfileContent(character);
        toggleProgressDialog(false);
    }

    @Override
    public void serviceFailure() {
        toggleProgressDialog(false);
    }

}