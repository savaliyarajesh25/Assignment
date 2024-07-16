package com.rajesh.assignment.amazon;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rajesh.configTest.BaseTest;
import com.rajesh.utils.ConstantPaths;

public class AmazonTest extends BaseTest{

	@Test()
	public void ValidateDropDownSuggestion() throws IOException, InterruptedException {
		AmazonPage amazonPage = new AmazonPage(getDriver()).load();
		amazonPage.btnsearchDropdown("Electronics");
		//amazonPage.linkDropdownOption("Electronics");
		amazonPage.txtSearchBox("iphone 13");
		Assert.assertEquals(amazonPage.validateDropdownProducts(), true);
	}
	
	@Test()
	public void VerifyQuickLook() throws IOException, InterruptedException {
		AmazonPage amazonPage = new AmazonPage(getDriver()).load();
		amazonPage.txtSearchBox("IPhone 13 128 GB");
		amazonPage.linkDropdownSearch("iphone 13 128 gb");
		String originalWindow = getDriver().getWindowHandle();
		amazonPage.linkProduct("Apple iPhone 13 (128GB)");
		for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
            	getDriver().switchTo().window(windowHandle);
                break;
            }
        }
		WebDriverWait wait = new WebDriverWait(getDriver(), ConstantPaths.getExplicitWaitOfSeconds());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Visit the Apple Store')]")));
		Assert.assertTrue(getDriver().getTitle().contains("Apple iPhone 13 (128GB)"));
		
		amazonPage.linkAppleStore();
		amazonPage.drpAppleWatch();
		amazonPage.appleWatchOption();
		amazonPage.hoverWatchImage();
		Assert.assertEquals(amazonPage.verifyQuickLookModal(), true);
		
		Assert.assertEquals(amazonPage.getTextOfQuickLookModal(), "Apple Watch SE (2nd Gen)[GPS + Cellular 40 mm]smart watch w/Starlight Aluminium Case & Starlight Sport Band...");
			
	}
}
