<%@ page language="java" import="java.util.List,com.blog.model.Blog" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>显示博客列表</title>
</head>
<%
List<Blog> list = (List<Blog>)request.getAttribute("list");
Blog blog = null;
 %>
<body>
<%for(int i =0; i < list.size(); i++){
	blog = list.get(i);
 %>
<table width="540" border="0" cellspacing="2" cellpadding="0">
  <tr>
    <td width="540"><%=blog.getCreated_time() %></td>
  </tr>
  <tr>
    <td><a href="http://localhost:8080/blog/servlet/GetBlogServlet?id=<%=blog.getId()%>"><%=blog.getTitle() %></a></td>
  </tr>
  <tr>
    <td><%
     String source = blog.getContent();
     if(source.length()<100){
     out.print(source.trim());
     }else{
     String piece = source.substring(1,100).trim();
     out.print(piece + "..."); 
     }
    
    %></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<%} %>
</body>
</html>
