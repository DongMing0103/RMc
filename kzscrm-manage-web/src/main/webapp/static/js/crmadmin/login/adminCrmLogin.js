
	//判断是否已经登录
	 $.ajax({  
          type : "post",  
          url : "/judgeLogin",  
          async : false,  
          success : function(data){  
        	  if (data.indexOf('login-content') > -1) {
              }else{
             	 window.location.reload();
              }
          }  
     }); 

	$.cookie("loadUrl",null);
	$(document).ready(function () {
		$.cookie("loadUrl",null); 
        $("#userId").focus();
        $('#userId').keydown(function (e) {
            if (e.keyCode == 13) {
                login();
            }
        });
        $('#password').keydown(function (e) {
            if (e.keyCode == 13) {
                login();
            }
        });
        $('#imgCode').keydown(function (e) {
            if (e.keyCode == 13) {
                login();
            }
        });
    });

    function login() {
    	console.log($('#roleSelect option:selected').val());
    	console.log($('#roleSelect option:selected').text());
        var uValue = $('#userId').val();
        var pValue = $('#password').val();
        var imgCode = $('#imgCode').val();
        var errorMsg=$('.error-msg');
        if($(".jurisdiction").get(0).selectedIndex==0){
        	errorMsg.css('visibility','visible');
        	errorMsg.text("请选择权限");
        	$('#refreshImgCode').click();
        	return;
        }
        if((uValue == "" || uValue == null)&&(pValue == "" || pValue == null)){
        	errorMsg.css('visibility','visible');
        	errorMsg.text("请输入登录账号和密码");
        	$('#refreshImgCode').click();
        	return;
        }
        if (uValue == "" || uValue == null) {
        	errorMsg.css('visibility','visible');
        	errorMsg.text("请输入登录账号");
        	$('#refreshImgCode').click();
            return;
        }
        if (pValue == "" || pValue == null) {
        	errorMsg.css('visibility','visible');
        	errorMsg.text("请输入密码");
        	$('#refreshImgCode').click();
            return;
        }
        
        if (imgCode == "" || imgCode == null) {
        	errorMsg.css('visibility','visible');
        	errorMsg.text("请输入图片验证码");
        	$('#refreshImgCode').click();
            return;
        } 
      
      //验证图片
        $.ajax({
        	cache: false,
            type: "POST",
            url: GlobAppContextPath+"/nl/verifyCodeImg",
            data: {"imgCode":imgCode},
            dataType: "json",
            success: function (data) {
                if (data && data.code == 0) {
                	var data = {"username": $('#userId').val(), "password": hex_md5($('#password').val()),"role":$('#roleSelect option:selected').val(),"traderLogin":$("#traderLogin:checked").val()};
                	submitAjax(data);
                } else {
                	$('.error-msg').css('visibility','visible');
                	$('.error-msg').text(data.desc);
                	$('#refreshImgCode').click();
                }
            }
        });
    }

    // 提交
    function submitAjax(data) {
        $.ajax({
            type: "post",
            url: GlobAppContextPath+"/doLogin",
            data: data,
            success: function (data) {
                if (data.data == 4) {
                    window.location =GlobAppContextPath+ "/index";
                } else {
                    console.log(data.message);
                    $('.error-msg').css('visibility','visible');
                    $('.error-msg').text(data.message);
                    $('#refreshImgCode').click();
                }
            }
        });
    }
     