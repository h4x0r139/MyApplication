package com.yinxm.serversocket_test;

import java.net.Socket;
import java.util.Vector;

/**
 * Created by H4X0R on 2016/3/6.
 */
public class ChatManager {//聊天管理器

    private ChatManager() {}
    public static final ChatManager chatManager = new ChatManager();
    public static ChatManager getChatManager() {
        return chatManager;
    }

    Vector<ChatSocket> vector = new Vector<ChatSocket>();
    public void addChatSocket(ChatSocket cs) {
            vector.add(cs);
    }

    public void publish(ChatSocket cs, String str) {
        for (ChatSocket chatSocket : vector) {
            if (!chatSocket.equals(cs)) {//向除自己外的所有客户端广播消息
                chatSocket.write(str);
            }
        }
    }
}
