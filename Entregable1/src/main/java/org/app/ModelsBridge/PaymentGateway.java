package org.app.ModelsBridge;

public interface PaymentGateway {
    void authorize(double amount);
    void capture(double amount);
}
