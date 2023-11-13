package main.pages;

import main.browserutility.Browser;
import org.openqa.selenium.support.PageFactory;
public final class PagesFactory {
    private static <T> T GetPage(Class<T> className){
        return PageFactory.initElements(Browser.getWebDriver(), className);
    }

    /**
     * Method to initiate Login page
     * @return
     */
    public static LoginPage getLoginPage(){
        return GetPage(LoginPage.class);
    }

    /**
     * Method to initiate Home page
     * @return
     */
    public static HomePage getHomePage(){
        return GetPage(HomePage.class);
    }

    /**
     * Method to initiate ReserveADeskPage page
     * @return
     */
    public static ReserveADeskPage reserveADeskPage(){
        return GetPage(ReserveADeskPage.class);
    }

    /**
     * Method to initiate Reservations page
     * @return
     */
    public static ReservationsPage reservationsPage(){
        return GetPage(ReservationsPage.class);
    }
}
