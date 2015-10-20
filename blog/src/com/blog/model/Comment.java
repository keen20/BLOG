package com.blog.model;

import java.util.Date;

public class Comment {
private String content;
private int id;
private String username;
private Date created_time;
private int blogid;
public int getBlogid() {
	return blogid;
}
public void setBlogid(int blogid) {
	this.blogid = blogid;
}
public Date getCreated_time() {
	return created_time;
}
public void setCreated_time(Date created_time) {
	this.created_time = created_time;
}
public String getContent() {
	return content;
}
public int getId() {
	return id;
}
public String getUsername() {
	return username;
}
public void setContent(String content) {
	this.content = content;
}
public void setId(int id) {
	this.id = id;
}
public void setUsername(String username) {
	this.username = username;
}
@Override
public String toString() {
	return "Comment [content=" + content + ", id=" + id + ", username="
			+ username + ", created_time=" + created_time + "]";
}

}
