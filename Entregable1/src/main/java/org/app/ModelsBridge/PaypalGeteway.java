package org.app.ModelsBridge;

public class PaypalGeteway implements PaymentGateway{
    @Override
    public boolean authorize(double amount) {
        System.out.println("Autrorizando...");
        return amount <= 100000 && amount > 0;
    }

    @Override
    public boolean capture(double amount) {
        System.out.println("Capturando el monto de $" + amount + " con PayPAl...");
        return true;
    }
}
