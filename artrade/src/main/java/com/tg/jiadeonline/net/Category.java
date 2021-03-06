package com.tg.jiadeonline.net;

import android.content.Context;
import android.util.Log;
import com.tg.jiadeonline.bean.CategoryBean;
import com.tg.jiadeonline.date.CategoryManager;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class Category extends JiaDeNetRequestTask
{
  Context mContext;
  Map<String, Object> parmas = new HashMap();

  public Category(JiaDeNetRequestTask.JiaDeNetCallback paramJiaDeNetCallback, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    super(paramJiaDeNetCallback);
    this.parmas.put("imei", paramString1);
    this.parmas.put("userId", paramString2);
    this.parmas.put("pcateId", paramString3);
    this.parmas.put("pnum", paramString4);
    this.parmas.put("pqty", paramString5);
    this.parmas.put("porder", paramString6);
    this.mContext = paramContext;
  }

  public JiaDeNetRequest getRequest()
  {
    return new JiaDeNetRequest(BASE_URL + "category", "category", false, this.parmas, this.mContext);
  }

  protected Object parseResponse(String paramString, Context paramContext)
    throws Exception
  {
    Log.d("测试", paramString);
    JSONObject localJSONObject1 = (JSONObject)new JSONObject(paramString).get("OutputData");
    String str1 = localJSONObject1.getString("Success");
    String str2 = localJSONObject1.getString("ErrorInfo");
    localJSONObject1.getString("totPgs");
    localJSONObject1.getString("totRecs");
    String str3 = localJSONObject1.getString("sysTime");
    CategoryManager localCategoryManager = new CategoryManager(paramContext);
    localCategoryManager.openDataBase();
    localCategoryManager.deleteAllDatas();
    if ((str1.equals("true")) && ((str2 == null) || (str2.equals(""))))
    {
      JSONArray localJSONArray = localJSONObject1.getJSONArray("Data");
      int i = 0;
      if (i >= localJSONArray.length())
      {
        localCategoryManager.closeDataBase();
        return "ok";
      }
      JSONObject localJSONObject2 = localJSONArray.getJSONObject(i);
      String str4 = localJSONObject2.getString("catId");
      String str5 = localJSONObject2.getString("catName");
      String str6 = "";
      if (localJSONObject2.has("cpicpath"))
        str6 = localJSONObject2.getString("cpicpath");
      String str7 = localJSONObject2.getString("cdlSTime");
      String str8 = "";
      if (localJSONObject2.has("cdlETime"))
        str8 = localJSONObject2.getString("cdlETime");
      String str9 = localJSONObject2.getString("isDisp");
      CategoryBean localCategoryBean = new CategoryBean();
      if (str4 == null)
        str4 = "";
      localCategoryBean.setCatId(str4);
      if (str5 == null)
        str5 = "";
      localCategoryBean.setCatName(str5);
      if (str6 == null)
        str6 = "";
      localCategoryBean.setCpicpath(str6);
      if (str7 == null)
        str7 = "";
      localCategoryBean.setCdlSTime(str7);
      if (str8 == null)
        str8 = "";
      localCategoryBean.setCdlETime(str8);
      String str10;
      if (str9 == null)
      {
        str10 = "";
        label333: localCategoryBean.setIsDisp(str10);
        if (str3 != null)
          break label387;
      }
      label387: for (String str11 = ""; ; str11 = str3)
      {
        localCategoryBean.setSysTime(str11);
        if ("Y".equals(str9))
          localCategoryManager.insertData(localCategoryBean);
        i++;
        break;
        str10 = str9;
        break label333;
      }
    }
    localCategoryManager.closeDataBase();
    return str2;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.tg.jiadeonline.net.Category
 * JD-Core Version:    0.6.2
 */