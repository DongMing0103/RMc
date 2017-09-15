/**
 * 
 */
package com.hd.kzscrm.manager.delayed;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * @description TODO
 * @author zyg
 *
 * @date 2017年4月28日 上午11:47:25
 */
public class SplitOrderDelayed<T extends Runnable> implements Delayed {

	/**
	 * 到期时间
	 */
	private long timeoutTime;
	private SplitOrderEnum splitOrderEnum;

	/**
	 * 加入队列的对象
	 */
	private T splitStatusTask;
	private static final AtomicLong atomic = new AtomicLong(0);

	// 原子对象，防止并发问题
	private long n;
	// 加入序列对象的ID,任ID删除
	private Long id;

	public SplitOrderDelayed(Long id, SplitOrderEnum splitOrderEnum) {
		this.id = id;
		this.splitOrderEnum = splitOrderEnum;
	}

	/**
	 * 超时时间设置
	 * 
	 * @param timeout
	 *            超时（纳秒）
	 * @param orderStatusTask
	 * @param id
	 */
	public SplitOrderDelayed(long timeout, T splitStatusTask, Long id,
			SplitOrderEnum splitOrderEnum) {
		this.timeoutTime = System.nanoTime() + timeout;
		this.splitStatusTask = splitStatusTask;
		this.n = atomic.getAndIncrement();
		this.id = id;
		this.splitOrderEnum = splitOrderEnum;
	}

	/**
	 * 返回与此对象相关的剩余延迟时间，以给定的时间单位表示
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(this.timeoutTime - System.nanoTime(),
				TimeUnit.NANOSECONDS);
	}

	@Override
	@SuppressWarnings("unchecked")
	public int compareTo(Delayed other) {
		// TODO Auto-generated method stub
		if (other == this) // compare zero ONLY if same object
			return 0;
		if (other instanceof SplitOrderDelayed) {
			SplitOrderDelayed<Runnable> x = (SplitOrderDelayed<Runnable>) other;
			long diff = timeoutTime - x.timeoutTime;
			if (diff < 0)
				return -1;
			else if (diff > 0)
				return 1;
			else if (n < x.n)
				return -1;
			else
				return 1;
		}
		long d = (getDelay(TimeUnit.NANOSECONDS) - other
				.getDelay(TimeUnit.NANOSECONDS));
		return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
	}

	public T getTask() {
		return this.splitStatusTask;
	}

	@Override
	public int hashCode() {
		// 根据ID判断是否为同一个对象
		return id.hashCode();
	}

	// 先执行equals方法，再执行hashCode
	@Override
	public boolean equals(Object object) {
		if (object instanceof SplitOrderDelayed
				&& this.splitOrderEnum
						.equals(((SplitOrderDelayed) object).splitOrderEnum)) {
			return object.hashCode() == hashCode() ? true : false;
		}
		return false;
	}

}
