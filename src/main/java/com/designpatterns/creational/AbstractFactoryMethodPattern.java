package com.designpatterns.creational;

// Abstract Factory
/*
provides an interface for creating families of related or dependent objects without specifying their concrete classes.
This pattern is useful when a system needs to be independent of how its objects are created, composed, and represented.

Benefits:
Encapsulation of object creation: The pattern hides the concrete classes from the client, which only interacts with the abstract factories and products.
Consistency among products: It ensures that products created by the same factory are compatible with each other.
Ease of switching product families: The client can switch the entire family of products by changing the factory without altering the client code.
Drawbacks:
Complexity: The pattern increases the number of classes in the system.
Rigid structure: Adding new kinds of products can require changes to the Abstract Factory interface and all of its subclasses.
 */
interface UIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory for Windows
class WindowsFactory implements UIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// Concrete Factory for MacOS
class MacOSFactory implements UIFactory {
    public Button createButton() {
        return new MacOSButton();
    }

    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}

// Abstract Product - Button
interface Button {
    void paint();
}

// Concrete Product - Windows Button
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering a button in Windows style.");
    }
}

// Concrete Product - MacOS Button
class MacOSButton implements Button {
    public void paint() {
        System.out.println("Rendering a button in MacOS style.");
    }
}

// Abstract Product - Checkbox
interface Checkbox {
    void paint();
}

// Concrete Product - Windows Checkbox
class WindowsCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a checkbox in Windows style.");
    }
}

// Concrete Product - MacOS Checkbox
class MacOSCheckbox implements Checkbox {
    public void paint() {
        System.out.println("Rendering a checkbox in MacOS style.");
    }
}

// Client code
class Application {
    private UIFactory factory;

    public Application(UIFactory factory) {
        this.factory = factory;
    }

    public void createUI() {
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        button.paint();
        checkbox.paint();
    }
}

// Usage
public class AbstractFactoryMethodPattern {
    public static void main(String[] args) {
        UIFactory factory = new WindowsFactory(); // or new MacOSFactory()
        Application app = new Application(factory);
        app.createUI();
    }
}
