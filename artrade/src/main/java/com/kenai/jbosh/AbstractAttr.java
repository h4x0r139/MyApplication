package com.kenai.jbosh;

abstract class AbstractAttr<T extends Comparable>
  implements Comparable
{
  private final T value;

  protected AbstractAttr(T paramT)
  {
    this.value = paramT;
  }

  public int compareTo(Object paramObject)
  {
    if (paramObject == null)
      return 1;
    return this.value.compareTo(paramObject);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    while (!(paramObject instanceof AbstractAttr))
      return false;
    AbstractAttr localAbstractAttr = (AbstractAttr)paramObject;
    return this.value.equals(localAbstractAttr.value);
  }

  public final T getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return this.value.hashCode();
  }

  public String toString()
  {
    return this.value.toString();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.AbstractAttr
 * JD-Core Version:    0.6.2
 */