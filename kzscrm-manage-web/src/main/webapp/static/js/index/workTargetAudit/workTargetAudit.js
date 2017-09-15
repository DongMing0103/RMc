var workTargetAudit={};

workTargetAudit.agree=function (id){
    /*var index=layer.open({
        title: '同意',
        content: '赞成该申报人的月度工作目标吗?',
        icon: 3,
        btn: ['确定','取消'],
        yes: function(index){
            layer.msg("操作成功", {
                time: 1000
            }, function () {
                layer.close(index);//关掉弹出层
            });
        }
    })*/

	layer.confirm("赞成该申报人的月度工作目标吗?",{
		title:"同意"
	},function(){
		$.ajax({
			type : "POST",
			url : "/crmworktarget/doUpdateWorkTarget",
			data : {id : id},
			dateType : "json",
			error : function () {
				layer.msg("超时或系统异常");
			},
			success : function (data) {
				if (data.code == 0) {
					$('#table').bootstrapTable('refresh');
					layer.msg(data.desc, {
						time : 1000
					});
					layer.closeAll();
				}
			}
		})
	})
	
}
workTargetAudit.queryParams=function(params){
	var formValues=$('form').serializeArray();
	var formJson={};
	
	$.each(formValues,function(index,item){
		if(!$.isEmptyObject(item.value)){
			if(item.name=='applyMonth'){
				item.value=$dp.el.realValue;
			}
			formJson[item.name]=item.value;
			
		}
	});
	//如果名称类型和名称值都不为空
	if(!$.isEmptyObject(formJson.nameType)&& !$.isEmptyObject(formJson.businessAgentName)){
		//1.代理商，2.业务员
		if(formJson.nameType=='1'){
			delete formJson.nameType;
			formJson['agentName']=formJson.businessAgentName;
			delete formJson.businessAgentName;
		}else{
			delete formJson.nameType;
			formJson['businessName']=formJson.businessAgentName;
			delete formJson.businessAgentName;
		}
	}else{
		delete formJson.nameType;
		delete formJson.businessAgentName;
	}
	params['applyStatus']=1;//申请中
	return $.extend( formJson, params );
}
/**
 * 搜索
 */
workTargetAudit.search=function(){
	$('#table').bootstrapTable('refresh');
}
$(function(){
    $('#table').bootstrapTable();
});