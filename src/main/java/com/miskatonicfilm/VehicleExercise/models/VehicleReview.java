package com.miskatonicfilm.VehicleExercise.models;

/**
 * Composite model representing data from the Vehicle and Review tables.
 */
public class VehicleReview {
    private String userName;
    private String review;
    private int star;
    private Long reviewId;
    private String make;
    private String model;
    private int year;

    public VehicleReview(String userName, String review, int star, Long reviewId, String make, String model, int year) {
        this.userName = userName;
        this.review = review;
        this.star = star;
        this.reviewId = reviewId;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public VehicleReview() {

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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
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
}
