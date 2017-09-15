package com.hd.kzscrm.manage.listener;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 容器关闭时杀掉未关闭线程
* @ClassName: ThreadKillListener 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xiongchangjing 
* @date 2017年4月28日 下午7:07:52 
*
 */
public class ThreadKillListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory.getLogger(ThreadKillListener.class);

	public Integer immolate() {  
        int count = 0;  
        try {  
            final Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");  
            threadLocalsField.setAccessible(true);  
            final Field inheritableThreadLocalsField = Thread.class.getDeclaredField("inheritableThreadLocals");  
            inheritableThreadLocalsField.setAccessible(true);  
            for (final Thread thread : Thread.getAllStackTraces().keySet()) {  
                    count += clear(threadLocalsField.get(thread));  
                    count += clear(inheritableThreadLocalsField.get(thread));  
                    if (thread != null) {  
                        thread.setContextClassLoader(null);  
                    }  
            }  
            logger.info("immolated " + count + " values in ThreadLocals"); 
        } catch (Exception e) {  
            throw new Error("ThreadLocalImmolater.immolate()", e);  
        }  
        return count;  
    }  
  
    private int clear(final Object threadLocalMap) throws Exception {  
        if (threadLocalMap == null)  
                return 0;  
        int count = 0;  
        final Field tableField = threadLocalMap.getClass().getDeclaredField("table");  
        tableField.setAccessible(true);  
        final Object table = tableField.get(threadLocalMap);  
        for (int i = 0, length = Array.getLength(table); i < length; ++i) {  
            final Object entry = Array.get(table, i);  
            if (entry != null) {  
                final Object threadLocal = ((WeakReference)entry).get();  
                if (threadLocal != null) {  
                    Array.set(table, i, null);  
                    ++count;  
                }  
            }  
        }  
        return count;  
    }  
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		immolate();

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

}
