/**
* 手机和用户，公司级联功能.在select2框架的基础上实现的
* <p>
* 当输入手机号后，用户和公司的输入框数据会默认加载出来并赋值到相应输入框中，
* 如果有值，用户和公司的输入框不能改变;
* 如果没有值，用户和公司的输入框还是可以选择的
* </p>
* @author YongW
* @date 2015-04-10
*/
(function($){
	
	var PhoneLinkage;
	
	/**
	 * 定义手机级联功能对象
	 */
	PhoneLinkage = clazz(Object, {
		
		/**
		 * 初始化
		 * @param opts 
		 */
		init: function(opts){
			
			//PhoneLinkage.options = this.options = opts;
			
			this.options = opts;
			
			this.element = this.options.element;
			
			this.changeEvent(this.element);
			
			//select2框架绑定
			this.select2LoadData(this.options);
			
			//加载公司
			loadCompanyDataBySelect2($(this.options.companyIdDom), false,  this.options.defaultCompanyName, this.options.defaultCompanyId,this.options.companyNameDom);
		},
		
		/**
		 * 手机改变事件
		 */
		changeEvent: function(obj){
			var $self = this;
			
			$.each(obj, function(k, v){
				$(v).on("blur", function(e){
					if(!isMobile(this.value)){
						$($self.options.userIdDom).select2('readonly','');
	            		$($self.options.companyIdDom).select2('readonly','');
						return;
					}
					$self.options.defaultPhone = this.value;
					$self.loadNameAndCompany();
				});
			});
			
		},
		
		/**
		 * 用户select2
		 */
		select2LoadData: function(opts){
			var options = opts,
				$user = options.userNameDom,
				dataUrl = options.showData.user.url,
				showName = options.showData.user.name,
				$userId = options.userIdDom;
			
			
			$($userId).val(''); //先清空数据
			$($userId).select2({
				placeholder: options.defaultUserName || '请选择',
				allowClear: false,
				formatResult: function(result, container, query, escapeMarkup){ //返回结果时做的操作
					$($user).blur();
					return result[showName] || result.placeholder;
				},
				formatSelection: function(item){//选择下拉框值时做的操作
					$($user).blur();
					$($userId).val(item.id);
					$($user).val(item.realName);
					return item[showName]; //当前的输入框赋值
				},
				ajax:{
					url:dataUrl,
					dataType: 'json',
					data: function(params, pageNo){
						//请求时，加入的参数
						return{
							realName: params,
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
			$($userId).val(options.defaultUserId);
			$($user).val(options.defaultUserName);
			
		},
		
		/**
		 * 加载手机和公司信息
		 */
		loadNameAndCompany: function(){
			var options = this.options
				$self = this;
				$.ajax({
                    url: options.showData.user.url,
                    data:{"mobileNo":options.defaultPhone,"pageSize":1,"rdm":Math.random()},
                    dataType:'json',
                    type:'post',
                    success:function(data){
                    	$(options.userIdDom).select2('readonly','');
                		$(options.companyIdDom).select2('readonly','');
                    	if (data && data.total > 0) {
                            var curUser = data.rows[0];
                            
                            options.defaultUserId = curUser[options.showData.user.id];
                            options.defaultUserName = curUser[options.showData.user.name];
                            
                            //加载用户
                            $self.select2LoadData(options);

                            if (isNotNull(options.defaultUserId) && isNotNull(options.defaultUserName)) {
                            	
                            	if(options.userIdDom){
                            		//$(options.userIdDom).select2('readonly','readonly');
                            	}	
                                var company = loadCompany(curUser.id);
                                
                                loadCompanyDataBySelect2($(options.companyIdDom),false,company.companyName,company.companyId, options.companyNameDom);
                                
                                if (isNotNull(company) && isNotNull(company.companyId) && isNotNull(company.companyName)) {
                                   //$(options.companyIdDom).select2('readonly','readonly');
                                } 
                            } 
                    	}
                    }
				});
		}
	});
		
	
	/**
     * Creates a new class
     *
     * @param superClass
     * @param methods
     */
    function clazz(SuperClass, methods) {
        var constructor = function () {};
        constructor.prototype = new SuperClass;
        constructor.prototype.constructor = constructor;
        constructor.prototype.parent = SuperClass.prototype;
        constructor.prototype = $.extend(constructor.prototype, methods);
        return constructor;
    }
    
    /**
     * 手机号验证
     */
    function isMobile(str){
		var reg = /^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|17[0-9]{9}$|18[0-9]{9}$/;
		if(isNotNull(str) && reg.exec(str)){
			return true;
		}
		return false;
	}
    
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
	
	/**
	 * 参数列表
	 */
	PhoneLinkage.DEFAULTS = {
			showData: {
				'user': {'url': '/AdminUserAccount.do?action=query', 'id': 'id', 'name': 'realName','placeholder': '请选择用户'},
				'company': {'url': '/Company.do?action=query', 'id': 'id', 'name': 'companyName', 'placeholder': '请选择公司'}
			},
			userIdDom: null,
			userNameDom: null,
			companyIdDom: null,
			companyNameDom: null,
			defaultPhone: '',  //手机号默认值
			defaultUserName: null, //默认的用户名称
			defaultUserId: null, //默认的用户id
			defaultCompanyId: null, //默认的公司id
			defaultCompanyName: null, //默认的公司名称
			
	}

	/**
	* 
	*/
	$.fn.phoneLinkage = function(option){
		var value;
    	
    	//参数自定义，默认参数值设置
    	this.each(function(){
    		var $this = $(this),
    			options = $.extend({}, PhoneLinkage.DEFAULTS, option, {element: $this});
    			value = new PhoneLinkage();
    			
    			value.init(options);
    	});
    	
    	return typeof value === 'undefined' ? this : value;
	}
})(jQuery)