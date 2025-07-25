package org.app.ModelsBridge;

public abstract class PaymentProcessor{
    protected PaymentGateway paymentGateway;

    public PaymentProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    public abstract void processPayment(double amount);
    public abstract void refundPayment(double amount);
}
