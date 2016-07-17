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

public class k extends t<i, DriveRouteResult>
{
  public k(i parami, Proxy paramProxy)
  {
    super(parami, paramProxy);
  }

  private void a(DriveStep paramDriveStep, JSONObject paramJSONObject)
  {
    if (!paramJSONObject.has("cities"))
      return;
    try
    {
      ArrayList localArrayList = new ArrayList();
      JSONArray localJSONArray = paramJSONObject.getJSONArray("cities");
      for (int i = 0; i < localJSONArray.length(); i++)
      {
        RouteSearchCity localRouteSearchCity = new RouteSearchCity();
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        localRouteSearchCity.setSearchCityName(a(localJSONObject, "name"));
        localRouteSearchCity.setSearchCitycode(a(localJSONObject, "citycode"));
        localRouteSearchCity.setSearchCityhAdCode(a(localJSONObject, "adcode"));
        a(localRouteSearchCity, localJSONObject);
        localArrayList.add(localRouteSearchCity);
      }
      paramDriveStep.setRouteSearchCityList(localArrayList);
      return;
    }
    catch (JSONException localJSONException)
    {
    }
  }

  private void a(RouteSearchCity paramRouteSearchCity, JSONObject paramJSONObject)
  {
    if (!paramJSONObject.has("districts"))
      return;
    try
    {
      ArrayList localArrayList = new ArrayList();
      JSONArray localJSONArray = paramJSONObject.getJSONArray("districts");
      for (int i = 0; i < localJSONArray.length(); i++)
      {
        District localDistrict = new District();
        JSONObject localJSONObject = localJSONArray.getJSONObject(i);
        localDistrict.setDistrictName(a(localJSONObject, "name"));
        localDistrict.setDistrictAdcode(a(localJSONObject, "adcode"));
        localArrayList.add(localDistrict);
      }
      paramRouteSearchCity.setDistricts(localArrayList);
      return;
    }
    catch (JSONException localJSONException)
    {
    }
  }

  protected DriveRouteResult a(InputStream paramInputStream)
    throws AMapException
  {
    String str = c.b(paramInputStream);
    DriveRouteResult localDriveRouteResult;
    if ((str == null) || (str.equals("")))
      localDriveRouteResult = null;
    while (true)
    {
      return localDriveRouteResult;
      c.b(str);
      try
      {
        JSONObject localJSONObject1 = new JSONObject(str);
        if (!localJSONObject1.has("route"))
          return null;
        localDriveRouteResult = new DriveRouteResult();
        try
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("route");
          localDriveRouteResult.setStartPos(b(localJSONObject2, "origin"));
          localDriveRouteResult.setTargetPos(b(localJSONObject2, "destination"));
          if (localJSONObject2.has("paths"))
          {
            JSONArray localJSONArray1 = localJSONObject2.getJSONArray("paths");
            ArrayList localArrayList1 = new ArrayList();
            for (int i = 0; i < localJSONArray1.length(); i++)
            {
              DrivePath localDrivePath = new DrivePath();
              JSONObject localJSONObject3 = localJSONArray1.getJSONObject(i);
              localDrivePath.setDistance(g(a(localJSONObject3, "distance")));
              localDrivePath.setDuration(h(a(localJSONObject3, "duration")));
              localDrivePath.setStrategy(a(localJSONObject3, "strategy"));
              localDrivePath.setTolls(g(a(localJSONObject3, "tolls")));
              localDrivePath.setTollDistance(g(a(localJSONObject3, "toll_distance")));
              if (localJSONObject3.has("steps"))
              {
                JSONArray localJSONArray2 = localJSONObject3.getJSONArray("steps");
                ArrayList localArrayList2 = new ArrayList();
                for (int j = 0; j < localJSONArray2.length(); j++)
                {
                  DriveStep localDriveStep = new DriveStep();
                  JSONObject localJSONObject4 = localJSONArray2.getJSONObject(j);
                  localDriveStep.setInstruction(a(localJSONObject4, "instruction"));
                  localDriveStep.setOrientation(a(localJSONObject4, "orientation"));
                  localDriveStep.setRoad(a(localJSONObject4, "road"));
                  localDriveStep.setDistance(g(a(localJSONObject4, "distance")));
                  localDriveStep.setTolls(g(a(localJSONObject4, "tolls")));
                  localDriveStep.setTollDistance(g(a(localJSONObject4, "toll_distance")));
                  localDriveStep.setTollRoad(a(localJSONObject4, "toll_road"));
                  localDriveStep.setDuration(g(a(localJSONObject4, "duration")));
                  localDriveStep.setPolyline(c(localJSONObject4, "polyline"));
                  localDriveStep.setAction(a(localJSONObject4, "action"));
                  localDriveStep.setAssistantAction(a(localJSONObject4, "assistant_action"));
                  a(localDriveStep, localJSONObject4);
                  localArrayList2.add(localDriveStep);
                }
                localDrivePath.setSteps(localArrayList2);
              }
              localArrayList1.add(localDrivePath);
            }
            localDriveRouteResult.setPaths(localArrayList1);
            return localDriveRouteResult;
          }
        }
        catch (JSONException localJSONException2)
        {
          return localDriveRouteResult;
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
    localStringBuffer.append("&origin=").append(c.a(((i)this.b).a.getFrom()));
    if (!e(((i)this.b).a.getStartPoiID()))
      localStringBuffer.append("&originid=").append(((i)this.b).a.getStartPoiID());
    localStringBuffer.append("&destination=").append(c.a(((i)this.b).a.getTo()));
    if (!e(((i)this.b).a.getDestinationPoiID()))
      localStringBuffer.append("&destinationid=").append(((i)this.b).a.getDestinationPoiID());
    localStringBuffer.append("&strategy=").append("" + ((i)this.b).b);
    localStringBuffer.append("&extensions=all");
    if (((i)this.b).b())
      localStringBuffer.append("&waypoints=").append(((i)this.b).a());
    if (((i)this.b).d())
      localStringBuffer.append("&avoidpolygons=").append(((i)this.b).c());
    if (((i)this.b).f())
      localStringBuffer.append("&avoidroad=").append(((i)this.b).e());
    localStringBuffer.append("&output=json");
    return localStringBuffer.toString();
  }

  protected byte[] c()
  {
    return a().getBytes();
  }

  protected String d()
  {
    return "http://restapi.amap.com/v3/direction/driving?";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.k
 * JD-Core Version:    0.6.2
 */