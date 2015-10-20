package com.blog.servlet;
import java.io.IOException;
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
import com.blog.model.User;
public class AdminBlogListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect("/blog/login.jsp");
		}else{
		try {
				String sql = "select id,category_id,title,content,created_time from blog";
				QueryRunner qr = DBHelper.getQueryRunner();
				@SuppressWarnings("unchecked")
				List<Blog> list = (List<Blog>)qr.query(sql, new BeanListHandler(Blog.class));
				request.setAttribute("list", list);
				request.getRequestDispatcher("/admin/adminBlog.jsp").forward(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}