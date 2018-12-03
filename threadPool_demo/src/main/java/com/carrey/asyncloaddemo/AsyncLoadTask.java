package com.carrey.asyncloaddemo;

import com.carrey.customview.customview.CustomView;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

public class AsyncLoadTask extends AsyncTask<Integer, Void, Pair<Integer, Bitmap>> {

	private static final String TAG = "AsyncLoadTask";
	
	/** 要刷新的view */
	private CustomView view;
		
	public AsyncLoadTask(CustomView view) {
		this.view = view;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
	
	@Override
	protected Pair<Integer, Bitmap> doInBackground(Integer... params) {
		int position = params[0];
		String imageUrl = ImageHelper.getImageUrl(MainActivity.webServerStr, position);
		Log.i(TAG, "AsyncLoad from NET :" + imageUrl);
		Bitmap bitmap = ImageHelper.loadBitmapFromNet(imageUrl);
		return new Pair<Integer, Bitmap>(position, bitmap);
	}
	
	@Override
	protected void onPostExecute(Pair<Integer, Bitmap> result) {
		if (result.first == view.position) {
			view.setImageBitmap(result.second);
		}
	}

}
