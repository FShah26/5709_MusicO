//Author: Simar Saggu
// Created on: 14th July,2021
package com.musico.Requests;

import org.apache.tomcat.util.codec.binary.Base64;

public class RegistrationRequest {

    private String email;
    private String password;
    private String country;
    private String phoneNumber;
    private byte[] profileImage ;
    private String name;

    public String getCountry() {
        return country;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = Base64.decodeBase64(profileImage);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
