package com.hd.kzscrm.common.util;

public class SessionUtil {
	private static ThreadLocal<Object> threaLocal=new ThreadLocal<Object>();

	   public static void setUserSession(Object obj) {  
		   threaLocal.set(obj);  
	   }  
	   public static Object getUserSession() {  
	        return threaLocal.get();  
	   } 
	   public static void removeUserSession() {  
		   threaLocal.remove();
	   }
	   
	
}
