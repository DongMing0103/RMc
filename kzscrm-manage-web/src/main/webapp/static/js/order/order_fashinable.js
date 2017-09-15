

/**
 * 分页查询
 * 
 * @param params
 * @returns
 */
function queryParams(params) {
	return $('#form').serialize() + '&order=' + params.order + '&offset='
			+ params.offset + '&limit=' + params.limit;
}

/**
 * 支付状态
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function opFormatter(value, row, index) {
	var a = "";
	if (value == 1) {
		var a = '<span>已下单</span>';
	} else if (value == 2) {
		var a = '<span>已支付</span>';
	} else if (value == 3) {
		var a = '<span>已接单</span>';
	} else if (value == 4) {
		var a = '<span>已送达</span>';
	} else if (value == 5) {
		var a = '<span>已完成</span>';
	} else if (value == 6) {
		var a = '<span>已评价</span>';
	} else if (value == 7) {
		var a = '<span>已取消</span>';
	} else if (value == 8) {
		var a = '<span>待评价</span>';
	} else if (value == 9) {
		var a = '<span>待取餐</span>';
	}
	return a;
}

/**
 * 支付方式
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function payModel(value, row, index) {
	if (value == 1) {
		return "余额";
	} else if (value == 2) {
		return "支付宝";
	} else if (value == 3) {
		return "微信";
	}
}

/**
 * 操作
 * 
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operationFormatter(value, row, index) {
	var ONo = row.orderNo;
	orderNo = ONo.toString();
	var str = "<a href='javascript:void(0)' onclick=view('"+ orderNo +"')>查看</a>";
	return str;
}

/**
 * 查看
 * @param orderNo
 * @returns
 */
function view (orderNo) {
	var url = '/orderCutController/orderView/' + orderNo;
	window.open(url);
}
