package com.hl.locationprovidertest.testcase_networkLocaton;

import android.content.Context;
import android.content.res.Resources;
import com.hl.locationprovidertest.testcase_networkLocaton.Case_abstract;

//https://android.googlesource.com/platform/frameworks/base/+log/master/services/java/com/android/server/LocationManagerService.java			
//https://android.googlesource.com/platform/frameworks/base/+/android-4.2.2_r1.2/core/res/res/values/config.xml

//https://android.googlesource.com/platform/frameworks/base/+/master/services/java/com/android/server/LocationManagerService.java

//https://android.googlesource.com/platform/frameworks/base/+/4118012da9a22694b3353040a485f8cdc27e2f17/services/java/com/android/server/LocationManagerService.java
//https://android.googlesource.com/platform/frameworks/base/+/android-4.2.2_r1.2/services/java/com/android/server/LocationManagerService.java
//com.android.internal.R.array.config_locationProviderPackageNames
//com.android.internal.R.array.config_overlay_locationProviderPackageNames

/**
 * 此处为判断Android 4.2.x及以上的网络定位可用性
 * 手头无机器，暂时不做
 * Android 4.2.x及以上版本，是采用config_locationProviderPackageNames（甚至还有config_overlay_locationProviderPackageNames），格式数组
 */	
public class Case_config_locationProviderPackageNames extends Case_abstract {

	@Override
	public Result test(Context mcontext) {

		Resources resources = Resources.getSystem();
        int res_id = resources.getIdentifier("config_locationProviderPackageNames", "array", "android");
        if(res_id <= 0){
        	this.result.push_log("config_locationProviderPackageNames", "[INFO]not found, ignore test");
        	return this.result;
        }
        
        //命中此测试的预设检查，继续
        this.result.set_hit(1);        

        String[] config = Resources.getSystem().getStringArray(res_id);
        
        
        String[] config2 = {};
        
        int res_id_2 = resources.getIdentifier("config_overlay_locationProviderPackageNames", "array", "android");
        if(res_id_2 <= 0){
        	this.result.push_log("config_overlay_locationProviderPackageNames", "[INFO]not found, ignore test");
        	return this.result;
        }    
        config2 = Resources.getSystem().getStringArray(res_id_2);
        
        config = this.getMergeArray(config, config2);
        this.result.push_log("config_overlay_locationProviderPackageNames", config.toString());
        
        this.result.set_success(-1, "暂时没实现Android 4.2.x网络定位初始化可用性检查");
        
        return this.result;
        
	}
	
	
	protected String[] getMergeArray(String[] al,String[] bl) {
		  String[] a = al;
		  String[] b = bl;
		  String[] c = new String[a.length + b.length];
		  System.arraycopy(a, 0, c, 0, a.length);
		  System.arraycopy(b, 0, c, a.length, b.length);
		  return c;
	}
	
}
