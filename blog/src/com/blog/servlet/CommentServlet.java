package com.blog.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.blog.DB.DBHelper;
import com.blog.model.Category;
import com.blog.model.Comment;
import com.blog.model.User;

public class CommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method.equals("add")){
			add(request, response);
		}else if (method.equals("delete")) {
			delete(request, response);
		} else if (method.equals("edit")) {
			preEdit(request, response);
		}else if(method.equals("list")){
			list(request,response);
		}else if(method.equals("update")){
			postEdit(request, response);
		}else {
			list(request,response);
		}
	}
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String blog_id =  request.getParameter("blog_id");
		String username = request.getParameter("username");
		String content = request.getParameter("content");
		int result = 0;
		if(username == null||"".equals(username)){
			username = "匿名";
		}
		String sql = "insert into `comment`(blog_id,username,content,created_time) values (?,?,?,now())";
		String[] params = {blog_id,username,content};
		QueryRunner qr = DBHelper.getQueryRunner();
		try {
			result = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message = null;
		if(result == 1){
			//request.getRequestDispatcher("/servlet/GetBlogServlet?id=" + blog_id).forward(request, response);
			response.sendRedirect("/blog/servlet/HomeServlet?method=get&id=" + blog_id);
		}else{
			message = "你评论失败了，请重试！";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		}
		
	}
	@SuppressWarnings("unchecked")
	public void list(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		//String blog_id = request.getParameter("blog_id");
		//String sql = "select id,blog_id,username,content,created_time from comment where blog_id=" + blog_id +"order by id desc";
		String sql = "select id,blog_id blogid,username,content,created_time from comment";
		List<Comment> list = null;
		QueryRunner qr = DBHelper.getQueryRunner();
		try {
			list =(List<Comment>) qr.query(sql,new BeanListHandler(Comment.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("list",list);
		request.getRequestDispatcher("/admin/adminCommentList.jsp").forward(request,response);
	}
	public void delete(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		String id = request.getParameter("id");
		String sql = "delete from comment where id =" + id;
		QueryRunner qr = DBHelper.getQueryRunner();
		try {
			qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		list(request, response);
	}
	
	public void preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Comment> comments = null;
		Comment comment = null;
		String id = request.getParameter("id");
		String sql = "select id,username,content from comment where id = " +id;
		QueryRunner qr = DBHelper.getQueryRunner();
		try {
			comments = (List<Comment>) qr.query(sql,new BeanListHandler(Comment.class));
			comment = comments.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("comment",comment);
		request.getRequestDispatcher("/admin/editComment.jsp").forward(request, response);
	}

	public void postEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String id = request.getParameter("id");
		String content = request.getParameter("content");
		String[] params = {id,username,content}; 
		int result = 0;
		String sql = "update comment set id=?,username=?, content=? where id="+id;
		QueryRunner qr = DBHelper.getQueryRunner();
		try {
			result = qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String message = null;
		if(result == 1){
			message = "您更新成功了";
		}else{
			message = "你更新失败了";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/admin/result.jsp").forward(request, response);
	}
}
