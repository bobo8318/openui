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
    <meta name="haowenhao" content="">

    <!-- Le styles -->
    <link href="<%=basePath%>/assets/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }

      @media (max-width: 980px) {
        /* Enable use of floated navbar text */
        .navbar-text.pull-right {
          float: none;
          padding-left: 5px;
          padding-right: 5px;
        }
       
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
  
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Welcome to OpenUI</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              欢迎： <a href="#" class="navbar-link">${admin.loginname}</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <a href="<%=basePath%>/hao/blog/exit" class="navbar-link">退出</a>
            </p>
            <ul class="nav">
              <!-- li class="active"><a href="index">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li-->
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">信息管理</li>
              <li class="active" id="introduce"><a href="#" onclick="slide('introduce')" >简介</a></li>
              <li id="article"><a href="#" onclick="slide('article')" >文章管理</a></li>
              
              <li id="webheader"><a href="#" onclick="slide('webparameter')" >参数管理</a></li>
              <li id="webfooter"><a href="#" onclick="slide('webfooter')" >网站底部通用管理</a></li>
              <li id="tagManage"><a href="#" onclick="slide('tagManage')" >标签管理</a></li>
             
              <li class="nav-header">账号管理</li>
              <li><a href="#">修改密码</a></li>
             
              <li class="nav-header">辅助功能</li>
              <li><a href="#">Link</a></li>
             
            </ul>
          </div><!--/.well -->
        </div><!--/span-->

  <div class="span9">
        
          <div class="alert alert-success" id="introduceContent">
            <p>OpenUI 是一个使用bootstrap 和 SpringMVC 搭建的一个rest风格的个人博客，由我本人开发维护，各种功能不断完善中，
            欢迎有兴趣的朋友一起</p>
          </div>
          
          <div class="alert alert-info" id="articleContent" style="display:none">
            <p>文章管理</p><a href="#">添加文章</a>
            <div id="articlelist"></div>
            <div class="pagination" id="pagination"></div>
          </div>
          
         <div class="alert alert-info" id="webparameterContent" style="display:none">
         <p>参数修改</p>
         
     
        <form class="form-actions" method="post" action="<%=basePath%>/hao/blog/parameter">
         <fieldset>
         <legend>常用设置</legend>

<div class="control-group">
 <label class="control-label" for="inputPassword">头部通用</label>
 <div class="controls">
<textarea rows="3" name="HEADER" id="HEADER">${parameter.HEADER}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">底部通用</label>
 <div class="controls">
<textarea rows="3"  name="FOOTER" id="FOOTER">${parameter.FOOTER}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">个人介绍</label>
 <div class="controls">
<textarea rows="3" name="introduce" id="introduce">${parameter.introduce}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">邮箱</label>
 <div class="controls">
<textarea rows="3"  name="email" id="email">${parameter.email}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">微博</label>
 <div class="controls">
<textarea rows="3"  name="weibo" id="weibo">${parameter.weibo}</textarea>
</div>
</div>

 <button type="submit" class="btn">update</button>
 </fieldset>
</form>
         
         
         </div>
        
        
         </div>
         
         <div class="alert alert-info" id="webfooterContent" style="display:none">
         底部通用修改
          <div class="form-actions" action="">
         <div> <textarea rows="3" value="${footer}"></textarea></div>
         <div><button type="submit" class="btn btn-primary">Save changes</button>
		 <button type="button" class="btn">Cancel</button></div>
		 </div>
         
         </div>
         <div id="tagManageContent" style="display:none">标签管理</div>
         
        </div><!--/span-->

        </div><!--/span-->
      </div><!--/row-->

      <hr>
    <footer>
        ${FOOTER}
    </footer>

    </div><!--/.fluid-container-->

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
var base = "<%=basePath%>";

var activeContent = "introduce";

function slide(id){
	//导航active显示
	
	if(activeContent!=id){

		$("#"+id).addClass("active");
		$("#"+activeContent).removeClass("active");
	
		//显示内容
		document.getElementById(activeContent+"Content").style="display:none";
		document.getElementById(id+"Content").style="";
		
		activeContent = id;
		
	}
	//根据不同的id通过ajax获取相应数据
	if(id=='article'){//文章管理
		articlelist(1);
	}

	
	
}
function articlelist(page){
	//alert('get article list');
	$.get(base+"/hao/blog/list/"+page,function(data){
		$("#articlelist").html(data);
		
	});
	//取得页数
	$.get(base+"/hao/blog/pagination/"+page,function(data){
		$("#pagination").html(data);
	});
}


</script>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F4e48ade92c2de02e8b6af25b7e138cfd' type='text/javascript'%3E%3C/script%3E"));
</script>

  </body>
</html>
