$(function(){
});
//命名空间
var teamAuditFormatter={};
//格式化订单金额
teamAuditFormatter.orderMoneyFormatter=function(value,row,index){
	return numberUtils.formatCurrency(value,2);
}
//格式化目标类型
teamAuditFormatter.targetTypeFormatter=function(value,row,index){
	if(value==1){
		value='团队';
	}else if(value==2){
		value='个人';
	}else if(value==3){
		value='代理商';
	}else{
		value='未定义'
	}
	return value;
}
//格式化申报日期
teamAuditFormatter.creatTimeFormatter=function(value,row,index){
	return dateUtils.formatToDate(value,'yyyy-MM-dd hh:mm:ss');
}

//格式化操作按钮
teamAuditFormatter.operationFormatter=function(value,row,index){
	var str="<a class=look crmWorkTargetAuditAgree href=javascript:void(0); onclick='teamAudit.agree("+row.id+")'>同意</a>&nbsp;&nbsp;"+
    "<a class=look crmWorkTargetAuditEditData href=javascript:void(0); onclick=editData('工作目标修正','crmworktarget/crmWorkTargetModifyInit?id="+row.id+"','768px','400px')>修正</a>";
	return str;
}