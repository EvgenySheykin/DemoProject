package org.example;

public class DeliveryCalculator {

    public static int calculateDistanceCost(int distance, boolean isFragile) {
        if (isFragile && distance > 30) {
            throw new IllegalArgumentException("Хрупкие грузы нельзя перевозить дальше 30 км");
        }
        if (distance > 30) return 300;
        if (distance > 10) return 200;
        if (distance > 2) return 100;
        return 50;
    }

    public static int calculateSizeCost(boolean isLarge) {
        return isLarge ? 200 : 100;
    }

    public static int calculateFragileCost(boolean isFragile) {
        return isFragile ? 300 : 0;
    }

    public static double calculateLoadMultiplier(DeliveryLoad loadLevel) {
        return loadLevel.getMultiplier();
    }
}

