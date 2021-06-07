package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testContactCreation() throws Exception {
    driver.get("http://localhost/addressbook/index.php");
    driver.findElement(By.name("user")).clear();
    driver.findElement(By.name("user")).sendKeys("admin");
    driver.findElement(By.name("pass")).click();
    driver.findElement(By.name("pass")).clear();
    driver.findElement(By.name("pass")).sendKeys("secret");
    driver.findElement(By.xpath("//input[@value='Login']")).click();
    driver.findElement(By.linkText("add new")).click();
    driver.findElement(By.name("firstname")).click();
    driver.findElement(By.name("firstname")).clear();
    driver.findElement(By.name("firstname")).sendKeys("Test");
    driver.findElement(By.name("lastname")).click();
    driver.findElement(By.name("lastname")).clear();
    driver.findElement(By.name("lastname")).sendKeys("Test");
    driver.findElement(By.name("address")).click();
    driver.findElement(By.name("address")).clear();
    driver.findElement(By.name("address")).sendKeys("Am Helmholtzring\n4d");
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("kseniia.karmaleeva@tu-ilmenau.de");
    driver.findElement(By.name("email2")).clear();
    driver.findElement(By.name("email2")).sendKeys("kseniia.karmaleeva@tu-ilmenau.de");
    driver.findElement(By.name("email3")).clear();
    driver.findElement(By.name("email3")).sendKeys("kseniia.karmaleeva@tu-ilmenau.de");
    driver.findElement(By.name("address2")).clear();
    driver.findElement(By.name("address2")).sendKeys("Ilmenau");
    driver.findElement(By.name("mobile")).click();
    driver.findElement(By.name("mobile")).clear();
    driver.findElement(By.name("mobile")).sendKeys("71111111111");
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("email3")).clear();
    driver.findElement(By.name("email3")).sendKeys("");
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("email2")).clear();
    driver.findElement(By.name("email2")).sendKeys("");
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("kind-kar@mail.ru");
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.name("address2")).clear();
    driver.findElement(By.name("address2")).sendKeys("");
    driver.findElement(By.name("theform")).click();
    driver.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    driver.findElement(By.linkText("home page")).click();
    driver.findElement(By.linkText("Logout")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
