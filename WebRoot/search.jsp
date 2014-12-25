<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Welcome to OpenUI search: </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ui,juery,css,html5,js">
    <meta name="haownhao" content="">

    <!-- Le styles -->
    <link href="<%=basePath%>/assets/css/bootstrap.css" rel="stylesheet">
   <link href="<%=basePath%>/css/style.css" rel="stylesheet">
    <style type="text/css">
     

      /* Custom container */
      .container {
        margin: 0 auto;
        max-width: 1000px;
      }
      .container > hr {
        margin: 60px 0;
      }

      /* Main marketing message and sign up button */
      .jumbotron {
        margin: 80px 0;
        text-align: center;
      }
      .jumbotron h1 {
        font-size: 100px;
        line-height: 1;
      }
      .jumbotron .lead {
        font-size: 24px;
        line-height: 1.25;
      }
      .jumbotron .btn {
        font-size: 21px;
        padding: 14px 24px;
      }

      /* Supporting marketing content */
      .marketing {
        margin: 60px 0;
      }
      .marketing p + h4 {
        margin-top: 28px;
      }


      /* Customize the navbar links to be fill the entire space of the .navbar */
      .navbar .navbar-inner {
        padding: 0;
      }
      .navbar .nav {
        margin: 0;
        display: table;
        width: 100%;
      }
      .navbar .nav li {
        display: table-cell;
        width: 1%;
        float: none;
      }
      .navbar .nav li a {
        font-weight: bold;
        text-align: center;
        border-left: 1px solid rgba(255,255,255,.75);
        border-right: 1px solid rgba(0,0,0,.1);
      }
      .navbar .nav li:first-child a {
        border-left: 0;
        border-radius: 3px 0 0 3px;
      }
      .navbar .nav li:last-child a {
        border-right: 0;
        border-radius: 0 3px 3px 0;
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

    <div class="container">

      <div class="masthead">
       <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide">
      <div class="carousel-inner">
        <div class="item active">
          <img src="<%=basePath%>/assets/img/slide-01.jpg" alt="">
          <div class="container">
            <div class="carousel-caption">
              <h1>Welcome to OpenUI</h1>
              <p class="lead">OpenUI 是我个人开发的一个博客系统，内容主要涉及一些我日常生活中接触到的一些计算机知识。推荐使用新版本的chrome 或者 firefox 浏览本网站 以获得更好的体验</p>
              
            </div>
          </div>
        </div>
        <div class="item">
          <img src="<%=basePath%>/assets/img/slide-02.jpg" alt="">
          <div class="container">
            <div class="carousel-caption">
              <h1>About OpenUI</h1>
              <p class="lead">OpenUI 是一个使用bootstrap + jquery + SpringMVC 开发的一个rest风格的个人博客</p>
              
            </div>
          </div>
        </div>
        <div class="item">
          <img src="<%=basePath%>/assets/img/slide-03.jpg" alt="">
          <div class="container">
            <div class="carousel-caption">
              <h1>Join OpenUI</h1>
              <p class="lead">目前OpenUI由我一个人开发维护，内容不是很丰富，欢迎有兴趣的朋友联系我，为OpenUI贡献一份力量</p>
              
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div><!-- /.carousel -->
        <div class="navbar">
          <div class="navbar-inner">
            <div class="container">
              <ul class="nav">
                ${HEADER}
              </ul>
            </div>
          </div>
        </div><!-- /.navbar -->
      </div>


      <!-- Example row of columns -->
      <div class="row-fluid">
<div class="span12">


<!--left content start-->
        <div class="span9">

<h5>Search:关键字"<span class="label label-important">${keys}</span>"</h5>
    <div class="input-append">
    <form method="post" action="<%=basePath %>/hao/blog/search">
    <input class="span8" type="text" name="keys">
    <button class="btn" type="submit">Go!</button>
    </form>
   

    </div>
 <c:forEach items="${newslist}" var="news">
 <div class="div_content">
<div><font size=5><strong><a href="<%=basePath %>/hao/blog/show/${news.newsID}" target="_blank">[${news.newsType_str}]&nbsp;${news.newsTitle}</a></strong></font>&nbsp;&nbsp;<span class='muted'><small>[${news.newsDate}]<span class="text-warning">[${news.newsClick}]</span></small></span></div>
         <p>  <c:choose> 
    <c:when test="${fn:length(news.newsContent) > 250}"> 
     <c:out value="${fn:substring(news.newsContent, 0, 250)}......" escapeXml="false"/> 
    </c:when> 
    <c:otherwise> 
     <c:out value="${news.newsContent}" escapeXml="false"/> 
    </c:otherwise>
   </c:choose>&nbsp;&nbsp;<a href='#'>more...</a> </p>
   </div>
</c:forEach>
        

    <div class="pagination">
    <form method="get" name="searchform" action="<%=basePath %>/hao/blog/search">
        <ul>
        		<c:choose>
					<c:when test="${CURRENTPAGE==1}"><li class="disabled"><a href="#">&laquo;</a></li></c:when> 
					<c:otherwise><li><a href="#" onclick='nextPage(${CURRENTPAGE}-1)'>&laquo;</a></li></c:otherwise>
				</c:choose> 
        	<c:forEach begin="${responsePageStart}" end="${responsePageEnd}" var="inpage">
        		<c:choose>
					<c:when test="${inpage==CURRENTPAGE}"><li class="active"><a href="#">${inpage}</a></li></c:when> 
					<c:otherwise><li><a href="#" onclick='nextPage(${inpage})'>${inpage}</a></li></c:otherwise>
				</c:choose> 
          </c:forEach>
          		<c:choose>
					<c:when test="${CURRENTPAGE==totalpage||totalpage==0}"><li class="disabled"><a href="#">&raquo;</a></li></c:when> 
					<c:otherwise><li><a href="#" onclick='nextPage(${CURRENTPAGE}+1)'>&raquo;</a></li></c:otherwise>
				</c:choose> 
        </ul>
        <input type="hidden" id="page" name="page"/>
        <input type="hidden" id="keys" name="${key}"/>
        </form>
        
      </div>
    
</div>

<!--left content end-->

<!--right content start-->
       <div class="span3 div_content" align='left'> 
<h5>Introduce:</h5>
<img  id="introduce" src='<%=basePath%>/img/self.jpg' class="img-polaroid">
<div>&nbsp;</div>
<small>
<p>${introduce}</p>
<p>微博：${weibo}</p>
<p>邮箱：${email}</p>
</small>

<hr/>
<h5>search:</h5>
    <div class="input-append">
    <form method="post" action="<%=basePath %>/hao/blog/search">
     <input class="span8" id="keys" type="text" name="keys">
    <button class="btn" type="submit">Go!</button>
    </form>
    </div>
<hr/>


<h5>Tags:</h5>
 <c:forEach items="${tagslist}" var="tag">
<span class="label hand label-${tag.style}" onclick="window.location.href='<%=basePath %>/hao/blog/tag/${tag.tagName}/1'">${tag.tagName}</span>
</c:forEach>
<hr/>


<!-- start -->
<div>
<h5>热门文章:</h5>
<div id="hotNews"></div>
</div>
<hr/>
<!-- end -->

<!-- start -->
<div>
<h5>最新留言:</h5>
<div id="newMsg"></div>
</div>
<!-- end -->
<hr/>
<!-- start -->
<div>
<h5>站点统计:</h5>
<div id="web_stat"></div>
</div>
<!-- end -->
      </div>
<!--ritht end-->
	</div>

      <hr>

     <div class="clear"></div>
      <div class="footer div_content">
         ${FOOTER}
      </div>

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
var base = "<%=basePath%>";

function nextPage(page){
	$("#page").val(page);
	this.searchform.submit();
}
</script>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F4e48ade92c2de02e8b6af25b7e138cfd' type='text/javascript'%3E%3C/script%3E"));
</script>

  </body>
</html>
