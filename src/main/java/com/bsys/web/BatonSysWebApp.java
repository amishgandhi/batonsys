package com.bsys.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.bsys.web.service.FairScheduler;

/**
 * 
 * @author amish
 * Main method to kick off spring boot application.
 * Starts the web app and server side fair scheduling thread
 * 
 */

@SpringBootApplication
@ComponentScan("com.bsys.web")
public class BatonSysWebApp {

	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		return pool;
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BatonSysWebApp.class, args);
	    ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) ctx.getBean("taskExecutor");

		FairScheduler fs = (FairScheduler) ctx.getBean("fairScheduler");
		taskExecutor.execute(fs);
	}
	
}
