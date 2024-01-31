import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class ChooseLocation {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10 seconds wait


    @BeforeClass
    public void setUp(){
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://www.uhaul.com/Storage/");

    }

    @AfterClass
    public void closeDown(){
        driver.quit();
    }

    private void selectFromMenu (String topMenuItem, String subMenuItem){
        WebElement mainMenu = driver.findElement(By.linkText(topMenuItem));
        Actions actions = new Actions(driver);
        actions.moveToElement(mainMenu).perform();
        WebElement subMenu = driver.findElement(By.linkText(subMenuItem));
        subMenu.click();
    }
    

@Test (priority = 1)
public void chooseLocationZipCode(){
    selectFromMenu("Storage", "Move-In Online Today!");
    WebElement dropDownLocation = driver.findElement(By.xpath("//*[@id=\"movingFromInput\"]"));
    WebElement fndStorageButton =  driver.findElement(By.xpath("//*[@id=\"locationSearchForm\"]/fieldset/div/div/div[4]/button"));
    dropDownLocation.click();
    dropDownLocation.sendKeys("9810");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-menu-item-wrapper")));
    //locate the autocomplete option
    WebElement autoOption = driver.findElement(By.className("ui-menu-item-wrapper"));
    autoOption.click();
    fndStorageButton.click();
    String actualmessage = driver.findElement(By.xpath("//*[@id=\"mainRow\"]/div[1]/div/div/h1")).getText();
    Assert.assertTrue(actualmessage.contains("Find a Storage Unit Near You in"),"\n message does not contain: Find a Storage Unit Near You in ");
}
@Test (priority = 2)
public void chooseLocationCity(){
    selectFromMenu("Storage", "Move-In Online Today!");
    WebElement dropDownLocation = driver.findElement(By.xpath("//*[@id=\"movingFromInput\"]"));
    WebElement fndStorageButton = driver.findElement(By.xpath("//*[@id=\"locationSearchForm\"]/fieldset/div/div/div[4]/button"));
    dropDownLocation.click();
    dropDownLocation.sendKeys("New York");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-menu-item-wrapper")));
    //locate the autocomplete option
    WebElement autoOption = driver.findElement(By.className("ui-menu-item-wrapper"));
    autoOption.click();
    fndStorageButton.click();
}

@Test (priority = 3)
public void chooseLocationState(){
    selectFromMenu("Storage", "Move-In Online Today!");
    WebElement dropDownLocation = driver.findElement(By.xpath("//*[@id=\"movingFromInput\"]"));
    WebElement fndStorageButton = driver.findElement(By.xpath("//*[@id=\"locationSearchForm\"]/fieldset/div/div/div[4]/button"));
    dropDownLocation.click();
    dropDownLocation.sendKeys("California");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ui-menu-item-wrapper")));
    //locate the autocomplete option
    WebElement autoOption = driver.findElement(By.className("ui-menu-item-wrapper"));
    autoOption.click();
    fndStorageButton.click();
}
@Test (priority = 4)
public void chooseBadLocation (){
    selectFromMenu("Storage", "Move-In Online Today!");

    try {
        WebElement dropDownLocation = driver.findElement(By.xpath("//*[@id=\"movingFromInput\"]"));
        WebElement fndStorageButton = driver.findElement(By.xpath("//*[@id=\"locationSearchForm\"]/fieldset/div/div/div[4]/button"));
        dropDownLocation.click();
        dropDownLocation.sendKeys("*(&$(*@)@");
        dropDownLocation.sendKeys(Keys.ENTER);
        fndStorageButton.click();
        String actualmessage = driver.findElement(By.xpath("//*[@id=\"locationSearchForm\"]/fieldset/div/div[1]/ul/li/text()")).getText();
        System.out.println(actualmessage);
        Assert.assertFalse(actualmessage.contains("We could not find your location. Please enter your zip/postal code, city or address again."), "\n message does not contain: We could not find your location. Please enter your zip/postal code, city or address again.");
    } catch (Exception e){
        e.printStackTrace();
    }
}

}