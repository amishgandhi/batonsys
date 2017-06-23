package com.bsys.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import com.bsys.web.model.Client;
import com.bsys.web.service.ClientService;
import com.bsys.web.service.FairScheduler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatonSysIntegrationClientDisconnect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired ClientService service;
	@Autowired
    private ApplicationContext applicationContext;
	
	
	@Test
	public void testDisconnect() {
	    ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) applicationContext.getBean("taskExecutor");

		FairScheduler fs = (FairScheduler) applicationContext.getBean("fairScheduler");
		taskExecutor.execute(fs);
		Client clientB = service.getClient(2);
		Client clientA = service.getClient(1);
		while(clientB.getCounter()<=1)
		try {
			Thread.sleep(FairScheduler.TIME_INTERVAL_IN_MILLIS);
		} catch (InterruptedException e) {
			logger.warn("Thread slee was interrupted", e);
		}
		
		int clientACounter = clientA.getCounter();	
		assertTrue("Counter should be greater than 4", clientACounter>=4);				
		service.toggleConnection(1);
			
		while(clientB.getCounter()<=5)
		try {
			Thread.sleep(FairScheduler.TIME_INTERVAL_IN_MILLIS);
		} catch (InterruptedException e) {
			logger.warn("Thread slee was interrupted", e);
		}
		
		assertEquals("Client A counter shouldn't change", clientACounter, clientA.getCounter());
		
		
	}

}
