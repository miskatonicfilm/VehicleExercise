package com.miskatonicfilm.VehicleExercise.exceptions;

public class EmptyVehicleReviewsException extends RuntimeException {
    public EmptyVehicleReviewsException() {
        super("No reviews found");
    }
}
