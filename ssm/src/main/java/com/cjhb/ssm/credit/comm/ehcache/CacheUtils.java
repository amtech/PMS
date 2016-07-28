/**
* 
*
*@author：
*@since： JDK1.6
*@version：1.0
*@date：2015年5月20日 下午3:48:45
*/ 

package com.cjhb.ssm.credit.comm.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: CacheBuilder
 * @Description: Cache工具类
 * @author 
 * @date 2015年5月20日 下午3:48:45
 *
 */
public class CacheUtils {
	
	final static Log log = LogFactory.getLog(CacheUtils.class);  
	  
    private static CacheManager manager = new CacheManager(System.getProperty("creditMgr.root") + "WEB-INF/classes/config/ehcache.xml");  
      
    static{  
    	manager = CacheManager.getInstance();  
    	if(manager == null)  {
    		manager = CacheManager.create(System.getProperty("creditMgr.root") + "WEB-INF/classes/config/ehcache.xml");  
    		System.out.println();
    	}
    }  
    
    /**
     * @author: kevin
     * @date: 2015年5月20日 下午4:20:35
     * @Description:  将Cache放入CacheManager中
     * @param cacheEntity
     */
    protected synchronized static void put(CacheEntity cacheEntity){
    	 Cache cache = getCache(cacheEntity.getCacheName());  
         if(cache != null){  
	         try {  
	        	 cache.remove(cacheEntity.getKey());  
	        	 Element elem = new Element(cacheEntity.getKey(), cacheEntity.getCacheObject());  
	        	 cache.put(elem);  
	        	 if (log.isDebugEnabled()){
	        		 log.debug("put cache("+cacheEntity.getCacheName()+") of "+cacheEntity.getKey()+" done.");  
	        	 }
	         } catch (Exception e) {  
	        	 log.error("put cache("+cacheEntity.getCacheName()+") of "+cacheEntity.getKey()+" failed.", e);  
	         }  
         }
    }
    
    /**
     * @author: kevin
     * @date: 2015年5月20日 下午4:44:11
     * @Description: 按缓存名称与KEY取的对象
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(CacheName cacheName, String key){  
        Cache cache = getCache(cacheName);  
        if(cache != null){  
            try {  
                Element elem = cache.get(key);  
                if(elem != null && !cache.isExpired(elem))  
                    return elem.getObjectValue();  
            } catch (Exception e) {  
                log.error("Get cache("+cacheName+") of "+key+" failed.", e);  
            }  
        }  
        log.info("Element is null ("+cacheName+") or "+key+" undefined.");  
        return null;  
    }  
    
    /**
	 * @author: kevin
	 * @date: 2015年5月20日 下午5:16:22
	 * @Description: 更新CommonsCache缓存内容
	 * @param cacheName
	 * @param key
	 */
    public synchronized static void updateByKey(CacheUpdateMapping cacheUpdateMapping){
		CacheBuilder.updateByKey(cacheUpdateMapping);
    }
    
    /**
	 * @author: kevin
	 * @date: 2015年5月26日 下午1:59:32
	 * @Description: 更新BaseDataCache的内容
	 * @param key
	 */
    public synchronized static void updateByKey(String baseDataCode){
		CacheBuilder.updateByKey(baseDataCode);
    }
    
    /**
     * @author: kevin
     * @date: 2015年5月20日 下午4:24:53
     * @Description: 
     * @param cacheName
     * @return
     * @throws IllegalStateException
     */
    private static Cache getCache(CacheName cacheName) throws IllegalStateException {  
        return manager.getCache(cacheName.name);  
    }  
    
    /**
     * @author: kevin
     * @date: 2015年5月20日 下午4:30:41
     * @Description: 刷新Cache
     * @param cache
     * @throws IllegalStateException
     * @throws CacheException
     */
    public static void flushCache(CacheName cacheName) throws IllegalStateException, CacheException{  
        Cache cache = getCache(cacheName);  
        if(cache != null){
        	cache.flush();
        }
    }
    
    /**
     * @author: kevin
     * @date: 2015年5月20日 下午4:30:28
     * @Description: 停止缓存管理器
     */
    public static void shutdown(){  
        if(manager != null)  {
            manager.shutdown();
        }
    }  
}
