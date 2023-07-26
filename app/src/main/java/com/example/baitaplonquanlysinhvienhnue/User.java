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

    public User(int resourceID, String name, String msv, String adress, String dateOfBirth, Boolean gioiTinh) {
        this.resourceID = resourceID;
        this.name = name;
        this.msv = msv;
        this.address = adress;
        this.dateOfBirth= dateOfBirth;
        this.gioiTinh = gioiTinh;
    }


    public int getResourceID() {
        return resourceID;
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

    public void setMsv(String adress) {
        this.msv = msv;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String adress) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }


}
