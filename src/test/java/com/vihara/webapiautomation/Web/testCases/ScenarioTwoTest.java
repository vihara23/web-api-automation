package com.vihara.webapiautomation.Web.testCases;

import lombok.extern.java.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Log
public class ScenarioTwoTest extends BaseUITest {

    @Test
    public void scenarioTwo() {
        try {
            lumaPage.itemFilterByColor();
            
            assertEquals(driver.getCurrentUrl(), "https://magento.softwaretestingboard.com/women/tops-" +
                    "women.html?cat=26&color=58");

            Assert.assertTrue(lumaPage.validateItemTitle(lumaPage.item1Title.getText()));
            Assert.assertTrue(lumaPage.validateItemTitle(lumaPage.item2Title.getText()));
            Assert.assertTrue(lumaPage.validateItemTitle(lumaPage.item3Title.getText()));
            Assert.assertTrue(lumaPage.validateItemTitle(lumaPage.item4Title.getText()));

            assertEquals(lumaPage.item1RedColor.getAttribute("id").substring(13, 21), "color-93");
            assertEquals(lumaPage.item2RedColor.getAttribute("id").substring(13, 21), "color-93");
            assertEquals(lumaPage.item3RedColor.getAttribute("id").substring(13, 21), "color-93");
            assertEquals(lumaPage.item4RedColor.getAttribute("id").substring(13, 21), "color-93");
        } catch (Exception e) {
            log.info("scenarioTwo Test Failed!!:" + e.getMessage());
        }
    }
}
