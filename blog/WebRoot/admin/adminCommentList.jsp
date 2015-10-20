<%@page import="com.blog.model.Blog"%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*,com.blog.model.Comment"%>
<%@include file="header.jsp" %>
<style type="text/css" media="all">
        @import url("/blog/admin/css/screen.css");
</style>

<style type="text/css">
.s {
	text-align: center;
}
</style>
<%
List<Comment> list =(List<Comment>)request.getAttribute("list");
Comment comment = null;
 %>
<script type="text/javascript">		
			function del() {
				var msg = "您真的确定要删除吗？\n\n请确认！";
				if (confirm(msg)==true){
						return true;
					}else{
						return false;
					}
				}
</script>

<h1 class="s">博客评论管理</h1>
<table width="1500" border="4" align="center" cellpadding="4" cellspacing="0">
  <tr>
    <td width="172" class="s">评论编号</td>
    <td width="172" class="s">博文编号</td>
    <td width="116" class="s">作者</td>
    <td width="635" class="s">内容</td>
    <td width="215" class="s">日期</td>
    <td width="215" class="s">操作</td>
  </tr>
  <%
  for(int i = 0; i < list.size(); i++){
  comment = list.get(i);
   %>
  <tr>
    <td class="s"><%=comment.getId() %></td>
    <td><%=comment.getBlogid() %></td>
    <td><%=comment.getUsername() %></td>
    <td><%=comment.getContent() %></td>
    <td><%=comment.getCreated_time() %></td>
    <td class="s"><a href="/blog/servlet/CommentServlet?method=edit&id=<%=comment.getId()  %>"><img src="/blog/admin/images/edit.gif " border="0"/></a>|
    <a href="/blog/servlet/CommentServlet?method=delete&id=<%=comment.getId() %>" onclick="javascript:deleteConfirm()"><img src="/blog/admin/images/delete.gif" border="0"/></a></td>
  </tr>
  <%} %>
</table>
<p align="center" class="a">&nbsp;</p>

<%@include file="footer.jsp" %>

