package com.designpatterns.behavioral;

/*
encapsulates how a set of objects interact with each other. The idea is to reduce the complexity and dependencies between objects by
having them communicate through a mediator object rather than directly with each other.
Pros:

Reduced Coupling: Mediator reduces the direct dependencies between colleague objects, leading to a more decoupled and manageable system.
Centralized Control: All communication is managed by the mediator, making it easier to understand and maintain.
Improved Flexibility: New interactions can be added without modifying existing classes by simply adding new methods to the mediator.
Cons:

Complexity: The mediator can become complex as more colleague objects are added, leading to a large and complex mediator class.
Single Point of Failure: If the mediator fails or becomes a bottleneck, it can affect the entire system.
Overhead: The mediator may introduce unnecessary overhead, especially if the system only has simple interactions between objects.
 */
// Mediator Interface
interface ChatMediator {
    void showMessage(User user, String message);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    @Override
    public void showMessage(User user, String message) {
        System.out.println(user.getName() + ": " + message);
    }
}

// Colleague Class
class User {
    private String name;
    private ChatMediator mediator;

    public User(String name, ChatMediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        mediator.showMessage(this, message);
    }
}

// Client Code
public class MediatorPattern {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatRoom();

        User user1 = new User("Alice", chatMediator);
        User user2 = new User("Bob", chatMediator);
        User user3 = new User("Charlie", chatMediator);

        user1.sendMessage("Hello, Bob!");
        user2.sendMessage("Hey, Alice!");
        user3.sendMessage("Hi everyone!");
    }
}
