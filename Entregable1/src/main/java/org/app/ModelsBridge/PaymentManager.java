package org.app.ModelsBridge;

import java.util.concurrent.CompletableFuture;

public class PaymentManager {
    public void processPayment(double amount, int paymentProcessor) {
        getProcessor(paymentProcessor).processPayment(amount);
    }

    public CompletableFuture<Void> processPaymentAsync(double amount, int paymentProcessor) {
        return CompletableFuture.runAsync(() -> processPayment(amount, paymentProcessor));
    }

    public void refundPayment(double amount, int paymentProcessor) {
        getProcessor(paymentProcessor).refundPayment(amount);
    }

    public CompletableFuture<Void> refundPaymentAsync(double amount, int paymentProcessor) {
        return CompletableFuture.runAsync(() -> refundPayment(amount, paymentProcessor));
    }

    private PaymentProcessor getProcessor(int paymentProcessor) {
        return switch (paymentProcessor) {
            case 1 -> new PaypalPaymentProcessor(new PaypalGeteway());
            case 2 -> new MercadoPagoPaymentProcessor(new MercadoPagoGateway());
            default -> throw new IllegalArgumentException("Solo proveedores como PayPal y MercadoPago.");
        };
    }
}
