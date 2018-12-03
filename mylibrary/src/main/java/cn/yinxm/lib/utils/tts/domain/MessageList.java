package cn.yinxm.lib.utils.tts.domain;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by yinxm on 2017/2/14.
 * 功能: TTS消息列表
 */

public class MessageList {
    public String msgQueueId;

    public LinkedList<TTSMessage> msgList = new LinkedList<>();

    public String getMsgQueueId() {
        return msgQueueId;
    }

    public void setMsgQueueId(String msgQueueId) {
        this.msgQueueId = msgQueueId;
    }

    public LinkedList<TTSMessage> getMsgList() {
        return msgList;
    }

    public void addMessage(TTSMessage msg) {
        if (msg != null && msg.getMsgQueueId() != null) {
            if (msgList == null) {
                msgList = new LinkedList<>();
            }
            msgList.add(msg);
        }
    }

    public void setMsgList(LinkedList<TTSMessage> msgList) {
        this.msgList = msgList;
    }

    public static class TTSMessage implements Serializable {
        //消息
        String message;
        //消息id
        String msgId;
        //发送人id
        String msgQueueId;
        //是否朗读
        boolean isSpeech;
        //消息类型 MsgType
        int msgType;
        //发消息人名称
        String msgQueueName;

        public TTSMessage(){}
        public TTSMessage(String msg){
            this.message = msg;
        }

        public TTSMessage(String message, String msgId, String msgQueueId, int msgType, String msgQueueName) {
            this.message = message;
            this.msgId = msgId;
            this.msgQueueId = msgQueueId;
            this.msgType = msgType;
            this.msgQueueName = msgQueueName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public String getMsgQueueId() {
            return msgQueueId;
        }

        public void setMsgQueueId(String msgQueueId) {
            this.msgQueueId = msgQueueId;
        }

        public boolean isSpeech() {
            return isSpeech;
        }

        public void setSpeech(boolean speech) {
            isSpeech = speech;
        }

        public int getMsgType() {
            return msgType;
        }

        public void setMsgType(int msgType) {
            this.msgType = msgType;
        }

        public String getMsgQueueName() {
            return msgQueueName;
        }

        public void setMsgQueueName(String msgQueueName) {
            this.msgQueueName = msgQueueName;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TTSMessage{");
            sb.append("message='").append(message).append('\'');
            sb.append(", msgId='").append(msgId).append('\'');
            sb.append(", msgQueueId='").append(msgQueueId).append('\'');
            sb.append(", isSpeech=").append(isSpeech);
            sb.append(", msgType=").append(msgType);
            sb.append(", msgQueueName='").append(msgQueueName).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public static class MsgType {
        /**
         * 文本消息
         */
        public static final  int TEXT = 0;
        /**
         * 音频消息
         */
        public static final  int AUDIO = 1;
    }
}
