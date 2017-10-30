package com.sitech.multidb.common.threadPool;

import java.lang.reflect.Method;

/**
 * 
 *
 * @Package com.sitech.multidb.common.threadPool 
 * @ClassName:  Thread4DAO   
 * @author 针虎虎 zhenhh@si-tech.com.cn
 * @date 2017年9月20日 上午10:58:52 
 * @Copyright: © SI-TECH 2017. All rights reserved
 * @version V1.0 
 *
 * 修改日期    修改人    修改目的
 *
 */
public class Thread4DAO implements Runnable{
	
	private String slaver;
	private String methodName;
	private Object[] methodArgs;
	
	
	
	public Thread4DAO(String slaver, String methodName, Object[] methodArgs) {
		super();
		this.slaver = slaver;
		this.methodName = methodName;
		this.methodArgs = methodArgs;
	}



	@Override
	public void run() {
		Class[] argsTypes = new Class[methodArgs.length];
        for (int i = 0; i < methodArgs.length; i++) {
            argsTypes[i] = methodArgs[i].getClass();
        }
		Method methods;
		try {
			methods = Class.forName(slaver).getDeclaredMethod(methodName, argsTypes);
			methods.invoke(Class.forName(slaver).newInstance(), methodArgs);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
