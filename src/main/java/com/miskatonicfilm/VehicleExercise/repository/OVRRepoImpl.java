package com.miskatonicfilm.VehicleExercise.repository;

import com.miskatonicfilm.VehicleExercise.exceptions.EmptyVehicleReviewsException;
import com.miskatonicfilm.VehicleExercise.exceptions.ErrorInsertingReviewException;
import com.miskatonicfilm.VehicleExercise.models.OwnerVehicleReview;
import com.miskatonicfilm.VehicleExercise.models.Review;
import com.miskatonicfilm.VehicleExercise.models.VehicleReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OVRRepoImpl implements OVRRepo {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public OVRRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createReview(Review review) {
        try {
            jdbcTemplate.update(
                    "INSERT into review (vehicle_id, username, review, star) values (?, ?, ?, ?)",
                    review.getVehicleId(), review.getUserName(), review.getReview(), review.getStar());
        } catch (DataAccessException e) {
            throw new ErrorInsertingReviewException();
        }
    }

    @Override
    public List<OwnerVehicleReview> getAllReviewsByUsername(String username) {
        String query = "SELECT r.username, r.review, v.make, v.model, v.year, o.name, o.email\n" +
                "FROM review as r\n" +
                "JOIN vehicle as v ON v.id = r.vehicle_id\n" +
                "JOIN owner as o on o.id = v.owner_id\n" +
                "where r.username = ?";

        BeanPropertyRowMapper OVRBeanMapper = new BeanPropertyRowMapper<>(OwnerVehicleReview.class);
        OVRBeanMapper.setPrimitivesDefaultedForNullValue(true);
        List<OwnerVehicleReview> ownerVehicleReviews = jdbcTemplate.query(query, OVRBeanMapper, username);
        if(ownerVehicleReviews.isEmpty()) {
            throw new EmptyVehicleReviewsException();
        }
        return ownerVehicleReviews;
    }

    @Override
    public List<VehicleReview> getAllReviewsSortedByStar() {
        String query = "SELECT r.username, r.review, r.star, r.id AS review_id, v.make, v.model, v.year\n" +
                "FROM review as r\n" +
                "JOIN vehicle as v ON v.id = r.vehicle_id\n" +
                "ORDER BY star DESC\n";

        BeanPropertyRowMapper VehicleReviewMapper = new BeanPropertyRowMapper<>(VehicleReview.class);
        VehicleReviewMapper.setPrimitivesDefaultedForNullValue(true);
        List<VehicleReview> vehicleReviews = jdbcTemplate.query(query, VehicleReviewMapper);
        if(vehicleReviews.isEmpty()) {
            throw new EmptyVehicleReviewsException();
        }
        return vehicleReviews;
    }
}
