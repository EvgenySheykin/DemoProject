package org.example;

public class DeliveryService {

    private static final int MIN_DELIVERY_COST = 400;

    public static int calculateDeliveryCost(DeliveryRequest request) {
        int baseCost = 0;

        baseCost += DeliveryCalculator.calculateDistanceCost(request.getDistance(), request.isFragile());
        baseCost += DeliveryCalculator.calculateSizeCost(request.isLarge());
        baseCost += DeliveryCalculator.calculateFragileCost(request.isFragile());

        double multiplier = DeliveryCalculator.calculateLoadMultiplier(request.getLoadLevel());
        int finalCost = (int) (baseCost * multiplier);

        return Math.max(finalCost, MIN_DELIVERY_COST);
    }
}

