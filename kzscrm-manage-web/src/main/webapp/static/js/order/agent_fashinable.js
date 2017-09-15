

// 操作
function operationFormatter(value, row, index) {
	var str = "<a class=look crmBusinessSplitDetailSee href=javascript:void(0); onclick='view(" + row.agentId
			+ ")'>查看</a>";
	return str;
}

// 查看页面
function view(agentId) {
	var url = '/crmbusinesssplitdetail/viewInfo/' + agentId;
	window.open(url);
}

/* 地理联动 */
$("#city").citySelect({
	url : "/static/js/cityselect/city.min.js",
	prov : "",// 省份
	city : "",// 城市
	dist : "",// 区县
	nodata : "none",// 当子集无数据时，隐藏select
	required : false
});