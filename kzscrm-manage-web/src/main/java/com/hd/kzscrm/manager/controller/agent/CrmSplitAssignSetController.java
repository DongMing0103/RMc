package com.hd.kzscrm.manager.controller.agent;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.dao.entity.agent.CrmSplitAssignSetPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessOrderPO;
import com.hd.kzscrm.dao.entity.business.OrderPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenApplyPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.agent.CrmSplitAssignSetParam;
import com.hd.kzscrm.service.param.canteen.CrmCanteenApplyParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmSplitAssignSetService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessOrderService;
import com.hd.kzscrm.service.serviceInter.business.ICrmOrderService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenApplyService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.user.ICrmUserService;
import com.hd.kzscrm.service.vo.agent.CrmSplitAssignSetVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;

/**
 * crmSplitAssignSet CRMSPLITASSIGNSET
 * 
 * @author system code gen
 *
 */
@Controller
@RequestMapping("/crmsplitassignset")
public class CrmSplitAssignSetController extends BaseController {
	protected static final Logger logger = LoggerFactory.getLogger(CrmSplitAssignSetController.class);

	@Autowired
	ICrmSplitAssignSetService crmSplitAssignSetService;

	@Autowired
	ICrmBusinessOrderService crmBusinessOrderService;
	/**
	 * 业务员跟踪记录表
	 */
	@Autowired
	ICrmBusinessOrderService businessOrderService;
	/**
	 * 食堂基本信息
	 */
	@Autowired
	ICrmCanteenBaseInfoService canteenBaseInfoService;
	@Autowired
	private ICrmOrderService orderService;
	// 用户表
	@Autowired
	ICrmUserService crmUserService;
	//食堂入驻申请表
	@Autowired
	ICrmCanteenApplyService crmCanteenApplyService;
	
	@Value("${img.view.address}")
    private String imgViewAddress;
	/**
	 ** 预留实现
	 */
	public void initControler(HttpServletRequest request, Map<String, Object> map) {

	}

	/**
	 *
	 * 初始化
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/init")
	@ResponseBody
	public ModelAndView init() {

		ModelAndView view = new ModelAndView("/my_wealth/my_wealth");
//		ParamMap param = new ParamMap();
		CrmSplitAssignSetParam param2 = new CrmSplitAssignSetParam();
		List<CrmSplitAssignSetPO> crmSplitPOs = crmSplitAssignSetService.findAll(param2);
		view.addObject("crmSplitPOs", crmSplitPOs);

		// 菜单
		view.addObject("active", "CrmSplitAssignSet");
		// 行业

		return view;
	}

	/**
	 * 新增
	 *
	 * @return
	 *
	 */
	@RequestMapping(value = "addCrmSplitAssignSet", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addCrmSplitAssignSet() {
		ModelAndView modelAndView = new ModelAndView("/crmsplitassignset/mainAddOrEditCrmSplitAssignSet");
		CrmSplitAssignSetParam CrmSplitAssignSetparam = new CrmSplitAssignSetParam();
		modelAndView.addObject("type", "add");
		return modelAndView;
	}

	/**
     * 进入查看详情页面
     * @return
     */
    @RequestMapping(value = "/editCrmSplitAssignSet")
    @ResponseBody
    public ModelAndView editCrmSplitAssignSet(HttpServletRequest request) {
    	String id1 = request.getParameter("id");
    	Long id = Long.parseLong(id1);
         ModelAndView modelAndView = new ModelAndView("/system_operate/order_fashionalbe_detail");
         ParamMap paramMap = new ParamMap();
         CrmSplitAssignSetParam assignSetParam = new CrmSplitAssignSetParam();
         assignSetParam.setId(id);
         List<CrmSplitAssignSetPO> po = crmSplitAssignSetService.findById(id);
         CrmSplitAssignSetPO  setPO = po.get(0);
 		 CrmSplitAssignSetVO cAssignSetVO = BeanConvertor.convertBean(setPO, CrmSplitAssignSetVO.class);
 		 OrderPO oPO = new  OrderPO();
 		 CrmUserPO cUserPO  = new CrmUserPO();;
 		 CrmCanteenBaseInfoPO canteenBaseInfoPO=new CrmCanteenBaseInfoPO();
         if(po!=null){
        	 Long businessId = setPO.getBusinessId();
			 CrmBusinessOrderPO cOrderPO= businessOrderService.findByBusinessId(businessId);//根据业务员id查询订单
			 if(BeanUtils.isNotEmpty(cOrderPO)){
				 	Long  orderId = cOrderPO.getOrderId();//订单表orderPO
				 	oPO = orderService.findById(orderId);
				 	if(BeanUtils.isNotEmpty(oPO) && BeanUtils.isNotEmpty(oPO.getUserId())){
				 		Long userId = oPO.getUserId();//获取用户资料
				 		cUserPO = crmUserService.getById(userId);
				 		
				 	}
				 	if(BeanUtils.isNotEmpty(oPO) && BeanUtils.isNotEmpty(oPO.getCanteenId())){//根据商家id查询对应信息
				 		canteenBaseInfoPO = canteenBaseInfoService.findByCanteenId(oPO.getCanteenId());
				 	}
				 	
				 	//供应类目和消费状态  去查order表  没有对应的实体类
					BigDecimal splitPercent = setPO.getBusinssSplitPercent();//获取业务员分账比例
					cAssignSetVO.setOrderNum(cOrderPO.getOrderNo());//获取订单id
					cAssignSetVO.setRealMoney(cOrderPO.getOrderRealMoney());//订单的总金额
					cAssignSetVO.setCreaterTime(cOrderPO.getCreateTime());//分账时间
					if(BeanUtils.isNotEmpty(cOrderPO.getOrderRealMoney())&& BeanUtils.isNotEmpty(splitPercent) && splitPercent.compareTo(BigDecimal.ZERO)>0){
						//BigDecimal的乘法计算
						cAssignSetVO.setSplitMoney(cOrderPO.getOrderRealMoney().multiply(splitPercent.divide(new BigDecimal(100))));
					}
				}
			 Long canteenId = setPO.getCanteenId();//获取食堂信息
			 CrmCanteenBaseInfoPO cpInfoPO =canteenBaseInfoService.findByCanteenId(canteenId); 
			 if(BeanUtils.isNotEmpty(cpInfoPO)){//查询食堂名称
					cAssignSetVO.setName(cpInfoPO.getName());
				//查询现金流水号
				/*if (BeanUtils.isNotEmpty(cpInfoPO)) {
				}*/
				
				}
			 
		
         }
         modelAndView.addObject("canteenBaseInfoPO",canteenBaseInfoPO);
         modelAndView.addObject("oPO", oPO);
         modelAndView.addObject("cUserPO", cUserPO);
         modelAndView.addObject("po", po);
         modelAndView.addObject("cAssignSetVO", cAssignSetVO);
         return modelAndView;
    }

	/**
	 * 删除
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(value = "doDelete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public RespModel delete(@PathVariable Long id) {
		RespModel res = new RespModel();
		try {
			crmSplitAssignSetService.deleteById(id);
		} catch (BizException e) {
			logger.error("delete", e);
		}
		return res;
	}

	@RequestMapping(value = "doSave")
	@ResponseBody
	public RespModel doSave(CrmSplitAssignSetParam param, HttpServletRequest request) {
		RespModel res = new RespModel();
		CrmSplitAssignSetPO po = BeanConvertor.convertBean(param, CrmSplitAssignSetPO.class);
		crmSplitAssignSetService.saveEntity(po);
		return res;
	}

	@RequestMapping(value = "doUpdate")
	@ResponseBody
	public RespModel doUpdate(CrmSplitAssignSetParam param, HttpServletRequest request) {
		RespModel res = new RespModel();
		CrmSplitAssignSetPO po = BeanConvertor.convertBean(param, CrmSplitAssignSetPO.class);
		crmSplitAssignSetService.updateEntity(po);
		return res;
	}

	/**
	 * 一览查询 页面详情展示
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public PageRespModel queryPage(CrmSplitAssignSetParam param) throws Exception {
		PageRespModel model = new PageRespModel();

		Page<CrmSplitAssignSetVO> pages = crmSplitAssignSetService.queryPage(param);
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);

		return model;
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "/viewInfo/{id}")
	@ResponseBody
	public ModelAndView viewInfo(CrmSplitAssignSetParam param, @PathVariable Long id) {
		ModelAndView view = new ModelAndView("CrmSplitAssignSet/CrmSplitAssignSet_Detail");
		// SplitAccountRolePO po = mainService.get(SplitAccountRolePO.class,
		// id);
		// if (po != null ) {
		// view.addObject("firstPO", po);
		// } else {
		// view.addObject("firstPo", new CrmSplitAssignSetPO());
		// return view;
		// }
		return view;
	}


	/**
	 * 
	 * @Title: adjustSplitPercentInit 
	 * @Description: 调整食堂分账比 页面初始化 
	 * @param @param clientId
	 * @param @return  
	 * @return ModelAndView    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月20日 下午8:20:21
	 */
	@RequestMapping(value="/adjustSplitPercentInit")
	public ModelAndView adjustSplitPercentInit(Long clientId){
		logger.info("#####CrmSplitAssignSetController###adjustSplitPercentInit##clientId:"+clientId);
		ModelAndView modelAndView = new ModelAndView("system_operate/adjust_permissions");
		//查询食堂入驻申请
		CrmCanteenApplyParam crmCanteenApplyParam = new CrmCanteenApplyParam();
		crmCanteenApplyParam.setClientId(clientId);
		crmCanteenApplyParam.setDelFlag(1);
		List<CrmCanteenApplyPO> crmCanteenApplyPOs = crmCanteenApplyService.commonQuery(crmCanteenApplyParam);//查询食堂入驻信息
		if(CollectionUtils.isEmpty(crmCanteenApplyPOs)){
			return modelAndView;
		}
		
		CrmCanteenApplyPO crmCanteenApplyPO = crmCanteenApplyPOs.get(0);
		Date enterTime = crmCanteenApplyPO.getEnterTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String enterTimeStr = sdf.format(enterTime);
		modelAndView.addObject("enterTime", enterTimeStr);
		modelAndView.addObject("imgView", imgViewAddress+crmCanteenApplyPO.getContractImgPath());
		
		//查询抽成分配设置信息
		Long canteenId = crmCanteenApplyPO.getCanteenId();
		if(null == canteenId){
			return modelAndView;
		}
		CrmSplitAssignSetParam crmSplitAssignSetParam = new CrmSplitAssignSetParam();
		crmSplitAssignSetParam.setCanteenId(canteenId);
		crmSplitAssignSetParam.setDelFlag(1);
		crmSplitAssignSetParam.setJudgeEffectiveTime(new Date());
		//查询最近生效时间的抽成信息
		CrmSplitAssignSetPO crmSplitAssignSetPO = crmSplitAssignSetService.getLastEffectiveSplitMsg(crmSplitAssignSetParam);
		if(BeanUtils.isEmpty(crmSplitAssignSetPO)){
			return modelAndView;
		}

		modelAndView.addObject("splitAssignSetPO", crmSplitAssignSetPO);
		
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title: saveAdjustSplitPercent 
	 * @Description: 保存调整食堂分账比 
	 * @param @param crmSplitAssignSetParam
	 * @param @return  
	 * @return RespModel    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月20日 下午8:15:51
	 */
	@RequestMapping(value = "/saveAdjustSplitPercent",method=RequestMethod.POST)
	@ResponseBody
	public RespModel saveAdjustSplitPercent(@RequestBody CrmSplitAssignSetParam crmSplitAssignSetParam){
		logger.info("#####CrmSplitAssignSetController###saveAdjustSplitPercent##crmSplitAssignSetParam:"+crmSplitAssignSetParam.toString());
		RespModel respModel = new RespModel();
		crmSplitAssignSetParam.setCreaterUid(CrmControllerHelper.getSessionUserId());//获取当前UserId
		
		try {
			Boolean flag = crmSplitAssignSetService.saveAdjustSplitPercent(crmSplitAssignSetParam,respModel);//保存调整食堂分账比 
			if(flag) return respModel;
		} catch (Exception e) {
			logger.error("CrmSplitAssignSetController:saveAdjustSplitPercent:"+e);
			respModel.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			respModel.setDesc(e.getMessage());
			return respModel;
		}
		
		return respModel;
	}
	/**
	 * 食堂分账 初始化
	* @Title: canteenSplitAssignSetInit 
	* @author : lcl
	* @time : 2017年6月22日 下午3:25:06
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@RequestMapping(value="/canteenSplitAssignSetInit")
	public ModelAndView canteenSplitAssignSetInit(){
		ModelAndView model = new ModelAndView("collecting_statistics/cateen_fashinable");
		
		
		return model;
	}
	@RequestMapping(value="/canteenSplitAssignSet")
	public PageRespModel canteenSplitAssignSet(CrmSplitAssignSetParam crmSplitAssignSetParam){
		PageRespModel model = new PageRespModel();
	//	Page<CrmSplitAssignSetPO> page = this.crmSplitAssignSetService.findPageByParam(crmSplitAssignSetParam);
		
		
		return model;
	}
	
	
	
	
	
	
	
}