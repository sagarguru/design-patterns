package com.designpatterns.behavioral;

/*
The Strategy Pattern is a behavioral design pattern that defines a family of algorithms,
encapsulates each one, and makes them interchangeable.
The strategy pattern lets the algorithm vary independently from the clients that use it.

Pros of the Strategy Pattern
Flexibility: The pattern provides a way to define a family of algorithms, encapsulate each one, and make them interchangeable. This makes the code more flexible and easier to extend.

Single Responsibility Principle (SRP): By delegating the algorithm to a separate class, each class has a single responsibility. This keeps the context class simple and focused on one task.

Open/Closed Principle (OCP): New strategies can be introduced without modifying the context class. The context only needs to be aware of the strategy interface.

Avoids Conditional Logic: Strategy Pattern eliminates the need for complex conditional logic that would otherwise be used to select different behaviors.

Cons of the Strategy Pattern
Increased Number of Classes: The Strategy Pattern can lead to a large number of additional classes, especially if you have many different strategies.

Client Awareness: The client needs to be aware of the different strategies to select the appropriate one, which can increase the complexity if there are many strategies to choose from.

No State Sharing: If the strategies need to share state, this can lead to redundant code or require additional mechanisms to manage shared state.

Complexity: For simple scenarios, using the Strategy Pattern might add unnecessary complexity. If there are only a couple of variations, simpler approaches might be more appropriate.
 */
// Step 1: Define the Strategy Interface
interface PaymentStrategy {
    void pay(int amount);
}

// Step 2: Implement Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with Credit Card: " + cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using PayPal: " + email);
    }
}

class BitcoinPayment implements PaymentStrategy {
    private String bitcoinAddress;

    public BitcoinPayment(String bitcoinAddress) {
        this.bitcoinAddress = bitcoinAddress;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Bitcoin: " + bitcoinAddress);
    }
}

// Step 3: Implement the Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    // Allows setting different strategies at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

// Step 4: Usage
public class StrategyDesign {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Paying with Credit Card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432", "John Doe"));
        cart.checkout(100);

        // Paying with PayPal
        cart.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        cart.checkout(200);

        // Paying with Bitcoin
        cart.setPaymentStrategy(new BitcoinPayment("1ABCDEF2GHIJK3LMNOP"));
        cart.checkout(300);
    }
}

