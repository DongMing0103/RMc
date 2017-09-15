(function ($) {
	//=======================基本函数========================	
	/**
	 * 判断是否是字符串类型.是返回true，否则返回false
	 */
	function isString(str){
		if(typeof(str) == 'string'){
			
			return true;
		}
		return false;
	}
	
	var AreaSelect;
	
    // ======================

    AreaSelect = function (options) {
        this.options = options;
        this.init();
    };
    
    /**
     * 初始化做的工作
     */
    AreaSelect.prototype.init = function () {
    	this.clearAll();
    	if(this.options.isCountry){
    		this.initCountry();
    	}else{
    		this.initRegion();
    	}
    };
    
    AreaSelect.prototype.clearAll = function () {
    	this.options.isCleanDefault = false;
    	$(this.options.divDom).empty();
    };
    
    
    /**
     * 初始化地区数据
     */
    AreaSelect.prototype.initCountry = function () {
    	var option = this.options;
    	var self = this;
    	if(isString(option.country)){
    		//=================初始化国家select下拉框=======================
    		var newCountry = self.getSelect(option, option.country); 
    		if(!newCountry || newCountry.length <= 0){
    			//如果没有地区下拉框时，就创建一个
    			var containerParams = $.extend({}, option,{
    				id: option.country,
    				name: option.country,
    				msg: "请选择国家",
    				isShowNullMsg: true
    			});
    			option.createContainer(containerParams);
    		}
    		newCountry = self.getSelect(option, option.country);
    		newCountry.empty(); //保证数据为空
        	$(newCountry)[0].options.add(new Option("-请选择国家-",''));
        	//改变事件
        	if(isString(option.province)){
        		var tempProvince =  self.getSelect(option, option.province);
        		
        		//当第一次加载时，需要添加一个改变事件
        		//if(!tempProvince || tempProvince.length <= 0){
        			$(newCountry).unbind('change').change(function(){
        				var val = $(this).val();
        				var text = $(this).find('option:selected').text();
        				var tempFlag = false;
        				
        				//省、市和县的数据要清除
        				var tempCity =  self.getSelect(option, option.city);
        				var tempArea =  self.getSelect(option, option.area);
        				var tempProvince =  self.getSelect(option, option.province);
        				
        				if(tempCity && tempCity.length > 0){
        					tempCity.remove();
        				}
        				if(tempArea && tempArea.length > 0){
        					tempArea.remove();
        				}
        				if(tempProvince && tempProvince.length > 0){
        					tempProvince.remove();
        				}
        				//没有选择国家时，地区的数据清空
        				if(!val || val == ''){
        					var tempRegion = self.getSelect(option, option.region);
        					tempRegion.empty();
        					$(tempRegion)[0].options.add(new Option("-请选择地区-",''));
        					$(tempRegion).blur();
        					
        					self.setAddress(option);
        				}else{
        					$.each(option.administrativeArea, function(index, adminArea){
        						if(text == adminArea){
        							tempFlag = true;
        							return false;
        						}
        					});
        					if(!tempFlag){
        						if(text == '中国'){//暂时只支持中国的地区
        							//选择的有国家时再加载大区数据
            						self.initRegion({'parentAreaCode': val});
        						}else{
        							var tempRegion = self.getSelect(option, option.region);
                					tempRegion.remove();
        						}
        					}
        					$(self.options.areaCode).val(val);
    						$(self.options.areaName).val(text);
        				}
        				
        				
        				self.reLoadHouseData(self.options, true);
        				
        			});
        		//}
        		
        	}
        	
        	//加载地区数据的请求参数
        	var params = $.extend({}, option, {
        		data: {'areaLevel':0 },
        		selectDom: option.country
        	});
        	//加载对地区数据,并赋值到地区下拉框中
        	self.ajax(params);
        }
    }
    
    /**
     * 初始化地区数据
     */
    AreaSelect.prototype.initRegion = function () {
    	var option = this.options;
    	var self = this;
    	if(isString(option.region)){
    		//=================初始化地区select下拉框=======================
    		var newRegion = self.getSelect(option, option.region); 
    		if(!newRegion || newRegion.length <= 0){
    			//如果没有地区下拉框时，就创建一个
    			var containerParams = $.extend({}, option,{
    				id: option.region,
    				name: option.region,
    				msg: "请选择地区",
    				isShowNullMsg: true
    			});
    			option.createContainer(containerParams);
    		}
    		newRegion = self.getSelect(option, option.region);
        	newRegion.empty(); //保证数据为空
        	$(newRegion)[0].options.add(new Option("-请选择地区-",''));
        	//改变事件
        	if(isString(option.province)){
        		var tempProvince =  self.getSelect(option, option.province);
        		
        		//当第一次加载时，需要添加一个改变事件
        		//if(!tempProvince || tempProvince.length <= 0){
        			$(newRegion).unbind('change').change(function(){
        				var val = $(this).val();
        				var text = $(this).find('option:selected').text();
        				var tempFlag = false;
        				
        				//市和县的数据要清除
        				var tempCity =  self.getSelect(option, option.city);
        				var tempArea =  self.getSelect(option, option.area);
        				if(tempCity && tempCity.length > 0){
        					tempCity.remove();
        				}
        				if(tempArea && tempArea.length > 0){
        					tempArea.remove();
        				}
        				
        				//没有选择地区时，省的数据清空
        				if(!val || val == ''){
        					tempProvince =  self.getSelect(option, option.province);
        					tempProvince.empty();
        					$(tempProvince)[0].options.add(new Option("-请选择省-",''));
        					$(tempProvince).blur();
        					
        					self.setAddress(option);
        				}else{
        					$.each(option.administrativeArea, function(index, adminArea){
        						if(text == adminArea){
        							tempFlag = true;
        							return false;
        						}
        					});
        					
        					if(tempFlag){
        						//特别行政区时，要清除县级下拉框
        						tempProvince =  self.getSelect(option, option.province);
        						tempProvince.remove();
    							self.initArea({'parentAreaCode': val});
        					}else{
        						//选择的有地区时再加载省份数据
        						self.initProvince({'parentAreaCode': val});
        					}
        					
        					$(self.options.areaCode).val(val);
    						$(self.options.areaName).val(text);
        				}
        				
        				
        				self.reLoadHouseData(self.options, true);
        				
        			});
        		//}
        		
        	}
        	
        	//加载地区数据的请求参数
        	var params = $.extend({}, option, {
        		data: {'areaLevel':1 },
        		selectDom: option.region
        	});
        	//加载对地区数据,并赋值到地区下拉框中
        	self.ajax(params);
        }
    };
    
    /**
     * 初始化省数据
     */
    AreaSelect.prototype.initProvince = function (query) {
    	var option = this.options;
    	var self = this;
    	var data = query || {};
    	data.areaLevel = 2;
    	if(isString(option.province)){
    		var newProvince = self.getSelect(option, option.province);
    		if(!newProvince || newProvince.length <= 0){
    			
    			var containerParams = $.extend({}, option,{
    				id: option.province,
    				name: option.province,
    				msg: "请选择省"
    			});
    			option.createContainer(containerParams);
    		}
    		newProvince = self.getSelect(option, option.province);;
        	newProvince.empty();
        	$(newProvince)[0].options.add(new Option("-请选择省-",''));
        	
        	//改变事件
        	if(isString(option.city)){
        		
    			var tempCity =  self.getSelect(option, option.city);
    			//只在第一次时才赋值改变事件
    			//if(!tempCity || tempCity.length <= 0){
    				$(newProvince).unbind('change').change(function(){
    					var val = $(this).val();
    					var text = $(this).find('option:selected').text();
    					var tag = false;
    					//市
    					tempCity =  self.getSelect(option, option.city);
    					//县
    					var tempArea =  self.getSelect(option, option.area);
    					if(tempArea && tempArea.length > 0){ //选择省时，清除县级下拉框
    						tempArea.remove();
    					}
    					//没有选择省份时，市区数据需要清空
    					if(!val ||val == ''){
    						tempCity.empty();
    						$(tempCity)[0].options.add(new Option("-请选择市-",''));
    						$(tempCity).blur();
    						
    						self.setAddress(option);
    					}else{
    						//省份有数据时，如果是直辖市，特殊处理
    						$.each(option.municipalities, function(index, munic){
    							if(munic == text){
    								tag = true;
    								return false;
    							}
    						});
    						
    						if(tag){
    							//直辖市时，要清除市级下拉框
    							tempCity =  self.getSelect(option, option.city);
        						tempCity.remove();
    							self.initArea({'parentAreaCode': val});
    						}else{
    							self.initCity({'parentAreaCode': val});
    						}
    						$(self.options.areaCode).val(val);
    						$(self.options.areaName).val(text);
    					}
    					
    					self.reLoadHouseData(self.options, true);
    				});
    			//}
        	}
        	
        	var params = $.extend({}, option, {
        		data: data,
        		selectDom: option.province
        	});
        	self.ajax(params);
        }
    };
    
    
    /**
     * 初始化市
     */
    AreaSelect.prototype.initCity = function (query) {
    	var option = this.options;
    	var self = this;
    	var data = query || {};
    	data.areaLevel = 3;
    	if(isString(option.city)){
    		var newCity = self.getSelect(option, option.city);
    		if(!newCity || newCity.length <= 0){
    			
    			var containerParams = $.extend({}, option,{
    				id: option.city,
    				name: option.city,
    				msg: "请选择市"
    			});
    			option.createContainer(containerParams);
    		}
    		newCity = self.getSelect(option, option.city);
        	newCity.empty();
        	$(newCity)[0].options.add(new Option("-请选择市-",''));
        	
        	
        	//改变事件
        	if(isString(option.area)){
        		
    			var tempArea =  self.getSelect(option, option.area);
    			//if(!tempArea || tempArea.length <= 0){
    			
    				$(newCity).unbind('change').change(function(){
    					var val = $(this).val();
    					if(!val || val == ''){
    						tempArea =  self.getSelect(option, option.area);
    						tempArea.empty();
    						$(tempArea)[0].options.add(new Option("-请选择县-",''));
    						$(tempArea).blur();
    						
    						self.setAddress(option);
    					}else{
    						self.initArea({'parentAreaCode': val});
    						$(self.options.areaCode).val(val);
    						$(self.options.areaName).val($(this).find('option:selected').text());
    					}
    					
    					
    					self.reLoadHouseData(self.options, true);
    				});
    			//}
        	}
        	var params = $.extend({}, option, {
        		data: data,
        		selectDom: option.city
        	});
        	self.ajax(params);
        }
    };
    
    /**
     * 初始化县
     */
    AreaSelect.prototype.initArea = function (query) {
    	var option = this.options;
    	var self = this;
    	var data = query || {};
    	data.areaLevel = 4;
    	if(isString(option.area)){
    		var newArea = self.getSelect(option, option.area);
    		if(!newArea || newArea.length <= 0){
    			
    			var containerParams = $.extend({}, option,{
    				id: option.area,
    				name: option.area,
    				msg: "请选择县",
    				isLast: true
    			});
    			option.createContainer(containerParams);
    		}
    		newArea = self.getSelect(option, option.area);
        	newArea.empty();
        	$(newArea)[0].options.add(new Option("-请选择县-",''));
        	var params = $.extend({}, option, {
        		data: data,
        		selectDom: option.area,
        		isCleanDefault: true,
        		isLast: true
        	});
        	
        	$(newArea).unbind('change').change(function(){
        		var areaAreaName = $(this).find("option:selected").text();
        		self.setAddress(option, areaAreaName, this.value);
        		if(this.value){
	        		$(self.options.areaCode).val($(this).val());
	        		$(self.options.areaName).val(areaAreaName);
        		}
        		
        		self.reLoadHouseData(self.options, true);
        	});
        	self.ajax(params);
        }
    };
    
    /**
     * 处理结果
     */
    AreaSelect.prototype.processResult = function (data, options) {
    	var option = options;
    	var self = this;
    	data = eval(data);

    	if(data.total > 0){
    		
    		var newArea = self.getSelect(option, option.selectDom);
			$(newArea).show();
    		var results = data.rows;
    		if(option.defaultCode){
    			option.defaultCode = option.defaultCode + '';
    		}
    		$.each(results, function(index, area){
    			if(option.defaultCode && option.defaultCode.indexOf(area.areaCode) >= 0){
    				//默认选中
					var opt = new Option(area.areaName, area.areaCode);
					$(opt).attr("selected", true);
					$(newArea)[0].options.add(opt);
					$(self.options.areaCode).val(area.areaCode);
					$(self.options.areaName).val(area.areaName);
					$(newArea).change();
    			}else{
    				$(newArea)[0].options.add(new Option(area.areaName, area.areaCode));
    			}
    		});
    		if(option.isCleanDefault){
    			self.options.defaultCode = false;
    		}
    		
    		if(!options.isLast){
    			self.setAddress(option);
    		}
    	}
    };
    
    /**
	 * 对详细地址赋值
	 */
    AreaSelect.prototype.setAddress = function(options, areaAreaName, areaAreaCode){
    		
    		//区 + 省 + 市 + 县
    		if(this.getSelect(options, options.region).val()){
    			options.regionName = this.getSelect(options, options.region).find("option:selected").text();
    			$(options.areaCode).val(this.getSelect(options, options.region).val());
    			$(options.areaName).val(options.regionName);
    		}else{
    			options.regionName = '';
    		}
    		if(this.getSelect(options, options.province).val()){
    			options.provinceName = this.getSelect(options, options.province).find("option:selected").text();
    			$(options.areaCode).val(this.getSelect(options, options.province).val());
    			$(options.areaName).val(options.provinceName);
    		}else{
    			options.provinceName = '';
    		}
    		if(this.getSelect(options, options.city).val()){
    			
    			options.cityName = this.getSelect(options, options.city).find("option:selected").text();
    			$(options.areaCode).val(this.getSelect(options, options.city).val());
    			$(options.areaName).val(options.cityName);
    		}else{
    			options.cityName = '';
    		}
    		
    		if(areaAreaCode){
    			$(options.areaCode).val(this.getSelect(options, options.area).val());
    			$(options.areaName).val(areaAreaName || '');
    		}
    		
    		areaAreaName = areaAreaName || '';
    	if(isString(options.address)){
    		
    		$(options.address).val(options.regionName + options.provinceName + options.cityName + areaAreaName); 
    	}
    }
    
    /**
     * 通过名称，获取当前div下面的select对象
     */
    AreaSelect.prototype.getSelect = function(options, selectName){
    	
    	return $(options.divDom).find("select[name=" + selectName +"]");
    }
    
    /**
     * 重新加载仓库信息
     */
    AreaSelect.prototype.reLoadHouseData = function(option){
    	
    	
    	//加载仓库,由仓库控件传入的,并且不是首次加载
		if(option.areChangeIsLoadHouse && !option.defaultCode){
			this.loadHouseData(option, true);
		}
    }
    
    AreaSelect.prototype.loadHouseData = function(option, isReload){
    	var $house = $(option.houseDom);
    	if(!$house){ //没有仓库节点时，就不用加载
    		return;
    	}
    	
    	if($(option.houseInputDom) &&  !isReload){
    		$(option.houseInputDom).val('');
    	}
    	
    	$house.val('');
    	
    	var timestamp = (new Date()).valueOf();

		var pageSize = 10;
		if (option.unNeedIndustryId == 0) {
			pageSize = 200;
		}
    	
    	$house.select2({
			placeholder: option.defaultHouseName || '请选择仓库',
			allowClear: false,
			formatResult: function(result, container, query, escapeMarkup){ //返回结果时做的操作
				return result.houseName || result.placeholder;
			},
			formatSelection: function(item){//选择下拉框值时做的操作
				//如果有仓库文本输入框，就需要赋值
				if($(option.houseInputDom)){
					$(option.houseInputDom).val(item.houseName);
				}
				$house.val(item.id);
				return item.houseName; //当前的输入框赋值
			},
			ajax:{
				url:"/GoodsStockHouse.do?action=query&t="+timestamp,
				dataType: 'json',
				data: function(params, pageNo){
					//请求时，加入的参数
					return{
						areaCode: $(option.areaCode).val(),
						pageSize: pageSize,
						//默认需要行业ID 值为1时 后台查询全部行业的分类与牌号 只在聚贸平台下作用
						unNeedIndustryId : option.unNeedIndustryId,
						industryId : WEBSITE_COMMON_OBJECT.industryId,
						houseName : params,
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
    	
    	if($(option.houseInputDom) && !isReload){
			$(option.houseInputDom).val(option.defaultHouseName || '');
		}
    	$house.val(option.defaultHouseId || '');
    	
    	option.defaultHouseName = false;
    	option.defaultHouseId = false;
    }
    
    /**
	 * 
	 */
    AreaSelect.prototype.ajax = function(options){
		
		var self = this;
		var url = options.url,
		data = options.data || {},
		dataType = options.dataType || 'json';
		data.pageSize = 100;
		$.ajax({
			cache: false,
	        type: "POST",
	        url: url,
	        data: data,
	        async: true,//是否同步,默认异步
	        dataType:dataType,
	        error: function(request) {
	        	
	        },
	        success: function(data) {
	        	self.processResult(data, options);
	        }
		})
	}
    
    AreaSelect.DEFAULTS = {
    		divDom: false,  //当前操作的div节点
    		isCountry : false,
    		country:'areaCountry', //国家
    		region: 'areaRegion', //地区下拉框名称和id
    		province: 'areaProvince', //省下拉框名称和id
    		city: 'areaCity', //市下拉框名称和id
    		area: 'areaArea', //县下拉框名称和id
    		regionName: '',  //地区名称
    		provinceName: '', //省份名称
    		cityName: '', //市区名称
    		areaAreaName:'', //县级名称
    		areaCode: false, //需要将当前选中的地区的编码值赋值给的dom对象
    		areaName: false, //需要将当前选中的地区的名称赋值给的dom对象
    		address: '', //选择的详细的地址信息 regionName + provinceName + cityName + areaAreaName
    		url: '/logistics/area/query',
    		dataType:'json',
    		//defaultCode: false, //设置默认值
    		isValidForm: true, //是否需要用validForm控件进行表单验证,默认可以
    		isCleanDefault: false,
    		processResult: AreaSelect.processResult,
    		municipalities: ['上海市','北京市','天津市','重庆市'], //省级直辖市
    		administrativeArea: ['台湾', '港澳'],
    		createContainer: function (options) {
    			var container = '';
    			if(this.isValidForm && options.isShowNullMsg){
    				container = $(this.divDom).append([
    				                       	            "<select  class='area-select' style='display:none;'  name=\"" + options.name + "\" nullmsg=\"" + options.msg +"\" datatype='*'>",
    				                       	            "</select>"
    				                       	            ].join(""));
    			}else{
    				container = $(this.divDom).append([
    				                       	            "<select  class='area-select' style='display:none;'  name=\"" + options.name + "\"> ",
    				                       	            "</select>"
    				                       	            ].join(""));
    			}
    			
    	        return container;
    	    }
    };
	
	
    $.fn.areaSelect = function(option){
    	var value;
    	
    	//参数自定义，默认参数值设置
    	this.each(function(){
    		var $this = $(this),
    			options = $.extend({}, AreaSelect.DEFAULTS, option, {
    				divDom: $this
    			});
    			value = new AreaSelect(options);
    	});
    	
    	return typeof value === 'undefined' ? this : value;
    };
    
})(jQuery);
