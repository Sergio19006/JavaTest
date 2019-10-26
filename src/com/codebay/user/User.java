package com.codebay.user;

import java.util.ArrayList;

public class User {
    public String name;
    public String surname;
    public boolean active;
    public String email;
    public String city;
    public String creationDate;

    public User() {
    }

    public User(String name, String surname, boolean active, String email, String city, String date) {
        this.name = name;
        this.surname = surname;
        this.active = active;
        this.email = email;
        this.city = city;
        this.creationDate = date;
    }
}
