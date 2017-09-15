/**
 * 统计页面停留时间
 * Author：zhengbin
 * Email：zhengbin@jumore.com
 * Date：2016.1.30
 */

// 起始时间
var startTime = '';

getResidenceTimeJsonp(5000);

/**
 * 获取页面停留时间
 * @param time 毫秒 设置请求时间间隔，默认5秒
 * url origin：前端：http://qd-msl.jm.com 测试：http://www.jm.com 正式：http://www.maisulang.com
 */
function getResidenceTimeJsonp(time) {
    if(!$.ajax) {
        return false;
    }

    $.ajax({
        url: 'http://www.maisulang.com/basemetadata.do?action=residenceTimeJsonp',
        dataType: 'jsonp',
        jsonp:"callbackparam",
        jsonpCallback:"setResidenceTime",
        data: {
            startTime: startTime,
            REFRERE_SOURCE: getCookie('REFRERE_SOURCE') || ''
        },
        success: function(data) {
            startTime = startTime || data[0].startTime || '';
        }
    });
    // 递归调用
    setTimeout(function(){
        getResidenceTimeJsonp(time);
    }, time || 5000);
}

/**
 * 获取cookie
 * @param name
 * @returns {*}
 */
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)"); //正则匹配
    if (arr = document.cookie.match(reg)) {
        return decodeURIComponent(arr[2]);
    } else {
        return null;
    }
}