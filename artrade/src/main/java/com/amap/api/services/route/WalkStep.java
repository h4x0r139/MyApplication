package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.amap.api.services.core.LatLonPoint;
import java.util.List;

public class WalkStep
  implements Parcelable
{
  public static final Parcelable.Creator<WalkStep> CREATOR = new z();
  private String a;
  private String b;
  private String c;
  private float d;
  private float e;
  private List<LatLonPoint> f;
  private String g;
  private String h;

  public WalkStep()
  {
  }

  public WalkStep(Parcel paramParcel)
  {
    this.a = paramParcel.readString();
    this.b = paramParcel.readString();
    this.c = paramParcel.readString();
    this.d = paramParcel.readFloat();
    this.e = paramParcel.readFloat();
    this.f = paramParcel.createTypedArrayList(LatLonPoint.CREATOR);
    this.g = paramParcel.readString();
    this.h = paramParcel.readString();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAction()
  {
    return this.g;
  }

  public String getAssistantAction()
  {
    return this.h;
  }

  public float getDistance()
  {
    return this.d;
  }

  public float getDuration()
  {
    return this.e;
  }

  public String getInstruction()
  {
    return this.a;
  }

  public String getOrientation()
  {
    return this.b;
  }

  public List<LatLonPoint> getPolyline()
  {
    return this.f;
  }

  public String getRoad()
  {
    return this.c;
  }

  public void setAction(String paramString)
  {
    this.g = paramString;
  }

  public void setAssistantAction(String paramString)
  {
    this.h = paramString;
  }

  public void setDistance(float paramFloat)
  {
    this.d = paramFloat;
  }

  public void setDuration(float paramFloat)
  {
    this.e = paramFloat;
  }

  public void setInstruction(String paramString)
  {
    this.a = paramString;
  }

  public void setOrientation(String paramString)
  {
    this.b = paramString;
  }

  public void setPolyline(List<LatLonPoint> paramList)
  {
    this.f = paramList;
  }

  public void setRoad(String paramString)
  {
    this.c = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.a);
    paramParcel.writeString(this.b);
    paramParcel.writeString(this.c);
    paramParcel.writeFloat(this.d);
    paramParcel.writeFloat(this.e);
    paramParcel.writeTypedList(this.f);
    paramParcel.writeString(this.g);
    paramParcel.writeString(this.h);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.amap.api.services.route.WalkStep
 * JD-Core Version:    0.6.2
 */