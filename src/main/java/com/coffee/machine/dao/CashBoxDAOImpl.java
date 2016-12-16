package com.coffee.machine.dao;

import com.coffee.machine.domain.CashBox;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CashBoxDAOImpl implements CashBoxDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<CashBox> getAll(){
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(CashBox.class).list();
    }
}
