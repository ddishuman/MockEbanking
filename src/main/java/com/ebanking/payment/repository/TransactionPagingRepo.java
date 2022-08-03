package com.ebanking.payment.repository;

import com.ebanking.payment.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionPagingRepo extends PagingAndSortingRepository<Transaction, Long> {

    Page<Transaction> findByValueDateBetween(
            Date valueDateStart,
            Date valueDateEnd,
            Pageable pageable
    );
}
