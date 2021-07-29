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
	private int acc_no;
	@Column
	private String name;
	@Column
	private double balance;

	public User() {
		super();
	}

	public User(int acc_no, String name, double balance) {
		super();
		this.acc_no = acc_no;
		this.name = name;
		this.balance = balance;
	}

	public int getAccNo() {
		return acc_no;
	}

	public void setAccNo(int acc_no) {
		this.acc_no = acc_no;
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
		return "Employee [acc_no=" + acc_no + ", name=" + name + ", balance=" + balance + "]";
	}

}
