
function commit(){
	alert(111);
	query(url);
}
//根据条件查询
function query(url){
	var _url=$('#table').attr("data-url");
	alert(_url);
	if(url==null){
		url=_url;
	}
	
	
	$('#table').bootstrapTable('removeAll'); 
	$('#table').bootstrapTable('refresh', {
		url : url
	}, {
		query : $('#form').serialize()
	});
}

//重置
$("#reset").click(function(){  
	alert("你点击了重置按钮");
  	
}); 

/*表格分页*/
function queryParams(params) {
	return $('#form').serialize() + '&order=' + params.order + '&offset=' + params.offset + '&limit=' + params.limit;
}