package com.blog.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.blog.DB.DBHelper;
import com.blog.model.Blog;
import com.blog.model.Category;
import com.blog.model.User;

public class PreEditBlogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect("/blog/admin/login.jsp");
		}else{
		String  id = request.getParameter("id");
		String sql = "select id,title,content from blog where id = " + id;
		list(request,response);
		try {
			QueryRunner qr = DBHelper.getQueryRunner();
			@SuppressWarnings("unchecked")
			List<Blog> list = (List<Blog>)qr.query(sql,new BeanListHandler(Blog.class));
			Blog blog = list.get(0);
			request.setAttribute("blog",blog);
			request.getRequestDispatcher("/admin/editBlog.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	}
	}


