package org.app.ModelsStrategy;

public interface ShippingStrategy {
    double calculateCost(double weight, double dimension, String origin, String destination);
}
