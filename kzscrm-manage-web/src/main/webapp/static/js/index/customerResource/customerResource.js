var customerResource={};
customerResource.queryParams=function(params){
	var formJson=$('#form').serializeJson();
	//如果客户类型名称选项和客户名称都不为空，进行处理
	if($.isNotEmptyObject(formJson.clientTypeName)&&$.isNotEmptyObject(formJson.nameLike)){
		var clientTypeName=formJson.clientTypeName;
		delete formJson.clientType;
		delete formJson.clientTypeName;
		if(clientTypeName==1){
			formJson['clientType']=clientTypeName;
		}else{
			formJson['clientTypes']=[2,3,4];
		}
		
	}else if($.isNotEmptyObject(formJson.clientTypeName)){
		delete formJson.clientTypeName;
	}else if($.isNotEmptyObject(formJson.nameLike)){
		delete formJson.clientType;
	}
	
	return $.extend(params,formJson);
};

customerResource.reset=function(){
	$('#form').resetForm();
	$('#city .prov').change();//重置代理区域
	customerResource.query();
};
customerResource.query=function(){
	$('#table').bootstrapTable('refresh');
};
$(function(){
    $('#table').bootstrapTable();
});