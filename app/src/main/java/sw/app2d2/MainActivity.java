package sw.app2d2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import sw.app2d2.characters.CharacterActivity;
import sw.app2d2.forcemeter.ForceMeterActivity;
import sw.app2d2.forcemeter.ForceValue;
import sw.app2d2.quiz.QuizNewGameActivity;
import sw.app2d2.quiz.QuizResultActivity;
import sw.app2d2.quiz.highscore.HighScoreActivity;
import sw.app2d2.quotes.YodaQuotes;

public class MainActivity extends Activity implements SensorEventListener {

    private AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
    private AlphaAnimation fadeOutAnimation = new AlphaAnimation(1, 0);
    private float accelerometer;
    private float accelerometerCurrent;
    private float accelerometerLast;
    private Map<String, Intent> activities;
    private Sensor sensor;
    private SensorManager sensorManager;
    private String quote;
    private TextView tvContentMain;
    private View thisView;
    private YodaQuotes yodaQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme();
        setContentView(R.layout.activity_main);
        setActivities();
        setFadeDurations(1000);

        // Get access to the accelerometer sensor and set float values for later use.
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelerometer = 0.00f;
        accelerometerCurrent = SensorManager.GRAVITY_EARTH;
        accelerometerLast = SensorManager.GRAVITY_EARTH;

        // Get access to Yoda quotes!
        yodaQuotes = new YodaQuotes();

        tvContentMain = (TextView) findViewById(R.id.content_main);
        setYodaQuote();

        // Set a new Yoda quote when clicking on the quote.
        tvContentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setYodaQuote();
            }
        });
    }

    private void setYodaQuote() {
        quote = yodaQuotes.generateYodaQuote(quote);
        tvContentMain.setText(quote);
        tvContentMain.startAnimation(getFadeInAnimation());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                startActivity((Intent) getActivities().get("home"));
                return true;
            case R.id.action_about:
                startActivity((Intent) getActivities().get("about"));
                return true;
            case R.id.action_lightdarksidemeter:
                startActivity((Intent) getActivities().get("lightDarkSideMeter"));
                return true;
            case R.id.action_profiles:
                startActivity((Intent) getActivities().get("profiles"));
                return true;
            case R.id.action_quiz:
                startActivity((Intent) getActivities().get("quiz"));
                return true;
            case R.id.action_quiz_high_score:
                startActivity((Intent) getActivities().get("highScore"));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Hide the menu item if already viewing that item's content.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (getLocalClassName()) {
            case "sw.app2d2.MainActivity":
                menu.getItem(0).setVisible(false);
                break;
            case "sw.app2d2.AboutActivity":
                menu.getItem(1).setVisible(false);
                break;
            case "sw.app2d2.forcemeter.ForceMeterActivity":
                menu.getItem(2).setVisible(false);
                break;
            case "sw.app2d2.characters.CharacterActivity":
                menu.getItem(3).setVisible(false);
                break;
            case "sw.app2d2.quiz.QuizNewGameActivity":
                menu.getItem(4).getSubMenu().getItem(0).setVisible(false);
                break;
            case "sw.app2d2.quiz.QuizActivity":
                menu.getItem(4).setVisible(false);
                break;
            case "sw.app2d2.quiz.highscore.HighScoreActivity":
                menu.getItem(4).getSubMenu().getItem(1).setVisible(false);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Set all the activities used throughout the application.
     */
    public void setActivities() {
        activities = new HashMap<>();
        activities.put("home", new Intent(this, MainActivity.class));
        activities.put("about", new Intent(this, AboutActivity.class));
        activities.put("lightDarkSideMeter", new Intent(this, ForceMeterActivity.class));
        activities.put("profiles", new Intent(this, CharacterActivity.class));
        activities.put("quiz", new Intent(this, QuizNewGameActivity.class));
        activities.put("quizResult", new Intent(this, QuizResultActivity.class));
        activities.put("highScore", new Intent(this, HighScoreActivity.class));
    }

    public Map getActivities() {
        return activities;
    }

    public AlphaAnimation getFadeInAnimation() {
        return fadeInAnimation;
    }

    public AlphaAnimation getFadeOutAnimation() {
        return fadeOutAnimation;
    }

    public View getThisView() {
        return thisView;
    }

    public void setThisView(View thisView) {
        this.thisView = thisView;
    }

    /**
     * Sets theme depending on the user's force value -- but only if it has been measured.
     */
    public void setTheme() {
        if (ForceValue.isMeasured()) {
            if (ForceValue.getForceValue() < 100) {
                setTheme(R.style.DarkSideTheme);
            } else {
                setTheme(R.style.LightSideTheme);
            }
        }
    }

    /**
     * Set the durations of the fade in and fade out used throughout application.
     */
    public void setFadeDurations(int duration) {
        fadeInAnimation.setDuration(duration);
        fadeOutAnimation.setDuration(duration);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get accelerometer values.
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Do magic with all values. Keep the variable "accelerometer" updated.
        accelerometer = accelerometerCurrent;
        accelerometerCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = accelerometerCurrent - accelerometerLast;
        accelerometer = accelerometer * 0.9f + delta;

        // If phone is shaken -- set new Yoda quote.
        if (accelerometer > 10) {
            setYodaQuote();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}