package main.dao;

import main.model.Product;

import java.util.List;

public interface DaoProduct<T> {
    void save(T element);
    T get(String name);
    List<T> getAll();
    long count();
    double getAvg();
    Product getCheapest();
    Product getMostExpensive();
    int countProductsPriceGreaterThan(double price);
//    void update(T element);
//    void addProductToShop(Shop shop, Product product);
//    int countUniqueProductNames();
//    Double getAverageProductPrice();
//    String getMostExpensiveProduct();
//    String getCheapestProduct();
//    int countProductsPriceLess100();
}
