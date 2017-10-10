package com.housemates.user.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.housemates.user.dao.UserInfoDAO;
import com.housemates.user.model.UserCredentials;
import com.housemates.user.model.UserDetails;
import com.housemates.user.model.UserInfoBean;

import java.io.*;
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
	String filePath;
	@RequestMapping(value="/login",method= RequestMethod.POST)
	public UserInfoBean getUserInfo(@RequestBody UserCredentials userCredentials)
	{
		UserInfoBean userInfo= userinfodao.getDetails(userCredentials);
		return userInfo;
	}
	@RequestMapping(value="/signup",method= RequestMethod.POST)
	public String postUserDetails(@RequestBody UserDetails userDetails)
	{
		return userinfodao.insertUserDetails(userDetails,filePath);	
	}
	@RequestMapping(value="/uploadimage",method= RequestMethod.POST)
	public String postImage(@RequestParam("file") MultipartFile file)
	{
			if(!file.isEmpty())
			{
				try
				{
					byte[] bytes = file.getBytes();
					String rootPath = "D:\\Movies";
					File dir = new File(rootPath + File.separator + "tmpFiles");
					if (!dir.exists())
						dir.mkdirs();
					File serverFile = new File(dir.getAbsolutePath()+ File.separator + file.getOriginalFilename());
					filePath=dir.getAbsolutePath()+ File.separator + file.getOriginalFilename();
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
				}
				catch(Exception e)
				{
					
				}
				
				
			}
			return filePath;
				
	}
}
