package com.stockTrading.stockTradingSystem.controller;

import com.stockTrading.stockTradingSystem.model.CashTransaction;
import com.stockTrading.stockTradingSystem.model.Response;
import com.stockTrading.stockTradingSystem.model.UserDtl;
import com.stockTrading.stockTradingSystem.model.request.LoginRequest;
import com.stockTrading.stockTradingSystem.service.CashTransactionService;
import com.stockTrading.stockTradingSystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user")
public class UserController {


    private UserService userService;

    @Autowired
    private CashTransactionService cashTransactionService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response add(@RequestBody UserDtl user){
        System.out.println("UserRestController:  post request /add");
        user.setCreationTime(System.currentTimeMillis());
        return  userService.saveUser(user);
    }

    @PostMapping (path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response get(@RequestBody LoginRequest loginRequest){
        System.out.println("UserRestController:  post request /login");
        return userService.getUser(loginRequest.getUsername(), loginRequest.getPwd());
    }


    //Cash Transaction

    @PostMapping(path="addtransaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addTransaction(@RequestBody CashTransaction cashTransaction){
        return cashTransactionService.addCashTransaction(cashTransaction);
    }

}