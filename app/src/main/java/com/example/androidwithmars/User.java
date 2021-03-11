package com.example.androidwithmars;

import android.net.Uri;

public class User {
    public String email,name,password,phone;
    public Uri selectedImageUri;

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    public User(){

    }

    public User(String email, String name, String password, String phone) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(String email, String name, String password, String phone, Uri selectedImageUri)
    {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.selectedImageUri=selectedImageUri;

    }
    public User (Uri selectedImageUri)
    {
        this.selectedImageUri=selectedImageUri;
    }
}
