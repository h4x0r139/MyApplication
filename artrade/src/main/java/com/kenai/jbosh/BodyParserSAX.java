package com.kenai.jbosh;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

final class BodyParserSAX
  implements BodyParser
{
  private static final Logger LOG = Logger.getLogger(BodyParserSAX.class.getName());
  private static final ThreadLocal<SoftReference<SAXParser>> PARSER = new ThreadLocal()
  {
    protected SoftReference<SAXParser> initialValue()
    {
      return new SoftReference(null);
    }
  };
  private static final SAXParserFactory SAX_FACTORY = SAXParserFactory.newInstance();

  static
  {
    SAX_FACTORY.setNamespaceAware(true);
    SAX_FACTORY.setValidating(false);
  }

  private static SAXParser getSAXParser()
  {
    SAXParser localSAXParser1 = (SAXParser)((SoftReference)PARSER.get()).get();
    if (localSAXParser1 == null);
    try
    {
      SAXParser localSAXParser2 = SAX_FACTORY.newSAXParser();
      SoftReference localSoftReference = new SoftReference(localSAXParser2);
      PARSER.set(localSoftReference);
      return localSAXParser2;
    }
    catch (SAXException localSAXException)
    {
      throw new IllegalStateException("Could not create SAX parser", localSAXException);
      localSAXParser1.reset();
      return localSAXParser1;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      label46: break label46;
    }
  }

  public BodyParserResults parse(String paramString)
    throws BOSHException
  {
    BodyParserResults localBodyParserResults = new BodyParserResults();
    try
    {
      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramString.getBytes());
      SAXParser localSAXParser = getSAXParser();
      localSAXParser.parse(localByteArrayInputStream, new Handler(localSAXParser, localBodyParserResults, null));
      return localBodyParserResults;
    }
    catch (IOException localIOException)
    {
      throw new BOSHException("Could not parse body:\n" + paramString, localIOException);
    }
    catch (SAXException localSAXException)
    {
      label46: break label46;
    }
  }

  private static final class Handler extends DefaultHandler
  {
    private String defaultNS = null;
    private final SAXParser parser;
    private final BodyParserResults result;

    private Handler(SAXParser paramSAXParser, BodyParserResults paramBodyParserResults)
    {
      this.parser = paramSAXParser;
      this.result = paramBodyParserResults;
    }

    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    {
      if (BodyParserSAX.LOG.isLoggable(Level.FINEST))
      {
        BodyParserSAX.LOG.finest("Start element: " + paramString3);
        BodyParserSAX.LOG.finest("    URI: " + paramString1);
        BodyParserSAX.LOG.finest("    local: " + paramString2);
      }
      BodyQName localBodyQName1 = AbstractBody.getBodyQName();
      if ((!localBodyQName1.getNamespaceURI().equals(paramString1)) || (!localBodyQName1.getLocalPart().equals(paramString2)))
        throw new IllegalStateException("Root element was not '" + localBodyQName1.getLocalPart() + "' in the '" + localBodyQName1.getNamespaceURI() + "' namespace.  (Was '" + paramString2 + "' in '" + paramString1 + "')");
      for (int i = 0; i < paramAttributes.getLength(); i++)
      {
        String str1 = paramAttributes.getURI(i);
        if (str1.length() == 0)
          str1 = this.defaultNS;
        String str2 = paramAttributes.getLocalName(i);
        String str3 = paramAttributes.getValue(i);
        if (BodyParserSAX.LOG.isLoggable(Level.FINEST))
          BodyParserSAX.LOG.finest("    Attribute: {" + str1 + "}" + str2 + " = '" + str3 + "'");
        BodyQName localBodyQName2 = BodyQName.create(str1, str2);
        this.result.addBodyAttributeValue(localBodyQName2, str3);
      }
      this.parser.reset();
    }

    public void startPrefixMapping(String paramString1, String paramString2)
    {
      if (paramString1.length() == 0)
      {
        if (BodyParserSAX.LOG.isLoggable(Level.FINEST))
          BodyParserSAX.LOG.finest("Prefix mapping: <DEFAULT> => " + paramString2);
        this.defaultNS = paramString2;
      }
      while (!BodyParserSAX.LOG.isLoggable(Level.FINEST))
        return;
      BodyParserSAX.LOG.info("Prefix mapping: " + paramString1 + " => " + paramString2);
    }
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.BodyParserSAX
 * JD-Core Version:    0.6.2
 */