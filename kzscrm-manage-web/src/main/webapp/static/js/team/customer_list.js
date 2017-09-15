/**
 * 合作状态查询
 */
function findClientNature(id, value) {
	$("#" + id + "").val(value);
	querys();
}

/**
 * 条件查询
 */
function querys() {
	var prov = $(".prov").find("option:selected").text();
	var city = $(".city").find("option:selected").text();
	var dist = $(".dist").find("option:selected").text();
	if ("请选择" == prov) {
		prov = "";
	}
	if ("请选择" == city) {
		city = "";
	}
	if ("请选择" == dist) {
		dist = "";
	}

	$("input[name=address]").val(prov + city + dist);
	var _url = $('.table').attr("data-url");
	$('.table').bootstrapTable('removeAll');
	$('.table').bootstrapTable('refresh', {
		url : _url
	}, {
		query : $('#form').serialize()
	});
}

/* 分页查询 */
function queryParams(params) {
	return $('#form').serialize() + '&order=' + params.order + '&offset='
			+ params.offset + '&limit=' + params.limit;
}

/**
 * 客户分类格式化
 */
function type(value, row, index) {
	if (value == 1) {
		return "代理商";
	} else if (value == 2) {
		return "厂内食堂";
	} else if (value == 3) {
		return "校内食堂";
	} else {
		return "独立食堂";
	}
}

/**
 * 合作状态格式化
 */
function nature(value, row, index) {
	if (value == 1) {
		return "散客";
	} else if (value == 2) {
		return "保护客户";
	} else {
		return "正式客户";
	}
}

$(function() {
	$('#table').bootstrapTable();
});

/* 操作 */
function operateFormatter(value, row, index) {
	if (row.clientType == '1') { // 代理商
		var str = '<div class=tddiv>' + '<span class=settingtda>请选择</span>'
				+ '<ul class=settingtdmenu>'
				+ '<li><a href="javascript:void(0);" onclick="view('+ row.id +')">查看</a></li>'
				// + '<li><a href="javascript:void(0);">学校信息</a></li>'
				// + '<li><a href="javascript:void(0);">工厂信息</a></li>'
				// + '<li><a href="javascript:void(0);"
				// onclick="editData(\'编辑\',\'/crmcanteenbaseinfo/addInit\',\'768px\',\'500px\')">编辑</a></li>'
				// + '<li><a href="javascript:void(0);">跟踪记录</a></li>'
				// + '<li><a href="javascript:void(0);">延长保护时间</a></li>'
				+ '</ul>' + '</div>';
	} else if (row.clientType == '2') { // 厂内食堂
		var str = '<div class=tddiv>' + '<span class=settingtda>请选择</span>'
				+ '<ul class=settingtdmenu>'
				+ '<li><a href="javascript:void(0);" onclick="view('+ row.id +')">查看</a></li>'
				+ '<li><a href="javascript:void(0);" onclick="SchoolOrFactory('+ row.id +')">工厂信息</a></li>'
				+ '</ul>' + '</div>';
	} else if (row.clientType == '3') { // 校内食堂
		var str = '<div class=tddiv>' + '<span class=settingtda>请选择</span>'
				+ '<ul class=settingtdmenu>'
				+ '<li><a href="javascript:void(0);" onclick="view('+ row.id +')">查看</a></li>'
				+ '<li><a href="javascript:void(0);" onclick="SchoolOrFactory('+ row.id +')">学校信息</a></li>'
				+ '</ul>' + '</div>';
	} else { // 独立食堂
		var str = '<div class=tddiv>' + '<span class=settingtda>请选择</span>'
				+ '<ul class=settingtdmenu>'
				+ '<li><a href="javascript:void(0);" onclick="view('+ row.id +')">查看</a></li>'
				+ '</ul>' + '</div>';
	}

	return str;
}

/**
 * 查看
 * @param id
 * @returns
 */
function view (id) {
	var url = '/crmteam/view/' + id;
	window.open(url);
}

/**
 * 学校信息
 */
function SchoolOrFactory (id) {
	var url = '/crmteam/SchoolOrFactory/' + id;
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
