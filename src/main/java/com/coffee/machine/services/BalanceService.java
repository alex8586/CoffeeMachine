package com.coffee.machine.services;

import com.coffee.machine.dao.CashBoxDAO;
import com.coffee.machine.domain.Balance;
import com.coffee.machine.domain.CashBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BalanceService {

    @Autowired
    private CashBoxDAO cashBoxDAO;

    @Autowired
    private Balance balance;

    public Map<Double, Integer> giveChange(double productPrice) {
        Map<Double, Integer> changeMap = new HashMap<>();
        List<CashBox> cashBoxList = cashBoxDAO.getAll();

        Collections.sort(cashBoxList, new Comparator<CashBox>() {
            @Override
            public int compare(CashBox o1, CashBox o2) {
                return Double.compare(o2.getCoin().getValue(), o1.getCoin().getValue());
            }
        });
        double change = balance.getSumBalance() - productPrice;
        change = Math.round(change * 100.0) / 100.0;
        double currentCoinValue = 0;
        for(int i = 0; i < cashBoxList.size(); i++){

            if(change >= cashBoxList.get(i).getCoin().getValue()){
                currentCoinValue = cashBoxList.get(i).getCoin().getValue();
                if(changeMap.isEmpty()){
                    changeMap.put(currentCoinValue, 1);
                }else {
                    if(changeMap.containsKey(currentCoinValue)){
                        changeMap.put(currentCoinValue, changeMap.get(currentCoinValue) + 1);
                    } else {
                        changeMap.put(currentCoinValue, 1);
                    }
                }
                change = change - currentCoinValue;
                change = Math.round(change*100.0)/100.0;
                i = 0;
            }
            if(change == 0)
                break;
        }

        return changeMap;
    }
}
