// ZipCode.java
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChooseTypeStorage {

    WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void setUP(){
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

@Test
    public void chooseStorage (){
        selectFromMenu("Storage", "Move-In Online Today!");
        WebElement drpStorage = driver.findElement(By.id("storageSelectbox"));
        drpStorage.click();
        WebElement chckBox = driver.findElement(By.xpath("//*[@id=\"storageFilters\"]/ul/li[2]/label"));
        chckBox.click();
    }
@Test
    public void chooseMultplStorage(){
        selectFromMenu("Storage", "Move-In Online Today!");
        WebElement drpStorage = driver.findElement(By.id("storageSelectbox"));
        drpStorage.click();
        WebElement chckBox1 = driver.findElement(By.xpath("//*[@id=\"storageFilters\"]/ul/li[2]/label"));
        WebElement chckBox2 = driver.findElement(By.xpath("//*[@id=\"storageFilters\"]/ul/li[3]/label"));
        chckBox1.click();
        chckBox2.click();
    }
}
