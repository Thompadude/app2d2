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

    private void populateAdapter() {
        Cursor result = highScoreDatabase.getAllData();

        if (result.getCount() < 1) {
            Toast.makeText(getApplicationContext(), "No player scores registered.", Toast.LENGTH_SHORT).show();
        }

        while (result.moveToNext()) {
            String userName = result.getString(1);
            int userScore = result.getInt(2);
            user = new User(userName);
            user.setScore(userScore);
            highScoreAdapter.add(user);
        }
    }

}