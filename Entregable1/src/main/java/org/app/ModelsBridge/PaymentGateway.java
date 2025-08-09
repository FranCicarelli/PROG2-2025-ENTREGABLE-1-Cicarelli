package org.app.ModelsBridge;

public interface PaymentGateway {
    boolean authorize(double amount);
    boolean capture(double amount);
}
