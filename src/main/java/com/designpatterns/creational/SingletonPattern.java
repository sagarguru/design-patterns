package com.designpatterns.creational;

/*
 Singleton Pattern is a design pattern in Java (and other object-oriented languages)
 that restricts the instantiation of a class to a single instance.
 This is useful when exactly one object is needed to coordinate actions across the system.
 Pros and Cons:
Pros:
    Controlled access to the single instance.
    Reduced memory footprint.
    Can be extended to support lazy initialization and thread safety.
Cons:
    Violates the Single Responsibility Principle since the class has two responsibilities:
        managing its instance and its business logic.
    Difficult to unit test due to the global state.
    Can lead to tight coupling and reduced flexibility in some designs.
 */

public class SingletonPattern {

    private static final SingletonPattern instance = new SingletonPattern();

    private SingletonPattern() {}

    public static synchronized SingletonPattern getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton Pattern!");
    }
}
class Solution{
    public static void main(String[] args) {
        SingletonPattern object = SingletonPattern.getInstance();
        object.showMessage();
    }
}
