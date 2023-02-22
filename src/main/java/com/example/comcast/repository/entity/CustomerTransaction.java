package com.example.comcast.repository.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

@Data
@Entity(name = "customerTransaction")
public class CustomerTransaction {
    @Id
    @Column(name = "transactionId")
    @Type(type = "uuid-char")
    private UUID transactionId;
    @Column(name = "customerId")
    private long customerId;

    @Column(name = "dateOfPurchase")
    private LocalDate dateOfPurchase;

    @Column(name = "costOfPurchase")
    private BigDecimal costOfPurchase;

    public Month getMonthOfPurchase() {
        return this.getDateOfPurchase().getMonth();
    }
}
