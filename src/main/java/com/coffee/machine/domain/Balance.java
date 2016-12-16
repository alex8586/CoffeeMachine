package com.coffee.machine.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class Balance {

    private Map<Coin, Integer> coinsAmount;

    public Balance(){
        this.coinsAmount = new HashMap<>();
    }

    public Map<Coin, Integer> getCoinsAmount() {
        return coinsAmount;
    }

    public void setCoinsAmount(Map<Coin, Integer> coinsAmount) {
        this.coinsAmount = coinsAmount;
    }

    public void addCoin(Coin coin){
        if(coinsAmount.isEmpty()){
            coinsAmount.put(coin, 1);
            return;
        }
        for(Map.Entry<Coin, Integer> entry : coinsAmount.entrySet()){
            if(entry.getKey().equals(coin)){
                coinsAmount.put(coin, entry.getValue() + 1);
                return;
            }
        }
        coinsAmount.put(coin, 1);
    }

    public double getSumBalance(){
        if(coinsAmount.isEmpty())
            return 0;
        double sum = 0;
        for(Map.Entry<Coin, Integer> entry : coinsAmount.entrySet()){
            sum += entry.getKey().getValue() * entry.getValue();
        }
        sum = Math.round(sum*100.0)/100.0;
        return sum;
    }

    public Map<Double, Integer> returnCoins(){
        Map<Double, Integer> change = new HashMap<>();
        for(Map.Entry<Coin, Integer> entry : coinsAmount.entrySet()){
            change.put(entry.getKey().getValue(), entry.getValue());
        }
        return change;
    }

    public void setToZero(){
        coinsAmount.clear();
    }

    @Override
    public String toString() {
        return "Balance{" +
                "coinsAmount=" + coinsAmount +
                '}';
    }
}
