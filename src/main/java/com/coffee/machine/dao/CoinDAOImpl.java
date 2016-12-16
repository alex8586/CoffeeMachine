package com.coffee.machine.dao;

import com.coffee.machine.domain.Coin;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CoinDAOImpl implements CoinDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Coin coin){
        Session session = sessionFactory.getCurrentSession();
        session.persist(coin);
        session.flush();
    }

    @Override
    public void update(Coin coin){
        Session session = sessionFactory.getCurrentSession();
        session.update(coin);
    }

    @Override
    public void delete(Coin coin){
        Session session = sessionFactory.getCurrentSession();
        session.delete(coin);
    }

    @Override
    public Coin getById(long id){
        Session session = sessionFactory.getCurrentSession();
        return (Coin) session.get(Coin.class, id);
    }

    @Override
    public List<Coin> getAllSortByValue(){
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Coin.class)
                .addOrder(Order.desc("value"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public Coin getByValue(double value){
        Session session = sessionFactory.getCurrentSession();
        return (Coin) session.createCriteria(Coin.class)
                .add(Restrictions.eq("value", value))
                .uniqueResult();
    }
}
