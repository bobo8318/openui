<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8" />
  <head>
    <title>test</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
*{
padding:0px;
margin:0px;
}
body{
padding:5px;
background-color:#EEEEEE;
}
.btn_normal{
	padding: 0 3px 2px;
	cursor: pointer;
	font-family:"Helvetica Neue", Helvetica, Arial, sans-serif;
	text-align:center;
	vertical-align:middle;
	font-size:15px;
	border-width:1px;
	
	background-color:#f5f5f5;
	-moz-border-radius:3px;
	border-radius:5px;
	
	width:60px;
	height:30px;
	padding:0px;
	line-height:15px;
}
.btn_normal:hover{
	background-color:#FFFFCC;
}

.tag_min{
	text-align:center;
	vertical-align:middle;
	font-family:"Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size:8px;
	border-color:#FF6600;
	background-color:#FF6600;
	-moz-border-radius:4px;
	border-radius:4px;
	box-shadow:2px 2px 2px #333333;
	width:40px;
	height:18px;
	padding:2px;
	line-height:10px;
}

.tag_normal{
	text-align:center;
	vertical-align:middle;
	font-family:"Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size:15px;
	border-color:#FFCCCC;
	background-color:#FFCCCC;
	width:60px;
	height:30px;
	padding:3px;
	line-height:1;
}
.tag_normal:hover{
background-color:#FF9966;
}

.main-ul{
display:inline;
list-style:none;

background-color:#FFFF00;
height:40px;
font-size:22px;
-moz-border-radius:4px;
	border-radius:4px;
}
.main-ul li{
float:left;
padding-left:5px;
padding-right:5px;
height:40px;
}
father-ul{
z-index:100;
}
.son-ul{
 display:none;
 z-index:100;
 background-color:pink;
}
.father-ul:hover{
background-color:pink;
}
.father-ul:hover .son-ul{
display:block;

}

.div_content{
background-color:#FFFFFF;
width:450px;
height:250px;
padding:10px;

border-radius:8px;
	box-shadow:2px 2px 2px #333333;
}
.div_content .content_title{
padding:10px;
font-weight:bold;
font-size:35px;
color:#0099CC;
}
.div_content div{
color:#0099CC;
}
</style>
  </head>
  
  <body>
    This is my test page. <br>
    <hr>
    <div>Button</div>
    <div>
    	<button class="btn_normal">提交</button>
    
    </div>
    <hr>
    <div>Tags</div>
    <div>
    	<button class="tag_min">红色</button>
    
    </div>
    <hr>
    <div>navigation</div>
    
    
    <ul class="main-ul">
    	<li>father1</li>
    	<li>father2</li>
    	<li class="father-ul">father3</li>
    	<ul class="son-ul">
    		<li>son</li>
    	</ul>
    </ul>
    <div style="clear:both;"></div>
    
    <div class="div_content">
	    <div class="content_title">这是标题</div>
	    <div> 这是显示内容</div>
	    <button class="tag_normal">北京</button>
    </div>
  </body>
</html>
