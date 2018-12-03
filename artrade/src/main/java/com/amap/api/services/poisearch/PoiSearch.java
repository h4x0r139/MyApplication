package com.amap.api.services.poisearch;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.b;
import com.amap.api.services.core.c;
import java.util.ArrayList;
import java.util.List;

public class PoiSearch
{
  private static ArrayList<PoiResult> i;
  Handler a = new i(this);
  private SearchBound b;
  private Query c;
  private Context d;
  private OnPoiSearchListener e;
  private Query f;
  private SearchBound g;
  private int h;

  public PoiSearch(Context paramContext, Query paramQuery)
  {
    b.a(paramContext);
    this.d = paramContext.getApplicationContext();
    setQuery(paramQuery);
  }

  private void a(PoiResult paramPoiResult)
  {
    i = new ArrayList();
    for (int j = 0; j < this.h; j++)
      i.add(null);
    if (this.h > 0)
      i.set(this.c.getPageNum(), paramPoiResult);
  }

  private boolean a()
  {
    return (!c.a(Query.a(this.c))) || (!c.a(Query.b(this.c)));
  }

  private boolean a(int paramInt)
  {
    return (paramInt <= this.h) && (paramInt > 0);
  }

  private static boolean b(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null))
      return true;
    if ((paramString1 != null) && (paramString2 != null))
      return paramString1.equals(paramString2);
    return false;
  }

  public SearchBound getBound()
  {
    return this.b;
  }

  protected PoiResult getPageLocal(int paramInt)
  {
    if (!a(paramInt))
      throw new IllegalArgumentException("page out of range");
    return (PoiResult)i.get(paramInt);
  }

  public Query getQuery()
  {
    return this.c;
  }

  public PoiResult searchPOI()
    throws AMapException
  {
    if (!a())
      throw new AMapException("无效的参数 - IllegalArgumentException");
    if (((!this.c.queryEquals(this.f)) && (this.b == null)) || ((!this.c.queryEquals(this.f)) && (!this.b.equals(this.g))))
    {
      this.h = 0;
      this.f = this.c.clone();
      if (this.b != null)
        this.g = this.b.clone();
      if (i != null)
        i.clear();
    }
    SearchBound localSearchBound1 = this.b;
    SearchBound localSearchBound2 = null;
    if (localSearchBound1 != null)
      localSearchBound2 = this.b.clone();
    if (this.h == 0)
    {
      k localk1 = new k(new l(this.c.clone(), localSearchBound2), c.a(this.d));
      localk1.a(Query.c(this.c));
      localk1.b(Query.d(this.c));
      PoiResult localPoiResult1 = PoiResult.a(localk1, (ArrayList)localk1.i());
      a(localPoiResult1);
      return localPoiResult1;
    }
    PoiResult localPoiResult2 = getPageLocal(this.c.getPageNum());
    if (localPoiResult2 == null)
    {
      k localk2 = new k(new l(this.c.clone(), localSearchBound2), c.a(this.d));
      localk2.a(Query.c(this.c));
      localk2.b(Query.d(this.c));
      PoiResult localPoiResult3 = PoiResult.a(localk2, (ArrayList)localk2.i());
      i.set(Query.c(this.c), localPoiResult3);
      return localPoiResult3;
    }
    return localPoiResult2;
  }

  public void searchPOIAsyn()
  {
    new Thread()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = 100;
        Bundle localBundle = new Bundle();
        PoiResult localPoiResult = null;
        try
        {
          localPoiResult = PoiSearch.this.searchPOI();
          localBundle.putInt("errorCode", 0);
          return;
        }
        catch (AMapException localAMapException)
        {
          localBundle.putInt("errorCode", localAMapException.getErrorCode());
          return;
        }
        finally
        {
          localMessage.obj = localPoiResult;
          localMessage.setData(localBundle);
          PoiSearch.this.a.sendMessage(localMessage);
        }
      }
    }
    .start();
  }

  public PoiItemDetail searchPOIDetail(String paramString)
    throws AMapException
  {
    return (PoiItemDetail)new j(paramString, c.a(this.d)).i();
  }

  public void searchPOIDetailAsyn(final String paramString)
  {
    new Thread()
    {
      public void run()
      {
        Message localMessage = new Message();
        localMessage.what = 101;
        Bundle localBundle = new Bundle();
        PoiItemDetail localPoiItemDetail = null;
        try
        {
          localPoiItemDetail = PoiSearch.this.searchPOIDetail(paramString);
          localBundle.putInt("errorCode", 0);
          return;
        }
        catch (AMapException localAMapException)
        {
          localBundle.putInt("errorCode", localAMapException.getErrorCode());
          return;
        }
        finally
        {
          localMessage.obj = localPoiItemDetail;
          localMessage.setData(localBundle);
          PoiSearch.this.a.sendMessage(localMessage);
        }
      }
    }
    .start();
  }

  public void setBound(SearchBound paramSearchBound)
  {
    this.b = paramSearchBound;
  }

  public void setOnPoiSearchListener(OnPoiSearchListener paramOnPoiSearchListener)
  {
    this.e = paramOnPoiSearchListener;
  }

  public void setQuery(Query paramQuery)
  {
    this.c = paramQuery;
  }

  public static abstract interface OnPoiSearchListener
  {
    public abstract void onPoiItemDetailSearched(PoiItemDetail paramPoiItemDetail, int paramInt);

    public abstract void onPoiSearched(PoiResult paramPoiResult, int paramInt);
  }

  public static class Query
  {
    private String a;
    private String b;
    private String c;
    private int d = 1;
    private int e = 20;
    private boolean f;
    private boolean g;

    public Query(String paramString1, String paramString2)
    {
      this(paramString1, paramString2, null);
    }

    public Query(String paramString1, String paramString2, String paramString3)
    {
      this.a = paramString1;
      this.b = paramString2;
      this.c = paramString3;
    }

    private String a()
    {
      return "";
    }

    public Query clone()
    {
      Query localQuery = new Query(this.a, this.b, this.c);
      localQuery.setPageNum(this.d);
      localQuery.setPageSize(this.e);
      localQuery.setLimitDiscount(this.g);
      localQuery.setLimitGroupbuy(this.f);
      return localQuery;
    }

    public boolean equals(Object paramObject)
    {
      boolean bool = true;
      if ((paramObject == null) || (!(paramObject instanceof Query)))
        bool = false;
      Query localQuery;
      do
      {
        do
          return bool;
        while (paramObject == this);
        localQuery = (Query)paramObject;
      }
      while ((PoiSearch.a(localQuery.a, this.a)) && (PoiSearch.a(localQuery.b, this.b)) && (PoiSearch.a(localQuery.c, this.c)) && (localQuery.d == this.d) && (localQuery.e == this.e));
      return false;
    }

    public String getCategory()
    {
      if ((this.b == null) || (this.b.equals("00")) || (this.b.equals("00|")))
        return a();
      return this.b;
    }

    public String getCity()
    {
      return this.c;
    }

    public int getPageNum()
    {
      return this.d;
    }

    public int getPageSize()
    {
      return this.e;
    }

    public String getQueryString()
    {
      return this.a;
    }

    public boolean hasDiscountLimit()
    {
      return this.g;
    }

    public boolean hasGroupBuyLimit()
    {
      return this.f;
    }

    public boolean queryEquals(Query paramQuery)
    {
      boolean bool = true;
      if (paramQuery == null)
        bool = false;
      while ((paramQuery == this) || ((PoiSearch.a(paramQuery.a, this.a)) && (PoiSearch.a(paramQuery.b, this.b)) && (PoiSearch.a(paramQuery.c, this.c)) && (paramQuery.e == this.e)))
        return bool;
      return false;
    }

    public void setLimitDiscount(boolean paramBoolean)
    {
      this.g = paramBoolean;
    }

    public void setLimitGroupbuy(boolean paramBoolean)
    {
      this.f = paramBoolean;
    }

    public void setPageNum(int paramInt)
    {
      this.d = paramInt;
    }

    public void setPageSize(int paramInt)
    {
      this.e = paramInt;
    }
  }

  public static class SearchBound
  {
    public static final String BOUND_SHAPE = "Bound";
    public static final String ELLIPSE_SHAPE = "Ellipse";
    public static final String POLYGON_SHAPE = "Polygon";
    public static final String RECTANGLE_SHAPE = "Rectangle";
    private LatLonPoint a;
    private LatLonPoint b;
    private int c;
    private LatLonPoint d;
    private String e;
    private boolean f = true;
    private List<LatLonPoint> g;

    public SearchBound(LatLonPoint paramLatLonPoint, int paramInt)
    {
      this.e = "Bound";
      this.c = paramInt;
      this.d = paramLatLonPoint;
      a(paramLatLonPoint, c.a(paramInt), c.a(paramInt));
    }

    public SearchBound(LatLonPoint paramLatLonPoint, int paramInt, boolean paramBoolean)
    {
      this.e = "Bound";
      this.c = paramInt;
      this.d = paramLatLonPoint;
      a(paramLatLonPoint, c.a(paramInt), c.a(paramInt));
      this.f = paramBoolean;
    }

    public SearchBound(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
    {
      this.e = "Rectangle";
      a(paramLatLonPoint1, paramLatLonPoint2);
    }

    private SearchBound(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2, int paramInt, LatLonPoint paramLatLonPoint3, String paramString, List<LatLonPoint> paramList, boolean paramBoolean)
    {
      this.a = paramLatLonPoint1;
      this.b = paramLatLonPoint2;
      this.c = paramInt;
      this.d = paramLatLonPoint3;
      this.e = paramString;
      this.g = paramList;
      this.f = paramBoolean;
    }

    public SearchBound(List<LatLonPoint> paramList)
    {
      this.e = "Polygon";
      this.g = paramList;
    }

    private void a(LatLonPoint paramLatLonPoint, double paramDouble1, double paramDouble2)
    {
      double d1 = paramDouble1 / 2.0D;
      double d2 = paramDouble2 / 2.0D;
      double d3 = paramLatLonPoint.getLatitude();
      double d4 = paramLatLonPoint.getLongitude();
      a(new LatLonPoint(d3 - d1, d4 - d2), new LatLonPoint(d1 + d3, d2 + d4));
    }

    private void a(LatLonPoint paramLatLonPoint1, LatLonPoint paramLatLonPoint2)
    {
      this.a = paramLatLonPoint1;
      this.b = paramLatLonPoint2;
      if ((this.a.getLatitude() >= this.b.getLatitude()) || (this.a.getLongitude() >= this.b.getLongitude()))
        throw new IllegalArgumentException("invalid rect ");
      this.d = new LatLonPoint((this.a.getLatitude() + this.b.getLatitude()) / 2.0D, (this.a.getLongitude() + this.b.getLongitude()) / 2.0D);
    }

    public SearchBound clone()
    {
      return new SearchBound(this.a, this.b, this.c, this.d, this.e, this.g, this.f);
    }

    public boolean equals(Object paramObject)
    {
      if ((paramObject == null) || (!(paramObject instanceof SearchBound)));
      SearchBound localSearchBound;
      do
      {
        return false;
        localSearchBound = (SearchBound)paramObject;
      }
      while ((!localSearchBound.d.equals(this.d)) || (!localSearchBound.a.equals(this.a)) || (!localSearchBound.b.equals(this.b)) || (localSearchBound.c != this.c) || (!PoiSearch.a(localSearchBound.e, this.e)));
      return true;
    }

    public LatLonPoint getCenter()
    {
      return this.d;
    }

    public double getLatSpanInMeter()
    {
      return this.b.getLatitude() - this.a.getLatitude();
    }

    public double getLonSpanInMeter()
    {
      return this.b.getLongitude() - this.a.getLongitude();
    }

    public LatLonPoint getLowerLeft()
    {
      return this.a;
    }

    public List<LatLonPoint> getPolyGonList()
    {
      return this.g;
    }

    protected int getRange()
    {
      return this.c;
    }

    public String getShape()
    {
      return this.e;
    }

    public LatLonPoint getUpperRight()
    {
      return this.b;
    }

    public boolean isDistanceSort()
    {
      return this.f;
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.poisearch.PoiSearch
 * JD-Core Version:    0.6.2
 */