package com.rajesh.assignment.amazon;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rajesh.enums.WaitStrategy;
import com.rajesh.utils.ConstantPaths;

import io.netty.util.Constant;

public class AmazonPage extends BasePage{
	
	public AmazonPage(WebDriver driver) {
        super(driver);
    }
	
	public AmazonPage load() {
        load("");
        return this;
    };
    
    private final By btnSearchDropdown = By.xpath("//*[@id='searchDropdownBox']");
    private final By txtSearchBox = By.id("twotabsearchtextbox");
    private final By listSuggestions  = By.cssSelector(".s-suggestion"); 
    private final By linkAppleStore = By.xpath("//a[contains(text(),'Visit the Apple Store')]");
    private final By drpAppleWatch = By.xpath("//span[contains(text(), 'Apple Watch')]");
    private final By appleWatchOption = By.linkText("Apple Watch SE (GPS + Cellular)");
    private final By watchImage = By.xpath("//a[contains(@title,'Apple Watch SE (2nd Gen)[GPS + Cellular 40 mm]')]");
    private final By quickLookModal = By.xpath("//button[contains(@aria-label,'Quick look')]");
    private final By quickLookModalText = By.xpath("//h2[@id='asin-title']//a");
    

    public boolean validateDropdownProducts() {
    	boolean flag = false;
    	WebDriverWait wait = new WebDriverWait(driver, ConstantPaths.getExplicitWaitOfSeconds());
    	List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".s-suggestion")));
    	//List<WebElement> suggestions = driver.findElements(listSuggestions);
        for (WebElement suggestion : suggestions) {
        	if(suggestion.getText().contains("iphone 13")) {
        		flag = true;
        		
        	}else {
        		flag = false;
        	}
        }
		return flag;
    }
    
    public AmazonPage btnsearchDropdown(String product)  {
    	sendKeys(btnSearchDropdown, product, WaitStrategy.PRESENCE, "SearchDropdown");
        return this;
    }
    
    public AmazonPage linkDropdownOption(String product) {
        click(By.xpath("//option[text()='"+product+"']"), WaitStrategy.CLICKABLE, "Electronics");
        return this;
    }
    
    public AmazonPage txtSearchBox(String product) {
        sendKeys(txtSearchBox, product, WaitStrategy.PRESENCE, "search box");
        return this;
    }
    
    public AmazonPage linkDropdownSearch(String product) {
        click(By.xpath("//div[contains(text(),'"+product+"')]"), WaitStrategy.CLICKABLE, "Electronics");
        return this;
    }
    
    public AmazonPage linkProduct(String product) {
        click(By.xpath("//span[contains(text(),'"+product+"')]"), WaitStrategy.CLICKABLE, "Electronics");
        return this;
    }
    
    public boolean validateNewTabIsOpen() {
    	boolean flag = false;
    	String originalWindow = driver.getWindowHandle();
        WebElement productLink = driver.findElement(By.xpath("//span[contains(text(),'Apple iPhone 13 (128GB)')]"));
        productLink.click();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
		return flag;
    }
    
    public AmazonPage linkAppleStore() {
        click(linkAppleStore, WaitStrategy.CLICKABLE, "Apple Store Link");
        return this;
    }
    
    public AmazonPage drpAppleWatch() throws InterruptedException {
    	Thread.sleep(5000);   
    	Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(drpAppleWatch)).click().perform();
        return this;
    }
    
    public AmazonPage appleWatchOption() {
        click(appleWatchOption, WaitStrategy.CLICKABLE, "Apple Watch Option");
        return this;
    }
    
    public AmazonPage hoverWatchImage() {
    	Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(watchImage)).perform();
        return this;
    }
    
    public boolean verifyQuickLookModal() {
    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(watchImage));
    	hoverWatchImage();
		return driver.findElement(quickLookModal).isDisplayed();
    }
    
    
    public String getTextOfQuickLookModal() {
    	click(quickLookModal, WaitStrategy.CLICKABLE, "Quick Look");
		return driver.findElement(quickLookModalText).getText();
    }
}
