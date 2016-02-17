package sw.app2d2.forcemeter;

/**
 * Stores the user's current force value and keeps track of if it has been measured.
 */
public class ForceValue {

    private static float forceValue;
    private static boolean isMeasured = false;

    public static float getForceValue() {
        return forceValue;
    }

    public static void setForceValue(float forceValue) {
        ForceValue.forceValue = forceValue;
        isMeasured = true;
    }

    /**
     * @return true if the force value has been set once, else false.
     */
    public static boolean isMeasured() {
        return isMeasured;
    }

}