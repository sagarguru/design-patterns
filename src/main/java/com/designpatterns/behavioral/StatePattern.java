package com.designpatterns.behavioral;

/*
The State design pattern is a behavioral design pattern that allows an object to change its behavior when its internal state changes.
 The pattern is often used in scenarios where an object can exist in different states, and the behavior of the object varies depending on its current state.
 Pros:

Simplifies Code: State-specific behavior is encapsulated in individual state classes, making the code easier to understand and maintain.
Open/Closed Principle: New states can be added without modifying existing code, adhering to the Open/Closed Principle.
Improved Maintainability: Changes in state-specific logic can be made independently of other states, improving code maintainability.
Decouples Context from State: The context object delegates state-specific behavior to the state objects, reducing coupling between the context and the individual behaviors.
Cons:

Increased Number of Classes: Each state requires its own class, which can lead to a proliferation of classes, especially in systems with many states.
Complexity: For simple state machines, the pattern may introduce unnecessary complexity by requiring the developer to create multiple state classes and manage their transitions.
Tight Coupling Between States: Even though the context is decoupled from the state, the state classes can be tightly coupled if they need to communicate with each other, which may violate the Single Responsibility Principle.
 */
// State interface
interface State {
    void play(MediaPlayer context);
    void pause(MediaPlayer context);
    void stop(MediaPlayer context);
}

// Concrete State: Playing
class PlayingState implements State {
    @Override
    public void play(MediaPlayer context) {
        System.out.println("Already playing.");
    }

    @Override
    public void pause(MediaPlayer context) {
        System.out.println("Pausing playback.");
        context.setState(new PausedState());
    }

    @Override
    public void stop(MediaPlayer context) {
        System.out.println("Stopping playback.");
        context.setState(new StoppedState());
    }
}

// Concrete State: Paused
class PausedState implements State {
    @Override
    public void play(MediaPlayer context) {
        System.out.println("Resuming playback.");
        context.setState(new PlayingState());
    }

    @Override
    public void pause(MediaPlayer context) {
        System.out.println("Already paused.");
    }

    @Override
    public void stop(MediaPlayer context) {
        System.out.println("Stopping playback.");
        context.setState(new StoppedState());
    }
}

// Concrete State: Stopped
class StoppedState implements State {
    @Override
    public void play(MediaPlayer context) {
        System.out.println("Starting playback.");
        context.setState(new PlayingState());
    }

    @Override
    public void pause(MediaPlayer context) {
        System.out.println("Cannot pause. Media is already stopped.");
    }

    @Override
    public void stop(MediaPlayer context) {
        System.out.println("Already stopped.");
    }
}

// Context
class MediaPlayer {
    private State state;

    public MediaPlayer() {
        // Initial state
        this.state = new StoppedState();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void play() {
        state.play(this);
    }

    public void pause() {
        state.pause(this);
    }

    public void stop() {
        state.stop(this);
    }
}

// Client
public class StatePattern {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer();

        player.play();   // Output: Starting playback.
        player.pause();  // Output: Pausing playback.
        player.play();   // Output: Resuming playback.
        player.stop();   // Output: Stopping playback.
    }
}
