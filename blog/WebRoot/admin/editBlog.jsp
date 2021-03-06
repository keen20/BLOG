<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.blog.model.Category" %>
<%@ page import="com.blog.model.Blog" %>
 <script type="text/javascript" src="/blog/admin/ckeditor/ckeditor.js"></script>
<style type="text/css">
.title {
	text-align: center;
}
</style>
<% Blog blog=(Blog)request.getAttribute("blog");%>


<h2>修改博文</h2>    
<form id="form1" name="form1" method="post" action="/blog/servlet/PostEditBlogServlet">
<input type="hidden" name="id" value="<%=blog.getId() %>" />

<table id="tab">
<tr>
    <td>主题: </td>
    <td>
	<input name="title" type="text" id="title" size="100"   value="<%=blog.getTitle()%>"/>
	</td>
</tr>

<tr>
    <td>分类: </td>
    <td>  	
	  <select name="category" id="select">
	  <%List categorys=(List)request.getAttribute("categorys"); 
	  	int oldcid=blog.getCategoryId();
	  	for(int i=0;i<categorys.size();i++){
	  		Category c=(Category)categorys.get(i);
	  		if(c.getId() == oldcid){
	  		%>
         	<option value="<%=c.getId() %>" selected><%=c.getName()%></option>
       <%}else{%>
	  		<option value="<%=c.getId() %>" ><%=c.getName()%></option>
	  	<%}
	  	}%>                     
        </select>
</td>
</tr>


<tr>
    <td colspan="2">内容: <br/>
         <textarea name="content" cols="60" rows="18" id="content"><%=blog.getContent()%></textarea>
    </td>
</tr>

<tr>
    <td colspan="2">
					<input type="submit" name="submit" value="更新"/>
		    </td>
</tr>
</table>
     <script>
   CKEDITOR.replace( 'content', {
    language: 'zh-cn',
    uiColor: '#9AB8F3'
});
   </script>
</form>
<%@include file="footer.jsp" %>

