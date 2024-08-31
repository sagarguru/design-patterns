package com.designpatterns.behavioral;


import java.util.Stack;

/*
The Memento design pattern is a behavioral design pattern that allows you to capture and externalize an object’s internal state
so that the object can be restored to this state later without violating encapsulation.
This pattern is particularly useful when you need to implement features like undo or rollback, where it's essential
 to revert an object to a previous state.
 Pros:

Encapsulation preserved: The internal state of the object is not exposed, preserving encapsulation.
Simplifies undo functionality: It is easy to implement undo operations, which can be critical in applications such as text editors, graphics editors, etc.
State management: Allows for easy management and restoration of different states of an object.
Cons:

Memory consumption: If the memento stores large amounts of data or there are many mementos, this can lead to high memory usage.
Complexity: Managing the lifecycle of mementos can be complex, particularly in systems where the originator's state changes frequently.
No direct control over mementos: The caretaker is not supposed to modify the memento. This can be restrictive in some scenarios where partial state restoration is desired.
 */
// Memento Class
class EditorMemento {
    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Originator Class
class Editor {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public EditorMemento save() {
        return new EditorMemento(content);
    }

    public void restore(EditorMemento memento) {
        content = memento.getContent();
    }
}

// Caretaker Class
class EditorHistory {
    private Stack<EditorMemento> history = new Stack<>();

    public void save(Editor editor) {
        history.push(editor.save());
    }

    public void undo(Editor editor) {
        if (!history.isEmpty()) {
            editor.restore(history.pop());
        }
    }
}

public class MementoPattern {
    public static void main(String[] args) {
        Editor editor = new Editor();
        EditorHistory history = new EditorHistory();

        editor.setContent("Version 1");
        history.save(editor);

        editor.setContent("Version 2");
        history.save(editor);

        editor.setContent("Version 3");

        System.out.println("Current Content: " + editor.getContent());
        history.undo(editor);
        System.out.println("After undo: " + editor.getContent());
        history.undo(editor);
        System.out.println("After second undo: " + editor.getContent());
    }
}
