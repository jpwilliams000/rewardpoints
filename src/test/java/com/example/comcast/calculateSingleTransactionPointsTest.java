package com.example.comcast;

import com.example.comcast.repository.entity.CustomerTransaction;
import com.example.comcast.service.CustomerPointsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class calculateSingleTransactionPointsTest {

	@Autowired
	private CustomerPointsService customerPointsService;

	@Test
	void testCalculatePointsForSingleTransaction() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(120.00));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(90,response);
	}
	@Test
	void testCalculatePointsForSingleTransaction_BelowLowerBound() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(49.99));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(0,response);
	}

	@Test
	void testCalculatePointsForSingleTransaction_LowerBound() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(50));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(0,response);
	}

	@Test
	void testCalculatePointsForSingleTransaction_AboveLowerBound() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(50.01));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(0,response);
	}

	@Test
	void testCalculatePointsForSingleTransaction_MiddleAboveLowerBound() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(75.76));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(25,response);
	}

	@Test
	void testCalculatePointsForSingleTransaction_BelowUpperBound() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(99.99));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(49,response);
	}

	@Test
	void testCalculatePointsForSingleTransaction_UpperBound() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(100));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(50,response);
	}

	@Test
	void testCalculatePointsForSingleTransaction_AboveUpperBound() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(100.99));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(50,response);
	}

	@Test
	void testCalculatePointsForSingleTransaction_LowAboveUpperBound() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(101.01));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(52,response);
	}

	@Test
	void testCalculatePointsForSingleTransaction_MiddleAboveUpperBound() {
		CustomerTransaction transaction = new CustomerTransaction();
		transaction.setCostOfPurchase(BigDecimal.valueOf(150.99));
		int response = customerPointsService.calculatePointsForSingleTransaction(transaction);
		assertEquals(150,response);
	}

}
