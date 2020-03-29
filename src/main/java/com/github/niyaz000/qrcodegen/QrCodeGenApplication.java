package com.github.niyaz000.qrcodegen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class QrCodeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrCodeGenApplication.class, args);
	}

}
