/* 载入右侧页面 */
//function loadHtmlDiv(urlPath){
//    $.get(urlPath, function (result) {
//        if (result.indexOf('login-content') > -1) {
//            window.location.reload();
//        } else {
//        	/*var sysDate = new Date();
//      	    //设置有效时间 30分钟
//      	    sysDate.setTime(sysDate.getTime() + (30 * 60 * 1000));
//      	 	$.cookie("loadUrl",urlPath, {expires: sysDate});*/
//            $("#content-div").html(result);
//            window.scrollTo(0,0);
//        }
//    });
//};

/* 退出登录 */
$('.logout').click(function(){
	location.href = 'doLogout';
});

/* 新增、修改、编辑  */
function editData(title,url,width,height){
	$.get(url, function (result) {
        if (result.indexOf('login-content') > -1) {
            window.location.reload();
        } else {
        	var index=layer.open({
  		      	type:2,
  		      	title:title,
  		      	shade:[0.5,'#000'],
  		      	area:[width,height],
  		      	content:url
  			});
        }
    });
}


//表格搜索
function query(){
	var _url=$('.table').attr("data-url");
	$('.table').bootstrapTable('removeAll');
	$('.table').bootstrapTable('refresh', {
		url : _url
	}, {
		query : $('#form').serialize()
	});
}  

/*导出*/
function downloadData(msg, url){
    var idsElm = $('#table').bootstrapTable('getSelections');
    if(idsElm.length==0){
		return layer.msg('请选择要导出的数据');
    }
	var ids = new Array();
	$.each(idsElm,function(i){
		ids.push(idsElm[i].id);
	})
	if(ids!=null){
		location.href = url+ids; 
	}
};

/*表格分页*/
function queryParams(params) {
	return $('#form').serialize() + '&order=' + params.order + '&offset=' + params.offset + '&limit=' + params.limit;
}

/* 日期格式 */
function dateFormatter(value){
	if(isNaN(parseInt(value))){
		return null;
	}
	var separator1='-';
	var separator2=':';
	var date=new Date(value);
	//将日期中的各字段存入数组，格式化成两位数
	var fullDate=[];
	fullDate.push(date.getMonth()+1);
	fullDate.push(date.getDate());
	fullDate.push(date.getHours());
	fullDate.push(date.getMinutes());
	/* fullDate.push(date.getSeconds()); */
	var date=date.getFullYear();
	$.each(fullDate,function(index,item){
		item=item+"";
		if(item.length==1){
			item='0'+item;
		}
		//年月日
		if(index<2){
			date=date+separator1+item;
		}else{//时分秒
			//年月日与时分秒之间用空格分开
			if(index==2){
				date=date+' '+item;
			}else{
				//时分秒用:（冒号）分隔
				date=date+separator2+item;
			}
		}
	});
	return date;
}
/* 日期格式 */
function applyMonthFormatter(value){
	if(isNaN(parseInt(value))){
		return null;
	}
	var separator1='-';
	var date=new Date(value);
	//将日期中的各字段存入数组，格式化成两位数
	var fullDate=[];
	fullDate.push(date.getMonth()+1);
	/* fullDate.push(date.getSeconds()); */
	var date=date.getFullYear();
	$.each(fullDate,function(index,item){
		item=item+"";
		if(item.length==1){
			item='0'+item;
		}
		//年月日
		if(index<2){
			date=date+separator1+item;
		}
	});
	return date;
}

/*
 * 判断是否为空 
 * @return true 不为空
 */
function isNotNull(str){
	if(str && str != undefined && str != null && str != ''){
		return true;
	}
}
/* 点击时将状态码赋值给form表单里的隐藏域实现tab数据切换*/
function tabStatus(id, value){
	if(value==null || value==""){
		query1();
	}
	
	$("#"+id+"").val(value);
	query1();
}

//导出
function tabledown(url) {
	var idsElm = $(".tabledown").bootstrapTable('getSelections');
	if(idsElm.length==0){
		return layer.msg('请选择要导出的数据');
	}
	var ids = new Array();
    $.each(idsElm,function(i){
    	ids.push(idsElm[i].id);
    })
	location.href=url+ids;  
}

//客户类型格式化
function clientTypeFormatter(value){
	switch (value) {
	case 1: return "代理商";
		break;
		
	case 2: return "厂内食堂";
		break;
		
	case 3: return "校内食堂";
		break;
		
	case 4: return "独立食堂";
		break;

	default:return "--";
		break;
	}
}


/* 日期格式 */
function dateNoMinFormatter(value){
	if(isNaN(parseInt(value))){
		return null;
	}
	var separator1='-';
	var date=new Date(value);
	//将日期中的各字段存入数组，格式化成两位数
	var fullDate=[];
	fullDate.push(date.getMonth()+1);
	fullDate.push(date.getDate());
	/* fullDate.push(date.getSeconds()); */
	var date=date.getFullYear();
	$.each(fullDate,function(index,item){
		item=item+"";
		if(item.length==1){
			item='0'+item;
		}
		//年月日
		if(index<2){
			date=date+separator1+item;
		}
	});
	return date;
}