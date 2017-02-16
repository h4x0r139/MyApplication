package cn.yinxm.tts;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import cn.yinxm.lib.utils.LogUtil;
import cn.yinxm.lib.utils.StringUtil;


/**
 * Created by yinxm on 2017/2/14.
 * 功能: TTS 朗读管理器
 */
public class TTSManager {
    public static final int CODE_SUCCESS = 0;//设置为英语是也返回成功
    public static final int CODE_FAIL = 1;
    public static final int CODE_INIT_LANGUAGE_ENGLISH = 2;

    private static final String QUEUE_DONE_FLAG = "queue_done_flag";
    private static final String TAG_QUEUEID_LINK_MSGID = "_###_";//queueId和msgId连接分隔符
    private Context context;
    //等待朗读的消息队列
    private LinkedBlockingQueue<MessageList> msgQueueToTts = new LinkedBlockingQueue<>();
    private TextToSpeech tts;
    private HashMap<String, String> ttsParam = new HashMap<String, String>();
    //回调监听
    private TTSInitCallback initCallback;
    private TTSFinishCallback finishCallback;

    private ExecutorService executorService;
    private boolean isInitSuccess = false;
    private boolean isRuningTTS = false;
    private TTSFinishBean lastFinishBean;//最后一条朗读完成消息


    public TTSManager(Context context, TTSInitCallback initCallback) {
        this.context = context;
        if (initCallback != null) {
            this.initCallback = initCallback;
        }
        initTtsEngine();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void setFinishCallback(TTSFinishCallback finishCallback) {
        this.finishCallback = finishCallback;
    }

    /**
     * 往消息队列中添加一条消息，自动添加到指定的消息列表中
     * @param ttsMessage
     */
    public void addMessage(MessageList.TTSMessage ttsMessage) {
        LogUtil.d("addMessage size=" + msgQueueToTts.size() + ",  ttsMessage=" + ttsMessage);
        if (ttsMessage != null && ttsMessage.getMessage() != null) {
            boolean isFindQueue = false;
            for (MessageList msgList : msgQueueToTts) {
                if (msgList != null && ttsMessage.getMsgQueueId().equals(msgList.getMsgQueueId())) {
                    isFindQueue = true;
                    ArrayList<MessageList.TTSMessage> list = msgList.getMsgList();
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(ttsMessage);
                    msgList.setMsgList(list);
                    break;
                }
            }
            LogUtil.d("isFindQueue=" + isFindQueue);
            if (!isFindQueue) {
                MessageList msgList = new MessageList();
                msgList.setMsgQueueId(ttsMessage.getMsgQueueId());
                msgList.addMessage(ttsMessage);
                try {
                    msgQueueToTts.put(msgList);
                } catch (InterruptedException e) {
                    LogUtil.e("addMessage异常", e);
                }
            }
        }
        LogUtil.d("size=" + msgQueueToTts.size());
    }

    public void removeQueue(String queueId) {
        msgQueueToTts.remove(queueId);
    }

    public boolean isQueueEmpty() {
        return msgQueueToTts.isEmpty();
    }

    /**
     * 获取下一条消息列表记录，不从消息队列中移除
     * @return
     */
    public MessageList getNextMsgQueue() {
        MessageList messageList = null;
        if (!msgQueueToTts.isEmpty()) {
            try {
                messageList = msgQueueToTts.peek();
            } catch (Exception e) {
                LogUtil.e("getNextMsgQueue异常", e);
            }
        }
        LogUtil.d("getNextMsgQueue=" + messageList);
        return messageList;
    }

    /**
     * 消费下一条消息列表记录，从消息队列中移除
     * @return
     */
    private MessageList takeNextMsgQueue() {
        MessageList messageList = null;
        if (!msgQueueToTts.isEmpty()) {
            try {
                messageList = msgQueueToTts.take();
            } catch (InterruptedException e) {
                LogUtil.e("getNextMsgQueue异常", e);
            }
        }
        LogUtil.d("getNextMsgQueue=" + messageList);
        return messageList;
    }

    /**
     * tts 初始化
     */
    public void initTtsEngine() {
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(final int status) {
                LogUtil.d("status=" + status);
                // 如果装载TTS引擎成功
                if (status == TextToSpeech.SUCCESS) {
                    isInitSuccess = true;

                    int result = tts.setLanguage(Locale.CHINA);
                    LogUtil.d("result=" + result);
                    // 如果不支持所设置的语言
                    if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE
                            && result != TextToSpeech.LANG_AVAILABLE) {
                        Toast.makeText(context,
                                "TTS暂时不支持这种语言的朗读！", Toast.LENGTH_LONG).show();
                        LogUtil.d("default=" + Locale.getDefault());
                        // TODO: 2017/2/15
                        result = tts.setLanguage(Locale.US);
                        LogUtil.d("result=" + result);
                    } else {
//                        isInitSuccess = true;
                        if (initCallback != null) {

                        }
                    }
                    tts.setPitch(1.0f);// 设置音调,  值越大声音越尖（女生）,  值越小则变成男声,1.0是常规
                    tts.setSpeechRate(1.0f);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                        tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                            @Override
                            public void onStart(String utteranceId) {
                                LogUtil.d("onStart utteranceId=" + utteranceId);
                                isRuningTTS = true;
                            }

                            @Override
                            public void onDone(String utteranceId) {
                                LogUtil.d("onDone utteranceId=" + utteranceId);
                                try {
                                    boolean isQueueFinished = false;
                                    boolean isAllFinished = false;

                                    if (utteranceId != null && utteranceId.startsWith(QUEUE_DONE_FLAG)) {
                                        LogUtil.e("本次序列已经完成");
                                        isRuningTTS = false;
                                        isQueueFinished = true;
                                        if (msgQueueToTts.size() == 0) {
                                            isAllFinished = true;
                                        }
                                        utteranceId = utteranceId.replaceFirst(QUEUE_DONE_FLAG,"");
                                    }
                                    if (finishCallback != null) {
                                        TTSFinishBean bean = new TTSFinishBean();
                                        String msgId = null;
                                        String msgQueueId = null;
                                        if (utteranceId != null) {
                                            String[] str = utteranceId.split(TAG_QUEUEID_LINK_MSGID);
//                                            LogUtil.e("utteranceId="+utteranceId+", array="+ Arrays.toString(str));
                                            if (str != null && str.length > 0) {
                                                msgQueueId = str[0];
                                                if (str.length > 1) {
                                                    msgId = str[1];
                                                }
                                            }
                                        }
                                        bean.msgId = msgId;
                                        bean.msgQueueId = msgQueueId;
                                        bean.isQueueFinished = isQueueFinished;
                                        bean.isAllFinished = isAllFinished;
                                        //没有发送方的不记录成最后一条消息
                                        if (StringUtil.isNotBlank(msgQueueId)) {
                                            lastFinishBean = bean;
                                        }
                                        finishCallback.finish(CODE_SUCCESS, bean);
                                    }
                                } catch (Exception e) {
                                    LogUtil.e("TTS onDone异常" , e);
                                }
                            }

                            @Override
                            public void onError(String utteranceId) {
                                LogUtil.d("onError utteranceId=" + utteranceId);
                                isRuningTTS = false;
                            }
                        });
                    }

                    if (initCallback != null) {
                        if (isInitSuccess) {
                            initCallback.finish(CODE_SUCCESS);
                        } else {
                            initCallback.finish(CODE_INIT_LANGUAGE_ENGLISH);
                        }
                    }

                    LogUtil.d("getDefaultEngine=" + tts.getDefaultEngine() + "，getEngines=" + tts.getEngines() + ", getLanguage=" + tts.getLanguage());
                } else {
                    Toast.makeText(context,
                            "没有安装语音朗读引擎", Toast.LENGTH_SHORT).show();
                    if (initCallback != null) {
                        initCallback.finish(CODE_FAIL);
                    }
//                    Intent intent = new Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
                }
            }
        });

    }


    /**
     * 开始从消息队列中取出下一条消息列表，进行朗读
     */
    public void startTTS() {
        LogUtil.d("startTTS start");
        try {
            LogUtil.d("isShutdown=" + executorService.isShutdown() + ", isTerminated=" + executorService.isTerminated()
                    + ", msgQueueToTts=" + msgQueueToTts);
            executorService.submit(ttsRun);

        } catch (Exception e) {
            LogUtil.e("startTTS 异常");
        }
        LogUtil.d("startTTS end");
    }

    /**
     * 立即朗读消息，会清空正在朗读的消息列表
     * @param ttsMessage
     */
    public void startTTSNow(MessageList.TTSMessage ttsMessage) {
        startTTSNow(ttsMessage, 0);
    }

    /**
     * 间隔afterDurationInMs后立即朗读消息，会清空正在朗读的消息列表
     * @param ttsMessage
     * @param afterDurationInMs
     */
    public void startTTSNow(MessageList.TTSMessage ttsMessage, long afterDurationInMs) {
        LogUtil.d("startTTSNow start");
        if (ttsMessage == null || ttsMessage.getMessage() == null) {
            LogUtil.e("消息不合法，无法朗读 ttsMessage="+ttsMessage);
            return;
        }
        try {
            if (afterDurationInMs < 0) {
                afterDurationInMs = 0;
            }
            tts.playSilence(afterDurationInMs, TextToSpeech.QUEUE_FLUSH, null);
            ttsParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, getUtteranceId(ttsMessage));
            tts.speak(ttsMessage.getMessage(), TextToSpeech.QUEUE_ADD, ttsParam);
        } catch (Exception e) {
            LogUtil.e("startTTSNow 异常");
        }

        LogUtil.d("startTTSNow end");
    }

    /**
     * 组装朗读消息标识
     * @param ttsMessage
     * @return
     */
    public String getUtteranceId(MessageList.TTSMessage ttsMessage) {
        String utteranceId = null;
        if (ttsMessage != null) {
            utteranceId = ttsMessage.getMsgQueueId()+TAG_QUEUEID_LINK_MSGID+ttsMessage.getMsgId();
        }
        LogUtil.d("getUtteranceId="+utteranceId+", ttsMessage="+ttsMessage);
        return utteranceId;
    }

    public boolean isInitSuccess() {
        return isInitSuccess;
    }

    public boolean isRuningTTS() {
        return isRuningTTS;
    }

    public TTSFinishBean getLastFinishBean() {
        return lastFinishBean;
    }

    public Runnable ttsRun = new Runnable() {
        @Override
        public void run() {
            LogUtil.d("runnable start");
            try {
                MessageList msgList = takeNextMsgQueue();
                if (msgList != null
                        && msgList.getMsgList() != null && msgList.getMsgList().size() > 0) {
//                LogUtil.d("isInitSuccess="+isInitSuccess);
                    if (isInitSuccess && tts != null) {
                        ArrayList<MessageList.TTSMessage> list = msgList.getMsgList();
                        MessageList.TTSMessage ttsMessage = null;//当前消息
                        int i = 0;
                        while (list != null && (ttsMessage = list.get(i++)) != null) {
                            LogUtil.d("text=" + ttsMessage.getMessage());
                            MessageList.TTSMessage nextMsg = null;//下一条消息
                            if (i < list.size()) {
                                nextMsg = list.get(i);
                            }
                            if (nextMsg != null) {
                                ttsParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, getUtteranceId(ttsMessage));
                            } else {
                                ttsParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, QUEUE_DONE_FLAG+getUtteranceId(ttsMessage));
                            }
                            tts.speak(ttsMessage.getMessage(), TextToSpeech.QUEUE_ADD, ttsParam);
                            if (i >= list.size()) {
                                break;
                            }
                        }
                        LogUtil.d("while end");
                    }
                }

            } catch (Exception e) {
                LogUtil.e("ttsRun 异常");
            }
            LogUtil.d("runnable end");

        }
    };

    public static class TTSFinishBean implements Serializable{
        public String msgId;
        public String msgQueueId;
        public boolean isQueueFinished;
        public boolean isAllFinished;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TTSFinishBean{");
            sb.append("msgId='").append(msgId).append('\'');
            sb.append(", msgQueueId='").append(msgQueueId).append('\'');
            sb.append(", isQueueFinished=").append(isQueueFinished);
            sb.append(", isAllFinished=").append(isAllFinished);
            sb.append('}');
            return sb.toString();
        }
    }

    public interface TTSInitCallback {
        void finish(int code);
    }

    public interface TTSFinishCallback {
        void finish(int code, TTSFinishBean finishBean);
    }
}
