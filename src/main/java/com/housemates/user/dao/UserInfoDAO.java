package com.housemates.user.dao;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.housemates.user.model.UserCredentials;
import com.housemates.user.model.UserDetails;
import com.housemates.user.model.UserInfoBean;

public class UserInfoDAO 
{
	JdbcTemplate jdbcTemplate;
	public UserInfoDAO(DataSource dataSource) 
	{
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public UserInfoBean getDetails(UserCredentials userCredentials)
	{
		UserInfoBean userInfo=new UserInfoBean();
		try
		{
			//userInfo=jdbcTemplate.queryForObject("select * from t_user_info where userName='"+userCredentials.getUserName()+"' and password='"+userCredentials.getPassword()+"'", new Object[] {}, new UserInfoRowMapper());
			userInfo=jdbcTemplate.queryForObject("select * from t_user_info where userName=? and password=?", new Object[] {userCredentials.getUserName(),userCredentials.getPassword()}, new UserInfoRowMapper());
			userInfo.setMetadata("Success"); 
			return userInfo;
		}
		catch(Exception e)
		{
			userInfo.setMetadata("Invalid User Name or password");
			return userInfo;
		}
		
	}
	public String insertUserDetails(UserDetails userDetails,String filePath)
	{
		UserInfoBean userInfo=new UserInfoBean();
		try
		{
			if(userInfo.getUserName().isEmpty())
			{
				String sql="insert into t_user_info (phoneNo,userName,password,IDProofNumber,IdProof,nativeCity,image,emailId,createdDate) values(?,?,?,?,?,?,?,?,CURDATE())";
				jdbcTemplate.update(sql,new Object[] {userDetails.getMobileNumber(),userDetails.getUserName(),userDetails.getPassword(),userDetails.getIdProofNumber(),userDetails.getIdProof(),userDetails.getCity(),filePath,userDetails.getEmailId()});
				return "User added Successfully";
			}
			else if(userInfo.getUserName().equals(userDetails.getUserName()))
			{
				return "User already exists";
			} 
			else
			{
				return "Failed";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failed";
		}
	}
	public UserInfoBean validateUser(UserDetails userDetails)
	{
		UserInfoBean userInfo=new UserInfoBean();
		try
		{
			//userInfo=jdbcTemplate.queryForObject("select * from t_user_info where userName='"+userCredentials.getUserName()+"' and password='"+userCredentials.getPassword()+"'", new Object[] {}, new UserInfoRowMapper());
			userInfo=jdbcTemplate.queryForObject("select * from t_user_info where userName=?", new Object[] {userDetails.getUserName()}, new UserInfoRowMapper());
			userInfo.setMetadata("Success"); 
			return userInfo;
		}
		catch(Exception e)
		{
			userInfo.setUserName("");
			userInfo.setMetadata("Invalid User Name or password");
			return userInfo;
		}
		
	}
}
