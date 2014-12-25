<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Welcome to OpenUI Lottery</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ui,juery,css,html5,js,lottery">
    <meta name="haownhao" content="">
    <!-- Le styles -->
  
    <!-- Fav and touch icons -->
   <link rel="shortcut icon" href="<%=basePath%>/assets/ico/favicon.ico" type="image/x-icon">
  </head>
<style>
*{
	padding:0;
	margin:0;
}
.table_div{

}
table {
font-size:13px;
color:#333333;
border-width:1px;
border-color:#999999;
border-style:solid;
border-collapse:collapse;
margin:3px auto;
}
table td{
padding:8px;
border-width:1px;
border-color:#999999;
border-style:solid;
background-color:#eee000;
}
table th {
background-color:#FFF000;
}

ul
{
text-align:center;
border:2px solid #a1a1a1;
padding-left:10px; 
background:#dddddd;
width:350px;
height:30px;
border-radius:10px;
box-shadow:2px 2px 5px #888888;
-moz-border-radius:25px; /* 老的 Firefox */
margin:15px auto;
line-height:30px;
}
a
{
display:block;
width:60px;
text-decoration:none;
height:30px;
}
a:hover{
background-color:#FFF000;
}
li
{
display:inline;
float:left;
}
.clear{
clear:both;
}
</style>
  <body>

 <div>
<ul>
<li><a href="default.asp">home</a></li>
<li><a href="news.asp">News</a></li>
<li><a href="contact.asp">Contact</a></li>
<li><a href="about.asp">About</a></li>
</ul>
</div>
  <div class="clear">&nbsp;&nbsp;</div>
  <div class="table_div">
  <table>
  	<th >序号</th ><th >数据</th ><th >日期</th >

   <c:forEach items="${dcList}" var="lottery">
  <tr>
  	 <td>1</td><td>${lottery.lotteryData}</td><td>${lottery.lotteryDate}</td>
  </tr>
  </c:forEach>
  </table>
</div>
   
  <%@include file="/common/js.jsp" %>
  </body>
</html>
