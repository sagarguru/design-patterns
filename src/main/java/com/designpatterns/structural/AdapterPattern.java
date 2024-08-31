package com.designpatterns.structural;

/*
allows two incompatible interfaces to work together. It's like a bridge between two objects.
The pattern involves a single class, called an adapter, which is responsible for joining functionalities of independent or incompatible interfaces.
 Pros of Adapter Pattern
Single Responsibility Principle: You can separate the interface or data conversion code from the business logic of the program.
Open/Closed Principle: You can introduce new adapters into the program without breaking the existing code.
Legacy Code Integration: Helps in reusing existing code, even if its interface is not compatible with the current system.
Cons of Adapter Pattern
Complexity: The adapter pattern can introduce additional layers of abstraction and increase complexity in the codebase.
Performance: Depending on the complexity of the adaptee and the adapter, this pattern can sometimes introduce performance overhead due to the added level of indirection.
 */
// Adaptee
class FrenchSpeaker {
    public void speakFrench(String message) {
        System.out.println("Speaking in French: " + message);
    }
}

// Target Interface
interface EnglishSpeaker {
    void speakEnglish(String message);
}

// Adapter
class Translator implements EnglishSpeaker {
    private FrenchSpeaker frenchSpeaker;

    public Translator(FrenchSpeaker frenchSpeaker) {
        this.frenchSpeaker = frenchSpeaker;
    }

    @Override
    public void speakEnglish(String message) {
        String frenchMessage = translateToFrench(message);
        frenchSpeaker.speakFrench(frenchMessage);
    }

    private String translateToFrench(String message) {
        // Simplified translation logic
        return message.replace("Hello", "Bonjour").replace("Thank you", "Merci");
    }
}

// Client
class EnglishClient {
    private EnglishSpeaker speaker;

    public EnglishClient(EnglishSpeaker speaker) {
        this.speaker = speaker;
    }

    public void express(String message) {
        speaker.speakEnglish(message);
    }
}

// Main class to demonstrate the adapter pattern
public class AdapterPattern {
    public static void main(String[] args) {
        FrenchSpeaker frenchSpeaker = new FrenchSpeaker();
        Translator translator = new Translator(frenchSpeaker);
        EnglishClient client = new EnglishClient(translator);

        client.express("Hello! Thank you for the meeting.");
    }
}

