package sw.app2d2.characters;

import android.os.Bundle;
import android.text.Html;
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

/**
 * Collects information about characters in the Star Wars universe from Swapi, a
 * Star Wars API. The format is JSON.
 */
public class ProfileActivity extends MainActivity {

    private ImageView ivProfilePic;
    private String name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender, homeworld;
    private TextView tvProfileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);
        setTheme();

        ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);
        tvProfileContent = (TextView) findViewById(R.id.tvProfileContent);

        Spinner spinnerCharacters = (Spinner) findViewById(R.id.spinnerCharacters);
        ArrayAdapter<CharSequence> spinnerCharAdapter = ArrayAdapter.createFromResource(this, R.array.array_characters, R.layout.spinner);
        spinnerCharacters.setAdapter(spinnerCharAdapter);
        spinnerCharacters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                OnItemSelectedHandler onItemSelectedHandler = new OnItemSelectedHandler();
                name = onItemSelectedHandler.getStringCharacter(position);
                if (name != null) {
                    setCharacter();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Collect the information needed from Swapi and create new character.
     */
    private void setCharacter() {
        CharacterUrlHandler characterUrlHandler = new CharacterUrlHandler();
        String swapiUrl = characterUrlHandler.getCharacterUrl(name);

        AsyncHttpClient getCharacter = new AsyncHttpClient();
        getCharacter.get(swapiUrl,
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(JSONObject response) {
                        getSwapiConnectionSuccessToast();
                        try {
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
                                            getSwapiConnectionFailedToast();
                                        }
                                    });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, JSONObject errorResponse) {
                        getSwapiConnectionFailedToast();
                    }
                });
    }

    /**
     * Displays the information from Swapi in the TextView.
     *
     * @param tempCharacter is the tempCharacter created with the information from Swapi.
     */
    private void setTvProfileContent(Character tempCharacter) {
        String profileContent = String.format(getResources().getString(R.string.content_profile_html),
                tempCharacter.getHeight(),
                tempCharacter.getMass(),
                tempCharacter.getHairColor(),
                tempCharacter.getSkinColor(),
                tempCharacter.getEyeColor(),
                tempCharacter.getBirthYear(),
                tempCharacter.getGender(),
                tempCharacter.getHomeworld());
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
        ivProfilePic.setImageResource(profilePicHandler.getCharacterProfilePic(name));
    }

    private void getSwapiConnectionSuccessToast() {
        Toast.makeText(getApplicationContext(), "Downloading Profile Info", Toast.LENGTH_SHORT).show();
    }

    private void getSwapiConnectionFailedToast() {
        Toast.makeText(getApplicationContext(), "Connection to SWAPI database failed", Toast.LENGTH_SHORT).show();
    }

}