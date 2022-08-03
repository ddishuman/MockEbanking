package com.ebanking.payment.repository;

import com.ebanking.payment.model.UserBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends JpaRepository<UserBankAccount, Long> {
    UserBankAccount findByUsername(String username);
}
