/**
 * @author luGaogao
 * @date 2017年7月19日
 */

$(function(){
	var userType = $('#userType').val();
	if(isNotNull(userType) && (userType == 3 || userType == 4)){
		var businessId = $('#businessID').val();
		console.log(businessId);
		var teamId = $('#businessID').attr('bTeamId');
		console.log(teamId);
		
		$('#teamId').val(teamId);
		$('.business'+teamId).show();
		$('#crmBusinessId').val(businessId);
	}
});

/*
 * 判断是否为空 
 * @return true 不为空
 */
function isNotNull(str){
	if(str && str != undefined && str != null && str != ''){
		return true;
	}
}

/**
 * 团队变动事件
 */
function changeTeam(self){
	$('.businessPO').hide();
	var teamId=$(self).val();
	
	if(isNotNull(teamId)){
		$('#crmBusinessId').val("");
		$('.business'+teamId).show();
	}
}