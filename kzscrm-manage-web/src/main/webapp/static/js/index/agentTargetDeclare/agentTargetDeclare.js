$(function(){
	
});
//命名空间
var agentTargetDeclare={};
//代理商目标申报
agentTargetDeclare.submitTarget=function(){
	var data={};
	var formValues=$('form').serializeArray();
	//处理日期格式
	$.each(formValues,function(index,item){
		if(item.name=='applyMonth'){
			item.value=$dp.el.realValue;
		}
		data[item.name]=item.value;
	});
	//添加数据
	$.ajax({
		url:"/crmworktarget/addCrmWorkTarget",
		dataType:"json",
		type:'post',
		data:JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		success:function(data,textStatus,jqXHR){
			layer.alert(data.desc,function(index){
				layer.close(index);//点击确定后，关闭弹出的窗口
				//如果成功，就关闭申报窗口
				if(data.code==0){
					parent.layer.closeAll();		
					parent.query();
				}
			});
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
 		   alert(XMLHttpRequest.status);
	       alert(XMLHttpRequest.readyState);
	       alert(textStatus);
		}
	});
}
/**
 * @deprecated
 */
agentTargetDeclare.workMonthPicked=function(dp){
//	console.log($.isEmptyObject('1'));
//	console.log(dp.cal.date.y);
//	console.log(stringUtils.leftPad(dp.cal.date.M,2,'0'));
//	console.log(stringUtils.leftPad(dp.cal.date.d,2,'0'));
//	console.log(stringUtils.leftPad(dp.cal.date.H,2,'0'));
//	console.log(stringUtils.leftPad(dp.cal.date.m,2,'0'));
//	console.log(stringUtils.leftPad(dp.cal.date.s,2,'0'));
//	console.log(dp.cal);
//	console.log(dp);
//	dateUtils.formatToDate($(dp.el).val(),dp.dateFmt);
}
