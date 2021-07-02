package com.miskatonicfilm.VehicleExercise.exceptions;

public class ErrorInsertingReviewException extends RuntimeException {
    public ErrorInsertingReviewException() {
        super("There was an error when attempting to create this review.");
    }
}
