<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>标签管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ui,juery,css,html5,js">
    <meta name="haownhao" content="">
<%@include file="/common/css.jsp" %>
    <!-- Fav and touch icons -->
   <link rel="shortcut icon" href="<%=basePath%>/assets/ico/favicon.ico" type="image/x-icon">
 
 
  </head>

  <body>
<%@ include file="/common/navbar.jsp" %>
<%@include file="/common/js.jsp" %>
<script type="text/javascript">
var base = "<%=basePath%>";
function updateTag(id){
var newTagName = $("#tagname_"+id).val();
var tagId = $("#tagId_"+id).val();
$.post(base+"/hao/blog/updateTag",{"tagName":newTagName,"tagId":tagId},function(msg){
		alert("修改成功");
	}
);
}
</script>
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
								标签管理
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							
								<table id="sample-table-1" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th width="8%">序号</th>
										<th >标签名</th>
										<th width="10%" >操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${tagslist}"  var="tags"  >
									<tr>
										<td>${tags.tagId}<input id="tagId_${tags.tagId}" type="hidden" value="${tags.tagId}"/></td>
										<td><input id="tagname_${tags.tagId}" type="text" value="${tags.tagName}"/></td>
										<td><a href="#" onclick="updateTag('${tags.tagId}')">修改</a>/<a href="<%=request.getContextPath() %>/hao/blog/removeTag/${tags.tagId}" onclick="return confirm('确定删除标签？')">删除</a></td>
										
									</tr>
								</c:forEach>
								</tbody>
							</table>
							<form class="form-inline" method="post" action="<%=request.getContextPath() %>/hao/blog/addTag">
								
								<textarea class="span12" name="tagname"></textarea>
								<div>&nbsp;</div>
								<button type="submit" class="btn btn-purple btn-small">
									添加标签
									<i class="icon-plus-sign icon-on-right bigger-110"></i>
								</button>
							</form>
							
					 		
</div>
</div>
</div>
</div>
</div>
			


  </body>
</html>
