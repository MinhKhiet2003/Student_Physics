package com.example.baitaplonquanlysinhvienhnue;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    int resourceID;
    private int image;
    private String studentId;

    @SerializedName("fullName")
    private String fullName;

    private String className;
    private String date;
    private String home;
    private boolean gender;
    @SerializedName("email")
    private String email;
    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("password")
    private String password;
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(String fullName,String phoneNumber, String email, String password) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public User(String studentId, String fullName, String date, String home, String className,boolean gender,String email,String phoneNumber) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.date = date;
        this.home = home;
        this.className = className;
        this.gender=gender;
        this.email=email;
        this.phoneNumber=phoneNumber;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
