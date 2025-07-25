package org.app.ModelsStrategy;

import java.util.concurrent.CompletableFuture;

public class ShippingCalculator {
    private double weight;
    private double dimension;
    private String origin;
    private String destination;
    private ShippingStrategy strategy;

    public ShippingCalculator(double weight, double dimension, String origin, String destination, ShippingStrategy strategy) {
        this.weight = weight;
        this.dimension = dimension;
        this.origin = origin;
        this.destination = destination;
        this.strategy = strategy;
    }

    public double calculateCost() {
        return strategy.calculateCost(weight, dimension, origin, destination);
    }
    public CompletableFuture<Double> calculateCostAsync() {
        return CompletableFuture.supplyAsync(this::calculateCost);
    }
}
