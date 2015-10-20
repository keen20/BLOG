package com.blog.model;

public class User {
private int id;
private String password;
private String username;
public int getId() {
	return id;
}
public String getPassword() {
	return password;
}
public String getUsername() {
	return username;
}
public void setId(int id) {
	this.id = id;
}
public void setPassword(String password) {
	this.password = password;
}
public void setUsername(String username) {
	this.username = username;
}
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password="
			+ password + "]";
}
}
