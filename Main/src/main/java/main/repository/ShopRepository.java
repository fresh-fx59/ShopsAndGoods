package main.repository;

import main.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ShopRepository extends MongoRepository<Shop, String> {

    @Query("{name:'?0'}")
    Shop getShopByName(String name);

    List<Shop> findAll();

    long count();

    @Query("{name:'1'}")
    double getAvgProductsCount();


}
