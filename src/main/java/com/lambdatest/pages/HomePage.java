package com.lambdatest.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    //Variables private
    private By bootstrapProgressBar = By.linkText("Bootstrap Progress bar");

    //Methods public
    public BootstrapProgressBarPage clickBootstrapProgressBar(){
        click(bootstrapProgressBar);
        return new BootstrapProgressBarPage();
    }
}
