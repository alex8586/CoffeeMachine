package com.coffee.machine.dao;

import com.coffee.machine.domain.Coin;

import java.util.List;

public interface CoinDAO {

    void create(Coin coin);

    void update(Coin coin);

    void delete(Coin coin);

    Coin getById(long id);

    List<Coin> getAllSortByValue();

    Coin getByValue(double value);

}
