

/**
 * 分页查询
 * @param params
 * @returns
 */
function queryParams(params) {
	return $('#form').serialize() + '&order=' + params.order + '&offset=' + params.offset + '&limit=' + params.limit;
}

/* 操作 */
function operateFormatter(value, row, index) {
	var str = '<div class=tddiv>'
			+ '<span class=settingtda>请选择</span>'
			+ '<ul class=settingtdmenu>'
			/*+ '<li><a href="/static/html/system_operate/team_member.html" target="_blank">团队成员</a></li>'*/
			+ '<li><a class="crmTeamMem" href="javascript:void(0)" title="团队成员" onclick="teamMem(' + row.id + ')" target="_blank" >团队成员</a></li>'
			/*+ '<li><a href="/static/html/system_operate/work_targe.html" target="_blank">当月工作目标</a></li>'*/
			+ '<li><a class="crmTeamWorkTarget" href="javascript:void(0)" title="当月工作目标" onclick="workTarget(' + row.id + ')" target="_blank">当月工作目标</a></li>'
			/*+ '<li><a href="/static/html/system_operate/customer_list.html" target="_blank">客户列表</a></li>'*/
			+ '<li><a class="crmTeamCustomer"  href="javascript:void(0)" title="客户列表" onclick="customer(' + row.id + ')">客户列表</a></li>'
			+ '</ul></div>';
	return str;
}

/**
 * 当月工作目标
 * @param id
 */
function workTarget (id) {
	var url = '/crmteam/workTarget/' + id;
	window.open(url)
}

/**
 * 客户列表
 * @param id
 */
function customer (id) {
	var url = '/crmteam/customer/' + id;
	window.open(url);
}

/**
 * 团队成员
 * @param id
 * @returns
 */
function teamMem (id) {
	var url = '/crmteam/teamMember/' + id;
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

/**
 * 格式化业务经理
 * @param value
 * @param row
 * @param index
 * @returns
 */
function business(value, row, index) {
	var formatter = row.businessName;
	if (row.businessName === null || row.businessName === "") {
		formatter = "-";
	}
	return formatter;
}
/**
 * 团队人数
 * @param value
 * @param row
 * @param index
 * @returns
 */
function team(value, row, index) {
	var formatter = row.teamNumber;
	if (row.teamNumber === null || row.teamNumber === "") {
		formatter = "-";
	}
	return formatter;
}
/**
 * 发展代理商
 * @param value
 * @param row
 * @param index
 * @returns
 */
function agent (value, row, index) {
//	debugger;
	var formatter = row.agentNum;
	if (row.agentNum === null || row.agentNum === "") {
		formatter = "-";
	}
	return formatter;
}
/**
 * 发展食堂
 * @param value
 * @param row
 * @param index
 * @returns
 */
function canteen(value, row, index) {
	var formatter = row.canteenNum;
	if (row.canteenNum === null || row.canteenNum === "") {
		formatter = "-";
	}
	return formatter;
}
/**
 * 订单数量
 * @param value
 * @param row
 * @param index
 * @returns
 */
function completeOrder(value, row, index) {
	var formatter = row.completeOrderMoney;
	if (row.completeOrderMoney === null || row.completeOrderMoney === "") {
		formatter = "-";
	}
	return formatter;
}
/**
 * 订单金额
 * @param value
 * @param row
 * @param index
 * @returns
 */
function money(value, row, index) {
	var formatter = row.realMoney;
	if (row.realMoney === null || row.realMoney === "") {
		formatter = "-";
	}
	return formatter;
}


