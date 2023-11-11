import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Product class hierarchy
abstract class Product implements Cloneable {
    String name;
    double price;
    boolean available;

    public Product(String name, double price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    abstract void display();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Laptop extends Product {
    public Laptop(String name, double price, boolean available) {
        super(name, price, available);
    }

    @Override
    void display() {
        System.out.println(name + ": $" + price + " (Available: " + available + ")");
    }
}

class Headphones extends Product {
    public Headphones(String name, double price, boolean available) {
        super(name, price, available);
    }

    @Override
    void display() {
        System.out.println(name + ": $" + price + " (Available: " + available + ")");
    }
}

// Strategy Pattern for discount strategies
interface DiscountStrategy {
    double applyDiscount(double price);
}

class PercentageOff implements DiscountStrategy {
    private double percentage;

    public PercentageOff(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double price) {
        return price - (price * percentage / 100);
    }
}

class BuyOneGetOneFree implements DiscountStrategy {
    @Override
    public double applyDiscount(double price) {
        return price / 2;
    }
}

// Prototype Pattern for cloning products
class ProductPrototype {
    private Product product;

    public ProductPrototype(Product product) {
        this.product = product;
    }

    public Product cloneProduct() {
        try {
            return (Product) product.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Cart class
class ShoppingCart {
    private List<Product> cart;
    private DiscountStrategy discountStrategy;

    public ShoppingCart(DiscountStrategy discountStrategy) {
        this.cart = new ArrayList<>();
        this.discountStrategy = discountStrategy;
    }

    public void addToCart(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            Product clonedProduct = new ProductPrototype(product).cloneProduct();
            cart.add(clonedProduct);
        }
    }

    public void removeFromCart(String productName) {
        cart.removeIf(item -> item.name.equals(productName));
    }

    public void updateQuantity(String productName, int quantity) {
        for (Product item : cart) {
            if (item.name.equals(productName)) {
                item.available = false; // Assuming unavailable after updating quantity
            }
        }
        addToCart(getProductByName(productName), quantity);
    }

    public Product getProductByName(String productName) {
        // In a real system, this should fetch the product from a database or a product catalog
        if ("Laptop".equals(productName)) {
            return new Laptop(productName, 1000, true);
        } else if ("Headphones".equals(productName)) {
            return new Headphones(productName, 50, true);
        }
        return null;
    }
    

    public void displayCart() {
        Map<String, Integer> itemCount = new HashMap<>();
        for (Product item : cart) {
            itemCount.put(item.name, itemCount.getOrDefault(item.name, 0) + 1);
        }

        StringBuilder displayItems = new StringBuilder("Cart Items: You have ");
        for (Map.Entry<String, Integer> entry : itemCount.entrySet()) {
            displayItems.append(entry.getValue()).append(" ").append(entry.getKey()).append(", ");
        }

        System.out.println(displayItems.substring(0, displayItems.length() - 2) + " in your cart.");
    }

    public void calculateTotalBill() {
        double total = cart.stream().mapToDouble(item -> item.price).sum();
        double discountedPrice = discountStrategy.applyDiscount(total);
        System.out.println("Total Bill: Your total bill is $" + discountedPrice + ".");
    }
}

// Example usage
public class Ecommerce {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	Laptop laptop = new Laptop("Laptop", 1000, true);
        Headphones headphones = new Headphones("Headphones", 50, true);

        List<Product> products = List.of(laptop, headphones);

        DiscountStrategy discountStrategy = new PercentageOff(5); // 5% off
        ShoppingCart cart = new ShoppingCart(discountStrategy);

        while (true) {
            System.out.println("Options:");
            System.out.println("1. Display Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. Display Cart");
            System.out.println("4. Calculate Total Bill");
            System.out.println("5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Available Products:");
                    for (Product product : products) {
                        product.display();
                    }
                    break;
                case 2:
                    System.out.print("Enter the product name to add to the cart: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character
                    Product selectedProduct = cart.getProductByName(productName);
                    if (selectedProduct != null) {
                        cart.addToCart(selectedProduct, quantity);
                        System.out.println(quantity + " " + productName + " added to the cart.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    cart.calculateTotalBill();
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
  
    }
}
