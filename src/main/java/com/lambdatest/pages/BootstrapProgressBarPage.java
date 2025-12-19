package com.lambdatest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BootstrapProgressBarPage extends BasePage{

    //Variables private
    private By startDownloadButton = By.id("dwnBtn");
    private By progressBarPercentage = By.cssSelector(".counter");
    private By completedMessage = By.xpath("//p[contains(@class,'success')]");

    //Methods public
    public void clickStartDownloadButton(){
        click(startDownloadButton);
    }
    public String getProgressBarPercentage(){
        //Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(progressBarPercentage));
        return getText(progressBarPercentage);
    }

    public String getCompletedMessage(){
        //Explicit Wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(completedMessage));
        return getText(completedMessage);
    }


}
