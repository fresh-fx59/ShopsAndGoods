package main.dao;

import java.util.List;

public interface DaoShop<T> {
    void save(T element);
    T get(String name);
    List<T> getAll();
    long count();
    double getAvg();
//    void update(T element);
//    void addProductToShop(Shop shop, Product product);
//    int countUniqueProductNames();
//    Double getAverageProductPrice();
//    String getMostExpensiveProduct();
//    String getCheapestProduct();
//    int countProductsPriceLess100();
}
