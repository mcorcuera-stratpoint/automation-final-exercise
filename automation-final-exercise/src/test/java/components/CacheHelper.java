package components;

import java.util.HashMap;
import java.util.Map;

public class CacheHelper {
	private Map<String, Object> scenarioContext;
	private static CacheHelper cache;
	
	public CacheHelper(){
	    scenarioContext = new HashMap<>();
	}
	
	public static CacheHelper getInstance(){
	    if(cache==null){
	       synchronized (CacheHelper.class){
	           cache= new CacheHelper();
	       }
	    }
	    return cache;
	}
	
	public void setContext(String key, Object value) {
	    scenarioContext.put(key, value);
	}
	
	public Object getContext(String key){
	    return scenarioContext.get(key);
	}
}