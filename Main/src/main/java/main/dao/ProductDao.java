package main.dao;

import main.aggregation.LookupAggregation;
import main.model.Product;
import main.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao implements DaoProduct<Product> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product get(String name) {
        return productRepository.getProductByName(name);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public double getAvg() {
        LookupAggregation lookupAggregation = new LookupAggregation();
        return lookupAggregation.getAverageProductsPrice();
    }

    @Override
    public Product getCheapest() {
        System.out.println(productRepository.findFirstByOrderByPriceAsc().getPrice());
        return productRepository.findFirstByOrderByPriceAsc();
    }

    @Override
    public Product getMostExpensive() {
        System.out.println(productRepository.findFirstByOrderByPriceDesc().getPrice());
        return productRepository.findFirstByOrderByPriceDesc();
    }

    @Override
    public int countProductsPriceGreaterThan(double price) {
        return productRepository.findByPriceGreaterThan(price).size();
    }
}
