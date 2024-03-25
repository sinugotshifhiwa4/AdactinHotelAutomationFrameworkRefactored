package com.automation.steps.tests;

import com.automation.steps.pages.LoginPage;
import com.automation.steps.pages.SearchHotelPage;
import com.automation.steps.pages.SelectHotelPage;
import com.automation.steps.reusableComponents.ExcelConfiguration;
import com.automation.steps.reusableComponents.ExceptionHandler;
import com.automation.steps.reusableComponents.PropertiesConfiguration;
import com.automation.steps.reusableComponents.RerunFailedTestsConfiguration;
import com.automation.steps.utilities.MyLogger;
import com.automation.steps.utilities.TestBase;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestCases extends TestBase {

    LoginPage loginPage = new LoginPage();
    ExceptionHandler handler = new ExceptionHandler();
    SearchHotelPage searchHotelPage = new SearchHotelPage();
    SelectHotelPage selectHotelPage = new SelectHotelPage();

    //@Test(retryAnalyzer = RerunFailedTestsConfiguration.class)
    public void testBookHotel(){

        try{
            // Read test data from Excel file
            String sExcelPath = PropertiesConfiguration.getValueByKey("excelPath");
            String sSheetName = PropertiesConfiguration.getValueByKey("sheetName");
            List<Map<String, String>> dataFromExcel = ExcelConfiguration.readExcelData(sExcelPath, sSheetName);

            // Iterate over test data and perform login
            for(Map<String, String> readData : dataFromExcel){

                loginPage.logIn(
                        readData.get("Username"),
                        readData.get("Password")
                );
                searchHotelPage.searchHotel(
                        readData.get("Location"),
                        readData.get("Hotels"),
                        readData.get("RoomType"),
                        readData.get("NumberOfRooms"),
                        readData.get("CheckInDate"),
                        readData.get("CheckOutDate"),
                        readData.get("AdultPerRoom"),
                        readData.get("ChildrenPerRoom")
                );
                selectHotelPage.selectHotel();
            }

            MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());

            MyLogger.info("Select  Hotel was successful");

        } catch (IOException e) {
            handler.handleException("testBookHotel", e);
        }
    }

    @Test()
    public void testInvalidLogin(){

        try{
            // Read test data from Excel file
            String sExcelPath = PropertiesConfiguration.getValueByKey("excelPath");
            String sSheetName = PropertiesConfiguration.getValueByKey("sheetName");
            List<Map<String, String>> dataFromExcel = ExcelConfiguration.readExcelData(sExcelPath, sSheetName);

            // Iterate over test data and perform login
            for(Map<String, String> readData : dataFromExcel){

                loginPage.performLogIn(
                        "lunah",
                        readData.get("Password")
                );

            }

            MyLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());

            MyLogger.info("Login was unsuccessful");

        } catch (IOException e) {
            handler.handleException("testInvalidLogin", e);
        }
    }
}
