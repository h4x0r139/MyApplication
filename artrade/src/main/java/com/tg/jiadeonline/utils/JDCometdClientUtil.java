package com.tg.jiadeonline.utils;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import java.io.IOException;
import org.cometd.bayeux.client.ClientSessionChannel;
import org.cometd.bayeux.client.ClientSessionChannel.MessageListener;
import org.cometd.client.BayeuxClient;
import org.cometd.client.BayeuxClient.State;
import org.cometd.client.transport.ClientTransport;
import org.cometd.client.transport.LongPollingTransport;

public class JDCometdClientUtil
{
  private String TAG = "JDCometdClientUtil";
  private volatile String channelid = "/auction/";
  private final ChatListener chatListener = new ChatListener(null);
  private volatile BayeuxClient client;
  private Handler lxhandler;

  public JDCometdClientUtil(String paramString, Handler paramHandler)
  {
    this.channelid += paramString;
    this.lxhandler = paramHandler;
  }

  private void connectionBroken()
  {
    Log.i(this.TAG, "system: Connection to Server Broken%n");
  }

  private void connectionEstablished()
  {
    Log.i(this.TAG, "system: Connection to Server Opened%n");
  }

  private void initialize()
  {
    this.client.batch(new Runnable()
    {
      public void run()
      {
        JDCometdClientUtil.this.client.getChannel(JDCometdClientUtil.this.channelid).subscribe(JDCometdClientUtil.this.chatListener);
      }
    });
  }

  public static void main(String[] paramArrayOfString)
    throws IOException
  {
  }

  public void connectionClosed()
  {
    if ((this.client != null) && (this.client.isConnected()))
    {
      this.client.disconnect();
      Log.i(this.TAG, "连接关闭成功!");
    }
    Log.i(this.TAG, "system: Connection to Server Closed%n");
  }

  public void run()
    throws IOException
  {
    this.client = new BayeuxClient("http://211.157.8.141:9002/auctionassist/cometd", LongPollingTransport.create(null), new ClientTransport[0]);
    this.client.getChannel("/meta/handshake").addListener(new InitializerListener(null));
    this.client.getChannel("/meta/connect").addListener(new ConnectionListener(null));
    this.client.handshake();
    if (!this.client.waitFor(2000L, BayeuxClient.State.CONNECTED, new BayeuxClient.State[0]));
  }

  private class ChatListener
    implements ClientSessionChannel.MessageListener
  {
    private ChatListener()
    {
    }

    public void onMessage(ClientSessionChannel paramClientSessionChannel, org.cometd.bayeux.Message paramMessage)
    {
      Log.i(JDCometdClientUtil.this.TAG, (String)paramMessage.getData());
      android.os.Message localMessage = new android.os.Message();
      Bundle localBundle = new Bundle();
      localBundle.putString("msg", (String)paramMessage.getData());
      localMessage.setData(localBundle);
      JDCometdClientUtil.this.lxhandler.sendMessage(localMessage);
    }
  }

  private class ConnectionListener
    implements ClientSessionChannel.MessageListener
  {
    private boolean connected;
    private boolean wasConnected;

    private ConnectionListener()
    {
    }

    public void onMessage(ClientSessionChannel paramClientSessionChannel, org.cometd.bayeux.Message paramMessage)
    {
      Log.i(JDCometdClientUtil.this.TAG, "get listener msg !");
      if (JDCometdClientUtil.this.client.isDisconnected())
      {
        this.connected = false;
        JDCometdClientUtil.this.connectionClosed();
      }
      do
      {
        return;
        this.wasConnected = this.connected;
        this.connected = paramMessage.isSuccessful();
        if ((!this.wasConnected) && (this.connected))
        {
          JDCometdClientUtil.this.connectionEstablished();
          return;
        }
      }
      while ((!this.wasConnected) || (this.connected));
      JDCometdClientUtil.this.connectionBroken();
    }
  }

  private class InitializerListener
    implements ClientSessionChannel.MessageListener
  {
    private InitializerListener()
    {
    }

    public void onMessage(ClientSessionChannel paramClientSessionChannel, org.cometd.bayeux.Message paramMessage)
    {
      if (paramMessage.isSuccessful())
        JDCometdClientUtil.this.initialize();
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.utils.JDCometdClientUtil
 * JD-Core Version:    0.6.2
 */