package com.businessapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

	public static void main( String[] args ) {
		System.err.println( "\nHello SpringApplication!\n" );
		SpringApplication.run( Application.class, args );
		System.err.println( "\nBye, SpringApplication!\n" );
	}

}
