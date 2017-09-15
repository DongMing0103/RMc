
	function initTaskDeitailDivSize22(){
		var windowH = $(window).height();
		var windowH = windowH -40;
		$("#div_task_detail").height(windowH);
		$("#iframe_task_detail").height(windowH);
		var targetW = $("#div_task_detail").width()-35;
		$("#attach-viewer-wrapper").width(targetW);
	}
	
    
	function showMenu(id){
		if($("#entry_projectStatus_"+id).val() != 0){
			return ;
		}
		if($("#entry_status_"+id).val() == 50){
			return;
		}
		if($("#entry_status_"+id).val() == 40){
			return;
		}
		var menuFlag = $("#task-list-tr-"+id).attr('menuFlag');
		if(menuFlag != undefined && menuFlag == 1)
			return ;
		
    	var top = $('#entry_td_'+id).offset().top;
    	var left = $('#entry_td_'+id).offset().left;
     	if($("#entry_status_"+id).val() == 40 || $("#entry_status_"+id).val() == 60){
     		left = left - 35;
     	}
     	if($("#entry_status_"+id).val() == 10 || $("#entry_status_"+id).val() == 20){
     		if(login_user_id == $("#entry_actorId_"+id).val())
     			left = left - 105;
     		else
     			left = left - 80;
     	}
        if($("#task_menu_"+id).length > 0){
        	$("#task_menu_"+id).css("top",top);
        	$("#task_menu_"+id).css("left",left);
        	$("#task_menu_"+id).show();
            return ;
        }
    	var boarddiv = "<div id=\"task_menu_"+id+"\" onmouseout=\"hiddenMenu('"+id+"');\" onmouseover=\"showMenu('"+id+"');\" class=\"popover fade left in\" style=\"top: "+top+"px; left: "+left+"px; display: block;\" ><div class=\"arrow\"></div><div class=\"popover-content\">";
    	var operation = "";
    	if($("#entry_status_"+id).val() == 40 || $("#entry_status_"+id).val() == 60){
    		operation = operation + "<i onclick=\"del('"+id+"');\" title="+menu_del+" class=\"icon-trash\"></i>";
    	}
    	var startOptStr = "";
    	if(login_user_id == $("#entry_actorId_"+id).val()){
    		if($("#entry_status_"+id).val() == 10)
    			startOptStr = "&nbsp;&nbsp;<i id=\"btn_start_"+id+"\" title="+menu_start+" onclick=\"start('"+id+"');\" class=\"icon-play\"></i><i style=\"display:none;\" id=\"btn_stop_"+id+"\" title="+menu_stop+" class=\"icon-pause\" onclick=\"stop('"+id+"');\"></i>";
    		if($("#entry_status_"+id).val() == 20)
    			startOptStr = "&nbsp;&nbsp;<i id=\"btn_start_"+id+"\" title="+menu_start+" style=\"display:none;\" onclick=\"start('"+id+"');\" class=\"icon-play\"></i><i id=\"btn_stop_"+id+"\" class=\"icon-pause\" title="+menu_stop+" onclick=\"stop('"+id+"');\"></i>";
    	}
    		
    	if($("#entry_status_"+id).val() == 10){
    		operation = operation + "<i class=\"icon-ok\" title="+menu_finish+" onclick=\"finish('"+id+"');\"></i>"+startOptStr+"&nbsp;&nbsp;<i class=\"icon-pencil\" title="+menu_edit+" onclick=\"editTaskEntry('"+id+"');\"></i>&nbsp;&nbsp;<i onclick=\"del('"+id+"');\" title="+menu_del+" class=\"icon-trash\"></i>";
    	}
    	if($("#entry_status_"+id).val() == 20){
    		operation = operation + "<i class=\"icon-ok\" title="+menu_finish+" onclick=\"finish('"+id+"');\"></i>"+startOptStr+"&nbsp;&nbsp;<i class=\"icon-pencil\" title="+menu_edit+" onclick=\"editTaskEntry('"+id+"');\"></i>&nbsp;&nbsp;<i onclick=\"del('"+id+"');\" title="+menu_del+" class=\"icon-trash\"></i>";
    	}
    	boarddiv = boarddiv + operation + "</div></div>";
    	$('#entry_td_'+id).append(boarddiv);
     }

    function hiddenMenu(id){
        $("#task_menu_"+id).hide();
     }
    
    
    function initForm(){
    	$("#updateNewEntryout").hide();
    	$("#saveNewEntry").show();
    	$("#saveNewEntryout").show();
    	if(companyCrmFlag == 1)
    		$("#div-task-select-process").show();
        $("#div-task-show-process").hide();
    	$("#myModalLabel").html(button_task_new);
    	$("#new_task_id").val("");
     	$("#new_task_title").val("");
        $("#new_task_listId").val("");
        $("#new_task_project_list_Id").val("");
        $("#new_task_actorId").val("");
        $("#new_task_actorId").empty();
        $("#new_task_checkerId").val("");
        $("#new_task_checkerId").empty();
        $("#new_task_maybeDay").val(todayStr);
        $("#new_task_maybeTime").val("4");
        $("#new_task_weight").val(5);
        $("#new_task_priority").val(20);
        $("#new_task_status").val("");
        $("#new_task_des").html("");
        $("#new_task_des").height(120);
        $("#new_task_projectId").val("");
        $("#new_task_projectCode").val("");
        $("#new_task_listId").val("");
        $("#new_task_project_list_Id").prop("disabled", false);
        $("#new_task_project_list_Id").val("");
        $("#new_task_file").css("display","block");
        $("#edit_task_actorId").val("");
        $("#edit_task_checkerId").val("");
        
        
        $("#task-process-type").val("0");
		selectProcessType();
		
        removeUploadFiles();
        //$("#sel-file").attr("disabled","disabled");
        showNormalProjectList();
    }
    
    function initAfterSaveForm(){
     	$("#new_task_title").val("");
        $("#new_task_listId").val("");
        $("#new_task_project_list_Id").val("");
        $("#new_task_actorId").val("");
        $("#new_task_actorId").empty();
        $("#new_task_checkerId").val("");
        $("#new_task_checkerId").empty();
        $("#new_task_des").html("");
        $("#edit_task_actorId").val("");
        $("#edit_task_checkerId").val("");
        
        removeUploadFiles();
    }
    
    function initCustomerTaskForm(id){
    	
    	initForm();
    	
        $("#task-process-type").val("1");
        $("#new_task_opportunityId").val("");
        
        $("#new_task_customerId").empty();
        $("#new_task_customerId").append("<option value='"+id+"' selected>"+$("#span_name_"+id).html()+"</option>");
        $("#new_task_customerId").val(id);
        $("#div-task-select-process").hide();
        $("#div-task-show-process").hide();
        
        $("#span-process-show").html("客户 - " + $("#span_name_"+id).html());
    	$("#div-task-show-process").show();
    }
    
    function initOpportunityTaskForm(id){
    	
    	initForm();
        
        $("#task-process-type").val("2");
        $("#new_task_customerId").val("");
        
        $("#new_task_opportunityId").empty();
        $("#new_task_opportunityId").append("<option value='"+id+"' selected>"+$("#span_name_"+id).html()+"</option>");
        $("#new_task_opportunityId").val(id);
        $("#div-task-select-process").hide();
        $("#div-task-show-process").hide();
        
        $("#span-process-show").html("销售机会 - " + $("#span_name_"+id).html());
    	$("#div-task-show-process").show();
    }
    
    //隐藏归档 删除的项目列表
    function showNormalProjectList()
    {
     var listNodes = $("#new_task_project_list_Id").find("option");
     if(listNodes.length != 0){
 	   for(var i=0;i<listNodes.length;i++){
 		  if($(listNodes[i]).attr("status") != 0)
 			 $(listNodes[i]).hide();
 		  else
 			 $(listNodes[i]).show(); 
 	   }
     }
   }
    
  //显示同项目的列表
    function showSameProjectList(projectId)
    {
     var listNodes = $("#new_task_project_list_Id").find("option");
     if(listNodes.length != 0){
 	   for(var i=0;i<listNodes.length;i++){
 		  if($(listNodes[i]).attr("projectId") != projectId)
 			 $(listNodes[i]).hide();
 		  else
 			 $(listNodes[i]).show();
 	   }
     }
   }
    
    //显示所有项目列表
    function showAllProjectList(){
    	 var listNodes = $("#new_task_project_list_Id").find("option");
         if(listNodes.length != 0){
     	   for(var i=0;i<listNodes.length;i++){
     			 $(listNodes[i]).show();
     	   }
         }
    }
    
    function editTaskEntry(entry_id){
    	taskDivHidden = 0;
    	$("#saveNewEntry").hide();
    	$("#updateNewEntryout").hide();
    	$("#saveNewEntryout").show();
    	showAllProjectList();
    	$("#myModalLabel").html(button_task_edit);
    	var id = $("#entry_id_"+entry_id).val();
    	$("#new_task_file").css("display","none");
    	$("#new_task_id").val(id);
    	$("#new_task_title").val($("#entry_title_"+id).val());
        $("#new_task_projectId").val($("#entry_projectId_"+id).val());
        showSameProjectList($("#entry_projectId_"+id).val());
        $("#new_task_listId").val($("#entry_listId_"+id).val());
        var project_task = $("#entry_projectId_"+id).val()+"_"+$("#entry_listId_"+id).val();
        $("#new_task_project_list_Id").val(project_task);
        $("#edit_task_actorId").val($("#entry_actorId_"+id).val());
        $("#edit_task_checkerId").val($("#entry_checkerId_"+id).val());
        //$("#new_task_project_list_Id").prop("disabled", true);
        $("#new_task_maybeDay").val($("#entry_maybeDay_"+id).val());
        $("#new_task_maybeTime").val($("#entry_maybeTime_"+id).val());
        $("#new_task_weight").val($("#entry_weight_"+id).val());
        $("#new_task_priority").val($("#entry_priority_"+id).val());
        $("#new_task_status").val($("#entry_status_"+id).val());
        $("#new_task_des").html($("#entry_des_"+id).html());
        selectProject();
        $("#new_task_actorId").val($("#entry_actorId_"+id).val());
        $("#new_task_checkerId").val($("#entry_checkerId_"+id).val());
        $("#new_task_milestoneId").val($("#entry_milestoneId_"+id).val());
        
        var processType = $("#entry_processType_"+id).val();
        $("#div-task-select-process").hide();
        $("#div-task-show-process").hide();
        if(processType == 1){
        	$("#span-process-show").html("客户 - " + $("#entry_customerName_"+id).val());
        	$("#div-task-show-process").show();
    	}
    	if(processType == 2){
    		$("#span-process-show").html("销售机会 - " + $("#entry_opportunityName_"+id).val());
    		$("#div-task-show-process").show();
    	}
    	
    	removeUploadFiles();
        $("#sel-file").attr("disabled","disabled");
        
        if(taskDivHidden == "" || taskDivHidden == "0")
        	showTaskDetailDiv("");
        $("#myModal").modal('toggle');
    }
    
    function selectProcessType(){
    	var processType = $("#task-process-type").val();
    	if(processType == 1){
    		$("#task-process-type").show();
    		$("#new_task_opportunityId").select2("destroy");
    		$("#new_task_opportunityId").hide();
    		$("#new_task_customerId").val("");
    		$("#new_task_customerId").show();
    		$("#new_task_customerId").select2();
    	}
    	if(processType == 2){
    		$("#task-process-type").show();
    		$("#new_task_customerId").select2("destroy");
    		$("#new_task_customerId").hide();
    		$("#new_task_opportunityId").val("");
    		$("#new_task_opportunityId").show();
    		$("#new_task_opportunityId").select2();
    	}
    	if(processType == 0){
    		$("#new_task_customerId").select2("destroy");
    		$("#new_task_customerId").hide();
    		$("#new_task_opportunityId").select2("destroy");
    		$("#new_task_opportunityId").hide();
    		$("#task-process-type").hide();
    		$("#btn-select-task-process").show();
    	}
    }
    
    function selectProject(){
    	var project_list = $("#new_task_project_list_Id").val();
    	var projectCode = $("#new_task_project_list_Id option:selected").attr("projectCode");
    	$('#folderSelectorMode').modal('hide');
    	if(project_list == ""){
    		$("#new_task_projectId").val("");
        	$("#new_task_projectCode").val("");
        	$("#new_task_listId").val("");
        	//$("#task-file-form-projectId").val("");
    		//$("#sel-file").attr("disabled","disabled");
    		return ;
    	}
    	if(project_list == -1){
    		$("#myModal").modal('toggle');
        	$("#taskListModal").modal('toggle');
        	$("#new_taskList_id").val("");
            $("#new_taskList_title").val("");
            $("#new_taskList_projectId").val("");
            $("#new_taskList_des").val("");
            return ;
    	}
    	var idsArray = project_list.split("_");
    	$("#new_task_projectId").val(idsArray[0]);
    	$("#new_task_projectCode").val(projectCode);
    	$("#new_task_listId").val(idsArray[1]);
    	$("#new_task_actorId").empty();
    	$("#new_task_checkerId").empty();
    	$("#new_milestone_actorId").empty();
        var projectId = $("#new_task_projectId").val();
        //var attach_projectId = $("#task-file-form-projectId").val();
        // if(projectId != ""){
        	//if(attach_projectId != "" && attach_projectId != projectId){
        		//removeUploadFiles();
        //}
        //$("#task-file-form-projectId").val(projectId);
        //$("#sel-file").removeAttr("disabled");
        // }else{
        	//$("#sel-file").attr("disabled","disabled");
        //}
        var listId = $("#new_task_listId").val();
        var urlWork = url_listMembers;
    	$.ajaxSettings.async = false;
    	var memberList = "";
    	var memberData = new Array();
    	var default_actor = $("#edit_task_actorId").val();
    	var default_checker = $("#edit_task_checkerId").val();
    	
    	//改变项目的时候获取项目成员，可以@
    	$.getJSON(urlWork,{"projectId":projectId,"listId":listId},function(data){
    		memberList = data.beanList;
    		memberData = data.beanList;
    		$("#new_task_des").atwho({
    		     at: "@",
    		     'data':memberData,
    		     search_key: "name"
    		 });
    		if(default_actor == "")
    			default_actor = data.actorId;
    		if(default_checker == "")
    			default_checker = data.checkerId
    		$("#new_task_actorId").append("<option value=''>"+menu_task_actor+"</option>");
    		$("#new_task_checkerId").append("<option value=''>"+menu_task_actor+"</option>");
    		$("#new_milestone_actorId").append("<option value=''>"+menu_milestone_owner+"</option>");  
    		if(memberList.length != 0){
    			for(var i=0;i<memberList.length;i++){
    				 if(memberList[i].id == default_actor)
    					 $("#new_task_actorId").append("<option value='"+memberList[i].id+"' selected>"+memberList[i].name+"</option>");
    				 else
    					 $("#new_task_actorId").append("<option value='"+memberList[i].id+"' >"+memberList[i].name+"</option>");
    				 if(memberList[i].id == default_checker)
    					 $("#new_task_checkerId").append("<option value='"+memberList[i].id+"' selected>"+memberList[i].name+"</option>");
    				 else
    					 $("#new_task_checkerId").append("<option value='"+memberList[i].id+"' >"+memberList[i].name+"</option>");
					 $("#new_milestone_actorId").append("<option value='"+memberList[i].id+"' >"+memberList[i].name+"</option>");  
    			}
    		}
    	});

    	$("#new_task_milestoneId").empty();
        var projectId = $("#new_task_projectId").val();
        var urlWork = url_milestones;
    	$.ajaxSettings.async = false;
    	var milestoneList = "";
    	$.getJSON(urlWork,{projectId:projectId},function(data){
    		milestoneList = data.beanList;
    		$("#new_task_milestoneId").append("<option value=''>"+menu_select_milestone+"</option>");  
    		if(milestoneList.length != 0){
    			for(var i=0;i<milestoneList.length;i++){
					  $("#new_task_milestoneId").append("<option value='"+milestoneList[i].id+"' >"+milestoneList[i].name+"</option>");  
    			}
    		}
    		//$("#new_task_milestoneId").append("<option value='-1'>"+menu_new_milestone+"</option>");  
    	});
    }

    function selectMilestone(){
        if($("#new_task_milestoneId").val() == -1){
    	$("#myModal").modal('toggle');
    	$("#milestoneModal").modal('toggle');
    	$("#new_milestone_id").val("");
        $("#new_milestone_title").val("");
        $("#new_milestone_actorId").val("");
        $("#new_milestone_startDay").val("");
        $("#new_milestone_endDay").val("");
        $("#new_milestone_des").val("");
        }
    }
    
    function selectListProject(){
    	var projectId = $("#new_taskList_projectId").val();
        if(projectId == "")
    		return ;
        $("#new_taskList_actorId").empty();
        $("#new_taskList_checkerId").empty();
    	loading("show");
		$.ajax({
			type: "GET",
			timeout: 6000,
    		url: url_listMembers,
    		data: {projectId:projectId},
			contentType: "application/json; charset=utf-8",
			dataType:"json",
			success: function (data) {
    			var memberList = data.beanList;
        		$("#new_taskList_actorId").append("<option value=''>"+tasklist_default_actor+"</option>");  
        		$("#new_taskList_checkerId").append("<option value=''>"+tasklist_default_checker+"</option>");  
        		if(memberList.length != 0){
        			for(var i=0;i<memberList.length;i++){
    					 $("#new_taskList_actorId").append("<option value='"+memberList[i].id+"' >"+memberList[i].name+"</option>");
    					 $("#new_taskList_checkerId").append("<option value='"+memberList[i].id+"' >"+memberList[i].name+"</option>"); 
        			}
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
            	loading("hide");
	        }
		});

    }
    
    function finish(id){
      	 var urlWork = url_finish;
      	 var params = {
   	     	 "id":id
   	     };
      	 $.ajaxSettings.async = false;
      	 var result = "";
     	$.getJSON(urlWork,params,function(data){
     		result = data.result;
     	});
     	if(result == 0){
     		var count = $("#task_count").html();
         	count = parseInt(count) - 1;
         	$("#task_count").html(count);
         	$("#entry_status_"+id).val("40");
     		if(type == 0){//待办任务的时候，完成要消失
     			$("#entry_td_"+id).parent().remove();
     		}else{
     			if($("#task_menu_"+id).length > 0){
         			$("#task_menu_"+id).remove();
     			}
         		if($("#badge_info_status_"+id).length > 0){
         			$("#badge_info_status_"+id).html(show_task_status_40);
         		}
           }
     		
     		if($("#count-finish-num").length > 0){
     			var finishNum = $("#count-finish-num").html();
     			var finishTime = $("#count-finish-time").html();
     			var taskNum = $("#count-task-num").html();
     			var taskTime = $("#count-task-time").html();
     			var thisTime = $("#entry_maybeTime_"+id).val();
     			finishNum = parseInt(finishNum) + 1;
     			taskNum = parseInt(taskNum) - 1;
     			finishTime = parseFloat(finishTime) + parseFloat(thisTime);
     			taskTime = parseFloat(taskTime) - parseFloat(thisTime);
     			$("#count-finish-num").html(finishNum);
     			$("#count-finish-time").html(finishTime);
     			$("#count-task-num").html(taskNum);
     			$("#count-task-time").html(taskTime);
     		}
     			
     			//目标里程碑，编辑任务时候的改变
     			var tableId = $("#entry_td_"+id).parent().parent().attr("id");
     			if(tableId == 'table_unBindList'){
     				$("#entry_td_"+id).parent().remove();
     			}
     			if(tableId == 'table_tasklist'){
     				//$("#checkbox_unBind_"+id).remove();
     				var table1 = $('#table_finishList'); 
     				var row = $("<tr id=\"entry_tr_"+id+"\" onmouseover=\"showMenu("+id+");\" onmouseout=\"hiddenMenu("+id+");\" style=\"line-height: 40px;\"></tr>");
     				row.append($("#entry_tr_"+id).html()); 
     		    	$("#entry_tr_"+id).remove();
     		    	table1.append(row);
     		    	if($("#task_menu_"+id).length > 0){
             			$("#task_menu_"+id).remove();
                    }
     			}
     			if(tableId == 'table_tasklist_today'){
     				var futureHtml = $("#table_tasklist_future").html();
					futureHtml = futureHtml.replace(/(^\s*)/g,"");
     				if(futureHtml == "")
     					$("#table_tasklist_today").html("<tr id=\"today_tr_tips\"><td style=\"border: 0;\">今日任务请拖放入这里</td></tr>");
     			}
     			if(tableId == 'table_tasklist_future'){
     				var todayHtml = $("#table_tasklist_today").html();
					todayHtml = todayHtml.replace(/(^\s*)/g,"");
     				if(todayHtml == "")
     					$("#table_tasklist_future").html("<tr id=\"non_today_tr_tips\"><td style=\"border: 0;\">今日不做的任务请拖放入这里</td></tr>");
     			}
     				
     		setSucecessAlert(alert_success);
     	}
     	if(result == -1){
     		setErrorAlert(alert_timeout);
   		}
     	if(result == -2){
     		setErrorAlert(alert_empty);
   		}
     	if(result == -3){
     		setErrorAlert(alert_status_wrong);
     	}
     	if(result == -5){
           setErrorAlert(alert_access_wrong);
        }
       if(result == -4){
          setErrorAlert(alert_fail);
       }
       if(result == -6){
    	   setErrorAlert(operator_project_file_error);
       }
       if(result == -7){
          setErrorAlert(alert_task_issue_exsit);
       }
       }

       function unfinish(id){
         	 var urlWork = url_unfinish;
         	 var params = {
      	     	 "id":id
      	 	};
         	 $.ajaxSettings.async = false;
         	 var result = "";
        	$.getJSON(urlWork,params,function(data){
        		result = data.result;
        	});
        	if(result == 0){
        		var count = $("#task_count").html();
             	count = parseInt(count) + 1;
             	$("#task_count").html(count);
        		if(type==2)
        		$("#entry_td_"+id).parent().remove();
        		setSucecessAlert(alert_success);
        	}
        	if(result == -1){
      			setErrorAlert(alert_timeout);
      		}
      		if(result == -2){
      			setErrorAlert(alert_empty);
      		}
      		if(result == -3){
      			setErrorAlert(alert_fail);
      		}
      		if(result == -6){
         	   setErrorAlert(operator_project_file_error);
            }
      	}
       
       function del(id){
    	   showTaskDetailDiv("");
           $("#comfirm_del_id").val(id);
       		$("#comfirmModal").modal('toggle');
       }
       
       //删除不显示任务
       function comfirmDel(){
      	 var urlWork = url_del_task;
      	 var id = $("#comfirm_del_id").val();
         var params = {
      	     "id":id
      	 };
         	 $.ajaxSettings.async = false;
         	 var result = "";
         	 var msg = "";
        	$.getJSON(urlWork,params,function(data){
        		result = data.result;
        		msg = data.msg;
        	});
        	var tableId = $("#entry_td_"+id).parent().parent().attr("id");
        	$("#comfirmModal").modal('toggle');
        	if(result == 0 || result == 1){
            	if(result == 1){
            	var count = $("#task_count").html();
            	count = parseInt(count) - 1;
            	$("#task_count").html(count);
            	}
        		$("#entry_td_"+id).parent().remove();
        		setSucecessAlert(alert_success);
        		if(data_task_list != null && data_task_list.length > 0){
            		for(var i=0;i<data_task_list.length;i++){
            			if(data_task_list[i].id == id){
            				data_task_list.splice(i,i);
            			}
            		}
            	}
        		if(tableId == 'table_tasklist_today'){
     				var futureHtml = $("#table_tasklist_today").html();
					futureHtml = futureHtml.replace(/(^\s*)/g,"");
     				if(futureHtml == "")
     					$("#table_tasklist_today").html("<tr id=\"today_tr_tips\"><td style=\"border: 0;\">今日任务请拖放入这里</td></tr>");
     			}
     			if(tableId == 'table_tasklist_future'){
     				var todayHtml = $("#table_tasklist_future").html();
					todayHtml = todayHtml.replace(/(^\s*)/g,"");
     				if(todayHtml == "")
     					$("#table_tasklist_future").html("<tr id=\"non_today_tr_tips\"><td style=\"border: 0;\">今日不做的任务请拖放入这里</td></tr>");
     			}
        	}
        	else{
        		setErrorAlert(msg);
        	}
      }
       
       /**
        * 删除还显示任务
        * @return
        */
       function comfirmDelShow(){
        	 var urlWork = url_del_task;
        	 var id = $("#comfirm_del_id").val();
           	 var params = {
        	     	 "id":id
        	 };
           	 $.ajaxSettings.async = false;
           	 var result = "";
          	$.getJSON(urlWork,params,function(data){
          		result = data.result;
          	});
          	$("#comfirmModal").modal('toggle');
          	if(result == 0 || result == 1){
              	if(result == 1){
              		var count = $("#task_count").html();
              		count = parseInt(count) - 1;
              		$("#task_count").html(count);
              	}
          		setSucecessAlert(alert_success);
          	}
          	if(result == -1){
        		setErrorAlert(alert_timeout);
        	}
        	if(result == -2){
        		setErrorAlert(alert_empty);
        	}
        	if(result == -3){
          		setErrorAlert(alert_status_wrong);
          	}
          	if(result == -4){
          		setErrorAlert(alert_fail);
          	}
        	if(result == -6){
        		setErrorAlert(operator_project_file_error);
        	}
        }

      function start(id){
      	var urlWork = url_start;
        	var params = {
     	     	 "id":id
     	    };
        	$.ajaxSettings.async = false;
        	var result = "";
       	$.getJSON(urlWork,params,function(data){
       		result = data.result;
       	});
       	if(result == 0){
       		 $("#btn_start_"+id).hide();
             $("#btn_stop_"+id).show();
             $("#badge_info_status_"+id).html(show_task_status_20);
       		setSucecessAlert(alert_success);
       	}
       	if(result == -1){
           	setErrorAlert(alert_timeout);
           }
          if(result == -2){
              setErrorAlert(alert_empty);
            }
          if(result == -3){
              setErrorAlert(alert_status_wrong);
          }
          if(result == -5){
              setErrorAlert(alert_access_wrong);
            }
          if(result == -4){
              setErrorAlert(alert_fail);
          }
          if(result == -6){
       	   setErrorAlert(operator_project_file_error);
          }
      }

      function stop(id){
          var urlWork = url_stop;
        	var params = {
     	     	 "id":id
     	    };
        	$.ajaxSettings.async = false;
        	var result = "";
       	$.getJSON(urlWork,params,function(data){
       		result = data.result;
       	});
       	if(result == 0){
       		 $("#btn_stop_"+id).hide();
             $("#btn_start_"+id).show();
             $("#badge_info_status_"+id).html("");
       		setSucecessAlert(alert_success);
       	}
       	if(result == -1){
           	setErrorAlert(alert_timeout);
           }
          if(result == -2){
              setErrorAlert(alert_empty);
            }
          if(result == -3){
              setErrorAlert(alert_status_wrong);
          }
          if(result == -5){
              setErrorAlert(alert_access_wrong);
            }
          if(result == -4){
              setErrorAlert(alert_fail);
          }
          if(result == -6){
       	   setErrorAlert(operator_project_file_error);
          }
      }
      
      function starred(id) {
  		var sta = false;
  		if(!$("#ok_task_star_"+id).hasClass("starred")){
  			sta = true;
  		}
  		var url = url_starred;
  		var params = {
  			"task.id" : id,
  			"starred" : sta
  		};
  		loading('show');
  		$.ajax({
  			type: "GET",
  			timeout: 20000,
  			url: url_starred,
  			data: params,
  			contentType: "application/json; charset=utf-8",
  			dataType:"json",
  			success: function (data) {
  				if(data.result == "-1"){
  					setErrorAlert(alert_timeout);
  				}
  				else if(data.result == "-2"){
  					setErrorAlert(alert_access_wrong);
  				}
  				else if(data.result == "-3"){
  					setErrorAlert(alert_fail);
  				}
  				else if(data.result == "1"){
  					$("#ok_task_star_"+id).addClass("starred").attr("data-original-title",mene_cancelCollection);
  					setSucecessAlert(alert_starred_success);
  					if(data_task_list != null && data_task_list.length > 0){
			    		for(var i=0;i<data_task_list.length;i++){
			    			if(data_task_list[i].id == id){
			    				data_task_list[i].starredStatus = true;
			    			}
			    		}
			    	}
  					$("#ok_task_star_"+id).tooltip({
  						placement : 'top',
  					});
  					
  				}else if(data.result == "2"){
  					$("#ok_task_star_"+id).removeClass("starred").attr("data-original-title",mene_collection);
  					setSucecessAlert(alert_cancel_starred_success);
  					if(data_task_list != null && data_task_list.length > 0){
			    		for(var i=0;i<data_task_list.length;i++){
			    			if(data_task_list[i].id == id){
			    				data_task_list[i].starredStatus = false;
			    			}
			    		}
			    	}
  					$("#ok_task_star_"+id).tooltip({
  						placement : 'top',
  					});
  				}else{
  					setErrorAlert(alert_starred_fail);
  				}
              },
              error: function (XMLHttpRequest, textStatus, errorThrown) {
                  if(textStatus == 'timeout'){
                  	setErrorAlert(alert_ajax_timeout);
                  }else{
                  	setErrorAlert(alert_ajax_exception);
  	            }
              },
              complete: function(xhr, ts){
              	loading('hide');
  	        }
  		});
      }
      
      function updateStarredShow(id, result){
    	  if(result == "1"){
				$("#ok_task_star_"+id).addClass("starred").attr("data-original-title",mene_cancelCollection);
				$("#ok_task_star_"+id).tooltip({
					placement : 'top',
				});
			}else if(result == "2"){
				$("#ok_task_star_"+id).removeClass("starred").attr("data-original-title",mene_collection);
				$("#ok_task_star_"+id).tooltip({
					placement : 'top',
				});
			}
      }
      
      function saveMilestone(){
      	var id = $("#new_milestone_id").val();
          var name = $("#new_milestone_title").val();
          var projectId = $("#new_task_projectId").val();
          var accId = $("#new_milestone_actorId").val();
          var startDay = $("#new_milestone_startDay").val();
          var endDay = $("#new_milestone_endDay").val();
          var des = $("#new_milestone_des").val();
          if(name == ''){
        	  setErrorAlert(alert_title_empty);
          	return;
          }
          if(projectId == ''){
        	  setErrorAlert(alert_project_empty);
          	return;
          }
          if(accId == ''){
        	  setErrorAlert(alert_owner_empty);
          	return;
          }
          if(startDay == ''){
        	  setErrorAlert(alert_startDay_empty);
          	return;
          }
          if(endDay == ''){
        	  setErrorAlert(alert_endDay_empty);
          	return;
          }
          if(startDay > endDay){
        	  setErrorAlert(project_startDay_endDay_big);
        	  return;
          }

        var urlWork = url_addMilestone;
       	$.ajaxSettings.async = false;
       	 var params = {
       	     	 "id":id,
  	    		 "name" : name,
  				 "projectId" : projectId,
  				 "accId" : accId,
  				 "startDay" : startDay,
  				 "endDay" : endDay,
  				 "des" : des
  		    };
       	var result = "";
       	var num = 0;
       	var msg = "";
       	$.getJSON(urlWork,params,function(data){
       		result = data.result;
       		msg = data.msg;
       	});

       	if(result == 0){
  			setSucecessAlert(alert_success);
  		}
       	if(result > 0){
       		$("#new_task_milestoneId option[value='']").remove();
       		$("#new_task_milestoneId").prepend("<option value='"+result+"'>"+name+"</option>"); 
       		$("#new_task_milestoneId").prepend("<option value=''>"+menu_select_milestone+"</option>");  
       		$("#new_task_milestoneId").val(result);
          	$("#milestoneModal").modal('toggle');
          	$("#new_milestone_id").val("");
            $("#new_milestone_title").val("");
            $("#new_milestone_actorId").val("");
            $("#new_milestone_startDay").val("");
            $("#new_milestone_endDay").val("");
            $("#new_milestone_des").val("");
  			setSucecessAlert(alert_new);
  		}
       	if(result < 0){
  			setErrorAlert(msg);
  		}else{
  			setSucecessAlert(msg);
  		}
      }
      
      function saveTaskList(){
        	var id = $("#new_taskList_id").val();
            var title = $("#new_taskList_title").val();
            var projectId = $("#new_taskList_projectId").val();
            var accId = $("#new_taskList_actorId").val();
            var projectName = $("#new_taskList_projectId option:selected").text();
            var projectCode = $("#new_taskList_projectId option:selected").attr("code");
            var checkerId = $('#new_taskList_checkerId').val();
       	    var checkerName = $("#new_taskList_checkerId").find("option:selected").text(); 
            var des = $("#new_taskList_des").val();
            if(title == ''){
            	setErrorAlert(name_empty);
            	return;
            }
            if(projectId == ''){
            	setErrorAlert(alert_project_empty);
            	return;
            }
            if(accId == ''){
            	setErrorAlert(tasklist_default_actor_empty);
            	return;
            }
            
            if(checkerId == ''){
       		  setErrorAlert(tasklist_default_checker_empty);
       		  return;
       	   }

          var urlWork = url_addTaskList;
         	$.ajaxSettings.async = false;
         	 var params = {
         	     	 "id":id,
    	    		 "title" : title,
    				 "projectId" : projectId,
    				 "accId" : accId,
    				 "checkerId":checkerId,
    				 "des" : des
    		    };
         	var result = "";
         	var num = 0;
         	$.getJSON(urlWork,params,function(data){
         		result = data.result;
         	});

         	if(result == 0){
    			setSucecessAlert(alert_success);
    		}
         	if(result > 0){
         		$("#new_task_project_list_Id option[value='']").remove();
         		$("#new_task_project_list_Id").prepend("<option value='"+projectId+"_"+result+"' projectCode='"+projectCode+"'>"+projectName+"&nbsp;-&nbsp;"+title+"</option>"); 
         		$("#new_task_project_list_Id").prepend("<option value=''>"+menu_select_project_tasklist+"</option>");  
         		$("#new_task_project_list_Id").val(projectId+"_"+result);
         		$("#new_task_project_list_Id").change();
            	$("#taskListModal").modal('toggle');
    			setSucecessAlert(alert_new);
    		}
    		if(result == -1){
    			setErrorAlert(alert_timeout);
    		}
    		if(result == -2){
    			setErrorAlert(alert_empty);
    		}
    		if(result == -3){
    			setErrorAlert(alert_access_wrong);
    		}
    		if(result == -4){
    	        setErrorAlert(alert_fail);
    	     }
    		if(result == -6){
    	    	setErrorAlert(operator_project_file_error);
    	    }
    		if(result == -7){
    	    	setErrorAlert(tasklist_name_exist);
    	    }
        }
      
      
    //获取文件上传的ids, 以逗号隔开
  	function getFileIds(){
  		var oInputs = $("#files-container input");
  		var ids = "";
  		if(oInputs.length > 0){
  			for(var i = 0; i < oInputs.length; i++){
  				ids +=  $(oInputs[i]).attr("id").replace(/---/, ".") + ",";
  			}
  			ids = ids.substring(0,ids.length-1);
  		}
  		return ids;
  	}

  	function getFileNames(){
  		var oInputs = $("#files-container input");
  		var names = "";
  		if(oInputs.length > 0){
  			for(var i = 0; i < oInputs.length; i++){
  				names +=  $(oInputs[i]).attr("fname") + ",";
  			}
  			names = names.substring(0,names.length-1);
  		}
  		return names;
  	}

  	function removeUploadFiles(){
  		$("#table_fileContainer").empty();
  		$("#files-container").empty();
  	}
  	
  	function updateTaskEntryOnly(opt){
  		var id = $("#new_task_id").val();
        var title = $("#new_task_title").val();
        var projectId = $("#new_task_projectId").val();
        var listId = $("#new_task_listId").val();
        var projectListName = $("#new_task_project_list_Id option:selected").text();
        var projectListArray = projectListName.split("-");
        var projectName = projectListArray[0];
        var listName = projectListArray[1]; 
        var actorId = $("#new_task_actorId").val();
        var actorName = $("#new_task_actorId option:selected").text();
        var checkerId = $("#new_task_checkerId").val();
        var checkerName = $("#new_task_checkerId option:selected").text();
        var maybeDay = $("#new_task_maybeDay").val();
        var maybeTime = $("#new_task_maybeTime").val();
        var weight = $("#new_task_weight").val();
        var priority = $("#new_task_priority").val();
        var status = $("#new_task_status").val();
        var des = $("#new_task_des").html();
        var milestoneId =  $("#new_task_milestoneId").val();

        if(title == ''){
        	setErrorAlert(alert_title_empty);
        	return;
        }
        if(listId == null || listId == ''){
        	setErrorAlert(alert_list_empty);
        	return;
        }
        if(projectId == null || projectId == ''){
        	setErrorAlert(alert_project_empty);
        	return;
        }
        if(actorId == null || actorId == ''){
        	setErrorAlert(alert_actor_empty);
        	return;
        }
        
        if(maybeDay == ''){
        	setErrorAlert(alert_maybeDay_empty);
        	return;
        }
        
        if(maybeTime == ''){
        	setErrorAlert(alert_maybeTime_empty);
        	return;
        }
        
        var processType = $("#task-process-type").val();
        var customerId = $("#new_task_customerId").val();
        var opportunityId = $("#new_task_opportunityId").val();
        if(id== "" && processType == 1 && customerId == ""){
        	setErrorAlert(alert_customer_empty);
        	return;
        }
        if(id== "" && processType == 2 && opportunityId == ""){
        	setErrorAlert(alert_opportunity_empty);
        	return;
        }
        
        var params = {
    	     	 "id":id,
	    		 "title" : title,
				 "projectId" : projectId,
				 "listId" : listId,
				 "actorId" : actorId,
				 "checkerId" : checkerId,
				 "maybeDay" : maybeDay,
				 "maybeTime" : maybeTime,
				 "weight" : weight,
				 "processType" : processType,
				 "customerId" : customerId,
				 "opportunityId" : opportunityId,
				 "priority" : priority,
				 "status" : status,
				 "milestoneId" : milestoneId,
				 "des" : des
		    };

     	$.ajax({
	        url : url_update_task,
	        type:'post',
	        data: params,
	        dataType: 'json',
	        success:function(data) {
          	var result = data.result;
          	var num = data.num;
          	var bean = data.bean;
          	if(result > 0){
          		var count = $("#task_count").html();
                count = parseInt(count) + parseInt(num);
                $("#task_count").html(count);
                
                addTaskRow(bean);
                if($("#span-task-count").length > 0){
                	var taskCount = $("#span-task-count").html();
                	taskCount = parseInt(taskCount) + 1;
                	$("#span-task-count").html(taskCount);
                }
          		if(opt == 1){
                 	$("#myModal").modal('hide');
             	}else{
             		initAfterSaveForm();
             	}
          	}
          	if(result == 0){
          		
          		var count = $("#task_count").html();
                count = parseInt(count) + parseInt(num);
                $("#task_count").html(count);
                
                updateTaskEntryShow(bean);
                
                setSucecessAlert(data.msg);
                $("#myModal").modal('hide');
                
    		}
    		if(result < 0){
    			setErrorAlert(data.msg);
    		}
	        },
           error: function (XMLHttpRequest, textStatus, errorThrown) {
               if(textStatus == 'timeout'){
               	setErrorAlert(error_connect_timeout);
               }else{
               	setErrorAlert(error_connect_exception);
	            }
           },
           complete: function(xhr, ts){
        	   loading("hide");
	       }
	});
  	}
  	
  	function updateTaskEntryShow(bean) {
  		if(bean == null || bean == "")
  			return ;
  		if($("#task_menu_"+bean.id).length > 0){
 			$("#task_menu_"+bean.id).remove();
		}
  		
  		$("#badge_info_status_"+bean.id).html("");
  		if(bean.status == 1)
  			$("#badge_info_status_"+bean.id).html("&nbsp;"+show_task_status_1);
		else if(bean.status == 40)
			$("#badge_info_status_"+bean.id).html("&nbsp;"+show_task_status_40);
		else if(bean.status == 50)
			$("#badge_info_status_"+bean.id).html("&nbsp;"+show_task_status_50);
		else if(bean.status == 60)
			$("#badge_info_status_"+bean.id).html("&nbsp;"+show_task_status_60);
		else if(bean.status == 30)
			$("#badge_info_status_"+bean.id).html("&nbsp;"+show_task_status_30);
		else if(bean.status == 20)
			$("#badge_info_status_"+bean.id).html("&nbsp;"+show_task_status_20);
  		
  		var maybeDateSpan = "<span>";
  		if(bean.maybeDay < todayStr && (bean.status == 10 || bean.status == 20 || bean.status == 60)){
    		 maybeDateSpan = "<span style=\"color: red;font-weight: bold;\">";
    	 }
  		
		 if(bean.validIssue > 0){
			 $("#span-task-issue-count-"+bean.id).html("<i class=\"icon-exclamation-sign icon-white\"></i>&nbsp;"+bean.fixedIssue+"/"+bean.validIssue);
			 $("#span-task-issue-count-"+bean.id).show();
		 }
		 
		 var actorStatus = $("#task-list-tr-"+bean.id).attr("actorStatus");
		 var finishFlag = $("#task-list-tr-"+bean.id).attr("finishFlag");
		 
		 var actorStr = "";
		 if(actorStatus == undefined || actorStatus == 1){
			 actorStr = "&nbsp;<i class=\"icon-user\"></i>"+bean.actorName;
		 }
		 
		 var finishStyle = "";
	 	 if(finishFlag != undefined && finishFlag == 1 && bean.status == 40){
	 		finishStyle = "style=\"text-decoration:line-through;color: #000000;\"";
	 	  }
  		
		//newlineFlag 小于1200的屏幕，换行处理
		var newlineStr = "&nbsp; ";
 		if(newlineFlag == 1)
 			newlineStr="<br>";
 		 
  		bean.title = bean.title.replace(/</g,"&lt;").replace(/>/g,"&gt;");
  		$("#entry_title_"+bean.id).val(bean.title);
  	    $("#entry_projectId_"+bean.id).val(bean.projectId);
  	    $("#entry_listId_"+bean.id).val(bean.listId);
  	    $("#entry_milestoneId_"+bean.id).val(bean.milestoneId);
  	    $("#entry_maybeDay_"+bean.id).val(bean.maybeDay);
  	    $("#entry_maybeTime_"+bean.id).val(bean.maybeTime);
  	    $("#entry_weight_"+bean.id).val(bean.weight);
  	    $("#entry_status_"+bean.id).val(bean.status);
  	    $("#entry_priority_"+bean.id).val(bean.priority);
  	    $("#entry_des_"+bean.id).html(bean.des);
  	    $("#entry_actorId_"+bean.id).val(bean.actorId);
  	    $("#entry_checkerId_"+bean.id).val(bean.checkerId);
  	    var td2 = "";
  	    td2 = td2 + maybeDateSpan + bean.maybeDay+"</span>&nbsp;<a href=\"javascript:void(0);\" "+finishStyle+" onclick=\"showTaskDetailDiv('"+bean.id+"')\">"+bean.title+"</a>";
  	    //td2 = td2 + maybeDateSpan + bean.maybeDay+"</span>&nbsp;<a href=\""+url_task_detail+bean.code+"\">"+bean.title+"</a>";
  	    td2 = td2 + newlineStr + "<span style=\"font-size: 12px;color: #BBBBBB;\">#"+bean.projectName + " - "+ bean.listName + actorStr + "&nbsp;<i class=\"icon-time gray\"> </i>"+bean.maybeTime + menu_hour+"</span>";
  		    $("#entry_td_"+bean.id).html(td2);

  		var priorityStyle = "";
  	 	if(bean.priority == 30)
  			 priorityStyle = "td-priority-high";
  	 	if(bean.priority == 10)
  			 priorityStyle = "td-priority-low";
  	 	$("#entry_td_"+bean.id).parent().attr("class", priorityStyle);
  	 	
  	   //更新数据里的任务
  	 	if(data_task_list != null && data_task_list.length > 0){
    		for(var i=0;i<data_task_list.length;i++){
    			if(data_task_list[i].id == bean.id){
    				data_task_list.splice(i,1,bean);
    			}
    		}
    	}
  	 	
  	 	
  	}
      
      function addTaskRow(bean){
    	 var tableId = 'table_tasklist'; 
      	 addTaskToTable(tableId,bean);
      }
      
      function addMenuTaskRow(bean){
     	 var tableId = 'table_tasklist'; 
     	addMenuTaskToTable(1,tableId,bean);
       }
      
      //reportStatus
      function addNonMenuTaskRow(bean){
     	 var tableId = 'table_tasklist'; 
     	 addMenuTaskToTable(0,tableId,bean);
       }
      
      function prepareDateDiv(){
      	var dateId = "";
    	  	var dateName = "";
    		dateId = "date_overdue";
    		dateName = date_list_overdue;
    		if($("#table_tasklist_"+dateId).length == 0){
    			$("#div_table_tasklist").append("<div id=\"div_list_table_"+dateId+"\"></div>");
   		 		$("#div_list_table_"+dateId).append("<h5>"+dateName+"</h5>");
   		 		$("#div_list_table_"+dateId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+dateId+"\"><tbody></tbody></table>");
   	 		}
    		$("#div_list_table_"+dateId).hide();
   	 	
   	 	
    		dateId = "date_today";
    		dateName = date_list_today;
    		if($("#table_tasklist_"+dateId).length == 0){
  	 	    $("#div_table_tasklist").append("<div id=\"div_list_table_"+dateId+"\"></div>");
   		 	$("#div_list_table_"+dateId).append("<h5>"+dateName+"</h5>");
       	 	$("#div_list_table_"+dateId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+dateId+"\"><tbody></tbody></table>");
   	 	}
    		$("#div_list_table_"+dateId).hide();
   	 	
    		dateId = "date_weekday";
    		dateName = date_list_weekday;
    		if($("#table_tasklist_"+dateId).length == 0){
  	 	    $("#div_table_tasklist").append("<div id=\"div_list_table_"+dateId+"\"></div>");
   		 	$("#div_list_table_"+dateId).append("<h5>"+dateName+"</h5>");
       	 	$("#div_list_table_"+dateId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+dateId+"\"><tbody></tbody></table>");
   	 	}
    		$("#div_list_table_"+dateId).hide();
   	 	
    		dateId = "date_future";
    		dateName = date_list_future;
    		if($("#table_tasklist_"+dateId).length == 0){
  	 	    $("#div_table_tasklist").append("<div id=\"div_list_table_"+dateId+"\"></div>");
   		 	$("#div_list_table_"+dateId).append("<h5>"+dateName+"</h5>");
       	 	$("#div_list_table_"+dateId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+dateId+"\"><tbody></tbody></table>");
   	 	}
    		$("#div_list_table_"+dateId).hide();
      }
      
      function  addListTaskRow(bean){
     	 if($("#table_tasklist_"+bean.listId).length == 0){
     	 	     $("#div_table_tasklist").append("<div id=\"div_list_table_"+bean.listId+"\"></div>");
     	 	     var projectListName = bean.listName;
     	 	     if(bean.type != 1)
     	 	    	projectListName = bean.projectName+" - "+bean.listName;
      		 $("#div_list_table_"+bean.listId).append("<h5>"+projectListName+"</h5>");
          	 $("#div_list_table_"+bean.listId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+bean.listId+"\"><tbody></tbody></table>");
      	 }
       	var tableId = 'table_tasklist_'+bean.listId; 
       	bean.reportStatus = bean.status;
     	addTaskToTable(tableId,bean);
        }
      
      function addDateTaskRow(bean){
    	  //dateId date_overdue,date_today,date_weekday,date_future
    	  var dateId = bean.maybeDay;
    	  var dateName = bean.maybeDay;
    	  if(bean.maybeDay < todayStr){
    		  dateId = "date_overdue";
    		  dateName = date_list_overdue;
    	  }
    	  if(bean.maybeDay == todayStr){
    		  dateId = "date_today";
    		  dateName = date_list_today;
    	  }
    	  if(bean.maybeDay > todayStr && bean.maybeDay <= currentDate){
    		  dateId = "date_weekday";
    		  dateName = date_list_weekday;
    	  }
    	  if( bean.maybeDay > currentDate){
    		  dateId = "date_future";
    		  dateName = date_list_future;
    	  }
    	  
    	  if($("#table_tasklist_"+dateId).length == 0){
   	 	     $("#div_table_tasklist").append("<div id=\"div_list_table_"+dateId+"\"></div>");
    		 $("#div_list_table_"+dateId).append("<h5>"+dateName+"</h5>");
        	 $("#div_list_table_"+dateId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+dateId+"\"><tbody></tbody></table>");
    	 }
    	  
    	 $("#div_list_table_"+dateId).show();
    	  
     	var tableId = 'table_tasklist_'+dateId; 
     	bean.reportStatus = bean.status;
     	addTaskToTable(tableId,bean);
      }
      
      function  addTodayTaskRow(bean){
        	var tableId = 'table_tasklist_future'; 
        	if(bean.todayFlag == 1){
        		tableId = 'table_tasklist_today'; 
        		if($("#today_tr_tips").length > 0)
        			$("#today_tr_tips").remove();
        	}else{
        		if($("#non_today_tr_tips").length > 0)
        			$("#non_today_tr_tips").remove();
        	}
        	bean.reportStatus = bean.status;
        	addTaskToTable(tableId,bean);
       }
      
      //reportStatus
      function addReoportTaskRow(bean){
    	  if($("#table_tasklist_"+bean.actorId).length == 0){
  	 	     $("#div_table_tasklist").append("<div id=\"div_list_table_"+bean.actorId+"\"></div>");
   		     $("#div_list_table_"+bean.actorId).append("<h5>"+bean.actorName+"</h5>");
       	     $("#div_list_table_"+bean.actorId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+bean.actorId+"\"><tbody></tbody></table>");
    	  }
    	var tableId = 'table_tasklist_'+bean.actorId; 
    	addMenuTaskToTable(0,tableId,bean);
      }
      
      function addDateFinishTaskRow(bean){
    	  //dateId date_overdue,date_today,date_weekday,date_future
    	  var dateId = bean.endDay;
    	  var dateName = bean.endDay;
    	  if(bean.finishDay == ""){
    		  dateId = bean.maybeDay;
        	  dateName = bean.maybeDay;
    	  }
    	 
    	  if($("#table_tasklist_"+dateId).length == 0){
   	 	     $("#div_table_tasklist").append("<div id=\"div_list_table_"+dateId+"\"></div>");
    		 $("#div_list_table_"+dateId).append("<h5>"+dateName+"</h5>");
        	 $("#div_list_table_"+dateId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+dateId+"\"><tbody></tbody></table>");
    	 }
    	  
     	var tableId = 'table_tasklist_'+dateId; 
     	bean.reportStatus = bean.status;
     	addTaskToTable(tableId,bean);
      }
      
      function addDateCreateTaskRow(bean){
    	  var dateId = bean.createDate;
    	  var dateName = bean.createDate;
    	  if(bean.createDate == ""){
    		  dateId = bean.maybeDay;
        	  dateName = bean.maybeDay;
    	  }
    	 
    	  if($("#table_tasklist_"+dateId).length == 0){
   	 	     $("#div_table_tasklist").append("<div id=\"div_list_table_"+dateId+"\"></div>");
    		 $("#div_list_table_"+dateId).append("<h5>"+dateName+"</h5>");
        	 $("#div_list_table_"+dateId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+dateId+"\"><tbody></tbody></table>");
    	 }
    	  
     	var tableId = 'table_tasklist_'+dateId; 
     	bean.reportStatus = bean.status;
     	addTaskToTable(tableId,bean);
      }
      
      function addDateMaybeTaskRow(bean){
    	  var dateId = bean.maybeDay;
    	  var dateName = bean.maybeDay;
    	 
    	  if($("#table_tasklist_"+dateId).length == 0){
   	 	     $("#div_table_tasklist").append("<div id=\"div_list_table_"+dateId+"\"></div>");
    		 $("#div_list_table_"+dateId).append("<h5>"+dateName+"</h5>");
        	 $("#div_list_table_"+dateId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+dateId+"\"><tbody></tbody></table>");
    	 }
    	  
     	var tableId = 'table_tasklist_'+dateId; 
     	bean.reportStatus = bean.status;
     	addTaskToTable(tableId,bean);
      }
      
      function addActorTaskRow(bean){
     	 	if($("#table_tasklist_"+bean.actorId).length == 0){
     	 	     $("#div_table_tasklist").append("<div id=\"div_list_table_"+bean.actorId+"\"></div>");
      		 $("#div_list_table_"+bean.actorId).append("<h5>"+bean.actorName+"</h5>");
          	 $("#div_list_table_"+bean.actorId).append("<table class=\"table table-hover\" id=\"table_tasklist_"+bean.actorId+"\"><tbody></tbody></table>");
      	 }
       	var tableId = 'table_tasklist_'+bean.actorId; 
       	bean.reportStatus = bean.status;
       	addTaskToTable(tableId,bean);
        }
      
      function  addTaskToTable(tableId,bean){
    	  addMenuTaskToTable(1,tableId,bean)
      }
      
      //type 0是完成的 1是逾期的
      function addWorklogTaskToTable(bean){
    	  var tableId = "table_tasklist_overdue";
    	  if(bean.status == 40)
    		  tableId = "table_tasklist_finish";
    	  addMenuTaskToTable(0, tableId, bean);
      }
      
      function addMenuTaskToTable(memuFlag,tableId,bean){
    	  addTaskDetailToTable(0, memuFlag,tableId,bean, 1, 0);
      }
      /*
       * memuFlag 0不显示 1显示
       * actorStatus 0 不显示  1显示执行人
       * 
       * finishFlag 0完成的没有删除线  1有删除线
       * 
       * firstFlag 1最前
       * 
       * 
       * */
      function addTaskDetailToTable(firstFlag, memuFlag,tableId,bean,actorStatus, finishFlag){
    	 var table1 = $('#'+tableId);
    	 var title = bean.title;
    	 if(title != null)
    		 bean.title = title.replace(/</g,"&lt;").replace(/>/g,"&gt;");
    	 
    	 var maybeDateSpan = "<span>";
    	 if(bean.maybeDay < todayStr && (bean.status == 10 || bean.status == 20 || bean.status == 60)){
    		 maybeDateSpan = "<span style=\"color: red;font-weight: bold;\">";
    	 }
    	 var priorityStyle = "";
    	 if(bean.priority == 30)
    		 priorityStyle = "td-priority-high";
    	 if(bean.priority == 10)
    		 priorityStyle = "td-priority-low";
     	 var row = $("<tr class='"+priorityStyle+"' id='task-list-tr-"+bean.id+"' actorStatus=\""+actorStatus+"\" finishFlag=\""+finishFlag+"\"></tr>"); 
 		 var finish_str = "";
 		 if(bean.reportStatus == 40)
 			 finish_str = "&nbsp;&nbsp;at "+bean.finishDay;
 		 var menuStr = "";
 		 if(memuFlag == 1)
 			menuStr = "onmouseover=\"showMenu("+bean.id+");\" onmouseout=\"hiddenMenu("+bean.id+");\"";
 		 
 		 //newlineFlag 小于1200的屏幕，换行处理
 		 var newlineStr = "&nbsp; ";
 		 if(newlineFlag == 1)
 			newlineStr="<br>";
 		 
 		 var processStr = "";
 		 if(bean.processType == 1){
 			processStr = "&nbsp;<i class=\"icon-flag\" data-original-title=\"客户\" rel=\"tooltip\" ></i><a href=\""+url_customer_detail+bean.customerCode+"\">"+bean.customerName+"</a>";
 		 }
 		if(bean.processType == 2){
 			processStr = "&nbsp;<i class=\"icon-tag\" data-original-title=\"销售机会\" rel=\"tooltip\"></i><a href=\""+url_opportunity_detail+bean.opportunityCode+"\">"+bean.opportunityName+"</a>";
 		 }
 		
 		//执行人
 		var actorStr = "";
 		if(actorStatus == 1){
 			actorStr = "&nbsp;<i class=\"icon-user\"></i>"+bean.actorName;
 		}
 		var finishStyle = "";
 		if(finishFlag == 1 && bean.status == 40){
 			finishStyle = "style=\"text-decoration:line-through;color: #000000;\"";
 		}
 		 var td1 = $("<td  "+menuStr+" id=\"entry_td_"+bean.id+"\" >"); 
 		 td1.append(maybeDateSpan + bean.maybeDay+"</span>&nbsp;<a href=\"javascript:void(0);\" "+finishStyle+" onclick=\"showTaskDetailDiv('"+bean.id+"')\">"+bean.title+"</a>");
 		 //td1.append(maybeDateSpan + bean.maybeDay+"</span>&nbsp;<a href=\""+url_task_detail+bean.code+"\">"+bean.title+"</a><img class=\"avatar-mini\" src=\""+url_root+bean.actorAvatar+"\"></img>");
 		 td1.append(newlineStr+"<span style=\"font-size: 12px;;color: #BBBBBB;\">#"+bean.projectName + " - "+ bean.listName + actorStr + "&nbsp;<i class=\"icon-time gray\"></i>"+bean.maybeTime+" "+menu_hour+finish_str+processStr+"</span>"); 
 		 var td2 = $("<td style=\"text-align: right;width:200px;\"></td>");
 		 td2.append("<input type=\"hidden\" id=\"entry_id_"+bean.id+"\" value=\""+bean.id+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_title_"+bean.id+"\" value=\""+bean.title+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_code_"+bean.id+"\" value=\""+bean.code+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_milestoneId_"+bean.id+"\" value=\""+bean.milestoneId+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_projectId_"+bean.id+"\" value=\""+bean.projectId+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_projectStatus_"+bean.id+"\" value=\""+bean.projectStatus+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_listId_"+bean.id+"\" value=\""+bean.listId+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_processType_"+bean.id+"\" value=\""+bean.processType+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_customerId_"+bean.id+"\" value=\""+bean.customerId+"\">");
 		td2.append("<input type=\"hidden\" id=\"entry_customerCode_"+bean.id+"\" value=\""+bean.customerCode+"\">");
 		td2.append("<input type=\"hidden\" id=\"entry_customerName_"+bean.id+"\" value=\""+bean.customerName+"\">");
		 td2.append("<input type=\"hidden\" id=\"entry_opportunityId_"+bean.id+"\" value=\""+bean.opportunityId+"\">");
		 td2.append("<input type=\"hidden\" id=\"entry_opportunityCode_"+bean.id+"\" value=\""+bean.opportunityCode+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_opportunityName_"+bean.id+"\" value=\""+bean.opportunityName+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_actorId_"+bean.id+"\" value=\""+bean.actorId+"\">");
 		td2.append("<input type=\"hidden\" id=\"entry_checkerId_"+bean.id+"\" value=\""+bean.checkerId+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_maybeDay_"+bean.id+"\" value=\""+bean.maybeDay+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_maybeTime_"+bean.id+"\" value=\""+bean.maybeTime+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_weight_"+bean.id+"\" value=\""+bean.weight+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_priority_"+bean.id+"\" value=\""+bean.priority+"\">");
 		 td2.append("<input type=\"hidden\" id=\"entry_status_"+bean.id+"\" value=\""+bean.status+"\">");
 		 td2.append("<div style=\"display: none;\" id=\"entry_des_"+bean.id+"\">"+bean.des+"</div>");
 		if(bean.reportStatus == 1)
			 td2.append(" <span class=\"badge badge-info\" id=\"badge_info_status_"+bean.id+"\" title=\"task status\">&nbsp;"+show_task_status_1+"</span>");
 		else if(bean.reportStatus == 40)
 			 td2.append(" <span class=\"badge badge-info\" id=\"badge_info_status_"+bean.id+"\" title=\"task status\">&nbsp;"+show_task_status_40+"</span>");
 		else if(bean.reportStatus == 50)
			 td2.append(" <span class=\"badge badge-info\" id=\"badge_info_status_"+bean.id+"\" title=\"task status\">&nbsp;"+show_task_status_50+"</span>");
 		else if(bean.reportStatus == 60)
 			 td2.append(" <span class=\"badge badge-info\" id=\"badge_info_status_"+bean.id+"\" title=\"task status\">&nbsp;"+show_task_status_60+"</span>");
 		else if(bean.reportStatus == 30)
			 td2.append(" <span class=\"badge badge-info\" id=\"badge_info_status_"+bean.id+"\" title=\"task status\">&nbsp;"+show_task_status_30+"</span>");
 		else if(bean.reportStatus == 20)
			 td2.append(" <span class=\"badge badge-info\" id=\"badge_info_status_"+bean.id+"\" title=\"task status\">&nbsp;"+show_task_status_20+"</span>");
 		else
 			td2.append(" <span class=\"badge badge-info\" id=\"badge_info_status_"+bean.id+"\" title=\"task status\"></span>");
 		 var starredClass = "";
 		 var starredTitle = mene_collection;
 		 if(bean.starredStatus){
 			starredClass  = " starred";
 			starredTitle = mene_cancelCollection;
 		 }
 		var commentStatus = "style=\"display:none;\"";
 		if(bean.commentCount > 0)
 			commentStatus = "";
 		var issueStatus = "style=\"display:none;\"";
		 if(bean.validIssue > 0)
			 issueStatus = "";
		 
 		 td2.append("&nbsp;<span class=\"badge badge-info\" data-original-title=\"讨论数量\" rel=\"tooltip\" title=\"讨论数量\" id=\"span-task-comment-count-"+bean.id+"\" "+commentStatus+"><i class=\"icon-comment icon-white\"></i>&nbsp;"+bean.commentCount+"</span>");
 		 td2.append("&nbsp;<span class=\"badge badge-warning\" data-original-title=\"解决数/问题总数\" rel=\"tooltip\" title=\"解决数/问题总数\" id=\"span-task-issue-count-"+bean.id+"\" "+issueStatus+"><i class=\"icon-exclamation-sign icon-white\"></i>&nbsp;"+bean.fixedIssue+"/"+bean.validIssue+"</span>");
 		 td2.append("&nbsp;<span id=\"ok_task_star_"+bean.id+"\" data-original-title=\""+starredTitle+"\" rel=\"tooltip\" onclick=\"starred("+bean.id+");\" class=\"big-star"+starredClass+"\" ></span>");
 		 
 		 row.append(td1); 
 		 row.append(td2); 
 		 if(firstFlag == 0)
 			 table1.append(row);
 		 else
 			table1.prepend(row);
 		 
 		$("span[rel=tooltip]").tooltip({
			placement : 'top',
		});
 		
 		$("i[rel=tooltip]").tooltip({
			placement : 'top',
		});
      }
      
      