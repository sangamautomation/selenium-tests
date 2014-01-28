package com.wikia.webdriver.PageObjectsFactory.PageObject.Mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

public class MobileEditModePageObject extends MobileBasePageObject {

	public MobileEditModePageObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="#wpTextbox1")
	private WebElement textArea;
	@FindBy(css="#wkMainCntHdr > h1")
	private WebElement selectedPageHeader;
	@FindBy(css="#wkMobileCancel")
	private WebElement editCancelButton;
	@FindBy(css="#wkPreview")
	private WebElement editPreviewButton;
	
	private String editPreviewButtonString = "#wkPreview";

	public MobileArticlePageObject clickCancel() {
		editCancelButton.click();
		return new MobileArticlePageObject(driver);
	}
	
	public String getHeader(){
		return selectedPageHeader.getText();
	}
	
	public MobileEditPreviewPageObject clickPreview() {
		WebElement editPreviewButton = waitForElementByCss(editPreviewButtonString);
		scrollAndClick(editPreviewButton);
		return new MobileEditPreviewPageObject(driver);
	}
	
	public String getEditArticleName() {
		String header = getHeader();
		return header.substring(header.indexOf(' ') + 1);
	}
	
	public String getModeName() {
		String header = getHeader();
		return header.substring(0, header.indexOf(' '));
	}
	
	public void verifyEditArticleName() {
		String url = driver.getCurrentUrl();
		String urlArticleName =
			url.substring(url.indexOf(PageContent.articleNamePrefix), url.indexOf('?'));
		Assertion.assertEquals(urlArticleName, getEditArticleName());
		PageObjectLogging.log("verifyModeName", 
				"verifying the article shows '" + urlArticleName + "'", true);
	}
	
	public void verifyModeName() {
		Assertion.assertEquals(PageContent.editModeHeader, getModeName());
		PageObjectLogging.log("verifyModeName", 
				"verifying the header shows '" + PageContent.editModeHeader + "'", true);
	}
	
	public void enterEditText(String text) {
		textArea.sendKeys(text);
	}
	
	public String getEditText() {
		return textArea.getAttribute("value");
	}
	
	public void verifyEditText(String targetText) {
		Assertion.assertEquals(targetText, getEditText());
		PageObjectLogging.log("verifyEditText", 
				"verifying the summary shows " + targetText, true);
	}
}
