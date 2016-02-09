package sw.app2d2.data;

import sw.app2d2.forcemeter.ForceValue;

public class ForceValueData {

    private static ForceValue forceValue = new ForceValue();

    public static ForceValue getForceValueData() {
        return forceValue;
    }

}