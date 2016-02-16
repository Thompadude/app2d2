package sw.app2d2.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import sw.app2d2.MainActivity;
import sw.app2d2.R;
import sw.app2d2.quiz.highscore.HighScoreDatabase;
import sw.app2d2.quiz.questions.Question;
import sw.app2d2.quiz.questions.QuestionHandler;

public class QuizResultActivity extends MainActivity {

    private ArrayAdapter<Question> questionAdapter;
    private QuestionHandler questionHandler;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_quiz_result);

        Intent intent = getIntent();

        user = (User) intent.getSerializableExtra("user");
        saveHighScore();

        questionAdapter = new QuizResultAdapter(this, R.layout.list_questions);

        questionHandler = (QuestionHandler) intent.getSerializableExtra("questionHandler");
        populateAdapter();

        TextView tvGameOverFeedback = (TextView) findViewById(R.id.tvGameOverFeedback);
        String feedback = String.format(getResources().getString(R.string.quiz_feedback), user.getUserName(), user.getScore(), questionHandler.getQuestions().size());
        tvGameOverFeedback.setText(feedback);

        ListView lvQuestions = (ListView) findViewById(R.id.lvQuestions);
        lvQuestions.setAdapter(questionAdapter);

        Button btnViewHighScore = (Button) findViewById(R.id.btnViewHighScore);
        btnViewHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent highScoreIntent = (Intent) getActivities().get("highScore");
                startActivity(highScoreIntent);
            }
        });
    }

    /**
     * Saves high score to the SQLite database.
     */
    private void saveHighScore() {
        HighScoreDatabase highScoreDatabase = new HighScoreDatabase(getApplicationContext());
        boolean dataSuccessfullyAdded = highScoreDatabase.insertData(user.getUserName(), user.getScore());
        if (dataSuccessfullyAdded) {
            Toast.makeText(getApplicationContext(), "High Score Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "High Score Save Error", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Populates the question result feedback adapter.
     */
    private void populateAdapter() {
        for (int i = 0; i < questionHandler.getQuestions().size(); i++) {
            questionAdapter.add(questionHandler.getQuestions().get(i));
        }
    }

    @Override
    public void onBackPressed() {
        // Prevents duplicate high score saves.
    }

}