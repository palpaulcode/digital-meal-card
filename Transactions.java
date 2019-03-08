package com;

import java.util.Date;

public class Transactions {

    private int units;
    private double amount;
    private String menuItem;
    private String cardNumber;
    private Date transactionTime;

    public Transactions(){ }
    
    public Transactions(Date transactionTime,int units, double amount,String menuItem, String cardNumber){
        this.transactionTime=transactionTime;
        this.units=units;
        this.menuItem=menuItem;
        this.amount=amount;
        this.cardNumber=cardNumber;
        
    }
    public int getUnits(){
        return units;
    }
    public void setUnits(){
        this.units=units;
    }
    public double getAmount(){
        return amount;
    }
    public void setAmount(){
        this.amount=amount;
    }
    public String getMenuItem(){
        return menuItem;
    }
    public void setMenuItem(){
        this.menuItem=menuItem;
    }
    public String getCardNumber(){
        return cardNumber;
    }
    public void setCardNumber(){
        this.cardNumber=cardNumber;
    }
    public Date getTransactionTime(){
        return transactionTime;
    }
    public void setTransactionTime(){
        this.transactionTime=transactionTime;
    }
        
    }
    


