package com.coffee.machine.dao;

import com.coffee.machine.domain.Balance;
import com.coffee.machine.domain.Coin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BalanceDAOImpl implements BalanceDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void create(Balance balance){
        Session session = sessionFactory.getCurrentSession();
        session.persist(balance);
        session.flush();
    }

    public void update(Balance balance){
        Session session = sessionFactory.getCurrentSession();
        session.update(balance);
    }

    public void delete(Balance balance){
        Session session = sessionFactory.getCurrentSession();
        session.delete(balance);
    }

    public void addCoin(Coin coin){

    }
}
