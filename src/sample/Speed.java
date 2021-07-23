package sample;

/**
 * The enum Speed.
 */
public enum Speed {
    /**
     * Slow speed.
     */
    SLOW(.5),
    /**
     * Medium speed.
     */
    MEDIUM(.3),
    /**
     * Fast speed.
     */
    FAST(.2);
    private final double speedValue;
    Speed(double speedValue) {
        this.speedValue = speedValue;
    }

    /**
     * Gets speed value.
     *
     * @return the speed value
     */
    public double getSpeedValue() {
        return speedValue;
    }

}
