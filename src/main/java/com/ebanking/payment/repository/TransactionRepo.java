package com.ebanking.payment.repository;

import com.ebanking.payment.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByValueDateBetween(
            Date valueDateStart,
            Date valueDateEnd
    );

//    Page<Transaction> findByValueDateBetweenAndPaging(
//            Date valueDateStart,
//            Date valueDateEnd,
//            Pageable pageable
//    );
}
