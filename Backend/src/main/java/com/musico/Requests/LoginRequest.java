//Author: Simar Saggu
// Created on: 16th July,2021
package com.musico.Requests;

public class LoginRequest {
    private String registeredName;
    private String password;

    public String getRegisteredName() {
        return registeredName;
    }

    public void setRegisteredName(String registeredName) {
        this.registeredName = registeredName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
