/**
 * jquery扩展函数
 * @param retainEmpty 是否保留空,true为留下空的数据，否则会给出非空数据
 */
//获取表单的JSON数据
$.fn.serializeJson = function (retainEmpty) {
      var serializeObj = {};
      $(this.serializeArray()).each(function () {
    	  //如果不允许非空，并且不是空对象，就加入，否则可以加入空数据
    	  if(!retainEmpty){
    		  if(!$.isEmptyObject(this.value)){
    			  serializeObj[this.name] = this.value;
    		  }
    	  }else{
    		  serializeObj[this.name] = this.value;
    	  }
      });
      //处理子对象
      for(var serializeObjTemp in serializeObj){
    	  var serializeObjSplit=serializeObjTemp.split('.');
    	  //只有为2的才能分割
    	  if(serializeObjSplit.length==2){
    		  var sonJson=serializeObj[serializeObjSplit[0]];//serializeObj的子对象
    		  var sonJsonKey=serializeObjSplit[1];//serializeObj的子对象的键
    		  var sonJsonValue=serializeObj[serializeObjTemp];//serializeObj的子对象的值
    		  if($.isEmptyObject(sonJson)){
    			  sonJson=serializeObj[serializeObjSplit[0]]={};
    		  }
    		  delete serializeObj[serializeObjTemp];
    		  sonJson[sonJsonKey]=sonJsonValue;
    	  }
      }
      return serializeObj;
};
//重置表单 
$.fn.resetForm = function () {
   $.each($(this),function(index,item){
	  if($(item).is('form')){
		  item.reset();
	  } 
   });
};
//以JSON形式发送数据
jQuery.extend({
	postJson:function( url, data, callback, type,async ) {
		// shift arguments if data argument was omitted
		if ( jQuery.isFunction( data ) ) {
			type = type || callback;
			callback = data;
			data = undefined;
		}

		return jQuery.ajax({
			url: url,
			type: 'post',
			dataType: type,
			async:async,
			data: JSON.stringify(data),
			contentType:"application/json;charset=UTF-8",
			success: callback
		});
	},
	isNotEmptyObject:function(object){
		return ! $.isEmptyObject(object);
	}
});
