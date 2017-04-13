package cn.yinxm.lib.vcr;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import cn.yinxm.lib.media.player.IPlayHelper;
import cn.yinxm.lib.media.player.OnPlayFinishCallback;
import cn.yinxm.lib.media.player.media.MediaPlayHelper;
import cn.yinxm.lib.utils.LogUtil;
import cn.yinxm.lib.utils.NetworkUtil;
import cn.yinxm.lib.utils.StringUtil;
import cn.yinxm.lib.vcr.domain.MessageList;

/**
 * Created by yinxm on 2017/2/14.
 * 功能: TTS 朗读管理器
 */
public class TTSManager {
    public static final int CODE_SUCCESS = 0;//设置为英语是也返回成功
    public static final int CODE_FAIL = 1;
    public static final int CODE_INIT_LANGUAGE_ENGLISH = 2;

    public static final int READ_STATUS_STOP = 0;
    public static final int READ_STATUS_READING = 1;
    public static final int READ_STATUS_PAUSED = 2;


    private static final String QUEUE_DONE_FLAG = "queue_done_flag";
    private static final String TAG_QUEUEID_LINK_MSGID = "_###_";//queueId和msgId连接分隔符
    private Context context;
    //等待朗读的消息队列
    private LinkedBlockingQueue<MessageList> msgQueueToTts = new LinkedBlockingQueue<>();
    private TextToSpeech tts;
    private IPlayHelper playHelper;
    private HashMap<String, String> ttsParam = new HashMap<String, String>();
    //回调监听
    private TTSInitCallback initCallback;
    private TTSFinishCallback finishCallback;

    private ExecutorService executorService;
    private boolean isInitSuccess = false;
    /**
     * 一条消息朗读完毕
     */
    private boolean isOneFinish = false;

    /**
     * 正在朗读的消息
     */
    private  MessageList.TTSMessage readingMessage;
    /**
     * 正在朗读消息类型
     */
    private int readingMsgType;

    /**
     * 朗读状态
     */
    private int readStatus;

    public MessageList.TTSMessage getReadingMessage() {
        return readingMessage;
    }

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
     *
     * @param ttsMessage
     */
    public void addMessage(MessageList.TTSMessage ttsMessage) {
        LogUtil.d("addMessage size=" + msgQueueToTts.size() + ",  ttsMessage=" + ttsMessage);

        if (ttsMessage != null && ttsMessage.getMessage() != null) {//message='��' 消息内容是乱码，将会引朗读引擎后续无法使用
            boolean isFindQueue = false;
            for (MessageList msgList : msgQueueToTts) {
                //找到消息队列中是否有同样的人发来消息
                if (msgList != null && ttsMessage.getMsgQueueId().equals(msgList.getMsgQueueId())) {
                    isFindQueue = true;
                    LinkedList<MessageList.TTSMessage> list = msgList.getMsgList();
                    if (list == null) {
                        list = new LinkedList<>();
                    }
                    list.add(ttsMessage);
                    msgList.setMsgList(list);
                    break;
                }
            }
            LogUtil.d("isFindQueue=" + isFindQueue);
            //没有相同人发来的消息
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
     *
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
     *
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
     * 获取下一条消息记录，从消息队列中移除
     *
     * @return
     */
    public MessageList.TTSMessage takeNextMsg() {
        MessageList.TTSMessage ttsMessage = null;//消息
        //如果消息队列不为空，一直循环迭代，知道取出有效消息为止
        while (!msgQueueToTts.isEmpty()) {
            try {
                //取出一个消息列表
                MessageList messageList = msgQueueToTts.peek();
                if (messageList != null) {
                    LinkedList<MessageList.TTSMessage> list = messageList.getMsgList();
                    if (list != null && !list.isEmpty()) {
                        //取出消息列表中一条数据
                        ttsMessage = list.poll();
                        LogUtil.d("list.poll ttsMessage="+ttsMessage);
                        if (ttsMessage != null) {
                            break;
                        }
                    } else {
                        //这个人的消息记录已经为空
                        LogUtil.d("这个人的消息记录已经为空");
                        msgQueueToTts.take();
                    }
                } else {
                    LogUtil.d("消息队列头为空");
                    msgQueueToTts.take();
                }
            } catch (Exception e) {
                LogUtil.e("getNextMsgQueue异常", e);
            }
        }
        LogUtil.d("ttsMessage=" + ttsMessage);
        return ttsMessage;
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
                    playHelper = new MediaPlayHelper();

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
                        tts.setOnUtteranceProgressListener(textReadFinishCallback);
                    } else {
                        tts.setOnUtteranceCompletedListener(textReadFinishCallbackForLowerApi);
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
        LogUtil.d("startTTS start "+", threadId="+Thread.currentThread().getId());
        try {
            LogUtil.d("isShutdown=" + executorService.isShutdown() + ", isTerminated=" + executorService.isTerminated()
                    + ", msgQueueToTts=" + msgQueueToTts);
            if (isInitSuccess() && !isRuningTTS()) {
                executorService.submit(ttsRun);
            }

        } catch (Exception e) {
            LogUtil.e("startTTS 异常");
        }
        LogUtil.d("startTTS end");
    }

    /**
     * 立即朗读消息，会清空正在朗读的消息列表
     *
     * @param ttsMessage
     */
    public void startTTSNow(MessageList.TTSMessage ttsMessage) {
        startTTSNow(ttsMessage, 0);
    }

    /**
     * 间隔afterDurationInMs后立即朗读消息，会清空正在朗读的消息列表
     *
     * @param ttsMessage
     * @param afterDurationInMs
     */
    public void startTTSNow(MessageList.TTSMessage ttsMessage, long afterDurationInMs) {
        LogUtil.d("startTTSNow start");
        if (ttsMessage == null || ttsMessage.getMessage() == null) {
            LogUtil.e("消息不合法，无法朗读 ttsMessage=" + ttsMessage);
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

    public int getReadingMsgType() {
        return readingMsgType;
    }

    /**
     * 是否是同一个人的消息
     * @return
     */
    public boolean isSameQueue(int newMsgType, String newMsgQueueId) {
        boolean flag = false;
        MessageList.TTSMessage ttsMsg =  getReadingMessage();
        if (ttsMsg != null) {
            if (newMsgType == ttsMsg.getMsgType() && StringUtil.isNotBlank(newMsgQueueId) && newMsgQueueId.equals(ttsMsg.getMsgQueueId())) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 组装朗读消息标识
     *
     * @param ttsMessage
     * @return
     */
    public String getUtteranceId(MessageList.TTSMessage ttsMessage) {
        String utteranceId = null;
        if (ttsMessage != null) {
            utteranceId = ttsMessage.getMsgQueueId() + TAG_QUEUEID_LINK_MSGID + ttsMessage.getMsgId();
        }
        LogUtil.d("getUtteranceId=" + utteranceId + ", ttsMessage=" + ttsMessage);
        return utteranceId;
    }

    public boolean isInitSuccess() {
        boolean flag = false;
        if (isInitSuccess && tts != null) {
            flag =  true;
        }
        LogUtil.d("isInitSuccess="+flag);
        return flag;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public boolean isRuningTTS() {
        boolean flag = false;
        if (isInitSuccess()) {
            LogUtil.d("isRuningTTS getReadStatus="+getReadStatus()+", isSpeaking="+tts.isSpeaking()+", getReadingMsgType="+getReadingMsgType());
            if (getReadStatus() == READ_STATUS_READING && (getReadingMsgType()==MessageList.MsgType.TEXT && tts.isSpeaking() || getReadingMsgType()!=MessageList.MsgType.TEXT)
                    || getReadStatus() == READ_STATUS_PAUSED) {
                // 正在朗读并且是文字正在说话或者不是文字，或者已经暂停
                flag = true;
            }
        }

        LogUtil.d("isRuningTTS="+flag);
        return flag;

    }

    public boolean isPaused() {
        if (getReadStatus() == READ_STATUS_PAUSED) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 暂停并且清空当前播报队列
     */
    public void pauseTtsAndClear() {
        LogUtil.d("pauseTtsAndClear getReadStatus="+getReadStatus()+", isPaused="+isPaused());
        if (getReadStatus() == READ_STATUS_READING) {//正在运行
             if (getReadingMsgType() == MessageList.MsgType.AUDIO && playHelper != null) {
                 playHelper.stop();
             } else if (getReadingMsgType() == MessageList.MsgType.TEXT && tts != null) {
                 tts.stop();
             }
            msgQueueToTts.clear();
            //reset data
            isOneFinish = false;
            readingMessage = null;
            readingMsgType = -1;
            //修改状态
            readStatus = READ_STATUS_PAUSED;
        }
    }

    public void resumeTts() {
        LogUtil.d("resumeTts getReadStatus="+getReadStatus()+", isPaused="+isPaused()+", isQueueEmpty="+isQueueEmpty());
        if (isPaused() && !isQueueEmpty()) {//已被暂停
            //修改状态
            readStatus = READ_STATUS_STOP;
            startTTS();
        }
    }


    public Runnable ttsRun = new Runnable() {
        @Override
        public void run() {
            LogUtil.d("runnable start"+", threadId="+Thread.currentThread().getId());
            try {
                readMsgQueue();
            } catch (Exception e) {
                LogUtil.e("ttsRun 异常",e);
            }
            LogUtil.d("runnable end");

        }
    };


    /**
     * 开始朗读队列
     */
    private void readMsgQueue() {
        LogUtil.d("readMsgQueue"+", threadId="+Thread.currentThread().getId()+", isOneFinish="+isOneFinish+", getReadStatus="+getReadStatus());
        try {
            if (!isRuningTTS()|| isOneFinish) {//朗读没有开始，朗读一条结束 && 不是暂停中——》开始下一条朗读
                isOneFinish = false;
                MessageList.TTSMessage ttsMessage =   takeNextMsg();
                if (ttsMessage != null) {
                    readOneMsg(ttsMessage);
                } else {
                    readStatus = READ_STATUS_STOP;
                    readingMessage = null;
                    readingMsgType = -1;
                    LogUtil.e("所有消息朗读完毕");
                }
            }
        }catch (Exception e) {
            LogUtil.e(e);
        }

    }

    private void readOneMsg(MessageList.TTSMessage ttsMessage) {
        try {
            if (ttsMessage != null) {
                LogUtil.d("readOneMsg"+", threadId="+Thread.currentThread().getId()+", ttsMessage="+ttsMessage);
                readStatus = READ_STATUS_READING;
                readingMessage = ttsMessage;
                if ( MessageList.MsgType.TEXT == ttsMessage.getMsgType()) {
                    readingMsgType = MessageList.MsgType.TEXT;
                    ttsParam.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, getUtteranceId(ttsMessage));
                    tts.speak(ttsMessage.getMessage(), TextToSpeech.QUEUE_ADD, ttsParam);
                } else if (MessageList.MsgType.AUDIO == ttsMessage.getMsgType() && NetworkUtil.isNetworkConnected(context)){
                    readingMsgType = MessageList.MsgType.AUDIO;
                    playHelper.play(ttsMessage.getMessage(), audioPlayFinishCallback);
                } else {
                    //由于异常，抛弃当前消息，直接进行下一条朗读
                    LogUtil.e("read next");
                    readMsgQueue();
                }
            }
        }catch (Exception e) {
            LogUtil.e(e);
        }

    }


    /**
     * 语音消息朗读完毕回调
     */
    private OnPlayFinishCallback audioPlayFinishCallback = new OnPlayFinishCallback() {
        @Override
        public void onPlayfinish(int code) {
            LogUtil.d("onPlayfinish code="+code+", threadId="+Thread.currentThread().getId());

            //朗读完毕，继续执行
            oneMsgFinishCallback.finish(CODE_SUCCESS);
        }
    };



    //api15以上使用
    @SuppressWarnings("all")
    private UtteranceProgressListener textReadFinishCallback = new UtteranceProgressListener() {
        @Override
        public void onStart(String utteranceId) {
            LogUtil.d("onStart utteranceId=" + utteranceId);
        }

        @Override
        public void onDone(String utteranceId) {
            LogUtil.d("onDone utteranceId=" + utteranceId);
            //朗读完毕，继续执行
            oneMsgFinishCallback.finish(CODE_SUCCESS);
            /* try {
                boolean isQueueFinished = false;
                boolean isAllFinished = false;

                if (utteranceId != null && utteranceId.startsWith(QUEUE_DONE_FLAG)) {
                    LogUtil.e("本次序列已经完成");
                    isRuningTTS = false;
                    isQueueFinished = true;
                    if (msgQueueToTts.size() == 0) {
                        isAllFinished = true;
                    }
                    utteranceId = utteranceId.replaceFirst(QUEUE_DONE_FLAG, "");
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
                LogUtil.e("TTS onDone异常", e);
            }*/
        }

        @Override
        public void onError(String utteranceId) {
            LogUtil.d("onError utteranceId=" + utteranceId);
            oneMsgFinishCallback.finish(CODE_FAIL);
        }
    };

    //api小于15使用
    private TextToSpeech.OnUtteranceCompletedListener textReadFinishCallbackForLowerApi = new TextToSpeech.OnUtteranceCompletedListener() {

        @Override
        public void onUtteranceCompleted(String utteranceId) {
            LogUtil.d("onUtteranceCompleted utteranceId=" + utteranceId+", threadId="+Thread.currentThread().getId());
            //朗读完毕，继续执行
            oneMsgFinishCallback.finish(CODE_SUCCESS);
        }
    };



    public static class TTSFinishBean implements Serializable {
        public String msgId;
        public String msgQueueId;
        //一条消息朗读完
        public boolean isMsgFinished;
        //一个人的消息朗读完
        public boolean isQueueFinished;
        //所有消息朗读完
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

    OneMsgFinishCallback oneMsgFinishCallback = new OneMsgFinishCallback(){

        @Override
        public void finish(int code) {
            isOneFinish = true;
            readMsgQueue();
        }
    };

    private interface OneMsgFinishCallback {
        void finish(int code);
    }

    public interface TTSInitCallback {
        void finish(int code);
    }

    public interface TTSFinishCallback {
        void finish(int code, TTSFinishBean finishBean);
    }

    public String debugInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("getDefaultEngine="+tts.getDefaultEngine()+"，getEngines=" +tts.getEngines()+", getLanguage="+tts.getLanguage()
                +"isSpeaking="+tts.isSpeaking()+", isRuningTTS="+isRuningTTS());
        return sb.toString();
    }
}
