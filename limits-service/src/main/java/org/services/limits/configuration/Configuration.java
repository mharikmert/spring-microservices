package org.services.limits.configuration;

import org.services.limits.model.Limits;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("limits-service")
public class Configuration {
    private int minimum;
    private int maximum;

    public Configuration() {
        super();
    }

    public Configuration(int minimum, int maximum) {
        super();
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
