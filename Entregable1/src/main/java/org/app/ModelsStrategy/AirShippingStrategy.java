package org.app.ModelsStrategy;

public class AirShippingStrategy implements ShippingStrategy{
    @Override
    public double calculateCost(double weight, double dimension, String origin, String destination) {
        double price = dimension * 800 + weight * 0.5;
        return price;
    }
}
