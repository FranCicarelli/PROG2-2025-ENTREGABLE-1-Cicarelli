package org.app.ModelsBridge;

import java.util.concurrent.CompletableFuture;

public class PaymentManager {
    public boolean processPayment(double amount, int paymentProcessor) {
        try {
            PaymentProcessor processor = getProcessor(paymentProcessor);
            boolean result = processor.processPayment(amount);
            if (!result) {
                System.err.println("Error al procesar el pago.");
            }
            return result;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public CompletableFuture<Boolean> processPaymentAsync(double amount, int paymentProcessor) {
        return CompletableFuture.supplyAsync(() -> processPayment(amount, paymentProcessor));
    }

    public boolean refundPayment(double amount, int paymentProcessor) {
        try {
            PaymentProcessor processor = getProcessor(paymentProcessor);
            boolean result = processor.refundPayment(amount);
            if (!result) {
                System.err.println("Error al procesar el reembolso.");
            }
            return result;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public CompletableFuture<Boolean> refundPaymentAsync(double amount, int paymentProcessor) {
        return CompletableFuture.supplyAsync(() -> refundPayment(amount, paymentProcessor));
    }

    private PaymentProcessor getProcessor(int paymentProcessor) {
        return switch (paymentProcessor) {
            case 1 -> new PaypalPaymentProcessor(new PaypalGeteway());
            case 2 -> new MercadoPagoPaymentProcessor(new MercadoPagoGateway());
            default -> throw new IllegalArgumentException("Solo proveedores como PayPal y MercadoPago.");
        };
    }
}
