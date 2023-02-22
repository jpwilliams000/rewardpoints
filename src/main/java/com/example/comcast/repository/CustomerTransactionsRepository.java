package com.example.comcast.repository;

import com.example.comcast.repository.entity.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerTransactionsRepository extends JpaRepository<CustomerTransaction, UUID> {
}
