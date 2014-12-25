
function getStat(){
	$.get(base+"/hao/blog/webStat",function(msg){
		$("#web_stat").html(msg);
	});
}

function getHotNews(){
	$.get(base+"/hao/blog/hotNews",function(msg){
		$("#hotNews").html(msg);
	});
}

function getNewSaveMsg(){
	$.get(base+"/hao/blog/newSaveMsg",function(msg){
		$("#newMsg").html(msg);
	});
}