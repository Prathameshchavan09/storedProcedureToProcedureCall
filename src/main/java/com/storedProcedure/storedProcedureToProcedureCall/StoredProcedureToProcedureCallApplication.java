package com.storedProcedure.storedProcedureToProcedureCall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.storedProcedure.storedProcedureToProcedureCall.controller.*"})

public class StoredProcedureToProcedureCallApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoredProcedureToProcedureCallApplication.class, args);
		
	}

}
