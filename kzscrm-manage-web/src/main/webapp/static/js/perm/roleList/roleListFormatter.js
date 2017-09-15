var roleListFormatter={};

roleListFormatter.type=function(value,row,index){
	if(value==1){
		return "平台";
	}else if(value==2){
		return "代理商";
	}else{
		return value;
	}
}

roleListFormatter.delFlag=function(value,row,index){
	if(value==1){
		return "启用";
	}else if(value==0){
		return "<a style='color:red'>停用</a>";
	}
}

/* 操作 */
roleListFormatter.operateFormatter=function(value, row, index) {	
	var edit='<a class="pl15 role_edit  crmRoleEdit" href="javascript:void(0);" onclick="editData(\'编辑\',\''+roleListVar.editUrl+'/'+row.id+'\',\'40%\',\'70%\')">编辑</a>';
	var cancel='<a class="crmRoleCancel" href="javascript:void(0);" onclick="roleList.operate('+row.id+',\'注销\',\'3\',\'确定要注销吗\',\''+roleListVar.cancelUrl+'/'+row.id+'\',\'注销成功\')">注销</a>&nbsp;';
	if(row.delFlag==0){
		return [edit].join('');				
	}else{
		return [cancel,edit].join('');
	}
}