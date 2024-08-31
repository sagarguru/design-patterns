package com.designpatterns.structural;

/*
 provides a simplified interface to a complex system of classes, libraries, or frameworks.
 By encapsulating the complexity behind a single interface,
 the Facade Pattern makes a subsystem easier to use and understand for the client.
 Pros of the Facade Pattern:
Simplification: The Facade reduces complexity by providing a simpler interface to a complex system, making it easier for clients to use.

Decoupling: The Facade decouples the client from the subsystem, reducing the dependencies between them. This makes the system easier to maintain and modify.

Improved Readability: By abstracting away the complex interactions within a subsystem, the Facade makes the code more readable and maintainable.

Encapsulation: The Facade encapsulates the detailed operations of the subsystem, preventing the client from needing to understand the underlying complexity.

Flexibility: The Facade allows you to modify the subsystem without affecting the client code. You can extend or modify the subsystem behind the Facade without changing the client’s usage.

Cons of the Facade Pattern:
Potential Over-Simplification: The Facade might hide too much of the subsystem’s functionality, making it difficult to access specific features or capabilities that the client might need.

Limited Transparency: The Facade can obscure the underlying complexity, making it harder to debug issues or understand how the system actually works.

Increased Maintenance: If the underlying subsystems change frequently, the Facade might need to be updated regularly to reflect those changes, increasing maintenance efforts.

Performance Overhead: Depending on how the Facade is implemented, there could be a slight performance overhead due to the additional layer of abstraction.

Restricted Flexibility: While the Facade simplifies the interface, it can also limit the flexibility of the client by providing only a limited view of the subsystem’s functionality.
 */
public class FacadePattern {
    public static void main(String[] args) {
        // Creating subsystem objects
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();

        // Creating the facade
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvdPlayer, projector, soundSystem);

        // Using the facade to watch a movie
        homeTheater.watchMovie("Inception");

        // Using the facade to end the movie
        homeTheater.endMovie();
    }
}
class DVDPlayer {
    public void on() {
        System.out.println("DVD Player is ON");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    public void stop() {
        System.out.println("Stopping the DVD");
    }

    public void off() {
        System.out.println("DVD Player is OFF");
    }
}

class Projector {
    public void on() {
        System.out.println("Projector is ON");
    }

    public void wideScreenMode() {
        System.out.println("Projector is in widescreen mode");
    }

    public void off() {
        System.out.println("Projector is OFF");
    }
}

class SoundSystem {
    public void on() {
        System.out.println("Sound system is ON");
    }

    public void setVolume(int level) {
        System.out.println("Setting sound system volume to " + level);
    }

    public void off() {
        System.out.println("Sound system is OFF");
    }
}
class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;

    public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, SoundSystem soundSystem) {
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.soundSystem = soundSystem;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        dvdPlayer.on();
        dvdPlayer.play(movie);
        projector.on();
        projector.wideScreenMode();
        soundSystem.on();
        soundSystem.setVolume(10);
        System.out.println("Movie is now playing!");
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        dvdPlayer.stop();
        dvdPlayer.off();
        projector.off();
        soundSystem.off();
        System.out.println("Movie theater is off");
    }
}
