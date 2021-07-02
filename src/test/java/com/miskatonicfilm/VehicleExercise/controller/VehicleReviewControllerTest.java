package com.miskatonicfilm.VehicleExercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miskatonicfilm.VehicleExercise.models.OwnerVehicleReview;
import com.miskatonicfilm.VehicleExercise.models.Review;
import com.miskatonicfilm.VehicleExercise.models.VehicleReview;
import com.miskatonicfilm.VehicleExercise.repository.OVRRepo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleReviewController.class)
class VehicleReviewControllerTest {

    @Autowired
    private VehicleReviewController vehicleReviewController;

    @MockBean
    private OVRRepo ovrRepo;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testPostReview() throws Exception {
        Review review = new Review(2L, "BobTest", "Car goes fast.", 5);

        ovrRepo.createReview(review);
        mockMvc.perform(post("/reviews")
                .content(asJsonString(review))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetReviewsByUserName() throws Exception {
        String userName = "BobTest";

        List<OwnerVehicleReview> ownerVehicleReviewList = Arrays.asList(new OwnerVehicleReview(userName, "Car goes vroom vroom!", "Ford", "Fiesta", 1998, "Bob", "bob@test.com"),
        new OwnerVehicleReview(userName, "Car goes way way fast", "Pontiac", "Firebird", 2001, "Bob", "bob@test.com"));


        when(ovrRepo.getAllReviewsByUsername(userName)).thenReturn(ownerVehicleReviewList);
        mockMvc.perform(get("/reviews/" + userName)).andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].year", Matchers.is(1998)))
                .andExpect(jsonPath("$[1].year", Matchers.is(2001)));
    }

    @Test
    void testGetAllReviewsSortedByStar() throws Exception {
        List<VehicleReview> vehicleReviews = Arrays.asList(new VehicleReview("Bob Test", "Zoom zoom", 5, 3L, "Toyota", "Supra", 1993),
        new VehicleReview("Dr. Strange", "I loved this car, until I crashed it and lost use of my hands.", 4, 4L, "Lamborghini", "Huracan Coupe", 2016),
        new VehicleReview("Tony Stark", "Even the suit I made in a cave is faster than this rust bucket.", 2, 5L, "Audi", "TT", 2008));

        when(ovrRepo.getAllReviewsSortedByStar()).thenReturn(vehicleReviews);
        mockMvc.perform(get("/reviews"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[0].star", Matchers.is(5)))
                .andExpect(jsonPath("$[1].star", Matchers.is(4)))
                .andExpect(jsonPath("$[2].star", Matchers.is(2)));
    }

    /**
     * Helper method to turn object into a json string.
     * @param obj
     * @return Json String of object.
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}