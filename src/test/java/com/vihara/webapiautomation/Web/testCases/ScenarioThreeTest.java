package com.vihara.webapiautomation.Web.testCases;

import lombok.extern.java.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log
public class ScenarioThreeTest extends BaseUITest {

    @Test
    public void scenarioThree() {
        try {
            logger.info("Scenario Three test started");
            lumaPage.addGearItem();
            Assert.assertEquals(lumaPage.successAddToCartMessage.getText(), "You added Fusion Backpack to your shopping cart.");
            logger.info("Scenario Three test finished");
        } catch (Exception e) {
            logger.info("scenarioThree Test Failed!!:" + e.getMessage());
        }
    }

}
