<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>基本信息设置</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ui,juery,css,html5,js">
    <meta name="haownhao" content="">
<%@include file="/common/css.jsp" %>
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
				 
				<div class="page-content">
					<div class="page-header position-relative">
						<h1>
							<small>
								<i class="icon-list-alt"></i>
								参数设置
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							  <form class="form-actions" method="post" action="<%=basePath%>/hao/blog/parameter">
         <fieldset>
         <legend>常用设置</legend>
<div class="control-group">
 <label class="control-label" for="inputPassword">根地址</label>
 <div class="controls">
<textarea class="span12"  name="webhost" id="webhost">${parameter.webhost}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">头部通用</label>
 <div class="controls">
<textarea rows="3" class="span12"  name="HEADER" id="HEADER">${parameter.HEADER}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">底部通用</label>
 <div class="controls">
<textarea rows="3"  class="span12" name="FOOTER" id="FOOTER">${parameter.FOOTER}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">个人介绍</label>
 <div class="controls">
<textarea rows="3" class="span12"  name="introduce" id="introduce">${parameter.introduce}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">邮箱</label>
 <div class="controls">
<textarea  class="span12"  class="span12" name="email" id="email">${parameter.email}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">微博</label>
 <div class="controls">
<textarea  class="span12"  name="weibo" id="weibo">${parameter.weibo}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">页面大小</label>
 <div class="controls">
<textarea rows="3" class="span12"  name="PAGE_SIZE" id="PAGE_SIZE">${parameter.PAGE_SIZE}</textarea>
</div>
</div>

<div class="control-group">
 <label class="control-label" for="inputPassword">数据源</label>
 <div class="controls">
<textarea class="span12"  name="DATA_SOURCE_TYPE" id="DATA_SOURCE_TYPE">${parameter.DATA_SOURCE_TYPE}</textarea>
</div>
</div>

 <button type="submit" class="btn">update</button>
 </fieldset>
</form>
							
					 		
							<!--PAGE CONTENT ENDS-->
					</div><!--/.span-->
				</div><!--/.row-fluid-->
			</div><!--/.page-content-->
		</div><!--/.main-content-->


</div>
			

<%@include file="/common/js.jsp" %>
  </body>
</html>
