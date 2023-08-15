package com.vihara.webapiautomation.Web.testCases;

import lombok.extern.java.Log;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Log
public class ScenarioOneTest extends BaseUITest {

    @Test
    public void scenarioOne() {
        try {
            logger.info("Scenario One test started");
            lumaPage.Search();
            assertEquals(lumaPage.searchResultTitle.getText(), "Search results for: 'Bag'");
            logger.info("Scenario One test finished");
        } catch (Exception e) {
            logger.error("Search Test Failed!!:", e);
        }
    }

}
