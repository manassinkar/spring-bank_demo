package com.db.spring.bank.service;

public class Response {
	public String status;
	public double balance;
	
	public Response(String status, double balance) {
		this.status = status;
		this.balance = balance;
	}
}
