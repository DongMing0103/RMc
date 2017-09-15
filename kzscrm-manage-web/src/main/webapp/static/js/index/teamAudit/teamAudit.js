
//命名空间
var teamAudit={};
/**
 * 重置查询条件
 */
teamAudit.reset=function(){
	$('.form-control').val('');
}
teamAudit.queryParams=function(params){
	var formValues=$('form').serializeArray();
	var formJson={};
	
	$.each(formValues,function(index,item){
		if(!$.isEmptyObject(item.value)){
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
teamAudit.search=function(){
	$('#table').bootstrapTable('refresh');
}

/**
 * 同意目标
 */
teamAudit.agree=function(id){
    var index=layer.open({
        title: '同意',
        content: '赞成该申报人的月度工作目标吗?',
        icon: 3,
        btn: ['确定','取消'],
        yes: function(index){
        	var data={};
        	data.where={};//查询条件
        	data.where['id']=id;
        	data['applyStatus']=2;//通过
        	$.ajax({
        		url:"/crmworktarget/targetCheckPass",
        		dataType:"json",
        		type:'post',
        		data:JSON.stringify(data),
        		contentType: "application/json; charset=utf-8",
        		success:function(data,textStatus,jqXHR){
        			teamAudit.search();//刷新页面
        			 layer.msg(data.desc, {
                         time: 1000
                     }, function () {
                         layer.close(index);//关掉弹出层
                     });
        			/* layer.alert(data.desc,function(index){
        				layer.close(index);//点击确定后，关闭弹出的窗口
        				//如果成功，就关闭申报窗口
        				if(data.code==0){
        					parent.layer.closeAll();
        				}
        			}); */
        		},
        		error:function(XMLHttpRequest, textStatus, errorThrown){
        			layer.msg("服务器异常", {
                        time: 1000
                    }, function () {
                        layer.close(index);//关掉弹出层
                    });
         		   alert(XMLHttpRequest.status);
        	       alert(XMLHttpRequest.readyState);
        	       alert(textStatus);
        		}
        	});
           
        }
    })
}
//以上函数初始化完成后加载表数据
$(function(){
	 $('#table').bootstrapTable();
});