function clientNature(id,value){
	$("#"+id+"").val(vaule);
	
	query();
}
function query(url){
	var _url = $("#table").attr("data-url");
	if(url==null){
		url=_url;
	}
	
	$("#tabale").bootstrapTable("removeAll");
	$("#table").bootstrapTable('refresh',{
		url=url
		},{
			query:$('#form').serialize();
		}
	)
}
/*分页 */
function queryParams(params){
	return $('#form').serialize() +'&order=' + params.order+'&offset='+params.offset + '&limit='+params.limit;
}