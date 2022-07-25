package com.ebanking.payment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID tid;

    public String amount;

    public String iban;

    @Column(name = "value_date")
    public Date valueDate;

    public String description;

}
