package org.example;

public enum DeliveryLoad {
    VERY_HIGH(1.6),
    HIGH(1.4),
    INCREASED(1.2),
    NORMAL(1.0);

    private final double multiplier;

    DeliveryLoad(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }

}

