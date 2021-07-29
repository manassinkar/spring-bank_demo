package com.db.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.spring.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
