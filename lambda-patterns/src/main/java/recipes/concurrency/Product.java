package recipes.concurrency;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:14
 * @since : 2019.09
 **/
public class Product {
    int id;
    String name;
    String description;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
