package com.example.comcast;

import com.example.comcast.repository.entity.CustomerTransaction;
import com.example.comcast.service.CustomerPointsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class calculatePointsByMonthTest {

	@Autowired
	private CustomerPointsService customerPointsService;

	@Test
	void testCalculatePointsForManyTransaction() {
		List<CustomerTransaction> transactionList = new ArrayList<>();
		CustomerTransaction transaction0 = new CustomerTransaction();
		CustomerTransaction transaction1 = new CustomerTransaction();
		CustomerTransaction transaction2 = new CustomerTransaction();

		transaction0.setCostOfPurchase(BigDecimal.valueOf(120.00));
		transaction0.setDateOfPurchase(LocalDate.of(2023,1,10));

		transaction1.setCostOfPurchase(BigDecimal.valueOf(250.33));
		transaction1.setDateOfPurchase(LocalDate.of(2023,1,10));

		transaction2.setCostOfPurchase(BigDecimal.valueOf(55.00));
		transaction2.setDateOfPurchase(LocalDate.of(2023,1,10));

		transaction2.setCostOfPurchase(BigDecimal.valueOf(55.00));
		transaction2.setDateOfPurchase(LocalDate.of(2023,2,10));

		transactionList.add(transaction0);
		transactionList.add(transaction1);
		transactionList.add(transaction2);

		HashMap<Month, Integer> response = customerPointsService.calculatePointsByMonth(transactionList);
		assertEquals(5, response.get(Month.FEBRUARY));
		assertEquals(440, response.get(Month.JANUARY));
	}

}