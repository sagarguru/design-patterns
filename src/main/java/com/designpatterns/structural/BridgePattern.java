package com.designpatterns.structural;

/*
decouples an abstraction from its implementation so that the two can vary independently.
 This is particularly useful when both the abstraction and the implementation could have
 multiple variants, and you need to avoid a complex inheritance hierarchy.
 Pros
Decoupling Abstraction and Implementation: This allows you to change or extend the abstraction and the implementation independently.
Increased Flexibility: You can easily introduce new abstractions and implementors without modifying existing code.
Improved Maintainability: The separation of concerns leads to cleaner, more maintainable code.
Enhanced Scalability: It’s easy to scale your system by adding new abstractions and implementors.
Cons
Increased Complexity: The Bridge Pattern introduces additional layers and interfaces, which can make the design more complex.
Overhead: Sometimes, the separation of abstraction and implementation may introduce unnecessary indirection, leading to performance overhead.
Can Be Over-Engineering: For simpler systems, this pattern might be overkill and could make the design unnecessarily complicated.
 */
// Implementor interface
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
}

// Concrete Implementor 1
class TV implements Device {
    @Override
    public void turnOn() {
        System.out.println("Turning on the TV");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the TV");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("Setting TV volume to " + volume);
    }
}

// Concrete Implementor 2
class Radio implements Device {
    @Override
    public void turnOn() {
        System.out.println("Turning on the Radio");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the Radio");
    }

    @Override
    public void setVolume(int volume) {
        System.out.println("Setting Radio volume to " + volume);
    }
}

// Abstraction
abstract class RemoteControl {
    protected Device device;

    protected RemoteControl(Device device) {
        this.device = device;
    }

    abstract void turnOn();
    abstract void turnOff();
    abstract void setVolume(int volume);
}

// Refined Abstraction
class AdvancedRemoteControl extends RemoteControl {

    protected AdvancedRemoteControl(Device device) {
        super(device);
    }

    @Override
    public void turnOn() {
        device.turnOn();
    }

    @Override
    public void turnOff() {
        device.turnOff();
    }

    @Override
    public void setVolume(int volume) {
        device.setVolume(volume);
    }

    public void mute() {
        System.out.println("Muting the device");
        device.setVolume(0);
    }
}

// Client code
public class BridgePattern {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remoteControl = new AdvancedRemoteControl(tv);

        remoteControl.turnOn();
        remoteControl.setVolume(10);
        remoteControl.turnOff();

        Device radio = new Radio();
        RemoteControl radioRemote = new AdvancedRemoteControl(radio);

        radioRemote.turnOn();
        radioRemote.setVolume(5);
        radioRemote.turnOff();
    }
}
