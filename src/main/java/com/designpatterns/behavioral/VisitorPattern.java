package com.designpatterns.behavioral;

/*
The Visitor design pattern is a behavioral design pattern that allows you to separate algorithms from the objects
on which they operate. This pattern is particularly useful when you have a set of operations to perform on a structure
of objects of different types,
and you want to avoid adding these operations to the classes of the objects themselves.

Pros
Separation of Concerns: Visitor pattern separates the algorithm from the objects on which it operates. This makes it easier to add new operations without modifying the objects.

Open/Closed Principle: The pattern follows the open/closed principle, allowing you to introduce new operations without changing existing code.

Ease of Extension: Adding new operations is straightforward because the pattern allows you to define new visitors without altering the object structure.

Flexibility: The pattern makes it easy to perform complex operations on objects without complicating their interfaces or cluttering them with unrelated responsibilities.

Cons
Violation of Encapsulation: Visitor pattern can violate encapsulation by exposing the internal state of the elements to the visitor, which may lead to maintenance issues.

Complexity in Adding New Element Types: Adding new types of elements requires changes to the Visitor interface and all of its implementations, which can be cumbersome if there are many visitors.

Double Dispatch: The pattern relies on double dispatch, which might be confusing and difficult to understand for those unfamiliar with the concept.

Tight Coupling: The visitor and the element interfaces are tightly coupled, which can lead to less flexible designs.
 */

interface ItemElement {
    int accept(ShoppingCartVisitor visitor);
}

class Book implements ItemElement {
    private int price;
    private String isbnNumber;

    public Book(int price, String isbnNumber) {
        this.price = price;
        this.isbnNumber = isbnNumber;
    }

    public int getPrice() {
        return price;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

class Fruit implements ItemElement {
    private int pricePerKg;
    private int weight;
    private String name;

    public Fruit(int pricePerKg, int weight, String name) {
        this.pricePerKg = pricePerKg;
        this.weight = weight;
        this.name = name;
    }

    public int getPricePerKg() {
        return pricePerKg;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

interface ShoppingCartVisitor {
    int visit(Book book);
    int visit(Fruit fruit);
}

class ShoppingCartVisitorImpl implements ShoppingCartVisitor {

    @Override
    public int visit(Book book) {
        int cost = book.getPrice();
        System.out.println("Book ISBN::" + book.getIsbnNumber() + " cost =" + cost);
        return cost;
    }

    @Override
    public int visit(Fruit fruit) {
        int cost = fruit.getPricePerKg() * fruit.getWeight();
        System.out.println(fruit.getName() + " cost = " + cost);
        return cost;
    }
}

public class VisitorPattern {

    public static void main(String[] args) {
        ItemElement[] items = new ItemElement[]{
                new Book(20, "1234"),
                new Book(100, "5678"),
                new Fruit(10, 2, "Banana"),
                new Fruit(5, 5, "Apple")
        };

        int total = calculatePrice(items);
        System.out.println("Total Cost = " + total);
    }

    private static int calculatePrice(ItemElement[] items) {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum = 0;
        for (ItemElement item : items) {
            sum += item.accept(visitor);
        }
        return sum;
    }
}