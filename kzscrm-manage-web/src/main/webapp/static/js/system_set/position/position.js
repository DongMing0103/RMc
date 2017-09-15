var position={};

position.queryParams=function(params){
	return params;
}

/*新增、编辑*/
position.editPosition=function(positionId,positionName){
	$.get("/judgeLogin", function (result) {
        if (result.indexOf('login-content') > -1) {
            window.location.reload();
        } else {
        	var index=layer.open({
  		      	type:2,
  		      	title:"岗位职务",
  		      	shade:[0.5,'#000'],
  		      	area:["600px","400px"],
  		      	content:"/crmposition/initShow?url=/system_operate/add_position",
				success: function(layero, index){
					layer.getChildFrame('.first-input',index).focus();
					layer.getChildFrame('#positionId',index).val(positionId);
					layer.getChildFrame('#positionName',index).val(positionName);
				}
  			});
        }
    });
}

/*删除*/
position.deletePosition=function(id){
	layer.open({
        title: "删除",
        content: "确定要删除该岗位职务吗?",
        icon:"3",
        btn: ["确定","取消"],
        yes: function(index, layero) {
        	$.postJson("/crmposition/deletePosition",{id:id},function(data){
	       		 layer.alert(data.desc,function(index){
	       				layer.close(index);//点击确定后，关闭弹出的窗口
	       				//如果成功，就关闭申报窗口
	       				if(data.code==0){
	       					$('#table').bootstrapTable('refresh');//刷新表格
	       				}
	       				
	       		 	});
	        }); 
        }
    })
}


