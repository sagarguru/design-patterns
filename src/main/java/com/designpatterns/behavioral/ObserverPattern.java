package com.designpatterns.behavioral;
import java.util.ArrayList;
import java.util.List;

/*
The Observer Design Pattern is a behavioral design pattern that defines a one-to-many dependency between objects.
When one object (the Subject) changes its state, all its dependents (the Observers) are notified and updated automatically.
This pattern is particularly useful in scenarios where changes to an object's state need to be reflected across multiple other objects without tightly coupling them.
Pros of Observer Design Pattern:
Loose Coupling: The subject and observers are loosely coupled. The subject knows only that it has a list of observers, without needing to know any details about those observers.
Flexibility: Observers can be added or removed dynamically without modifying the subject.
Reusability: You can reuse observers across different subjects or in different contexts.
Consistency: All observers are automatically updated when the subject's state changes, ensuring consistency across different parts of the system.
Cons of Observer Design Pattern:
Memory Leaks: If observers are not properly detached from the subject, they can lead to memory leaks.
Complexity: For systems with a large number of observers, managing and debugging can become complex.
Unexpected Updates: Observers may receive updates that they do not necessarily need or want, leading to potential performance issues or unnecessary processing.
Order of Notifications: The order in which observers are notified is not guaranteed, which might lead to race conditions or inconsistent states in some cases.
 */
// Observer interface
interface Observer {
    void update(String message);
}

// ConcreteObserver
class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

// Subject interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// ConcreteSubject
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String message;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}

// Client code
public class ObserverPattern {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        Observer observer1 = new ConcreteObserver("Observer 1");
        Observer observer2 = new ConcreteObserver("Observer 2");

        subject.attach(observer1);
        subject.attach(observer2);

        subject.setMessage("Hello Observers!");

        subject.detach(observer1);

        subject.setMessage("Another update!");
    }
}
