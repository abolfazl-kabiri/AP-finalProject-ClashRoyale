package sample;

public enum Speed {
    SLOW(.8),
    MEDIUM(.6),
    FAST(.5);
    private final double speedValue;
    Speed(double speedValue) {
        this.speedValue = speedValue;
    }

    public double getSpeedValue() {
        return speedValue;
    }

}
