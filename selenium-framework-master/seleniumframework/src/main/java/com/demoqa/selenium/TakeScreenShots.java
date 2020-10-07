package com.demoqa.selenium;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class TakeScreenShots {
  @Attachment
  public static void takeChromScreenShot(final WebDriver driver, final String fileName) {
    final Screenshot screenshot =
        new AShot().shootingStrategy(new ViewportPastingStrategy(500)).takeScreenshot(driver);
    final BufferedImage image = screenshot.getImage();
    try {
      ImageIO.write(image, "PNG", new File(fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
