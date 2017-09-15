/**
 * Created by Iceli on 2015/8/19.
 */

;(function ($, undefined) {
    var Area = function (options) {
        this.options = $.extend(true, {}, Area.defaults, options);
        this.$el = $(this.options.target);
        if (!this.$el.length) return;
        this.init();
    };

    Area.defaults = {
        target: '.J_area',
        url: '/Area.do?action=query&isOpenJyt=1',
        value: '',
        width: '',
        theme: 'default',
        showClass: 'onShow',
        houseDom: '#houseId',
        houseInputDom: '#mainForm input[name=houseName]',
        defaultHouseName: '',
        defaultHouseId: '',
        defaultCode: '',
        placeholder: '请选择',
        inputPlaceholder: '请输入仓库名称',
        municipalities: ['上海市', '北京市', '天津市', '重庆市'], //省级直辖市
        administrativeArea: ['台湾', '港澳'],
        houseText: '仓库',
        loadingText: '正在加载...',
        noDataText: '暂无数据',
        disabled: false,
        showHouse: true, //仓库控件：true, 地区控件：false
        data: {
        	areaLevel: 1
    	},
        isFirst: true,
        afterChange: function () {
            return false;
        }
    };

    Area.prototype = {
        constructor: Area,

        init: function () {
            this.render();
            this.initAttr();
            this.bindEvents();

            this.$tabNav.eq(this.options.data.areaLevel - 1 || 0).addClass('current').show().removeClass('fn-hide').siblings().removeClass('current').end().nextAll().hide();
            if(this.options.showHouse) {
                this.$tabNav.filter('.ck').show();
            }
            this.$tabCon.eq(0).html('<li>'+ this.options.loadingText +'</li>').show().siblings().hide();
            //加载对地区数据,并赋值到地区下拉框中
            this.ajax(this.options);
        },

        render: function () {
            this.$originalElement = this.$el;

            if (this.$originalElement.next().is('.area-select[data-role="area-select"]')) {
                this.$el = this.$originalElement.next();
                return false;
            }

            this.$el = $('<div class="area-select" data-role="area-select">' +
                '<p class="area-select-view ' + (this.options.defaultHouseName ? this.options.defaultHouseName : this.options.placeholder ? 'placeholder' : '') + '">' +
                (this.options.defaultHouseName ? this.options.defaultHouseName : (this.options.placeholder + this.options.houseText)) + '</p>' +
                '<div class="area-select-content fn-hide"><div class="tabs">' +
                '<ul class="tab-nav">' +
                '<li class="fn-hide"><a href="#"></a></li>' +
                '<li class="fn-hide"><a href="#"></a></li>' +
                '<li class="fn-hide"><a href="#"></a></li>' +
                '<li class="fn-hide"><a href="#"></a></li>' +
                '<li class="ck"><a href="#">'+ this.options.houseText +'</a></li>' +
                '</ul>' +
                '<div class="tab-content">' +
                '<ul class="area-select-list fn-hide"></ul>' +
                '<ul class="area-select-list fn-hide"></ul>' +
                '<ul class="area-select-list fn-hide"></ul>' +
                '<ul class="area-select-list fn-hide"></ul>' +
                '<ul class="ck area-select-list fn-hide"></ul>' +
                '</div>' +
                '</div><input type="text" value="" placeholder="'+ this.options.inputPlaceholder +'" class="J_houseName" /> </div>' +
                '</div>');
            this.$originalElement.after(this.$el).hide();
        },

        // 设置属性、设置默认值
        initAttr: function () {
            this.uuid = "sel" + (+new Date);
            this.$el.attr("data-selid", this.uuid).width(this.options.width || this.$originalElement.outerWidth()); // .addClass(this.$originalElement[0].className);
            this.$selectView = this.$el.find('.area-select-view').html(this.options.defaultHouseName ? this.options.defaultHouseName : this.options.placeholder);
            this.$selectCon = this.$el.find('.area-select-content');
            this.$tabNav = this.$el.find('.tab-nav li');
            this.$tabCon = this.$el.find('.tab-content .area-select-list');
            this.$houseName = this.$el.find('.J_houseName').hide();
        },

        bindEvents: function () {
            var that = this,
                specialArea = that.options.municipalities.concat(that.options.administrativeArea).join(',');

            this.$houseName.off('change').on('change', function(){
                var val = $.trim($(this).val());

                if(!val) {
                    return false;
                }

                that.$selectView.text(val).attr('title', val);
                that.$originalElement.val(val);
                $(that.options.houseDom).val('');
            });

            this.$selectView.off('click').on('click', function (e) {
                e.preventDefault();

                $('.area-select-content').not(that.$selectCon).hide().parents('.area-select').removeClass('hover');
                
                if(that.$el.hasClass('hover')) {
                    that.hide();
                } else {
                    that.$el.addClass('hover');
                    that.$selectCon.show();
                }
            });

            this.$tabNav.off('click').on('click', function (e) {
                e.preventDefault();
                var me = $(this),
                    index = me.index();

                me.addClass('current').siblings().removeClass('current');
                that.$tabCon.eq(index).show().siblings().hide();
                that.$houseName.hide();
                // TODO
                if (me.is('.ck')) {
                    that.getHouse(that.options.areaCode);
                    that.$houseName.show();
                }
            });

            this.$tabCon.off('click').on('click', 'li > a', function (e, isFirst) {
                e.preventDefault();
                var me = $(this),
                    areaCode = me.attr('data-value'),// .ck 时为 houseId
                    areaName = me.text(),
                    index = me.parents('.area-select-list').index();

                me.addClass('selected').parent().siblings().find('a').removeClass('selected');

                if (me.parents('.ck').length) {
                    $(that.options.houseDom).val(areaCode);
                    that.$selectView.text(areaName).attr('title', areaName);
                    that.$originalElement.val(areaName);
                    that.$houseName.val('');
                    that.options.afterChange();
                    that.hide();
                    return false;
                }

                that.$tabNav.eq(index).find('a').html(areaName).attr('title', areaName).end().nextAll().not('.ck').hide().addClass('fn-hide');
                that.options.isFirst = isFirst || false;
                that.options.areaCode = areaCode;
                if (index != 3) {
                    //加载地区数据的请求参数
                    that.options = $.extend(true, {}, that.options, {
                        data: {
                            areaLevel: specialArea.indexOf(areaName) > -1 ? 4 : index + 2,
                            parentAreaCode: areaCode
                        }
                    });
                    //加载对地区数据,并赋值到地区下拉框中
                    that.ajax(that.options);
                    that.options.showHouse && isFirst && that.getHouse(areaCode);
                    that.options.showHouse && isFirst && that.showHouseWrap();
                } else {
                    that.options.showHouse && that.getHouse(areaCode);
                    that.options.showHouse && that.showHouseWrap();
                    !that.options.showHouse && that.hide();
                }

                if(!that.options.showHouse) {
                    if(index==3){
                        var s = that.$tabNav.eq(index).prevAll().map(function(i, item){
                            return $(item).text();
                        }).get().reverse().join('')
                        areaNames=s+areaName;
                    }else{
                        areaNames = that.$tabNav.eq(index).prevAll(isFirst ? '' : ':visible').map(function(i, item){
                                return $(item).text();
                            }).get().reverse().join('')+areaName;
                    }
                    that.$selectView.text(areaNames).attr('title', areaNames);
                    that.$originalElement.val(areaName);
                }
                // 更新地区值
                $(that.options.userDeliveryaddrIdDom).val(areaCode);
            });
        },

        ajax: function (options) {
            var that = this,
                index = options.data.areaLevel - 1,
                url = options.url,
                data = options.data || {},
                dataType = options.dataType || 'json';

            data.pageSize = 100;
            that.$tabNav.eq(index).addClass('current').removeClass('fn-hide').html('<a href="#">'+ that.options.placeholder +'</a>').show().siblings().removeClass('current');
            that.$tabCon.eq(index).html('<li>'+ that.options.loadingText +'</li>').show().siblings().hide();
        	
            $.ajax({
                cache: false,
                type: "POST",
                url: url,
                data: data,
                async: true,//是否同步,默认异步
                dataType: dataType,
                error: function (request) {

                },
                success: function (data) {
                    that.processResult(data, options);
                }
            })
        },

        processResult: function (data, options) {
            var that = this,
                tmp = '',
                $tabCon = that.$tabCon.eq(options.data.areaLevel - 1);

            if (data.total && data.total > 0) {
                var results = data.rows;
                if (options.defaultCode) {
                    options.defaultCode = options.defaultCode + '';
                }
                $.each(results, function (index, area) {
                    tmp += '<li><a href="#" data-value="' + area.areaCode + '" title="' + area.areaName + '" ' +
                        (options.isFirst && options.defaultCode && options.defaultCode.indexOf(area.areaCode) >= 0 ? 'class="selected"' : '') +
                        '>' + area.areaName + '</a></li>';
                });
            } else {
                tmp += '<li>'+ that.options.noDataText +'</li>';
            }

            $tabCon.html(tmp);
            $tabCon.find('.selected').length && $tabCon.find('.selected').trigger('click', options.isFirst);
        },

        getHouse: function (areaCode) {
            var that = this,
                pageSize = 10;

            if (that.options.unNeedIndustryId == 0) {
                pageSize = 200;
            }

            $.ajax({
                url: "/GoodsStockHouse.do?action=query",
                dataType: 'json',
                cache: false,
                data: {
                    areaCode: areaCode || '',
                    pageSize: pageSize,
                    //默认需要行业ID 值为1时 后台查询全部行业的分类与牌号 只在聚贸平台下作用
                    unNeedIndustryId: that.options.unNeedIndustryId,
                    industryId: WEBSITE_COMMON_OBJECT.industryId
                },
                error: function (request) {

                },
                success: function (data) {
                    var tmp = '',
                        $tabCon = that.$tabCon.last();

                    if (data.total && data.total > 0) {
                        var results = data.rows;
                        if (that.options.defaultHouseId) {
                            that.options.defaultHouseId = that.options.defaultHouseId + '';
                        }
                        $.each(results, function (index, house) {
                            tmp += '<li><a href="#" data-value="' + house.id + '" title="' + house.houseName + '" ' +
                                (that.options.isFirst && that.options.defaultHouseId && that.options.defaultHouseId == house.id ? 'class="selected"' : '') +
                                '>' + house.houseName + '</a></li>';
                        });
                    } else {
                        if (that.options.isFirst) {
                            tmp = '<li><a href="#" data-value="' + that.options.defaultHouseId + '" title="' + that.options.defaultHouseName + '" ' +
                                'class="selected" >' + (that.options.defaultHouseName || that.options.noDataText) + '</li>';
                        } else {
                            tmp = '<li>'+ that.options.noDataText +'</li>';
                        }
                        that.options.isFirst && that.$houseName.val(that.options.defaultHouseName);
                    }

                    $tabCon.html(tmp);
                    that.$houseName.show();
                }
            });
        },

        showHouseWrap: function () {
            var that = this;

            that.$tabNav.removeClass('current').last().addClass('current').removeClass('fn-hide').show();
            that.$tabCon.hide().last().show();
            that.$houseName.show();
        },

        hide: function () {
            var that = this;

            that.$selectCon.fadeOut(0, function () {
                that.$el.removeClass('hover');
            });
        }
    };
    var areaNames ='';
    $(document).off('click.area-select').on('click.area-select', function(e){
        var target = e.target || e.srcElement;

        if(!$(target).parents('.area-select[data-role="area-select"]').length) {
            $('.area-select[data-role="area-select"]').removeClass('hover').find('.area-select-content').hide();
        }
    });

    window.Area = Area;
})(jQuery);