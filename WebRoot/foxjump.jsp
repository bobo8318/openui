<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
  <head>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
    <title>OpenUI</title> 
  <body>
<script type="text/javascript">
var base = "<%=basePath%>";
	window.location.href = base+"hao/blog/index/1";
</script>
  </body>
</html>
