package sw.app2d2.characters.json;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

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

public class CharacterService extends Service {

    BufferedReader bufferedReader;
    Character character;
    CharacterUrlHandler characterUrlHandler;
    JSONObject characterData, characterHomeworldData;
    String characterName, characterHomeworld, characterHomeworldUrl;
    StringBuilder response;

    public CharacterService() {
    }

    public Character getCharacter(String characterRequest) {
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
                    Toast.makeText(getApplicationContext(), "Connection to Swapi failed", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    characterData = new JSONObject(s);
                    characterHomeworldUrl = characterData.optString("homeworld");
                    setCharacterHomeworld();
                    createNewCharacter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
        return null;
    }


    private void createNewCharacter() {
        // TODO fixa homeworld!!!
        character = new Character(
                characterName,
                characterData.optString("name"),
                characterData.optString("mass"),
                characterData.optString("hair_color"),
                characterData.optString("skin_color"),
                characterData.optString("eye_color"),
                characterData.optString("birth_year"),
                characterData.optString("gender"),
                characterHomeworld
        );
        Toast.makeText(getApplicationContext(), "JAAAA", Toast.LENGTH_LONG).show();
    }

    public void setCharacterHomeworld() {
        new AsyncTask<String, Void, String>() {

            @Override
            protected String doInBackground(String... params) {
                setBufferedReader(characterHomeworldUrl);
                setResponse();
                return response.toString();
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null) {
                    Toast.makeText(getApplicationContext(), "Connection to Swapi failed", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    characterHomeworldData = new JSONObject(s);
                    characterHomeworld = characterHomeworldData.optString("name");
                    createNewCharacter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
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