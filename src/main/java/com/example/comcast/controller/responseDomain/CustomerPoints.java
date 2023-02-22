package com.example.comcast.controller.responseDomain;

import lombok.Data;

import java.time.Month;
import java.util.HashMap;


@Data
public class CustomerPoints {
    private long customerId;
    private int customerPointsTotal;
    private HashMap<Month, Integer> monthlyTotals;
}
