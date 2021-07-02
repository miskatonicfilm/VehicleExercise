package com.miskatonicfilm.VehicleExercise.models;

/**
 * Model representing a Review from the Review table.
 */
public class Review {
    private Long id;
    private Long vehicleId;
    private String userName;
    private String review;
    private int star;

    public Review() {

    }
    public Review(Long vehicleId, String userName, String review, int star) {
        this.vehicleId = vehicleId;
        this.userName = userName;
        this.review = review;
        this.star = star;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
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
}
