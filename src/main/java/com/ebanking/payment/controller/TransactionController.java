package com.ebanking.payment.controller;

import com.ebanking.payment.dto.APIResponse;
import com.ebanking.payment.model.AuthenticationRequest;
import com.ebanking.payment.model.Transaction;
import com.ebanking.payment.repository.TransactionPagingRepo;
import com.ebanking.payment.repository.TransactionRepo;
//import com.ritaja.xchangerate.util.Currency;
import com.ebanking.payment.repository.UserAccountRepo;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TransactionController {
    // test
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private MyUserService userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtTokenUtil;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionPagingRepo transactionPagingRepo;

    @Autowired
    private UserAccountRepo userAccountRepo;

    //private CurrencyConverter converter;

//    public TransactionController() {
//        this.converter = new CurrencyConverterBuilder()
//            .strategy(Strategy.YAHOO_FINANCE_FILESTORE)
//            .accessKey("zDtTadI36icWCr3Cqz1hMTYVUmXoDpg4")
//            .buildConverter();
//    }

    @RequestMapping("/hello2")
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "/getTrans", method = RequestMethod.GET)
    public APIResponse<List<Transaction>> readTransactionsFromKafka()
            throws Exception {
        try {
            List<Transaction> result = transactionRepo.findByValueDateBetween(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-08-03 07:00:00"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-08-03 14:00:00"));


            // Setting URL
//            String url_str = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD";
//
//            // Making Request
//            URL url = new URL(url_str);
//            HttpURLConnection request = (HttpURLConnection) url.openConnection();
//            request.connect();

            // Convert to JSON
//            JsonParser jp = new JsonParser();
//            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
//            JsonObject jsonobj = root.getAsJsonObject();

            // Accessing object
//            String req_result = jsonobj.get("result").getAsString();
//            System.out.println("req_result = " + req_result);
            Double debit = 1.0; //= converter.convertCurrency(new BigDecimal("100"), Currency.USD, Currency.EUR);
            // TODO
            Double credit = 12452.0;
            // log
            System.out.println("Trans = " + result.size());
            System.out.println("debit = " + debit);
            System.out.println("credit = " + credit);

            result.forEach((txn) ->{
                System.out.println(txn.tid);
                System.out.println(txn.getAmount());
            });

            // TODO
            return new APIResponse<>(credit, debit, result);
        }
        catch (Exception e) {
            throw new Exception("readTransactionsFromKafka exception: ", e);
        }
    }

    @RequestMapping(value = "/pagination/getTrans/{page}/{size}", method = RequestMethod.GET)
    public APIResponse<Page<Transaction>> readTransactionsWithPagination(@PathVariable int page,
                                                                         @PathVariable int size)
            throws Exception {

        Map<String, Double> currMap = new HashMap<>();

        try {
            List<Transaction> result = transactionRepo.findByValueDateBetween(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-08-03 07:00:00"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-08-03 14:00:00"));

            currMap = result.stream()
                    .collect(Collectors.groupingBy(
                            (txn -> txn.currency),
                            Collectors.summingDouble(Transaction::getAmount)
                    ));

            Page<Transaction> resultWithPage = transactionPagingRepo.findByValueDateBetween(
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-08-03 07:00:00"),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-08-03 14:00:00"),
                    PageRequest.of(page, size));

            // Setting URL
//            String url_str = "https://v6.exchangerate-api.com/v6/YOUR-API-KEY/latest/USD";
//
//            // Making Request
//            URL url = new URL(url_str);
//            HttpURLConnection request = (HttpURLConnection) url.openConnection();
//            request.connect();

            // Convert to JSON
//            JsonParser jp = new JsonParser();
//            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
//            JsonObject jsonobj = root.getAsJsonObject();

            // Accessing object
//            String req_result = jsonobj.get("result").getAsString();
//            System.out.println("req_result = " + req_result);

            //### exchange rate count by myself
//            GBP,
//                    CHF,
//                    USD;
            Double debit = currMap.get("GBP") * 1.22 +
                           currMap.get("CHF") * 1.04 +
                           currMap.get("USD"); //= converter.convertCurrency(new BigDecimal("100"), Currency.USD, Currency.EUR);
            // TODO
            Double credit = userAccountRepo.findByUsername("arnold").credit;
            //### log

            System.out.println("debit = " + debit);
            System.out.println("credit = " + credit);

            resultWithPage.forEach((txn) ->{
                System.out.println(txn.tid);
                System.out.println(txn.getAmount());
            });

            // TODO
            return new APIResponse<>(credit, debit, resultWithPage);
        }
        catch (Exception e) {
            throw new Exception("readTransactionsFromKafka exception: ", e);
        }
    }




    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
        throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//
//        final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
        return null;
    }

}
