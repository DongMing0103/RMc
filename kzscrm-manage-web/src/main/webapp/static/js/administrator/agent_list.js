
function commit(){
	qurery();
}

////查询
//function qurery(url){
//	var _url =$("#table").attr("data-url");
//	if(url==null){
//		url=_url;
//	}
//	
//	$("#table").bootstrapTable("removeAll");
//	$("#table").bootstrapTable('refresh',{
//		url:url
//	},{
//		query:$('#form').serialize()
//	})
//}
//	/*分页 */ 
//	function queryParams(params){
//		return $("#form").serialize() + '&order='+ params.order+'&offset='+params.offset + '&limit='+params.limit;
//	}


//代理状态 0失效 1有效
function agentStatus(value,row,index){
	if(value==0){
		return "<span style='color:red'>失效</span>";
	}else if(value==1){
		return "<span style='color:green'>有效</span>";
	}else{
		return "-";
	}
}


/*性质*/
function agentNature(value,row,index){
	/*jQuery.fn.bootstrapTable.defaults.rowStyle=function(row,index){
		var current=new Date();
		var cooperationEndTime=new Date(row.cooperationEndTime);
		cooperationEndTime.setMonth(cooperationEndTime.getMonth()+1);
		//快到期一个月之内时，标红提示
		if(current.getFullYear()>cooperationEndTime.getFullYear()||current.getMonth()>=cooperationEndTime.getMonth()-1){
			return {
				css:{'color':'red'}
			}
		}else{
			return {
				css:{'color':'black'}
			}
		}
	}*/
	var a = "";
	if(value == 1){
		return "企业法人";
	}else if(value == 2){
		return "非企业法人";
	}else{
		return "-";
	}
}

/*操作*/
function operateFormatter(value, row, index) {
	var id = row.id;
	var str = '<div class=tddiv>'+
		        '<span class=settingtda>请选择</span>'+
		        '<ul class=settingtdmenu>'+
			        '<li><a class="crmAgentSee" href=crmagent/see?agentId='+row.id+' target=_blank>查看资料</a></li>'+
			        '<li><a class="crmAgentMonthGoalse" href=crmagent/monthGoals?agentId=' +row.id+  ' target=_blank>当月工作目标</a></li>'+
			        /*代理状态有效为解约操作，代理状态无效为续约操作*/
			        '<li><a class="crmAgentSurrender" href="javascript:void(0);" onclick="termination('+id+',1)">解约</a></li>'+
			        '<li><a class="crmAgentRenew" href="javascript:void(0);" onclick=editData(\'续约\',\'/crmagentapply/agentApplyInit?agentId='+row.id+'\',\'768px\',\'700px\')>续约</a></li>'+
			        /* ********************************************** */
			        '<li><a class="crmAgentEdit" href="javascript:void(0);" onclick="editData(\'编辑\',\'/crmagent/editInit?id='+id+'\',\'768px\',\'90%\')">编辑资料</a></li>'+
			        '<li><a class="crmAgentBusinessListInit" href=crmagent/businessListInit?agentId='+id+' target=_blank>业务员列表</a></li>'+
			        '<li><a class="crmAgentClientListInit" href=crmclientresource/clientListInit?agentId='+id+' target=_blank>客户列表</a></li>'+
			        /*'<li><a class="crmAgentLowerAgentListInit" href=crmagent/LowerAgentListInit?agentId='+id+'   target=_blank>下级代理列表</a></li>'+*/
			        '<li><a class="crmAgentViewInfoInit" href=crmbusinesssplitdetail/viewInfoInit?agentId='+id+' target=_blank>分账明细</a></li>'+
		        '</ul></div>';
    return str;
}


/*解约*/
function termination(id,updateType){
	//非1的就是批量
	var ids = new Array();
	if(updateType==1){
		if(!!!id){
			return layer.msg('请选择要解约的用户');
		}
		ids.push(id);
	}else{
		var idsElm = $('#table').bootstrapTable('getSelections');
		if(idsElm.length==0){
			return layer.msg('请选择要解约的用户');
		}
		$.each(idsElm,function(i){
			ids.push(idsElm[i].id);
		})
	}
	if(ids!=null){
		var data={ids:ids};
		console.log(data);
		layer.open({
			title: "解约",
			content: "确定要解除和该单位的合作关系吗?",
			icon:"3",
			btn: ["确定","取消"],
			yes: function(index, layero) {
				$.ajax({
					type:"POST",
					url:"/crmagent/updateStatus",
					contentType:'application/json;charset=UTF-8',
					data:JSON.stringify(data),
					success:function(data){
						layer.msg(data.desc);
						//按钮【按钮一】的回调
						$('#table').bootstrapTable('refresh');  //刷新table
						layer.close(index);//关闭弹窗
					}
				});         
			}
		})
	}
}

//编辑
function editData(title,url,width,height){
	$.get(url,function(result){
		if(result.indexOf('login-content')>-1){
			window.location.reload();
		}else{
			layer.open({
				type:2,
				title:title,
				shade:true,
				shade:[0.0,'#000'],
				area:[width,height],
				content:url
			});
			
		}
		
	});
	
	
}

























