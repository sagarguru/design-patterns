package com.designpatterns.structural;
import java.util.ArrayList;
import java.util.List;

/*
The Composite Pattern is a structural design pattern that allows you to compose objects into tree structures to represent part-whole hierarchies.
This pattern treats individual objects and compositions of objects uniformly, meaning you can handle both single objects and composites
(objects that contain other objects) in the same way.
Pros
Simplifies Client Code: Clients can treat individual objects and compositions of objects uniformly. This means client code can be simpler, not worrying about whether it is dealing with a leaf or a composite.

Flexible Hierarchy: The Composite pattern makes it easy to add new kinds of components. You can add new Leaf or Composite classes without changing the existing code.

Hierarchical Structures: This pattern naturally represents tree structures, which is common in real-world scenarios like GUI components, file systems, etc.

Encapsulation: It encapsulates the structure and the behavior of composite objects.

Cons
Overhead: Introducing a Composite structure can add complexity to the system, especially if the system doesn’t inherently require a tree structure.

Inappropriate Design for Simple Structures: If the hierarchy is simple and unlikely to change, the Composite pattern might be overkill and unnecessarily complicate the design.

Difficulties with Type Safety: Because the components in a Composite structure are treated uniformly, enforcing type constraints can be challenging, leading to potential run-time errors.

Potential for Unintended Consequences: Operations that modify the structure (like adding or removing children) might have unintended side effects, especially in large and complex structures.
 */
// Component interface
interface Graphic {
    void draw();
}

// Leaf class - Line
class Line implements Graphic {
    @Override
    public void draw() {
        System.out.println("Drawing a Line");
    }
}

// Leaf class - Rectangle
class Rectangle implements Graphic {
    @Override
    public void draw() {
        System.out.println("Drawing a Rectangle");
    }
}

// Composite class - Picture
class Picture implements Graphic {
    private List<Graphic> children = new ArrayList<>();

    @Override
    public void draw() {
        for (Graphic graphic : children) {
            graphic.draw();
        }
    }

    public void add(Graphic graphic) {
        children.add(graphic);
    }

    public void remove(Graphic graphic) {
        children.remove(graphic);
    }
}

// Client code
public class CompositePattern {
    public static void main(String[] args) {
        // Create leaf objects
        Graphic line1 = new Line();
        Graphic line2 = new Line();
        Graphic rectangle1 = new Rectangle();

        // Create a composite object
        Picture picture = new Picture();

        // Add leaf objects to the composite
        picture.add(line1);
        picture.add(rectangle1);
        picture.add(line2);

        // Draw the composite object
        System.out.println("Drawing the Picture:");
        picture.draw();

        // Create another composite to demonstrate nesting
        Picture nestedPicture = new Picture();
        nestedPicture.add(new Line());
        nestedPicture.add(picture);

        System.out.println("\nDrawing the Nested Picture:");
        nestedPicture.draw();
    }
}
