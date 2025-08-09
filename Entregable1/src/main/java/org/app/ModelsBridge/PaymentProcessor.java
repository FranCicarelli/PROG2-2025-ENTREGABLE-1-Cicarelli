package org.app.ModelsBridge;

public abstract class PaymentProcessor{
    protected PaymentGateway paymentGateway;

    public PaymentProcessor(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    public abstract boolean processPayment(double amount);
    public abstract boolean refundPayment(double amount);
}
