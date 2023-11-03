package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class RegisterTest extends BaseTest{

    RegisterPage registerPage;


    @BeforeMethod
    public void setupRegister(){
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void registerTest(){
        registerPage.goToRegisterPage()
                .registerPage();
    }

    @Test
    public void negativeRegisterTest(){

    }


}
