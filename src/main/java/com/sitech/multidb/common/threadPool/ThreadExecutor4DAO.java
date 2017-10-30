package com.sitech.multidb.common.threadPool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.sitech.core.architecture.log.logstat.thread.ThreadExecutor4JDK;
import com.sitech.core.cache.config.CoreConfig;


/**
 * 
 *
 * @Package com.sitech.multidb.common.threadPool 
 * @ClassName:  ThreadExecutor4DAO   
 * @author 针虎虎 zhenhh@si-tech.com.cn
 * @date 2017年9月20日 上午10:11:32 
 * @Copyright: © SI-TECH 2017. All rights reserved
 * @version V1.0 
 *
 * 修改日期    修改人    修改目的
 *
 */
public class ThreadExecutor4DAO extends ThreadExecutor4JDK{
	private static Logger logger = Logger.getLogger(ThreadExecutor4DAO.class);
	public void start() {
		logger.info("top-dao线程池初始化开始.....");
		if (!StringUtils.isEmpty(CoreConfig.getValue("config4ThreadPool.properties","corePoolSize"))) {
			corePoolSize = Integer.parseInt(CoreConfig.getValue("config4ThreadPool.properties","corePoolSize"));
		}
		if (!StringUtils.isEmpty(CoreConfig.getValue("config4ThreadPool.properties","maximumPoolSize"))) {
			maximumPoolSize = Integer.parseInt(CoreConfig.getValue("config4ThreadPool.properties","maximumPoolSize"));
		}
		if (!StringUtils.isEmpty(CoreConfig.getValue("config4ThreadPool.properties","maxIdleTime"))) {
			maxIdleTime = Integer.parseInt(CoreConfig.getValue("config4ThreadPool.properties","maxIdleTime"));
		}
		if (!StringUtils.isEmpty(CoreConfig.getValue("config4ThreadPool.properties","maxSize4Queue"))) {
			maxSize4Queue = Integer.parseInt(CoreConfig.getValue("config4ThreadPool.properties","maxSize4Queue"));
		}
		logger.info("top-dao  corePoolSize=" + corePoolSize);
		logger.info("top-dao  maximumPoolSize=" + maximumPoolSize);
		logger.info("top-dao  maxIdleTime=" + maxIdleTime);
		logger.info("top-dao  maxSize4Queue=" + maxSize4Queue);
		taskqueue = new TaskQueue();

		// 可以通过设置默认的ThreadFactory来改变threadpool如何创建thread
		TaskThreadFactory tf = new TaskThreadFactory(namePrefix);
		executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, maxIdleTime, TimeUnit.MILLISECONDS, taskqueue, tf) {
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				AtomicInteger atomic = submittedTasksCount;
				if (atomic != null) {
					atomic.decrementAndGet();
				}
			}
		};
		
		taskqueue.setParent((ThreadPoolExecutor) executor);
		submittedTasksCount = new AtomicInteger();
		logger.info("top-dao线程框架完成启动...");
	}
	
//	 ---------------------------------------------- ThreadFactory Inner Class
	class TaskThreadFactory implements ThreadFactory {
		final ThreadGroup group;
		final AtomicInteger threadNumber = new AtomicInteger(1);
		final String namePrefix;

		TaskThreadFactory(String namePrefix) {
			SecurityManager s = System.getSecurityManager();
			group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			this.namePrefix = namePrefix;
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement());
			t.setDaemon(daemon);
			t.setPriority(getThreadPriority());
			return t;
		}
	}
}
