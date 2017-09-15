//筛选条件
function findBusiness(id,value){
	$("#"+id+"").val(value);
	qurery();
}
//查询
function qurery(url){
	var _url =$("#table").attr("data-url");
	if(url==null){
		url=_url;
	}
	
	$("#table").bootstrapTable("removeAll");
	$("#table").bootstrapTable('refresh',{
		url:url
	},{
		query:$('#form').serialize()
	})
}
/*分页查询*/
function queryParams(params) {
	return $('#form').serialize() + '&order=' + params.order + '&offset=' + params.offset + '&limit=' + params.limit;
}