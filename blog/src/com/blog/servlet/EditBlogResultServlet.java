package com.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.blog.model.User;

public class EditBlogResultServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect("/blog/admin/login.jsp");
		}else{
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String category = request.getParameter("category");
		String id = request.getParameter("id");
		String sql = "update blog set title=?,category_id=?,content=? where id=?";
		String[] params = {title,category,content,id};
		DataSource ds = null;
		int result = 0;
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/mysqlds");
		} catch (Exception e) {
			System.out.print("获取数据源失败");
		}
		
		try {
			QueryRunner qr = new QueryRunner(ds);
			result = qr.update(sql,params);
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
	
}
