package sw.app2d2;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends MainActivity {

    TextView tvContentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme();

        tvContentMain = (TextView) findViewById(R.id.content_main);
        tvContentMain.setText(R.string.about);
    }

}