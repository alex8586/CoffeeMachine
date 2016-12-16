package com.coffee.machine.dao;

import com.coffee.machine.domain.Product;

import java.util.List;

public interface ProductDAO {

    void create(Product product);

    void update(Product product);

    void delete(Product product);

    Product getById(long productId);

    List<Product> getAll();

    Product getByName(String name);
}
