package com.blog.servlet;

import java.io.IOException;
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

public class PostEditBlogServlet extends HttpServlet {
	private static final long serialVersionUID = -136961912199637006L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null){
			response.sendRedirect("/blog/admin/login.jsp");
		}else{
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String id = request.getParameter("id");
			String categoryId=request.getParameter("category");

			String sql = "update blog set title=?,content=?,category_id=? where id=?";
			String params[] = { title, content, categoryId,id };

			int result = 0;
			String message = "";
			DataSource ds = null;
			try {
				QueryRunner qr = DBHelper.getQueryRunner();
				result = qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (result == 1) {
				message = "您修改成功了！";
			} else {
				message = "您修改失败了！";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher("/admin/result.jsp").forward(request,
					response);
		}
	}
}