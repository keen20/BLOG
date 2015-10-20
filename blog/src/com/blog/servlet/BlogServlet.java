package com.blog.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.blog.DB.DBHelper;
import com.blog.model.User;

public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public BlogServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect("/blog/login.jsp");
		}else{
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String  category = request.getParameter("category");
		int result = 0;
			//Connection conn;
			try {
				//conn = ds.getConnection();
				String sql = "insert into blog (title,content,category_id,created_time) values(?,?,?,now())";
				String params[] ={title,content,category}; 
				QueryRunner qr = DBHelper.getQueryRunner();
				result = qr.update(sql,params);
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String message = "";
			if(result == 1){
				message = "添加博文成功";
			}
			else{
				message = "添加博文失败";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/admin/addBlogResult.jsp").forward(request,response);
		}
	}
	public void init() throws ServletException {
	}

}
