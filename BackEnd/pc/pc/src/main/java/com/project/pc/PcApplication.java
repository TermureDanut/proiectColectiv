package com.project.pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PcApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(PcApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
