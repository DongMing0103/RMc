package com.hd.kzscrm.manager.controller.split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.model.PageRespModel;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.ExcelUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.agent.CrmAgentPO;
import com.hd.kzscrm.dao.entity.business.CrmBusinessPO;
import com.hd.kzscrm.dao.entity.canteen.CrmCanteenBaseInfoPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermUserRolePO;
import com.hd.kzscrm.dao.entity.split.PayCanteenCashflowPO;
import com.hd.kzscrm.dao.entity.split.PayWithdrawPO;
import com.hd.kzscrm.manage.common.util.CrmControllerHelper;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.param.split.PayWithdrawParam;
import com.hd.kzscrm.service.serviceInter.agent.ICrmAgentService;
import com.hd.kzscrm.service.serviceInter.business.ICrmBusinessService;
import com.hd.kzscrm.service.serviceInter.canteen.ICrmCanteenBaseInfoService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermUserRoleService;
import com.hd.kzscrm.service.serviceInter.split.PayCanteenCashflowService;
import com.hd.kzscrm.service.serviceInter.split.PayWithdrawService;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.kzscrm.service.vo.split.PayWithdrawVO;
import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.plugin.Page;
//import com.sun.tools.internal.ws.processor.model.Model;


/**
 * @ClassName: PayWithdrawaController
 * @Description:提现记录
 * @author wcf
 * @date 2017年3月17日
 */
@Controller
@RequestMapping("/myBill")
public class PayWithdrawController extends BaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PayWithdrawController.class);
	
	/**
	 * 提现service
	 */
	@Autowired
	private PayWithdrawService payWithdrawService;
	
	/**
	 * 流水表
	 */
	@Autowired
	private PayCanteenCashflowService payCanteenCashflowService;
	
	/**
	 * 代理商表
	 */
	@Autowired
	private ICrmAgentService crmAgentService;
	
	/**
	 * 食堂基本信息表
	 */
	@Autowired
	private ICrmCanteenBaseInfoService canteenBaseInfoService;
	
	/**
	 * 业务员表
	 */
	@Autowired
	private ICrmBusinessService crmBusinessService;
	@Resource
    ICrmPermUserRoleService iCrmPermUserRoleService;
	@Autowired
	ICrmPermRoleSourcesService roleSourcesService;
	/**
	 * 资源表（页面按钮控件）
	 */
	@Autowired
    ICrmPermSourcesService  pSourcesService;
	
	@RequestMapping(value="/withdrawalInit" , method =RequestMethod.GET)
	public ModelAndView withdrawalInit(){
		return new ModelAndView("index/withdrawal");
	}
	/**
	 * @Description:初始化
	 * @return ModelAndView 返回类型
	 */
	@RequestMapping(value="/init" , method =RequestMethod.GET)
	public ModelAndView init(){
		ModelAndView view = new ModelAndView("collecting_statistics/withdrawal_record");
		
		Map<String,Integer> map = new  HashMap<String,Integer>();
		PayWithdrawParam param = new  PayWithdrawParam();
		param.setDelFlag(1);
		Page<PayWithdrawVO> pages = payWithdrawService.findPageByParams(param);
		int list =pages.getTotalResult();
		param.setStatus(1);//申请中
		Page<PayWithdrawVO> pages1 = payWithdrawService.findPageByParams(param);
		int list1 =pages1.getTotalResult();
		param.setStatus(4);//成功
		Page<PayWithdrawVO> pages2 = payWithdrawService.findPageByParams(param);
		int list2 =pages2.getTotalResult();
		param.setStatus(3);//失败
		Page<PayWithdrawVO> pages3 = payWithdrawService.findPageByParams(param);
		int list3 =pages3.getTotalResult();
		map.put("all", 0);
		map.put("nonCkecked", 0);//未审核
		map.put("success", 0);//成功
		map.put("falses", 0);//失败
		if(BeanUtils.isNotEmpty(list)){
			map.put("all",list);
		}
		if(BeanUtils.isNotEmpty(list1)){
			map.put("nonCkecked", list1);
		}
		if(BeanUtils.isNotEmpty(list2)){
			map.put("success", list2);
		}
		if(BeanUtils.isNotEmpty(list3)){
			map.put("falses", list3);
		}
	
		List<CrmPermSourcesVO>  cSourcesVOs = getPermSourceVOs();
		
		view.addObject("cSourcesVOs", cSourcesVOs);
		view.addObject("map", map);
		return view;
	}
	
	@RequestMapping(value="/queryPage")
	@ResponseBody
	public PageRespModel queryPage(PayWithdrawParam param) throws Exception{
		PageRespModel model = new PageRespModel();
		param.setDelFlag(1);
		Page<PayWithdrawVO> pages = payWithdrawService.findPageByParams(param);
		model.setTotal(pages.getTotalResult());
		model.setRows(pages.result);
		return model;
	}
	
	/**
	 * 提现记录详情导出
	 * @author xu
	 */
	@RequestMapping(value="/payWithdrawExcelOut")
	public void payWithdrawExcelOut(HttpServletResponse response, PayWithdrawParam param){
		Page<PayWithdrawVO> pages = payWithdrawService.findPageByParams(param);
		List<PayWithdrawVO> list = pages.result;
		List<PayWithdrawVO> listNew = new ArrayList<>();
		
		for (PayWithdrawVO payWithdrawVO : list){
			Long userId = payWithdrawVO.getUserId(); 
			PayWithdrawPO payWithdrawPO = payWithdrawService.findByUserId(userId);
			Long cashflowId = payWithdrawPO.getPayCanteenCashFlowId();
			PayCanteenCashflowPO cashflowPO = payCanteenCashflowService.findById(cashflowId);
			
			if (BeanUtils.isNotEmpty(payWithdrawPO)) {
				payWithdrawVO.setWithdrawalsFlowNo(payWithdrawPO.getWithdrawalsFlowNo());  //提现编号
				payWithdrawVO.setCreateTime(payWithdrawPO.getCreateTime());       //提现创建时间
				payWithdrawVO.setUserType(payWithdrawPO.getUserType());           //提现角色
				payWithdrawVO.setRealMoney(payWithdrawPO.getRealMoney());        //提现金额
			}
			if (BeanUtils.isNotEmpty(cashflowPO)) {
				payWithdrawVO.setCashFlowNo(cashflowPO.getCashFlowNo());   //获取流水号
				payWithdrawVO.setStatus(cashflowPO.getCashFlowStatus());    //提现状态
				payWithdrawVO.setBalance(cashflowPO.getBalance());          //余额
			}
			if (payWithdrawVO.getUserType().equals(4)) {
				CrmCanteenBaseInfoPO canteenBaseInfoPO = canteenBaseInfoService.findByUserId(userId);
				payWithdrawVO.setAllName(canteenBaseInfoPO.getName());      //获取食堂名称
			}
			if (payWithdrawVO.getUserType().equals(5)) {
				CrmAgentPO agentPO = crmAgentService.findByUserId(userId);
				payWithdrawVO.setAllName(agentPO.getName());           //获取代理商名称
			}
			if (payWithdrawVO.getUserType().equals(6)) {
			    CrmBusinessPO businessPO = crmBusinessService.findByUserId(userId);
			    payWithdrawVO.setAllName(businessPO.getName());       //获取业务员名称
			}
			
			if (BeanUtils.isNotEmpty(payWithdrawVO)) {
				if (payWithdrawVO.getUserType().equals(4)) {
					payWithdrawVO.setUserTypeName("平台");
				}
				if (payWithdrawVO.getUserType().equals(5)) {
					payWithdrawVO.setUserTypeName("代理商");
				}
				if (payWithdrawVO.getUserType().equals(6)) {
					payWithdrawVO.setUserTypeName("业务员");
				}
				listNew.add(payWithdrawVO);
			}
	  } // end for payWithdrawVO
		if (listNew != null) {
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			map.put("withdrawalsFlowNo", "提现编号");
			map.put("cashFlowNo", "流水号");
			map.put("operationTime", "提现时间");
			map.put("userTypeName", "提现角色");
			map.put("allName", "名称");
			map.put("statusName", "提现状态");
			map.put("initialMoney", "提现金额");
			map.put("balance", "账户余额");
			try {
				ExcelUtil.writeXls(response, "提现记录", map, listNew, PayWithdrawVO.class);
			} catch (Exception e) {
				LOGGER.error("PayWithdrawController:payWithdrawExcelOut", e);
			}
		}
	}

	
	public List<CrmPermSourcesVO> getPermSourceVOs(){
		CrmAccountPO sessionUser = CrmControllerHelper.getSessionUser();
		Long userId = sessionUser.getId();
		CrmPermUserRolePO crmPermUserRolePO = iCrmPermUserRoleService.findByUserId(userId);//用户，角色映射

		Set<Long> roleSourceSet = new HashSet<Long>();
		if(BeanUtils.isNotEmpty(crmPermUserRolePO)){
			Long roleId = crmPermUserRolePO.getRoleId();
			List<CrmPermRoleSourcesPO> permRoleSourcesPOs = roleSourcesService.findByRoleId(roleId);
			if(CollectionUtils.isNotEmpty(permRoleSourcesPOs)){
				for(CrmPermRoleSourcesPO cRoleSourcesPO : permRoleSourcesPOs){
					roleSourceSet.add(cRoleSourcesPO.getSourcesId());
				}
			}
			
		}

      //查询 角色的按钮资源
		CrmPermSourcesParam permSourcesParam = new  CrmPermSourcesParam();
		permSourcesParam.setDelFlagAll(1);//非空就全查询
		List<CrmPermSourcesPO> cRoleSourcesPOs = pSourcesService.listByParam(permSourcesParam);
		List<CrmPermSourcesVO> cSourcesVOs = BeanConvertor.copyList(cRoleSourcesPOs, CrmPermSourcesVO.class);
		for(CrmPermSourcesVO cSourcesVO : cSourcesVOs){
			if(BeanUtils.isNotEmpty(cSourcesVO) && BeanUtils.isNotEmpty(cSourcesVO.getChecked())){
				if(cSourcesVO.getChecked()==1){
					cSourcesVO.setCheck(true);
				}else{
					cSourcesVO.setCheck(false);
				}
			}
		}
		return cSourcesVOs;
	}
}
