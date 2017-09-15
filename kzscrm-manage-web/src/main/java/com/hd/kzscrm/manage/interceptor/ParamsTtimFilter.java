package com.hd.kzscrm.manage.interceptor;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.hd.kzscrm.common.util.StringUtil;
 

public class ParamsTtimFilter implements Filter {

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void doFilter(ServletRequest paramServletRequest,ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		ParameterRequestWrapper myRequest=new ParameterRequestWrapper((HttpServletRequest) paramServletRequest);
		
		
		Enumeration<?> em = myRequest.getParameterNames();
		String keyValue ="";
		while (em.hasMoreElements()) {
			String key = (String) em.nextElement();
			if (StringUtil.isNotEmpty(key)) {
				keyValue = myRequest.getParameter(key);
				if(keyValue instanceof String){
					myRequest.addParameter(key,keyValue.trim());
				}
			}
		}
		ServletRequest request=myRequest;
		chain.doFilter(request,response);
	}
	
	
	  
	
	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {
		
	}

	
	public class ParameterRequestWrapper extends HttpServletRequestWrapper{
		
		    private Map<String , String[]> params = new HashMap<String, String[]>();
		    
		    @SuppressWarnings("unchecked")  
		    public ParameterRequestWrapper(HttpServletRequest request) {  
		        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似  
		        super(request);  
		        //将参数表，赋予给当前的Map以便于持有request中的参数  
		        this.params.putAll(request.getParameterMap());  
		    }  
		    
		    //重载一个构造方法  
		    public ParameterRequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {  
		        this(request);  
		        addAllParameters(extendParams);//这里将扩展参数写入参数表  
		    }
		      
		    
		    @Override  
		    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取  
		        String[]values = params.get(name);  
		        if(values == null || values.length == 0) {  
		            return null;  
		        }  
		        return values[0];  
		    }  
		      
		    public String[] getParameterValues(String name) {//同上  
		         return params.get(name);  
		    }  
		  
		 
		   public void addAllParameters(Map<String , Object>otherParams) {//增加多个参数  
		        for(Map.Entry<String , Object>entry : otherParams.entrySet()) {  
		            addParameter(entry.getKey() , entry.getValue());  
		        }  
		    } 
		  
		  
		    public void addParameter(String name , Object value) {//增加参数  
		        if(value != null) {  
		            if(value instanceof String[]) {  
		                params.put(name , (String[])value);  
		            }else if(value instanceof String) {  
		                params.put(name , new String[] {(String)value});  
		            }else {  
		                params.put(name , new String[] {String.valueOf(value)});  
		            }  
		        }  
		    } 
		    
	}
}
