package com.miskatonicfilm.VehicleExercise.controller;


import com.miskatonicfilm.VehicleExercise.models.OwnerVehicleReview;
import com.miskatonicfilm.VehicleExercise.models.Review;
import com.miskatonicfilm.VehicleExercise.models.Vehicle;
import com.miskatonicfilm.VehicleExercise.models.VehicleReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.miskatonicfilm.VehicleExercise.repository.OVRRepo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class VehicleReviewController {

    @Autowired
    OVRRepo ovrRepo;

    /**
     * Create a review for a particular vehicle.
     * @param review
     * @throws com.miskatonicfilm.VehicleExercise.exceptions.ErrorInsertingReviewException
     * @return the created Review.
     */
    @PostMapping("/reviews")
    public ResponseEntity postReview(@RequestBody Review review) {
        ovrRepo.createReview(review);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Returns all reviews, with vehicle and owner info, by a particular username.
     * @param userName
     * @throws com.miskatonicfilm.VehicleExercise.exceptions.EmptyVehicleReviewsException when no reviews are present. I probably wouldn't do this,
     * but wanted to demonstrate the ControllerAdvice.
     * @return a list of composite data that includes Owner, Vehicle and Review data.
     */
    @GetMapping("reviews/{userName}")
    @ResponseBody
    public ResponseEntity<List<OwnerVehicleReview>> getReviewsWithDataByUserName(@PathVariable("userName") String userName) {
        return new ResponseEntity<>(ovrRepo.getAllReviewsByUsername(userName), HttpStatus.OK);
    }

    /**
     * Returns all reviews, sorted by star rating, regardless of if there's a star or review associated, i.e, they're null.
     * @throws com.miskatonicfilm.VehicleExercise.exceptions.EmptyVehicleReviewsException when no reviews are present.
     * @return a list of composite data that includes Vehicle and Review data, sorted by star.
     */
    @GetMapping("/reviews")
    @ResponseBody
    public ResponseEntity<List<VehicleReview>> getAllReviewsSortedByStar() {
        return new ResponseEntity<>(ovrRepo.getAllReviewsSortedByStar(), HttpStatus.OK);
    }
}
