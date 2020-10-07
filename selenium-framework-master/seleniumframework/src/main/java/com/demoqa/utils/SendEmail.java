package com.demoqa.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class SendEmail {
  public static void sendEmailThis(final String filePath) throws IOException, EmailException {
    sendEmailThis(filePath, "TestMail- Alternative message");

  }

  public static void sendEmailThis(final String filePath, final String subjectName)
      throws IOException, EmailException {
    File file = new File(System.getProperty("user.dir") + filePath);
    String string = FileUtils.readFileToString(file, "UTF-8");
    HtmlEmail email = new HtmlEmail();
    email.setHostName("smtp.gmail.com");
    email.setSmtpPort(465);
    email.setAuthenticator(new DefaultAuthenticator("seleniumauto0@gmail.com", "manoj1234"));
    email.setSSLOnConnect(true);
    email.setFrom("seleniumauto0@gmail.com");
    email.setSubject(subjectName);
    email.setHtmlMsg(string);
    email.addTo("manoj.kumar.tinku@gmail.com");
    email.send();

  }

}
