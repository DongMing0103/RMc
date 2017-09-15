package com.hd.kzscrm.manager.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hd.kzscrm.manager.delayed.SplitOrderDelayed;
import com.hd.kzscrm.manager.singleton.SplitSingleton;
/**
 * 
 * @description 执行分账包含队列的线程
 * @author zyg
 *
 * @date 2017年5月5日 上午10:40:30
 */
public class SplitQueueManagerTask implements Runnable{
	//队列处理线程池
	private static ExecutorService executorService = Executors.newCachedThreadPool(); 
	//日志  
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void run() { 
		try {
			// 从延迟队列中取值,如果没有对象过期则队列一直等待，
			SplitOrderDelayed<Runnable> t1 = SplitSingleton.getInstance().get().take();
			if (t1 != null) {
				// 修改问题的状态
				Runnable task = t1.getTask();
				if (task == null) {
					return;
				}
				executorService.execute(task);
				LOG.info("[at task:" + task + "]   [Time:" + System.currentTimeMillis() + "]");
			}
		} catch (Throwable t) {
			LOG.error("[at task:Exception [Time:" + System.currentTimeMillis() + "]");
			t.printStackTrace();
		}
	}
 
}
