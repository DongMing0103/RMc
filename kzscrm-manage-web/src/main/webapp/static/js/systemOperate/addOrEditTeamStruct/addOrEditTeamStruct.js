var addOrEditTeamStruct={};
$(function(){
	//默认查国级的团队
	$.postJson('/crmteam/getCrmTeam',
		{level:1},
		function(data){
			if(data.code==0){
				if(data.rows.length>0){
					//默认查大区
					$.postJson('/baseArea/getBaseArea',
						{},
						function(data){
							if(data.code==0){
								if(data.rows.length>0){
									addOrEditTeamStruct.allArea=[];
									addOrEditTeamStruct.bigArea={};//大区
									addOrEditTeamStruct.province={};//省
									addOrEditTeamStruct.city={};//市
									addOrEditTeamStruct.district={};//区
									$.each(data.rows,function(index,item){
										if(item.areaLevel==1){
											addOrEditTeamStruct.bigArea[item.areaCode]=item;
											//启用组件
//											$('#bigArea').attr('disabled',null);
											//追加选项
											$('#bigArea option:last').after('<option value="'+item.areaCode+'">'+item.areaName+'</option>');
										}else if(item.areaLevel==2){
											addOrEditTeamStruct.province[item.areaCode]=item;
										}else if(item.areaLevel==3){
											addOrEditTeamStruct.city[item.areaCode]=item;
										}else if(item.areaLevel==4){
											addOrEditTeamStruct.district[item.areaCode]=item;
										}
									});
									addOrEditTeamStruct.allArea.push(addOrEditTeamStruct.bigArea);
									addOrEditTeamStruct.allArea.push(addOrEditTeamStruct.province);
									addOrEditTeamStruct.allArea.push(addOrEditTeamStruct.city);
									addOrEditTeamStruct.allArea.push(addOrEditTeamStruct.district);
									//编辑时显示
									var parentCrmTeamVOs=$('.parentCrmTeamVO');
									var teamId=$('#teamId').val();//当前编辑的团队ID
									$.each(parentCrmTeamVOs,function(index,item){
										$($('.leaderManager')[index]).show();
										var id=$(item).attr('id');//父ID
										//不能选择和编辑团队一样的团队
										$($('.leaderManager')[index]).find('select').val(id);
										$($('.leaderManager')[index]).find('select').change();
										if(id==teamId){
											$($('.leaderManager')[index]).find('select').val('');//设置成默认值
											$($('.leaderManager')[index]).find('select option[value="'+id+'"]').hide();//隐藏和自己编辑的团队一样的团队
											//如果有一个显示，就不隐藏
											$.each($($('.leaderManager')[index]).find('select option:not(:first)'),function(index,item){
												if($(item).css('display')=='none'){
													if(($(item).css('display')=='block')){
														$($('.leaderManager')[index]).hide();//隐藏整个组件
														return false;
													}
												}
											});
											return false;
										}
									});
									parentCrmTeamVOs.remove();
								}else{
									$('#bigArea').attr('disabled','disabled');
								}
							}else{
								layer.msg('数据获取失败');						
							}
						});
				}else{
					$('#bigArea').attr('disabled','disabled');
				}
			}else{
				layer.msg('数据获取失败');						
			}
		});
	
});
/**
 * 搜索上级主管
 */
addOrEditTeamStruct.searchTeam=function(level){
	//当前选择的组件
	var selectedCom=$('.leaderManager:eq('+(level-1)+')');
	//当前选择的组件值
	var currentSelected=selectedCom.find('select').val();
	$('#bigArea').attr('disabled',null);
//	var teamId=$('#teamId').val();//当前编辑的团队ID
	if($.isNotEmptyObject(currentSelected)){
		
		//删除当前之后的Option
		$('.leaderManager:gt('+(level-1)+')').find('select option:not(:first)').remove();
		//获取下一级的团队
		$.postJson('/crmteam/getCrmTeam',
				{level:level+1,parentId:currentSelected},function(data){
					if(data.code==0){
						if(data.rows.length>0){
							//当的下一个显示
							$('.leaderManager:eq('+level+')').show();
							//显示下一级的团队
							var nextLevelTeam=$('.leaderManager:eq('+level+')');
//						nextLevelTeam.show();
							//添加Option到下一级团队
							$.each(data.rows,function(index,item){
//								if(!($.isNotEmptyObject(teamId)&&item.id==teamId)){
									nextLevelTeam.find('select option:last').after('<option value="'+item.id+'" areaCode="'+item.areaCode+'">'+item.name+'</option>')
//								}
							});
						}else{
							$('.leaderManager:eq('+level+')').hide();
						}
					}else{
						layer.msg(data.desc);
					}
				},'json',$('.parentCrmTeamVO').length==0&&true);//如果是编辑，就是同步请求，否则异步
		//大于1是大区级团队操作,操作业务范围
		if(level>1){
			//已选择的团队业务范围
			var selectedAraeCode=selectedCom.find('select option:selected').attr('areaCode');
			//隐藏所有Option
			$('.businessScope:eq('+(level-2)+') select option:not(:first)').hide().attr('selected',null);
			//控制业务范围的显示
			addOrEditTeamStruct.searchArea((level-1),$('.businessScope:eq('+(level-2)+') select option[value="'+selectedAraeCode+'"]').show().attr('selected','selected').parent('select'));
			//删除业务范围的地区
			$('.businessScope:gt('+(level-1)+')').find('select option:not(:first)').remove();
		}
	}else{
		//显示所有Option
		$('.businessScope:eq('+(level-2)+') select option').show();
//		$('.businessScope').hide().find('select option').show().attr('selected',null);
//		$('.businessScope:eq('+(level-1)+')').show();
		//隐藏当前组件操作的下一个,并且删除option
		$('.leaderManager:gt('+(level-1)+')').hide().find('select option:not(:first)').remove();
//		$('.leaderManager:gt('+(level-1)+')').find('select option:not(:first)').remove();
	}
}
/**
 * 业务范围
 */
addOrEditTeamStruct.searchArea=function(areaLevel,self){
	//获取已选择的下一个组件的选择框,这是我要们渲染的直接数据
	var nextFormGroup=$(self).parents('.form-group').next('.form-group');
	//如果当前选择的有值
	if(!!$(self).val()){
		//获取对应范围
		var areas=addOrEditTeamStruct.allArea[areaLevel];
		//删除除第1个的所有子option
		nextFormGroup.find('select option:not(:first)').remove();

		//当前结点隔一个的组件全部隐藏,并且值清空
		$('.businessScope:gt('+(areaLevel)+')').hide();
		$('.businessScope:gt('+(areaLevel)+')').find('select').val('');
		
		//显示下一个范围的组件
		nextFormGroup.show();
		//遍历数据，如果等于一个组的，就显示
		for(area in areas){
			var areaObj=areas[area];
			if(areaObj.parentCode==$(self).val()){
				nextFormGroup.find('select option:last').after('<option value="'+area+'">'+areas[area].areaName+'</option>');
			}
		}
	}else{
		//除了当前点击的，其它都隐藏
		$('.businessScope:gt('+(areaLevel-1)+')').hide();
	}
}
/**
 * 新增团队
 */
addOrEditTeamStruct.addTeam=function(){
	var formJson=$('#form').serializeJson();
	$.postJson('/crmteam/addTeam',formJson,function(data){
		if(data.code==0){
			layer.msg(data.desc, {
		         time: 1500 //2秒关闭（如果不配置，默认是3秒）
			 }, function(){
				parent.layer.closeAll();
				parent.teamStructure.refresh();
			}); 
		}else{
			layer.msg(data.desc);
		}
	});
}
