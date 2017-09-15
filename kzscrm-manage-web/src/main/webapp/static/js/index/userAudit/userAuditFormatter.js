var userAuditFormatter={};
/* 操作 */
userAuditFormatter.operationFormatter=function(value, row, index) {
	//如果非独立食堂，可以查看客户资料
	var clientResource='';
	if(row.clientType==2){
		clientResource="<li><a href=javascript:void(0);  onclick=editData('查看用户资料','/crmcanteenapply/lookupEnterpriseOrSchool/"+row.id+"','768px','400px')>查看企业信息</a></li>";
	}else if(row.clientType==3){
		clientResource="<li><a href=javascript:void(0);  onclick=editData('查看用户资料','/crmcanteenapply/lookupEnterpriseOrSchool/"+row.id+"','768px','400px')>查看学校信息</a></li>";
	}
	 var str = 	"<div class=tddiv>"+
     "<span class=settingtda>请选择</span>"+
     "<ul class=settingtdmenu>"+
     "<li><a href=javascript:void(0); onclick=editData('查看用户资料','/crmcanteenapply/newClientCheckOfCheck/"+row.id+"','768px','400px')>查看用户资料</a></li>"+
     clientResource+
     "<li><a href=javascript:void(0); onclick=editData('审核','/crmcanteenapply/newClientCheckOfCheck/"+row.id+"','768px','400px')>审核</a></li>"+
     "</ul></div>";
	 return str;

}
userAuditFormatter.applyTimeFormatter=function(value,row,index){
	return dateUtils.formatToDate(value,'yyyy-MM-dd hh:mm:ss');
}
//客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
userAuditFormatter.clientTypeFormatter=function(value,row,index){
	if(value==1){
		value="代理商";
	}else if(value==2){
		value="厂内食堂";
	}else if(value==3){
		value="校内食堂";
	}else if(value==4){
		value="独立食堂";
	}else{
		value="未定义";
	}
	return value;
}