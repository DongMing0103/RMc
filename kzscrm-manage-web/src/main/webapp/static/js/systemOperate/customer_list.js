//表格搜索
function query1(){
	var prov = $(".prov").find("option:selected").text();
	var city = $(".city").find("option:selected").text();
	var dist = $(".dist").find("option:selected").text();
	if("请选择" == prov){
		prov = "";
	}
	if("请选择" == city){
		city = "";
	}
	if("请选择" == dist){
		dist = "";
	}
	
	$("input[name=address]").val(prov+city+dist);
	var _url=$('.table').attr("data-url");
	$('.table').bootstrapTable('removeAll');
	$('.table').bootstrapTable('refresh', {
		url : _url
	}, {
		query : $('#form').serialize()
	});
}
//重置
function resetBtn(){
	var strat = $("[name=strat]").val();
	var end = $("[name=end]").val();
	$("[name=stratTimes]").val(strat);
	$("[name=endTimes]").val(end);
	$("[name=address]").val("");
	$("[name=clientType]").val("");
	$(".prov").val("");
	$(".city").val("");
	$(".dist").val("");
	$("#city").citySelect({
        url:"/static/js/cityselect/city.min.js",
        prov:"",//省份
        city:"",//城市
        dist:"",//区县
        nodata:"none",//当子集无数据时，隐藏select
        required:false
    });
	$("[name=searchCriteria]").val("");
	$("[name=searchContent]").val("");
	query1();
}

//合作状态格式化
function clientNatureFormatter(value){
	switch (value) {
	case 1:return "散客";
		break;
		
	case 2:return "保护客户";
		break;
		
	case 3:return "正式客户";
		break;

	default:return "--";
		break;
	}
}
