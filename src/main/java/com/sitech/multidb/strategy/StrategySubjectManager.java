package com.sitech.multidb.strategy;

import java.util.HashMap;
import java.util.Map;




import org.apache.commons.lang.StringUtils;

import com.sitech.core.cache.config.CoreConfig;


/**
 * 
* 类名称：StrategySubjectManager
* 类描述： 策略主题管理
* 创建人：针虎虎
* 创建时间：2017年9月1日 下午2:37:00  
* 修改人：针虎虎
* 修改时间：2017年9月1日 下午2:37:00   
* 修改备注：   
* @version
 */
public class StrategySubjectManager {
	public final static String DEFAULT_SUBJECT = "defaultSubject";
	private final static Map<String, StrategySubjectSupport> subjects = new HashMap<String, StrategySubjectSupport>();
	public static void attach(String subjectName,StrategySubjectSupport subjectObj){
		subjects.put(subjectName, subjectObj);
	}
	
	public static void delete(String subjectName){
		if(subjects==null){
    		return;
    	}
		subjects.remove(subjectName);
	}
	
	public static StrategySubjectSupport getStrategySubject(String subjectName){
		return subjects.get(subjectName);
	}
	
	public static StrategySubjectSupport getDefaultStrategySubject(){
		return subjects.get(DEFAULT_SUBJECT);
	}
	
	
	
	public static void initSingleStrategy(){
		String subjectClassLabel = CoreConfig.getValue("config4Strategy.properties", "single");
		StrategySubjectSupport strategySubjectSupport = new StrategySubjectSupport();
		if(StringUtils.isNotBlank(subjectClassLabel)){
			String[] subjectClassLabelArry = subjectClassLabel.split(";");
			for(int i=0;i<subjectClassLabelArry.length;i++){
				String strategyClassName4Master = CoreConfig.getValue("config4dbExample.properties", subjectClassLabelArry[i]);
				strategySubjectSupport.registerMaster(strategyClassName4Master);
				attach(subjectClassLabelArry[i],strategySubjectSupport);
			}
		}
	}
	
	
	public static void initMultiStrategy(){
		//TODO
	}
	
	
	
//	/**
//	 * 
//	* @Title: initStrategy 
//	* @Description: 目前只支持简单策略，即singleMaster，在动态添加策略的过程中，如果模板也是新模板，则暂无法支持动态添加（---策略中心可以解决完全动态策略）。
//	* @param @param templateName
//	* @param @param strategyName 策略TODO：后续需要建立策略中心，用于动态配置策略
//	* @return void    返回类型 
//	* @throws
//	 */
//	public static void initStrategy(String templateName){//templateName = s-mySqlA
//		String subjectClassName = SubjectTemplateManager.initTemplateByKey(templateName);
//		StrategySubjectSupport strategySubjectSupport;
//		try {
//			strategySubjectSupport = (StrategySubjectSupport)Class.forName(subjectClassName).newInstance();
//			String strategyName4Master = null;
//			String strategyClassName4Master = null;
//			if(templateName.startsWith("s")){
//				strategyName4Master = templateName.substring(templateName.indexOf("-")+1);
//				strategyClassName4Master = CoreConfig.getValue("config4"+templateName+".properties", strategyName4Master);
//				strategySubjectSupport.registerMaster(strategyClassName4Master);
//			}else if(templateName.startsWith("m")){
//				//TODO 
//			}else{
//				System.out.println("出错");
//			}
//			
//			attach(templateName,strategySubjectSupport);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public static void initDefaultStrategy(){
//		String subjectClassName = SubjectTemplateManager.initSingleMasterTemplate();
//		StrategySubjectSupport strategySubjectSupport;
//		try {
//			strategySubjectSupport = (StrategySubjectSupport)Class.forName(subjectClassName).newInstance();
//			String configFileName = subjectClassName.substring(subjectClassName.lastIndexOf(".")+1);
//			String dmDB = CoreConfig.getValue("config4"+configFileName+".properties", "dmDB");
//			String ecoraDB = CoreConfig.getValue("config4"+configFileName+".properties", "ecoraDB");
//			strategySubjectSupport.registerMaster(dmDB);
//			strategySubjectSupport.registerSlavers(ecoraDB);
//			attach(DEFAULT_SUBJECT,strategySubjectSupport);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//	public static void defaultStrategy4failOver(){
//		String subjectClassName = SubjectTemplateManager.initSingleMasterTemplate();
//		StrategySubjectSupport strategySubjectSupport;
//		try {
//			strategySubjectSupport = (StrategySubjectSupport)Class.forName(subjectClassName).newInstance();
//			String configFileName = subjectClassName.substring(subjectClassName.lastIndexOf(".")+1);
//			String dmDB = CoreConfig.getValue("config4"+configFileName+".properties", "dmDB");
//			String ecoraDB = CoreConfig.getValue("config4"+configFileName+".properties", "ecoraDB");
//			strategySubjectSupport.registerMaster(ecoraDB);
//			strategySubjectSupport.registerSlavers(dmDB);
//			attach(DEFAULT_SUBJECT,strategySubjectSupport);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
