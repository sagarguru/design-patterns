package com.designpatterns.structural;
import java.util.HashMap;
import java.util.Map;

/*
The Flyweight pattern is a structural design pattern used to minimize memory usage or computational
expenses by sharing as much data as possible with similar objects.
This pattern is particularly useful when working with large numbers of objects that have some shared, immutable data.
Pros:
Reduced Memory Usage: The primary advantage of the Flyweight pattern is that it dramatically reduces the amount of memory needed to store large numbers of objects by sharing common data.
Improved Performance: Less memory usage can lead to performance improvements, especially in systems with limited resources.
Simplified Object Management: Since intrinsic state is shared, changes to shared data can be managed centrally, reducing the need to update multiple objects individually.
Cons:
Complexity: Implementing the Flyweight pattern can add complexity to the code, as you need to carefully separate intrinsic and extrinsic state.
Maintenance Challenges: Because of the shared nature of the intrinsic state, changes to this state can have wide-ranging effects, making the system harder to maintain and debug.
Potential for Memory Leaks: If not managed properly, the Flyweight pattern can lead to memory leaks, especially if objects that should be shared are accidentally duplicated.
Not Always Applicable: The Flyweight pattern is most beneficial when there are many similar objects, but in cases where objects have a lot of unique data, the pattern may not provide significant benefits.
 */
// Flyweight interface
interface Character {
    void display(CharacterContext context);
}
// Concrete Flyweight class
class ConcreteCharacter implements Character {
    private char symbol;  // Intrinsic state
    private String fontFamily; // Intrinsic state
    private int fontSize;  // Intrinsic state
    private String color;  // Intrinsic state

    public ConcreteCharacter(char symbol, String fontFamily, int fontSize, String color) {
        this.symbol = symbol;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.color = color;
    }

    @Override
    public void display(CharacterContext context) {
        System.out.println("Character: " + symbol +
                ", Font: " + fontFamily +
                ", Size: " + fontSize +
                ", Color: " + color +
                ", Position: (" + context.getX() + ", " + context.getY() + ")");
    }
}


class CharacterFactory {
    private Map<String, Character> characterPool = new HashMap<>();

    public Character getCharacter(char symbol, String fontFamily, int fontSize, String color) {
        String key = symbol + fontFamily + fontSize + color;
        Character character = characterPool.get(key);

        if (character == null) {
            character = new ConcreteCharacter(symbol, fontFamily, fontSize, color);
            characterPool.put(key, character);
        }

        return character;
    }
}
// Context class
class CharacterContext {
    private int x;  // Extrinsic state
    private int y;  // Extrinsic state

    public CharacterContext(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

public class FlyWeightPattern {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();

        Character characterA1 = factory.getCharacter('A', "Arial", 12, "Black");
        Character characterA2 = factory.getCharacter('A', "Arial", 12, "Black");
        Character characterB1 = factory.getCharacter('B', "Arial", 12, "Black");

        // Even though we are using multiple character objects, they share the same intrinsic state.
        characterA1.display(new CharacterContext(10, 10));  // Using extrinsic state
        characterA2.display(new CharacterContext(20, 10));  // Using extrinsic state
        characterB1.display(new CharacterContext(30, 10));  // Using extrinsic state

        // Verify that characterA1 and characterA2 are the same object
        System.out.println("characterA1 and characterA2 are the same object: " + (characterA1 == characterA2));
        System.out.println("characterA1 and characterB1 are the same object: " + (characterA1 == characterB1));
    }
}
