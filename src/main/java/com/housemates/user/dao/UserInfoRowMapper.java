package com.housemates.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.housemates.user.model.UserInfoBean;


public class UserInfoRowMapper implements RowMapper<UserInfoBean>
{

	public UserInfoBean mapRow(ResultSet rs, int count) throws SQLException 
	{
		UserInfoBean userInfo= new UserInfoBean();
		userInfo.setUserId(rs.getString("userId"));
		userInfo.setUserName(rs.getString("userName"));
		userInfo.setImagePath(rs.getString("image"));
		return userInfo;
	}

}
