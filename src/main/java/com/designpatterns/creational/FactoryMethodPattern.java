package com.designpatterns.creational;

/*
provides an interface for creating objects in a super class, but allows subclasses to alter the type of objects
that will be created.
This pattern promotes loose coupling by reducing the dependency of a client class on the specific classes of objects
that it needs to instantiate.

### Pros:

- **Promotes Code Reuse**: By centralizing the creation of objects in a method, it allows for code reuse and consistency in the way objects are created.

- **Loose Coupling**: Reduces the dependency of the client code on specific classes, making it easier to extend or modify without changing existing code.

- **Single Responsibility Principle**: The factory method pattern promotes a single responsibility for classes—creating objects is centralized, and the rest of the code focuses on its main tasks.

- **Flexibility**: Allows you to introduce new products (concrete classes) without modifying the existing code.

### Cons:

- **Complexity**: Introducing a Factory Method can increase the complexity of the code as it adds more classes and interfaces to the system.

- **Overhead**: For simple applications, using a Factory Method might add unnecessary overhead and make the code harder to follow.
 */
// Abstract base class
abstract class Shape {
    abstract void draw();
}

// Concrete class 1
class Hexagon extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a Hexagon...");
    }
}

// Concrete class 2
class Pentagon extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a Pentagon...");
    }
}

// Abstract Factory class
abstract class ShapeFactory {
    abstract Shape createShape();

    // Factory Method
    static ShapeFactory getFactory(String type) {
        switch (type.toLowerCase()) {
            case "hexagon":
                return new HexagonFactory();
            case "pentagon":
                return new PentagonFactory();
            default:
                throw new IllegalArgumentException("Unknown shape type: " + type);
        }
    }
}

// Concrete Factory for Hexagon
class HexagonFactory extends ShapeFactory {
    @Override
    Shape createShape() {
        return new Hexagon();
    }
}

// Concrete Factory for Pentagon
class PentagonFactory extends ShapeFactory {
    @Override
    Shape createShape() {
        return new Pentagon();
    }
}

// Client code
public class FactoryMethodPattern {
    public static void main(String[] args) {
        ShapeFactory hexagonFactory = ShapeFactory.getFactory("Hexagon");
        Shape hexagon = hexagonFactory.createShape();
        hexagon.draw();

        ShapeFactory pentagonFactory = ShapeFactory.getFactory("Pentagon");
        Shape pentagon = pentagonFactory.createShape();
        pentagon.draw();
    }
}

