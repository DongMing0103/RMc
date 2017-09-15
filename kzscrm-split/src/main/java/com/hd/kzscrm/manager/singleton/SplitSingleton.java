package com.hd.kzscrm.manager.singleton;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import com.hd.kzscrm.manager.delayed.SplitOrderDelayed;
import com.hd.kzscrm.manager.delayed.SplitOrderEnum;
/**
 * 
 * @description 分账单例类
 * @author zyg
 *
 * @date 2017年5月6日 上午10:24:32
 */
public class SplitSingleton {
	
	//创建一个最初为空的新 DelayQueue 
	private DelayQueue<SplitOrderDelayed<Runnable>> t = new DelayQueue<>();
	
	 
	/**
	 *  内部类使用原生的单例，类在加载时，天生的加了锁
	 * @description TODO
	 * @author zyg
	 * @date 2017年5月4日 下午2:28:42
	 */
	private static class LazyHolder {
		private static SplitSingleton splitSingleton = new SplitSingleton(); 
	}
	
	/**
	 * 调用当前线程的唯一方式
	 * @return
	 */
	public static SplitSingleton getInstance() {
		return LazyHolder.splitSingleton;
	} 
	/**
	 *私有化 构造方法
	 */
	private SplitSingleton(){
		
	}
	/**
	 * 添加任务， time 延迟时间 task 任务 用户为问题设置延迟时间
	 * @time time转换成纳秒
	 */
	public void put(long time, Runnable task,Long id,SplitOrderEnum splitOrderEnum) { 
		// 毫秒转化成纳秒
		long nanoTime = TimeUnit.NANOSECONDS.convert(time,TimeUnit.MILLISECONDS);
		// 创建一个任务
		SplitOrderDelayed<Runnable> k = new SplitOrderDelayed<>(nanoTime, task,id,splitOrderEnum);
		// 将任务放在延迟的队列中
		t.put(k);
	}

	/**
	 * 结束订单 
	 * @param task
	 */
	public boolean endTask(SplitOrderDelayed<Runnable> task) {
		return t.remove(task);
	}
	/**
	 * 获取方法
	 * @return
	 */
	public DelayQueue<SplitOrderDelayed<Runnable>> get(){
		return t;
	}
	
	 
	
	
}
