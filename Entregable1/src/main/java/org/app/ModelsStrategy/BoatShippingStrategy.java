package org.app.ModelsStrategy;

public class BoatShippingStrategy implements ShippingStrategy{
    @Override
    public double calculateCost(double weight, double dimension, String origin, String destination) {
        double price = dimension * 250;
        return price;
    }
}
