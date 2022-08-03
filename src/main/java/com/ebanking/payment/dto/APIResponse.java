package com.ebanking.payment.dto;

import com.ebanking.payment.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {
    BigDecimal credit;
    BigDecimal debit;
    T response;

    public APIResponse(int size, List<Transaction> result) {

    }
}
