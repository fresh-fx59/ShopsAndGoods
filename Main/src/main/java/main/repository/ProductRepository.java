package main.repository;

import main.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{name:'?0'}")
    Product getProductByName(String name);

    List<Product> findAll();

    @Override
    long count();

    Product findFirstByOrderByPriceDesc();

    Product findFirstByOrderByPriceAsc();

    List<Product> findByPriceGreaterThan(double price);

}
