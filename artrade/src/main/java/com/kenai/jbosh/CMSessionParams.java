package com.kenai.jbosh;

final class CMSessionParams
{
  private final AttrAccept accept;
  private final AttrAck ack;
  private final boolean ackingRequests;
  private final AttrCharsets charsets;
  private final AttrHold hold;
  private final AttrInactivity inactivity;
  private final AttrMaxPause maxPause;
  private final AttrPolling polling;
  private final AttrRequests requests;
  private final AttrSessionID sid;
  private final AttrVersion ver;
  private final AttrWait wait;

  private CMSessionParams(AttrSessionID paramAttrSessionID, AttrWait paramAttrWait, AttrVersion paramAttrVersion, AttrPolling paramAttrPolling, AttrInactivity paramAttrInactivity, AttrRequests paramAttrRequests, AttrHold paramAttrHold, AttrAccept paramAttrAccept, AttrMaxPause paramAttrMaxPause, AttrAck paramAttrAck, AttrCharsets paramAttrCharsets, boolean paramBoolean)
  {
    this.sid = paramAttrSessionID;
    this.wait = paramAttrWait;
    this.ver = paramAttrVersion;
    this.polling = paramAttrPolling;
    this.inactivity = paramAttrInactivity;
    this.requests = paramAttrRequests;
    this.hold = paramAttrHold;
    this.accept = paramAttrAccept;
    this.maxPause = paramAttrMaxPause;
    this.ack = paramAttrAck;
    this.charsets = paramAttrCharsets;
    this.ackingRequests = paramBoolean;
  }

  static CMSessionParams fromSessionInit(AbstractBody paramAbstractBody1, AbstractBody paramAbstractBody2)
    throws BOSHException
  {
    AttrAck localAttrAck = AttrAck.createFromString(paramAbstractBody2.getAttribute(Attributes.ACK));
    String str = paramAbstractBody1.getAttribute(Attributes.RID);
    if ((localAttrAck != null) && (((String)localAttrAck.getValue()).equals(str)));
    for (boolean bool = true; ; bool = false)
      return new CMSessionParams(AttrSessionID.createFromString(getRequiredAttribute(paramAbstractBody2, Attributes.SID)), AttrWait.createFromString(getRequiredAttribute(paramAbstractBody2, Attributes.WAIT)), AttrVersion.createFromString(paramAbstractBody2.getAttribute(Attributes.VER)), AttrPolling.createFromString(paramAbstractBody2.getAttribute(Attributes.POLLING)), AttrInactivity.createFromString(paramAbstractBody2.getAttribute(Attributes.INACTIVITY)), AttrRequests.createFromString(paramAbstractBody2.getAttribute(Attributes.REQUESTS)), AttrHold.createFromString(paramAbstractBody2.getAttribute(Attributes.HOLD)), AttrAccept.createFromString(paramAbstractBody2.getAttribute(Attributes.ACCEPT)), AttrMaxPause.createFromString(paramAbstractBody2.getAttribute(Attributes.MAXPAUSE)), localAttrAck, AttrCharsets.createFromString(paramAbstractBody2.getAttribute(Attributes.CHARSETS)), bool);
  }

  private static String getRequiredAttribute(AbstractBody paramAbstractBody, BodyQName paramBodyQName)
    throws BOSHException
  {
    String str = paramAbstractBody.getAttribute(paramBodyQName);
    if (str == null)
      throw new BOSHException("Connection Manager session creation response did not include required '" + paramBodyQName.getLocalPart() + "' attribute");
    return str;
  }

  AttrAccept getAccept()
  {
    return this.accept;
  }

  AttrAck getAck()
  {
    return this.ack;
  }

  AttrCharsets getCharsets()
  {
    return this.charsets;
  }

  AttrHold getHold()
  {
    return this.hold;
  }

  AttrInactivity getInactivityPeriod()
  {
    return this.inactivity;
  }

  AttrMaxPause getMaxPause()
  {
    return this.maxPause;
  }

  AttrPolling getPollingInterval()
  {
    return this.polling;
  }

  AttrRequests getRequests()
  {
    return this.requests;
  }

  AttrSessionID getSessionID()
  {
    return this.sid;
  }

  AttrVersion getVersion()
  {
    return this.ver;
  }

  AttrWait getWait()
  {
    return this.wait;
  }

  boolean isAckingRequests()
  {
    return this.ackingRequests;
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.CMSessionParams
 * JD-Core Version:    0.6.2
 */