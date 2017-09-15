
//搜索按钮
function commit(){
	var prov = $(".prov").find("option:selected").text();
	var city = $(".city").find("option:selected").text();
	var dist = $(".dist").find("option:selected").text();
	if("请选择" == prov){
		prov = "";
	}
	if("请选择" ==  city){
		city = "";
	}
	if("请选择" == dist){
		dist = "";
	}
	
	$("input[name=address]").val(prov+city+dist);
	var _url =$('.table').attr("data-url");
	$(".table").bootstrapTable('removeAll');
	$(".table").bootstrapTable('refresh',{
			url:_url
		},{
			query:$('#form').serialize()
		});
}
/*分页查询*/
function queryParams(params) {
	return $('#form').serialize() + '&order=' + params.order + '&offset=' + params.offset + '&limit=' + params.limit;

}

function reset(){
	$('#form').resetForm();
}