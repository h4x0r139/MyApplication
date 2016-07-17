package com.amap.api.services.route;

import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.c;
import java.io.InputStream;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d extends t<b, BusRouteResult>
{
  public d(b paramb, Proxy paramProxy)
  {
    super(paramb, paramProxy);
  }

  private BusStep a(JSONObject paramJSONObject)
    throws JSONException
  {
    BusStep localBusStep = new BusStep();
    if (paramJSONObject.has("walking"));
    try
    {
      localBusStep.setWalk(b(paramJSONObject.getJSONObject("walking")));
      label31: if (paramJSONObject.has("bus"))
        localBusStep.setBusLines(c(paramJSONObject.getJSONObject("bus")));
      if (paramJSONObject.has("entrance"));
      try
      {
        localBusStep.setEntrance(d(paramJSONObject.getJSONObject("entrance")));
        label77: if (paramJSONObject.has("exit"));
        try
        {
          localBusStep.setExit(d(paramJSONObject.getJSONObject("exit")));
          return localBusStep;
        }
        catch (JSONException localJSONException1)
        {
          return localBusStep;
        }
      }
      catch (Exception localException)
      {
        break label77;
      }
    }
    catch (JSONException localJSONException2)
    {
      break label31;
    }
  }

  private List<BusPath> a(JSONArray paramJSONArray)
    throws JSONException
  {
    ArrayList localArrayList1 = new ArrayList();
    for (int i = 0; i < paramJSONArray.length(); i++)
    {
      BusPath localBusPath = new BusPath();
      JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
      localBusPath.setCost(g(a(localJSONObject, "cost")));
      localBusPath.setDuration(h(a(localJSONObject, "duration")));
      localBusPath.setNightBus(i(a(localJSONObject, "nightflag")));
      localBusPath.setWalkDistance(g(a(localJSONObject, "walking_distance")));
      if (localJSONObject.has("segments"))
      {
        JSONArray localJSONArray = localJSONObject.getJSONArray("segments");
        ArrayList localArrayList2 = new ArrayList();
        int j = 0;
        float f1 = 0.0F;
        float f2 = 0.0F;
        while (j < localJSONArray.length())
        {
          BusStep localBusStep = a(localJSONArray.getJSONObject(j));
          localArrayList2.add(localBusStep);
          if (localBusStep.getWalk() != null)
            f1 += localBusStep.getWalk().getDistance();
          if (localBusStep.getBusLine() != null)
            f2 += localBusStep.getBusLine().getDistance();
          j++;
        }
        localBusPath.setSteps(localArrayList2);
        localBusPath.setBusDistance(f2);
        localBusPath.setWalkDistance(f1);
      }
      localArrayList1.add(localBusPath);
    }
    return localArrayList1;
  }

  private RouteBusWalkItem b(JSONObject paramJSONObject)
    throws JSONException
  {
    RouteBusWalkItem localRouteBusWalkItem = new RouteBusWalkItem();
    localRouteBusWalkItem.setOrigin(b(paramJSONObject, "origin"));
    localRouteBusWalkItem.setDestination(b(paramJSONObject, "destination"));
    localRouteBusWalkItem.setDistance(g(a(paramJSONObject, "distance")));
    localRouteBusWalkItem.setDuration(h(a(paramJSONObject, "duration")));
    if (!paramJSONObject.has("steps"))
      return localRouteBusWalkItem;
    JSONArray localJSONArray = paramJSONObject.getJSONArray("steps");
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < localJSONArray.length(); i++)
      localArrayList.add(e(localJSONArray.getJSONObject(i)));
    localRouteBusWalkItem.setSteps(localArrayList);
    return localRouteBusWalkItem;
  }

  private List<RouteBusLineItem> c(JSONObject paramJSONObject)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    if (!paramJSONObject.has("buslines"))
      return localArrayList;
    JSONArray localJSONArray = paramJSONObject.getJSONArray("buslines");
    for (int i = 0; i < localJSONArray.length(); i++)
      localArrayList.add(f(localJSONArray.getJSONObject(i)));
    return localArrayList;
  }

  private Doorway d(JSONObject paramJSONObject)
    throws JSONException
  {
    Doorway localDoorway = new Doorway();
    localDoorway.setName(a(paramJSONObject, "name"));
    localDoorway.setLatLonPoint(b(paramJSONObject, "location"));
    return localDoorway;
  }

  private WalkStep e(JSONObject paramJSONObject)
    throws JSONException
  {
    WalkStep localWalkStep = new WalkStep();
    localWalkStep.setInstruction(a(paramJSONObject, "instruction"));
    localWalkStep.setOrientation(a(paramJSONObject, "orientation"));
    localWalkStep.setRoad(a(paramJSONObject, "road"));
    localWalkStep.setDistance(g(a(paramJSONObject, "distance")));
    localWalkStep.setDuration(g(a(paramJSONObject, "duration")));
    localWalkStep.setPolyline(c(paramJSONObject, "polyline"));
    localWalkStep.setAction(a(paramJSONObject, "action"));
    localWalkStep.setAssistantAction(a(paramJSONObject, "assistant_action"));
    return localWalkStep;
  }

  private RouteBusLineItem f(JSONObject paramJSONObject)
    throws JSONException
  {
    RouteBusLineItem localRouteBusLineItem = new RouteBusLineItem();
    if (paramJSONObject.has("departure_stop"))
      localRouteBusLineItem.setDepartureBusStation(h(paramJSONObject.getJSONObject("departure_stop")));
    if (paramJSONObject.has("arrival_stop"))
      localRouteBusLineItem.setArrivalBusStation(h(paramJSONObject.getJSONObject("arrival_stop")));
    localRouteBusLineItem.setBusLineName(a(paramJSONObject, "name"));
    localRouteBusLineItem.setBusLineId(a(paramJSONObject, "id"));
    localRouteBusLineItem.setBusLineType(a(paramJSONObject, "type"));
    localRouteBusLineItem.setDistance(g(a(paramJSONObject, "distance")));
    localRouteBusLineItem.setDuration(g(a(paramJSONObject, "duration")));
    localRouteBusLineItem.setPolyline(c(paramJSONObject, "polyline"));
    localRouteBusLineItem.setFirstBusTime(c.d(a(paramJSONObject, "start_time")));
    localRouteBusLineItem.setLastBusTime(c.d(a(paramJSONObject, "end_time")));
    localRouteBusLineItem.setPassStationNum(f(a(paramJSONObject, "via_num")));
    localRouteBusLineItem.setPassStations(g(paramJSONObject));
    return localRouteBusLineItem;
  }

  private List<BusStationItem> g(JSONObject paramJSONObject)
    throws JSONException
  {
    ArrayList localArrayList = new ArrayList();
    if (!paramJSONObject.has("via_stops"))
      return localArrayList;
    JSONArray localJSONArray = paramJSONObject.getJSONArray("via_stops");
    for (int i = 0; i < localJSONArray.length(); i++)
      localArrayList.add(h(localJSONArray.getJSONObject(i)));
    return localArrayList;
  }

  private BusStationItem h(JSONObject paramJSONObject)
    throws JSONException
  {
    BusStationItem localBusStationItem = new BusStationItem();
    localBusStationItem.setBusStationName(a(paramJSONObject, "name"));
    localBusStationItem.setBusStationId(a(paramJSONObject, "id"));
    localBusStationItem.setLatLonPoint(b(paramJSONObject, "location"));
    return localBusStationItem;
  }

  protected BusRouteResult a(InputStream paramInputStream)
    throws AMapException
  {
    String str = c.b(paramInputStream);
    BusRouteResult localBusRouteResult;
    if ((str == null) || (str.equals("")))
      localBusRouteResult = null;
    while (true)
    {
      return localBusRouteResult;
      c.b(str);
      try
      {
        JSONObject localJSONObject1 = new JSONObject(str);
        if (!localJSONObject1.has("route"))
          return null;
        localBusRouteResult = new BusRouteResult();
        try
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("route");
          localBusRouteResult.setStartPos(b(localJSONObject2, "origin"));
          localBusRouteResult.setTargetPos(b(localJSONObject2, "destination"));
          localBusRouteResult.setTaxiCost(g(a(localJSONObject2, "taxi_cost")));
          if (localJSONObject2.has("transits"))
          {
            localBusRouteResult.setPaths(a(localJSONObject2.getJSONArray("transits")));
            return localBusRouteResult;
          }
        }
        catch (JSONException localJSONException2)
        {
          return localBusRouteResult;
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
    localStringBuffer.append("key=").append(com.amap.api.services.core.b.a);
    localStringBuffer.append("&origin=").append(c.a(((b)this.b).a.getFrom()));
    localStringBuffer.append("&destination=").append(c.a(((b)this.b).a.getTo()));
    String str1 = ((b)this.b).b();
    if (!e(str1))
    {
      String str2 = d(str1);
      localStringBuffer.append("&city=").append(str2);
    }
    localStringBuffer.append("&strategy=").append("" + ((b)this.b).b);
    localStringBuffer.append("&nightflag=").append(((b)this.b).a());
    localStringBuffer.append("&output=json");
    return localStringBuffer.toString();
  }

  protected byte[] c()
  {
    return a().getBytes();
  }

  protected String d()
  {
    return "http://restapi.amap.com/v3/direction/transit/integrated?";
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.d
 * JD-Core Version:    0.6.2
 */