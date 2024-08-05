package org.lab4.wed.weblab4;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = FunctionalTest.class)
public class FunctionalTest {
    
    @Test
    public void functionalTest() {
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.youtube.com/");
    }
}
