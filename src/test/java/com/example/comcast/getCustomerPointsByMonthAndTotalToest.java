package com.example.comcast;

import com.example.comcast.controller.responseDomain.CustomerPoints;
import com.example.comcast.repository.CustomerTransactionsRepository;
import com.example.comcast.repository.entity.CustomerTransaction;
import com.example.comcast.service.CustomerPointsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class getCustomerPointsByMonthAndTotalToest {

	private CustomerPointsService customerPointsService;

	@Mock
	private CustomerTransactionsRepository mockCustomerTransactionsRepository;

	List<CustomerTransaction> customerTransactionsList = new ArrayList<>();

	@BeforeEach
	void setMockCustomerTransactionsClassLevel(){
		customerPointsService = new CustomerPointsService(mockCustomerTransactionsRepository);

		CustomerTransaction customerTransaction0 = new CustomerTransaction();
		customerTransaction0.setTransactionId(UUID.randomUUID());
		customerTransaction0.setCustomerId(1);
		customerTransaction0.setDateOfPurchase(LocalDate.of(2023,1,10));
		customerTransaction0.setCostOfPurchase(BigDecimal.valueOf(12.99));
		customerTransactionsList.add(customerTransaction0);
	}
	@Test
	void testSummationOfMonthlyPoints() {
		List<CustomerPoints> expectedResponseList = new ArrayList<>();
		CustomerPoints expectedCustomerPoints = new CustomerPoints();
		expectedCustomerPoints.setCustomerPointsTotal(0);
		expectedCustomerPoints.setCustomerId(1);
		HashMap<Month, Integer> expectedMonthlyTotals = new HashMap<>();
		expectedMonthlyTotals.put(Month.JANUARY, 0);
		expectedCustomerPoints.setMonthlyTotals(expectedMonthlyTotals);
		expectedResponseList.add(expectedCustomerPoints);

		when(this.mockCustomerTransactionsRepository.findAll()).thenReturn(customerTransactionsList);
		List<CustomerPoints> customerPointsList = customerPointsService.getCustomerPointsByMonthAndTotal();
		assertEquals(expectedResponseList, customerPointsList);
	}

}