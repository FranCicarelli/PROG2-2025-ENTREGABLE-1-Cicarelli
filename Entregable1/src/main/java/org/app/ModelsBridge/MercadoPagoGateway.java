package org.app.ModelsBridge;

public class MercadoPagoGateway implements PaymentGateway{
    @Override
    public boolean authorize(double amount) {
        System.out.println("Autorizando...");
        return amount > 0 && amount <= 50000;
    }

    @Override
    public boolean capture(double amount) {
        System.out.println("Capturando el monto de $" + amount + " con MercadoPago...");
        return true;
    }
}
