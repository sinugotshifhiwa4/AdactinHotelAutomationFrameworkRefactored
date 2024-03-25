package com.automation.steps.pages;

import com.automation.steps.reusableComponents.ExceptionHandler;
import com.automation.steps.utilities.DriverFactory;
import com.automation.steps.utilities.TestBase;
import org.openqa.selenium.By;

public class SelectHotelPage extends TestBase {

    ExceptionHandler handler = new ExceptionHandler();
    By radioBtn = By.xpath("//*[@id=\"radiobutton_0\"]");
    By continueBtn = By.xpath("//input[@id='continue']");
    By bookHotelTitle = By.xpath("//*[@id=\"book_hotel_form\"]/table/tbody/tr[2]/td");


    public void selectHotel(){

        try{
            clickObjects(DriverFactory.getInstance().getDriver().findElement(radioBtn), "SelectHotel");
            clickObjects(DriverFactory.getInstance().getDriver().findElement(continueBtn), "ContinueButton");

            isElementPresentObjects(DriverFactory.getInstance().getDriver().findElement(bookHotelTitle), "BookHotelTitle");
            captureScreenshot("BookHotelPage");

        } catch (Exception e) {
            handler.handleException("selectHotel", e);
        }

    }
}
