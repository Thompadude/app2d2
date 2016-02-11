package sw.app2d2.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sw.app2d2.R;
import sw.app2d2.quiz.questions.Question;

public class QuizResultAdapter extends ArrayAdapter<Question> {

    private Context context;
    private Question currentQuestion;
    private List<Question> questions = new ArrayList<>();
    private TextView tvQuestion;
    private TextView tvCorrectAnswer;
    private TextView tvUserAnswer;
    private TextView tvIsAnsweredCorrect;

    public QuizResultAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    /**
     * From developer.android.com:
     * Get a View that displays the data at the specified position in the data set.
     * You can either create a View manually or inflate it from an XML layout file.
     * When the View is inflated, the parent View (GridView, ListView...) will apply
     * default layout parameters unless you use inflate(int, android.view.ViewGroup, boolean)
     * to specify a root view and to prevent attachment to the root.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // Get the layout inflater service from this context.
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            /*
                Inflate the view with list_questions.xml,
                provide a set of layout params (use same as parent),
                and create the correct subclass of LayoutParams.

                When last param is set to false the layout is inflated
                but will not be attached to any other layout. Although,
                it will use the parent params when created.
            */
            convertView = layoutInflater.inflate(R.layout.list_questions, parent, false);
        }

        // Get the specific position in the question list.
        currentQuestion = questions.get(position);

        // Get the Text Views from list_questions.xml
        tvQuestion = (TextView) convertView.findViewById(R.id.tvQuestion);
        tvCorrectAnswer = (TextView) convertView.findViewById(R.id.tvCorrectAnswer);
        tvUserAnswer = (TextView) convertView.findViewById(R.id.tvUserAnswer);
        tvIsAnsweredCorrect = (TextView) convertView.findViewById(R.id.tvIsAnsweredCorrect);

        setTextViewsText();

        return convertView;
    }

    private void setTextViewsText() {
        String question = String.format(context.getString(R.string.quiz_question), currentQuestion.getQuestion());
        tvQuestion.setText(question);

        String correctAnswer = String.format(context.getString(R.string.quiz_correct_answer), currentQuestion.getCorrectAnswer());
        tvCorrectAnswer.setText(correctAnswer);

        String userAnswer = String.format(context.getString(R.string.quiz_user_answer), currentQuestion.getUserAnswer());
        tvUserAnswer.setText(userAnswer);

        if (currentQuestion.isAnsweredCorrect()) {
            tvIsAnsweredCorrect.setText(context.getString(R.string.correct));
            tvIsAnsweredCorrect.setTextColor(context.getResources().getColor(R.color.correct));
        } else {
            tvIsAnsweredCorrect.setText(context.getString(R.string.incorrect));
            tvIsAnsweredCorrect.setTextColor(context.getResources().getColor(R.color.incorrect));
        }
    }

    @Override
    public void add(Question question) {
        questions.add(question);
        super.add(question);
    }

    @Override
    public Question getItem(int position) {
        return questions.get(position);
    }

    @Override
    public int getCount() {
        return questions.size();
    }

}