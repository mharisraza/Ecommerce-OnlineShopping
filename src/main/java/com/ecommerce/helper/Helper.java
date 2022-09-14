package com.ecommerce.helper;

public class Helper {
	public static String shortDesc(String desc) {
		
		String[] strs = desc.split(" ");
		
		
		if(strs.length>10) {
			String res="";
			for(int i=0;i<10;i++) {
				res=res+strs[i]+" ";	
				
			}
			return (res+"....");
			
		}
		else {
			return (desc+"...");
		}
		
	}

}
