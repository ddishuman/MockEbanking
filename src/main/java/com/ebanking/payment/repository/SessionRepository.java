package com.ebanking.payment.repository;

import com.ebanking.payment.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
