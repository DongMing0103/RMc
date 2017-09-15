/**
 * @author 黄霄仪
 * @date 2017年6月12日
 */

var addCanteen={};

//form1,form2的下一步按钮事件
addCanteen.formNext=function(self){
	var clientType=$('.category option:selected').val();
    var clientProperty=$('.attribute option:selected').val();
    if($(self).is('#form1')){
		//1.代理商，2.平台
		if(clientProperty=="1"){
			$(".type1").show();//代理商选择
			$(".type2").remove();//删除业务员选择
		}else if(clientProperty=="2"){
			$(".type2").show();//业务员选择，显示团队选择
//			$(".business").hide();//隐藏业务员选择
			//如果初始化时，没有值，就隐藏业务员选项
			if($.isEmptyObject($('.team').val())){
				$('.business').parent().hide();
			}
			$(".type1").remove();//删除代理商选择
		}
		//隐藏表单
		$(".add-merchants").show().siblings().hide();
		$(".nextbtn").show();
		//如果等于独立食堂
		if(clientType=="4"){
			$('.nextbtn').hide();//隐藏第二个表单的下一步按钮
    		$('.surebtn').show();//显示第二个表单的下一步按钮
		}
    }else if($(self).is('#form2')){
    	//如果是独立食堂
		$(".add-enterprise").show().siblings().hide();
		$('#eName').focus();
    }
};
/**
 * 团队按钮选择事件
 */
addCanteen.teamChange=function(self){
	var index=self.selectedIndex;
	if(index==0){
	}
	//找出与团队相匹配的业务员
	$.each($('.business option'),function(index,item){
		if(index==0){
			return true;//continue
		}
		//如果团队选择的值，等于业务员所属团队的值（业务员团队ID等于所选团队）
		if($(self).val()==$(item).attr('teamid')){
			$(item).show();
		}else{
			$(item).hide();
		}
	});
	if($.isNotEmptyObject($(self).val())){
		$('.business').parent().show();
	}else{
		$('.business').parent().hide();
	}
	$('.business').val('');//重置业务员
};
/**
 * 代理商选择事件
 */
addCanteen.agentChange=function(self){
	var level=$(self).attr("level");
	var agentId=$(self).val();
	var isNext=false;//是否有下一个选择组件
	
	//控制向前两级以上的按钮选择事件，如果选择了，那就是当前选择组件的的下两个开始都隐藏
	var selectAgents=$('.selectAgent');
	//控制向前两级按钮选择事件，如果触发，则隐藏当前的下二个级别的标签
	$.each(selectAgents,function(index,selectAgent){
		var levelTemp=$(selectAgent).attr('level');
		//如果当前的代理级别大于等于当前级别的下二个级别的标签，就隐藏
		if(parseInt(levelTemp)>=(parseInt(level)+2)){
			//清空当前select的下一个的值
			$(selectAgent).parent().prev().children().val("");
			
			$(selectAgent).parent().hide();
		}
	});
	
	
	//下一级代理商结点
	var nextSelectAgent=$('.selectAgent[level="'+(parseInt(level)+1)+'"]');
	//遍历下一个节点，找到当前节点相对应的选项(option)
	var nextSelectAgentOptions=$(nextSelectAgent).find('option:not(:first)');
	$.each(nextSelectAgentOptions,function(index,item){
		var parentId=$(item).attr('parentid');
		if(agentId==parentId){
			$(item).show();
			isNext=true;
		}else{
			$(item).hide();
		}
	});
	//如果不为空，并且下一个组件有可选的数据就显示
	if(!$.isEmptyObject(agentId)&&isNext){
		
		$(nextSelectAgent).parent().show();
	}else{
		$(nextSelectAgent).parent().hide();
	}
};
$(function(){
	//归并代理商数据
	$.each($('.selectAgent'),function(index,item){
		var level=$(item).attr('level');
		//各级代理商数据
		var options=$('.crmAgentVOTemp'+level).show().remove();
		$(item).append(options);
		//初始化显示第1级的代理商
		if(index==0){
			$('.selectAgent:not(:first)').parent().hide();
		}
//		if($.isNotEmptyObject($('#crmClientResourceId').val())){
//			if($.isNotEmptyObject($(item).val())){
//				$(item).parent().show();
//			}
//		}
	});
	
	/* 表单验证 ,第一个下一步按钮*/
    $('.select-merchants').Validform({
        btnSubmit : ".save",
        tiptype : 2,
        ignoreHidden:true,
        showAllError:true,
        datatype:{
            "zh2-10":/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,10}$/,
            "zn":/^[\u4E00-\u9FA50-9]+$/,
            "n11":/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|1|3|5|6|7|8]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
            "select":/[0-9]{1}/
        },
        postonce : true,
        ajaxPost : true,
        beforeSubmit:function(curform){
        	//验证通过才会走这段代码
       		addCanteen.formNext(curform);
       		return false;
        }
    });
    
    /*第2个下一步按钮*/
    var submitForm2=$('.add-merchants').Validform({
        btnSubmit : ".save",
        tiptype : 2,
        showAllError:true,
        datatype : {
            "zh2-10" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,10}$/,
            "zn" : /^[\u4E00-\u9FA50-9]+$/,
            "n11" : /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|1|3|5|6|7|8]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
            "dn11" : /^\d{3}-\d{8}|\d{4}-\d{7}$/,
            "nd":/^[0-9-]{8,}$/,
            "wn" : /^[a-zA-Z\d_]{5,}$/,
            "n15-18":/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/
        },
        postonce : true,
        ajaxPost : true,
        beforeCheck:function(curform){
        	var phone ="";
        	var formJsons=$.extend($('#form1').serializeJson(),$('#form2').serializeJson());
//        	$.ajax({
//				type:"POST",
//				url:"/crmclientresource/addCrmClientResourceByCanteen1",
//		        contentType:'application/json;charset=UTF-8;',
//				data:JSON.stringify(formJsons),
//				dataType:"json",
//		        error: function (data) {
//		            console.log(data)
//		        },
//				success:function(data){
//		        	if(data.code!=0){
//						layer.msg(data.desc);
//					}
//				}
//			}) 
			
        	//处理代理商ID
        	if(formJsons.agentIds){
        		var agentIdsTemp=new Array();
        		$.each($('.selectAgent'),function(index,item){
        			if($.isNotEmptyObject($(item).val())){
        				agentIdsTemp.push($(item).val());
        			}
        		});
        		formJsons.agentIds=agentIdsTemp;
        	}
//        	if(formJsons.address){
//        		var address="";
//        		$.each($('#city1 .selectarrow'),function(index,item){
//        			address+=$(item).find('option:selected').text();
//        		});
//        		formJsons.address=address+formJsons.address;
//        	}
        	
        	(function(){
        		for(var formJson in formJsons){
        			//分隔对象前缀
        			if(formJson.indexOf('.')!=-1){
        				var formJsonVal=formJsons[formJson];//值
        				//将分割的键对象组装成bean对象,crmEnterpriseParam，crmCanteenExtInfoParam
        				var key=formJsons[formJson.substring(0,formJson.indexOf('.'))]=formJsons[formJson.substring(0,formJson.indexOf('.'))]||{};
        				
        				key[formJson.substring(formJson.indexOf('.')+1,formJson.length)]=formJsonVal;
        				delete formJsons[formJson];
        			}
        		}
        	})();
        	submitForm2.config({
            	url:"/crmclientresource/addCrmClientResourceByCanteen",
            	ajaxpost:{
            		data:JSON.stringify(formJsons),
            		contentType:'application/json;charset=UTF-8;'
            	}
            });
        	
        	//验证通过才会走这段代码
        	var prov=$('#city1 .prov').val(),
            city=$('#city1 .city').val(),
            dist=$('#city1 .dist').val(),
            textarea=$('#city1 address1').val(),
            showInfo=$('.geography .Validform_checktip');
        	headPhoneValid=$('.headPhoneValid .Validform_checktip');
        	if(prov != '' && city != ''  && city != null && dist != '' && dist != null && textarea != ''){
				showInfo.text('');
				showInfo.removeClass('Validform_wrong');
				showInfo.addClass('Validform_right');
			}else if(prov == '' && city == null && dist == null && textarea == ''){
				showInfo.addClass('Validform_wrong');
				showInfo.text('地理信息不能为空');
			}else{
				showInfo.addClass('Validform_wrong');
				showInfo.text('地理信息不完善');
			}
        	if(phone!=""){
        		headPhoneValid.addClass('Validform_wrong');
        		headPhoneValid.text("商家帐号已存在");
        	}
            return true;
            
        	$.ajax({
				type:"POST",
				url:"/crmclientresource/addCrmClientResourceByCanteen1",
		        contentType:'application/json;charset=UTF-8;',
				data:JSON.stringify(formJsons),
				dataType:"json",
		        error: function (data) {
		            console.log(data)
		        },
				success:function(data){
		        	if(data.code!=0){
						layer.msg(data.desc);
					}
				}
			}) 
			
        },
        beforeSubmit:function(curform){
        
        
        	//如果下一步按钮是隐藏的，就提交
        	if($(curform).find('.nextbtn').is(":hidden")){
				return true;
			}else{
	        	//切换页面
	       		addCanteen.formNext(curform);
	       		return false;
			}
        },
        callback : function(data) {
            if (data.code == 0) {
                $('.save').prev().css('display', 'none');
                parent.$("table").bootstrapTable('refresh');
                parent.layer.closeAll();
                parent.layer.msg("保存成功!");
            } else {
                layer.msg(data.desc);
                $('.save').prev().css('display', 'none');
            }
        }
    });
    
    /*第3步确认*/
    var submitForm3=$('.add-enterprise').Validform({
        btnSubmit : ".save",
        tiptype : 2,
        ignoreHidden:true,
        showAllError:true,
        datatype : {
            "zh2-10" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,10}$/,
            "zn" : /^[\u4E00-\u9FA50-9]+$/,
            "n11" : /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|1|3|5|6|7|8]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
            "dn11" : /^\d{3}-\d{8}|\d{4}-\d{7}$/,
            "nd":/^[0-9-]{8,}$/,
            "wn" : /^[a-zA-Z\d_]{5,}$/,
            "n15-18":/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/
        },
        postonce : true,
        ajaxPost : true,
        beforeCheck:function(curform){
        	var formJsons=$('form').serializeJson();
        	//处理代理商ID
        	if(formJsons.agentIds){
        		var agentIdsTemp=new Array();
        		$.each($('.selectAgent'),function(index,item){
        			if($.isNotEmptyObject($(item).val())){
        				agentIdsTemp.push($(item).val());
        			}
        		});
        		formJsons.agentIds=agentIdsTemp;
        	}
        	(function(){
        		for(var formJson in formJsons){
        			//分隔对象前缀
        			if(formJson.indexOf('.')!=-1){
        				var formJsonVal=formJsons[formJson];//值
        				//将分割的键对象组装成bean对象,crmEnterpriseParam，crmCanteenExtInfoParam
        				var key=formJsons[formJson.substring(0,formJson.indexOf('.'))]=formJsons[formJson.substring(0,formJson.indexOf('.'))]||{};
        				
        				key[formJson.substring(formJson.indexOf('.')+1,formJson.length)]=formJsonVal;
        				delete formJsons[formJson];
        			}
        		}
        	})();
        	/*if(formJsons.address){
        		var address="";
        		$.each($('#city1 .selectarrow'),function(index,item){
        			address+=$(item).find('option:selected').text();
        		});
        		formJsons.address=address+formJsons.address;
        	}
        	if(formJsons.crmEnterpriseParam||formJsons.crmEnterpriseParam.address){
        		var address="";
        		$.each($('#city2 .selectarrow'),function(index,item){
        			address+=$(item).find('option:selected').text();
        		});
        		formJsons.crmEnterpriseParam.address=address+formJsons.crmEnterpriseParam.address;
        	}*/
        	var canteenHealthPicList = new Array();
    		$.each($('.businessHealthPic'),function(index,item){
    			console.log(index+":"+$(item).val());
    			if($.isNotEmptyObject($(item).val())){
    				canteenHealthPicList.push($(item).val());
    				//delete formJsons.canteenHealthPicList[index];
    			}
    		});
    		formJsons.canteenHealthPicList = canteenHealthPicList;
    		//console.log(formJsons);
        	//配置提交路径 
        	submitForm3.config({
            	url:"/crmclientresource/addCrmClientResourceByCanteen",
            	ajaxpost:{
            		data:JSON.stringify(formJsons),
            		contentType:'application/json;charset=UTF-8;'
            	}
            });
        	var prov=$('.prov').val(),
            city=$('.city').val(),
            dist=$('.dist').val(),
            textarea=$('textarea').val(),
            showInfo=$('.geography .Validform_checktip');
	        if(prov != '' && city != ''  && city != null && dist != '' && dist != null && textarea != ''){
	            showInfo.addClass('Validform_right');
	        }else if(prov == '' && city == null && dist == null && textarea == ''){
	            showInfo.addClass('Validform_wrong');
	            showInfo.text('地理信息不能为空');
	        }else{
	            showInfo.text('地理信息不完善');
	        }
            return true;
        },
        beforeSubmit:function(curform){
        	//验证通过才会走这段代码
       		addCanteen.formNext(curform);
       		return true;
        },
        callback : function(data) {
            if (data.code == 0) {
                $('.save').prev().css('display', 'none');
                parent.$("table").bootstrapTable('refresh');
                parent.layer.closeAll();
                parent.layer.msg("保存成功!");
            } else {
                layer.msg(data.desc);
                $('.save').prev().css('display', 'none');
            }
        }
    });
});