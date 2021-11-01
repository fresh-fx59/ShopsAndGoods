package main.dao;

import main.model.Shop;
import main.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopDao implements DaoShop<Shop> {

    @Autowired
    ShopRepository shopRepository;

    @Override
    public void save(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public Shop get(String name) {
        return shopRepository.getShopByName(name);
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public long count() {
        return shopRepository.count();
    }

    @Override
    public double getAvg() {
        return shopRepository.getAvgProductsCount();
    }
}
