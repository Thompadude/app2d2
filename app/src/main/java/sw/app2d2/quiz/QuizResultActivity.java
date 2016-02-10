package sw.app2d2.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import sw.app2d2.MainActivity;
import sw.app2d2.R;
import sw.app2d2.quiz.handlers.QuestionHandler;
import sw.app2d2.quiz.handlers.UserHandler;

public class QuizResultActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_quiz_result);

        Intent intent = getIntent();
        QuestionHandler questionHandler = (QuestionHandler) intent.getSerializableExtra("questionHandler");
        UserHandler user = (UserHandler) intent.getSerializableExtra("user");

        // This works!
        Log.v("LOG", String.valueOf(questionHandler.getQuestions().get(0).isAnsweredCorrect()));
        Log.v("LOG", String.valueOf(questionHandler.getQuestions().get(1).isAnsweredCorrect()));
        Log.v("LOG", String.valueOf(questionHandler.getQuestions().get(2).isAnsweredCorrect()));
        Log.v("LOG", user.getUserName() + " " + user.getScore());
    }
}
