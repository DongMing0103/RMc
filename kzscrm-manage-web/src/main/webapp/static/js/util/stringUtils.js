/**
 * @author 黄霄仪
 * @date 2017年5月26日
 */
//申请命名空间
var stringUtils={};
/**
 *向左填充字符 
 *@param str 被填充的字符
 *@param size 指定大小之内才允许被填充
 *@param padChar 填充的字符 
 *@return 返回被填充后的字符串
 */
stringUtils.leftPad=function(str,size,padChar){
	str+="";//转换成字符串
	if(str.length>=size){
		return str;
	}
	for(var i=0;i<=size-str.length;i++){
		str=padChar+str;
	}
	return str;
	
}
/**
 *向右填充字符
 *@param str 被填充的字符
 *@param size 指定大小之内才允许被填充
 *@param padChar 填充的字符 
 *@return 返回被填充后的字符串
 */
stringUtils.rightPad=function(str,size,padChar){
	str+="";//转换成字符串
	if(str.length>=size){
		return str;
	}
	for(var i=0;i<=size-str.length;i++){
		str+=padChar;
	}
	return str;
	
}