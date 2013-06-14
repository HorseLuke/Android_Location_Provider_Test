package com.hl.locationprovidertest.testcase_networkLocaton;

import android.content.Context;
import android.content.res.Resources;
import com.hl.locationprovidertest.testcase_networkLocaton.Case_abstract;

/**
 * 此处为判断Android 4.1.x的网络定位可用性
 * Android 4.1.x版本，是采用config_networkLocationProviderPackageName，格式字符串
 * @author Horse Luke
 */
public class Case_config_networkLocationProviderPackageName extends Case_abstract {

	@Override
	public Result test(Context mcontext) {
		Resources resources = Resources.getSystem();
        int res_id = resources.getIdentifier("config_networkLocationProviderPackageName", "string", "android");
        if(res_id <= 0){
        	this.result.push_log("config_networkLocationProviderPackageName", "[INFO]not found, ignore test");
        	return this.result;
        }

        //命中此测试的预设检查，继续
        this.result.set_hit(1);
        
    	String res_str = resources.getString(res_id);
    	this.result.push_log("config_networkLocationProviderPackageName", res_str);
    	
    	this.result.set_success(-1, "暂时没实现检查Android 4.1.x网络定位初始化可用性");
    	
        return this.result;
        
	}
	
}
