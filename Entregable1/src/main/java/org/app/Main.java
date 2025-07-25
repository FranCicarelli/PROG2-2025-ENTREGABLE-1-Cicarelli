package org.app;

import org.app.ModelsBridge.PaymentManager;
import org.app.ModelsStrategy.*;
import java.util.concurrent.CompletableFuture;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShippingStrategy strategy;

        System.out.println("Seleccione el medio de transporte del envio:");
        System.out.println("1. Avion");
        System.out.println("2. Camion");
        System.out.println("3. Barco");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1 -> strategy = new AirShippingStrategy();
            case 2 -> strategy = new TruckShippingStrategy();
            case 3 -> strategy = new BoatShippingStrategy();
            default -> {
                System.out.println("No existe esa opción.");
                scanner.close();
                return;
            }
        }

        System.out.print("Ingrese el peso en Kg: ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Ingrese la dimension en metros cubicos: ");
        double dimension = Double.parseDouble(scanner.nextLine());
        System.out.print("Ingrese la ciudad de origen: ");
        String origin = scanner.nextLine();
        System.out.print("Ingrese la ciudad de destino: ");
        String destination = scanner.nextLine();

        ShippingCalculator calculator = new ShippingCalculator(weight, dimension, origin, destination, strategy);
        CompletableFuture<Double> priceFuture = calculator.calculateCostAsync();

        priceFuture.thenAccept(price -> {
            System.out.println("Precio total del envio: $" + price);

            System.out.println("Realizar el pago (1. Si - 2. No):");
            int optionPay = Integer.parseInt(scanner.nextLine());

            switch (optionPay) {
                case 1:
                    System.out.println("Seleccione el proveedor del pago:");
                    System.out.println("1. PayPal");
                    System.out.println("2. MercadoPago");
                    int paymentProcessor = Integer.parseInt(scanner.nextLine());

                    PaymentManager paymentManager = new PaymentManager();
                    CompletableFuture<Void> paymentFuture = paymentManager.processPaymentAsync(price, paymentProcessor);

                    paymentFuture.thenRun(() -> {
                        System.out.println("Deshacer el pago (1. Si - 2. No):");
                        int optionRefund = Integer.parseInt(scanner.nextLine());
                        if (optionRefund == 1) {
                            paymentManager.refundPaymentAsync(price, paymentProcessor);
                        }
                    }).join();
                    break;
                default:
                    break;
            }
        }).join();

        scanner.close();
    }
}
