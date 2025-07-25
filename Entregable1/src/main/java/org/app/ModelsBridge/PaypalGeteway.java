package org.app.ModelsBridge;

public class PaypalGeteway implements PaymentGateway{
    @Override
    public void authorize(double amount) {
        System.out.println("Autrorizadno el pago de $" + amount + " con PayPal");
    }

    @Override
    public void capture(double amount) {
        System.out.println("Capturando el monto de $" + amount + " con PayPAl");
    }
}
