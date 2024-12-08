package com.example;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<Product> products = new ArrayList<>();

    // Додати товар до кошикаа
    public void addProduct(Product product) {
        products.add(product);
    }

    // Видалити товар з кошика
    public void removeProduct(Product product) {
        products.remove(product);
    }

    // Очищення кошика
    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "Кошик порожній.";
        }
        StringBuilder sb = new StringBuilder("Товари в кошику:\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        return sb.toString();
    }
}
