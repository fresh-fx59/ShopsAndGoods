package main.controllers;

import main.dao.ProductDao;
import main.dao.ShopDao;
import main.model.Product;
import main.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ControlPanel {

    @Autowired
    private ShopDao shopDao;
    @Autowired
    private ProductDao productDao;

    @RequestMapping("/control-panel")
    public String loginMessage(Model model){
        List<Shop> shops = shopDao.getAll();
        List<Product> products = productDao.getAll();

        model.addAttribute("shops", shops)
                .addAttribute("products",products);
        return "index";
    }

}
