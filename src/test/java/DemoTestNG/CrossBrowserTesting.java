package DemoTestNG;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CrossBrowserTesting {

    public WebDriver driver;

    // Credenciales de LambdaTest
    private String username = "erickfloresovando";
    private String accessKey = "";

    @Parameters({"Browser", "Version", "Platform"})
    @BeforeMethod
    public void setUp(String browser, String version, String platform) {

        MutableCapabilities options;

        // Selección de navegador
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chrome = new ChromeOptions();
            chrome.setBrowserVersion(version);
            chrome.setPlatformName(platform);
            options = chrome;
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions edge = new EdgeOptions();
            edge.setBrowserVersion(version);
            edge.setPlatformName(platform);
            options = edge;
        } else {
            throw new IllegalArgumentException("Browser no soportado: " + browser);
        }

        // Configuración de LambdaTest
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "2.1");
        ltOptions.put("name", "Cross Browser Testing");
        ltOptions.put("network", true);
        ltOptions.put("console", true);
        ltOptions.put("visual", true);
        ltOptions.put("tunnel", false); // Cambiar a true si pruebas en local

        options.setCapability("LT:options", ltOptions);

        // Conexión a LambdaTest
        try {
            driver = new RemoteWebDriver(
                    new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"),
                    options
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Abrir URL inicial
        if (driver != null) {
            driver.get("https://www.lambdatest.com/selenium-playground/");
        }
    }

    // Test 1: Selección en dropdown
    @Test
    public void testDropDowns() {
        driver.findElement(By.linkText("Select Dropdown List")).click();

        WebElement findDropDown = driver.findElement(By.id("select-demo"));
        Select dayDropDown = new Select(findDropDown);

        dayDropDown.selectByVisibleText("Saturday");

        // Validar selección
        String selected = dayDropDown.getFirstSelectedOption().getText();
        assert selected.equals("Saturday") : "El día seleccionado no es Saturday";
    }

    // Test 2: Drag and Drop
    @Test
    public void testDragAndDrop() {
        driver.findElement(By.linkText("Drag and Drop")).click();

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        // Drag and drop confiable
        Actions act = new Actions(driver);
        act.clickAndHold(source).moveToElement(target).release().perform();

        // Validar que se realizó el drop
        String textAfterDrop = target.getText();
        assert textAfterDrop.contains("Dropped") : "Drag and Drop falló";
    }



}
