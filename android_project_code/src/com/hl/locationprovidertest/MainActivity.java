package com.hl.locationprovidertest;

import java.util.List;

import com.hl.locationprovidertest.testcase_networkLocaton.Case_abstract;

import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	protected static final String LOG_TAG = "com.hl.locationprovidertest_main";
	
	protected LocationManager locationManager;
	
	protected EditText editText_result;
	
	/**
	 * NetworkLocation可用状态：
	 * -1不可用；0未判定；1：可用
	 */
	protected int status_NetworkLocation = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//资源初始化
		locationManager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		editText_result = (EditText) findViewById(R.id.editText_result);
		
		this.after_onCreate_btn_test();
		
		Log.v(LOG_TAG, "test begin");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * 
	 */
	protected void after_onCreate_btn_test(){
		
		Button btn = (Button) findViewById(R.id.btn_test);
		btn.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				
				testcase_isProviderEnabled();
				testcase_getProviders();
				testcase_getProvider();
				
				String[] testcase = {"Case_config_networkLocationProvider", "Case_config_networkLocationProviderPackageName", "Case_config_locationProviderPackageNames"};
				com.hl.locationprovidertest.testcase_networkLocaton.Result result = null;
				for(String i: testcase){
					try {
						com.hl.locationprovidertest.testcase_networkLocaton.Case_abstract testcase_instance = (Case_abstract) Class.forName("com.hl.locationprovidertest.testcase_networkLocaton" + "." + i).newInstance();
						result = testcase_instance.test(((App_tester)getApplicationContext()));
						if(result.get_hit() > 0){
							break;
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
						MainActivity.this.editText_append("[Exception]" + i, e.toString());
						e.printStackTrace();
					}
				}
				
				String final_result = "";
				if(null == result){
					final_result = "暂时找不到合适的判断方法，来判断网络定位初始化是否正常";

				}else{
					MainActivity.this.editText_append("test log", result.get_log());
					int success = result.get_success();
					
					if(success < 0){
						final_result = result.get_error();
					}else if(success == 0){
						final_result = "暂时找不到合适的判断方法，来判断网络定位初始化是否正常";
					}else{
						final_result = result.get_succ_desc();
					}
					
				}
				
				MainActivity.this.logger("结论", final_result);
				setTitle(final_result);
				Toast.makeText(MainActivity.this, final_result, Toast.LENGTH_LONG).show();					
								
				
			}
			
		});
		
	}
	
	protected void logger(String title, String log_text){
		Log.v(LOG_TAG, title + ":" + log_text);
		this.editText_append(title, log_text);
	}
	
	protected void editText_append(String title, String log_text){
		editText_result.append("\r\n=========\r\n" + title + ":\r\n" + log_text);
	}
	
	/**
	 * LocationManager.isProviderEnabled()
	 * @return void
	 */
	protected void testcase_isProviderEnabled(){
		
		boolean GPS_status = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		boolean NETWORK_status = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		
		String log_test = "NETWORK:" + NETWORK_status + " ;GPS:" + GPS_status;
		logger("LocationManager.isProviderEnabled(PARAM)", log_test);
		
	}

	/**
	 * getProviders/getAllProviders
	 */
	protected void testcase_getProviders(){
		//http://developer.android.com/reference/android/location/LocationManager.html#PASSIVE_PROVIDER
		List<String> allProviders = locationManager.getAllProviders();
		logger("LocationManager.getAllProviders()", allProviders.toString());
		
		List<String> getProviders_res = locationManager.getProviders(false);
		logger("locationManager.getProviders(false)", getProviders_res.toString());
		
		List<String> getProviders_true_res = locationManager.getProviders(true);
		logger("locationManager.getProviders(true)", getProviders_true_res.toString());			
		
	}
	
	/**
	 * getProvider
	 * 暂时不判断Android 4.2开始新增的fused provider的providers
	 * @link https://code.google.com/p/android-source-browsing/source/detail?spec=svn.platform--frameworks--base.f2d89ef9d57b096962b802608a88d3483a51d08a&repo=platform--frameworks--base&name=android-cts-4.2_r2&r=6fa9ad4afcd762aea519ff61811386c23d18ddb2
	 */
	protected void testcase_getProvider(){
		String log_test= "";
		
		LocationProvider provider_network = locationManager.getProvider(LocationManager.NETWORK_PROVIDER);
		if(null == provider_network){
			log_test = "ERROR!NULL!!";
		}else{
			log_test = provider_network.getName() + " in " + provider_network.getClass().getName();
		}
		logger("locationManager.getProvider(LocationManager.NETWORK_PROVIDER)", log_test);
		
		LocationProvider provider_gps = locationManager.getProvider(LocationManager.GPS_PROVIDER);
		if(null == provider_network){
			log_test = "ERROR!NULL!!";					
		}else{
			log_test = provider_gps.getName() + " in " + provider_gps.getClass().getName();					
		}
		logger("locationManager.getProvider(LocationManager.GPS_PROVIDER", log_test);			
	}

	
}
