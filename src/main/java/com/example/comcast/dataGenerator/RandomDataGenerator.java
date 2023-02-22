package com.example.comcast.dataGenerator;

import com.example.comcast.repository.CustomerTransactionsRepository;
import com.example.comcast.repository.entity.CustomerTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomDataGenerator implements CommandLineRunner {

    private CustomerTransactionsRepository customerTransactionsRepository;

    @Autowired
    public RandomDataGenerator(CustomerTransactionsRepository customerTransactionsRepository) {
        this.customerTransactionsRepository = customerTransactionsRepository;
    }

    @Override
    public void run(String...args) throws Exception {
        int numberOfRecords = 0;
        int minDaysOfHistory = 1;
        int maxDaysOfHistory = 90 ;
        int minNumberOfCustomers = 1;
        int maxNumberOfCustomers = 4;
        int maxPurchaseAmount = 300;
        int minPurchaseAmount = 1;

        ThreadLocalRandom random = ThreadLocalRandom.current();

        List<CustomerTransaction> randomList = new ArrayList<>();

        for(int i = 0; i < numberOfRecords; i++) {
            CustomerTransaction customerTransaction = new CustomerTransaction();

            int randomCustomerId = random.nextInt(minNumberOfCustomers, maxNumberOfCustomers+1);
            LocalDate randomPurchaseDate = LocalDate.now().minusDays(random.nextInt(minDaysOfHistory, maxDaysOfHistory));
            BigDecimal randomPurchaseAmount = BigDecimal.valueOf(random.nextInt(minPurchaseAmount, maxPurchaseAmount));

            customerTransaction.setTransactionId(UUID.randomUUID());
            customerTransaction.setCustomerId(randomCustomerId);
            customerTransaction.setDateOfPurchase(randomPurchaseDate);
            customerTransaction.setCostOfPurchase(randomPurchaseAmount);

            randomList.add(customerTransaction);
        }

        customerTransactionsRepository.saveAll(randomList);

    }

}
