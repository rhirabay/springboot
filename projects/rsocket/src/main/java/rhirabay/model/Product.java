package rhirabay.model;

import lombok.Value;

@Value
public class Product {
    private String name;
    private int amount;

    public static Product of(String name, int amount) {
        return new Product(name, amount);
    }
}
