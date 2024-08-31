package com.designpatterns.creational;

/*
Create new objects by copying an existing object, known as the prototype.
Instead of instantiating a new object directly, you clone the prototype.

Pros of the Prototype Pattern:
Reduces Initialization Overhead: Cloning an existing object can be more efficient than creating a new one from scratch, especially if the creation process is resource-intensive.

Simplifies Object Creation: The Prototype pattern abstracts the creation process, making it easier to instantiate complex objects with specific configurations.

Avoids Subclassing: The pattern can reduce the need to subclass objects just to create new instances with specific attributes, as you can clone and modify an existing prototype.

Dynamic Object Creation: Allows for creating objects dynamically at runtime without having to know their exact type in advance. This is particularly useful in scenarios where the system needs to generate new types of objects on the fly.

Cons of the Prototype Pattern:
Cloning Complexity: If the object being cloned has complex structures (e.g., nested objects), deep cloning can be difficult to implement and manage. Mistakes can lead to unintended sharing of references (shallow copies).

Maintenance Overhead: Implementing cloning logic in a class requires extra code, which can lead to increased maintenance and potential bugs if not done correctly.

Limited Use Cases: The Prototype pattern is not as widely applicable as other creational patterns like Factory Method or Abstract Factory. It’s most useful in specific scenarios where objects are expensive to create or require frequent cloning.

Potential for Overuse: If not used judiciously, it might complicate the design unnecessarily. Some cases might be better served by using simpler approaches, such as constructors or factories.
 */
public class PrototypePattern {

    public static void main(String[] args) {
        // Create an original shape object
        PShape originalShape = new PShape("Circle");

        // Clone the original shape
        PShape clonedShape = originalShape.clone();

        // Display the cloned object
        System.out.println("Original Shape: " + originalShape);
        System.out.println("Cloned Shape: " + clonedShape);
    }
}
interface Prototype {
    Prototype clone();
}
class PShape implements Prototype {
    private String type;

    public PShape(String type) {
        this.type = type;
    }

    @Override
    public PShape clone() {
        return new PShape(this.type);
    }

    @Override
    public String toString() {
        return "Shape of type: " + type;
    }
}
