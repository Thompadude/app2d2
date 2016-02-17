package sw.app2d2.quiz.highscore;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import sw.app2d2.MainActivity;
import sw.app2d2.R;
import sw.app2d2.quiz.User;

public class HighScoreActivity extends MainActivity {

    private HighScoreAdapter highScoreAdapter;
    private HighScoreDatabase highScoreDatabase;
    private ListView lvHighScore;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        highScoreAdapter = new HighScoreAdapter(this, R.layout.list_high_score);
        highScoreDatabase = new HighScoreDatabase(this);

        populateAdapter();
        lvHighScore = (ListView) findViewById(R.id.lvHighScore);
        lvHighScore.setAdapter(highScoreAdapter);
    }

    /**
     * Get the database content and populate the high score adapter with it.
     */
    private void populateAdapter() {
        Cursor result = highScoreDatabase.getAllData();

        // If no content found in the database -- inform the user with a Toast.
        if (result.getCount() < 1) {
            Toast.makeText(getApplicationContext(), "No player scores registered.", Toast.LENGTH_SHORT).show();
        }

        // Create users with the information provided by the database and populate the high score adapter with them.
        while (result.moveToNext()) {
            String userName = result.getString(1);
            int userScore = result.getInt(2);
            user = new User(userName);
            user.setScore(userScore);
            highScoreAdapter.add(user);
        }
    }

}