<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!--basic styles-->
<link href="<%=request.getContextPath()%>/assets/css/bootstrap.min.css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/assets/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/font-awesome.min.css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/font-awesome-ie7.min.css" />
<![endif]-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace-fonts.css" />
<!--ace styles-->
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace-responsive.min.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace-skins.min.css" />
<!--[if lte IE 8]>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/ace-ie.min.css" />
<![endif]-->
    <link href="<%=basePath%>/css/style.css" rel="stylesheet">
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="<%=basePath%>/assets/js/html5shiv.js"></script>
    <![endif]-->