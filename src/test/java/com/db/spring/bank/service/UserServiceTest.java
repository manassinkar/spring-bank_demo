package com.db.spring.bank.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.spring.bank.model.User;
import com.db.spring.bank.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserService service;

	@Mock
	UserRepository repository;

	public static List<User> userList;

	@BeforeAll
	public static void createEmpData() {
		userList = new ArrayList<>();
		userList.add(new User(101, "Sonu", 1000.0));
		userList.add(new User(102, "Monu", 2000.0));
		userList.add(new User(103, "Tonu", 1500.0));
	}

	@Test
	public void testrSaveOrUpdate() {
		service.createUser(userList.get(2));
		verify(repository, times(1)).save(userList.get(2));
	}

	@AfterAll
	public static void nullifyEmpData() {
		userList = null;
	}
}
