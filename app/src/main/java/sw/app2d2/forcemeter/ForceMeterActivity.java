package sw.app2d2.forcemeter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import sw.app2d2.MainActivity;
import sw.app2d2.R;

public class ForceMeterActivity extends MainActivity {

    private ImageView ivForceMeterImg;
    private SensorManager sensorManager;
    private Sensor theForceSensor;
    private TextView tvForceFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forcemeter);
        setFadeDurations(5000);

        // Get access to the light sensor.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        theForceSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        tvForceFeedback = (TextView) findViewById(R.id.tvForceFeedback);
        tvForceFeedback.startAnimation(getFadeOutAnimation());

        ivForceMeterImg = (ImageView) findViewById(R.id.ivForceMeterImg);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Get the value from the light sensor. Set the user's force value.
        ForceValue.setForceValue(event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, theForceSensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Unregister the sensor after 5 seconds.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Animate the background color after unregistering the sensor.
                animateBackground();
                unregisterListener();
            }
        }, 5000);
    }

    @Override
    public void setTheme() {
    }

    /**
     * Animates the background depending on the client's force value.
     */
    public void animateBackground() {
        setThisView(this.findViewById(android.R.id.content));
        if (ForceValue.getForceValue() < 100) {
            tvForceFeedback.setText(R.string.content_forcemeter_dark);
            tvForceFeedback.setTextColor(getResources().getColor(R.color.white));

            ivForceMeterImg.setImageResource(R.drawable.theme_darkside);

            getThisView().setBackgroundColor(getResources().getColor(R.color.bg_dark_side));
        } else {
            tvForceFeedback.setText(R.string.content_forcemeter_light);
            tvForceFeedback.setTextColor(getResources().getColor(R.color.black));

            ivForceMeterImg.setImageResource(R.drawable.theme_lightside);

            getThisView().setBackgroundColor(getResources().getColor(R.color.bg_light_side));
        }
        ivForceMeterImg.startAnimation(getFadeInAnimation());
        getThisView().startAnimation(getFadeInAnimation());
    }

    /**
     * Forced into writing this method -- was getting errors in run() method in onResume()
     * when trying to unregister with the "normal" method.
     */
    private void unregisterListener() {
        sensorManager.unregisterListener(this);
    }

}