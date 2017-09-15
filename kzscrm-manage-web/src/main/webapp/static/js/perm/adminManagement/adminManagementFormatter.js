/**
 * @author 黄霄仪
 * @date 2017年6月14日
 */
var adminManagementFormatter={};
/*序号*/
adminManagementFormatter.indexFormatter=function(value,row,index){
	return (index+1)*$('#table').bootstrapTable('getOptions').pageNumber;
}
adminManagementFormatter.delFalgFormatter=function(value,row,index){
	if(value==1){
		return "<a style='color:green;'>启用</a>";
	}else{
		return "<a style='color:red;'>注销</a>";
	}
}
adminManagementFormatter.operationFormatter=function(value,row,index){
	var str = '<div class=tddiv>'+
    '<span class=settingtda  crmPermUserSetting>请选择</span>'+
    '<ul class=settingtdmenu>';
	if(row.delFlag==0){
		str+='<li><a class="role_editAccount crmPermUserEditAccount" href="javascript:void(0);" onclick="editData(\'编辑\',\'crmUser/editPermAccount/'+row.id+'\',\'768px\',\'80%\')">编辑</a></li>';		
	}else if(row.delFlag==1){
		str+='<li><a class="role_del crmPermUserDelt" href="javascript:void(0);" onclick="operate('+row.id+',\'注销\',\'3\',\'确定要注销吗\',\'crmUser/deleteById?id='+row.id+'\',\'注销成功\')">注销</a></li>';
		str+='<li><a class="role_editAccount crmPermUserEditAccount1" href="javascript:void(0);" onclick="editData(\'编辑\',\'crmUser/editPermAccount/'+row.id+'\',\'768px\',\'80%\')">编辑</a></li>';
		str+='<li><a class="role_resetPassword crmPermUserResetPassword" href="javascript:void(0);" onclick="resetPassword('+row.id+');">重置密码</a>';
	}else{
		str+='<li><a class="role_del crmPermUserDelt1" href="javascript:void(0);" onclick="operate('+row.id+',\'注销\',\'3\',\'确定要注销吗\',\'crmUser/deleteById?id='+row.id+'\',\'注销成功\')">注销</a></li>';
		str+='<li><a class="role_editAccount crmPermUserEditAccount2" href="javascript:void(0);" onclick="editData(\'编辑\',\'crmUser/editPermAccount/'+row.id+'\',\'768px\',\'80%\')">编辑</a></li>';
	}
	str+='</ul></div>';
	return str;
}
adminManagementFormatter.createTimeFormatter=function(value,row,index){
	return dateUtils.formatToDate(value,'yyyy-MM-dd hh:mm:ss')
}