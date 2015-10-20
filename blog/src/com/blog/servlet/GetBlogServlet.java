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
import com.blog.model.Blog;
import com.blog.model.Comment;
import com.blog.model.User;
public class GetBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		QueryRunner qr =DBHelper.getQueryRunner();
		String sql = "select id,title,content,created_time from blog where id =" + id;
		List<Blog> list = null;
		
		Blog blog  = null;
		try {
				list = (List<Blog>)qr.query(sql, new BeanListHandler(Blog.class));
				blog = (Blog) list.get(0);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		 
		sql = "select id,blog_id,username,content,created_time from comment where blog_id = " + id + " order by id desc";
		 List<Comment> CommentList = null;
		try {
			CommentList = (List<Comment>) qr.query(sql, new BeanListHandler(Comment.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("CommentList",CommentList);
		System.out.println(id);
		System.out.println(CommentList);
		request.setAttribute("blog", blog);
		request.getRequestDispatcher("/displayBlog.jsp").forward(request,response);
	}
}