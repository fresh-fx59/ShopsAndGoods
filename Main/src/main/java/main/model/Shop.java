package main.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("shops")
public class Shop {
    @Id
    private String id;
    private String name;
    private List<String> products;

    public Shop(String id, String name, List<String> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String toString() {
        String result = "id: " + id + "\n" +
                "name: " + name + "\n" +
                "products: ";
        String products = "[";
        if (!(this.products == null)) {
            for (String product : this.products) {
                products += product + ",";
            }

            products += "]" + "\n";

            return result + products;

        }
        products = "[]" + "\n";
        return result + products;

    }
}
