package org.app.ModelsStrategy;

public class TruckShippingStrategy implements ShippingStrategy{
    @Override
    public double calculateCost(double weight, double dimension, String origin, String destination) {
        double price = weight * 0.2;
        return price;
    }
}
