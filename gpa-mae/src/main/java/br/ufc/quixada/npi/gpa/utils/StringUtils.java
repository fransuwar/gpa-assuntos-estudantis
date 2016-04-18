package br.ufc.quixada.npi.gpa.utils;

public class StringUtils {
	
	public static String capitalize(String s) {
		return s.substring(0,1).toUpperCase() + s.substring(1);
	}
	
	public static boolean contains(String value, String[] args){
		for(int i = 0; i < args.length; i++){
			 if(args[i].equals(value))
				 return true;
		}
		return false;
	}
	
}
