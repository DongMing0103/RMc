/**
 * @author 黄霄仪
 * @date 2017年6月14日
 */
var adminManagement={};
adminManagement.queryParams=function(params){
	var formValue=$('#form').serializeJson();
	if(!formValue.delFlag){
		//查询所有状态的数据
		params['delFlagAll']=1;
	}else{
		delete params['delFlagAll'];
	}
	params['userType']=1;
	return $.extend(params,formValue);
};
/**
 * 重置表单
 */
adminManagement.reset=function(){
	$('#form').resetForm();
	$('#table').bootstrapTable('refresh');
};
adminManagement.searchClick=function(){
	$('#table').bootstrapTable('refresh');
};