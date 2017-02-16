package cn.yinxm.lib.vcr.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yinxm on 2017/2/14.
 * 功能: TTS消息列表
 */

public class MessageList {
    public String msgQueueId;

    public ArrayList<TTSMessage> msgList = new ArrayList<>();

    public String getMsgQueueId() {
        return msgQueueId;
    }

    public void setMsgQueueId(String msgQueueId) {
        this.msgQueueId = msgQueueId;
    }

    public ArrayList<TTSMessage> getMsgList() {
        return msgList;
    }

    public void addMessage(TTSMessage msg) {
        if (msg != null && msg.getMsgQueueId() != null) {
            if (msgList == null) {
                msgList = new ArrayList<>();
            }
            msgList.add(msg);
        }
    }

    public void setMsgList(ArrayList<TTSMessage> msgList) {
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

        public TTSMessage(){}
        public TTSMessage(String msg){
            this.message = msg;
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

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TTSMessage{");
            sb.append("message='").append(message).append('\'');
            sb.append(", msgId='").append(msgId).append('\'');
            sb.append(", msgQueueId='").append(msgQueueId).append('\'');
            sb.append(", isSpeech=").append(isSpeech);
            sb.append('}');
            return sb.toString();
        }
    }
}
