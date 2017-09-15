var userAudit={};
userAudit.queryParams=function(params){
	params['checkStatus']=0;//获取申请中的数据
	return params;
}


$(function(){
    $('#table').bootstrapTable();
});