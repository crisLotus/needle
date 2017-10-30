package com.sitech.multidb.strategy;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.sitech.core.architecture.bean.RetInfo;
import com.sitech.multidb.strategy.StrategySubjectManager;
import com.sitech.multidb.strategy.StrategySubjectSupport;

public class TestService {
//	private StrategySubjectSupport strategyMysqlA = StrategySubjectManager.getStrategySubject("s-mySqlA");
//	private StrategySubjectSupport strategyMysqlB = StrategySubjectManager.getStrategySubject("s-mySqlB");
//	private StrategySubjectSupport strategyRedis = StrategySubjectManager.getStrategySubject("s-Redis");
//	public void excuteTest(){
//		RetInfo bb = strategyMysqlA.excute("queryMysqlATest", new Object[]{"13834567251"});
//		RetInfo br = strategyRedis.excute("queryRedisTest", new Object[]{"13834567251"});
//		try {
//			BeanUtils.copyProperties(br, bb);
//			RetInfo result = strategyMysqlB.excute("insertMysqlBTest", new Object[]{br});
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	public static void main(String[] args){
		StrategySubjectManager.initSingleStrategy();
		StrategySubjectSupport strategyRedis = StrategySubjectManager.getStrategySubject("redis");
//		StrategySubjectManager.initStrategy("s-mySqlA");
//		StrategySubjectManager.initStrategy("s-mySqlB");
//		StrategySubjectManager.initStrategy("s-redis");
//		
//		StrategySubjectSupport strategyMysqlA1 = StrategySubjectManager.getStrategySubject("s-mySqlA");
//		StrategySubjectSupport strategyMysqlB1 = StrategySubjectManager.getStrategySubject("s-mySqlB");
//		StrategySubjectSupport strategyRedis1 = StrategySubjectManager.getStrategySubject("s-redis");
//		
//		
//
//		RetInfo bb = strategyMysqlA1.excute("queryMysqlATest", new Object[]{"13834567251"});
//		RetInfo br = strategyRedis1.excute("queryRedisTest", new Object[]{"13834567251"});
//		try {
//			BeanUtils.copyProperties(br, bb);//这里属性拷贝还有点问题，实际场景可以不用这个，或者不涉及这个。
//			RetInfo result = strategyMysqlB1.excute("insertMysqlBTest", new Object[]{br});
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
