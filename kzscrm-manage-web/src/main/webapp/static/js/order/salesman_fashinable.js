/**
 * 业务员分账一览页面js
 */

// 筛选条件
function findBusiness(id, value) {
	$("#" + id + "").val(value);
	query();
}

$(function() {
	$('#table').bootstrapTable();
});

// 职位状态
function opFormatter(value, row, index) {
	var a = "";
	if (value == 0) {
		var a = '<span>离职</span>';
	} else if (value == 1) {
		var a = '<span>在职</span>';
	} else {
		a ='<span>-</span>'
	}
	return a;
}

//操作
function operationFormatter(value, row, index) {
	var str = "<a class=look crmAgentSplitDetailSee href=javascript:void(0); onclick='view(" + row.businessId+ ")'>查看</a>";
	return str;
}

/**
 * 查看页面跳转
 * @param businessId
 * @returns
 */
function view (businessId) {
	var url = "/crmagentsplitdetail/viewInfo/" + businessId;
	window.open(url);
}