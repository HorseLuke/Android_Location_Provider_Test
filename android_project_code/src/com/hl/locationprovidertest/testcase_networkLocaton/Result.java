package com.hl.locationprovidertest.testcase_networkLocaton;

import android.util.Log;

public class Result {

	/**
	 * 是否命中此种测试
	 */
	protected int hit = 0;
	
	/**
	 * 该测试是否成功？
	 * 0：未经过测试；>0；通过测试；<0测试失败
	 */
	protected int success = 0;
	
	/**
	 * 测试错误说明
	 */
	protected String error = "";
	

	/**
	 * 测试成功说明
	 */
	protected String succ_desc = "";
	
	/**
	 * 日志
	 */
	protected String log = "";
	
	public int get_hit(){
		return this.hit;
	}
	
	public int get_success(){
		return this.success;
	}
	
	public String get_error(){
		return this.error;
	}
	
	public String get_succ_desc(){
		return this.succ_desc;
	}
	
	public int set_hit(int hit){
		this.hit = hit;
		return hit;
	}
	
	public int set_success(int success){
		return this.set_success(success, "");
	}
	
	public int set_success(int success, String error){
		this.success = success;
		if(success < 0){
			this.error = error;			
		}else{
			this.succ_desc = error;

		}
		return success;
	}
	
	public void push_log(String log){
		this.push_log("NO_TITLE", log);
	}
	
	public void push_log(String title, String log){
		log = "[" + title + "]" + log;
		Log.i("com.hl.locationprovidertest.testcase_networkLocaton", log);
		this.log += "\r\n" + log;
	}
	
	public String get_log(){
		return log;
	}
	
}
