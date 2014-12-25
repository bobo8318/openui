function sendmsg(base){
	var msg = $("#chatarea").val();
	var code = $("#code").val();
	if(msg==''||msg==null){
		$("#log").append("<p class='pull-left'><font color='blue'>iRobot Say:</font><strong>请先输入内容再和我聊</strong></p><br><hr>");
		$("#chatarea").focus();
	}
	else if(msg=='clear'||msg=='清除'){
		$("#log").html("");
		$("#chatarea").val("");
	}else {
	//用户说
	
	//robot说
	$.post(base+"hao/iRobot/chat",{question:msg,code:code},function(data){
		//取nodeid
		if(data.indexOf(';')!=-1){
			var id = data.substring(0,data.indexOf(';'));
			//alert(id);
			$("#code").val(id);
			var chatmsg = data.substring(data.indexOf(';')+1,data.length);
			//alert(chatmsg);
			$("#log").append("<p class='pull-left'><font color='blue'>iRobot Say:</font><code><strong>"+chatmsg+"</strong></code></p><br><hr>");
		}else{
			$("#log").append("<p class='pull-left'><font color='blue'>iRobot Say:</font><code><strong>"+data+"</strong></code></p><br><hr>");
		}
	});
	
	if(msg.indexOf('pwd')>=0) msg = 'pwd ******';
	
	
	var str = "<p class='pull-left'><font color='red'>U Say: </font><strong>"+msg+"</strong></p><br><hr>";
	$("#log").append(str);
	$("#chatarea").val("");
	
	}
}