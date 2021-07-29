package com.db.spring.bank.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.db.spring.bank.model.User;
import com.db.spring.bank.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest({ UserController.class })
public class UserControllerTest {
	private static Logger LOG = LoggerFactory.getLogger(UserControllerTest.class);
	
	@MockBean
	UserService userService;

	@Autowired
	MockMvc mockMvc;

	public static List<User> userList;

	@BeforeAll
	public static void createEmpData() {
		userList = new ArrayList<>();
		userList.add(new User(101, "Sonu", 1000.0));
		userList.add(new User(102, "Monu", 2000.0));
		userList.add(new User(103, "Tonu", 1500.0));
		LOG.info(userList.toString());
	}

	@Test
	public void testGetUserByAccNo() throws Exception {
		LOG.info("testGetUserById using andExpect()");
		Mockito.when(userService.getUserByAccNo(101)).thenReturn(userList.get(0));
		mockMvc.perform(get("/User/101")).andExpect(status().isOk()).andExpect(jsonPath("$.accNo", Matchers.is(101)))
				.andExpect(jsonPath("$.name", Matchers.is("Sonu"))).andExpect(jsonPath("$.balance", Matchers.is(1000.0)));
	}

	@AfterAll
	public static void nullifyEmpData() {
		LOG.info("nullifyEmpData");
		userList = null;
	}

}
