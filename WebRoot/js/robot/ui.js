luguoUI = new Object();
 /** Download by http://down.liehuo.net
 * @description {JSON} luguoUI常量，组DOM模板、好友DOM模板
 * @field
 */ 
  luguoUI.HTMLMODEL={
    sysinfor:"<DIV class=logitem><DIV class=statuslog>#mess#</DIV></DIV>",
	guestinfor:"<DIV class=logitem><DIV class=strangermsg><SPAN class=msgsource>陌生人#dm#：</SPAN> #mess#</DIV></DIV>",
	privateinfor:"<DIV class=logitem><DIV class=strangermsg><SPAN class=msgsource>陌生人悄悄地说：</SPAN> #mess#</DIV></DIV>",
	youinfor:"<DIV class=logitem><DIV class=youmsg><SPAN class=msgsource>我：</SPAN> #mess#</DIV></DIV>",
	overinfor:"<DIV class=logitem><DIV>您可以： <a href='javascript:luguoUI.newChat()'>寻找新缘分</a> 或 <a href='javascript:luguoUI.inviteLuguo()'>指定某个陌生人聊天</a> 或 <A href=\"javascript:luguoUI.downloadMessages()\">保存聊天记录</A> 或 <A target='_blank' href='http://bbs.faqee.com/'>官方交流与反馈</A>.</DIV></DIV>",
	glistinfor:"<div class='fex_row'><a href='#'>陌生人</a><br/><div class='fex_tail'><small>来自 [#addr#] </small></div></div>"
};
  
  luguoUI.args = {
		messList:new Array(),
		flvnum:2
  };

luguoUI.url={
	refreshGuest:'http://nb.faqee.com:9090/plugins/online/online?type=user&domain=nb.faqee.com&cmd=getonlinenum&jid=admin@nb.faqee.com',
	getrandg:'http://nb.faqee.com:9090/plugins/online/online?type=user&domain=nb.faqee.com&cmd=getrandguest&jid=admin@nb.faqee.com',
	guestlist:'http://nb.faqee.com:9090/plugins/online/online?type=user&domain=nb.faqee.com&cmd=guestlist&topn=10&jid=admin@nb.faqee.com',
	cam:'http://nb.faqee.com:9090/plugins/online/online?type=user&domain=nb.faqee.com&cmd=updatecam',
	sendemail:'http://www.faqee.com/crm/g_email.action'
}
 /**  
 * @description 初始化webim界面
 * @field
 */ 
luguoUI.init = function(){ 
	//this.retGuestList();
	luguoUI.refreshGuest();
	window.setInterval(function(){luguoUI.refreshGuest();},10000);

}
 /**  
 * @description {JSON} 格式化JID使其成为有效的DOM的ID值
 * @param {String} jid XMPP中的JID
 * @return {String} result 格式化后的DOM有效ID值 
 */ 
  luguoUI.formatJID = function(jid){
		jid = jid.split("@")[0];//cut server name and resource name
		return jid.replace(/@/g,"_at_").replace(/\//g,"_xx_").replace(/\./g,"_dt_").replace(" ","_b_").replace(/\s/g,"_b_");
  };
  luguoUI.getRandFlv = function(){
	  return Math.floor(Math.random()*luguoUI.args.flvnum+1)+".flv";
  }
  luguoUI.formatRoomNick = function(jid){
		jid = jid.split("/")[1];//cut server name and resource name
		return jid;
  };
  luguoUI.transFace = function(msg)
  {
	  for(var i=0;i<=26;i++){
		reg = "`face"+i+"`";
		msg = msg.replace(new RegExp(reg, 'g'),"<img src='images/msn/"+i+".gif' />");
	  }
	  msg = this.ubb(msg);
	  return msg;
  };
luguoUI.getDisplayName =function(jid)
{
	return jid.split("@")[0];
}
luguoUI.msgReplace = function(mess,isMy,vnickname)
  {
		var tt = "";
		if(typeof(isMy)!='undefined' && isMy)
			tt = this.HTMLMODEL.youinfor+"";
		else
			tt = this.HTMLMODEL.guestinfor+"";
		tt = tt.replace(/#mess#/g,mess);
		if((vnickname!="") && (vnickname.length != 8))
			tt = tt.replace(/#dm#/g,"("+vnickname+")");
		else
			tt = tt.replace(/#dm#/g,"");
		return tt;
  };

  luguoUI.sendMessage = function(who,message)
  {
	   GCONFIG.dotyping=false;
	   this.appendMessage(message,true,"");
	   webim.sendGroupMessage(GCONFIG.roomid,message);
  };

luguoUI.showEnabled = function (flag)
{
	if(flag){
		this.disableByID("nextBtn");
		window.setTimeout(function(){luguoUI.enableByID("nextBtn");}, 10000);
		this.enableByID("send");
		this.enableByID("stopBtn");
		this.enableByID("chatarea");
	}else{
		this.enableByID("nextBtn");
		this.disableByID("send");
		this.disableByID("stopBtn");	
		this.disableByID("chatarea");
	}
}

  luguoUI.randID = function(){
	var c="luguo_"
	for(i=0;i<10;i++)
	{
		var iNum=Math.ceil(Math.random()*9);//产生0-9的随即数字
		var sChar1=String.fromCharCode(Math.ceil(25*Math.random()+65));//产生A-Z的字符，其对应ASC十进制为65-95
		var sChar2=String.fromCharCode(Math.ceil(25*Math.random()+97));//产生a-z的字符，其对应ASC十进制为97-122
		var sSring="";
		var sChar="";
		var a=Math.round(Math.random())

		
		a==0?sChar=sChar1:sChar=sChar2
		b=iNum+sChar;
		c+=''+b
	}
	return c;
  }
  luguoUI.appendMessage = function(message,isSelf,vnickname)
  {
	   var vmsg=message;
	   //if(vmsg.indexOf("http://")>=0)
	   //   vmsg=vmsg.replace(/((http:\/\/www)(\.[A-Z0-9][A-Z0-9_-]*\.)(com|net|cn|com.cn|org|biz|tv|org.cn|net.cn|gov.cn|mobi|info|cc))(:(\d+))?\/?/gim,"<a href='javascript:void(window.open(\"$1\",\"_newWnd\"))'>$1</a>");
	   //else
	   //	   vmsg=vmsg.replace(/((www)(\.[A-Z0-9][A-Z0-9_-]*\.)(com|net|cn|com.cn|org|biz|tv|org.cn|net.cn|gov.cn|mobi|info|cc))(:(\d+))?\/?/gim,"<a href='javascript:void(window.open(\"$1\",\"_newWnd\"))'>$1</a>");
	   vmsg=this.transFace(vmsg);
	   vmsg = this.msgReplace(vmsg,isSelf,vnickname);
	   vmsg = vmsg.replace(/autoreply`/g,"");
	   $(".logbox").append(vmsg);
	   this.scrollDown();
  };
 luguoUI.refreshOnlineNum = function(){
	 $("#onlinenum").text($(".myonline").size());
 }
  luguoUI.addOnlineUser = function(who){
	   var ss = this.constant.userinHTML;
	   if(who == webim.status.nickname){
		   ss = this.constant.selfinHTML;
	   }
	   ss = ss.replace(/#who#/g,who);
	   $("#onlineUsers").append(ss);	 
	   this.refreshOnlineNum();
 }
  luguoUI.formatStr=function(str) {
  	var rtnStr = "";
  	if (str < 10) rtnStr = "0" + str;
  	else rtnStr =  str;
  	return rtnStr;
  }
  luguoUI.leaveRoomMessage = function(){
	  webim.leaveRoom(GCONFIG.roomid);
	  var mess = luguoUI.HTMLMODEL.overinfor;
	  sysMessage(mess,false);
	  webim.status.ischat = false;
	  webim.status.isDelayChat=false;
	  this.showEnabled(false);
	  this.scrollDown();
	  stopLive();
 }
  luguoUI.scrollDown = function(){
	  $(".logwrapper").attr("scrollTop","100000000");
	  $(".logbox").attr("scrollTop","100000000");
   }
  luguoUI.resvMessage = function(messageJSON)
  {
	  if(typeof(this.formatRoomNick(messageJSON.from))=='undefined') return;
	  if((messageJSON.body+"").indexOf("typing`") == 0){
		  var ww = messageJSON.body.split("`")[1];
		  if(ww!=webim.status.myname){
			  $("#whotyping").text("对方正在输入消息...");
			  document.title = (document.title == "随机视频聊天 — faqee.com")?"【正在输入消息】随机视频聊天 — faqee.com":"随机视频聊天 — faqee.com";
		  }
		  return;
	  }else if((messageJSON.body+"").indexOf("notyping`") == 0){
		  var ww = messageJSON.body.split("`")[1];
		  if(ww!=webim.status.myname){
			  $("#whotyping").text("");
			  document.title = "随机视频聊天 — faqee.com";
		  }
		  return;
	  }else if((messageJSON.body+"").indexOf("mfplaying`") == 0){
		  this.playMagicFace(messageJSON.body.split("`")[1]);
		  return;
	  }
	   var vmsg=messageJSON.body;
	   //if(vmsg.indexOf("http://")>=0)
	//	   vmsg=vmsg.replace(/((http:\/\/www)(\.[A-Z0-9][A-Z0-9_-]*\.)(com|net|cn|com.cn|org|biz|tv|org.cn|net.cn|gov.cn|mobi|info|cc))(:(\d+))?\/?/gim,"<a href='javascript:void(window.open(\"$1\",\"_newWnd\"))'>$1</a>");
	  // else
	//	   vmsg=vmsg.replace(/((www)(\.[A-Z0-9][A-Z0-9_-]*\.)(com|net|cn|com.cn|org|biz|tv|org.cn|net.cn|gov.cn|mobi|info|cc))(:(\d+))?\/?/gim,"<a href='javascript:void(window.open(\"$1\",\"_newWnd\"))'>$1</a>");
	   vmsg=this.transFace(vmsg);

	   if(webim.status.nickname != this.formatRoomNick(messageJSON.from)){
	  	  this.appendMessage(messageJSON.body+"",false,this.formatRoomNick(messageJSON.from));			   
		  luguoUI.newMessageCome();
		  luguoUI.playSound('message');
		  this.pushMessage({who:'陌生人',message:vmsg});	
		  if(webim.status.autoreply != ""){
			  if((messageJSON.body+"").indexOf("autoreply`") != 0){
				webim.sendGroupMessage(GCONFIG.roomid,"autoreply`"+webim.status.autoreply);
			  }
		  }
	   }else{
		  this.pushMessage({who:'我',message:vmsg});	
	   }
	   $(".chatmsg").focus();
	   
  };
 
  luguoUI.newMessageCome = function(){
	  if(typeof(TITLETIMER)=='undefined'){
			TITLETIMER = window.setInterval(function(){
				document.title = (document.title == "随机视频聊天 — faqee.com")?"【新消息】随机视频聊天— faqee.com":"随机视频聊天 — faqee.com";
			},500);
	  }
  }

  luguoUI.sendInvite = function(chkname){
	  var txt = $("#chkinvitetxt").val();
	  $("input[name='"+chkname+"']").each(function() { 
			var vjid = $(this).val();
			webim.sendMessage(txt,vjid);
	  });
	  artDialog({id:'dg_default'}).close();
  }

  luguoUI.selectAll = function(myname){
	$("input[name='"+myname+"']").each(function() { 
            $(this).attr("checked", true); 
    });
  }
  luguoUI.whoChange = function(jid,show,status){
		var ss = jid.split("@")[0];
		if(ss=="") return;
		if($("#myf_"+ss).length == 0)
			$("#inviteList").append("<p id='myf_"+ss+"'><input type='checkbox' value='"+jid+"' name='chkinvite'/>"+ss+"</p>");
  }
  luguoUI.loginSvr = function(svr){
	  var u = $("#usr_"+svr).val();
	  var p = $("#pwd_"+svr).val();
	  var s = svr;
	  if($.trim(u)==""||$.trim(p)==""){
		alert("请输入用户名和密码！");
		return;
	  }
	  webim.bindMSN(u,p,s);
	  $("#btn_"+svr).attr("disabled",true);
	  $("#btn_"+svr).val("正在读取"+svr+"好友，请稍候...");
  }
  
  luguoUI.openV = function(){
		var myurl = "http://red5.faqee.com/test/?myid="+webim.status.myname+"&youid="+GCONFIG.cguest;
		var vcont = '<iframe src="'+myurl+'" border="0" width="510" height="210" frameBorder="0" '+
			'style="z-index: -1; padding-bottom: 0px; margin: 0px; padding-left: 0px; width: 510px;'+
			'padding-right: 0px; height: 210px; top: 0px; padding-top: 0px; left: 0px;"></iframe>';
		this.openWnd({title:'陌生人视频显示窗口',content:vcont});
	}
  
  luguoUI.invite = function(svr){
	  var vcont = '<div id="msnlist"><p style="line-height:20px;">'+svr+
		  '用户名：<input type="text" id="usr_'+svr+'" /></p><p style="line-height:20px;">'+svr+
		  '密&nbsp;&nbsp;码&nbsp;&nbsp;：<input type="password" id="pwd_'+svr+'" /></p>'+
		  '</div>';
	  var vtitle = svr+"邀请";
	  artDialog({content:vcont, fixed:true, title:vtitle,yesText:'开始邀请'}, 
			  function(){luguoUI.loginSvr(svr);}, 
			  function(){}
	  );
	  
	  //this.openWnd({title:'邀请朋友',content:vcont});
  }

luguoUI.getJoke = function(){
  $.getJSON("http://www.faqee.com/crm/g_joke.action?cmd=getjoke&callback=?",
  function(data){
	  if(data.joke){
		  var t = data.joke.title;
		  var c = "<div style='font-size:12px;'>"+data.joke.content+"</div><center id=\"xiaohua_center\"><a href=\"javascript:luguoUI.getJoke();\">换一篇</a></center>";
		  var cname = data.joke.class_name;
		  luguoUI.openWnd({title:t+" — "+cname,content:c});
	  }
  });
}

function sendEmailcallback(){
	if(AjaxCrossDomainResponse.indexOf("1")>0){
					luguoUI.openWnd({title:'系统消息',content:'已成功将聊天记录发送到你指定邮箱中！'});
					window.setTimeout(function(){artDialog({id:'dg_default'}).close();},2000);
				}else{
					luguoUI.openWnd({title:'系统消息',content:'聊天记录发送到你指定邮箱失败，请检查你的邮箱地址是否准确！'});
					window.setTimeout(function(){artDialog({id:'dg_default'}).close();},2000);
	}
}

luguoUI.sendEmailOK = function(){

	email = $("#myemail").val();
	this.setCookie("email",email);

	this.openWnd({title:'系统消息',content:'正在将聊天记录发送到你邮箱中，请稍候....'});

	var e_title = "";
	var e_cont = this.args.messList.join("");
	e_cont = encodeURIComponent(e_cont);
	e_cont = encodeURIComponent(e_cont);
	if(e_cont==""){
		alert("当前无聊天数据！");
		artDialog({id:'dg_default'}).close();
		return;
	}
	
	$("#frm_email").val(email);
	$("#frm_content").val(e_cont);

	AjaxCrossDomainRequest(this.url.sendemail, 'POST', 'cross_domain_demo', 'sendEmailcallback()');

}

luguoUI.ubb = function(s)
{
	if(s.indexOf("://") > 0)
	{
		//url
		s = s.replace(/(^|[^\"\'\]])(http|ftp|mms|rstp|news|https)\:\/\/([^\s\033\[\]\"\']+)/gi, "$1[url]$2://$3[/url]");
		//img
		s = s.replace(/\[url\](http\:\/\/\S+\.)(gif|jpg|jpeg|png)\[\/url\]/gi, "[img]$1$2[/img]");
	}
	if(s.match(/\[(\w+)([^\[\]\s]*)\].*\[\/\1\]/))
	{
		s = s.replace(/\[url\](.+?)\[\/url\]/gi,"<a href=$1 target=_blank>$1</a>");
		s = s.replace(/\[img\](.+?\.(?:gif|jpg|jpeg|png))\[\/img\]/gi, "<img src='$1' alt='$1'>");
		s = s.replace(/\[flash\](.+?\.swf)\[\/flash\]/gi, "<embed src='$1' quality=high wmode=transparent type='application/x-shockwave-flash' width=400 height=300></embed><br> FLASH: <a href='$1' target=_blank>$1</a><br>");
		s = s.replace(/\[wma\](.+?\.(?:wma|mp3))\[\/wma\]/gi, "<embed src='$1' height=40 AutoStart=0></embed><br> WMA: <a href='$1' target=_blank>$1</a><br>");
		s = s.replace(/\[color=([#0-9a-zA-Z]{1,10})\](.+?)\[\/color\]/gi, "<font color='$1'>$2</font>");
		s = s.replace(/\[b\](.+?)\[\/b\]/gi, "<b>$1</b>");
		s = s.replace(/\[i\](.+?)\[\/i\]/gi, "<i>$1</i>");
	}
	return s;
}

luguoUI.sendEmail = function(obj){
	this.openWnd({title:'系统消息',content:'输入你的邮箱地址：<input type="text" id="myemail" value="@" />&nbsp;&nbsp;<input type="button" onclick="luguoUI.sendEmailOK()" value="发  送" />'});
	if(this.getCookie("email")!=null)
		$("#myemail").val(this.getCookie("email"));
}

luguoUI.getClipboard = function() {
   if (window.clipboardData) {
      return(window.clipboardData.getData('Text'));
   }
   else if (window.netscape) {
      netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
      var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
      if (!clip) return;
      var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
      if (!trans) return;
      trans.addDataFlavor('text/unicode');
      clip.getData(trans,clip.kGlobalClipboard);
      var str = new Object();
      var len = new Object();
      try {
         trans.getTransferData('text/unicode',str,len);
      }
      catch(error) {
         return null;
      }
      if (str) {
         if (Components.interfaces.nsISupportsWString) str=str.value.QueryInterface(Components.interfaces.nsISupportsWString);
         else if (Components.interfaces.nsISupportsString) str=str.value.QueryInterface(Components.interfaces.nsISupportsString);
         else str = null;
      }
      if (str) {
         return(str.data.substring(0,len.value / 2));
      }
   }
   return null;
} 

luguoUI.sendFile = function(){
	this.openWnd({title:'发送文件',content:'<iframe src="http://www.faqee.com/crm/common/luguo.jsp" border="0" frameBorder="0" style="z-index: -1; padding-bottom: 0px; margin: 0px; padding-left: 0px; width: 100%; padding-right: 0px; height: 100%; top: 0px; padding-top: 0px; left: 0px;"></iframe>'});
}
luguoUI.sendPic = function(flag){
	var ppid = luguoUI.randID();
	this.openWnd({id:'mypicwnd',title:'系统消息',content:'输入地址（URL）：<input type="text" style="width:350px;" id="picurl_'+ppid+'" value="http://" />&nbsp;&nbsp;<input type="button" onclick="luguoUI.sendPicOK(\''+flag+'\',\''+ppid+'\')" value="插  入" />'});
}
luguoUI.sendPicOK = function(flag,ppid){
	var uu = "["+flag+"]"+$("#picurl_"+ppid).val()+"[/"+flag+"]";
	insertAtCaret($(".chatmsg").get(0),uu);
	artDialog({id:'mypicwnd'}).close();	
}
luguoUI.insert2Text = function(str){
	$(".chatmsg").focus();
	insertAtCaret($(".chatmsg").get(0),str);
}
window.sendEmailCallback = function(json){
	if(typeof(json.res)!="undefined"){
		if(json.res == "1"){
			alert("聊天记录已成功发送到你指定邮箱！");
		}else{
			alert("聊天记录发送到你指定邮箱失败，请重试！");
		}
	}else{
		alert("聊天记录发送到你指定邮箱失败，请重试！");
	}
}
  luguoUI.downloadMessages = function()
  {
		var date = new Date();

		var sDate = (date.getDate()).toString();
		if (date.getDate() < 10) sDate = "0" + (date.getDate()).toString();

		var sMonth = (date.getMonth() + 1).toString();
		if ((date.getMonth() + 1) < 10) sMonth = "0" + sMonth;

		var title  = date.getYear().toString() + sMonth + sDate+ "_" + Math.floor(Math.random()*1000000) + ".html";
	    var st = window.open();
		st.document.open("html","UTF-8");
		st.document.writeln("<html></body>");
		st.document.writeln($(".logbox").html());
		st.document.writeln("<br>");
		st.document.writeln("<center>  Copyright @ 2009 faqee.com</center>");
		st.document.writeln();
		st.document.writeln("</body></html>");
		var ret = st.document.execCommand ("SaveAs", true, title);
		st.close();	  
	  
  }
   
  luguoUI.pushMessage = function(message){
	  this.args.messList.push("<div>"+message.who+"："+message.message+"</div>");
  }
  luguoUI.formatStr = function(str) {
		var rtnStr = "";
		if (str < 10) rtnStr = "0" + str;
		else rtnStr =  str;
		return rtnStr;
  }
  luguoUI.showDate = function(){
		var date = new Date();
		var sDate = (date.getDate()).toString();
		if (date.getDate() < 10) sDate = "0" + (date.getDate()).toString();
		var sMonth = (date.getMonth() + 1).toString();
		if ((date.getMonth() + 1) < 10) sMonth = "0" + sMonth;
		var title  = date.getYear().toString() +"-"+ sMonth +"-"+ sDate;
		var date_str = this.formatStr(date.getHours())+":"+this.formatStr(date.getMinutes())+":"+this.formatStr(date.getSeconds());
		return date_str;
  }
  
  luguoUI.setCookie=function(sName, sValue) {
		date = new Date();
		date.setTime(date.getTime() + 2 * 365 * (24 * 60 * 60 * 1000));
		document.cookie = sName + "=" + (sValue!=""?escape(sValue):"") + "; expires=" + date.toGMTString();
	}
  luguoUI.getCookie=function(sName) {
		var aCookie = document.cookie.split("; ");
		for (var i = 0; i < aCookie.length; i++) {
			var aCrumb = aCookie[i].split("=");
			if (sName == aCrumb[0]) {
				if(typeof(aCrumb[1])=='undefined') return null;
				return unescape(aCrumb[1]);
			}
		}
		return null;
	}

luguoUI.changeRoom = function(pregid){
	var args=this.url.getrandg+"&gid="+webim.status.myname+"&pregid="+pregid+"&site=luguo&callback=window.getRandUser";
	args=args+"&cam="+webim.status.iscam;
	args=args+"&t="+new Date();
	this.exeCode(args);
}

luguoUI.newChat=function(){
	$(".logbox").empty();
	this.startChat(true);
}

luguoUI.showChatWnd = function()
{	
	var offset = $(".chatmsg").offset();
	$("#whotyping").css("left",offset.left+540);
	$("#whotyping").css("top",offset.top+38);
	$("#whotyping").show();
}

luguoUI.enableByID= function(vid){
	$("#"+vid).attr("disabled",false);
	if(vid!="chatarea"){
		$("#"+vid).removeClass("disableBtn");
		$("#"+vid).addClass("enableBtn");
	}
}
luguoUI.disableByID= function(vid){
	$("#"+vid).attr("disabled",true);
	if(vid!="chatarea"){
		$("#"+vid).removeClass("enableBtn");
		$("#"+vid).addClass("disableBtn");
	}
}
luguoUI.startChat = function(isInvite,roomid){	
	this.showChatWnd();
	//开始对话窗口
	//sysMessage("正在进行陌生人速配，请稍候...");
	if(typeof(roomid)=='undefined'){
		GCONFIG.roomid = luguoUI.randID();
		webim.joinRoom(GCONFIG.roomid,webim.status.nickname);
	}else{		
		webim.leaveRoom(GCONFIG.roomid);
		GCONFIG.roomid=roomid;
		webim.joinRoom(roomid,webim.status.nickname);
	}
	if(isInvite){
		sysMessage("正在寻找和连接陌生人，请稍候...");
		this.changeRoom(GCONFIG.cguest);
	}
}

window.getRandUser = function(json){
	if(typeof(json.res)!='undefined'){
		if(json.res == "0"){
			if(webim.status.iscam==1)
				playLive(luguoUI.getRandFlv(),"");
			else
				sysMessage("系统的在线人数太少，你已经无法和其他陌生人聊天了,等会再来吧！&nbsp;&nbsp;&nbsp;<a href=\"javascript:luguoUI.getJoke()\"><b><font color=blue>看会笑话等等人吧！</font></b></a>");				
		}else{
			var gg = json.guest;
			GCONFIG.scity = gg.addr;
			GCONFIG.cguest = gg.gid;
			sysMessage("系统速配（陌生人ID："+GCONFIG.cguest+"，你的ID："+webim.status.nickname+"），如果长时间速配不上陌生人，<a href=\"javascript:luguoUI.inviteLuguo();\">试试指定某个陌生人聊天！</a>");
			webim.roomInviteOne(GCONFIG.roomid,gg.gid+"@"+GCONFIG.url,"系统自动发出的邀请");
			playLive(GCONFIG.cguest,"我");
		}
	}else{
		sysMessage("系统的在线人数太少，你已经无法和其他陌生人聊天了,等会再来吧！");
	}
}
window.getInviteLuguoCallback = function(json){
	var str="";
	$.each(json.glist,function(i,n){
		var vaddr = n.addr;
		var vgid = n.gid;
		if(typeof(vgid)!='undefined'){
			if(vgid!=webim.status.myname)
				str = str+"<input type='radio' name='radio_invitethissite' id='inv_chk_"+vgid+"' value='"+vgid+"' /><label for='inv_chk_"+vgid+"'>"+vgid+"("+vaddr+")</label><br/>";	
		}
	});
	str = str+"<p><center><textarea rows=3 style='border:1px solid #CCC;width:98%' id='inv_txt_reason'>说几句邀请的理由吧！</textarea></center></p>";
	str = str+"<p><center><input type='button' onclick=\"luguoUI.sendInviteFromThisSite()\" value='发出邀请' /></center></p>";
	str = str+"<p><center style='font-size:12px;'><b>特别提醒：</b>当您发出邀请的同时，与当前陌生人的聊天将被中断，<br/>如果你想与多个陌生人聊天，请打开多个窗口！</center></p>";
	luguoUI.openWnd({title:'站内邀请(最多随机抽取10名)',content:str});
}
luguoUI.sendInviteFromThisSite = function(){
	var ss = $("input[name='radio_invitethissite']:checked").val();
	if($.trim(ss)==""){
		alert("请选中其中1个陌生人！");
		return;
	}
	if(typeof(GCONFIG.roomid)!='undefined')
		webim.leaveRoom(GCONFIG.roomid);
	GCONFIG.roomid = this.randID();
	webim.joinRoom(GCONFIG.roomid,webim.status.nickname);
	window.setTimeout(function(){webim.roomInviteOne(GCONFIG.roomid,ss+'@'+GCONFIG.url,$("#inv_txt_reason").val());},2000);

	this.openWnd({title:'系统消息',content:'您的聊天请求已被发送，请稍候……'});
	window.setTimeout(function(){artDialog({id:'dg_default'}).close();},2000);

	if(document.getElementById("homediv").style.display != 'none'){
		//$("#homediv").hide();
		//$("#chatdiv").show();
		
		var offset = $(".sendbtnwrapper").offset();
		$("#whotyping").css("left",offset.left+540);
		$("#whotyping").css("top",offset.top+38);
		$("#whotyping").show();
	}

}
luguoUI.inviteLuguo = function(){
	var guestid	= webim.status.myname;
	var args=this.url.guestlist;
	args=args+"&cam="+webim.status.iscam;
	args=args+"&callback=window.getInviteLuguoCallback";
	args=args+"&t="+new Date();
	this.exeCode(args);
}
window.setCamCallback = function(){};
luguoUI.setCam = function(cam){
	var guestid	= webim.status.myname;
	var args=this.url.cam;
	args=args+"&gid="+guestid;
	args=args+"&cam="+cam;
	args=args+"&callback=window.setCamCallback";
	args=args+"&t="+new Date();
	this.exeCode(args);	
}
luguoUI.openWnd = function(option){
	var vid = option.id||"dg_default";
	artDialog({id:vid}).close();	
	artDialog({id:vid,content:option.content,title:option.title});	
}
luguoUI.loadFile=function(filename, filetype){
	var fileref = null;
    if (filetype=="js"){
        fileref=document.createElement('script');
        fileref.setAttribute("type","text/javascript");
        fileref.setAttribute("src",filename);
   }
   else if (filetype=="css"){
        fileref=document.createElement("link");
        fileref.setAttribute("rel", "stylesheet");
        fileref.setAttribute("type", "text/css");
        fileref.setAttribute("href",filename);
   }
   if (typeof fileref != "undefined")
        document.getElementsByTagName("head")[0].appendChild(fileref)
}

luguoUI.script={
	exeScript:'faqee_script_exescript'
}
luguoUI.openWelcome=function(){
	artDialog({url:'help.html',title:'聊前须知',time:10,width:680,height:380,yesText:'我知道了(10秒后自动关闭)'},function(){});
}
luguoUI.createScriptTag=function(){                       
	var head = document.getElementsByTagName("head").item(0);  
	var script = document.createElement ("script");  
	script.src = '';
	script.id=this.script.exeScript;
	head.appendChild(script);   		
}

luguoUI.exeCode=function(args){

	if(document.getElementById(this.script.exeScript)){
		var el=document.getElementById(this.script.exeScript);
		var p=el.parentNode;  
		p.removeChild(el);   
	}
	var head = document.getElementsByTagName("head").item(0);  
	var script = document.createElement ("script");  
	script.src = args;
	script.id=this.script.exeScript;
	head.appendChild(script);   	
}

luguoUI.retGuestList=function(){
	var guestid	= webim.status.myname;
	var args=this.url.guestlist;
	args=args+"&callback=window.retGuestList";
	args=args+"&t="+new Date();
	this.exeCode(args);
}

window.retGuestList = function(json){
	luguoUI.refreshGuest();
	window.setInterval(function(){luguoUI.refreshGuest();},10000);
	$.each(json.glist,function(i,n){
		var vaddr = n.addr;
		var vgid = n.gid;
		var vstr = luguoUI.HTMLMODEL.glistinfor.replace(/#addr#/g,vaddr).replace(/#id#/g,vgid);
        $(".fex_rows").append(vstr);	
	});

}

luguoUI.refreshGuest=function(){
	var args=this.url.refreshGuest+"&callback=window.getOnlineUser";
	args=args+"&t="+new Date();
	this.exeCode(args);
}
window.getOnlineUser=function(json){
    var num = json.len;
	$("#online").html(num.split("/")[1]);
	$("#pair").html(parseInt(num.split("/")[0])/2);
	
}

luguoUI.sendMagicFace = function(id){
	  webim.sendGroupMessage(GCONFIG.roomid,"mfplaying`"+id+"`"+webim.status.myname+"");
}
luguoUI.playMagicFace = function(id){
 	document.getElementById("magic_facewrapper").style.display = '';
    var params = {};
	params.wmode = "transparent";
	var attributes = {};
    attributes.id = "magic_face";
    swfobject.embedSWF("mf/mf_"+id+".swf", "magic_face", "500", "350", "6", null,null,params,attributes); 
    //swfobject.createCSS("#magic_face", "postion:absolute;left:450px;top:350px;");
    window.setTimeout(function(){document.getElementById("magic_facewrapper").style.display = 'none';},5000);
}
luguoUI.setAway = function(){
	var ss = document.getElementById("chkAllowChat").checked;
	if(ss){
		document.getElementById("chkAutoAllowChat").checked = false;
		this.setCookie("autochat","0");
		this.setCookie("nochat","1");
	}else{
		this.setCookie("nochat","0");
	}
}
luguoUI.setNoSound = function(){
	var ss = document.getElementById("chkNoSound").checked;
	if(ss){
		this.setCookie("nosound","1");
	}else{
		this.setCookie("nosound","0");
	}
}

luguoUI.playSound = function(id){
	var vset = this.getCookie("nosound");
	if(vset==null||vset!="1"){
		var vurl = "wave/msgcome.swf";
		if(id == 'invite'){
			vurl = "wave/invite.swf";
		}else if(id == 'leave'){
			vurl = "wave/leave.swf";
		}else if(id=='online'){
			vurl = "wave/online.swf";
		}
	   document.getElementById("magic_face").style.display = ''; 
	   var so = new SWFObject(vurl, "mymovie", "1", "1", "6", "#336699", "wmode"); 
	   so.addParam("wmode", "transparent");
	   so.write("magic_face"); 
	   window.setTimeout(function(){document.getElementById("magic_face").style.display = 'none';},3000);	


	}
}

luguoUI.setAutoAllow = function(){
	var ss = document.getElementById("chkAutoAllowChat").checked;
	if(ss){
		document.getElementById("chkAllowChat").checked = false;
		this.setCookie("nochat","0");
		this.setCookie("autochat","1");
	}else{
		this.setCookie("autochat","0");
	}
}
luguoUI.setHome=function(obj, vrl){ 
        try{
                obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
        }
        catch(e){
                if(window.netscape) {
                        try {
                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
                        } 
                        catch (e)  {
                                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入\"about:config\"并回车\n然后将[signed.applets.codebase_principal_support]设置为'true'"); 
                        }
                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                        prefs.setCharPref('browser.startup.homepage',vrl);
                 }
        }
}
luguoUI.addFavor=function(sTitle,sURL)
{
    try
    {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e)
    {
        try
        {
            window.sidebar.addPanel(sTitle, sURL,"");
        }
        catch (e)
        {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}


luguoUI.jp = function(){
	try 
	{
	    if(!document.all){
	      alert("对不起，该功能只能在IE内核下的浏览器使用！");
	      return;
	    }
        var o = new ActiveXObject("kf37.HotKeyX");
        var ver="1.0";
        //获取版本号
        try{
          ver=o.version();
          if(ver==null || ver=="undefined") ver="1.0";
        }catch(e){   
        	 
        }  
        if(ver!="2.0")
        {
          if (confirm("需要安装最新版本的插件才能使用该功能，你是否确定需要安装？")) {
            window.open("activex/WEB_IM_Screen.exe");
          }          
        }else{
		  var f=o.jp();
		  o=null;
		}
	}catch(e){
        if (confirm("需要安装插件才能使用该功能，你是否确定需要安装？")) {
            window.open("activex/WEB_IM_Screen.exe");
        }
        return;
	}  
}
// here scroll
luguoUI.scroll = {
	flag:true,
	t:0
}
luguoUI.startli=function()
{
	if(this.scroll.flag){
		if(typeof(luguoUI.scroll.t)!='undefined'){
			clearInterval(luguoUI.scroll.t);
		}
		luguoUI.scroll.t = setInterval('luguoUI.autoScroll(".scrollDiv")',5000);
	}
}
luguoUI.autoScroll=function(obj){
	$(obj).find("ul:first").animate({
	marginTop:"-20px"
	},500,function(){
	$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
	});
}