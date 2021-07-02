package com.miskatonicfilm.VehicleExercise.models;

/**
 * Composite model representing data from the Owner, Vehicle and Review tables.
 */
public class OwnerVehicleReview {
    private String userName;
    private String review;
    private String make;
    private String model;
    private int year;
    private String name;
    private String email;

    public OwnerVehicleReview(String userName, String review, String make, String model, int year, String name, String email) {
        this.userName = userName;
        this.review = review;
        this.make = make;
        this.model = model;
        this.year = year;
        this.name = name;
        this.email = email;
    }

    public OwnerVehicleReview() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
