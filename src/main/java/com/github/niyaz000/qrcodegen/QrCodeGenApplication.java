package com.github.niyaz000.qrcodegen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QrCodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrCodeGenApplication.class, args);
	}

}
