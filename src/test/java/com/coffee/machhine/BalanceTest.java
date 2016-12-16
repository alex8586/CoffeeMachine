package com.coffee.machhine;

import com.coffee.machine.domain.Balance;
import com.coffee.machine.domain.Coin;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BalanceTest {

    private Balance balance;

    @Before
    public void setup(){
        this.balance = new Balance();
    }

    @Test
    public void addCoinWhenBalanceIsEmpty(){
        Coin coin = createCoin(0.5);

        assertTrue(balance.getCoinsAmount().isEmpty());
        balance.addCoin(coin);
        assertTrue(balance.getCoinsAmount().size() == 1);
        assertTrue(balance.getCoinsAmount().containsKey(coin));
    }

    @Test
    public void addCoinSecondTheSameCoin(){
        Coin coin = createCoin(2.34);
        Coin second = createCoin(1.23);

        balance.addCoin(coin);
        balance.addCoin(second);

        assertTrue(balance.getCoinsAmount().size() == 2);
        assertTrue(balance.getCoinsAmount().containsKey(coin));
        assertTrue(balance.getCoinsAmount().containsKey(second));
    }

    @Test
    public void addTwoCoinsWithSameValue(){
        Coin coin = createCoin(3.21);

        balance.addCoin(coin);
        balance.addCoin(coin);

        assertTrue(balance.getCoinsAmount().size() == 1);
        assertTrue(balance.getCoinsAmount().containsKey(coin));
        assertEquals(2, (int)balance.getCoinsAmount().get(coin));
    }

    @Test
    public void addTwoCoinsWithSameValueAndOneAnotherValue(){
        Coin coin = createCoin(1.23);
        Coin other = createCoin(3.21);

        balance.addCoin(coin);
        balance.addCoin(other);
        balance.addCoin(coin);

        int a = balance.getCoinsAmount().get(coin);
        System.out.println(a);
        assertTrue(balance.getCoinsAmount().size() == 2);
        assertTrue(balance.getCoinsAmount().get(coin) == 2);
        assertTrue(balance.getCoinsAmount().get(other) == 1);
    }

    @Test
    public void addThreeDifferentCoins(){
        Coin first = createCoin(1.22);
        Coin second = createCoin(2.33);
        Coin third = createCoin(3.44);

        balance.addCoin(first);
        assertTrue(balance.getCoinsAmount().size() == 1);
        balance.addCoin(second);
        assertTrue(balance.getCoinsAmount().size() == 2);
        balance.addCoin(third);
        assertTrue(balance.getCoinsAmount().size() == 3);
    }

    @Test
    public void getSumBalanceIfBalanceIsEmpty(){
        assertTrue(balance.getSumBalance() == 0);
    }

    @Test
    public void getSumBalanceWithOneCoin(){
        Coin coin = createCoin(3.45);
        balance.addCoin(coin);
        assertTrue(balance.getSumBalance() == 3.45);
    }

    @Test
    public void getSumBalanceWithTwoDifferentCoins(){
        Coin coin = createCoin(2.22);
        Coin second = createCoin(4.56);

        balance.addCoin(coin);
        balance.addCoin(second);

        assertEquals(balance.getSumBalance(),6.78, 0.02);
    }

    @Test
    public void getSumBalanceWithFourCoins(){
        balance.addCoin(createCoin(0.2));
        assertEquals(0.2, balance.getSumBalance(), 0.02);

        balance.addCoin(createCoin(1));
        assertEquals(1.2, balance.getSumBalance(), 0.02);

        balance.addCoin(createCoin(0.2));
        assertEquals(1.4, balance.getSumBalance(), 0.02);

        balance.addCoin(createCoin(0.05));
        assertEquals(1.45, balance.getSumBalance(), 0.02);

    }

    @Test
    public void returnCoinsMethodWithOneCoin(){
        balance.addCoin(createCoin(0.5));
        Map<Double, Integer> map =  balance.returnCoins();
        assertTrue(getSumCoins(map) == 0.5);
        balance.setToZero();
        assertTrue(balance.getCoinsAmount().isEmpty());
    }

    @Test
    public void returnCoinsMethodWithThreeSameValueCoins(){
        balance.addCoin(createCoin(0.2));
        balance.addCoin(createCoin(0.2));
        balance.addCoin(createCoin(0.2));

        Map<Double, Integer> map = balance.returnCoins();
        assertEquals(0.6, getSumCoins(map), 0.02);
    }

    @Test
    public void returnCoinsMethodWithThreeDifferentCoins(){
        balance.addCoin(createCoin(0.2));
        balance.addCoin(createCoin(0.1));
        balance.addCoin(createCoin(0.5));

        Map<Double, Integer> map = balance.returnCoins();
        assertEquals(0.8, getSumCoins(map), 0.02);
    }

    @Test
    public void returnCoinsIfBalanceZero(){
        assertTrue(balance.getCoinsAmount().isEmpty());
        Map<Double, Integer> map = balance.returnCoins();
        assertTrue(0 == getSumCoins(map));
    }

    private Coin createCoin(double value){
        Coin coin = new Coin();
        coin.setValue(value);
        return coin;
    }

    private double getSumCoins(Map<Double, Integer> map){
        double result = 0;
        if(map.isEmpty()){
            return 0;
        }else{
            for(Map.Entry<Double, Integer> entry : map.entrySet()){
                result += entry.getKey() * entry.getValue();
            }
            return result;
        }
    }
}
