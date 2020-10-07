package com.demoqa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyDataImpl implements ReadPropertyData {
  Properties prop;

  public ReadPropertyDataImpl(final String propertyFilePath) {
    InputStream fis = null;
    fis = this.getClass().getClassLoader().getResourceAsStream(propertyFilePath);
    prop = new Properties();
    try {
      prop.load(fis);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public String readProperty(final String key) {
    return prop.getProperty(key);
  }

  @Override
  public Boolean isRunningDebug() {
    return DataUtils.stringToBoolean(
        System.getProperty("debug", prop.getProperty(GenericConstants.RUN_IN_DEBUG)));
  }

  @Override
  public Boolean isCodeChecksEnabled() {
    return DataUtils.stringToBoolean(System.getProperty("check-codes",
        prop.getProperty(GenericConstants.ENABLE_RESPONSE_CODE_CHECKS)));
  }
}
