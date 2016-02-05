package sw.app2d2.quiz;

import android.os.Bundle;
import android.widget.TextView;

import sw.app2d2.MainActivity;
import sw.app2d2.R;

public class QuizActivity extends MainActivity {

    TextView tvHeadline;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setTheme();

        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("userName");

        tvHeadline = (TextView) findViewById(R.id.quizHeadline);

        tvHeadline.setText(String.format(getResources().getString(R.string.quiz_headline), userName));
    }

}