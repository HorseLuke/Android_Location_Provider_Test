package com.hl.locationprovidertest.testcase_networkLocaton;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import com.hl.locationprovidertest.testcase_networkLocaton.Case_abstract;

/**
 * 此处为判断Android 4.0.x的网络定位可用性
 * Android 4.0.x及以下版本，是采用config_networkLocationProvider，格式字符串
 * @link https://android.googlesource.com/platform/frameworks/base/+/froyo-release/core/res/res/values/config.xml
 * @link https://android.googlesource.com/platform/frameworks/base/+/android-2.2.3_r2.1/location/java/android/location/LocationManager.java
 * @link https://android.googlesource.com/platform/frameworks/base/+/android-2.2.3_r2.1/services/java/com/android/server/LocationManagerService.java
 * @author Horse Luke
 */
public class Case_config_networkLocationProvider extends Case_abstract {

	@Override
	public Result test(Context mcontext) {
		Resources resources = Resources.getSystem();
        int res_id_config_networkLocationProvider = resources.getIdentifier("config_networkLocationProvider", "string", "android");
        if(res_id_config_networkLocationProvider <= 0){
        	this.result.push_log("config_networkLocationProvider", "[INFO]not found, ignore test");
        	return this.result;
        }

        //命中此测试的预设检查，继续
        this.result.set_hit(1);
        
    	String res_str_config_networkLocationProvider = resources.getString(res_id_config_networkLocationProvider);
    	this.result.push_log("config_networkLocationProvider", res_str_config_networkLocationProvider);
    	
    	PackageManager pm = mcontext.getPackageManager();
    	ResolveInfo ResolveInfo_config_networkLocationProvider = pm.resolveService(new Intent(res_str_config_networkLocationProvider), 0);
    	if(null == ResolveInfo_config_networkLocationProvider){
    		this.result.push_log("config_networkLocationProvider", "[resolveService " + res_str_config_networkLocationProvider + " ERROR]NULL");
    		this.result.set_success(-1, "[Android <= 4.0.x网络定位初始化可用性检查失败]config_networkLocationProvider中的" + res_str_config_networkLocationProvider + "进行resolveService失败；网络定位初始化异常，不能正常使用");
    	}else{
    		this.result.push_log("config_networkLocationProvider", "[resolveService " + res_str_config_networkLocationProvider + " SUCCESS]" + ResolveInfo_config_networkLocationProvider.toString());
    		this.result.set_success(1, "[Android <= 4.0.x网络定位初始化可用性检查成功]config_networkLocationProvider中的" + res_str_config_networkLocationProvider + "进行resolveService正常；网络定位初始化正常；如果你是使用Google Mobile Services，请自行检查与Google Service FrameWork通讯是否完好");
    	}
        
        return this.result;
        
	}
	
}
