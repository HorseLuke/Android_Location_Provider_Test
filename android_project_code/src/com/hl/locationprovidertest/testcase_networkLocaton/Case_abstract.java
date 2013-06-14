package com.hl.locationprovidertest.testcase_networkLocaton;

import android.content.Context;

abstract public class Case_abstract {
	
	/**
	 * 该测试的简要测试说明
	 */
	protected String desc = "一个abstract式测试说明";
	
	/**
	 * 测试结果
	 */
	protected Result result = new Result();
	
	/**
	 * 获取该测试的简要文本说明
	 * @return
	 */
	public String get_desc(){
		return this.desc;
	}
	
	protected Result get_result(){
		return this.result;
	}
	
	/**
	 * 测试主体。如果hit为0，表示不属于此testcase，跳走
	 * @param test 请在Activity中传递((App_tester)getApplicationContext());
	 * @return Result
	 */
	public abstract Result test(Context mcontext);
	
}
