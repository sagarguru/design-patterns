package com.designpatterns.behavioral;

/*
The Template Method design pattern is a behavioral design pattern that defines the skeleton of an algorithm in a base class
 but lets subclasses override specific steps of the algorithm without changing its structure.

 Pros of Template Method Design Pattern
Code Reusability: Common code is centralized in the base class, reducing code duplication.
Framework Development: Template Method is useful in frameworks where the framework defines the sequence of steps, but clients can override specific steps.
Encapsulation: The overall structure of the algorithm is encapsulated within the base class, making it easier to manage and modify.
Ease of Extension: New algorithms or steps can be easily added by creating new subclasses without altering the existing codebase.
Cons of Template Method Design Pattern
Inflexibility: Once the template method is defined in the base class, the overall structure of the algorithm cannot be changed easily.
Overhead of Subclassing: Every variation of the algorithm requires creating a new subclass, which can lead to a proliferation of classes.
Dependency on Inheritance: Since the Template Method pattern relies on inheritance, it can be less flexible than using composition, particularly if the base class implementation needs to be changed.
Hard to Debug: Since the template method relies on the cooperation between the base class and its subclasses, debugging can be tricky if errors occur in the sequence of steps.
 */
// Abstract class with the template method
abstract class DataProcessor {

    // Template method
    public final void process() {
        loadData();
        processData();
        saveData();
    }

    // Steps to be implemented by subclasses
    protected abstract void loadData();
    protected abstract void processData();

    // Common step implemented in the base class
    protected void saveData() {
        System.out.println("Saving data to the database");
    }
}

// Concrete class implementing specific steps
class CSVDataProcessor extends DataProcessor {

    @Override
    protected void loadData() {
        System.out.println("Loading data from CSV file");
    }

    @Override
    protected void processData() {
        System.out.println("Processing CSV data");
    }
}

// Another concrete class implementing specific steps
class XMLDataProcessor extends DataProcessor {

    @Override
    protected void loadData() {
        System.out.println("Loading data from XML file");
    }

    @Override
    protected void processData() {
        System.out.println("Processing XML data");
    }
}

// Client code
public class TemplateMethodPattern {
    public static void main(String[] args) {
        DataProcessor csvProcessor = new CSVDataProcessor();
        csvProcessor.process();

        System.out.println();

        DataProcessor xmlProcessor = new XMLDataProcessor();
        xmlProcessor.process();
    }
}
