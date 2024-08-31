package com.designpatterns.structural;

/*
allows you to dynamically add behavior or responsibilities to an object without modifying its code.
This pattern involves creating a set of decorator classes that are used to wrap concrete components.
Pros of the Decorator Pattern
Flexibility: Decorators provide a flexible alternative to subclassing for extending functionality. They allow behaviors to be added or removed at runtime.

Single Responsibility Principle: By using decorators, you can divide complex behaviors among different classes, adhering to the Single Responsibility Principle.

Open/Closed Principle: The pattern promotes the Open/Closed Principle by allowing objects to be open for extension but closed for modification.

Reusable and Composable: Decorators can be reused and combined in various ways, making them very composable.

Cons of the Decorator Pattern
Complexity: The decorator pattern can lead to a system that is more complex to understand and maintain, especially if there are many decorators.

Multiple Small Objects: This pattern tends to produce a large number of small objects, which can be overwhelming and may increase memory usage.

Order Matters: The order in which decorators are applied can change the behavior of the object, which can make the system more error-prone and difficult to debug.

Can be Overused: There is a risk of overusing decorators, leading to code that is difficult to understand and maintain.
 */
// Component Interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple coffee";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

// Decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription();
    }

    @Override
    public double getCost() {
        return coffee.getCost();
    }
}

// Concrete Decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 1.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

// Usage
public class DecoratorPattern {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
    }
}
