package com.sitech.multidb.common.threadPool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 *
 * @Package com.sitech.multidb.common.threadPool 
 * @ClassName:  ThreadPool4DAO   
 * @author 针虎虎 zhenhh@si-tech.com.cn
 * @date 2017年9月20日 上午10:58:44 
 * @Copyright: © SI-TECH 2017. All rights reserved
 * @version V1.0 
 *
 * 修改日期    修改人    修改目的
 *
 */
public class ThreadPool4DAO {
	private static final Log logger = LogFactory.getLog(ThreadPool4DAO.class);
	//
	public static boolean isReady=false;
	public static ThreadExecutor4DAO threadExecutor4DAO = null;

	public static void initTreadExecutor() {
		logger.info("【缴费线程池--初始化--开始！】");
		threadExecutor4DAO = new ThreadExecutor4DAO();
		threadExecutor4DAO.start();
		isReady=true;
		logger.info("【缴费线程池--初始化--完成！】");
	}
	public static void put(Thread thread) {

		try {
			logger.debug("线程池正在存放任务………………………………………………………………");
			threadExecutor4DAO.execute(thread);
		} catch (Exception e) {
			logger.error("缴费线程池接收任务失败！", e);
		}
	}

	
	public static void put(Runnable thread){

		try {
			logger.debug("线程池正在存放任务………………………………………………………………");
			//测试代码，生产上在监听器中初始化
			if(threadExecutor4DAO == null){
				initTreadExecutor();
			}
			threadExecutor4DAO.execute(thread);
		} catch (Exception e) {
			logger.error("缴费线程池接收任务失败！", e);
		}
	}
}
