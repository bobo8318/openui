<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Welcome to OpenUI</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ui,juery,css,html5,js">
    <meta name="haownhao" content="">
<%@include file="/common/css.jsp" %>
    <!-- Le styles -->
   
   


    <!-- Fav and touch icons -->
   <link rel="shortcut icon" href="<%=basePath%>/assets/ico/favicon.ico" type="image/x-icon">
  </head>

  <body>
  <%@ include file="/common/navbar.jsp" %>
<div class="main-container container-fluid">
			<a class="menu-toggler" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			<%@ include file="/common/sidebar.jsp" %>

<div class="main-content">
        <div class="span12">
        
          <div class="alert alert-success" id="introduceContent">
            <h3>欢迎登录我的网站</h3>
            <p>OpenUI 是一个使用bootstrap 和 SpringMVC 搭建的一个rest风格的个人博客，由我本人开发维护，各种功能不断完善中，
            欢迎有兴趣的朋友一起 目前后台不允许操作。</p>
          </div>
          
         
        </div><!--/span-->
     
</div>
</div>
   
  <%@include file="/common/js.jsp" %>
  </body>
</html>
