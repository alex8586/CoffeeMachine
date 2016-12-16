package com.coffee.machine.controllers;

import com.coffee.machine.dao.CoinDAO;
import com.coffee.machine.dao.ProductDAO;
import com.coffee.machine.domain.Balance;
import com.coffee.machine.domain.Coin;
import com.coffee.machine.domain.Product;
import com.coffee.machine.services.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BalanceRestController {

    @Autowired
    private Balance balance;

    @Autowired
    private CoinDAO coinDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private BalanceService balanceService;

    @PostMapping(value = "/addCoin")
    public Number addCoin(@RequestBody Coin coin) {
        balance.addCoin(coinDAO.getByValue(coin.getValue()));
        return balance.getSumBalance();
    }

    @GetMapping(value = "/returnCoins")
    public Map<Double, Integer> returnCoins() {
        return balance.returnCoins();
    }

    @DeleteMapping(value = "/setToZeroBalance")
    public void setToZeroBalance() {
        balance.setToZero();
    }

    @PostMapping(value = "/giveChange")
    public Map<Double, Integer> giveChange(@RequestBody Product productName){
        Product product = productDAO.getByName(productName.getName());
        System.out.println("in giveChange !");
        System.out.println("productPrice " + product.getPrice());
        Map<Double, Integer> testingMap = balanceService.giveChange(product.getPrice());
        for(Map.Entry<Double, Integer> entry : testingMap.entrySet()){
            System.out.println("key " + entry.getKey() + " value " + entry.getValue());
        }
        return testingMap;
    }
}
