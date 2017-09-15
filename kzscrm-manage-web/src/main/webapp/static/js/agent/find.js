//筛选条件
function find(id,value){
	$("#"+id+"").val(value);
	qurery();
}

function commit(){
	qurery();
}


//查询
function qurery(url){
	var _url =$("#table").attr("data-url");
	if(url==null){
		url=_url;
	}
	
	$("#table").bootstrapTable("removeAll");
	$("#table").bootstrapTable('refresh',{
		url:url
	},{
		query:$('#form').serialize()
	})
}
/*分页查询*/
function queryParams(params) {
	return $('#form').serialize() + '&order=' + params.order + '&offset=' + params.offset + '&limit=' + params.limit;

}

//代理状态统计 
function findAgent(id,value){
	$("#"+id+"").val(value);
	qurery();
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


