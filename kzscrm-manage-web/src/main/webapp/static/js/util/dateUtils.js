/**
 * @author 黄霄仪
 * @date 2017年5月26日
 */
var dateUtils={};
dateUtils.formatToDate=function(date,format){
	var dateTemp='';
	//是数值转成日期
	if(!isNaN(date)&&typeof date=='string'){
		dateTemp=new Date(date);
	}else{
		dateTemp=new Date(date);
	}
	if('Invalid Date'==dateTemp.toDateString()){
		return date;
	}
	var year=dateTemp.getFullYear();//年
	var month=dateTemp.getMonth()+1;//月
	var date=dateTemp.getDate();//日
	var hours=dateTemp.getHours();//时
	var minutes=dateTemp.getMinutes();//分
	var seconds=dateTemp.getSeconds();//秒
	if($.isEmptyObject(format)){
		if((month+"").length==1){
			month='0'+month;
		}
		if((date+"").length==1){
			date='0'+date;
		}
		if((hours+"").length==1){
			hours='0'+hours;
		}
		if((minutes+"").length==1){
			minutes='0'+minutes;
		}
		if((seconds+"").length==1){
			seconds='0'+seconds;
		}
		return year+'-'+month+'-'+date+' '+hours+':'+minutes+':'+seconds;
	}else{
		
		if(format.search(/y{4}/)!=-1){
			format=format.replace(/y{4}/g,year);
		}else if(format.search(/y{2}/)!=-1){
			format=format.replace(/y{2}/g,(year+"").substring(2));
		}
		//月
		if(format.search(/M{2}/)!=-1){
			if((month+"").length==1){
				month='0'+month;
			}
			format=format.replace(/M{2}/g,month);
		}else if(format.search(/M{1}/)!=-1){
			format=format.replace(/M{1}/g,month);
		}
		//日
		if(format.search(/d{2}/)!=-1){
			if((date+"").length==1){
				date='0'+date;
			}
			format=format.replace(/d{2}/g,date);
		}else if(format.search(/d{1}/)!=-1){
			format=format.replace(/d{1}/g,date);
		}
		
		//时
		if(format.search(/h{2}/)!=-1){
			if((hours+"").length==1){
				hours='0'+hours;
			}
			format=format.replace(/h{2}/g,hours);
		}else if(format.search(/h{1}/)!=-1){
			format=format.replace(/h{1}/g,hours);
		}
		
		//分
		if(format.search(/m{2}/)!=-1){
			if((minutes+"").length==1){
				minutes='0'+minutes;
			}
			format=format.replace(/m{2}/g,minutes);
		}else if(format.search(/m{1}/)!=-1){
			format=format.replace(/m{1}/g,minutes);
		}
		
		//秒
		if(format.search(/s{2}/)!=-1){
			if((seconds+"").length==1){
				seconds='0'+seconds;
			}
			format=format.replace(/s{2}/g,seconds);
		}else if(format.search(/s{1}/)!=-1){
			format=format.replace(/s{1}/g,seconds);
		}
		return format;
	}
}