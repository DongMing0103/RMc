package com.hd.kzscrm.manager.handler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.hd.kzscrm.manager.task.QuerySettlementTask;
import com.hd.kzscrm.manager.task.SplitQueueManagerTask;
 

/**
 * 
 * @description 分账项目启动程序
 * @author zyg
 *
 * @date 2017年5月5日 上午11:03:36
 */
public class InitSplitHandler implements InitializingBean {
	 
	//定时任务线程池
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
	//延迟时间
	private static int initDelay = 3000; 
	//日志
	protected final Logger log = LoggerFactory.getLogger(this.getClass()); 
	 
	/**
	 * 分账 spring 启动类
	 */ 
	@Override
	public void afterPropertiesSet() throws Exception { 
 
		log.info("分账启动加载------start-------");
		//获取昨天的订单分成明细
		executor.scheduleAtFixedRate(new QuerySettlementTask(), initDelay, 60*1000, TimeUnit.MILLISECONDS);  
		//处理分账序列 
		executor.scheduleAtFixedRate(new SplitQueueManagerTask(), initDelay, 20, TimeUnit.MILLISECONDS);
		log.info("分账启动加载------end-------"); 
	}
 
}
