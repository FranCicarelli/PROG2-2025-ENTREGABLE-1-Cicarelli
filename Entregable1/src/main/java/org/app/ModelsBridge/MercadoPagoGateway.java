package org.app.ModelsBridge;

public class MercadoPagoGateway implements PaymentGateway{
    @Override
    public void authorize(double amount) {
        System.out.println("Autorizando el pago de $" + amount + " con MercadoPago");
    }

    @Override
    public void capture(double amount) {
        System.out.println("Capturando el monto de $" + amount + " con MercadoPago");
    }
}
