/**
 * @author luGaogao
 * @date 2017年7月19日
 */
var editAgent = {};

/**
 * 团队选择事件
 */
editAgent.teamChange=function(self){
	$('.crmBusiness').val("");
	var level=$(self).attr("level");
	var teamId=$(self).val();
	var isNext=false;//是否有下一个选择组件
	
	//控制向前两级以上的按钮选择事件，如果选择了，那就是当前选择组件的的下两个开始都隐藏
	var selectTeams=$('.selectTeam');
	//控制向前两级按钮选择事件，如果触发，则隐藏当前的下二个级别的标签
	$.each(selectTeams,function(index,selectTeam){
		var levelTemp=$(selectTeam).attr('level');
		//如果当前的代理级别大于等于当前级别的下二个级别的标签，就隐藏
		if(parseInt(levelTemp)>=(parseInt(level)+2)){
			//清空当前select的下一个的值
			$(selectTeam).parent().prev().children().val("");
			
			$(selectTeam).parent().hide();
		}
	});
	
	//关联业务员
	$('.businessPO').hide();
	$('.business'+teamId).show();
	
	//下一级团队结点
	var nextSelectTeam=$('.selectTeam[level="'+(parseInt(level)+1)+'"]').val("");
	//遍历下一个节点，找到当前节点相对应的选项(option)
	var nextSelectTeamOptions=$(nextSelectTeam).find('option:not(:first)');
	$.each(nextSelectTeamOptions,function(index,item){
		var parentId=$(item).attr('parentid');
		if(teamId==parentId){
			$(item).show();
			isNext=true;
		}else{
			$(item).hide();
		}
	});
	//如果不为空，并且下一个组件有可选的数据就显示
	if(!$.isEmptyObject(teamId)&&isNext){
		
		$(nextSelectTeam).parent().show();
	}else{
		$(nextSelectTeam).parent().hide();
	}
};




$(function(){
	//归并团队数据
	$.each($('.selectTeam'),function(index,item){
		var level=$(item).attr('level');
		//console.log(level);
		//各级团队数据
		var options=$('.team'+level).show().remove();
		//console.log(options.attr('teamName'));
		$(item).append(options);
		
		/*if($.isEmptyObject($(item).val())){
			$(item).parent().hide();
		}else if($.isNotEmptyObject($(item).val())|| $(item).attr('parentid')==$($('.selectTeam')[index-1]).val()){//如果不为空，或者就算为空，但只要当前的下拉控件是的代理商ID是上一个控制选择的值时，亦展示
			$(item).parent().show();
		}
		
		if(index>0){
			//上级团队选中的团队ID(当前结点的父团队ID)
			var teamId_oneLevel=$($('.selectTeam')[index-1]).find('option:selected').val();
			$(item).find('option:not(:first)').hide();//隐藏所有选项
			//遍历所有option，如果等于父团队ID，就显示
			$.each($(item).find('option:not(:first)'),function(index1,item1){
				if(teamId_oneLevel==$(item1).attr('parentid')){
					$(item1).show();
				}
			});
		}*/
		if(index==0){
			$('.selectTeam:not(:first)').parent().hide();
		}
		
	});
	
	//团队数据回显
	var tlevel = $("#tlevel").val();
	for(var i = 1 ; i <= tlevel ; i++){
		var tid = $('.TP'+i).val();
		$('.sTeam'+i).val(tid).parent().show();
	}
	
	$('.sTeam'+tlevel+1).val("").parent().show();
	
	//业务员数据回显
	var teamId = $('.TP'+tlevel).val();
	$('.business'+teamId).show();
	var businessId = $("#businessId").val();
	$('.crmBusiness').val(businessId);
	
});