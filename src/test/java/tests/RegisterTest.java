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

    @Test(description = "Register user happy path; Expected result: User is successfully registered")
    public void registerTest(){
        registerPage.goToRegisterPage()
                .registerPage();
        login.loginUser(registerPage.getUsername(), registerPage.getPassword());
        Assert.assertTrue(registerPage.isUserRegistered(), "User is not registered!");
    }

    @Test
    public void negativeRegisterTest(){

    }


}
