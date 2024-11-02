package tests;

import dataproviders.DataProviders;
import listeners.RetryAnalyzer;
import listeners.TestListener;
import model.LoginUser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
import utils.Utils;

import java.util.List;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest{


    LoginPage login;
    RegisterPage registerPage;

    //shutterbug - visual testing

    @BeforeMethod(alwaysRun = true)
    public void loginSetup(){
        login = new LoginPage(driverThreadLocal.get());
        registerPage = new RegisterPage(driverThreadLocal.get());
    }


    @Test(retryAnalyzer = RetryAnalyzer.class, groups = "smoke")
    public void loginUserTest(){
        login.goToLoginForm()
                .loginUser("customer@practicesoftwaretesting.com", "welcome01");
        Assert.assertTrue(registerPage.isUserRegisteredAndLoggedIn());
    }

    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviders.class, groups = "smoke")
    public void invalidLoginTest(String username, String password){
        login.goToLoginForm()
                .loginUser(username, password);
        Assert.assertTrue(login.isErrorMessagePresent());
    }

    @Test()
    public void invalidLoginTestFromJson(){
        List<LoginUser> list = Utils.getDataFromJson();
        for (int i = 0; i < list.size(); i++) {
            login.goToLoginForm()
                    .loginUser(list.get(i).getUsername(), list.get(i).getPassword());
        }
        Assert.assertTrue(login.isErrorMessagePresent());
    }


//    @Test
//    public void lombokTest(){
//        LoginUser loginUserModel = LoginUser.builder()
//                .password("")
//                .username("")
//                .build();
//        System.out.println(loginUserModel);
//    }

    @Test()
    @Parameters({"username", "password"})
    public void loginUserFromTestngParameters(String username, String password){
        login.goToLoginForm()
                .loginUser(username, password);
        Assert.assertTrue(registerPage.isUserRegisteredAndLoggedIn());
    }



}
