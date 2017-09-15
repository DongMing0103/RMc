/**
 * 
 */
package com.hd.kzscrm.manager.controller.area;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.service.param.area.BaseAreaParam;
import com.hd.kzscrm.service.serviceInter.area.IBaseAreaService;
import com.hd.kzscrm.service.vo.area.BaseAreaVO;

/**
 * @author 黄霄仪
 * @date 2017年7月27日 下午2:15:49
 * 
 */
@Controller
@RequestMapping("/baseArea")
public class BaseAreaController {
	@Resource
	private IBaseAreaService iBaseAreaService;
	/**
	 * 
	 */
	public BaseAreaController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping("/getBaseArea")
	@ResponseBody
	public RespModel getBaseArea(@RequestBody BaseAreaParam baseAreaParam){
		RespModel respModel=RespModel.success("成功");
		respModel.setRows(BeanConvertor.copyList(iBaseAreaService.listByParam(baseAreaParam),BaseAreaVO.class));
		return respModel;
	}
}
