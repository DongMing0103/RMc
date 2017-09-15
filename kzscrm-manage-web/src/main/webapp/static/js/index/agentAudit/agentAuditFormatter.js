agentAuditFormatter={};
/* 操作 */
agentAuditFormatter.operationFormatter=function(value, row, index) {
    var str = "<div class=tddiv>"+
        "<span class=settingtda>请选择</span>"+
        "<ul class=settingtdmenu style='width:130px'>"+
        "<li><a href='#'onclick=\"editData('查看代理商资料','/crmagent/lookupInit/"+row.agentId+"','768px','90%')\">查看代理商资料</a></li>"+
        "<li><a href=javascirpt:void(0); onclick=\"editData('代理商审核','/crmagent/reviewAgent/"+ row.id +"','768px','90%')\">审核</a></li>"+
        "</ul></div>";
    return str;

}
agentAuditFormatter.registerTimeFormatter=function(value,row,index){
	return dateUtils.formatToDate(value,'yyyy-MM-dd hh:mm:ss');
}
agentAuditFormatter.areaName=function(value,row,index){
	return value.substring(3,value.length);
}

agentAuditFormatter.address=function(value,row,index){
	return value.substring(4,value.length);
}