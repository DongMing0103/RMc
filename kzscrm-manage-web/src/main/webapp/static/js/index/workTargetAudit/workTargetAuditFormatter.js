var workTargetAuditFormatter={};
workTargetAuditFormatter.operationFormatter=function(value, row, index) {
    var str="<a class=look href=javascript:void(0); onclick='workTargetAudit.agree("+ row.id +")'>同意</a>&nbsp;&nbsp;"+
    "<a class=look href=javascript:void(0); onclick=editData('工作目标修正','crmworktarget/addCrmWorkTargetInit?id="+row.id+"','768px','400px')>修正</a>";
    return str;
}
/**
 * 目标类型(1.团队 2.个人，3.代理商)
 */
workTargetAuditFormatter.targetType=function(value,row,index){
	if(value==1){
		return '团队';
	}else if(value==2){
		return '个人';
	}else if(value==3){
		return '代理商';
	}else{
		return value;
	}
}

//申报时间
workTargetAuditFormatter.creatTimeFormatter=function(value,row,index){
	return dateUtils.formatToDate(value,'yyyy-MM-dd hh:mm:ss');
}