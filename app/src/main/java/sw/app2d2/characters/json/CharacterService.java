package sw.app2d2.characters.json;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import sw.app2d2.characters.Character;
import sw.app2d2.characters.handlers.CharacterUrlHandler;

/**
 * Handles communication with Swapi -- a Star Wars API.
 */
public class CharacterService extends Service {

    BufferedReader bufferedReader;
    Character character;
    CharacterUrlHandler characterUrlHandler;
    CharacterServiceCallback characterServiceCallback;
    JSONObject characterData, characterHomeWorldData;
    String characterName, characterHomeWorld, characterHomeWorldUrl;
    StringBuilder response;

    /**
     * @param characterServiceCallback is the interface which handles the success and failures of the service.
     */
    public CharacterService(CharacterServiceCallback characterServiceCallback) {
        this.characterServiceCallback = characterServiceCallback;
    }

    /**
     * Fetch a character from the API. On success -- get the url to the character's home planet
     * and also fetch that from the same API.
     *
     * @param characterRequest is the name of the character to fetch information about.
     */
    public void fetchCharacter(String characterRequest) {
        this.characterName = characterRequest;
        characterUrlHandler = new CharacterUrlHandler();

        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                setBufferedReader(characterUrlHandler.getCharacterUrl(characterName));
                setResponse();
                return response.toString();
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null) {
                    // TODO add error handling
                    return;
                }

                try {
                    characterData = new JSONObject(s);
                    // Character data fetch successful! Move on to fetch home world URL.
                    characterHomeWorldUrl = characterData.optString("homeworld");
                    fetchCharacterHomeWorld();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    private Character createCharacter() {
        character = new Character(
                characterName,
                characterData.optString("height"),
                characterData.optString("mass"),
                characterData.optString("hair_color"),
                characterData.optString("skin_color"),
                characterData.optString("eye_color"),
                characterData.optString("birth_year"),
                characterData.optString("gender"),
                characterHomeWorld
        );
        return character;
    }

    /**
     * Fetch which home world the character have. characterHomeWorldUrl need to be set.
     */
    public void fetchCharacterHomeWorld() {
        if (!characterHomeWorldUrl.equals(null)) {
            new AsyncTask<String, Void, String>() {

                @Override
                protected String doInBackground(String... params) {
                    setBufferedReader(characterHomeWorldUrl);
                    setResponse();
                    return response.toString();
                }

                @Override
                protected void onPostExecute(String s) {
                    if (s == null) {
                        // TODO add error handling
                        return;
                    }

                    try {
                        characterHomeWorldData = new JSONObject(s);
                        characterHomeWorld = characterHomeWorldData.optString("name");
                        // All character data fetching successful!
                        characterServiceCallback.serviceSuccess(createCharacter());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }.execute();
        }
    }

    private void setResponse() {
        response = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.toString();
    }

    private void setBufferedReader(String url) {
        try {
            URL characterUrl = new URL(url);
            URLConnection urlConnection = characterUrl.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}