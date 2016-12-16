package com.coffee.machine.dao;

import com.coffee.machine.domain.Balance;

public interface BalanceDAO {

    void create(Balance balance);

    void update(Balance balance);

    void delete(Balance balance);
}
