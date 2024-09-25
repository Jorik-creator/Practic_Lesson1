import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Створення категорій
        Category electronics = new Category(1, "Електроніка");
        Category smartphones = new Category(2, "Смартфони");
        Category accessories = new Category(3, "Аксесуари");

        // Список категорій
        List<Category> categories = new ArrayList<>();
        categories.add(electronics);
        categories.add(smartphones);
        categories.add(accessories);

        // Створення товарів
        Product product1 = new Product(1, "Ноутбук", 19999.99, "Високопродуктивний ноутбук для роботи та ігор", electronics);
        Product product2 = new Product(2, "Комп'ютер", 30000.45, "Високопродуктивний комп'ютер для роботи та ігор", electronics);
        Product product3 = new Product(3, "Смартфон", 12999.50, "Смартфон з великим екраном", smartphones);
        Product product4 = new Product(4, "Розкладушка", 1299.50, "Розкладний, бюжетний смартфон", smartphones);
        Product product5 = new Product(5, "Навушники", 2499.00, "Бездротові навушники з шумозаглушенням", accessories);
        Product product6 = new Product(6, "Миша", 2899.00, "Бездротова миша", accessories);

        // Список товарівd
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);

        Cart cart = new Cart();

        while (true) {
            System.out.println("\nВиберіть опцію:");
            System.out.println("1 - Переглянути список товарів");
            System.out.println("2 - Пошук товару за назвою");
            System.out.println("3 - Пошук товару за категорією");
            System.out.println("4 - Додати товар до кошика");
            System.out.println("5 - Видалення товару з кошика");
            System.out.println("6 - Переглянути кошик");
            System.out.println("7 - Зробити замовлення");
            System.out.println("8 - Історія замовлень");
            System.out.println("0 - Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case 2: // Пошук товару за назвою
                    System.out.println("Введіть назву товару для пошуку:");
                    String productName = scanner.nextLine();
                    Product foundProduct = Product.searchByName(products, productName);
                    if (foundProduct != null) {
                        System.out.println("Знайдено товар: " + foundProduct);
                    } else {
                        System.out.println("Товар з такою назвою не знайдено.");
                    }
                    break;

                case 3: // Пошук товару за категорією
                    System.out.println("Введіть назву категорії для пошуку:");
                    String categoryName = scanner.nextLine();
                    Category foundCategory = Category.searchCategoryByName(categories, categoryName);
                    if (foundCategory != null) {
                        System.out.println("Категорія знайдена: " + foundCategory.getName());
                        System.out.println("Товари в категорії:");
                        for (Product product : products) {
                            if (product.getCategory().getId() == foundCategory.getId()) {
                                System.out.println(product);
                            }
                        }
                    } else {
                        System.out.println("Категорію не знайдено.");
                    }
                    break;

                case 4:
                    System.out.println("Введіть ID товару для додавання до кошика:");
                    int idToAdd = scanner.nextInt();
                    // Пошук товару за ID
                    Product productToAdd = Product.findProductById(products, idToAdd);
                    if (productToAdd != null) {
                        cart.addProduct(productToAdd);
                        System.out.println("Товар '" + productToAdd.getName() + "' додано до кошика.");
                    } else {
                        System.out.println("Товар з таким ID не знайдено.");
                    }
                    break;

                case 5:
                    System.out.println("Введіть ID товару для видалення з кошика:");
                    int idToRemove = scanner.nextInt();
                    // Пошук товару за ID в кошику
                    Product productToRemove = Product.findProductById(cart.getProducts(), idToRemove);
                    if (productToRemove != null) {
                        cart.removeProduct(productToRemove);
                        System.out.println("Товар '" + productToRemove.getName() + "' видалено з кошика.");
                    } else {
                        System.out.println("Товар з таким ID не знайдено в кошику.");
                    }
                    break;

                case 6:
                    System.out.println(cart);
                    break;
                case 7:
                    if (cart.getProducts().isEmpty()) {
                        System.out.println("Кошик порожній. Додайте товари перед оформленням замовлення.");
                    } else {
                        Order order = new Order(cart);
                        System.out.println("Замовлення оформлено:");
                        System.out.println(order);
                        cart.clear();
                    }
                    break;
                case 8  :
                    for (Order order : Order.displayOrderHistory()) { // Виводимо історію замовлень
                        System.out.println(order);
                    }
                    break;
                case 0:
                    System.out.println("Дякуємо, що використовували наш магазин!");
                    return;
                default:
                    System.out.println("Невідома опція. Спробуйте ще раз.");
                    break;
            }

        }
    }
}
