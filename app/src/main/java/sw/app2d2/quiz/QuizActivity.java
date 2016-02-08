package sw.app2d2.quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sw.app2d2.MainActivity;
import sw.app2d2.R;
import sw.app2d2.quiz.quizhandlers.QuestionHandler;
import sw.app2d2.quiz.quizhandlers.UserHandler;

public class QuizActivity extends MainActivity {

    private Button btnSubmit;
    private List<RadioButton> rbAnswerAlternatives;
    private QuestionHandler questionHandler;
    private RadioButton rbAnswer;
    private RadioGroup rgAnswers;
    private String answer;
    private TextView tvHeadline, tvQuestion;
    private UserHandler user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTheme();

        // Get the value from QuizNewGameActivity and set the user.
        Bundle bundle = getIntent().getExtras();
        user = new UserHandler(bundle.getString("userName"));

        // Welcome the user.
        tvHeadline = (TextView) findViewById(R.id.quizHeadline);
        tvHeadline.setText(String.format(getResources().getString(R.string.quiz_headline), user.getUserName()));

        // Set the first question.
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        questionHandler = new QuestionHandler();
        // This is set to the 0 index for now. Find out a more dynamic method.
        tvQuestion.setText(questionHandler.getQuestions().get(0).getQuestion());

        // Set the answer alternatives.
        rbAnswerAlternatives = new ArrayList<>();
        rgAnswers = (RadioGroup) findViewById(R.id.rgAnswers);
        for (int i = 0; i < 4; i++) {
            rbAnswerAlternatives.add((RadioButton) rgAnswers.getChildAt(i));
            rbAnswerAlternatives.get(i).setText(questionHandler.getQuestions().get(0).getAlternatives()[i]);
        }

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get answer from the user
                rbAnswer = (RadioButton) findViewById(rgAnswers.getCheckedRadioButtonId());
                answer = rbAnswer.getText().toString();
                questionFeedback(questionHandler.getQuestions().get(0).isCorrect(answer));
            }
        });
    }

    private void questionFeedback(boolean isCorrect) {
        if (isCorrect) {
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
        }
    }

}