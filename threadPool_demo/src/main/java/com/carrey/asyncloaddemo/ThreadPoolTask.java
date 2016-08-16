package com.carrey.asyncloaddemo;
/**
 * 任务单元
 * @author carrey
 *
 */
public abstract class ThreadPoolTask implements Runnable {

	protected String url;
	
	public ThreadPoolTask(String url) {
		this.url = url;
	}
	
	public abstract void run();
	
	public String getURL() {
		return this.url;
	}
}
