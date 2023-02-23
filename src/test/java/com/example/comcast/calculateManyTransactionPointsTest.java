package com.example.comcast;

import com.example.comcast.repository.entity.CustomerTransaction;
import com.example.comcast.service.CustomerPointsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class calculateManyTransactionPointsTest {

	@Autowired
	private CustomerPointsService customerPointsService;

	@Test
	void testCalculatePointsForManyTransaction() {
		List<CustomerTransaction> transactionList = new ArrayList<>();
		CustomerTransaction transaction0 = new CustomerTransaction();
		CustomerTransaction transaction1 = new CustomerTransaction();
		CustomerTransaction transaction2 = new CustomerTransaction();
		transaction0.setCostOfPurchase(BigDecimal.valueOf(120.00));
		transaction1.setCostOfPurchase(BigDecimal.valueOf(250.33));
		transaction2.setCostOfPurchase(BigDecimal.valueOf(55.00));
		transactionList.add(transaction0);
		transactionList.add(transaction1);
		transactionList.add(transaction2);
		int response = customerPointsService.calculatePointsForManyTransactions(transactionList);
		assertEquals(445, response);
	}

}