package com.conduit;

import com.conduit.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class Conduit {

	public static void main(String[] args) {
		SpringApplication.run(Conduit.class, args);
	}

}
