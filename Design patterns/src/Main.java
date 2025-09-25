import behavioral.observer.*;
import behavioral.strategy.*;
import creational.singleton.*;
import creational.factory.*;
import structural.adapter.*;
import structural.decorator.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Logger logger = Logger.getInstance();
        boolean running = true;

        try {
            while (running) {
                System.out.println("\n=== Design Patterns Demo Menu ===");
                System.out.println("1. Observer Pattern (Stock Price Alerts)");
                System.out.println("2. Strategy Pattern (Payment Gateway)");
                System.out.println("3. Singleton Logger Test");
                System.out.println("4. Factory Pattern (Vehicle Booking)");
                System.out.println("5. Adapter Pattern (Legacy Payment)");
                System.out.println("6. Decorator Pattern (Chat Message)");
                System.out.println("7. Exit");
                System.out.print("Choose an option: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> runObserver(sc, logger);
                    case 2 -> runStrategy(sc, logger);
                    case 3 -> logger.info("Singleton Logger test message executed.");
                    case 4 -> runFactory(sc, logger);
                    case 5 -> runAdapter(sc, logger);
                    case 6 -> runDecorator(sc, logger);
                    case 7 -> {
                        running = false;
                        logger.info("Exiting application. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Invalid input type. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Validation error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error occurred: " + e.getMessage());
        } finally {
            sc.close();
            logger.info("Application finished execution.");
        }
    }

    // ---------------- Observer ----------------
    private static void runObserver(Scanner sc, Logger logger) {
        logger.info("Observer Pattern started.");
        System.out.print("Enter stock symbol: ");
        String stockSymbol = sc.nextLine().trim();
        if (stockSymbol.isEmpty()) throw new IllegalArgumentException("Stock symbol cannot be empty.");

        System.out.print("Enter initial price: ");
        double initialPrice = sc.nextDouble();
        if (initialPrice < 0) throw new IllegalArgumentException("Price cannot be negative.");
        sc.nextLine();

        Stock stock = new Stock(stockSymbol, initialPrice);

        System.out.print("Enter number of investors: ");
        int n = sc.nextInt();
        sc.nextLine();
        if (n <= 0) throw new IllegalArgumentException("Number of investors must be positive.");

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter investor " + i + " name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) throw new IllegalArgumentException("Investor name cannot be empty.");
            stock.attach(new ConcreteInvestor(name));
        }

        System.out.print("Enter new stock price to notify investors: ");
        double newPrice = sc.nextDouble();
        sc.nextLine();
        stock.setPrice(newPrice);
    }

    // ---------------- Strategy ----------------
    private static void runStrategy(Scanner sc, Logger logger) {
        logger.info("Strategy Pattern started.");
        PaymentContext context = new PaymentContext();
        System.out.print("Choose payment type (1=CreditCard, 2=UPI): ");
        int paymentChoice = sc.nextInt();
        sc.nextLine();
        if (paymentChoice != 1 && paymentChoice != 2) throw new IllegalArgumentException("Invalid payment type.");

        System.out.print("Enter payment amount: ");
        double paymentAmount = sc.nextDouble();
        sc.nextLine();
        if (paymentAmount <= 0) throw new IllegalArgumentException("Payment must be positive.");

        if (paymentChoice == 1) {
            System.out.print("Enter credit card number: ");
            String cardNumber = sc.nextLine().trim();
            context.setStrategy(new CreditCardPayment(cardNumber));
        } else {
            System.out.print("Enter UPI ID: ");
            String upiId = sc.nextLine().trim();
            context.setStrategy(new UPIPayment(upiId));
        }

        // Transient error handling with retry
        int retries = 3;
        while (retries > 0) {
            try {
                context.pay(paymentAmount);
                break;
            } catch (Exception e) {
                retries--;
                logger.warn("Payment failed, retrying... Remaining: " + retries);
                if (retries == 0) throw e;
            }
        }
    }

    // ---------------- Factory ----------------
    private static void runFactory(Scanner sc, Logger logger) {
        logger.info("Factory Pattern started.");
        System.out.print("Enter vehicle to book (Car/Bike): ");
        String vehicleType = sc.nextLine().trim();
        Vehicle vehicle = VehicleFactory.createVehicle(vehicleType);
        vehicle.book();
    }

    // ---------------- Adapter ----------------
    private static void runAdapter(Scanner sc, Logger logger) {
        logger.info("Adapter Pattern started.");
        System.out.print("Enter payment amount for adapter: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        if (amount <= 0) throw new IllegalArgumentException("Payment must be positive.");
        NewPaymentGateway adapter = new PaymentAdapter();

        int retries = 3;
        while (retries > 0) {
            try {
                adapter.payAmount(amount);
                break;
            } catch (Exception e) {
                retries--;
                logger.warn("Adapter payment failed, retrying... Remaining: " + retries);
                if (retries == 0) throw e;
            }
        }
    }

    // ---------------- Decorator ----------------
    private static void runDecorator(Scanner sc, Logger logger) {
        logger.info("Decorator Pattern started.");
        System.out.print("Enter your message: ");
        String messageText = sc.nextLine().trim();
        if (messageText.isEmpty()) throw new IllegalArgumentException("Message cannot be empty.");

        Message message = new BasicMessage(messageText);

        System.out.print("Add emoji? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) message = new EmojiDecorator(message);

        System.out.print("Add timestamp? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) message = new TimestampDecorator(message);

        System.out.print("Add file attachment? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("Enter file name: ");
            String fileName = sc.nextLine().trim();
            message = new FileAttachmentDecorator(message, fileName);
        }

        System.out.println("Final message: " + message.send());
    }
}
