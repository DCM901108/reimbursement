package com.revature.models;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int ers_users_id;
	private String ers_username;
	private String ers_password;
	private String user_first_name;
	private String user_last_name;
	private String user_email;
	private int user_type;
	
	/*
	 * 
	 * 	private int ers_users_id;
		private String ers_username;
		private String ers_password;
		private String user_first_name;
		private String user_last_name;
		private String user_email;
		private int user_role_id;
	 * 
	 * 
	 */
	
	public User() {
		
	}

	public int getErs_users_id() {
		return ers_users_id;
	}

	public void setErs_users_id(int ers_users_id) {
		this.ers_users_id = ers_users_id;
	}

	public String getErs_username() {
		return ers_username;
	}

	public void setErs_username(String ers_username) {
		this.ers_username = ers_username;
	}

	public String getErs_password() {
		return ers_password;
	}

	public void setErs_password(String ers_password) {
		this.ers_password = ers_password;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}

	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public static long getSerialVersionId() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [ers_users_id=" + ers_users_id + ", ers_username="
				+ ers_username + ", ers_password=" + ers_password
				+ ", user_first_name=" + user_first_name + ", user_last_name="
				+ user_last_name + ", user_email=" + user_email + ", user_type="
				+ user_type + "]";
	}
	
}
