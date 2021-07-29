package com.db.spring.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//mark class as an Entity
@Entity

//define class name as Table name
@Table
public class User {

	// Defining employee id as primary key
	@Id
	@Column
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accNo;
	@Column
	private String name;
	@Column
	private double balance;

	public User() {
		super();
	}

	public User(int accNo, String name, double balance) {
		super();
		this.accNo = accNo;
		this.name = name;
		this.balance = balance;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "User [accNo=" + accNo + ", name=" + name + ", balance=" + balance + "]";
	}

}
