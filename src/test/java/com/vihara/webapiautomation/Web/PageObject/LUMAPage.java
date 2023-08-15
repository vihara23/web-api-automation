package com.vihara.webapiautomation.Web.PageObject;

import com.vihara.webapiautomation.Web.testCases.BaseUITest;
import com.vihara.webapiautomation.utilities.ScreenshotUtil;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.vihara.webapiautomation.Web.testCases.BaseUITest.action;

// page_url = https://magento.softwaretestingboard.com/

public class LUMAPage {
    WebDriver driver = BaseUITest.driver;
    Logger logger = BaseUITest.logger;
    @FindBy(xpath = "//*[@id='search']")
    public WebElement inputSearch;

    @FindBy(xpath = "//button[@title='Search']")
    public WebElement buttonSearch;

    @FindBy(xpath = "//span[@class='base']")
    public WebElement searchResultTitle;

    @FindBy(css = "a[id='ui-id-4'] span[class^='ui-menu-icon']")
    public WebElement linkWomen;

    @FindBy(xpath = "//a[@id='ui-id-9']//span[contains(text(),'Tops')]")
    public WebElement linkTops;

    @FindBy(xpath = "//div[normalize-space()='Category']")
    public WebElement filterCategory;

    @FindBy(xpath = "//a[@href='https://magento.softwaretestingboard.com/women/tops-women.html?cat=26']")
    public WebElement linkBrasTanksItem;

    @FindBy(xpath = "//div[@role='presentation'][.//div[@option-id='52']]")
    public WebElement filterColor;

    @FindBy(css = "a[aria-label='Red'] div[class^='swatch-option']")
    public WebElement selectRed;

    @FindBy(xpath = "//div[@class='swatch-opt-1764']//div[@id='option-label-color-93-item-58']")
    public WebElement item1RedColor;

    @FindBy(xpath = "//div[@class='swatch-opt-1732']//div[@id='option-label-color-93-item-58']")
    public WebElement item2RedColor;

    @FindBy(xpath = "//div[@class='swatch-opt-1716']//div[@id='option-label-color-93-item-58']")
    public WebElement item3RedColor;

    @FindBy(xpath = "//div[@class='swatch-opt-1636']//div[@id='option-label-color-93-item-58']")
    public WebElement item4RedColor;

    @FindBy(xpath = "//a[normalize-space()='Chloe Compete Tank']")
    public WebElement item1Title;

    @FindBy(xpath = "//a[normalize-space()='Nona Fitness Tank']")
    public WebElement item2Title;

    @FindBy(xpath = "//a[normalize-space()='Nora Practice Tank']")
    public WebElement item3Title;

    @FindBy(xpath = "//a[normalize-space()='Celeste Sports Bra']")
    public WebElement item4Title;

    @FindBy(xpath = "//span[normalize-space()='Gear']")
    public WebElement linkGear;

    @FindBy(xpath = "//a[@href='https://magento.softwaretestingboard.com/fusion-backpack" +
            ".html']//span[@class='product-image-container']//span[@class='product-image-wrapper']//img[@alt='Image']")
    public WebElement itemBackpack;

    @FindBy(xpath = "//li[1]//div[1]//div[1]//div[3]//div[1]//div[1]//form[1]//button[1]//span[1]")
    public WebElement addToCartButton;

    @FindBy(xpath = "//div[contains(@data-bind, 'text')]")
    public WebElement successAddToCartMessage;

    @FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[2]/div[2]/ol/li[1]/div/div/strong/a")
    public WebElement searchResultItem1;

    @FindBy(xpath = "/html/body/div[2]/main/div[3]/div[1]/div[2]/div[2]/ol/li[2]/div/div/strong/a")
    public WebElement searchResultItem2;

    public LUMAPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Perform search results")
    public void Search() {
        inputSearch.click();
        ScreenshotUtil.captureScreenshot("Home Page");
        inputSearch.sendKeys("Bag");
        ScreenshotUtil.captureScreenshot("Input Search Item");
        buttonSearch.click();
        ScreenshotUtil.captureScreenshot("Search Results");
        Assert.assertTrue(searchResultItem1.getText().contains("Bag"));
        Assert.assertTrue(searchResultItem2.getText().contains("Bag"));
    }


    public void itemFilterByColor() throws InterruptedException {

        action.moveToElement(linkWomen).moveToElement(linkTops).click().build().perform();

        filterCategory.click();
        linkBrasTanksItem.click();
        filterColor.click();
        selectRed.click();

        Thread.sleep(2000);
    }

    public void addGearItem() throws InterruptedException {
//        driver.get("https://magento.softwaretestingboard.com/");
        linkGear.click();

        action.moveToElement(itemBackpack).moveToElement(addToCartButton).click().build()
                .perform();
        Thread.sleep(2000);
    }

    public Boolean validateItemTitle(String title) throws Exception {
        try {
            if (title.contains("Tank") || title.contains("Bra")) {
                return true;
            }
        } catch (Exception e) {
            logger.info("Item is not a Tank or Bra : " + e.getMessage());
        }
        return false;
    }
}
