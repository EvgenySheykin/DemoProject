package org.example;

public class DeliveryRequest {
    private final int distance;
    private final boolean isLarge;
    private final boolean isFragile;
    private final DeliveryLoad loadLevel;

    public DeliveryRequest(int distance, boolean isLarge, boolean isFragile, DeliveryLoad loadLevel) {
        if (loadLevel == null) {
            throw new IllegalArgumentException("Уровень загрузки не может быть null");
        }
        this.distance = distance;
        this.isLarge = isLarge;
        this.isFragile = isFragile;
        this.loadLevel = loadLevel;
    }

    public int getDistance() {
        return distance;
    }

    public boolean isLarge() {
        return isLarge;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public DeliveryLoad getLoadLevel() {
        return loadLevel;
    }
}
