package com.miskatonicfilm.VehicleExercise;

import com.miskatonicfilm.VehicleExercise.controller.VehicleReviewController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VehicleExerciseApplicationTests {

	@Autowired
	VehicleReviewController vehicleReviewController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(vehicleReviewController);
	}

}
