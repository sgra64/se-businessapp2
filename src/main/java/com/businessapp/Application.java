package com.businessapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;

/**
 * Main Spring Boot application class marked with @SpringBootApplication annotation.
 * @author Graupner
 */
@SpringBootApplication
public class Application {
	//private static Logger logger = LoggerFactory.getLogger( Application.class );

	/**
	 * Protected constructor for Spring Boot to create an Application instance.
	 * Application instances must be created by Spring, never by "new".
	 * @param args arguments passed from main()
	 */
	Application( String[] args ) {
		//logger.info( "2. Hello SpringApplication, Constructor called." );
		System.out.println( "2. Hello SpringApplication, Constructor called." );
	}

	/**
	 * Entry point after Spring Boot has initialized the application instance.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		System.out.println( "3. Hello SpringApplication, doSomethingAfterStartup() called." );
	}

	/**
	 * Java/JVM entry point with static main() function. Called first.
	 * @param args
	 */
	public static void main( String[] args ) {
		System.out.println( "1. Hello SpringApplication!" );
		System.out.print( "   Initialize Spring Boot and create Application instance." );

		// Initialize Spring Boot and create Application instance.
		ApplicationContext applicationContext
			= SpringApplication.run( Application.class, args );

		System.out.println( "4. Bye, " + applicationContext.getId() + "!" );
	}

}
