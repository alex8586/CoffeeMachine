package com.coffee.machine.domain;

import javax.persistence.*;

@Entity
@Table(name = "coins")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "value")
    private double value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;

        return new org.apache.commons.lang.builder.EqualsBuilder()
                .append(id, coin.id)
                .append(value, coin.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang.builder.HashCodeBuilder(17, 37)
                .append(id)
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Coin{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
