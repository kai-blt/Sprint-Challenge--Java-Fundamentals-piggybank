package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
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
        //create list and store all coins
        List<Coin> coinsList = new ArrayList<>();
        coinrepo.findAll().iterator().forEachRemaining(coinsList::add);

        //value to store total
        double total = 0.0;

        //iterate
        for (Coin c: coinsList) {
            if (c.getQuantity() > 1) {
                System.out.println(c.getQuantity() + " " + c.getNameplural());
            } else {
                System.out.println(c.getQuantity() + " " + c.getName());
            }
            total += (c.getQuantity() * c.getValue());
        }

        System.out.println("The piggy bank holds " + total);
        return new ResponseEntity<>(HttpStatus.OK);
    }



    // http://localhost:2019/money/{amount}
    @GetMapping(value = "/money/{amount}", produces = "application/json")
    public ResponseEntity<?> removeCoins(@PathVariable double amount) {

        DecimalFormat df = new DecimalFormat("$#.#");

       //create list to hold coins
        List<Coin> coinsList = new ArrayList<>();
        coinrepo.findAll().iterator().forEachRemaining(coinsList::add);

        //value to store total
        double amountToRemove = amount;
        double total = 0.0;

        //get initial total balance
        for (Coin c: coinsList) {
            total += (c.getQuantity() * c.getValue());
        }

        //check if user entered amount is greater than bank balance
        //if so, print Money not available
        if (amountToRemove > total) {
            System.out.println("Money not available");
        } else {
            //reset total counter
            total = 0.0;
            //iterate
            for (Coin c : coinsList) {
                while (c.getQuantity() > 0 && amountToRemove >= 0.0) {
                    //get value of a single coin
                    double value = 1 * c.getValue();
                    //System.out.println("value " + value);

                    //if less than amt to remove, decrement
                    if (value <= amountToRemove) {
                        //System.out.println("amt to remove : " + amountToRemove);
                        amountToRemove -= value;
                        int currQuantity = c.getQuantity();
                        c.setQuantity(currQuantity - 1);
                    } else {
                        //If the value of a single coin is too much
                        //skip to next currency value
                        break;
                    }
                }
            }

            //final printout
            for (Coin c: coinsList) {
                if (c.getQuantity() > 1) {
                    System.out.println(df.format(c.getQuantity() * c.getValue()) + " " + c.getNameplural());
                } else if (c.getQuantity() == 1){
                    System.out.println(df.format(c.getQuantity() * c.getValue())+ " " + c.getName());
                }
                total += (c.getQuantity() * c.getValue());
            }
            System.out.println("The piggy bank holds " + df.format(total));
        }



        return new ResponseEntity<>(HttpStatus.OK);
    }
}
