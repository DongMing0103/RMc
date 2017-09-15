var teamStructureFormatter={};
/*操作*/
teamStructureFormatter.operateFormatter=function(value, row, index) {
    return [
	    '<a class="crmTeamEdit"  href="javascript:void(0);" onclick="editData(\'编辑团队\',\'/crmteam/showTeamSet?id='+row.id+'\',\'768px\',\'700px\')">编辑</a>&nbsp&nbsp&nbsp',
	    '<a class="crmTeamDelete" href="javascript:void(0);" class="pl15" onclick="teamStructure.deleteTeam('+row.id+')">删除</a>',
    ].join('');
}
teamStructureFormatter.createTimeFormatter=function(value, row, index) {
	return dateUtils.formatToDate(value,'yyyy-MM-dd hh:mm:ss');
}
/**
 * 格式化业务范围
 */
teamStructureFormatter.areaNameFormatter=function(value, row, index) {
	if(row.areaCode!=86){
		return value.substring(3,value.length);
	}else{
		return value;
	}
}