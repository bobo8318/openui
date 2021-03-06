<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>文章管理</title>
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
								文章管理
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							<form class="form-inline" method="get" action="<%=request.getContextPath() %>/manager/classes">
								<input type="hidden" name="pagenum" value="${pagenum}">
								&nbsp;&nbsp;名称：<input type="text" name="name" value="${classes.name}"  class="input-medium search-query">&nbsp;&nbsp;&nbsp;&nbsp;
								<button  type="submit" class="btn btn-purple btn-small">
									查找
									<i class="icon-search icon-on-right bigger-110"></i>
								</button>
								<button  type="button" onclick="location.href='<%=request.getContextPath() %>/hao/blog/toAdd'" class="btn btn-purple btn-small">
									添加文章
									<i class="icon-plus-sign icon-on-right bigger-110"></i>
								</button>
							</form>
								<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="8%">序号</th>
										<th>文章标题</th>
										<th width="10%">添加时间</th>
										<th width="10%" >操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${newslist}"  var="news"  >
									<tr>
										<td>${news.newsID}</td>
										<td>${news.newsTitle}</td>
										<td>${news.newsDate}</td>
										<td><a href="<%=request.getContextPath() %>/hao/blog/toUpdate/${news.newsID}">修改</a>/<a href="<%=request.getContextPath() %>/hao/blog/remove/${news.newsID}" onclick="return confirm('确定删除文章？')">删除</a></td>
										
									</tr>
								</c:forEach>
								</tbody>
							</table>
					 		<div class="dataTables_paginate paging_bootstrap pagination">
					 		
        <ul>
        		<c:choose>
					<c:when test="${CURRENTPAGE==1}"><li class="disabled"><a href="#">&laquo;</a></li></c:when> 
					<c:otherwise><li><a href="<%=request.getContextPath() %>/hao/blog/list/${CURRENTPAGE-1}" >&laquo;</a></li></c:otherwise>
				</c:choose> 
        	<c:forEach begin="${responsePageStart}" end="${responsePageEnd}" var="inpage">
        		<c:choose>
					<c:when test="${inpage==CURRENTPAGE}"><li class="active"><a href="#">${inpage}</a></li></c:when> 
					<c:otherwise><li><a href="<%=request.getContextPath() %>/hao/blog/list/${inpage}" >${inpage}</a></li></c:otherwise>
				</c:choose> 
          </c:forEach>
          		<c:choose>
					<c:when test="${CURRENTPAGE==totalpage||totalpage==0}"><li class="disabled"><a href="#">&raquo;</a></li></c:when> 
					<c:otherwise><li><a href="<%=request.getContextPath() %>/hao/blog/list/${CURRENTPAGE+1}" >&raquo;</a></li></c:otherwise>
				</c:choose> 
        </ul>

					 		</div>
							
					 		
							<!--PAGE CONTENT ENDS-->
					</div><!--/.span-->
				</div><!--/.row-fluid-->
			</div><!--/.page-content-->
		</div><!--/.main-content-->


</div>
			

<%@include file="/common/js.jsp" %>
  </body>
</html>
