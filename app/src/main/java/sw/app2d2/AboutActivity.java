package sw.app2d2;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends MainActivity {

    TextView tvAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_about);

        tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvAbout.setText(R.string.about);
    }

}