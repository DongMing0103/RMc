/**
 * @author 黄霄仪
 * @date 2017年7月4日
 */
var addResourceOrEdit={};

addResourceOrEdit.getSecondMenu=function(self){
	var oneMenuId=$(self).val();
	if($.isNotEmptyObject($(self).val())){
		$('#nameSecond :not(option:first)').remove();
		$('#nameSecond option:last').after(twoMenu[oneMenuId].join(''));
	}
}

addResourceOrEdit.getSources=function(self){
	
}

