package com.epam.cdp.bdd.wstesting;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.epam.cdp.bdd.wstesting.stepdef"})
public class Runner extends AbstractTestNGCucumberTests {

}
