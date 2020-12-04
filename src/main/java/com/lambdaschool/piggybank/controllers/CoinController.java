package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {

    //Connect controller + db
    @Autowired
    CoinRepository coinrepo;

    // http://localhost:2019/total
    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> getTotal() {
        //list to hold all coins
        List<Coin> coinsList = new ArrayList<>();
        coinrepo.findAll().iterator().forEachRemaining(coinsList::add);

        System.out.println();

        return new ResponseEntity<>(coinsList, HttpStatus.OK);
    }
}
