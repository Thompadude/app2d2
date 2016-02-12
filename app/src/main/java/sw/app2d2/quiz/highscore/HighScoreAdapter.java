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

    private User currentUser;
    private List<User> users = new ArrayList<>();
    private TextView tvUserName;
    private TextView tvHighScore;

    public HighScoreAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_high_score, parent, false);
        }

        currentUser = users.get(position);

        tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        tvHighScore = (TextView) convertView.findViewById(R.id.tvUserHighScore);

        setTextViewsText();

        return convertView;
    }

    private void setTextViewsText() {
        tvUserName.setText(currentUser.getUserName());
        tvHighScore.setText(String.valueOf(currentUser.getScore()));
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