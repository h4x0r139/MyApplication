package com.kenai.jbosh;

import java.io.IOException;
import java.io.StringReader;
import java.lang.ref.SoftReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

final class BodyParserXmlPull
  implements BodyParser
{
  private static final Logger LOG = Logger.getLogger(BodyParserXmlPull.class.getName());
  private static final ThreadLocal<SoftReference<XmlPullParser>> XPP_PARSER = new ThreadLocal()
  {
    protected SoftReference<XmlPullParser> initialValue()
    {
      return new SoftReference(null);
    }
  };

  private static XmlPullParser getXmlPullParser()
  {
    XmlPullParser localXmlPullParser = (XmlPullParser)((SoftReference)XPP_PARSER.get()).get();
    if (localXmlPullParser == null);
    try
    {
      XmlPullParserFactory localXmlPullParserFactory = XmlPullParserFactory.newInstance();
      localXmlPullParserFactory.setNamespaceAware(true);
      localXmlPullParserFactory.setValidating(false);
      localXmlPullParser = localXmlPullParserFactory.newPullParser();
      SoftReference localSoftReference = new SoftReference(localXmlPullParser);
      XPP_PARSER.set(localSoftReference);
      return localXmlPullParser;
    }
    catch (Exception localException)
    {
      throw new IllegalStateException("Could not create XmlPull parser", localException);
    }
  }

  public BodyParserResults parse(String paramString)
    throws BOSHException
  {
    BodyParserResults localBodyParserResults = new BodyParserResults();
    try
    {
      localXmlPullParser = getXmlPullParser();
      localXmlPullParser.setInput(new StringReader(paramString));
      i = localXmlPullParser.getEventType();
      if (i != 1)
        if (i == 2)
        {
          if (LOG.isLoggable(Level.FINEST))
            LOG.finest("Start tag: " + localXmlPullParser.getName());
          String str1 = localXmlPullParser.getPrefix();
          if (str1 == null)
            str1 = "";
          String str2 = localXmlPullParser.getNamespace();
          String str3 = localXmlPullParser.getName();
          QName localQName = new QName(str2, str3, str1);
          if (LOG.isLoggable(Level.FINEST))
          {
            LOG.finest("Start element: ");
            LOG.finest("    prefix: " + str1);
            LOG.finest("    URI: " + str2);
            LOG.finest("    local: " + str3);
          }
          BodyQName localBodyQName1 = AbstractBody.getBodyQName();
          if (localBodyQName1.equalsQName(localQName))
            break label554;
          throw new IllegalStateException("Root element was not '" + localBodyQName1.getLocalPart() + "' in the '" + localBodyQName1.getNamespaceURI() + "' namespace.  (Was '" + str3 + "' in '" + str2 + "')");
        }
    }
    catch (RuntimeException localRuntimeException)
    {
      XmlPullParser localXmlPullParser;
      while (true)
      {
        throw new BOSHException("Could not parse body:\n" + paramString, localRuntimeException);
        int i = localXmlPullParser.next();
      }
      while (j < localXmlPullParser.getAttributeCount())
      {
        str4 = localXmlPullParser.getAttributeNamespace(j);
        if (str4.length() != 0)
          break label547;
        str5 = localXmlPullParser.getNamespace(null);
        String str6 = localXmlPullParser.getAttributePrefix(j);
        if (str6 == null)
          str6 = "";
        String str7 = localXmlPullParser.getAttributeName(j);
        String str8 = localXmlPullParser.getAttributeValue(j);
        BodyQName localBodyQName2 = BodyQName.createWithPrefix(str5, str7, str6);
        if (LOG.isLoggable(Level.FINEST))
          LOG.finest("        Attribute: {" + str5 + "}" + str7 + " = '" + str8 + "'");
        localBodyParserResults.addBodyAttributeValue(localBodyQName2, str8);
        j++;
      }
      return localBodyParserResults;
    }
    catch (IOException localIOException)
    {
      break label326;
    }
    catch (XmlPullParserException localXmlPullParserException)
    {
      while (true)
      {
        label326: String str4;
        continue;
        label547: String str5 = str4;
        continue;
        label554: int j = 0;
      }
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.BodyParserXmlPull
 * JD-Core Version:    0.6.2
 */