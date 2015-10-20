<%@ page language="java" import="com.blog.model.Blog,com.blog.model.Comment,com.blog.model.Category,java.util.List" 

contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>王昆的博客</title>
<script type="text/javascript" src="/blog/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" type="text/css" href="/blog/style.css" />
</head>
<body>
<div id="container">	
	<div id="banner">
		<h1><a href="/blog">王昆的博客</a></h1>
	</div>

<div id="center">
<div class="content">

<%Blog blog = (Blog)request.getAttribute("blog");
	List<Comment> CommentList = (List<Comment>)request.getAttribute("CommentList");
 %>
<table id="tab">
  <tr>
    <td><h2><%=blog.getTitle()%></h2></td>
  </tr>
  <tr>
    <td><%=blog.getContent()%></td>
  </tr>
 
  <tr>
    <td><%=blog.getCreated_time() %></td>
  </tr>
 
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="79">
    
   <% 
    if(CommentList != null){
    for(int i = 0; i < CommentList.size(); i++){
    Comment comment = (Comment)CommentList.get(i);
     %>  
    <table id="tab">
      <tr>
        <td><%=comment.getUsername()%>的评论</td>
        </tr>
      <tr>
        <td><%=comment.getContent()%></td>
        </tr>
      <tr>
        <td><%=comment.getCreated_time() %></td>
        </tr>
    </table>
    <br><br>
      <%}
  }
  %>
   <p>&nbsp;</p></td>
  </tr>
    <tr>
    <td height="65">
    <form name="form1" id="form1" method="post" action="/blog/servlet/CommentServlet">
    <input type="hidden" name="blog_id" value="<%=blog.getId()%>"/>
    <input type="hidden" name="method" value="add"/>
    <table width="804" border="0" cellspacing="0" cellpadding="2">
      <tr>
        <td width="45">评论人:</td>
        <td width="718">
          <input name="username" type="text" id="username" maxlength="10" />
        </td>
      </tr>
      <tr>
        <td>内容:</td>
        <td>
          <textarea name="content" cols="80" rows="15" id="content"></textarea>
        </td>
      </tr>
      <tr>
        <td><input type="submit" name="button" id="button" value="提交" /></td>
        <td>&nbsp;</td>
      </tr>
    </table>
     <script>
   CKEDITOR.replace( 'content', {
    language: 'zh-cn',
    uiColor: '#9AB8F3'
});
   </script>
    </form>
    </td>
  </tr>
</table> 


<br clear="all" />
</div><!-- end content -->
</div><!-- end center -->


<div id="right">
<div class="sidebar">
        <ul>
    	<li>王昆自己的博客，欢迎大家访问！</li>
      </ul>

  
  <h2>分类</h2>
   <ul>		
	<li><a href="/blog">全部</a></li>	
	<% List categorys=(List)request.getAttribute("categorys"); 
	   for(int i=0;i<categorys.size();i++){	
		Category category=(Category)categorys.get(i);
	%>
	    <li><a href="<%=request.getContextPath()%>/servlet/HomeServlet?cid=<%=category.getId()%>"><%=category.getName()%></a></li>        
     <%} %>  
   </ul>
<%-- 
  	    <h2>最近的主题</h2>
		<ul>	
			<%List recentBlogs=(List)request.getAttribute("blogs");
			  for(int i=0;i<recentBlogs.size();i++){
				Blog blog2=(Blog)recentBlogs.get(i);
				%>
	    		<li><a href="<%=request.getContextPath()%>/servlet/HomeServlet?method=get&id=<%=blog2.getId()%>" target="_blank"><%=blog.getTitle()%></a></li>
            <%}%>
      </ul> --%>
  	
	   
	   <h2>最近的评论</h2>
	  <ul>		
	  <% List comments=(List)request.getAttribute("comments");
	  	 for(int i=0;i<comments.size();i++){
	  		 Comment c=(Comment)comments.get(i);
	  		 
	  %>
	  	    <li><a href="<%=request.getContextPath()%>/servlet/HomeServlet?method=get&id=<%=c.getBlogid()%>"><%=c.getContent()%></a></li>
        <%} %>        
      </ul>
  	   	
</div><!-- end sidebar -->	
</div><!-- end right -->
</div><!-- end container -->
</body>
</html>
