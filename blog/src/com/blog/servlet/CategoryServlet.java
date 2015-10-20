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

public class CategoryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
		String method = request.getParameter("method");
		if (method.equals("add")) {
			add(request, response);
		} else if (method.equals("delete")) {
			delete(request, response);
		} else if (method.equals("edit")) {
			preEdit(request, response);
		}else if(method.equals("list")){
			list(request,response);
		}else if(method.equals("postEdit")){
			postEdit(request, response);
		}else {
			list(request,response);
		}
		}
	}
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String level = request.getParameter("level");
		String sql = "insert into category (name,level) values (?,?)";
		String param[] = { name, level };
		QueryRunner qr = DBHelper.getQueryRunner();
		int result = 0;
		String message = "";
		try {
			result = qr.update(sql, param);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (result == 1) {
			message = "插入成功";
		} else {
			message = "插入失败";
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/admin/result.jsp").forward(request, response);
	}
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			String sql = "delete from category where id=" + id;
			QueryRunner qr = DBHelper.getQueryRunner();
			qr.update(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		list(request,response);
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
		request.getRequestDispatcher("/admin/adminCategoryList.jsp").forward(request, response);
	}
	public void preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categorys = null;
		Category category = null;
		String id = request.getParameter("id");
		String sql = "select id,name,level from category where id = " +id;
		QueryRunner qr = DBHelper.getQueryRunner();
		try {
			categorys = (List<Category>) qr.query(sql,new BeanListHandler(Category.class));
			category = categorys.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("category",category);
		request.getRequestDispatcher("/admin/editCategory.jsp").forward(request, response);
	}
	public void postEdit(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String level = request.getParameter("level");
		String[] params = {name,level,id}; 
		int result = 0;
		String sql = "update category set name=?,level=? where id=?";
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
