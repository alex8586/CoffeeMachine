package com.coffee.machine.dao;

import com.coffee.machine.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(product);
        session.flush();
    }

    @Override
    public void update(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.update(product);
    }

    @Override
    public void delete(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(product);
    }

    @Override
    public Product getById(long productId) {
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.get(Product.class, productId);
    }

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Product.class).list();
    }

    @Override
    public Product getByName(String name){
        Session session = sessionFactory.getCurrentSession();
        return (Product) session.createCriteria(Product.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }
}
