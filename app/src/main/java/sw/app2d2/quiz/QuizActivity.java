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
import sw.app2d2.quiz.handlers.QuestionHandler;
import sw.app2d2.quiz.handlers.UserHandler;

public class QuizActivity extends MainActivity {

    private boolean isGameOver;
    private Button btnSubmit;
    private int questionIndex;
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

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);

        // Get the question handler to get access to all the questions.
        questionHandler = new QuestionHandler();

        // Set the first question.
        setQuestion(questionIndex);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isGameOver) {
                    // Get answer from the user.
                    rbAnswer = (RadioButton) findViewById(rgAnswers.getCheckedRadioButtonId());
                    answer = rbAnswer.getText().toString();
                    // Check the answer
                    checkAnswer(questionHandler.getQuestions().get(questionIndex).isCorrect(answer));
                    // Uncheck the radio button.
                    rbAnswer.setChecked(false);
                }

                if (isGameOver) {
                    btnSubmit.setEnabled(false);
                    quizFinishedFeedback();
                    Button btnResult = (Button) findViewById(R.id.btnResult);
                    btnResult.setEnabled(true);
                }
            }
        });
    }

    /**
     * Set the question in the view.
     *
     * @param questionIndex is the index of the question list.
     */
    private void setQuestion(int questionIndex) {
        // Check if more questions is available.
        if (questionIndex >= questionHandler.getQuestions().size()) {
            isGameOver = true;
        }

        if (!isGameOver) {
            tvQuestion.setText(questionHandler.getQuestions().get(questionIndex).getQuestion());
            // Get the radio buttons to display the answer alternatives.
            List<RadioButton> rbAnswerAlternatives = new ArrayList<>();
            rgAnswers = (RadioGroup) findViewById(R.id.rgAnswers);
            for (int i = 0; i < 4; i++) {
                rbAnswerAlternatives.add((RadioButton) rgAnswers.getChildAt(i));
                // Get the answer alternatives and set it to the text of the radio buttons.
                rbAnswerAlternatives.get(i).setText(questionHandler.getQuestions().get(questionIndex).getAlternatives()[i]);
            }
        }
    }

    /**
     * Gets the answer from the user and handles it.
     *
     * @param isCorrect is true if the user provided the correct answer.
     */
    private void checkAnswer(boolean isCorrect) {
        if (isCorrect) {
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
            // Add a point if the user answered correct.
            user.addPoint();
            // ++ the questionIndex to access the correct question.
            questionIndex++;
            // Set the next question.
            setQuestion(questionIndex);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
            questionIndex++;
            setQuestion(questionIndex);
        }
    }

    private void quizFinishedFeedback() {
        String feedback = "Quiz is over! You answered " + user.getScore() +
                " of the " + questionHandler.getQuestions().size() +
                " questions correct.";
        Toast.makeText(getApplicationContext(), feedback, Toast.LENGTH_LONG).show();
    }

}