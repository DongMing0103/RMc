var agentAudit={};
agentAudit.queryParams=function(params){
	params['authenticationStatus']=0;//获取申请中的数据
	return params;
}


$(function(){
    $('#table').bootstrapTable();
});