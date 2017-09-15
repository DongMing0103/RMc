var PAGER = {
	currentPage: 1,  //当前页
	totalPages: 0,  //总页数
	pageNums: 10,  //显示的页码数
	total:0,   //总数
	pageNumArr: new Array(),  //页码数组
	pager: null,  //显示分页的html对象
	callbackFun: null,  //回调函数, 入参: 当前页码
	isCallBackFun: true,

	/**
	 * 初始化分页参数
	 * currentPage: 当前页码
	 * total: 总页数
	 * pageSize: 每页显示条目数
	 * obj: 显示分页的html对象id
	 * callbackFun: 分页回调函数
	 */
	initPager: function(currentPage, total, pageSize, pageNums, obj, callbackFun) {
		if(total%pageSize==0) {
			this.totalPages = total/pageSize;
		} else {
			this.totalPages = total/pageSize + 1;
		}
		this.total = total;
		this.totalPages = parseInt(this.totalPages);
		this.currentPage = currentPage;
		this.pageNums = pageNums;
		this.pager = document.getElementById(obj);
		this.callbackFun = callbackFun;
	
		this.renderPager();
	},
	
	/**
	 * 初始化分页参数
	 * currentPage: 当前页码
	 * total: 总页数
	 * pageSize: 每页显示条目数
	 * pageNums: 显示的页码数
	 * callbackFun: 分页回调函数
	 */
	changePage: function(total, pageSize){
		if(total% pageSize==0) {
			this.totalPages = total/pageSize;
		} else {
			this.totalPages = total/pageSize + 1;
		}
		
		this.total = total;
		this.totalPages = parseInt(this.totalPages);
		this.isCallBackFun = false;
		this.renderPager();
	},
	
	/**
	 * 执行回调函数
	 */
	doCallBack: function() {
		if(this.callbackFun) {
			this.callbackFun(this.currentPage);
		}
	},

	/**
	 * 跳转到首页
	 */
	gotoFirstPage: function() {
		this.currentPage = 1;
		this.renderPager();
	},

	/**
	 * 跳转到尾页
	 */
	gotoLastPage: function() {
		this.currentPage = this.totalPages;
		this.renderPager();
	},

	/**
	 * 跳转到上一页
	 */
	gotoPrePage: function() {
		if (this.currentPage>1) {
			this.currentPage --;
			this.renderPager();
		}
	},

	/**
	 * 跳转到下一页
	 */
	gotoNextPage: function() {
		//if(this.currentPage<this.pageNumArr[this.pageNumArr.length-1]) {
		if(this.currentPage<this.totalPages) {
			this.currentPage++;
			this.renderPager();
		}
	},

	/**
	 * 跳转到指定页
	 */
	gotoPage: function(targetPage) {
		if(targetPage<1) {
			targetPage = 1;
		} else if(targetPage>this.totalPages) {
			targetPage = this.totalPages;
		}
		
		this.currentPage = targetPage;
		this.renderPager();
	},

	/**
	 * 渲染分页数据
	 */
	renderPager: function() {
		//alert(this.currentPage);
		var begin = 1;
		this.pageNumArr = new Array();
	
		var midPage = Math.floor(this.pageNums/2);
		if(this.totalPages>this.pageNums) {
			if (this.currentPage>midPage) {
				begin = this.currentPage - midPage;
			} 
			if (this.totalPages-this.currentPage<midPage) {
				begin = this.totalPages - (this.pageNums-1);
			}
		}
	
		for(var i=begin; i<=this.totalPages&&i<this.pageNums+begin; i++) {
			this.pageNumArr.push(i);
		}
	
		var pageStr = "";
		var totalStr = "<div class='fn-left'>共有[" + this.total + "]条数据,共[" + this.totalPages + "]页</div>";
		var preStr = "<ul><li id='prePage' onclick='PAGER.gotoPrePage()'>上一页</li><li id='firstPage' onclick='PAGER.gotoFirstPage()'>首页</li>";
		var nextStr = "<li id='lastPage' onclick='PAGER.gotoLastPage()'>尾页</li><li id='nextPage' onclick='PAGER.gotoNextPage()'>下一页</li></ul>";
		var cenStr = "";
		for(var i=0; i<this.pageNumArr.length; i++) {
			if (this.currentPage==this.pageNumArr[i]) {
				cenStr += "<li class='curLi' onclick='PAGER.gotoPage(" + this.pageNumArr[i] + ")'>" + this.pageNumArr[i] + "</li>";
			} else {
				cenStr += "<li onclick='PAGER.gotoPage(" + this.pageNumArr[i] + ")'>" + this.pageNumArr[i] + "</li>";
			}
		}
		pageStr =totalStr + preStr + cenStr + nextStr;
		this.pager.innerHTML = pageStr;
		
		if(this.isCallBackFun){
			this.doCallBack();
		}
		this.isCallBackFun = true;
	}

}

