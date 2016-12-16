package com.coffee.machine.domain;

import javax.persistence.*;

@Entity
@Table(name = "cashbox")
public class CashBox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "amount")
    private int amount;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Coin coin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }
}
