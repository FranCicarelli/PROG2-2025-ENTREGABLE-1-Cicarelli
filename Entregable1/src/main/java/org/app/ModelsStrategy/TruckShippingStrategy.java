package org.app.ModelsStrategy;

public class TruckShippingStrategy implements ShippingStrategy{
    @Override
    public double calculateCost(double weight, double dimension, String origin, String destination) {
        if (weight > 0 && dimension > 0) {
            double price = weight * 0.2;
            return price;
        }
        return -1;
    }
}
