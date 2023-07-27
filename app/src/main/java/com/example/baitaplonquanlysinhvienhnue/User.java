package com.example.baitaplonquanlysinhvienhnue;

import android.content.res.Resources;

import java.io.Serializable;

public class User implements Serializable {
    int resourceID;
    private String name;
    private String msv;

    private String address;

    private String dateOfBirth;
    private Boolean gioiTinh;
    private String email;
    private String className;
    private String phoneNumber;

    public User(int resourceID, String name, String msv, String adress, String dateOfBirth, Boolean gioiTinh,String className, String email, String phoneNumber) {
        this.resourceID = resourceID;
        this.name = name;
        this.msv = msv;
        this.address = adress;
        this.dateOfBirth= dateOfBirth;
        this.gioiTinh = gioiTinh;
        this.className = className;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
