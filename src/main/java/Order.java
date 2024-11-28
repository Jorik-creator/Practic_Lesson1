import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private static List<Order> orderHistory = new ArrayList<>();
    private List<Product> products;

    // Конструктор створення замовлення на основі кошикаа
    public Order(Cart cart) {
        this.products = new ArrayList<>(cart.getProducts());
        orderHistory.add(this);
    }

    // Виведення історії замовлень
    public static List<Order> displayOrderHistory() {
        return orderHistory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Замовлення:\n");
        for (Product product : products) {
            sb.append(product).append("\n");
        }
        return sb.toString();
    }
}
