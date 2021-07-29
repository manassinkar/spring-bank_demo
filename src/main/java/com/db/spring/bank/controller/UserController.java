package com.db.spring.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.spring.bank.model.User;
import com.db.spring.bank.service.Response;
import com.db.spring.bank.service.UserService;

class DepositAndWithdrawRequest {
	public int accNo;
	public double amount;
}

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/User/{accNo}")
	private ResponseEntity<User> getUser(@PathVariable("accNo") int accNo) {
		User user = userService.getUserByAccNo(accNo);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/User")
	private User createUser(@RequestBody User user) {
		System.out.println(user.toString());
		return userService.createUser(user);
	}
	
	@PostMapping("/Deposit")
	private ResponseEntity<Double> deposit(@RequestBody DepositAndWithdrawRequest data) {
		Response resp = userService.deposit(data.accNo, data.amount);
		if (resp != null && resp.status == "SUCCESS") {
			return new ResponseEntity<Double>(resp.balance, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(-1.0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/Withdraw")
	private ResponseEntity<Double> withdraw(@RequestBody DepositAndWithdrawRequest data) {
		Response resp = userService.withdraw(data.accNo, data.amount);
		if (resp != null && resp.status == "SUCCESS") {
			return new ResponseEntity<Double>(resp.balance, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(-1.0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/CheckBalance/{accNo}")
	private double checkBalance(@PathVariable("accNo") int accNo) {
		return userService.checkBalance(accNo);
	}
}
