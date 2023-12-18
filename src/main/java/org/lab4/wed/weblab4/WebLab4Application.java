package org.lab4.wed.weblab4;

import lombok.extern.slf4j.Slf4j;
import org.lab4.wed.weblab4.security.BcryptEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@Slf4j
@SpringBootApplication
public class WebLab4Application {

	public static void main(String[] args) {
		SpringApplication.run(WebLab4Application.class, args);
		log.info("Start Application");
	}

}
