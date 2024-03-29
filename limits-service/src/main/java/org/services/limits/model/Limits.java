package org.services.limits.model;

public class Limits {
    private int minimum;
    private int maximum;

    public Limits() {
    }

    public Limits(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }
    public int getMaximum() {
        return maximum;
    }
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
