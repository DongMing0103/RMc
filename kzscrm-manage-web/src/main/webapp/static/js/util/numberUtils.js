/**
 * @author 黄霄仪
 * @date 2017年5月31日
 */

var numberUtils={};
/**
 * 格式化金额
 * @param amount 金额
 * @param scale 精确位数 
 * @param isRound 是否四舍五入 暂未实现
 * @return 返回精确到小数指定位数的数值
 */
numberUtils.formatCurrency=function(amount,scale,isRound){
	if(typeof amount=='string'){
		if(!isNaN(amount)){
			amount=parseFloat(amount);
		}else{
			return null;
		}
	}
	return amount.toFixed(scale);
}