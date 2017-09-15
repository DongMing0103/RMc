/*复制上传
 * 
 * */
function pasteUpload(obj){
	//alert("haha");
	var parameter = obj;
	if(parameter.onlyText == true){
		return true;
	}
	if(BrowserDetect.getBrowseName() == "chrome"){
		//for chrome  
	    var clipboardData = parameter.event.clipboardData;  
	    for(var i=0; i<clipboardData.items.length; i++){  
	        var item = clipboardData.items[i];  
	        if(item.kind=='file'&&item.type.match(/^image\//i)){  
	            //blob就是剪贴板中的二进制图片数据  
	            var blob = item.getAsFile(),reader = new FileReader();  
	            //定义fileReader读取完数据后的回调  
	            reader.onload=function(){
		            var src = event.target.result;//result应该是base64编码后的图片
		            //display upload info
		            var $dom = dispalyUploadInfo(src, parameter.displayContainer);
	                //upload
		            uploadImg(src, parameter.projectId, $dom, parameter.url, parameter.displayContainer, parameter.fileIdsContainer);
	            }  
	            reader.readAsDataURL(blob);//用fileReader读取二进制图片，完成后会调用上面定义的回调函数  
	        }
	    }
	}else if(BrowserDetect.getBrowseName() == "firefox"){
		var timer = setTimeout(function(evt) {
			var eles = parameter.text_edit.find('img');
			//alert(eles.length);
			for(var i = 0; i < eles.length; i++){
				var curr = $(eles[i]);
				if(curr.attr("src").match(/data:image\/(png|jpg|jpeg|gif);base64/)){
					//display upload info
					var src = curr.attr("src");
		            var $dom = dispalyUploadInfo(src, parameter.displayContainer);
					uploadImg(src, parameter.projectId, $dom, parameter.url, parameter.displayContainer , parameter.fileIdsContainer);
					curr.remove();
				}
			}
			clearTimeout(timer);
		}, 10);
	}
}
var img_progress = 0;
//ajax上传

function uploadImg(src,projectId, context, url, displayContainer, fileIdsContainer){
	var c = new FormData;
	c.append("sourceCode", src);
	c.append("projectId",projectId);
	c.append("uploadType",1);
	loading('show');
	$.ajax({
		xhr: function() {
			var req = jQuery.ajaxSettings.xhr();
			if(req && req.upload) {  
				req.upload.addEventListener('progress', function(ev){
		        	img_progress = Math.round(ev.loaded * 100 / ev.total);
		        	$(".bar", context).css("width",img_progress+"%");
		        }, false);  
		    }
			return req;
		},
		type: "POST",
		timeout: 40000,
		url: url,
		data: c,
		contentType: false,
		processData: false,
		dataType:"json",
		success: function (data) {
			//alert(data.result);
			if(data.result > 0){
				//$(".bar", context).css("width","100%");
				displayDownSuccessInfo(data, context, displayContainer, fileIdsContainer);
				setSucecessAlert("上传完成！");
			}else{
				displayDownErrorInfo(data, context);
			}
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if(textStatus == 'timeout'){
            	setErrorAlert('请求超时！');
            }else{
            	setErrorAlert('连接异常！');
            }
        },
        complete: function(xhr, ts){
        	loading('hide');
        }
	});
}

function dispalyUploadInfo(src, displayContainer){
	var htmlStr = '<tr class="template-upload fade in">';
	htmlStr += '<td class="preview"><img style="height:40px;" src="' + src + '"/></td>';	
	htmlStr += '<td class="progress-bar" colspan="2">';
	htmlStr += '<div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="bar" style="width:0%;"></div></div>';
	htmlStr += '</td>';
	htmlStr += '<td class="t-delete" title="取消上传">';
	htmlStr += '<i class="icon-ban-circle cancel"></i>';
	htmlStr += '</td></tr>';
	var htmlDom = $(htmlStr);
	$(displayContainer).append(htmlDom);
	return htmlDom;
}

function displayDownSuccessInfo(data, uploadDom, displayContainer, fileIdsContainer) {
	var files = data.files;
	for(var i=0; i < files.length; i++){
		var htmlStr = '<tr class="template-download fade in">';
		htmlStr += '<td class="preview">';
		htmlStr += '<img style="height:40px;" src="' + $("#common-app-name").val() + files[i].previewUrl + '">';
		htmlStr += '</td>';
		htmlStr += '<td class="name" title="' + files[i].name + '">';
		htmlStr += files[i].name.substring(0,15) + '...';
		htmlStr += '</td>';
		htmlStr += '<td class="size"><span>' + files[i].size + '</span></td>';
		htmlStr += '<td><i class="icon-trash delete" fileId="' + files[i].fileId + '" data-type="' + files[i].deleteType + '" data-url="' + files[i].deleteUrl +'"></i></td>'
		htmlStr += '</tr>';
		var htmlDom = $(htmlStr);
		uploadDom.remove();
		$(displayContainer).append(htmlDom);
		//bind the delete event
//		$(".delete", htmlDom).on('click',function(evt){
//			var $del = $(this);
//			loading('show');
//			$.ajax({
//				type: "GET",
//    			timeout: 10000,
//    			url: $("#common-app-name").val() + $del.attr('data-url'),
//    			contentType: "application/json; charset=utf-8",
//    			dataType:"json",
//    			success: function (data) {
//    				if(data.result == '0'){
//    					htmlDom.remove();
//    					setSucecessAlert('删除成功！');
//    				}else if(data.result == '-1'){
//    					setErrorAlert('登录超时！');
//    				}else if (data.result == '-2') {
//    					setErrorAlert('没权限！');
//					}
//                },
//                error: function (XMLHttpRequest, textStatus, errorThrown) {
//                    if(textStatus == 'timeout'){
//                    	setErrorAlert('请求超时！');
//                    }else{
//                    	setErrorAlert('连接异常！');
//    	            }
//                },
//                complete: function(xhr, ts){
//                	loading('hide');
//    	        }
//			});
//		});
		$(fileIdsContainer).append($("<input type='hidden'>").attr("id",files[i].code).attr("fname",files[i].code));
	}
}

function displayDownErrorInfo(data, uploadDom, displayContainer){
	var htmlStr = '<tr class="template-download fade in">';
	htmlStr += '<td class="error" colspan="3"><span class="label label-important">Error</span>&nbsp;&nbsp;&nbsp;<span class="err_msg"></span></td>';
	htmlStr += '<td><i class="icon-trash delete"></i></td>';
	htmlStr += '</tr>';
	var htmlDom = $(htmlStr);
	var err_msg = "";
	if(data.result == '-1'){
		err_msg = '登录超时！';
	}else if (data.result == '-2') {
		err_msg = '信息不完整！';
	}else if (data.result == '-3') {
		err_msg = '没权限！';
	}else if (data.result == '-5') {
		err_msg = '保存失败！';
	}
	$(".err_msg",htmlDom).html(err_msg);
	uploadDom.remove();
	$(displayContainer).append(htmlDom);
	$(".delete", htmlDom).on('click',function(evt){
		htmlDom.remove();
	});
}