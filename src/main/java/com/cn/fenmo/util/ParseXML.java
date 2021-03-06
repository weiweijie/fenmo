package com.cn.fenmo.util;

import java.io.PrintStream;
import java.net.URL;
import java.util.Properties;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ParseXML
{
  private Properties props;

  public Properties getProps()
  {
    return this.props;
  }

  public void parse(String filename)
  {
    ConfigParser handler = new ConfigParser();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setNamespaceAware(false);
    factory.setValidating(false);
    SAXParser parser = null;
    try {
      parser = factory.newSAXParser();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    URL confURL = null;
    try
    {
      confURL = ParseXML.class.getClassLoader().getResource(filename);
    }
    catch (Exception e) {
      System.out.print(e.toString());
    }
    try {
      parser.parse(confURL.toString(), handler);

      this.props = handler.getProps();
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      factory = null;
      parser = null;
      handler = null;
    }
  }
}