package com.designpatterns.behavioral;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*
The Iterator design pattern is a behavioral design pattern that provides a way to access the elements of an aggregate object
(such as a collection) sequentially without exposing its underlying representation.
This pattern is useful when you need to traverse through a collection without exposing its internal structure.
Pros:

Single Responsibility Principle: Iterator pattern allows the collection classes to focus on storing data, while iterators are concerned with traversal.
Encapsulation: The internal structure of the collection is hidden from the client.
Flexible Traversal: Different iterators can be implemented to traverse the same collection in various ways (e.g., forward, backward).
Polymorphism: Iterators provide a uniform interface for traversing different types of collections.
Cons:

Additional Complexity: Adding an iterator can increase the complexity of the code, especially if the traversal logic is simple.
Overhead: For small collections, the overhead of creating and using an iterator might be unnecessary.
Single Traversal: Basic iterators typically support only a single traversal at a time, which can be a limitation in some scenarios.
 */
// Iterator interface
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// Concrete Iterator
class ConcreteIterator<T> implements Iterator<T> {
    private final List<T> collection;
    private int position = 0;

    public ConcreteIterator(List<T> collection) {
        this.collection = collection;
    }

    @Override
    public boolean hasNext() {
        return position < collection.size();
    }

    @Override
    public T next() {
        if (this.hasNext()) {
            return collection.get(position++);
        }
        throw new NoSuchElementException("No more elements in the collection");
    }
}

// Aggregate interface
interface Aggregate<T> {
    Iterator<T> createIterator();
}

// Concrete Aggregate
class ConcreteAggregate<T> implements Aggregate<T> {
    private final List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public Iterator<T> createIterator() {
        return new ConcreteIterator<>(items);
    }
}

// Client code
public class IteratorPattern {
    public static void main(String[] args) {
        ConcreteAggregate<String> collection = new ConcreteAggregate<>();
        collection.addItem("Item 1");
        collection.addItem("Item 2");
        collection.addItem("Item 3");

        Iterator<String> iterator = collection.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
