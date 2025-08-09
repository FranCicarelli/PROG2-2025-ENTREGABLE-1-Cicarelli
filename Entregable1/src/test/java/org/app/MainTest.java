package org.app;

import org.app.ModelsBridge.PaymentManager;
import org.app.ModelsStrategy.AirShippingStrategy;
import org.app.ModelsStrategy.BoatShippingStrategy;
import org.app.ModelsStrategy.ShippingCalculator;
import org.app.ModelsStrategy.TruckShippingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private ShippingCalculator calculator1;
    private ShippingCalculator calculator2;
    private ShippingCalculator calculator3;
    private ShippingCalculator calculator4;
    private ShippingCalculator calculator5;
    private ShippingCalculator calculator6;
    private ShippingCalculator calculator7;
    private ShippingCalculator calculator8;
    private ShippingCalculator calculator9;
    private ShippingCalculator calculator10;
    private ShippingCalculator calculator11;
    private ShippingCalculator calculator12;
    private PaymentManager manager;

    @BeforeEach
    void setUp() {
        calculator1 = new ShippingCalculator(150, 13,
                "Madrid", "Barcelona", new TruckShippingStrategy());

        calculator2 = new ShippingCalculator(1425, 45,
                "Shangai", "New York", new AirShippingStrategy());

        calculator3 = new ShippingCalculator(6050, 150,
                "Buenos Aires", "Port Klang", new BoatShippingStrategy());

        calculator4 = new ShippingCalculator(-600, 18,
                "Buenos Aires", "Rosario", new TruckShippingStrategy());

        calculator5 = new ShippingCalculator(600, -18,
                "Buenos Aires", "Rosario", new TruckShippingStrategy());

        calculator6 = new ShippingCalculator(-600, -18,
                "Buenos Aires", "Rosario", new TruckShippingStrategy());

        calculator7 = new ShippingCalculator(-1000, 37,
                "Sao Paolo", "Santa Fe", new AirShippingStrategy());

        calculator8 = new ShippingCalculator(1000, -37,
                "Sao Paolo", "Santa Fe", new AirShippingStrategy());

        calculator9 = new ShippingCalculator(-1000, -37,
                "Sao Paolo", "Santa Fe", new AirShippingStrategy());

        calculator10 = new ShippingCalculator(-6050, 150,
                "Buenos Aires", "Port Klang", new BoatShippingStrategy());

        calculator11 = new ShippingCalculator(6050, -150,
                "Buenos Aires", "Port Klang", new BoatShippingStrategy());

        calculator12 = new ShippingCalculator(-6050, -150,
                "Buenos Aires", "Port Klang", new BoatShippingStrategy());

        manager = new PaymentManager();
    }

    //Shipping
    @Test
    void testCalculateCostAsyncTruck() {
        CompletableFuture<Double> result = calculator1.calculateCostAsync();
        double price = result.join();
        assertEquals(30, price);
    }

    @Test
    void testCalculateCostAsyncAir() {
        CompletableFuture<Double> result = calculator2.calculateCostAsync();
        double price = result.join();
        assertEquals(36712.5, price);
    }

    @Test
    void testCalculateCostAsyncBoat() {
        CompletableFuture<Double> result = calculator3.calculateCostAsync();
        double price = result.join();
        assertEquals(37500, price);
    }

    @Test
    void testCalculateCostAsyncErrorNegativeWeightTruck() {
        CompletableFuture<Double> result = calculator4.calculateCostAsync();
        double price = result.join();
        assertEquals(-1, price);
    }

    @Test
    void testCalculateCostAsyncErrorNegativeDimensionTruck() {
        CompletableFuture<Double> result = calculator5.calculateCostAsync();
        double price = result.join();
        assertEquals(-1, price);
    }

    @Test
    void testCalculateCostAsyncErrorNegativeWeightDimensionTruck() {
        CompletableFuture<Double> result = calculator6.calculateCostAsync();
        double price = result.join();
        assertEquals(-1, price);
    }

    @Test
    void testCalculateCostAsyncErrorNegativeWeightAir() {
        CompletableFuture<Double> result = calculator7.calculateCostAsync();
        double price = result.join();
        assertEquals(-1, price);
    }

    @Test
    void testCalculateCostAsyncErrorNegativeDimensionAir() {
        CompletableFuture<Double> result = calculator8.calculateCostAsync();
        double price = result.join();
        assertEquals(-1, price);
    }

    @Test
    void testCalculateCostAsyncErrorNegativeWeightDimensionAir() {
        CompletableFuture<Double> result = calculator9.calculateCostAsync();
        double price = result.join();
        assertEquals(-1, price);
    }

    @Test
    void testCalculateCostAsyncErrorNegativeWeightBoat() {
        CompletableFuture<Double> result = calculator10.calculateCostAsync();
        double price = result.join();
        assertEquals(-1, price);
    }

    @Test
    void testCalculateCostAsyncErrorNegativeDimensionBoat() {
        CompletableFuture<Double> result = calculator11.calculateCostAsync();
        double price = result.join();
        assertEquals(-1, price);
    }

    @Test
    void testCalculateCostAsyncErrorNegativeWeightDimensionBoat() {
        CompletableFuture<Double> result = calculator12.calculateCostAsync();
        double price = result.join();
        assertEquals(-1, price);
    }

    //Bridge
    @Test
    void testProcessPaymentAsyncPayPal() {
        assertTrue(manager.processPaymentAsync(37500, 1).join());
    }

    @Test
    void testProcessPaymentAsyncMercadoPago() {
        assertTrue(manager.processPaymentAsync(37500, 2).join());
    }

    @Test
    void testRefundPaymentAsyncPayPal() {
        assertTrue(manager.refundPaymentAsync(37500, 1).join());
    }

    @Test
    void testRefundPaymentAsyncMercadoPago() {
        assertTrue(manager.refundPaymentAsync(37500, 2).join());
    }

    @Test
    void testProcessPaymentAsyncInvalidProvider() {
        CompletableFuture<Boolean> future = manager.processPaymentAsync(1, 10);
        boolean result = future.join();
        assertFalse(result);
    }

    @Test
    void testRefundPaymentAsyncInvalidProvider() {
        CompletableFuture<Boolean> future = manager.refundPaymentAsync(1, 10);
        boolean result = future.join();
        assertFalse(result);
    }

    @Test
    void testProcessPaymentAsyncAmountNegativePayPal() {
        CompletableFuture<Boolean> future = manager.processPaymentAsync(-1, 1);
        boolean result = future.join();
        assertFalse(result);
    }

    @Test
    void testProcessPaymentAsyncAmountExcessivePayPal() {
        CompletableFuture<Boolean> future = manager.processPaymentAsync(200000, 1);
        boolean result = future.join();
        assertFalse(result);
    }

    @Test
    void testRefundPaymentAsyncInvalidAmountPayPal() {
        CompletableFuture<Boolean> future = manager.refundPaymentAsync(-1, 1);
        boolean result = future.join();
        assertFalse(result);
    }

    @Test
    void testProcessPaymentAsyncAmountNegativeMercadoPago() {
        CompletableFuture<Boolean> future = manager.processPaymentAsync(-1, 2);
        boolean result = future.join();
        assertFalse(result);
    }

    @Test
    void testProcessPaymentAsyncAmountExcessiveMercadoPago() {
        CompletableFuture<Boolean> future = manager.processPaymentAsync(100000, 2);
        boolean result = future.join();
        assertFalse(result);
    }

    @Test
    void testRefundPaymentAsyncInvalidAmountMercadoPago() {
        CompletableFuture<Boolean> future = manager.refundPaymentAsync(-1, 2);
        boolean result = future.join();
        assertFalse(result);
    }
}