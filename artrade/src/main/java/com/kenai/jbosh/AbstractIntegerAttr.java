package com.kenai.jbosh;

abstract class AbstractIntegerAttr extends AbstractAttr<Integer>
{
  protected AbstractIntegerAttr(int paramInt)
    throws BOSHException
  {
    super(Integer.valueOf(paramInt));
  }

  protected AbstractIntegerAttr(String paramString)
    throws BOSHException
  {
    super(Integer.valueOf(parseInt(paramString)));
  }

  private static int parseInt(String paramString)
    throws BOSHException
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      throw new BOSHException("Could not parse an integer from the value provided: " + paramString, localNumberFormatException);
    }
  }

  protected final void checkMinValue(int paramInt)
    throws BOSHException
  {
    int i = ((Integer)getValue()).intValue();
    if (i < paramInt)
      throw new BOSHException("Illegal attribute value '" + i + "' provided.  " + "Must be >= " + paramInt);
  }

  public int intValue()
  {
    return ((Integer)getValue()).intValue();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AbstractIntegerAttr
 * JD-Core Version:    0.6.2
 */