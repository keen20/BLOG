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
import com.blog.model.User;

public class PreAddBlogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect("/blog/admin/login.jsp");
		}else{
		list(request,response);
		}
	}
	@SuppressWarnings("unchecked")
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categorys = null;
		String sql = "select id,name,level from category order by level desc,id desc";
		QueryRunner qr = DBHelper.getQueryRunner();
		try {
			categorys = (List<Category>) qr.query(sql, new BeanListHandler(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("categorys",categorys);
		request.getRequestDispatcher("/admin/addBlog.jsp").forward(request, response);
	}
	}


