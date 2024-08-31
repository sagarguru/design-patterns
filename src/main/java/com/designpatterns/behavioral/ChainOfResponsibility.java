package com.designpatterns.behavioral;

/*
The Chain of Responsibility is a behavioral design pattern that allows you to pass a request along a chain of handlers.
 Each handler either processes the request or passes it to the next handler in the chain.
 This pattern is particularly useful when multiple objects can handle a request, and the handler is determined at runtime.
 Pros:
Decoupling of Senders and Receivers: The sender of a request doesn't need to know which object in the chain will handle the request, promoting loose coupling.
Flexibility in Assigning Responsibility: The chain can be composed dynamically, allowing you to add or remove handlers without affecting the client code.
Promotes Single Responsibility Principle: Each handler has a specific function and handles only one type of request, leading to cleaner, more maintainable code.
Cons:
No Guarantee of Handling: If none of the handlers can process the request, it might remain unhandled unless explicitly managed.
Debugging Complexity: It can be harder to debug as the request can pass through several handlers, making it difficult to track where the issue lies.
Performance Overhead: If the chain is long or poorly designed, it can lead to performance issues due to multiple passes through the chain.
 */
// 1. Handler Interface
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(SupportTicket ticket);
}

// 2. Concrete Handlers
class LevelOneSupport extends SupportHandler {
    public void handleRequest(SupportTicket ticket) {
        if (ticket.getSeverity().equals("Low")) {
            System.out.println("Level 1 support handling ticket: " + ticket.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(ticket);
        }
    }
}

class LevelTwoSupport extends SupportHandler {
    public void handleRequest(SupportTicket ticket) {
        if (ticket.getSeverity().equals("Medium")) {
            System.out.println("Level 2 support handling ticket: " + ticket.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(ticket);
        }
    }
}

class LevelThreeSupport extends SupportHandler {
    public void handleRequest(SupportTicket ticket) {
        if (ticket.getSeverity().equals("High")) {
            System.out.println("Level 3 support handling ticket: " + ticket.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(ticket);
        } else {
            System.out.println("No handler found for ticket: " + ticket.getDescription());
        }
    }
}

// 3. Support Ticket Class
class SupportTicket {
    private String severity;
    private String description;

    public SupportTicket(String severity, String description) {
        this.severity = severity;
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public String getDescription() {
        return description;
    }
}

// 4. Client
public class ChainOfResponsibility {
    public static void main(String[] args) {
        // Create the handlers
        SupportHandler l1Support = new LevelOneSupport();
        SupportHandler l2Support = new LevelTwoSupport();
        SupportHandler l3Support = new LevelThreeSupport();

        // Chain them together
        l1Support.setNextHandler(l2Support);
        l2Support.setNextHandler(l3Support);

        // Create and send some tickets
        SupportTicket lowSeverityTicket = new SupportTicket("Low", "Password reset");
        SupportTicket mediumSeverityTicket = new SupportTicket("Medium", "Software installation issue");
        SupportTicket highSeverityTicket = new SupportTicket("High", "Server is down");

        l1Support.handleRequest(lowSeverityTicket);
        l1Support.handleRequest(mediumSeverityTicket);
        l1Support.handleRequest(highSeverityTicket);
    }
}
