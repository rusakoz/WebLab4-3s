package org.lab4.wed.weblab4;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;

@Slf4j
@SpringBootApplication
public class WebLab4Application {

	public static void main(String[] args) {
		SpringApplication.run(WebLab4Application.class, args);
		log.info("Start Application");
		// String key = "super-secret-access-key-super-secret-access-key";
		// String base64Key = DatatypeConverter.printBase64Binary(key.getBytes());
		// System.out.println(base64Key);
	}

}
