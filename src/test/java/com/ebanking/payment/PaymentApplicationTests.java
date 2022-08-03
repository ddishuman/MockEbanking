package com.ebanking.payment;

import com.ebanking.payment.model.Transaction;
import com.ebanking.payment.repository.TransactionRepo;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;


//@RunWith(SpringRunner.class)
//@ContextConfiguration(locations={"classpath:application-context.xml"})
@ExtendWith(SpringExtension.class)
@DataJpaTest
class PaymentApplicationTests {
	@Autowired
	private TransactionRepo repo;

	@Test
	void contextLoads() {
	}

	@Test
	void findTransactionsBetweenDates() throws ParseException {

		// start date
//		LocalDateTime startDate = LocalDateTime.of(2022,8, 2, 14, 49, 0);
//		// end date
//		LocalDateTime endDate = LocalDateTime.of(2022,8, 2, 14, 51, 0);

		List<Transaction> result = repo.findByValueDateBetween(
				new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-01"),
				new SimpleDateFormat("yyyy-MM-dd").parse("2022-08-02"));

		result.forEach((txn) ->{
			System.out.println(txn.tid);
			System.out.println(txn.amount);
		});
		//assertEquals(1000, result.size());
	}


}
