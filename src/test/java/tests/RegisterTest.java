package tests;

import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterPage;
@Listeners(TestListener.class)
public class RegisterTest extends BaseTest{

    RegisterPage registerPage;
    LoginPage login;


    @BeforeMethod
    public void setupRegister(){
        registerPage = new RegisterPage(driver);
        login = new LoginPage(driver);
    }

    //nacin 1
    @Test(description = "Register user happy path; Expected result: User is successfully registered")
    public void registerTest(){
        registerPage.goToRegisterPage()
                .registerUser();
        login.loginUser(registerPage.getUsername(), registerPage.getPassword());
        Assert.assertTrue(registerPage.isUserRegisteredAndLoggedIn(), "User is not registered!");
    }

    //nacin2
    @Test(description = "Register user happy path; Expected result: User is successfully registered")
    public void registerTest2(){
        registerPage.goToRegisterPage()
                .registerUser();
        login.loginUser(registerPage.getUsername(), registerPage.getPassword());

        String expectedText = "My account";
        Assert.assertEquals(registerPage.actualText(), expectedText, "User is not registered!");
    }

    //nacin3
    @Test(description = "Register user happy path; Expected result: User is successfully registered")
    public void registerTest3(){
        registerPage.goToRegisterPage()
                .registerUser();
        login.loginUser(registerPage.getUsername(), registerPage.getPassword());

        registerPage.isUserRegisteredAndLoggedIn2();
    }




    @Test
    public void negativeRegisterTest(){

    }


}
