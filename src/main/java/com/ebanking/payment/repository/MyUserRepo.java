package com.ebanking.payment.repository;

import com.ebanking.payment.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepo extends JpaRepository<MyUser, Long> {
    MyUser findByUsername(String username);
}
