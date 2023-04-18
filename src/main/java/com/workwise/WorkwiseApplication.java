package com.workwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.*;

@Configuration
@EntityScan(basePackages="com.workwise")
@SpringBootApplication
public class WorkwiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkwiseApplication.class, args);
	}
}
