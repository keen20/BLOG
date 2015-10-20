<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@include file="header.jsp" %>
<h3> <%String msg = (String)request.getAttribute("message");
		if(msg == null){
		msg="";
		}else{
		out.print(msg);
		}
%> <br>
</h3> 

<%@include file="footer.jsp" %>