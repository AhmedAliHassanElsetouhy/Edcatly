package pages;

import base.BaseTest;
import config.DashboardPageLocatorsValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigUtils;

public class DashboardPage extends BaseTest {
    private WebDriver driver;
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }
    private By WhatToStudySearchBox = By.id(DashboardPageLocatorsValues.WHAT_TO_STUDY_LOCATOR_ID);
    private By WhatToStudyValue = By.xpath(DashboardPageLocatorsValues.WHAT_TO_STUDY_VALUE_LOCATOR_xPATH);
    private By WhereToStudySearchBox = By.id(DashboardPageLocatorsValues.WHERE_TO_STUDY_LOCATOR_ID);
    private By WhereToStudyValue = By.xpath(DashboardPageLocatorsValues.WHERE_TO_STUDY_VALUE_LOCATOR_xPATH);
    private By SearchButton = By.className(DashboardPageLocatorsValues.SEARCH_BUTTON_MAIN_PAGE);
    protected By WhereSearchedLabel = By.xpath(DashboardPageLocatorsValues.WHERE_SEARCHED_LABEL_VALUE);
    public void loadDashboardPage(){
        driver.get(ConfigUtils.getInstance().getBaseUrl());
    }

    public void UserSearchWhatToStudy(){
        waitUntilElementToBeVisibleThenSendKeys(driver, WhatToStudySearchBox, ConfigUtils.getInstance().getWhatToStudy());
        pressEnterKey(driver);
    }
    public String getWhatToStudyValue(){
       return findElement(driver, WhatToStudyValue).getText();
    }

    public void UserSearchWhereToStudy(){
        waitUntilElementToBeVisibleThenSendKeys(driver, WhereToStudySearchBox, ConfigUtils.getInstance().getWhereToStudy());
        pressEnterKey(driver);
    }

    public String getWhereToStudyValue(){
        return findElement(driver, WhereToStudyValue).getText();
    }

    public void clickOnSearchForSpecificCriteria(){
        waitUntilElementToBeClickableThenClick(driver, SearchButton);
    }
    public String getWhereToStudyLabelValue(){
        return findElement(driver, WhereSearchedLabel).getText();
    }
}