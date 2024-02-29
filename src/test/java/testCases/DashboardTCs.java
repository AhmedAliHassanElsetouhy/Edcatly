package testCases;

import base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import utils.ConfigUtils;

@Feature("Dashboard Features")
public class DashboardTCs extends BaseTest {
    @Story("Search Feature")
    @Test(description="Validate That Dashboard Open", enabled = true, priority = 1)
    public void ValidateThatWebsiteOpenTC(){
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.loadDashboardPage();
        Assert.assertTrue(getDriver().getTitle().equals(ConfigUtils.getInstance().getWebsiteTitle()));
    }
    @Test(description="Validate What To Test Search TxtField", enabled = true, priority = 2)
    public void SearchWhatToTest(){
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.UserSearchWhatToStudy();
        Assert.assertTrue(dashboardPage.getWhatToStudyValue().contains(ConfigUtils.getInstance().getWhatToStudy()));
    }
    @Test(description="Validate Where To Test Search TxtField", enabled = true, priority = 3)
    public void SearchWhereToTest(){
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.UserSearchWhereToStudy();
        Assert.assertTrue(dashboardPage.getWhereToStudyValue().contains(ConfigUtils.getInstance().getWhereToStudy()));
    }
    @Test(description="Validate that Search button Working Fine", enabled = true, priority = 4)
    public void SearchForCriteriaTest(){
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.clickOnSearchForSpecificCriteria();
        Assert.assertTrue(dashboardPage.getWhereToStudyLabelValue().contains(ConfigUtils.getInstance().getWhereToStudy()));
    }
}