<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar" id="sidebar">
	<ul class="nav nav-list">
		<li <c:if test="${sidebar=='article'}">class="active"</c:if> >
			<a href="<%=request.getContextPath()%>/hao/blog/list/1">
				<i class="icon-user"></i>
				<span class="menu-text">Article Manage</span>
			</a>
		</li>
		<li <c:if test="${sidebar=='parameter'}">class="active"</c:if> >
			<a href="<%=request.getContextPath()%>/hao/blog/toParameter">
				<i class="icon-list-alt"></i>
				<span class="menu-text"> Parameter Set</span>
			</a>
		</li>
		<li <c:if test="${sidebar=='tagManage'}">class="active"</c:if> >
			<a href="<%=request.getContextPath()%>/hao/blog/tagManage">
				<i class="icon-file"></i>
				<span class="menu-text">TAG Manage</span>
			</a>
		</li>
		<li <c:if test="${sidebar=='lottery'||sidebar=='replys'}">class="active"</c:if>>
			<a href="#" class="dropdown-toggle">
				<i class="icon-comment"></i>
				<span class="menu-text"> 彩票数据管理</span>

				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li <c:if test="${sidebar=='lottery'}">class="active"</c:if> >
					<a href="<%=request.getContextPath()%>/hao/lottery/updateData">
						<i class="icon-double-angle-right"></i>
						彩票数据管理
					</a>
				</li>
				<li <c:if test="${sidebar=='replys'}">class="active"</c:if> >
					<a href="<%=request.getContextPath()%>/hao/blog/getSaveMsgList/1">
						<i class="icon-double-angle-right"></i>
						回复管理
					</a>
				</li>
			</ul>
		</li>
		 
	</ul><!--/.nav-list-->
	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left"></i>
	</div>
</div>