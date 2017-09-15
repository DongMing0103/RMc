/**
 * 仓库地区级联功能
 * @author YongW
 * @date 2015-04-17
 */
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
	
	var $areaSelect = null;
    // ======================

    var HouseAreaSelect = function (options) {
        this.options = options;
        this.init();
    };
    
    /**
     * 初始化做的工作
     */
    HouseAreaSelect.prototype.init = function () {
    	this.initArea();
    	this.initHouse();
    };
    
    /**
     * 初始化地区
     */
    HouseAreaSelect.prototype.initArea = function(){
    	var option = this.options;
    	$areaSelect = $(option.divDom).areaSelect(option);
    }
    
    /**
     * 初始化仓库
     */
    HouseAreaSelect.prototype.initHouse = function(){
    	var option = this.options;
    	this.loadHouseData();
    	$areaSelect.options.defaultHouseName = false;
    	$areaSelect.options.defaultHouseId = false;
    }
    
    /**
     * 加载仓库数据
     */
    HouseAreaSelect.prototype.loadHouseData = function(){
    	$areaSelect.loadHouseData(this.options);
    }
    
    HouseAreaSelect.DEFAULTS = {
    		divDom: false,  //当前操作的div节点
    		country: false, //国家
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
    		houseDom: false, //需要将当前仓库的select下拉框赋值的dom对象
    		houseInputDom: false, //需要将当前仓库的input赋值的dom对象
    		areChangeIsLoadHouse: true, //地区修改时是否加载仓库
    		address: '', //选择的详细的地址信息 regionName + provinceName + cityName + areaAreaName
    		isValidForm: true, //是否需要用validForm控件进行表单验证,默认可以
    		isCleanDefault: false,
    		defaultCode: false,
    		defaultHouseId: false,
    		defaultHouseName: false,
    		unNeedIndustryId : null,//默认需要行业ID 值为1时 后台查询全部行业的分类与牌号 只在聚贸平台下作用
    };
	
	
    $.fn.houseAreaSelect = function(option){
    	var value;
    	
    	//参数自定义，默认参数值设置
    	this.each(function(){
    		var $this = $(this),
    			options = $.extend({}, HouseAreaSelect.DEFAULTS, option, {
    				divDom: $this
    			});
    			value = new HouseAreaSelect(options);
    	});
    	
    	return typeof value === 'undefined' ? this : value;
    };
    
})(jQuery);
