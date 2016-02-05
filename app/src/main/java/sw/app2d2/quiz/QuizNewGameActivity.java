package sw.app2d2.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sw.app2d2.MainActivity;
import sw.app2d2.R;

public class QuizNewGameActivity extends MainActivity {

    EditText etName;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newquiz);
        setTheme();

        etName = (EditText) findViewById(R.id.etName);
        btnStart = (Button) findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etName.getText().toString();
                if (!userName.equals("")) {
                    Intent newQuiz = new Intent(getApplicationContext(), QuizActivity.class);
                    newQuiz.putExtra("userName", userName);
                    startActivity(newQuiz);
                } else {
                    Toast.makeText(getApplicationContext(), "Fill in a user name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}