<%@ page language="java" import="com.blog.model.Blog,com.blog.model.Comment,java.util.List" 

contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-

transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>博文信息</title>
 <script type="text/javascript" src="/blog/ckeditor/ckeditor.js"></script>
<style type="text/css">
.title {
	text-align: center;
}
</style>
</head>

<body>
<%Blog blog = (Blog)request.getAttribute("blog");
	List<Comment> CommentList = (List<Comment>)request.getAttribute("CommentList");
 %>
<table width="810" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="810" height="58"><p class="title"><%=blog.getTitle() %></p></td>
  </tr>
  <tr>
    <td height="318"><%=blog.getContent() %></td>
  </tr>
  <tr>
    <td height="66"><%=blog.getCreated_time() %></td>
  </tr>
  <tr>
    <td height="83">
    <% 
    if(CommentList != null){
    for(int i = 0; i < CommentList.size(); i++){
    Comment comment = (Comment)CommentList.get(i);
     %>
    <table width="400" border="1" cellspacing="0" cellpadding="0">
        <tr>
          <td height="10">评论人：<%=comment.getUsername() %></td>
        </tr>
        <tr>
          <td height="10"><%=comment.getContent() %></td>
        </tr>
        <tr>
          <td height="10">评论时间:<%=comment.getCreated_time() %>></td>
        </tr>
    </table>
    <br />
    <%} 
    	}%>
    </td>
  </tr>
  <tr>
    <td height="65">
    <form name="form1" id="form1" method="post" action="/blog/servlet/CommentServlet">
    <input type="hidden" name="blog_id" value="<%=blog.getId()%>"/>
    <input type="hidden" name="method" value="add"/>
    <table width="804" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td width="72">评论人：</td>
        <td width="718">
          <input name="username" type="text" id="username" maxlength="10" />
        </td>
      </tr>
      <tr>
        <td>内容：</td>
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
<br />
<br />
<br />
</body>
</html>