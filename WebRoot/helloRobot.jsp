<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  	<title>iRobot</title>
  <meta name="author" content="sin">
  <meta name="viewport" content="width=device-width,initial-scale=1">

 <link href="<%=basePath%>/assets/css/bootstrap.css" rel="stylesheet">
    <link href="<%=basePath%>/css/style.css" rel="stylesheet">
        <script src="<%=basePath%>/assets/js/jquery.js"></script>
<script src="<%=basePath %>js/robot/robot.js" type="text/javascript"></script>
<script src="<%=basePath %>js/robot/ui.js?v=15" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/robot/styles.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/robot/skin/chrome/chrome.css">

</head>
  
  <body>
	<input type="hidden" id="code" value="">
        <div >
            <div class="chat">
                <div class="title">
                    <span style="float: left;">欢迎来到iRobot的世界</span>
                </div>
                <div class="chatlog">
                    <div id="log" class="logbox">
					
                    </div>
                </div>
               
                <div class="area">
                    <div class="chatarea">
                        <textarea id="chatarea" class="chatmsg"></textarea>
                    </div>
                    <a id="send" class="btn btn-primary" onclick="sendmsg('<%=basePath %>')">发送 </a>
                </div>
                <div class="btmBar">
                </div>
            </div>
        </div>
        <div class="cams">
              		情况说明：<br>
              		清除屏幕命令：clear 或者 清除<br>
              		sudo 进入命令模式 learn 进行学习 exit退出 showkeys 显示分词结果
         </div>
        <div id="winBorder">
        </div>
       
	   <div id="whotyping" style="display:none;background:#FFF; width:200px;font-size:12px; height:20px; position:absolute;"></div>
	   <div id="magic_facewrapper" style="display:none;width:500px;height:350px;Z-INDEX: 9999999999999999; POSITION: absolute; left:420px;top:200px">
	   <div id="magic_face"></div>
		</div>
  </body>
</html>
