package sw.app2d2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_about);

        TextView tvAbout = (TextView) findViewById(R.id.tvAbout);
        ImageView ivAbout = (ImageView) findViewById(R.id.ivAbout);

        // Fade in the about text in 1 second.
        tvAbout.setAlpha(0f);
        tvAbout.animate().alpha(1f).setDuration(1000l).setListener(null);

        // Fade in the image in 3 seconds.
        ivAbout.setAlpha(0f);
        ivAbout.animate().alpha(1f).setDuration(3000l).setListener(null);
    }

}