var teamStructure={};



/*新增、编辑*/
teamStructure.editTeam=function(id){
//	alert(id);
	$.get("/judgeLogin", function (result) {
		/*console.log(result);*/
        if (result.indexOf('login-content') > -1) {
            window.location.reload();
        } else {
        	var title = "团队设置";
        	if(id!='' && id != null && id>0){
        		title = "编辑团队";
        	}
        	var index=layer.open({
  		      	type:2,
  		      	title:title,
  		      	shade:[0.5,'#000'],
  		      	area:["768px","700px"],
  		      	content:"crmteam/showTeamSet?id="+id,
				success: function(layero, index){
					layer.getChildFrame('.first-input',index).focus();
				}
  			});
        }
    });
}
teamStructure.refresh=function(){
	$('#table').bootstrapTable('refresh');
}

/*删除*/
teamStructure.deleteTeam=function(id){
	layer.open({
        title: "删除",
        content: "确定要删除该团队结构吗?",
        icon:"3",
        btn: ["确定","取消"],
        yes: function(index, layero) {
        	$.postJson("/crmteam/deleteTeam",{id:id},function(data){
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
/*新增*/
teamStructure.addTeam=function(){//debugger;
	console.log($("#from").serialize());
	$("#prov1").val($("#prov").val());
	$("#city1").val($("#city").val());
	//alert($("#prov").val());
	$.ajax({
		type:'POST',
		data:$("#from").serialize(),
		dataType:"json",
		url:"/crmteam/addTeam",
		success:function(data){
			if(data.code==0){
				layer.msg(data.desc, {
			         time: 2000 //2秒关闭（如果不配置，默认是3秒）
				 }, function(){
							parent.layer.closeAll();
				});   parent.$("table").bootstrapTable('refresh');
				parent.$("#table").bootstrapTable('refresh');
			}else{
				layer.msg(data.desc);
			}
		}
	})
}
teamStructure.provinceSee=function(index){
	/*省*/
	$.ajax({
		type:"POST",
		data:{region:$("#region").val()},
		dataType:"json",
		url:"/crmteam/provinceSee",
		success:function(data){debugger;
			var areaTest = "";
			//编辑初始化
			var provCode= $("#provinceCode").val();
			var provName= $("#provinceName").val();
			var _html='<option value="">--请选择省--</option>';
			for(var i=0;i<data.rows.length;i++){
				if(provCode!=null && provCode!=""){
					if(provCode==data.rows[i].areaCode){
						_html+='<option value="'+data.rows[i].areaCode+'" selected="selected">'+data.rows[i].areaName+'</option>';
					}else{
						_html+='<option value="'+data.rows[i].areaCode+'">'+data.rows[i].areaName+'</option>';
					}
				}else{
					_html+='<option value="'+data.rows[i].areaCode+'">'+data.rows[i].areaName+'</option>';
				}
			}
			console.log("省areaCode = "+$("#region").val());
			if(index==2){
				teamStructure.teamSee();
			}
			if(index!=null && index!=""){
				if($("#region").val()==null || $("#region").val()==""){
					 $(".prov").removeAttr("disabled");
					 $('#city').citySelect({
							url:"/static/js/cityselect/city.min.js",
							prov:"", //省份 浙江省
					        city:"", //市	
					        dist:"", //区
							nodata:"none" //当子集无数据时，隐藏select  
			  			}); 
				}
			}
			$("#province").html(_html);
		}
	})
}
var aa=0;
teamStructure.teamSee=function(priv){
	/*团队*/
	var areaCode = "";
//	if($("#teamAreaCode").val()!=null && $("#teamAreaCode").val()!=""){
//		areaCode=$("#teamAreaCode").val();
//	}else{
		
//	}
	if(priv!=null && priv!=""){
		areaCode =priv;
	}else{
		areaCode=$("#province").val();
	}
	console.log(areaCode)
	$.ajax({
		type:'POST',
		data:{areaCode:areaCode},
		dataType:"json",
		url:"/crmteam/teamSee",
		success:function(data){debugger;
			var teamName=$("#teamNameUpdate").val();
			var teamId = $("#teamId").val();
			var _html='<option value="">--请选择团队--</option>';
			for(var i=0;i<data.rows.length;i++){
				if(teamId!=null && teamId!=""){
					if(teamId==data.rows[i].id){
						_html+='<option value="'+data.rows[i].id+'" selected="selected">'+data.rows[i].name+'</option>';
					}else{
						_html+='<option value="'+data.rows[i].id+'">'+data.rows[i].name+'</option>';
					}
				}else{
					_html+='<option value="'+data.rows[i].id+'">'+data.rows[i].name+'</option>';
				}
			}
			if($('#id').val()==null || $('#id').val()==""){
//			/* 地理信息  */
			   $('#city').citySelect({
					url:"/static/js/cityselect/city.min.js",
					prov:areaCode, //省份 浙江省
			        city:"", //市	
			        dist:"", //区
					nodata:"none" //当子集无数据时，隐藏select  
	  			}); 
			}
		   if(areaCode!=null && areaCode!=""){
			   $(".prov").attr("disabled","disabled")
		   }else{
			    $(".prov").removeAttr("disabled");
		   }
			$("#parentId").html(_html);
			if(aa==1){
				 $('#city').citySelect({
						url:"/static/js/cityselect/city.min.js",
						prov:areaCode, //省份 浙江省
				        city:"", //市	
				        dist:"", //区
						nodata:"none" //当子集无数据时，隐藏select  
		  			}); 
			}
			aa =1;
		}
	})
}
