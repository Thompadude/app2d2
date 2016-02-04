package sw.app2d2.forcemeter;

/**
 * Stores the user's current force value and keeps track of if it has been measured.
 */
public class ForceValue {

    private float forceValue;
    private boolean isMeasured = false;

    public float getForceValue() {
        return forceValue;
    }

    public void setForceValue(float forceValue) {
        this.forceValue = forceValue;
        this.isMeasured = true;
    }

    public boolean isMeasured() {
        return isMeasured;
    }

}