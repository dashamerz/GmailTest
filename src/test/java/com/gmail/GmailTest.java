package com.gmail;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources"}
)

public class GmailTest {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

    }

    WebDriver driver = new ChromeDriver();
    String userName = "dashatestingforlanit@gmail.com";
    String password = "testingparol123";
    String toAddress = "dashamerz@gmail.com";
    String subject = "Testing";

    @Given("User logged in")
    public void userLoggedIn () {
        driver.manage().window().maximize();
        driver.get("https://accounts.google.com/ServiceLogin? service=mail&continue=https://mail.google.com/mail/&hl=en");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("identifierId")).sendKeys(userName);
        driver.findElement(By.id("identifierNext")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("passwordNext")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("User sending an email")
    public void userSendingAnEmail () {
        driver.findElement(By.xpath("//*[@id=\":3o\"]/div/div")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\":7u\"]")).sendKeys(toAddress);
        driver.findElement(By.name("subjectbox")).sendKeys(subject);
        driver.findElement(By.xpath("//*[@id=\":8l\"]")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Then("Checking sent email")
    public void checkingSentEmail () {
        driver.get("https://mail.google.com/mail/u/0/#sent");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

}
