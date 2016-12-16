package com.coffee.machine.controllers;

import com.coffee.machine.dao.ProductDAO;
import com.coffee.machine.domain.Balance;
import com.coffee.machine.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private Balance balance;

    @GetMapping(value = "/products")
    public List<Product> getListProducts(){
        return productDAO.getAll();
    }

    @GetMapping(value = "/product/{drinkName}")
    public Product getDrink(@PathVariable("drinkName") String name){
        System.out.println("=== name " + name);
        Product product = productDAO.getByName(name);
        if(balance.getSumBalance() >= product.getPrice()){
            return product;
        }else
            return null;
    }
}
