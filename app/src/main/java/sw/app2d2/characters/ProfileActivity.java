package sw.app2d2.characters;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import sw.app2d2.MainActivity;
import sw.app2d2.R;
import sw.app2d2.database.Data;

/**
 * Collects information about characters in the Star Wars universe from Swapi, a
 * Star Wars API. The format is JSON.
 */
public class ProfileActivity extends MainActivity {

    private ArrayAdapter<CharSequence> spinnerCharAdapter;
    private ImageView ivProfilePic;
    private Spinner spinnerCharacters;
    private String name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender, homeworld;
    private TextView tvProfileName, tvProfileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_profiles);
        setTheme();

        ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);
        tvProfileName = (TextView) findViewById(R.id.tvProfileName);
        tvProfileContent = (TextView) findViewById(R.id.tvProfileContent);

        spinnerCharacters = (Spinner) findViewById(R.id.spinnerCharacters);
        spinnerCharAdapter = ArrayAdapter.createFromResource(this, R.array.array_characters, android.R.layout.simple_spinner_item);

        spinnerCharAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCharacters.setAdapter(spinnerCharAdapter);
        spinnerCharacters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProfileMenuSelection profileMenuSelection = new ProfileMenuSelection();
                if (profileMenuSelection.getCharacterString(position) != null) {
                    setCharacter(profileMenuSelection.getCharacterString(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Collect the information needed from Swapi and create new character.
     *
     * @param characterToDisplay is the string ID to identify what character the user want info about.
     */
    private void setCharacter(String characterToDisplay) {
        // Based on the param characterID -- set the currentcharacter Swapi url.
        Data.getCharacterUrlData().setCharacterUrl(characterToDisplay);

        AsyncHttpClient getCharacter = new AsyncHttpClient();
        getCharacter.get(Data.getCharacterUrlData().getCharacterUrl(),
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        try {
                            name = response.getString("name");
                            height = response.getString("height");
                            mass = response.getString("mass");
                            hairColor = response.getString("hair_color");
                            skinColor = response.getString("skin_color");
                            eyeColor = response.getString("eye_color");
                            birthYear = response.getString("birth_year");
                            gender = response.getString("gender");

                            AsyncHttpClient getHomeWorld = new AsyncHttpClient();
                            getHomeWorld.get(response.getString("homeworld"),
                                    new JsonHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(JSONObject response) {
                                            try {
                                                homeworld = response.getString("name");

                                                Character tempCharacter = new Character(name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender, homeworld);
                                                setTvProfileContent(tempCharacter);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Throwable e, JSONObject errorResponse) {
                                            Toast.makeText(getApplicationContext(), "Connection to SWAPI database failed.", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, JSONObject errorResponse) {
                    }
                });
    }

    /**
     * Displays the information from Swapi in the TextView.
     *
     * @param character is the character created with the information from Swapi.
     */
    private void setTvProfileContent(Character character) {
        tvProfileName.setText(character.getName());
        tvProfileContent.setText(
                getString(R.string.content_profile,
                        character.getHeight(),
                        character.getMass(),
                        character.getHairColor(),
                        character.getSkinColor(),
                        character.getEyeColor(),
                        character.getBirthYear(),
                        character.getGender(),
                        character.getHomeworld()));
        setIvProfilePic(character);
    }

    /**
     * Sets character profile pic depending on character name.
     * Adds an animation effect.
     *
     * @param character the character profile picture to display.
     */
    private void setIvProfilePic(Character character) {
        setFadeDurations(2000);
        ivProfilePic.startAnimation(getFadeInAnimation());
        switch (character.getName()) {
            case "Luke Skywalker":
                ivProfilePic.setImageResource(R.drawable.icon_lukeskywalker);
                break;
            case "Darth Vader":
                ivProfilePic.setImageResource(R.drawable.icon_vader);
                break;
            case "Anakin Skywalker":
                ivProfilePic.setImageResource(R.drawable.icon_anakin);
                break;
            case "R2-D2":
                ivProfilePic.setImageResource(R.drawable.icon_r2d2_2);
                break;
            case "C-3PO":
                ivProfilePic.setImageResource(R.drawable.icon_c3po);
                break;
            case "Leia Organa":
                ivProfilePic.setImageResource(R.drawable.icon_leia);
                break;
            case "Obi-Wan Kenobi":
                ivProfilePic.setImageResource(R.drawable.icon_obiwan);
                break;
            case "Chewbacca":
                ivProfilePic.setImageResource(R.drawable.icon_chewbacca);
                break;
            case "Han Solo":
                ivProfilePic.setImageResource(R.drawable.icon_hansolo);
                break;
            case "Yoda":
                ivProfilePic.setImageResource(R.drawable.icon_yoda);
                break;
        }
    }

}