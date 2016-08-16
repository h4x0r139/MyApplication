/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.testkey;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * Receives broadcasted intents. In particular, we are interested in the
 * android.media.AUDIO_BECOMING_NOISY and android.intent.action.MEDIA_BUTTON
 * intents, which is broadcast, for example, when the user disconnects the
 * headphones. This class works because we are declaring it in a
 * &lt;receiver&gt; tag in AndroidManifest.xml.
 */
@SuppressLint("InlinedApi")
public class MusicIntentReceiver extends BroadcastReceiver {
	private static final String LOG_TAG = "yika";
	private Context mContext;
	private KeyService mKeyService;

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Log.i(LOG_TAG, "onReceive action="+intent.getAction());
		} catch (Exception e) {
			Log.i(LOG_TAG, "onReceive 异常");
		}

		mContext = context;
		mKeyService = new KeyService(mContext);
//		if (intent.hasExtra(Intent.EXTRA_KEY_EVENT)) {
//			KeyEvent keyEvent0 = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
//			Log.i(LOG_TAG, "onReceive keyEvent0.getAction()="+keyEvent0.getAction()+", keyEventCode="+keyEvent0.getKeyCode());
//		}
//		try {
//			KeyEvent keyEvent0 = (KeyEvent) intent.getExtras().get(Intent.EXTRA_KEY_EVENT);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		if (intent.getAction().equals(
				android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
			Toast.makeText(context, "Headphones disconnected.",
					Toast.LENGTH_SHORT).show();

			// send an intent to our MusicService to telling it to pause the
			// audio
			// context.startService(new Intent(MusicService.ACTION_PAUSE));

		} else if (intent.getAction().equals(Intent.ACTION_MEDIA_BUTTON)) {
			Log.i(LOG_TAG, "ACTION_MEDIA_BUTTON!");
			KeyEvent keyEvent = (KeyEvent) intent.getExtras().get(
					Intent.EXTRA_KEY_EVENT);
			Log.i(LOG_TAG, "action="+keyEvent.getAction()+", keyCode="+keyEvent.getKeyCode());
			if (keyEvent.getAction() != KeyEvent.ACTION_DOWN)
				return;

			switch (keyEvent.getKeyCode()) {
			case KeyEvent.KEYCODE_HEADSETHOOK:
			case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
				// context.startService(new
				// Intent(MusicService.ACTION_TOGGLE_PLAYBACK));
				break;
			case KeyEvent.KEYCODE_MEDIA_PLAY:
				// context.startService(new Intent(MusicService.ACTION_PLAY));
				break;
			case KeyEvent.KEYCODE_MEDIA_PAUSE:
				// context.startService(new Intent(MusicService.ACTION_PAUSE));
				break;
			case KeyEvent.KEYCODE_MEDIA_STOP:
				// context.startService(new Intent(MusicService.ACTION_STOP));
				break;
			case KeyEvent.KEYCODE_MEDIA_NEXT:
				// context.startService(new Intent(MusicService.ACTION_SKIP));
				break;
			case KeyEvent.KEYCODE_MEDIA_PREVIOUS:
				// TODO: ensure that doing this in rapid succession actually
				// plays the
				// previous song
				// context.startService(new Intent(MusicService.ACTION_REWIND));
				break;
			}
		} else if (intent.getAction().equals(
				BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)) {
			Log.i(LOG_TAG, "BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED");

			int state = intent.getIntExtra(
					BluetoothAdapter.EXTRA_CONNECTION_STATE, 0);

			if (state == BluetoothAdapter.STATE_CONNECTED
					|| state == BluetoothAdapter.STATE_DISCONNECTED) {
				updateTime();
			}
		} else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Log.i(LOG_TAG, "Intent.ACTION_BOOT_COMPLETED");
			updateTime();
		} else if (intent.getAction().equals("android.intent.action.UPDATE_SUSPEND_TIME_BY_HAND")) {
			Log.i(LOG_TAG, "Intent.UPDATE_SUSPEND_TIME_BY_HAND");
			updateTime();
		} else {
			Log.i(LOG_TAG, "other intent");
		}
	}

	void updateTime() {
		Log.i(LOG_TAG, "updateTime");
		if(MainActivity.isEnableDSuspend()) {
			if(mKeyService.isBtConect())
				mKeyService.setScreenOffTime(MainActivity.getTime()[0]*1000); // def secs
			else
				mKeyService.setScreenOffTime(MainActivity.getTime()[1]*1000); // def secs
		} else {
			mKeyService.setScreenOffTime(MainActivity.getTime()[2]*1000); // def secs
		}
	}
}
