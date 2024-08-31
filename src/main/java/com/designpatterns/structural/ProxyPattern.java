package com.designpatterns.structural;

/*
The Proxy Pattern is a structural design pattern that provides an object representing another object.
It is used when we want to control access to an object by creating a placeholder, or a "proxy," which manages
the interaction with the actual object.
Pros:
Controlled Access: The proxy can control access to the real object, allowing for added security or functionality.
Lazy Initialization: The real object is only created when it's actually needed, saving resources.
Separation of Concerns: By using proxies, we can separate the responsibility of the object's usage and its creation.
Logging/Monitoring: Proxies can easily be used to add logging, caching, or other cross-cutting concerns without modifying the real object.
Cons:
Increased Complexity: Introducing a proxy adds additional classes and layers to the system, making it more complex.
Potential Performance Overhead: Depending on the implementation, proxies can add overhead, especially if they involve remote communication.
Maintenance Overhead: Maintaining proxy classes along with the real objects can be tedious, especially when changes are required.
Can Obscure Functionality: The use of proxies can make the codebase harder to understand as it may not be clear whether you are interacting with the real object or a proxy.
 */
interface Image {
    void display();
}
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

public class ProxyPattern {
    public static void main(String[] args) {
        Image image = new ProxyImage("test_image.jpg");

        image.display();
        System.out.println("");

        image.display();
    }
}
