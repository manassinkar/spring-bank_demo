package com.db.spring.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.spring.bank.model.User;
import com.db.spring.bank.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User getUserByAccNo(int accNo) {
		try {
			return userRepository.findById(accNo).get();			
		} catch (Exception e) {
			return null;
		}
	}
	
	public User createUser(User user) {
		user.setBalance(0);
		return userRepository.save(user);
//		return userRepository.findById(user.getAccNo()).get().getAccNo();
	}
	
	public Response deposit(int accNo, double amount) {
		try {
			User user = userRepository.getById(accNo);
			double newBalance = user.getBalance() + amount;
			user.setBalance(newBalance);
			userRepository.save(user);
			Response resp = new Response("SUCCESS", userRepository.findById(accNo).get().getBalance());
			return resp;			
		} catch (Exception e) {
			return null;
		}
	}
	
	public Response withdraw(int accNo, double amount) {
		try {
			User user = userRepository.getById(accNo);
			double currBalance = user.getBalance();
			if (currBalance > 0 && amount > 0 && currBalance > amount) {
				double newBalance = user.getBalance() - amount;
				user.setBalance(newBalance);			
				userRepository.save(user);
				Response resp = new Response("SUCCESS", userRepository.findById(accNo).get().getBalance());
				return resp;
			} else {
				Response resp = new Response("FAILURE", userRepository.findById(accNo).get().getBalance());
				return resp;
			}			
		} catch (Exception e) {
			return null;
		}
	}
	
	public double checkBalance(int accNo) {
		try {
			return userRepository.findById(accNo).get().getBalance();			
		} catch (Exception e) {
			return -1;
		}
	}

}
