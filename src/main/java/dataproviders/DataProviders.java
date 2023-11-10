package dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider(){
        return new Object[][]{
                {"", ""},
                {"", "password"},
                {"username", ""}
        };
    }


}
