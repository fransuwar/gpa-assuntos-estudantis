package br.ufc.quixada.npi.gpa.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil<T> {

	public Map<String, String> toMap(List<T> l, String campoChave, String campoValor){
		Map<String, String> mapV = new HashMap<String, String>();
		try {
			for(T o : l){
				Object objChave = getField(o, campoChave.split("\\."));
				Object objValor = getField(o, campoValor.split("\\."));
				mapV.put(objChave.toString(), objValor.toString());
			}
			if(mapV.isEmpty()){
				mapV = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mapV;
	}
	
	public Object getField(T o, String[] fieldNames) throws Exception{
		for(String fieldName : fieldNames){
			Method m = o.getClass().getMethod("get" + StringUtils.capitalize(fieldName), (Class<?>[])null);
			o = (T) m.invoke(o, (Object[])null);
		}
		return o;
	}
}
