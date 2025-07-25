package org.app.ModelsBridge;

public class MercadoPagoPaymentProcessor extends PaymentProcessor{
    public MercadoPagoPaymentProcessor(PaymentGateway paymentGateway) {
        super(paymentGateway);
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Procesando el pago.");
        paymentGateway.authorize(amount);
        paymentGateway.capture(amount);
    }

    @Override
    public void refundPayment(double amount) {
        System.out.println("Reembolsando el monto de: $" + amount);
    }
}
