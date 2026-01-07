package DemoTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GeoLocation_SeleniumGrid {
    public WebDriver driver;
    private String username = "erickfloresovando";
    private String accessKey = "";
    private String hub = "@hub.lambdatest.com/wd/hub";
    DesiredCapabilities caps = new DesiredCapabilities();

    @BeforeClass
    public void setUp() throws  MalformedURLException {
        ChromeOptions options = new ChromeOptions();

        // === W3C standard capabilities ===
        options.setCapability("browserName", "Chrome");
        options.setCapability("browserVersion", "103.0");
        options.setCapability("platformName", "Windows 10");

        // === LambdaTest specific capabilities ===
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "GeoLocation Build");
        ltOptions.put("name", "Test GeoLocation");
        ltOptions.put("geoLocation", "IN");
        ltOptions.put("console", true);
        ltOptions.put("network", true);
        ltOptions.put("visual", true);

        options.setCapability("lt:options", ltOptions);

        driver = new RemoteWebDriver(
                new URL("https://" + username + ":" + accessKey + hub),
                options
        );

        driver.get("https://where-am-i.org/");
    }


    @Test
    public void testGeoLocation(){
        WebElement address = driver.findElement(By.id("address"));
        System.out.println("Address: " + address.getText());

    }
}
