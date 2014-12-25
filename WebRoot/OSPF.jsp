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
    <title>Welcome to OpenUI</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ui,juery,css,html5,js">
    <meta name="haownhao" content="">

    <!-- Le styles -->
  <!-- Le styles -->
    <link href="<%=basePath%>/assets/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #f5f5f5;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 29px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

    </style>

    <link href="<%=basePath%>/assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="<%=basePath%>/assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
   <link rel="shortcut icon" href="<%=basePath%>/assets/ico/favicon.ico" type="image/x-icon">
  </head>

  <body>
<script language="javascript"> 
var base = "<%=basePath%>";
function loadimage(){ 
document.getElementById("randImage").src = base+"/hao/blog/image?"+Math.random(); 
} 
</script> 

 <div class="container">

      <form class="form-signin" method="post" action="<%=basePath%>/hao/blog/login">
        <h2 class="form-signin-heading">Welcome!</h2>
        
        <input type="text" class="input-block-level" placeholder="用户名" name="loginname">
        <input type="password" class="input-block-level" placeholder="密码" name="password">
         <input type="text" class="input-block-level" placeholder="识别码" name="idcode">
         <input type="text" class="input-block-level" placeholder="验证码 " name="imagecode">
         <img id="randImage" src="<%=basePath%>/hao/blog/image" onclick="loadimage()">
        ${msg}
        <button class="btn btn-large btn-primary" type="submit">登录</button>
      </form>

    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=basePath%>/assets/js/jquery.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-transition.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-alert.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-modal.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-dropdown.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-scrollspy.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-tab.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-tooltip.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-popover.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-button.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-collapse.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-carousel.js"></script>
    <script src="<%=basePath%>/assets/js/bootstrap-typeahead.js"></script>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F4e48ade92c2de02e8b6af25b7e138cfd' type='text/javascript'%3E%3C/script%3E"));
</script>

  </body>
</html>
