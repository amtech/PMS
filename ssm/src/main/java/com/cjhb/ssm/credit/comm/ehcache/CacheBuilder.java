/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年5月20日 下午3:48:45
*/ 

package com.cjhb.ssm.credit.comm.ehcache;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import com.credit.comm.lucene.BuildLuceneIndex;
import com.credit.comm.lucene.CityItem;
import com.credit.comm.util.SpringUtil;
import com.credit.entity.system.AuMenu;
import com.credit.entity.system.BaseDataItem;
import com.credit.service.cache.CacheService;

/**
 * @ClassName: CacheBuilder
 * @Description: 构建cache数据
 * @author 
 * @date 2015年5月20日 下午3:48:45
 *
 */
@SuppressWarnings({"rawtypes","unused","unchecked"})
public class CacheBuilder {

	public CacheService cacheService;
	
	private static Logger log = Logger.getLogger(CacheBuilder.class);
	
	/**
	 * @author: kevin
	 * @date: 2015年5月20日 下午5:11:39
	 * @Description: 自动载入
	 */
	private void init(){
		putRolePath();
		putAllBaseData();
		putCity();
		putNationality();
		putCityToIndex();
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月29日 上午9:46:59
	 * @Description: 将所有的权限的配置信息放入缓存
	 */
	private void putRolePath() {
		log.info("putRolePath Start Timestamp:" + System.currentTimeMillis());
    	//step 1: 将所有权限读取出来
		Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        //step 2: 将所有权限下的URL对应的权限放入resourceMap中
        List<Map<String,String>> list = cacheService.getAllMenuRoleRel();
        List<AuMenu> menuList = cacheService.getAllMenu();
        
        Collection<ConfigAttribute> anonymousAtts = new ArrayList<ConfigAttribute>();
        ConfigAttribute anonymousCa = new SecurityConfig("ROLE_ANONYMOUS");
        anonymousAtts.add(anonymousCa);
        
        resourceMap.put("/", anonymousAtts);
        resourceMap.put("/login", anonymousAtts);
        resourceMap.put("/assets/**", anonymousAtts);
        resourceMap.put("/css/**", anonymousAtts);
        resourceMap.put("/images/**", anonymousAtts);
        resourceMap.put("/js/**", anonymousAtts);
        resourceMap.put("/common/**", anonymousAtts);
        
        //所有ROLE配置
        for (Map<String, String> map : list) {
			String path = map.get("menuPath");
			Collection<ConfigAttribute> atts = resourceMap.get(path);
			if (atts == null){
				atts = new ArrayList<ConfigAttribute>();
			}
			ConfigAttribute ca = new SecurityConfig(map.get("roleCode"));
			atts.add(ca);
			resourceMap.put(path, atts);
		}
        
        //所有MENU配置
        for (AuMenu menu : menuList) {
			String path = menu.getMenuPath();
			if (!StringUtils.isBlank(path)){
				Collection<ConfigAttribute> atts = resourceMap.get(path);
				if (atts == null){
					atts = new ArrayList<ConfigAttribute>();
				}
				ConfigAttribute ca = new SecurityConfig(menu.getMenuCode());
				atts.add(ca);
				resourceMap.put(path, atts);
			}
		}
        
		CacheEntity cacheEntity = new CacheEntity(CacheName.COMMONSCACHE, "rolePath", resourceMap);
		CacheUtils.put(cacheEntity);
		log.info("putRolePath End Timestamp:" + System.currentTimeMillis());
    }
	
	/**
	 * @author: kevin
	 * @date: 2015年5月21日 下午5:03:52
	 * @Description: 载入所有数据字典
	 */
	private void putAllBaseData(){
		List<BaseDataItem> allList = cacheService.getBaseDataByCode(null);
		Map<String,List<BaseDataItem>> baseDataMap = new HashMap<String,List<BaseDataItem>>(); 
		for (BaseDataItem baseDataItem : allList) {
			List<BaseDataItem> itemList = baseDataMap.get(baseDataItem.getTypeCode());
			if (itemList == null){
				itemList = new ArrayList<BaseDataItem>();
				baseDataMap.put(baseDataItem.getTypeCode(), itemList);
			}
			itemList.add(baseDataItem);
		}
		
		//将每一项放到cache中
		log.info("putAllBaseData Start Timestamp:" + System.currentTimeMillis());
		Set<String> keySet = baseDataMap.keySet();
		for (String key : keySet) {
			CacheEntity cacheEntity = new CacheEntity(CacheName.BASEDATACACHE, key, baseDataMap.get(key));
			CacheUtils.put(cacheEntity);
		}
		log.info("putAllBaseData End Timestamp:" + System.currentTimeMillis());
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月21日 下午5:03:52
	 * @Description: 按CODE载入数据字典
	 * @param code
	 */
	private void putBaseDataByCode(String code){
		if (!StringUtils.isBlank(code)){
			List<BaseDataItem> list = cacheService.getBaseDataByCode(code);
			log.info("putBaseDataByCode Start Timestamp:" + System.currentTimeMillis());
			CacheEntity cacheEntity = new CacheEntity(CacheName.BASEDATACACHE, code, list);
			CacheUtils.put(cacheEntity);
			log.info("putBaseDataByCode End Timestamp:" + System.currentTimeMillis());
		}else{
			log.info("Code is null, cant put Element to Cache");
		}
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月22日 上午10:37:33
	 * @Description: 载入民族
	 */
	private void putNationality(){
		log.info("putNationality Start Timestamp:" + System.currentTimeMillis());
		List list = cacheService.getNationalitySelect();
		CacheEntity cacheEntity = new CacheEntity(CacheName.COMMONSCACHE, "nationalitySelect", list);
		CacheUtils.put(cacheEntity);
		log.info("putNationality End Timestamp:" + System.currentTimeMillis());
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年6月5日 下午4:33:39
	 * @Description: 生成城市索引并放入cache中
	 */
	private void putCityToIndex(){
		log.info("putCityToIndex Start Timestamp:" + System.currentTimeMillis());
		List<CityItem> items =  cacheService.getCityByType("3");
		Directory directory = new RAMDirectory();
		BuildLuceneIndex.buildCityIndexer(directory, items);
		CacheEntity cacheEntity = new CacheEntity(CacheName.INDEXCACHE, "cityOfDistrict_INDEX", directory);
		CacheUtils.put(cacheEntity);
		log.info("putCityToIndex End Timestamp:" + System.currentTimeMillis());
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月21日 下午1:48:52
	 * @Description: 载入城市
	 */
	private void putCity(){
		log.info("putCity Start Timestamp:" + System.currentTimeMillis());
		List list = cacheService.getCityByType("1");
		CacheEntity cacheEntity = new CacheEntity(CacheName.COMMONSCACHE, "cityOfProvince", list);
		CacheUtils.put(cacheEntity);
		
		list = cacheService.getCityByType("2");
		cacheEntity = new CacheEntity(CacheName.COMMONSCACHE, "cityOfMunicipality", list);
		CacheUtils.put(cacheEntity);
		
		list = cacheService.getCityByType("3");
		cacheEntity = new CacheEntity(CacheName.COMMONSCACHE, "cityOfDistrict", list);
		CacheUtils.put(cacheEntity);
		
		log.info("putCity End Timestamp:" + System.currentTimeMillis());
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月20日 下午5:16:08
	 * @Description: 更新所有Cache
	 */
	public void updateAll(){
		init();
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月20日 下午5:16:22
	 * @Description: 更新CommonsCache缓存内容
	 * @param key
	 */
	protected static void updateByKey(CacheUpdateMapping cacheUpdateMapping){
		log.info("updateByKey <" + cacheUpdateMapping.name + "> Start Timestamp:" + System.currentTimeMillis());
		Class clazz = SpringUtil.getBean("cacheBuilder").getClass();
		try {
			Method method = clazz.getDeclaredMethod(cacheUpdateMapping.name);
			method.setAccessible(true);
			method.invoke(SpringUtil.getBean("cacheBuilder"));
		} catch (Exception e) {
			log.error("updateByKey failed : " + cacheUpdateMapping.name + "\nException Info: " + e.toString());
		}
		log.info("updateByKey <" + cacheUpdateMapping.name + "> End Timestamp:" + System.currentTimeMillis());
	}
	
	/**
	 * @author: kevin
	 * @date: 2015年5月26日 下午1:59:32
	 * @Description: 更新BaseDataCache的内容
	 * @param key
	 */
	protected static void updateByKey(String key){
		log.info("updateByKey <" + key + "> Start Timestamp:" + System.currentTimeMillis());
		Class clazz = SpringUtil.getBean("cacheBuilder").getClass();
		try {
			Method method = clazz.getDeclaredMethod("putBaseDataByCode");
			method.setAccessible(true);
			method.invoke(SpringUtil.getBean("cacheBuilder"),new Object[]{key});
		} catch (Exception e) {
			log.error("putBaseDataByCode failed : " + key + "\nException Info: " + e.toString());
		}
		log.info("updateByKey <" + key + "> End Timestamp:" + System.currentTimeMillis());
	}
	
	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

}
