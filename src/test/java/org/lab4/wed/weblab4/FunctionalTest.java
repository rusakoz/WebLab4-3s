package org.lab4.wed.weblab4;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FunctionalTest.class)
public class FunctionalTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new EdgeDriver();
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    @EnabledOnJre(JRE.JAVA_17)
    public void functionalTestLogin() {
        driver.get("http://localhost:8887/login");
        
        String title = driver.getTitle();
        assertTrue(title.equals("Четвертая лаба"));
        String url = driver.getCurrentUrl();
        assertTrue(url.equals("http://localhost:8887/login"));

        driver.findElement(By.className("name")).sendKeys("ruslan2");
        driver.findElement(By.className("password")).sendKeys("123456");

        driver.findElement(By.className("btn-auth")).click();

        // Ждем 5 секунд отображения страницы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main-btn")));
    }


    @Test
    @EnabledOnOs(OS.WINDOWS)
    @EnabledOnJre(JRE.JAVA_17)
    public void functionalTestRegistration() {
        driver.get("http://localhost:8887/registration");
        
        String title = driver.getTitle();
        assertTrue(title.equals("Четвертая лаба"));
        String url = driver.getCurrentUrl();
        assertTrue(url.equals("http://localhost:8887/registration"));

        Random rand = new Random();
        int numberName = rand.nextInt(10000) + 3;
        String name = "ruslan" + numberName;

        driver.findElement(By.className("name")).sendKeys(name);
        driver.findElement(By.id("firstPassword")).sendKeys("123456");
        driver.findElement(By.id("secondPassword")).sendKeys("123456");

        driver.findElement(By.className("btn-auth")).click();

        // Ждем 5 секунд отображения страницы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("to-reg")));
    }

    @AfterEach
    public void close() {
        driver.quit();
    }
}
