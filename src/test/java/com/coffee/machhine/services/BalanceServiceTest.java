package com.coffee.machhine.services;

import com.coffee.machine.dao.CashBoxDAOImpl;
import com.coffee.machine.domain.Balance;
import com.coffee.machine.domain.CashBox;
import com.coffee.machine.domain.Coin;
import com.coffee.machine.services.BalanceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BalanceServiceTest {

    @Mock
    private Balance balance;

    @Mock
    private CashBoxDAOImpl cashBoxDAO;

    @InjectMocks
    private BalanceService balanceService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void giveChangeIfProductPriceSameAsBalanceShouldReturnZero(){
        List<CashBox> cashBoxList = createCashBoxList(0.01, 0.02, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0);
        Mockito.doReturn(cashBoxList).when(cashBoxDAO).getAll();
        Mockito.doReturn(0.79).when(balance).getSumBalance();
        Map<Double, Integer> change = balanceService.giveChange(0.79);

        assertEquals(0.0, getSumCoins(change), 0.0);
    }

    @Test
    public void giveChangeShoudReturnChange(){
        List<CashBox> cashBoxList = createCashBoxList(0.01, 0.02, 0.05, 0.1, 0.2, 0.5, 1.0, 2.0);
        when(cashBoxDAO.getAll()).thenReturn(cashBoxList);
        when(balance.getSumBalance()).thenReturn(2.0);
        Map<Double, Integer> change = balanceService.giveChange(1.19);

        assertEquals(0.81, getSumCoins(change), 0.0);
    }

    @Test
    public void giveChangeIfCashBoxNotContainBigValueCoinShouldReturnSmallValueCoins(){
        List<CashBox> cashBoxList = createCashBoxList(0.0, 0.02, 0.0, 0.1, 0.2, 0.5, 1.0, 2.0);
        Mockito.doReturn(cashBoxList).when(cashBoxDAO).getAll();
        Mockito.doReturn(0.24).when(balance).getSumBalance();
        Map<Double, Integer> change = balanceService.giveChange(0.1);

        assertEquals(0.14, getSumCoins(change), 0.0);
    }

    @Test
    public void giveChangeIfCashBoxNotContainBigValueCoinShouldReturnSmallValueCoins2(){
        List<CashBox> cashBoxList = createCashBoxList(0.01, 0.02, 0.05, 0.1, 0.2, 0.0, 1.0, 2.0);
        Mockito.doReturn(cashBoxList).when(cashBoxDAO).getAll();
        Mockito.doReturn(1.14).when(balance).getSumBalance();
        Map<Double, Integer> change = balanceService.giveChange(0.33);

        assertEquals(0.81, getSumCoins(change), 0.0);
    }

    private List<CashBox> createCashBoxList(double first, double second, double third, double fourth,
                                            double fifth, double sixth, double seventh, double eighth){
        List<CashBox> list = new ArrayList<>();
        list.add(createCashBox(first));
        list.add(createCashBox(second));
        list.add(createCashBox(third));
        list.add(createCashBox(fourth));
        list.add(createCashBox(fifth));
        list.add(createCashBox(sixth));
        list.add(createCashBox(seventh));
        list.add(createCashBox(eighth));
        return list;
    }

    private CashBox createCashBox(double coinValue){
        Coin coin = new Coin();
        coin.setValue(coinValue);
        CashBox cashBox = new CashBox();
        cashBox.setCoin(coin);
        cashBox.setAmount(15);
        return cashBox;
    }

    private double getSumCoins(Map<Double, Integer> map){
        double result = 0;
        for(Map.Entry<Double, Integer> entry : map.entrySet()){
            result += entry.getKey() * entry.getValue();
        }
        result = Math.round(result*100.0)/100.0;
        return result;
    }
}
