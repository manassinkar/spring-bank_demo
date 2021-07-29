package com.db.spring.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.spring.bank.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
