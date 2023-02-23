package com.example.comcast;

import com.example.comcast.service.CustomerPointsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Month;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class summationMonthlyPointsTest {

	@Autowired
	private CustomerPointsService customerPointsService;

	@Test
	void testSummationOfMonthlyPoints() {
		HashMap<Month, Integer> monthPointsHashMap = new HashMap<>();
		monthPointsHashMap.put(Month.JANUARY, 200);
		monthPointsHashMap.put(Month.JULY, 110);
		monthPointsHashMap.put(Month.SEPTEMBER, 110);
		Integer response = customerPointsService.summationMonthlyPoints(monthPointsHashMap);
		assertEquals(420, response);
	}

}