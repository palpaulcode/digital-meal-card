package com;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String firstName;
    private String middleName;
    private String surname;
    private String registration;
    private List<Cards> cards = new ArrayList<>();

    public Student() { }

    public Student(String firstName, String middleName, String surname, String registration) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.registration = registration;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public List<Cards> getCards() {
        return cards;
    }

    public void setCards(List<Cards> cards) {
        this.cards = cards;
    }
}
