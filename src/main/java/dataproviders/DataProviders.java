package dataproviders;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginDataProvider", parallel = true)
    public synchronized Object[][] loginDataProvider(){
        return new Object[][]{
                {"", ""},
                {"", "password"},
                {"username", ""}
        };
    }


}
