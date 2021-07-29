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
	public int acc_no;
	public double amount;
}

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/User/{acc_no}")
	private ResponseEntity<User> getUser(@PathVariable("acc_no") int acc_no) {
		System.out.println("Test123");
		User user = userService.getUserByAccNo(acc_no);
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
	private int createUser(@RequestBody User user) {
		System.out.println("Test123");
		return userService.createUser(user.getAccNo(), user.getName());
	}
	
	@PostMapping("/Deposit")
	private ResponseEntity<Double> deposit(@RequestBody DepositAndWithdrawRequest data) {
		Response resp = userService.deposit(data.acc_no, data.amount);
		if (resp.status == "SUCCESS") {
			return new ResponseEntity<Double>(resp.balance, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(resp.balance, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/Withdraw")
	private ResponseEntity<Double> withdraw(@RequestBody DepositAndWithdrawRequest data) {
		Response resp = userService.withdraw(data.acc_no, data.amount);
		if (resp.status == "SUCCESS") {
			return new ResponseEntity<Double>(resp.balance, HttpStatus.OK);
		} else {
			return new ResponseEntity<Double>(resp.balance, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/CheckBalance/{acc_no}")
	private double checkBalance(@PathVariable("acc_no") int acc_no) {
		return userService.checkBalance(acc_no);
	}
}
