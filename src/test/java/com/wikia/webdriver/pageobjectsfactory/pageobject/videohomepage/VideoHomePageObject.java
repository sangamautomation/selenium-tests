package com.wikia.webdriver.pageobjectsfactory.pageobject.videohomepage;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VideoHomePageObject extends WikiBasePageObject {

  @FindBy(css = ".featured-video-slider .bx-controls")
  private WebElement featuredModuleControls;
  @FindBys(@FindBy(css = "#featured-video-bxslider li"))
  private List<WebElement> featuredSlides;
  @FindBys(@FindBy(css = ".latest-videos-wrapper .carousel-wrapper"))
  private List<WebElement> latestVideoRows;

  public VideoHomePageObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public VideoHomePageObject open(){
    getUrl(new UrlBuilder().getUrlForWiki(Configuration.getWikiName()));

    return this;
  }

  public void verifyFeaturedSliderInitialized() {
    wait.forElementVisible(featuredModuleControls);
    PageObjectLogging
        .log("verifyFeaturedSliderInitialized", "Featured video slider has initialized", true);
  }

  public void verifyFeaturedSliderSlides(int count) {
    wait.forElementPresent(By.cssSelector("#featured-video-bxslider li[style*='z-index: 50;']"));
    Assertion.assertTrue(featuredSlides.size() >= count);
    PageObjectLogging.log("verifyFeaturedSliderSlides",
                          "At least " + count + " latest Videos modules have rendered", true);
  }

  public void verifyLatestVideosRows(int count) {
    wait.forElementVisible(latestVideoRows.get(0));
    Assertion.assertTrue(latestVideoRows.size() >= count);
    PageObjectLogging
        .log("verifyLatestVideosRows", "At least " + count + "latest Videos modules have rendered",
             true);
  }
}
