package com.ebanking.payment.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
//@Data
@Table(schema = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    public Long id;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "tid", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    public UUID tid;

    public String currency;

    public Double getAmount() {
        return amount;
    }

    private Double amount;

    public String iban;

    @Column(name = "value_date")
    public Date valueDate;

    public String description;

}
