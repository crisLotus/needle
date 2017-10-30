package com.sitech.multidb.strategy;

import com.sitech.core.cache.config.CoreConfig;


/**
 * 
 *默认主题为单主多从，这样的主题模式是最广泛的也是最常用的。所以暂时对模式不做扩展
 * @Package com.sitech.multidb.strategy 
 * @ClassName:  SubjectTemplateManager   
 * @author 针虎虎 zhenhh@si-tech.com.cn
 * @date 2017年9月20日 上午10:58:24 
 * @Copyright: © SI-TECH 2017. All rights reserved
 * @version V1.0 
 *
 * 修改日期    修改人    修改目的
 *
 */
@Deprecated
public class SubjectTemplateManager {

	public static String initSingleTemplate(){
		String subjectClassName = CoreConfig.getValue("config4Subject.properties", "single");
		return subjectClassName;
	}
	
	public static String initMultiTemplate(){
		String subjectClassName = CoreConfig.getValue("config4Subject.properties", "multi");
		return subjectClassName;
	}
}
