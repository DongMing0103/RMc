//订单状态格式化
function statusFormatter(value, row, index) {
    switch (value) {
	case 1:return "未支付 ";
		break;
		
	case 2:return "已支付 ";
		break;
		
	case 3:return "已接单";
		break;
		
	case 4:return "已送达 ";
		break;
		
	case 5:return "已完成 ";
		break;
		
	case 6:return "已评价 ";
		break;
		
	case 7:return "已取消 ";
		break;
	
	case 8:return "待评价 ";
		break;
	
	case 9:return "待取餐 ";
		break;

	default:return "-- ";
		break;
	}
}

//支付方式格式化
function payModelFormatter(value, row, index) {
    switch (value) {
	case 1:return "余额 ";
		break;
		
	case 2:return "支付宝 ";
		break;
		
	case 3:return "微信";
		break;
		
	default:return "-- ";
		break;
	}
}


//重置
function resetBtn(){
	$("[name=createTimeStart]").val("");
	$("[name=createTimeEnd]").val("");
	$("[name=status]").val("");
	$("[name=orderNoLike]").val("");
	query();
}