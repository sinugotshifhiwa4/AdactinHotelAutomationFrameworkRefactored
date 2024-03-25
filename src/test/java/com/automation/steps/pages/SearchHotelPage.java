package com.automation.steps.pages;

import com.automation.steps.reusableComponents.ExceptionHandler;
import com.automation.steps.utilities.DriverFactory;
import com.automation.steps.utilities.TestBase;
import org.openqa.selenium.By;

public class SearchHotelPage extends TestBase {

    ExceptionHandler handler  = new ExceptionHandler();

    By location = By.xpath("//*[@id=\"location\"]");
    By hotels = By.xpath("//*[@id=\"hotels\"]");
    By roomType = By.xpath("//*[@id=\"room_type\"]");
    By numberOfRooms = By.xpath("//*[@id=\"room_nos\"]");
    By checkInDate = By.xpath("//*[@id=\"datepick_in\"]");
    By checkOutDate = By.xpath("//*[@id=\"datepick_out\"]");
    By adultsPerRoom = By.xpath("//*[@id=\"adult_room\"]");
    By childrenPerRoom = By.xpath("//*[@id=\"child_room\"]");
    By searchBtn = By.xpath("//*[@id=\"Submit\"]");

    //validate
    By radioBtnToSelectHotel = By.xpath("//*[@id=\"radiobutton_0\"]");

    public void searchHotel(String Location, String Hotels, String RoomType, String NumberOfRooms, String CheckInDate, String CheckOutDate, String AdultsPerRoom, String ChildrenPerRoom){

        try{
            selectDropDownByVisibleTextObjects(DriverFactory.getInstance().getDriver().findElement(location), "LocationField", Location);
            selectDropDownByVisibleTextObjects(DriverFactory.getInstance().getDriver().findElement(hotels), "HotelsField", Hotels);
            selectDropDownByVisibleTextObjects(DriverFactory.getInstance().getDriver().findElement(roomType), "RoomTypeField", RoomType);
            selectDropDownByVisibleTextObjects(DriverFactory.getInstance().getDriver().findElement(numberOfRooms), "NumberOfRoomsField", NumberOfRooms);

            clearObjects(DriverFactory.getInstance().getDriver().findElement(checkInDate), "checkInDateField");
            passDateObjects(DriverFactory.getInstance().getDriver().findElement(checkInDate), "checkInDateField", CheckInDate);
            clearObjects(DriverFactory.getInstance().getDriver().findElement(checkOutDate), "CheckOutDateField");
            passDateObjects(DriverFactory.getInstance().getDriver().findElement(checkOutDate), "CheckOutDateField", CheckOutDate);

            selectDropDownByVisibleTextObjects(DriverFactory.getInstance().getDriver().findElement(adultsPerRoom), "AdultsPerRoomField", AdultsPerRoom);
            selectDropDownByVisibleTextObjects(DriverFactory.getInstance().getDriver().findElement(childrenPerRoom), "ChildrenPerRoomField", ChildrenPerRoom);
            Thread.sleep(2000);
            captureScreenshot("Check all data is available");
            clickObjects(DriverFactory.getInstance().getDriver().findElement(searchBtn), "SearchButton");

            isElementPresentObjects(DriverFactory.getInstance().getDriver().findElement(radioBtnToSelectHotel), "RadioButton");
            Thread.sleep(2000);
            captureScreenshot("SelectHotel");

        } catch (Exception e) {
            handler.handleException("searchHotel", e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
