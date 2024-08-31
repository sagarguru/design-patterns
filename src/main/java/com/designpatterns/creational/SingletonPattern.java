package com.designpatterns.creational;

public class SingletonPattern {

    private static final SingletonPattern instance = new SingletonPattern();

    private SingletonPattern() {}

    public static SingletonPattern getInstance() {
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
