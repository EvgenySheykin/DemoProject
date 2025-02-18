package org.example;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        DeliveryRequest request = new DeliveryRequest(10, true, true, DeliveryLoad.INCREASED);
        int deliveryCost = DeliveryService.calculateDeliveryCost(request);
        System.out.println("Стоимость перевозки " + deliveryCost + " рублей");
    }

}