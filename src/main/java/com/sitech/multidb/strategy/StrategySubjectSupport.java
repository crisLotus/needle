package com.sitech.multidb.strategy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import lombok.extern.log4j.Log4j;

import com.sitech.multidb.common.threadPool.Thread4DAO;
import com.sitech.multidb.common.threadPool.ThreadPool4DAO;


/**
 * 
 *
 * @Package com.sitech.multidb.strategy 
 * @ClassName:  StrategySubjectSupport   
 * @author 针虎虎 zhenhh@si-tech.com.cn
 * @date 2017年9月20日 上午10:58:11 
 * @Copyright: © SI-TECH 2017. All rights reserved
 * @version V1.0 
 *
 * 修改日期    修改人    修改目的
 *
 */
@Log4j
public class StrategySubjectSupport {
	private static Logger logger = Logger.getLogger(StrategySubjectSupport.class);
	private  String master;
    private  List<String> slavers;
    
    public  void registerMaster(String master) {
    	if(master!=null && !master.equals(this.master)){
    		this.master = null;
        	this.master = master;
    	}
    }

    public  void registerSlavers(String dbName) {
    	if(slavers==null){
    		slavers = new ArrayList<String>();
    	}
    	if(!slavers.contains(dbName)){
    		slavers.add(dbName);
    	}
    }
    
    public Object excute(String methodName, Object[] methodArgs) throws Throwable {
        return notifyObservers(methodName, methodArgs);
    }

//    public abstract void masterVerify();
    
    public  Object notifyObservers(String methodName, Object[] methodArgs) throws Throwable {
        Class[] argsTypes = new Class[methodArgs.length];
        for (int i = 0; i < methodArgs.length; i++) {
            argsTypes[i] = methodArgs[i].getClass();
        }
        
        if(master==null){
        	logger.error("master为null");
        }
        
        Method method;

		try {
			method = Class.forName(master).getDeclaredMethod(methodName, argsTypes);
			return method.invoke(Class.forName(master).newInstance(), methodArgs);
		} catch (NoSuchMethodException e) {
			logger.error("【top-dao】业务API调用失败===》找不到指定的方法："+methodName,e);
		} catch (SecurityException e) {
			logger.error("【top-dao】业务API调用失败",e);
		} catch (ClassNotFoundException e) {
			logger.error("【top-dao】业务API调用失败===》找不到指定的类："+master,e);
		} catch (IllegalAccessException e) {
			logger.error("【top-dao】业务API调用失败===》非法调用",e);
		} catch (IllegalArgumentException e) {
			logger.error("【top-dao】业务API调用失败===》非法参数",e);
		} catch (InvocationTargetException e) {
			logger.error("【top-dao】业务API调用失败===》类型转换失败",e);
		} catch (InstantiationException e) {
			logger.error("【top-dao】业务API调用失败===》实例化异常",e);
		}catch (Throwable e) {
			logger.error("【top-dao】业务API调用失败===》业务异常",e);
			throw e;
		}
		
	
    if(slavers!=null && slavers.size()>0){
    	for (String slaver : slavers) {
    		ThreadPool4DAO.put(new Thread4DAO(slaver,methodName,methodArgs));
    	}
		
			try {
				method = Class.forName(master).getDeclaredMethod(methodName, argsTypes);
				return method.invoke(Class.forName(master).newInstance(), methodArgs);
			} catch (NoSuchMethodException e) {
				logger.error("【top-dao】业务API调用失败===》找不到指定的方法："+methodName,e);
			} catch (SecurityException e) {
				logger.error("【top-dao】业务API调用失败",e);
			} catch (ClassNotFoundException e) {
				logger.error("【top-dao】业务API调用失败===》找不到指定的类："+master,e);
			} catch (IllegalAccessException e) {
				logger.error("【top-dao】业务API调用失败===》非法调用",e);
			} catch (IllegalArgumentException e) {
				logger.error("【top-dao】业务API调用失败===》非法参数",e);
			} catch (InvocationTargetException e) {
				logger.error("【top-dao】业务API调用失败===》类型转换失败",e);
			} catch (InstantiationException e) {
				logger.error("【top-dao】业务API调用失败===》实例化异常",e);
			}catch (Throwable e) {
				logger.error("【top-dao】业务API调用失败===》业务异常",e);
				throw e;
			}
			
		
        if(slavers!=null && slavers.size()>0){
        	for (String slaver : slavers) {
        		ThreadPool4DAO.put(new Thread4DAO(slaver,methodName,methodArgs));
        	}
        }
        return null;
    }
    return null;
}
    


}
