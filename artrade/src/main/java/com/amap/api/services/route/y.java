package com.amap.api.services.route;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.b;
import com.amap.api.services.core.c;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class y extends t<w, WalkRouteResult>
{
  public y(w paramw, Proxy paramProxy)
  {
    super(paramw, paramProxy);
  }

  protected WalkRouteResult a(InputStream paramInputStream)
    throws AMapException
  {
    String str = c.b(paramInputStream);
    WalkRouteResult localWalkRouteResult;
    if ((str == null) || (str.equals("")))
      localWalkRouteResult = null;
    while (true)
    {
      return localWalkRouteResult;
      c.b(str);
      try
      {
        JSONObject localJSONObject1 = new JSONObject(str);
        if (!localJSONObject1.has("route"))
          return null;
        localWalkRouteResult = new WalkRouteResult();
        try
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("route");
          localWalkRouteResult.setStartPos(b(localJSONObject2, "origin"));
          localWalkRouteResult.setTargetPos(b(localJSONObject2, "destination"));
          if (localJSONObject2.has("paths"))
          {
            JSONArray localJSONArray1 = localJSONObject2.getJSONArray("paths");
            ArrayList localArrayList1 = new ArrayList();
            for (int i = 0; i < localJSONArray1.length(); i++)
            {
              WalkPath localWalkPath = new WalkPath();
              JSONObject localJSONObject3 = localJSONArray1.getJSONObject(i);
              localWalkPath.setDistance(g(a(localJSONObject3, "distance")));
              localWalkPath.setDuration(h(a(localJSONObject3, "duration")));
              if (localJSONObject3.has("steps"))
              {
                JSONArray localJSONArray2 = localJSONObject3.getJSONArray("steps");
                ArrayList localArrayList2 = new ArrayList();
                for (int j = 0; j < localJSONArray2.length(); j++)
                {
                  WalkStep localWalkStep = new WalkStep();
                  JSONObject localJSONObject4 = localJSONArray2.getJSONObject(j);
                  localWalkStep.setInstruction(a(localJSONObject4, "instruction"));
                  localWalkStep.setOrientation(a(localJSONObject4, "orientation"));
                  localWalkStep.setRoad(a(localJSONObject4, "road"));
                  localWalkStep.setDistance(g(a(localJSONObject4, "distance")));
                  localWalkStep.setDuration(g(a(localJSONObject4, "duration")));
                  localWalkStep.setPolyline(c(localJSONObject4, "polyline"));
                  localWalkStep.setAction(a(localJSONObject4, "action"));
                  localWalkStep.setAssistantAction(a(localJSONObject4, "assistant_action"));
                  localArrayList2.add(localWalkStep);
                }
                localWalkPath.setSteps(localArrayList2);
              }
              localArrayList1.add(localWalkPath);
            }
            localWalkRouteResult.setPaths(localArrayList1);
            return localWalkRouteResult;
          }
        }
        catch (JSONException localJSONException2)
        {
          return localWalkRouteResult;
        }
      }
      catch (JSONException localJSONException1)
      {
      }
    }
    return null;
  }

  protected String a()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("key=").append(b.a);
    localStringBuffer.append("&origin=").append(c.a(((w)this.b).a.getFrom()));
    localStringBuffer.append("&destination=").append(c.a(((w)this.b).a.getTo()));
    localStringBuffer.append("&multipath=").append(((w)this.b).a());
    localStringBuffer.append("&output=json");
    return localStringBuffer.toString();
  }

  protected byte[] c()
  {
    return a().getBytes();
  }

  protected String d()
  {
    return "http://restapi.amap.com/v3/direction/walking?";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.y
 * JD-Core Version:    0.6.2
 */