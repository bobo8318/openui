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
    <title>${news.newsTitle}</title>
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
<script type="text/javascript">
var base = "<%=basePath%>";
function praise(id){
$.get(base+"/hao/blog/praise/"+id,function(data){
		//alert(data);
		$("#praise").html(data);
	});
}
function savemessage(){
var message = $("#message").val();
var imagecode = $("#imagecode").val();
var articleid = $("#articleid").val();
if(message === ""){
alert("请输入留言内容！");
$("#message").focus();
return false;
}else if(imagecode === ""){
alert("请输入验证码！");
$("#imagecode").focus();
return false;
}
else{
$.post(base+"/hao/blog/saveMessage",{"message":message,"imagecode":imagecode,"articleid":articleid},function(data){
		alert(data);
	});
return true;
}
}
function loadimage(){ 
document.getElementById("randImage").src = base+"/hao/blog/image?"+Math.random(); 
} 
</script>
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
  <div class="div_content">
<div align="center">
<div><h2>[${news.newsType_str}]&nbsp;${news.newsTitle}</h2></div>
<div><small>日期：<span class="badge badge-success">${news.newsDate}</span> 来源：<span class="badge badge-info">${news.source}</span>点击率：<span class="badge badge-warning">${news.newsClick}</span></small></div>
<br/>

<div class="tagStyle">
<ul>
<!-- li id="li_title">TAG:</li-->
<c:forEach items="${news.newsTagsList}" var="tag">
	<li><a href="#" class="tagStyle-${tag.style}">${tag.tagName}</a></li>
</c:forEach>
</ul>
</div>

<div class="clear"></div>
<hr/>
<div align="left" >
<p class="text-info"><c:out value="${news.newsContent}" escapeXml="false"/> </p>
</div>
</div>



<div>
<small>
<div class="bdsharebuttonbox">
<A class=bds_more href="#" data-cmd="more"></A><A class=bds_qzone title=分享到QQ空间 href="#" data-cmd="qzone"></A><A class=bds_tsina title=分享到新浪微博 href="#" data-cmd="tsina"></A><A class=bds_tqq title=分享到腾讯微博 href="#" data-cmd="tqq"></A><A class=bds_renren title=分享到人人网 href="#" data-cmd="renren"></A><A class=bds_weixin title=分享到微信 href="#" data-cmd="weixin"></A></div>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
<div>
<hr/>
<div>前一篇：<c:choose><c:when test="${pre!=null}"><a href="<%=basePath%>/hao/blog/show/${pre.newsID}">${pre.newsTitle}</a></c:when><c:otherwise>没有了</c:otherwise></c:choose></div>
<div>后一篇：<c:choose><c:when test="${next!=null}"><a href="<%=basePath%>/hao/blog/show/${next.newsID}">${next.newsTitle}</a></c:when><c:otherwise>没有了</c:otherwise></c:choose></div>
</div>
</small>
</div>


</div> 


<div class="div_content" align='center'>
<div>
<button type="button" class="btn btn-alert" onclick="praise(${news.newsID})">赞</button><br/>
<span class="help-inline">已有<span class="badge badge-info" id="praise">${news.newsPraise}</span>人表示这篇文章很赞</span>
</div>

<div>
<c:forEach items="${saveMsg}" var="msg">
<button style="margin: 3px" class="btn btn-${msg.btnStyle}" >${msg.message}</button>
</c:forEach>
</div>

<div align="left">
<fieldset><legend>我有话要说</legend>
<textarea  name="message" id="message" placeholder="I want to say something…"></textarea><br/>
<input type="hidden" name="articleid" id="articleid" value="${news.newsID}">
<input type="text" name="imagecode" id="imagecode" placeholder="验证码">
<img id="randImage" src="<%=basePath%>/hao/blog/image" onclick="loadimage()">
<button class="btn btn-info" onclick="return savemessage()">留言</button>
</fieldset>
</div>
    
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
</div>
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
