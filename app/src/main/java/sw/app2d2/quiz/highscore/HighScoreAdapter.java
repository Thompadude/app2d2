package sw.app2d2.quiz.highscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sw.app2d2.R;
import sw.app2d2.quiz.User;

public class HighScoreAdapter extends ArrayAdapter<User> {

    private Context context;
    private List<User> users = new ArrayList<>();
    private TextView tvUserName;
    private TextView tvHighScore;
    private User currentUser;

    public HighScoreAdapter(Context context, int resource) {
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
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            /*
                Inflate the view with list_high_score.xml,
                provide a set of layout params (use same as parent),
                and create the correct subclass of LayoutParams.

                When last param is set to false the layout is inflated
                but will not be attached to any other layout. Although,
                it will use the parent params when created.
            */
            convertView = layoutInflater.inflate(R.layout.list_high_score, parent, false);
        }

        // Get the specific position in the question list.
        currentUser = users.get(position);

        // Get the Text Views from list_high_score.xml
        tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        tvHighScore = (TextView) convertView.findViewById(R.id.tvUserHighScore);

        setTextViewsText(position);

        return convertView;
    }

    private void setTextViewsText(int position) {
        String userName = String.format(context.getString(R.string.player_name), (position + 1), currentUser.getUserName());
        tvUserName.setText(userName);

        String userScore = String.format(context.getString(R.string.player_score), currentUser.getScore());
        tvHighScore.setText(userScore);
    }

    @Override
    public void add(User user) {
        users.add(user);
        super.add(user);
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public int getCount() {
        return users.size();
    }

}