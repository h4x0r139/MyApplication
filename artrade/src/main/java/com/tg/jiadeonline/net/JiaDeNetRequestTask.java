package com.tg.jiadeonline.net;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JiaDeNetRequestTask extends AsyncTask<JiaDeNetRequest, Void, JiaDeNetResponse>
{
  public static String BASE_URL = "http://211.157.8.172:9001/";
  public final String JSONARRAYTAG = "JSONArray_";
  private JiaDeNetCallback callBack;
  String errorInfo = "网络好像不畅通，请再试一下";

  public JiaDeNetRequestTask(JiaDeNetCallback paramJiaDeNetCallback)
  {
    this.callBack = paramJiaDeNetCallback;
  }

  public JiaDeNetResponse callEx(String paramString)
  {
    new StringBuilder();
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpParams localHttpParams = localDefaultHttpClient.getParams();
    HttpConnectionParams.setConnectionTimeout(localHttpParams, 20000);
    HttpConnectionParams.setSoTimeout(localHttpParams, 20000);
    if (paramString != null)
    {
      HttpPost localHttpPost = new HttpPost(paramString);
      localHttpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
      Log.w("", "url=====================" + paramString);
      new ArrayList();
      UrlEncodedFormEntity localUrlEncodedFormEntity = getjsonresult(getRequest());
      try
      {
        localHttpPost.setEntity(localUrlEncodedFormEntity);
        JiaDeNetResponse localJiaDeNetResponse = new JiaDeNetResponse(parseResponse(getJsonData(EntityUtils.toString(localDefaultHttpClient.execute(localHttpPost).getEntity())), getRequest().mContext));
        return localJiaDeNetResponse;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.printStackTrace();
        return new JiaDeNetResponse(this.errorInfo);
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        while (true)
          localClientProtocolException.printStackTrace();
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        while (true)
          localConnectTimeoutException.printStackTrace();
      }
      catch (IOException localIOException)
      {
        while (true)
          localIOException.printStackTrace();
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
    return new JiaDeNetResponse(this.errorInfo);
  }

  protected JiaDeNetResponse doInBackground(JiaDeNetRequest[] paramArrayOfJiaDeNetRequest)
  {
    return callEx(getRequest().methodName);
  }

  public String getJsonData(String paramString)
  {
    System.out.println(paramString);
    int i;
    int j;
    if (!TextUtils.isEmpty(paramString))
    {
      i = paramString.indexOf("{");
      if (i != -1)
      {
        j = paramString.lastIndexOf("}");
        if (j != -1)
          break label41;
      }
    }
    return "";
    label41: return paramString.substring(i, j + 1);
  }

  public JiaDeNetRequest getRequest()
  {
    return null;
  }

  public UrlEncodedFormEntity getjsonresult(JiaDeNetRequest paramJiaDeNetRequest)
  {
    JSONArray localJSONArray1 = new JSONArray();
    JSONObject localJSONObject1;
    int i;
    Iterator localIterator;
    if (paramJiaDeNetRequest.getEntity() != null)
    {
      localJSONObject1 = new JSONObject();
      Map localMap = getRequest().getEntity();
      i = 0;
      localIterator = localMap.entrySet().iterator();
    }
    while (true)
    {
      JSONArray localJSONArray2;
      JSONObject localJSONObject2;
      JSONObject localJSONObject3;
      if (!localIterator.hasNext())
      {
        localJSONArray1.put(localJSONObject1);
        localJSONArray2 = new JSONArray();
        localJSONObject2 = new JSONObject();
        localJSONObject3 = new JSONObject();
      }
      try
      {
        localJSONObject2.put("Parameters", localJSONArray1);
        localJSONObject2.put("APIName", paramJiaDeNetRequest.APIName);
        localJSONArray2.put(localJSONObject2);
        localJSONObject3.put("InputData", localJSONArray2);
        BasicNameValuePair localBasicNameValuePair = new BasicNameValuePair("Parmeters", localJSONObject3.toString());
        localArrayList = new ArrayList();
        localArrayList.add(localBasicNameValuePair);
      }
      catch (JSONException localJSONException2)
      {
        try
        {
          ArrayList localArrayList;
          UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(localArrayList, "UTF-8");
          return localUrlEncodedFormEntity;
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          i++;
          String str = (String)localEntry.getKey();
          if (localEntry.getValue() == null);
          for (Object localObject = ""; ; localObject = localEntry.getValue())
          {
            try
            {
              if (!"itemData".equals(str))
                break label272;
              localJSONObject1.put(str, (JSONArray)localObject);
            }
            catch (JSONException localJSONException1)
            {
              localJSONException1.printStackTrace();
            }
            break;
          }
          label272: localJSONObject1.put(str, localObject);
          continue;
          localJSONException2 = localJSONException2;
          localJSONException2.printStackTrace();
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          localUnsupportedEncodingException.printStackTrace();
        }
      }
    }
    return null;
  }

  protected void onCancelled()
  {
    super.onCancelled();
    if (this.callBack != null)
      this.callBack.onCanceled();
  }

  protected void onPostExecute(JiaDeNetResponse paramJiaDeNetResponse)
  {
    if (this.callBack != null)
    {
      if (paramJiaDeNetResponse.hasError)
        this.callBack.onException(new JiaDeNetException(paramJiaDeNetResponse.errMsg));
    }
    else
      return;
    this.callBack.onFinished(paramJiaDeNetResponse);
  }

  public Map<String, String> parseEntityParmas(JiaDeNetRequest paramJiaDeNetRequest)
  {
    HashMap localHashMap = new HashMap();
    if (paramJiaDeNetRequest == null)
      return localHashMap;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("{");
    localStringBuffer.append("\"");
    localStringBuffer.append("InputData");
    localStringBuffer.append("\"");
    localStringBuffer.append(":");
    localStringBuffer.append("[{");
    localStringBuffer.append("\"");
    localStringBuffer.append("APIName");
    localStringBuffer.append("\"");
    localStringBuffer.append(":");
    localStringBuffer.append("\"");
    localStringBuffer.append(paramJiaDeNetRequest.APIName);
    localStringBuffer.append("\"");
    localStringBuffer.append(",");
    localStringBuffer.append("\"");
    localStringBuffer.append("Parameters");
    localStringBuffer.append("\"");
    localStringBuffer.append(":");
    localStringBuffer.append("[{");
    int j;
    Iterator localIterator;
    if (paramJiaDeNetRequest.getEntity() != null)
    {
      Map localMap = getRequest().getEntity();
      j = 0;
      localIterator = localMap.entrySet().iterator();
      if (localIterator.hasNext());
    }
    else
    {
      if ((paramJiaDeNetRequest.getEntity() == null) || (paramJiaDeNetRequest.getEntity().size() != 0))
        break label456;
    }
    label456: for (int i = localStringBuffer.length(); ; i = -1 + localStringBuffer.length())
    {
      localHashMap.put("Parmeters", localStringBuffer.substring(0, i).concat("}]}]}"));
      Log.w("post json===============", localStringBuffer.substring(0, -1 + localStringBuffer.length()).concat("}]}]}"));
      return localHashMap;
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = (String)localEntry.getKey();
      Object localObject = localEntry.getValue();
      if (j == 0)
      {
        localStringBuffer.append("\"");
        localStringBuffer.append(str);
        localStringBuffer.append("\"");
        localStringBuffer.append(":");
        localStringBuffer.append("\"");
        localStringBuffer.append(localObject.toString());
        localStringBuffer.append("\"");
      }
      while (true)
      {
        j++;
        break;
        localStringBuffer.append(",");
        localStringBuffer.append("\"");
        localStringBuffer.append(str);
        localStringBuffer.append("\"");
        localStringBuffer.append(":");
        localStringBuffer.append("\"");
        localStringBuffer.append(localObject.toString());
        localStringBuffer.append("\"");
      }
    }
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    return null;
  }

  public static abstract interface JiaDeNetCallback
  {
    public abstract void onCanceled();

    public abstract void onException(JiaDeNetException paramJiaDeNetException);

    public abstract void onFinished(JiaDeNetResponse paramJiaDeNetResponse);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.JiaDeNetRequestTask
 * JD-Core Version:    0.6.2
 */