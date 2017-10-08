package com.housemates.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.housemates.user.dao.UserInfoDAO;
import com.housemates.user.model.UserCredentials;
import com.housemates.user.model.UserDetails;
import com.housemates.user.model.UserInfoBean;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@EnableWebMvc
@Configuration
@RestController
public class UserInfoController 
{
	@Autowired
	UserInfoDAO userinfodao;
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public UserInfoBean getUserInfo(@RequestBody UserCredentials userCredentials)
	{
		UserInfoBean userInfo= userinfodao.getDetails(userCredentials);
		return userInfo;
	}
	@RequestMapping(value="/signup",method= RequestMethod.POST)
	public String postUserDetails(@RequestBody UserDetails userDetails)
	{
			return userinfodao.insertUserDetails(userDetails);		
	}
}
