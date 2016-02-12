package sw.app2d2.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import sw.app2d2.MainActivity;
import sw.app2d2.R;
import sw.app2d2.quiz.handlers.QuestionHandler;
import sw.app2d2.quiz.handlers.UserHandler;
import sw.app2d2.quiz.highscore.HighScoreDatabase;
import sw.app2d2.quiz.questions.Question;

public class QuizResultActivity extends MainActivity {

    private ArrayAdapter<Question> questionAdapter;
    private HighScoreDatabase highScoreDatabase;
    private QuestionHandler questionHandler;
    private UserHandler user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_quiz_result);

        Intent intent = getIntent();

        questionHandler = (QuestionHandler) intent.getSerializableExtra("questionHandler");

        user = (UserHandler) intent.getSerializableExtra("user");
        saveHighScore();

        ListView lvQuestions = (ListView) findViewById(R.id.lvQuestions);
        questionAdapter = new QuizResultAdapter(this, R.layout.list_questions);

        populateAdapter();

        TextView tvGameOverFeedback = (TextView) findViewById(R.id.tvGameOverFeedback);
        String feedback = String.format(getResources().getString(R.string.quiz_feedback), user.getUserName(), user.getScore(), questionHandler.getQuestions().size());
        tvGameOverFeedback.setText(feedback);

        lvQuestions.setAdapter(questionAdapter);
    }

    private void saveHighScore() {
        highScoreDatabase = new HighScoreDatabase(getApplicationContext());
        boolean dataSuccessfullyAdded = highScoreDatabase.insertData(user.getUserName(), user.getScore());
        if (dataSuccessfullyAdded) {
            Toast.makeText(getApplicationContext(), "High Score Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "High Score Save Error", Toast.LENGTH_SHORT).show();
        }
    }

    private void populateAdapter() {
        for (int i = 0; i < questionHandler.getQuestions().size(); i++) {
            questionAdapter.add(questionHandler.getQuestions().get(i));
        }
    }

}