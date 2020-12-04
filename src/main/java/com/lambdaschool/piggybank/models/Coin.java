package com.lambdaschool.piggybank.models;

import javax.persistence.*;

@Entity
@Table(name = "coins")
public class Coin {

    //Primary Key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long coinid;

    private String name;
    private String nameplural;
    private double value;
    private int quantity;

    //Constructors
    public Coin() {
        //default constructor for JPA
    }

    public Coin(String name, String nameplural, double value, int quantity) {
        this.coinid = coinid;
        this.name = name;
        this.nameplural = nameplural;
        this.value = value;
        this.quantity = quantity;
    }

    //Getters and Setters
    public long getCoinid() {
        return coinid;
    }

    public void setCoinid(long coinid) {
        this.coinid = coinid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameplural() {
        return nameplural;
    }

    public void setNameplural(String nameplural) {
        this.nameplural = nameplural;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
