<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>修改文章</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ui,juery,css,html5,js">
    <meta name="haownhao" content="">
<%@include file="/common/css.jsp" %>
<link rel="stylesheet" href="<%=basePath%>/jwysiwyg/jquery.wysiwyg.css" type="text/css"/>
<script type="text/javascript" src="<%=basePath%>/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/jwysiwyg/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="<%=basePath%>/jwysiwyg/wysiwyg.image.js"></script>
<script type="text/javascript" src="<%=basePath%>/jwysiwyg/wysiwyg.link.js"></script>
<script type="text/javascript" src="<%=basePath%>/jwysiwyg/wysiwyg.table.js"></script>
   
   
    <!-- Fav and touch icons -->
   <link rel="shortcut icon" href="<%=basePath%>/assets/ico/favicon.ico" type="image/x-icon">
 
 
  </head>

  <body>
<script type="text/javascript">
(function ($) {
	$(document).ready(function () {
		$('#wysiwyg').wysiwyg({
		  controls: {
			bold          : { visible : true },
			italic        : { visible : true },
			underline     : { visible : true },
			strikeThrough : { visible : true },
			
			justifyLeft   : { visible : true },
			justifyCenter : { visible : true },
			justifyRight  : { visible : true },
			justifyFull   : { visible : true },

			indent  : { visible : true },
			outdent : { visible : true },

			subscript   : { visible : true },
			superscript : { visible : true },
			
			undo : { visible : true },
			redo : { visible : true },
			
			insertOrderedList    : { visible : true },
			insertUnorderedList  : { visible : true },
			insertHorizontalRule : { visible : true },

			h4: {
				visible: true,
				className: 'h4',
				command: ($.browser.msie || $.browser.safari) ? 'formatBlock' : 'heading',
				arguments: ($.browser.msie || $.browser.safari) ? '<h4>' : 'h4',
				tags: ['h4'],
				tooltip: 'Header 4'
			},
			h5: {
				visible: true,
				className: 'h5',
				command: ($.browser.msie || $.browser.safari) ? 'formatBlock' : 'heading',
				arguments: ($.browser.msie || $.browser.safari) ? '<h5>' : 'h5',
				tags: ['h5'],
				tooltip: 'Header 5'
			},
			h6: {
				visible: true,
				className: 'h6',
				command: ($.browser.msie || $.browser.safari) ? 'formatBlock' : 'heading',
				arguments: ($.browser.msie || $.browser.safari) ? '<h6>' : 'h6',
				tags: ['h6'],
				tooltip: 'Header 6'
			},
			
			cut   : { visible : true },
			copy  : { visible : true },
			paste : { visible : true },
			html  : { visible: true },
			increaseFontSize : { visible : true },
			decreaseFontSize : { visible : true },
			exam_html: {
				exec: function() {
					this.insertHtml('<abbr title="exam">Jam</abbr>');
					return true;
				},
				visible: true
			}
		  },
		  events: {
			click: function(event) {
				if ($("#click-inform:checked").length > 0) {
					event.preventDefault();
					alert("You have clicked jWysiwyg content!");
				}
			}
		  }
		});

		
	});
})(jQuery);
</script>

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
								 修改文章
							</small>
						</h1>
					</div> 
					<div class="row-fluid">
						<div class="span12">
							<!--PAGE CONTENT BEGINS-->
							<form action="<%=basePath%>/hao/blog/update" id="updateArticleForm" method="post">
							<input type="hidden" class="span6" id="newsID" name="newsID" value="${news.newsID}"/><br/>
							标题<input type="text" class="span6" id="newsTitle" name="newsTitle" value="${news.newsTitle}"/><br/>
							日期<input type="text" class="span2" id="newsDate" name="newsDate" value="${news.newsDate}"/>
							标签<input type="text" class="span2" id="newsTags" name="newsTags" value="${news.newsTags}"/>
							来源<input type="text" class="span2" id="source" name="source" value="${news.source}"/>
							类型<select class="span2" id="newsType" name="newsType"/>
							<option value="1" selected>文章</option>
							<option value="2" >视频</option>
							<option value="3" >相册</option>
							</select><br/>
							视频代码：<textarea class="span12" rows="3"></textarea><br/>
							内容<textarea id="wysiwyg" name="newsContent" class="span12" rows="20">${news.newsContent}</textarea><br/>
							<br>
									<button class="btn btn-info" id="leavemessage" type="submit" >
										<i class="icon-inbox"></i>
										保存
									</button>
					 		
					 		</form>
							<!--PAGE CONTENT ENDS-->
							${msg}
					</div><!--/.span-->
				</div><!--/.row-fluid-->
			</div><!--/.page-content-->
		</div><!--/.main-content-->


</div>

<%@include file="/common/js.jsp" %>
  </body>
</html>
