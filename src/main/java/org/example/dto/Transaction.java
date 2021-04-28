package org.example.dto;

public class Transaction {

    private User user;
    private int year;
    private int value;

    public Transaction(User trader, int year, int value)
    {
        this.user = trader;
        this.year = year;
        this.value = value;
    }

    public User getTrader(){
        return this.user;
    }

    public int getYear(){
        return this.year;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public String toString(){
        return "{" + this.user + ", " +
                "year: "+this.year+", " +
                "value:" + this.value +"}";
    }

}
