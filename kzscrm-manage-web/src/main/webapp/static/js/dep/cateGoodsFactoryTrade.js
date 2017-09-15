/**
 * 品种，牌号，工厂级联功能
 * lcgf 全称:loadCateGoodsFactory
 */
(function(lcgf){
	
	var LoadCateGoodsFactory = function(doms, opts){
		
		var args = lcgf.extend(LoadCateGoodsFactory.DEFAULTS, opts);
		
		this.options = args;
		
		this.initParams(args);
		
		this.cateSelect2(args);
		
		this.goodsSelect2(args);
		
		this.factorySelect2(args);
		
		
		
	}
	
	/**
	 * 初始化参数
	 */
	LoadCateGoodsFactory.prototype.initParams = function(){
				
		var args = this.options;
		
		if(typeof(args.cateSelectDom) == 'string' && args.cateSelectDom.indexOf(",")!=-1){
			args.cateSelectDomList = args.cateSelectDom.split(",");
		}else{
			args.cateSelectDomList = [args.cateSelectDom];
		}
		
		//品种名称输入框处理
		if(typeof(args.cateNameDom) == 'string' && args.cateNameDom.indexOf(",")!=-1){
			args.cateNameDomList = args.cateNameDom.split(",");
		}else{
			args.cateNameDomList = [args.cateNameDom];
		}
		
		//牌号id输入框处理
		if(typeof(args.goodsSelectDom) == 'string' && args.goodsSelectDom.indexOf(",")!=-1){
			args.goodsSelectDomList = args.goodsSelectDom.split(",");
		}else{
			args.goodsSelectDomList = [args.goodsSelectDom];
		}
		
		//牌号名称输入框处理
		if(typeof(args.goodsNameDom) == 'string' && args.goodsNameDom.indexOf(",")!=-1){
			args.goodsNameDomList = args.goodsNameDom.split(",");
		}else{
			args.goodsNameDomList = [args.goodsNameDom];
		}
	},
	
	/**
	 * 品种select2功能
	 */
	LoadCateGoodsFactory.prototype.cateSelect2 = function(){
		var args = this.options;
		$.each(args.cateSelectDomList, function(index, cateSelect){
			//让每一个品种框都具有select2功能
			$(args.cateSelectDomList[index]).val('');
			$(args.cateSelectDomList[index]).select2({
				placeholder: args.defaultCateName || '请选择品类/品名',  //默认显示的数据
				allowClear: true,
				formatNoMatches: function () { return "没有数据"; },
				formatSearching: function () { return "查询中..."; },
				formatResult: function(result, container, query, escapeMarkup){ //返回结果时做的操作
					$(args.cateSelectDomList[index]).blur();
					return result.cateName;
				},
				formatSelection: function(item){//选择下拉框值时做的操作
					$(args.cateSelectDomList[index]).val(item.id); 
					if(args.cateNameDomList.length > 0){
						$(args.cateNameDomList[index]).val(item.cateName == '全部' ? '' : item.cateName); 
					}
					//品种改变，牌号跟着改变
					args.defaultCateId =item.id;
					/*if(args.goodsNameDomList.length > 0){
						$(args.goodsNameDomList[index]).val("");
					}*/
					$(args.goodsSelectDomList[index]).val("");
					$(args.goodsSelectDomList[index]).siblings(".select2-container").children("a").children(".select2-chosen").text("");
					
					if(args.factorySelectDom){
						$(args.factorySelectDom).val("");
						$(args.factorySelectDom).siblings(".select2-container").children("a").children(".select2-chosen").text("");
					}
					
					/*if($(args.factoryInputDom)){
						$(args.factoryInputDom).val('');
						$(args.factoryInputDom).focus();
						$(args.factoryInputDom).blur();
					}*/
					
					$(args.cateSelectDomList[index]).blur();
					return item.cateName; //当前的输入框赋值
				},
				ajax:{
					url:"/GoodsCategory.do?action=queryTrade",
					dataType: 'json',
					data: function(params, pageNo){
						//请求时，加入的参数
						return{
							parentId:cateParentId,
							cateName: params,
							pageSize: 10,
							unNeedIndustryId : args.unNeedIndustryId,
							industryId : WEBSITE_COMMON_OBJECT.industryId,
							t: (new Date()).valueOf()
						}
					},
					results: function(data, page, query){
						if(data.rows){
							if(query){
								if(!query.term){
									data.rows.splice(0,0,{cateName: query.term || '全部', id:''});
								}
							}
							//请求后返回的结果
							return{
								results: data.rows
							};
						}else{
							return{
								results: {cateName: query.term || '全部', id:''}
							};
						}
					}
				}
			});
			$(args.cateSelectDomList[index]).val(args.defaultCateId);
			if($(args.cateNameDomList[index])){
				$(args.cateNameDomList[index]).val(args.defaultCateName);
			}
		});
	},
	
	/**
	 * 牌号select2功能
	 */
	LoadCateGoodsFactory.prototype.goodsSelect2 = function(){
		var args = this.options;
		var pageSize = 10;
		if (args.unNeedIndustryId == 0) {
			pageSize = 200;
		}
		$.each(args.goodsSelectDomList, function(index, goodsSelectDom){
			$(args.goodsSelectDomList[index]).val('');
			//让每一个牌号都具有select2功能
			$(args.goodsSelectDomList[index]).select2({
				placeholder: args.defaultGoodsName || '请选择牌号',  //默认显示的数据
				allowClear: true,
				formatNoMatches: function () { return "没有数据"; },
				formatSearching: function () { return "查询中..."; },
				formatResult: function(result, container, query, escapeMarkup){ //返回结果时做的操作
					$(args.goodsSelectDomList[index]).blur();
					return result.goodsName;
				},
				formatSelection: function(item){//选择下拉框值时做的操作
					$(args.goodsSelectDomList[index]).val(item.goodsId); 
					if(args.goodsNameDomList.length > 0){
						$(args.goodsNameDomList[index]).val(item.goodsName == '全部' ? '' : item.goodsName); 
						$(args.goodsNameDomList[index]).blur();
					}
					$(args.goodsSelectDomList[index]).blur();
					
					args.defaultGoodsId = item.id;
					
					if(args.factorySelectDom){
						$(args.factorySelectDom).val("");
						$(args.factorySelectDom).siblings(".select2-container").children("a").children(".select2-chosen").text("");
					}
					
					return item.goodsName; //当前的输入框赋值
				},
				ajax:{
					url:"/GoodsList.do?action=query",
					dataType: 'json',
					data: function(params, pageNo){
						//请求时，加入的参数
						return{
							cateId: args.defaultCateId,
							goodsName: params,
							pageSize: pageSize,
							unNeedIndustryId : args.unNeedIndustryId,
							industryId : WEBSITE_COMMON_OBJECT.industryId,
							t: (new Date()).valueOf()
						}
					},
					results: function(data, page, query){
						if(data.rows){
							if(query){
								if(!query.term){
									data.rows.splice(0,0,{goodsName: query.term || '全部', id:''});
								}
							}
							//请求后返回的结果
							return{
								results: data.rows
							};
						}else{
							return{
								results: {goodsName: query.term || '全部', id:''}
							};
						}
					}
				}
			});
			if($(args.goodsNameDomList[index])){
				$(args.goodsNameDomList[index]).val(args.defaultGoodsName);
			}
			$(args.goodsSelectDomList[index]).val(args.defaultGoodsId);
		});
		
	},
	
	/**
	 * 工厂select2功能
	 */
	LoadCateGoodsFactory.prototype.factorySelect2 = function(){
		var args = this.options;
		if(!args.factorySelectDom){
			return;
		}
		$(args.factorySelectDom).val('');
		//让每一个牌号都具有select2功能
		$(args.factorySelectDom).select2({
			placeholder: args.defaultFactory || '请选择厂家',  //默认显示的数据
			allowClear: true,
			formatNoMatches: function () { return "没有数据"; },
			formatSearching: function () { return "查询中..."; },
			formatResult: function(result, container, query, escapeMarkup){ //返回结果时做的操作
				$(args.factorySelectDom).blur();
				return result.factory;
			},
			formatSelection: function(item){//选择下拉框值时做的操作
				$(args.factorySelectDom).val(item.factory); 
				if($(args.factoryInputDom)){
					$(args.factoryInputDom).val(item.factory);
					$(args.factoryInputDom).focus();
					$(args.factoryInputDom).blur();
				}
				return item.factory; //当前的输入框赋值
			},
			ajax:{
				url:"/GoodsTechSummary.do?action=queryFactory",
				dataType: 'json',
				data: function(params, pageNo){
					//请求时，加入的参数
					return{
						goodsId: args.defaultGoodsId,
						factory: params,
						pageSize: 10,
						t: (new Date()).valueOf()
					}
				},
				results: function(data, page, query){
					if(data.rows){
						//请求后返回的结果
						$.each(data.rows, function(i, row){
							row.id = 0;
						});
						return{
							results: data.rows
						};
					}
				}
			}
		});
		
		$(args.factorySelectDom).val(args.defaultFactory);
		if($(args.factoryInputDom)){
			$(args.factoryInputDom).val(args.defaultFactory);
		}
	}
			
			
		
	
	/**
	 * 默认参数
	 */
	LoadCateGoodsFactory.DEFAULTS = {
			async: true, //是否是异步加载数据
			cateSelectDom: null, //品类对应的select选择框 把获取到的数据注入对应的input框内
			cateNameDom: null, //品种名称select输入框
			goodsSelectDom: null, //牌号id对应的select输入框
			goodsNameDom: null, // 牌号对应的input输入框
			factorySelectDom: null, //厂家对应的select输入框
			factoryInputDom: null, //厂家对应的文本输入框
			
			//=============默认值设置======================================
			defaultCateId: null, //默认选中的品种
			defaultGoodsId: null, //默认显示的品牌id
			defaultGoodsName: null, //默认显示的牌号
			defaultCateName: null, //默认显示的品种
			defaultFactory: null,
			unNeedIndustryId : null,//默认需要行业ID 值为1时 后台查询全部行业的分类与牌号 只在聚贸平台下作用
			//=============================================================
			cateSelectDomList: [],
			cateNameDomList: [],
			goodsSelectDomList: [],
			goodsNameDomList: []
			
	}
	
	/**
	 * 控制调用入口
	 */
	lcgf.fn.loadCateGoodsFactoryTrade = function(opts){
		return new LoadCateGoodsFactory(this, opts);
	}
	
	
})(jQuery);

//品类
var cateParentId = '';

/**
 * 切换品类
 */
function updateQueryCateParentId(){
	cateParentId = $("#cateParentId").val();
	
	$("#cateId").loadCateGoodsFactoryTrade({
		cateSelectDom: '#cateId', //品类对应的select选择框 把获取到的数据注入对应的input框内
		goodsSelectDom: '#goodsId', //牌号对应的input输入框
		goodsNameDom: '#mainForm input[name=goodsName]', // 牌号id对应的input输入框
	    factorySelectDom: '#mainForm input[name=factoryName]', //厂家对应的select2输入框
	    factoryInputDom: '#mainForm input[name=factory]', //厂家名称文本输入框
	    defaultCateId: null, //默认选中的品种
		defaultGoodsId: null, //默认显示的品牌id
		defaultGoodsName: null, //默认显示的牌号
		defaultCateName: null, //默认显示的品种
		defaultFactory: null
	});
}