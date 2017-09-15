/**
 * 后台公用js
 */

window.alert= function(msg){
	layer.alert(msg);
};

function closeAlert() {
	$("#alert").fadeOut();
}

function setAlertContent(content) {
	$("#span_alert_content").html(content);
}

function showAlert() {
	$("#alert").show("noscroll");
	window.setTimeout("closeAlert()", 3000);
}

function showAlert(content) {
	$("#alert").find('strong').html(content);
	$("#alert").show("noscroll");
	window.setTimeout("closeAlert()", 3000);
}

function showAlert2(content) {
	$("#span_alert2_content").html(content);
	$("#alert2").show("noscroll");
	window.setTimeout("closeAlert2()", 3000);
}

function closeAlert2() {
	$("#alert2").fadeOut();
}

function showAlert3(content) {
	$("#span_alert3_content").html(content);
	$("#alert3").show("noscroll");
	window.setTimeout("closeAlert3()", 3000);
}

function closeAlert3() {
	$("#alert3").fadeOut();
}

function setInfoAlert(content) {
	var msg = Messenger().post({message: content,
		 type: 'info',
		 hideAfter: 4,
	    showCloseButton: true});
	msg.on('hide', function(){
	  // Anything you need
	});
}

function setSucecessAlert(content) {
	$.teninedialog({
        title:'系统提示',
        content:content,
        showCloseButton:false,
        isAlert : true,
        alertImgUrl :'/dsw/images/success_info.png',
        //otherButtons:["确定","取消"],
        //otherButtonStyles:['btn-primary','btn-primary'],
        bootstrapModalOption:{keyboard: true}
    });
}

function setErrorAlert(content) {
	/*
	$.teninedialog({
        title:'错误提示',
        content:content,
        showCloseButton:false,
        isAlert : true,
        alertImgUrl :'/dsw/images/error_info.png',
        //otherButtons:["确定","取消"],
        //otherButtonStyles:['btn-primary','btn-primary'],
        bootstrapModalOption:{keyboard: true}
    });
    */
    layer.alert(content,{title: "错误提示", icon:5});
}

function hideMode(){
	$("#myModal").modal('hide');
}
function showMid(obj){
	var Jobj = $(obj);
	var wWith = $(window).width();
	var wheight = $(window).height();
	var oWidth = Jobj.width();
	var oHeight = Jobj.height();
	var w = oWidth/2;
	var t = (wheight-oHeight)/2;
	Jobj.css({'marginLeft':'-'+w,'top': t > 60 ? t : 60});
}
function hideDivRight(id){
	$("body").css("overflow-x","hidden");
	var objDiv = $("#"+id);
	var startWidth = objDiv.width() + 80;
	objDiv.animate({
		right:'-' + startWidth
	},350);
}


function showDivLeft(id){
	$("#"+id).css("display","block").animate({
        right:'0px'
   },250,function(){
		$("body").css("overflow-y","auto");
		$("body").css("overflow-x","hidden");
	});
}
function fb_picClick(){
	$(".fb-pic").fancybox();
}

/**
 * 打开弹出框页面
 */
function showModal(obj){
	showMid(obj);
	$(obj).modal("toggle");
}
/**
 * 隐藏弹出框页面
 */
function hiddenModal(obj){
	$(obj).modal('hide');
	//去除validForm出错样式
	if($(".Validform_wrong")){
		$(".Validform_wrong").html("");
		$(".Validform_wrong").removeClass('Validform_wrong');
	}
}

/**
 * 加载confirm弹出框
 * confirmOkFun 点击确定触发的方法
 * confirmCancelFun 点击取消触发的方法
 * info 提示的信息
 */
function initConfirmModal(confirmOkFun,confirmCancelFun,info){
	var okDom = "";
	var cancelDom = "";
	if(confirmOkFun && confirmOkFun!=null && confirmOkFun!=undefined){
		okDom = "onclick='"+confirmOkFun+"'";
	}
	if(confirmCancelFun && confirmCancelFun!=null && confirmCancelFun!=undefined){
		cancelDom = "onclick='"+confirmCancelFun+"'";
	}
	var comfirmModal = $("#comfirmModal");
	if(comfirmModal){
		comfirmModal.find("#comfirm_cancel").attr("onclick",confirmCancelFun);
		comfirmModal.find("#comfirm_ok").attr("onclick",confirmOkFun);
		comfirmModal.find(".modal-body").html(info);
	}else{
		var confirmDiv = "<div id='comfirmModal' class='modal hide fade' tabindex='-1' role='dialog' aria-labelledby='comfirmModalLabel' aria-hidden='true' style='margin-left: -285px; display: none;'>"+
								"<div class='modal-header'>"+
								 "<button type='button' class='close' data-dismiss='modal' aria-hidden='true'>×</button>"+
								 "<h3 id='comfirmModalLabel'>确认</h3>"+
								"</div>"+
								"<div class='modal-body'>"+info+"</div>"+
								"<input type='hidden' id='comfirm_del_id'>"+
								"<div class='modal-footer'>" +
								"<a data-handler='0' id='comfirm_cancel' class='btn close' data-dismiss='modal' aria-hidden='true' "+okDom+">取消</a>" +
								"<a id='comfirm_ok' data-handler='1' class='btn btn-success' data-dismiss='modal' aria-hidden='true' "+cancelDom+">确定</a></div>"+
							"</div>";
		documnt.write(confirmDiv);
	}
	showModal("#comfirmModal");
}
/**
 * 升贴水符号处理
 * @param value
 */
function showPriceStopAgio(value){
	if(isNumber(value)){
		if(value > 0){
			return "+"+value;
		}
	}
	return value;
}
/**
 * 开票信息处理
 */
function showInvoiceType(value){
	if(value == 0){
		return "当月票";
	}
	if(value == 1){
		return "下月票";
	}
}
//上传文件大小判断
function findFileSize(field_id,num) {
	if(!num){
		num = 3;
	}
	var maxsize = num * 1024 * 1024;// 大小
	var errMsg = "上传的文件不能超过"+num+"M";
	var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过"+num+"M，建议使用IE、FireFox、Chrome浏览器。";
	var browserCfg = {};
	var ua = window.navigator.userAgent;
	if (ua.indexOf("MSIE") >= 1) {
		browserCfg.ie = true;
	} else if (ua.indexOf("Firefox") >= 1) {
		browserCfg.firefox = true;
	} else if (ua.indexOf("Chrome") >= 1) {
		browserCfg.chrome = true;
	}
	function checkfile() {
//		try {
			var obj_file = document.getElementById(field_id);
			if (obj_file.value == "") {
				setErrorAlert("请先选择上传文件");
				return false;
			}
			var filesize = 0;
			if (browserCfg.firefox || browserCfg.chrome) {
				filesize = obj_file.files[0].size;
			} else if (browserCfg.ie) {
				return true;
			} else {
				setErrorAlert(tipMsg);
				return false;
			}
			if (filesize == -1) {
				setErrorAlert(tipMsg);
				return false;
			} else if (filesize > maxsize) {
				setErrorAlert(errMsg);
				return false;
			} else {
				return true;
			}
//		} catch (e) {
//			//setErrorAlert(e);
//			setErrorAlert('系统异常:'+e);
//			return false;
//		}
	}
	return checkfile();
}

(function(xyx){
	var jurl = {'user':{url:'/UserAccount.do?action=query',name:'realName'},'company':'','goods':'','cate':''};
	xyx.fn.extend({
		loadDataBySelect2:function(opts) {//使用第三方组件select2加载数据
			var defaults = {
					moduleType: 'user',
					defaultName : '',
					defaultId : '',
					pageSize : 20
				};
			var options = xyx.extend(defaults, opts);
			var dataUrl = jurl[options.moduleType].url;
			var showName = jurl[options.moduleType].name;
            xyx(this).val('');
			return this.each(function(){
				xyx(this).select2({
					placeholder: options.defaultName || '请选择',
					allowClear: false,
					formatResult: function(result, container, query, escapeMarkup){ //返回结果时做的操作
						xyx(this).blur();
						return result[showName] || result.placeholder;
					},
					formatSelection: function(item){//选择下拉框值时做的操作
						xyx(this).blur();
						return item[showName]; //当前的输入框赋值
					},
					ajax:{
						url:dataUrl,
						dataType: 'json',
						data: function(params, pageNo){
							//请求时，加入的参数
							return{
								realName: params,
								companyName : params,
								goodsName : params,
								cateName : params,
								pageSize: options.pageSize,
								t: (new Date()).valueOf()
							}
						},
						results: function(data, page){
							return {
								results:data.rows
							};
						}
					}
				});
				xyx(this).val(options.defaultId);
			});
		}
	});
	
})(jQuery);

// 手机号与姓名，公司联动
(function(phone){
	phone.fn.extend({
		loadNameAndCompany:function(opts){
			var defaultParams = {
					accountUrl: 'AdminUserAccount.do?action=query',
					defaultPhone: '',
					userIdDom: null,
					userNameDom: null,
					companyIdDom: null,
					companyNameDom: null
			}
			var options = phone.extend(defaultParams, opts);
			
			if(!options.defaultPhone){
				
				return;
			}
				$.ajax({
                    url: options.accountUrl,
                    data:{"mobileNo":options.defaultPhone,"pageSize":1,"rdm":Math.random()},
                    dataType:'json',
                    type:'post',
                    success:function(data){
                    	
                    	if (data && data.total > 0) {
                            var curUser = data.rows[0];

                            //加载用户
                            $(options.userNameDom).loadDataBySelect2({
                                moduleType: 'user',
                                defaultName : curUser.realName,
                                defaultId : curUser.id
                            });

                            if (isNotNull(curUser.id) && isNotNull(curUser.realName)) {
                            	
                            	if(options.userNameDom){
                            		$(options.userNameDom).select2('readonly','readonly');
                            	}
                            	
                                var company = loadCompany(curUser.id);
                                
                                loadCompanyDataBySelect2($(options.companyIdDom),false,company.companyName,company.companyId, options.companyNameDom);
                                
                                if (isNotNull(company) && isNotNull(company.companyId) && isNotNull(company.companyName)) {
                                    $(options.companyIdDom).select2('readonly','readonly');
                                } else {
                                    $(options.companyIdDom).select2('readonly','');
                                }
                            } else {
                                $(options.userNameDom).select2('readonly','');
                            }
                    	}else{
                    		$(options.userNameDom).select2('readonly','');
                    		$(options.companyIdDom).select2('readonly','');
                    	}
                    }
				});
		}
	});
	

	function loadCompany(userId){
		//加载公司数据
		var company = new Object();
        $.ajax({
            cache : false,
            type : "get",
            url : '/adminLinkCompanyUser.do?action=query',
            data : {"userId":userId,"defaulted":1,"rdm":Math.random()},
            async:false,
            dataType : "json",
            success : function(data) {
                if (data && data.total == 1) {
                    var linkCompany = data.rows[0];
                    company.companyId = linkCompany.companyId;
                    company.companyName = linkCompany.companyName;
                }
            }
        });
        return company;
	}
})(jQuery);

var isGetMessaging = false;
//查询已(未)读 未处理的消息 并显示在菜单前 意向单、订单、采购(供应)单 等
function getNotReadMessage(){
	var messageUrl = "/UserMessage.do?action=getNotReadMessage";
	if(!isGetMessaging){
		$.getJSON(messageUrl + "&t="+new Date().getTime(),function(data){
	 	   if(data && data != null){
	 		   var li = $(".center-left").find("ul>li");
	     	   $.each(li,function(i,o){//未读消息
	     		   var o = $(o);
	     		   if(o.data('bustype') != '采购意向单' && o.data('bustype') != '供应意向单'){
	     			  var num = 0;
		     		   var busTypeUnread = o.data('bustype') + "_"+data.UNREAD;
		     		   var busTypeUnreadPro = o.data('bustype') + "_"+data.UNREAD_UNPROCESS;
		     		   var busTypeAreadreadPro = o.data('bustype') + "_"+data.ALREAD_READ_UNPROCESS
		     	   	   if(data[busTypeUnread] !=null && Number(data[busTypeUnread]) > 0){
			         		    num += Number(data[busTypeUnread]);
		     	   	   }
		     	   	   if(data[busTypeUnreadPro] !=null && Number(data[busTypeUnreadPro]) > 0){
		     	   			num += Number(data[busTypeUnreadPro]);
		     	   	   }
		        	   if(data[busTypeAreadreadPro] !=null && Number(data[busTypeAreadreadPro]) > 0){
		    	   			num += Number(data[busTypeAreadreadPro]);
		    	   	   }
		     	   	   if(num != 0){
		     	   		   var i = o.find('i');
			  	   	   	   if(i == null || i == undefined || i.length == 0){
			  	   	   		   o.append("<i>"+num+"</i>");
			  	   	   	   }else{
			  	   	   		   i.html(num);
			  	   	   	   }
		     	   	   }else{
		     	   		   var i = o.find('i');
		     	   		   if(i != null && i != undefined && i.length > 0){
		     	   			   o.find('i').remove();
		     	   		   }
		     	   	   }
	     		   }
	     	   });
	 	   }
		});
	}
}

//修改已读未读消息状态
function editUserMsg(no,type){
	$.getJSON("/UserMessage.do?action=editUserMsg&no="+no+"&type="+type+"&t="+new Date().getTime(),function(data){//消息改为已读未操作
//		isGetMessaging = false;
//		getNotReadMessage();//查询已(未)读 未处理的消息 并显示在菜单前 意向单、订单、采购(供应)单 等
	});
}


function checkNull(obj){
	if(obj == null || obj == '' || obj == "undefined" || obj.length <= 0 || obj == 'null' || typeof(obj) == "undefined"){
		return '';
	}else{
		return obj;
	}
}

