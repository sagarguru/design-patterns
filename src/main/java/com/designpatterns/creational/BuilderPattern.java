package com.designpatterns.creational;

/*
to construct complex objects step by step. Unlike other creational patterns, which build objects in a single step,
 the Builder pattern allows for more flexibility by providing a way to create a complex object
 through a sequence of method calls.
 Pros of the Builder Pattern
Improves Readability: The code to create an object is more readable, especially when dealing with objects that have many attributes, some of which are optional.

Immutable Objects: By using the Builder pattern, you can create immutable objects, where all attributes are set during construction, and no setters are provided.

Flexibility: The Builder pattern allows for flexible construction of objects by enabling optional attributes and multiple variations of an object.

Encapsulation: It encapsulates the construction process and isolates the complex logic of object creation from the client code.

Chaining Method Calls: The Builder pattern often supports chaining method calls, which makes it easy to set up the object's state in a concise manner.

Cons of the Builder Pattern
More Code: Implementing the Builder pattern requires more code, especially for simple objects, due to the additional Builder class and methods.

Complexity: The pattern adds complexity to the codebase, which might not be necessary if the object to be constructed is simple or has a few attributes.

Memory Overhead: The Builder pattern might consume more memory because it often requires holding a temporary builder object in addition to the final object.

Overkill for Simple Objects: If the object is simple or does not have optional attributes, the Builder pattern might be overkill compared to a simple constructor.
 */
public class BuilderPattern {

    public static void main(String[] args) {
        House house = new House.Builder(4, 2)
                .roof("shingles")
                .garage(true)
                .build();
    }
}
class House {
    // All possible attributes
    private final int windows;
    private final int doors;
    private final String roof;
    private final boolean hasGarage;

    // Private constructor so that only the builder can create an instance
    private House(Builder builder) {
        this.windows = builder.windows;
        this.doors = builder.doors;
        this.roof = builder.roof;
        this.hasGarage = builder.hasGarage;
    }

    // Getters for the attributes can be added here

    // Static nested Builder class
    public static class Builder {
        // Required attributes
        private int windows;
        private int doors;

        // Optional attributes with default values
        private String roof = "standard";
        private boolean hasGarage = false;

        // Constructor with required attributes
        public Builder(int windows, int doors) {
            this.windows = windows;
            this.doors = doors;
        }

        // Methods to set optional attributes
        public Builder roof(String roof) {
            this.roof = roof;
            return this;
        }

        public Builder garage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        // Build method that returns the constructed object
        public House build() {
            return new House(this);
        }
    }
}