var roleList={};

roleList.queryParams=function(params){
	params.delFlagAll=1;//查询全部数据
	return $.extend(params,$('#form').serializeJson());
};
/**
 * 搜索按钮
 */
roleList.search=function(){
	$("#table").bootstrapTable('refresh');
};

roleList.reset=function(){
	$('#form').resetForm();
};
//注销
roleList.operate=function(id, title, icon, content, url, msg){
	var index=layer.open({
		title: title,
		icon: icon,
		content: content,
		btn: ['确定','取消'],
		yes: function(){
			$.ajax({
	      		url:url,
	      		type:"post",
	      		dataType:"json",
	      		data:"id="+id,
	      		success:function(data){
	      			console.log(data);
	      			if(data.code==0){
	      				$('#table').bootstrapTable('refresh');	
						layer.close(index);
	   				}
	      			layer.msg(data.desc); 
	      		}
	      	})
		}
	})
}
$(function(){
    $('#table').bootstrapTable();
    
});