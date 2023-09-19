package ca.ulaval.glo4002.mockexercise;

public class Product {
    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public double getPrice() {
        return price;
    }

    private final String sku;
    private final String name;
    private final double price;

    public Product(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }
}
