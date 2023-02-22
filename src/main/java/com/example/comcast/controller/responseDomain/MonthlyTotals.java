package com.example.comcast.controller.responseDomain;

import lombok.Data;

import java.time.Month;

@Data
public class MonthlyTotals {
    private Month[] monthlyTotals;
}
