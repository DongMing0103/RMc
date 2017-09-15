customerResourceFormatter={};
/* 操作 */
/*customerResourceFormatter.operateFormatter = function(value, row, index) {
	var protect='';
	//只有散客才有保护按钮
	if(row.clientNature==1){
		protect='<li><a href="javascript:void(0);">保护</a></li>';
	}
	return [
			'<div class="dropdown">',
			'<a  href="javascript:void(0);" id="resource-operate" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">',
			'请操作', '<span class="caret"></span>', '</a>',
			'<ul class="dropdown-menu" aria-labelledby="resource-operate">',
			
            '<li><a class="crmClientCustomerResourceSee"  href="javascript:void(0);" onclick="view('+ row.id +')">查看</a></li>',
			'<li><a class="crmClientCustomerResourceEdit" href="javascript:void(0);" onclick="editData(\'编辑食堂\',\'/crmclientresource/editCrmClientResourceInit?crmClientResourceParam.id='+row.id+'\',\'768px\',\'500px\')">编辑</a></li>',
			'<li><a class="crmClientCustomerResourceStailrecord" href=/crmbusinesstailrecord/initClint?customerId='+row.id+' target=_blank>跟踪记录</a></li>',
			protect, '</ul>', '</div>' ]
			.join('');

};*/
/**
 * 查看页面
 */
function view (id) {
	var url = '/crmclientresource/viewInfo/' + id;
	window.open(url);
}

/**
 * 保护时间
 */
customerResourceFormatter.protectDeadlineFormatter=function(value,row,index){
	value=value!=null&&dateUtils.formatToDate(value,'yyyy-MM-dd hh:mm:ss')||null;
	return value;
};
/**
 * 编号
 */
customerResourceFormatter.numberFormatter=function(value,row,index){
	return index+$('#table').bootstrapTable('getOptions').pageNumber;
};
/**
 * 客户类型 1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
 */
customerResourceFormatter.clientTypeFormatter=function(value,row,index){
	value=(value==1&&"代理商")||(value==2&&"厂内食堂")||(value==3&&"校内食堂")||(value==4&&"独立食堂")||'未知类型';
	return value;
};
/**
 * 客户性质 1.散客，2.保护客户，3.正式客户
 */
customerResourceFormatter.clientNatureFormatter=function(value,row,index){
	value=(value==1&&"散客")||(value==2&&"保护客户")||(value==3&&"正式客户")||'未知类型';
	return value;
};
/* 操作 */
function operate(title, content,icon, yes, no, id, url, msg) {//参数
	var postData={};
	postData.id=id;

    layer.open({
        title: title,
        content: content,
        icon:icon,
        btn: [yes,no],
        yes: function(index, layero) {
        	$.ajax({
        		type:"POST",
        		url:url,
        		data:postData,
        		dataType:"json",
        		success:function(data){
        			if(data && data.code == 0){
        				layer.msg(msg);
        				$('#table').bootstrapTable('refresh');  //刷新table
        				//刷新index页面
        				window.open('http://'+window.location.host+'/index','huangxiaoyi');
        				window.focus();
	    			}else{
	    				layer.msg(data.desc);
	    			}
					layer.close(index);//关闭弹窗
        		}
        	});               
        }
    })
}