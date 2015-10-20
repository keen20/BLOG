package com.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.blog.model.User;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method == null){
			method="";
		}else if(method.equals("login")){
			login(request, response);
		}else if(method.equals("logout")){
			logout(request, response);
		}else if(method.equals("change")){
			changePassword(request,response);
		}else{
			method="";
		}
	}
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String[] params = {username,password};
		List<User> users = null;
		User user = null;
		String sql = "select username,password from users where username=? and password=?";
		QueryRunner qr = DBHelper.getQueryRunner();
		try {
			users = (List<User>) qr.query(sql, params, new BeanListHandler(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(users.size() != 0){
		user = users.get(0);
		HttpSession session = request.getSession();
		session.setAttribute("user",user);
		request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
		}else{
			request.setAttribute("message","用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session =request.getSession();
		session.invalidate();
		response.sendRedirect("/blog/login.jsp");
	}
	
	public void changePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String reNewPassword = request.getParameter("reNewPassword");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (!user.getPassword().equals(oldPassword)) {
			request.setAttribute("message", "您输入的原密码错误！");
			request.getRequestDispatcher("/admin/result.jsp").forward(request,
					response);
		} else if (newPassword.equals(reNewPassword)) {
			String sql = "update users set password=? where username=? and password=?";
			String[] params = {newPassword,user.getUsername(),user.getPassword()};
			//System.out.println(newPassword+user.getUsername()+user.getPassword());
			QueryRunner qr = DBHelper.getQueryRunner();
			try {
				qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("message", "修改成功！");
			request.getRequestDispatcher("/admin/admin.jsp").forward(request,
					response);	
			//response.sendRedirect("/blog/admin/admin.jsp");
		} else {
			request.setAttribute("message", "您输入的两次新密码不相同！");
			request.getRequestDispatcher("/admin/result.jsp").forward(request,
					response);		
			
		}
	}
}
