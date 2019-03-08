package com;

public class Menus {

    private String menu;
    private int units = 0;
    private double price = 0.00;
    private double total = 0.00;
    
    
    public Menus() { }

    public Menus(String menu, double price) {
        this.menu = menu;
        this.price = price;
    }
    
    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


}

