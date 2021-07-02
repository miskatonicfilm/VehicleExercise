package com.miskatonicfilm.VehicleExercise.repository;

import com.miskatonicfilm.VehicleExercise.models.OwnerVehicleReview;
import com.miskatonicfilm.VehicleExercise.models.Review;
import com.miskatonicfilm.VehicleExercise.models.VehicleReview;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OVRRepo {
    /**
     * Insert a review with the info provided
     * @param review
     */
    void createReview(Review review);

    /**
     * Get all the reviews associated with a username.
     * @param username
     * @return a list of composite data that includes Owner, Vehicle and Review data.
     */
    List<OwnerVehicleReview> getAllReviewsByUsername(String username);

    /**
     * Get all reviews, sorted by star rating.
     * @return a list of composite data that includes Vehicle and Review data, sorted by star.
     */
    List<VehicleReview> getAllReviewsSortedByStar();

}
