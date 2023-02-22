package com.example.comcast.controller;

import com.example.comcast.controller.responseDomain.CustomerPoints;
import com.example.comcast.service.CustomerPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="customer", produces="application/json")
public class RewardPointsController {

    private CustomerPointsService customerPointsService;
    @Autowired
    public RewardPointsController(CustomerPointsService customerPointsService) {
        this.customerPointsService = customerPointsService;
    }
    @RequestMapping(path = "/points", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerPoints>> getAllCustomerPointsByMonthAndTotal(){
        return new ResponseEntity<>(customerPointsService.getCustomerPointsByMonthAndTotal(), HttpStatus.OK);
    }
}
