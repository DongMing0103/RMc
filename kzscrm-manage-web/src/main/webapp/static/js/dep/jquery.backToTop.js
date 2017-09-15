(function($){
	jQuery.backToTop = function(options){
		var defaults = {
			text: '↑',
			time: 120
		},
		settings = $.extend(defaults, options);
		var backToTopEle = $("<div class='backToTop'></div>").appendTo($("body"))
					.text(settings.text).click(function(){
						$("html, body").animate({scrollTop:0}, settings.time);
					});
		var backToTopFun = function(){
			var st = $(document).scrollTop(),winH = $(window).height();
			(st > 0) ? backToTopEle.show() : backToTopEle.hide();
			 //for ie6
			 if (!window.XMLHttpRequest) {
				 backToTopEle.css("top", st + winh - 166);    
			}
		}
		$(window).bind("scroll", backToTopFun);
	    $(function() { backToTopFun();});
	};
})(jQuery);