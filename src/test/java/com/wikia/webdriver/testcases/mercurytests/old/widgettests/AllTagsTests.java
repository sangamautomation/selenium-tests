package com.wikia.webdriver.testcases.mercurytests.old.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.Navigation;
import com.wikia.webdriver.elements.mercury.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PolldaddyWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SpotifyWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;

import org.joda.time.DateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class AllTagsTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;

  private static final String ARTICLE_NAME = "AllTagsWidgetMercury";
  private static final String MAPS_ARTICLE_NAME = "Map";
  private static List<WidgetPageObject> widgets;

  public void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);
  }

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    widgets = new ArrayList<>();
    widgets.add(new PollsnackWidgetPageObject(driver));
    widgets.add(new SoundCloudWidgetPageObject(driver));
    widgets.add(new SpotifyWidgetPageObject(driver));
    widgets.add(new TwitterWidgetPageObject(driver));
    widgets.add(new VKWidgetPageObject(driver));
    widgets.add(new WeiboWidgetPageObject(driver));
    widgets.add(new GoogleFormWidgetPageObject(driver));
    widgets.add(new PolldaddyWidgetPageObject(driver));

    String content = "### " + DateTime.now().getMillis() + " ###";

    for (WidgetPageObject widget : widgets) {
      content += widget.getTag();
    }

    ArticleContent articleContent = new ArticleContent();
    articleContent.clear(ARTICLE_NAME);
    articleContent.push(content, ARTICLE_NAME);
  }

  @Test(groups = "MercuryAllTagsWidgetTest_001")
  public void MercuryAllTagsWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    init();
    navigation.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }

  @Test(groups = "MercuryAllTagsWidgetTest_002")
  public void MercuryAllTagsWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    init();
    navigation.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

    topBar.openNavigation();
    navigation.navigateToPage(ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }

  @Test(groups = "MercuryAllTagsWidgetTest_003")
  public void MercuryAllTagsWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    init();
    navigation.openArticleOnWikiByNameWithCbAndNoAds(wikiURL, ARTICLE_NAME);

    topBar.openNavigation();
    navigation.navigateToPage(MAPS_ARTICLE_NAME);
    topBar.openNavigation();
    navigation.navigateToPage(ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }
}
