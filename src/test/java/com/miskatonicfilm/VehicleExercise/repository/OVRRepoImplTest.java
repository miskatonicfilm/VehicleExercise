package com.miskatonicfilm.VehicleExercise.repository;

import com.miskatonicfilm.VehicleExercise.exceptions.EmptyVehicleReviewsException;
import com.miskatonicfilm.VehicleExercise.exceptions.ErrorInsertingReviewException;
import com.miskatonicfilm.VehicleExercise.models.OwnerVehicleReview;
import com.miskatonicfilm.VehicleExercise.models.Review;
import com.miskatonicfilm.VehicleExercise.models.VehicleReview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OVRRepoImplTest {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    OVRRepoImpl ovrRepo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReview() {
        Review review = new Review(2L, "BobTest", "Car goes fast.", 5);

        ovrRepo.createReview(review);
        verify(jdbcTemplate, times(1)).update(any(String.class), any(Long.class), any(String.class), any(String.class), any(int.class));
    }

    @Test
    void testCreateReviewFails() {
        Review review = new Review(2L, "BobTest", "Car goes fast.", 5);
        when(jdbcTemplate.update(any(String.class), any(Long.class), any(String.class), any(String.class), any(int.class))).thenThrow(new DataAccessException("Bad stuff happened") {});


        assertThrows(ErrorInsertingReviewException.class, () -> {
            ovrRepo.createReview(review);
        });
    }

    @Test
    void testGetAllReviewsByUserName() {
        String userName = "BobTest";
        List<OwnerVehicleReview> ownerVehicleReviewList = Arrays.asList(new OwnerVehicleReview(userName, "Car goes vroom vroom!", "Ford", "Fiesta", 1998, "Bob", "bob@test.com"),
                new OwnerVehicleReview(userName, "Car goes way way fast", "Pontiac", "Firebird", 2001, "Bob", "bob@test.com"));

        when(jdbcTemplate.query(any(String.class), any(RowMapper.class), any(String.class))).thenReturn(ownerVehicleReviewList);
        List<OwnerVehicleReview> ownerVehicleReviews = ovrRepo.getAllReviewsByUsername(userName);

        ownerVehicleReviews.forEach(x -> assertTrue(x.getUserName().equals("BobTest")));
        assertTrue(ownerVehicleReviews.size() == 2);
    }

    @Test
    void testGetAllReviewsByUserNameEmpty() {
        when(jdbcTemplate.query(any(String.class), any(RowMapper.class), any(String.class))).thenReturn(Arrays.asList());

        assertThrows(EmptyVehicleReviewsException.class, () -> {
            ovrRepo.getAllReviewsByUsername("Bob");
        });
    }

    @Test
    void testGetAllReviewsSorted() {
        List<VehicleReview> vehicleReviews = Arrays.asList(new VehicleReview("Bob Test", "Zoom zoom", 5, 3L, "Toyota", "Supra", 1993),
                new VehicleReview("Dr. Strange", "I loved this car, until I crashed it and lost use of my hands.", 4, 4L, "Lamborghini", "Huracan Coupe", 2016),
                new VehicleReview("Tony Stark", "Even the suit I made in a cave is faster than this rust bucket.", 2, 5L, "Audi", "TT", 2008));

        when(jdbcTemplate.query(any(String.class), any(RowMapper.class))).thenReturn(vehicleReviews);
        List<VehicleReview> vehicleReviewList = ovrRepo.getAllReviewsSortedByStar();

        assertTrue(vehicleReviewList.size() == vehicleReviews.size());
    }

    @Test
    void testGetAllReviewsSortedEmpty() {
        when(jdbcTemplate.query(any(String.class), any(RowMapper.class))).thenReturn(Arrays.asList());

        assertThrows(EmptyVehicleReviewsException.class, () -> {
            ovrRepo.getAllReviewsSortedByStar();
        });
    }


}