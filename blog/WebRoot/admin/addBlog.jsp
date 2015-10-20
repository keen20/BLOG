<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.blog.model.Category" %>

 <script type="text/javascript" src="/blog/admin/ckeditor/ckeditor.js"></script>
<style type="text/css">
.title {
	text-align: center;
}
</style>
<h2>
		发博文</h2>

    
<form id="form1" name="form1" method="post" action="/blog/servlet/AddBlogServlet">
<table id="tab">
<tr>
    <td>主题: </td>
    <td>
	<input name="title" type="text" id="title" size="98" />
	</td>
</tr>

<tr>
    <td>分类: </td>
    <td>  	
	 <select name="category" id="select">         
         <%List list=(List)request.getAttribute("categorys"); 
         	for(int i=0;i<list.size();i++){
         		Category c=(Category)list.get(i);
         %>          
          	<option value="<%=c.getId()%>"><%=c.getName()%></option>          
         <%} %>
          
        </select>
</td>
</tr>


<tr>
    <td colspan="2">内容: <br/>
        <textarea name="content" cols="100" rows="18" id="content"></textarea>
    </td>
</tr>

<tr>
    <td colspan="2">
					<input type="submit" name="submit" value="创建"/>
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