package com.coffee.machhine.dao;

import com.coffee.machine.config.Application;
import com.coffee.machine.dao.CoinDAO;
import com.coffee.machine.domain.Coin;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
public class CoinDAOImplTest {

    @Autowired
    private CoinDAO coinDAO;

    @Ignore
    public void  setup(){
        Coin coin = new Coin();
        coin.setValue(1.23d);
        coinDAO.create(coin);
    }

    @Test
    public void getByValueTest(){
        Coin coin = new Coin();
        coin.setValue(1.23d);
        coinDAO.create(coin);

        Coin fromDao = coinDAO.getByValue(1.23d);
        assertEquals(coin, fromDao);
    }
}
