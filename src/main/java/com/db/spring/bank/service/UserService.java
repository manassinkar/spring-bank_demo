package com.db.spring.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.spring.bank.model.User;
import com.db.spring.bank.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User getUserByAccNo(int acc_no) {
		return userRepository.findById(acc_no).get();
	}
	
	public int createUser(int acc_no, String name) {
		User user = new User(acc_no, name, 0);
		userRepository.save(user);
		return userRepository.findById(acc_no).get().getAccNo();
	}
	
	public Response deposit(int acc_no, double amount) {
		User user = userRepository.getById(acc_no);
		double newBalance = user.getBalance() + amount;
		user.setBalance(newBalance);
		userRepository.save(user);
		Response resp = new Response("SUCCESS", userRepository.findById(acc_no).get().getBalance());
		return resp;
	}
	
	public Response withdraw(int acc_no, double amount) {
		User user = userRepository.getById(acc_no);
		double currBalance = user.getBalance();
		if (currBalance > 0 && amount > 0 && currBalance > amount) {
			double newBalance = user.getBalance() - amount;
			user.setBalance(newBalance);			
			userRepository.save(user);
			Response resp = new Response("SUCCESS", userRepository.findById(acc_no).get().getBalance());
			return resp;
		} else {
			Response resp = new Response("FAILURE", userRepository.findById(acc_no).get().getBalance());
			return resp;
		}
	}
	
	public double checkBalance(int acc_no) {
		return userRepository.findById(acc_no).get().getBalance();
	}

}
