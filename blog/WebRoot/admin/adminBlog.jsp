<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.List,com.blog.model.Blog"%>
<%@include file="header.jsp" %>
<style type="text/css" media="all">
        @import url("/blog/admin/css/screen.css");
</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>博客文章管理</title>
<style type="text/css">
.s {
	text-align: center;
}
</style>
</head>
<%
List<Blog> list =(List<Blog>)request.getAttribute("list");
Blog blog = null;
 %>
<body>
<script language="javascript">
function deleteConfirm(){
var msg= "您确定要删除？\n\n请确认！";
if(confirm(msg)==true){
return true;
}else{
return false;
}
}
</script>
<h1 class="s">博客文章管理</h1>
<table width="1136" border="4" align="center" cellpadding="4" cellspacing="0">
  <tr>
    <td width="116" class="s">文章编号</td>
    <td width="728" class="s">主题</td>
    <td width="282" class="s">操作</td>
  </tr>
  <%
  for(int i = 0; i < list.size(); i++){
  blog = list.get(i);
   %>
  <tr>
    <td class="s"><%=blog.getId() %></td>
    <td><%=blog.getTitle() %></td>
    <td class="s"><a href="/blog/servlet/PreEditBlogServlet?id=<%=blog.getId()  %>"><img src="/blog/admin/images/edit.gif " border="0"/></a>|
    <a href="/blog/servlet/DeleteBlogServlet?id=<%=blog.getId() %>" onclick="javascript:deleteConfirm()"><img src="/blog/admin/images/delete.gif" border="0"/></a></td>
  </tr>
  <%} %>
</table>
<p align="center" class="a">&nbsp;</p>
</body>
</html>
<%@include file="footer.jsp" %>