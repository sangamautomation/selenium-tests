package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.dataprovider.ads.GermanAdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;

import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @ownership AdEng
 */
public class TestIVW2AnalyticsProvider extends TemplateNoFirstLoad {

  @Test(groups = "TestIVW2AnalyticsProvider")
  public void testIVW2AnalyticsProvider() throws IOException {
    for (Object[] data : GermanAdsDataProvider.IVW2_TEST_DATA) {
      String wikiName = (String) data[0];
      String article = (String) data[1];
      String ivw2Param = (String) data[2];
      String testedPage = urlBuilder.getUrlForPath(wikiName, article);
      driver.get(testedPage);
      String htmlSource = driver.getPageSource();
      Assertion.assertTrue(htmlSource.contains(ivw2Param));
      PageObjectLogging.log("IVW2", ivw2Param + " param is on the " + testedPage, true);
    }
  }
}
