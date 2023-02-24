package com.example.comcast.service;

import com.example.comcast.controller.responseDomain.CustomerPoints;
import com.example.comcast.repository.CustomerTransactionsRepository;
import com.example.comcast.repository.entity.CustomerTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CustomerPointsService {

    private CustomerTransactionsRepository customerTransactionsRepository;

    @Autowired
    public CustomerPointsService(CustomerTransactionsRepository customerTransactionsRepository){
        this.customerTransactionsRepository = customerTransactionsRepository;
    }

    /*
    * Variables are intentionally defined with int datatype as the type casting will default a truncation of decimals.
    * This is intentional and consistent in that the requirement awards a point, only if customer transaction is a full dollar expense.
    * May need reconsideration of data types if future requirements are altered to consider fractional dollar thresholds.
     */
    public int calculatePointsForSingleTransaction(CustomerTransaction ct){
        int pointsForPurchase = 0;
        int doublePointsThreshold = 100;
        int doublePointsMultiplier = 2;
        int singlePointsThreshold = 50;

        if(ct.getCostOfPurchase().intValue() > doublePointsThreshold) {
            pointsForPurchase = doublePointsMultiplier * (ct.getCostOfPurchase().intValue() - doublePointsThreshold) + singlePointsThreshold;
        } else if (ct.getCostOfPurchase().intValue() > singlePointsThreshold) {
            pointsForPurchase = ct.getCostOfPurchase().intValue() - singlePointsThreshold;
        }
        return pointsForPurchase;
    }

    public int calculatePointsForManyTransactions(List<CustomerTransaction> customerTransactionList){
        int totalPoints = 0;
        for(CustomerTransaction ct : customerTransactionList) {
            totalPoints += calculatePointsForSingleTransaction(ct);
        }
        return totalPoints;
    }

    public HashMap<Month, Integer> calculatePointsByMonth(List<CustomerTransaction> customerTransactionList){
        HashMap<Month, Integer> monthlyPoints = new HashMap<>();
        
        HashMap<Month, List<CustomerTransaction>> mapMonthToTransactions =
                (HashMap<Month, List<CustomerTransaction>>) customerTransactionList.stream()
                        .collect(groupingBy(CustomerTransaction::getMonthOfPurchase));

        mapMonthToTransactions.forEach( (month, monthlyTransactions) -> {
            monthlyPoints.put(month, calculatePointsForManyTransactions(monthlyTransactions));
        });

        return monthlyPoints;
    }

    public Integer summationMonthlyPoints(HashMap<Month, Integer> monthlyPointsMap) {
        Integer totalPoints = 0;
        for(Integer monthlyPoints : monthlyPointsMap.values()){
            totalPoints += monthlyPoints;
        }
        return totalPoints;
    }

    public List<CustomerPoints> getCustomerPointsByMonthAndTotal() {

        List<CustomerTransaction> customerTransactionsList = customerTransactionsRepository.findAll();

        HashMap<Long, List<CustomerTransaction>> mapCustomerIdToTransactions =
                (HashMap<Long, List<CustomerTransaction>>) customerTransactionsList.stream()
                        .collect(groupingBy(CustomerTransaction::getCustomerId));

        List<CustomerPoints> customerPointsList = new ArrayList<>();

        mapCustomerIdToTransactions.forEach( (customerId, customerTransactionList) -> {
            CustomerPoints customerPoints = new CustomerPoints();
            HashMap<Month, Integer> monthlyPointsMap = calculatePointsByMonth(customerTransactionList);

            customerPoints.setCustomerId(customerId);
            customerPoints.setMonthlyTotals(monthlyPointsMap);
            customerPoints.setCustomerPointsTotal(summationMonthlyPoints(monthlyPointsMap));
            customerPointsList.add(customerPoints);
        });

        return customerPointsList;
    }

}
