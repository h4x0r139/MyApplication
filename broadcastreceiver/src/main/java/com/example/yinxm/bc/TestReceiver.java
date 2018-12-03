package com.example.yinxm.bc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.widget.Toast;

public class TestReceiver extends BroadcastReceiver{

	public TestReceiver(){
		System.out.println("TestReceiver");
	}
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("onReceive");

		Toast.makeText(context, "onReceive="+intent.getAction(), Toast.LENGTH_SHORT).show();
		//接收方法中一般不能执行异步操作，随着onReceive方法到的结束，广播对象被销毁
		//如果确实像执行，可以采用goAsync()
		final PendingResult result = goAsync();
//		wl.acquire();
		AsyncHandler.post(new Runnable() {
			@Override
			public void run() {
//				handleIntent(context, intent);//耗时操作
				result.finish();
			}
		});
	}

}
//广播执行异步操作测试
 final class AsyncHandler {
	private static final HandlerThread sHandlerThread = new HandlerThread("AsyncHandler");
	private static final Handler sHandler;

	static {
		sHandlerThread.start();
		sHandler = new Handler(sHandlerThread.getLooper());
	}

	public static void post(Runnable r) {
		sHandler.post(r);
	}

	private AsyncHandler() {}
}