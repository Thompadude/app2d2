package sw.app2d2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;

import java.util.HashMap;
import java.util.Map;

import sw.app2d2.characters.ProfileActivity;
import sw.app2d2.database.Data;
import sw.app2d2.forcemeter.ForceMeterActivity;

public class MainActivity extends Activity {

    private AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
    private AlphaAnimation fadeOutAnimation = new AlphaAnimation(1, 0);
    private Map activities;
    private View thisView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        setActivities();
        setFadeDurations(5000);
        setTheme();
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
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Disables menu item if already viewing that item's content.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (getLocalClassName()) {
            case "MainActivity":
                menu.getItem(0).setEnabled(false);
                break;
            case "AboutActivity":
                menu.getItem(1).setEnabled(false);
                break;
            case "ForceMeterActivity":
                menu.getItem(2).setEnabled(false);
                break;
            case "characters.ProfileActivity":
                menu.getItem(3).setEnabled(false);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public void setActivities() {
        activities = new HashMap();
        activities.put("home", new Intent(this, MainActivity.class));
        activities.put("about", new Intent(this, AboutActivity.class));
        activities.put("lightDarkSideMeter", new Intent(this, ForceMeterActivity.class));
        activities.put("profiles", new Intent(this, ProfileActivity.class));
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
        if (Data.getForceValueData().isMeasured()) {
            setThisView(this.findViewById(android.R.id.content));
            if (Data.getForceValueData().getForceValue() < 100) {
                getThisView().setBackgroundColor(ContextCompat.getColor(this, R.color.bg_dark_side));
                super.setTheme(R.style.DarkSide);
            } else {
                getThisView().setBackgroundColor(ContextCompat.getColor(this, R.color.bg_light_side));
                super.setTheme(R.style.LightSide);
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

}