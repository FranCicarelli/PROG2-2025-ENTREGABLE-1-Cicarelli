package org.app;

import org.app.ModelsBridge.PaymentManager;
import org.app.ModelsStrategy.AirShippingStrategy;
import org.app.ModelsStrategy.BoatShippingStrategy;
import org.app.ModelsStrategy.ShippingCalculator;
import org.app.ModelsStrategy.TruckShippingStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    private ShippingCalculator calculator1;
    private ShippingCalculator calculator2;
    private ShippingCalculator calculator3;
    private PaymentManager manager;

    @BeforeEach
    void setUp() {
        calculator1 = new ShippingCalculator(150,13,
                "Madrid", "Barcelona", new TruckShippingStrategy());

        calculator2 = new ShippingCalculator(1425,45,
                "Shangai", "New York", new AirShippingStrategy());

        calculator3 = new ShippingCalculator(6050,150,
                "Buenos Aires", "Port Klang", new BoatShippingStrategy());

        manager = new PaymentManager();
    }

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
    void testProcessPaymentAsyncPayPal() {
        CompletableFuture<Void> result = manager.processPaymentAsync(37500, 1);

        result.join();
        assertTrue(result.isDone());
    }

    @Test
    void testProcessPaymentAsyncMercadoPago() {
        CompletableFuture<Void> result = manager.processPaymentAsync(37500, 2);

        result.join();
        assertTrue(result.isDone());
    }

    @Test
    void testRefundPaymentAsyncPayPal() {
        CompletableFuture<Void> result = manager.refundPaymentAsync(37500, 1);

        result.join();
        assertTrue(result.isDone());
    }

    @Test
    void testRefundPaymentAsyncMercadoPago() {
        CompletableFuture<Void> result = manager.refundPaymentAsync(37500, 2);

        result.join();
        assertTrue(result.isDone());
    }

    @Test
    void testProcessPaymentAsyncMetodoInvalido() {
        Exception exception = assertThrows(CompletionException.class, () -> {
            manager.processPaymentAsync(37500, 99).join();
        });

        assertTrue(exception.getCause() instanceof IllegalArgumentException);
        assertEquals("Solo proveedores como PayPal y MercadoPago.", exception.getCause().getMessage());
    }
}
