package cn.yinxm.tts;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import cn.yinxm.lib.utils.LogUtil;
import cn.yinxm.lib.vcr.TTSManager;
import cn.yinxm.lib.vcr.domain.MessageList;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btn_startTts, btn_addData, btn_getInfo, btn_stop, btn_pause_resume;
    TextView tv;
//    TextToSpeech tts;

    int num;
    final String[] datas = {"start test 1", "start test 2",
            "http://music.ecarx.com.cn/music/3288012.mp3?qcode=werd3819dkeK",
            "start test 测试数据3", "start test 测试数据4"};


    int ttsState;

    boolean isPaused = false;

    HashMap<String, String> myHash = new HashMap<String, String>();

    TTSManager ttsManager;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_startTts = (Button) findViewById(R.id.btn_startTts);
        btn_addData = (Button) findViewById(R.id.btn_addData);
        btn_getInfo = (Button) findViewById(R.id.btn_getInfo);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_pause_resume = (Button) findViewById(R.id.btn_pause_resume);
        tv = (TextView) findViewById(R.id.tv);

        btn_startTts.setOnClickListener(this);
        btn_addData.setOnClickListener(this);
        btn_getInfo.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_pause_resume.setOnClickListener(this);

//        bindService(new Intent(this, TextToSpeechService.class), new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                LogUtil.d("onServiceConnected service=" + service + ", name=" + name);
//
//                if (service != null && service instanceof TextToSpeechService) {
//                    TextToSpeechService speechService = (TextToSpeechService) service;
//                }
//
//            }
//
//            @Override

//            public void onServiceDisconnected(ComponentName name) {
//                LogUtil.d("onServiceDisconnected name=" + name);
//
//            }
//        }, Context.BIND_AUTO_CREATE);

        ttsManager = new TTSManager(getApplicationContext(), new TTSManager.TTSInitCallback() {
            @Override
            public void finish(int code) {
                if (code == TTSManager.CODE_SUCCESS ||
                        code == TTSManager.CODE_INIT_LANGUAGE_ENGLISH) {
                    for (int i = 0; i < datas.length; i++) {
                        String str = datas[i];
                        LogUtil.d("str=" + str);
                        MessageList.TTSMessage msg = new MessageList.TTSMessage();
                        if (i < 1) {
                            msg.setMessage("");

                        } else {
                            msg.setMessage(str);
                        }
//                        msg.setMessage("坚挺爱十分aweohhasd阿斯顿发hi啊!！@#%……&*（）——+？“。");
                        msg.setMsgQueueId("A");
                        msg.setMsgId("" + (i + 1));
                        if (i==2) {
                            msg.setMsgType(MessageList.MsgType.AUDIO);
                        }

                        ttsManager.addMessage(msg);
                        if (!ttsManager.isRuningTTS()) {
                            LogUtil.e("启动TTS");
                            ttsManager.startTTS();
                        }
                    }
//                    if (!ttsManager.isRuningTTS()) {
//                        LogUtil.e("启动TTS");
//                        ttsManager.startTTS();
//                    }

                }
            }
        });

        ttsManager.setFinishCallback(new TTSManager.TTSFinishCallback() {
            @Override
            public void finish(int code, TTSManager.TTSFinishBean finishBean) {
                LogUtil.d("finish="+code+", finishBean="+finishBean);
                if (code == TTSManager.CODE_SUCCESS && finishBean != null) {
                    LogUtil.d("1");
                    if (finishBean.isQueueFinished && !finishBean.isAllFinished) {
                        LogUtil.d("2");
                        Looper.prepare();
                        Toast.makeText(MainActivity.this, "你有新消息，是否朗读", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                        ttsManager.startTTSNow(new MessageList.TTSMessage("您有新消息，是否朗读? you have new message, read aloud ?"), 800);
//                        ttsManager.startTTSNow(new MessageList.TTSMessage("read"), 2000);
                        ttsManager.startTTS();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        LogUtil.d("view=" + view);
        switch (view.getId()) {
            case R.id.btn_startTts:
                try {
//                speakSpeech("this is 1 test. this is 2 test. this is 3 test");
//                tts.synthesizeToFile(editText.getText().toString(), null,   "/mnt/sdcard/sound.wav");语音文件
//                for (String str : datas) {
//                    LogUtil.d("str="+str);
//                    myHash.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, str);
//                    tts.speak(str, TextToSpeech.QUEUE_ADD, myHash);
//                }
                    LogUtil.e("click1");
                    ttsManager.startTTS();
                    LogUtil.e("click2");
                } catch (Exception e) {
                    LogUtil.e("btn_startTts异常", e);
                }

                break;
            case R.id.btn_addData:
                String str = "new start test " + num++;
//                LogUtil.d(str);
//                tts.speak(str, TextToSpeech.QUEUE_ADD, myHash);
//                tts.addSpeech("test", "test");
                MessageList.TTSMessage msg = new MessageList.TTSMessage();
                msg.setMessage(str);
                msg.setMsgQueueId("B");
                msg.setMsgId("" + (num));
                ttsManager.addMessage(msg);
                LogUtil.e("click3");
                break;
            case R.id.btn_stop:
//                tts.stop();
                break;
            case R.id.btn_pause_resume:
//                ttsPauseResume();
                if (ttsManager.isPaused()) {
                    ttsManager.resumeTts();
                } else {
                    ttsManager.pauseTtsAndClear();
                }
                break;
            case R.id.btn_getInfo:

                tv.setText(ttsManager.debugInfo());
                break;
        }
    }

    private void ttsPauseResume() {
        LogUtil.d("isPaused=" + isPaused);
        if (!isPaused) {
            btn_pause_resume.setText("恢复");
            isPaused = true;
//            new Thread(pauseThread).start();
//            tts.playSilence(5000, TextToSpeech.QUEUE_ADD, null);//手动加入时间间隔
        } else {
            isPaused = false;
            btn_pause_resume.setText("暂停");
        }


    }

//    public void speakSpeech(String speech) {
//        HashMap<String, String> myHash = new HashMap<String, String>();
//        myHash.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "done");
//        String[] splitspeech = speech.split("\\.");
//        for (int i = 0; i < splitspeech.length; i++) {
//            LogUtil.d("i="+splitspeech[i].toString().trim());
//            if (i == 0) { // Use for the first splited text to flush on audio stream
//                tts.speak(splitspeech[i].toString().trim(), TextToSpeech.QUEUE_FLUSH, myHash);
//            } else{ // add the new test on previous then play the TTS
//                tts.speak(splitspeech[i].toString().trim(), TextToSpeech.QUEUE_ADD, myHash);
//            }
//            tts.playSilence(5000, TextToSpeech.QUEUE_ADD, null);//手动加入时间间隔，没法后面再插入
//        }
//    }

}
