# E-commerce Cart System

This is a simple E-commerce cart system implemented in Java.

## Features

- Display a list of products with their attributes.
- Allow users to add products to the cart.
- Implement cart functionality where users can view, update quantities, and remove items.
- Calculate and display the total bill.
- Utilizes the Strategy Pattern for discount strategies, Prototype Pattern for cloning product objects, and emphasizes OOP principles.

## Usage

1. Compile the Java code:

    ```bash
    javac Main.java
    ```

2. Run the Java program:

    ```bash
    java Main
    ```

3. Follow the on-screen instructions to interact with the E-commerce cart system.

## Code Structure

- `Main.java`: Main entry point for the program.
- `Product.java`: Abstract base class for products.
- `Laptop.java`, `Headphones.java`: Concrete subclasses for specific product types.
- `DiscountStrategy.java`, `PercentageOff.java`, `BuyOneGetOneFree.java`: Strategy Pattern for discount strategies.
- `ProductPrototype.java`: Prototype Pattern for cloning product objects.
- `ShoppingCart.java`: Cart class that handles adding, removing, and displaying items.

## Evaluation Criteria

The code is designed to fulfill the following criteria:

1. **Code Quality:** The code adheres to OOP principles, design patterns, and best practices.
2. **Functionality:** The E-commerce cart system fulfills all functional requirements and edge cases.
3. **Global Convention:** The code follows global conventions, making it easy to understand and maintain.
4. **Gold Standards:** The code includes logging, error handling, and considerations for performance.
5. **Code Walkthrough:** The developer should be able to walk through the code and explain the architectural decisions and patterns used.
