package org.app.ModelsBridge;

public class MercadoPagoPaymentProcessor extends PaymentProcessor{
    public MercadoPagoPaymentProcessor(PaymentGateway paymentGateway) {
        super(paymentGateway);
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Procesando el pago...");
        if (!paymentGateway.authorize(amount)) {
            return false;
        }
        return paymentGateway.capture(amount);
    }

    @Override
    public boolean refundPayment(double amount) {
        System.out.println("Reembolsando el monto de: $" + amount);
        return amount > 0;
    }
}
