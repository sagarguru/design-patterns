package com.designpatterns.behavioral;

/*

The Command design pattern is a behavioral design pattern that turns a request into a stand-alone object
that contains all information about the request. This transformation
lets you parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.
Pros:
Decoupling: The Command pattern decouples the objects that invoke the operation from the objects that actually perform the operation. This makes the code more flexible and easier to extend or modify.
Extensibility: Adding new commands is easy and doesn't require changes to existing classes. You can create new command classes that implement the Command interface.
Support for Undo/Redo: The Command pattern makes it easier to implement features like undo/redo. By keeping a history of executed commands, you can easily reverse operations.
Simplified Client: The client code becomes simpler as it only deals with the invoker and commands without needing to know the details of how requests are processed.
Cons:
Increased Complexity: The Command pattern introduces additional classes and objects into the code, which can increase complexity, especially for simple operations.
Overhead: For simple commands that do not benefit from decoupling, the Command pattern might introduce unnecessary overhead.
Harder to Maintain: The proliferation of command classes can make the codebase harder to maintain, especially if there are many commands with similar behavior.
 */
// Command Interface
interface Command {
    void execute();
}

// Receiver Class
class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}

// Concrete Command for turning on the light
class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// Concrete Command for turning off the light
class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// Invoker Class
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Client Class
public class CommandPattern {
    public static void main(String[] args) {
        // Receiver
        Light livingRoomLight = new Light();

        // Concrete Commands
        Command turnOn = new TurnOnLightCommand(livingRoomLight);
        Command turnOff = new TurnOffLightCommand(livingRoomLight);

        // Invoker
        RemoteControl remote = new RemoteControl();

        // Turn on the light
        remote.setCommand(turnOn);
        remote.pressButton();

        // Turn off the light
        remote.setCommand(turnOff);
        remote.pressButton();
    }
}
