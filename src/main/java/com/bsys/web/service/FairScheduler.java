package com.bsys.web.service;

import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bsys.web.model.Client;

/**
 * 
 * @author amish
 * Server thread modeling fair scheduler algorithm
 * Goes through the list of client and gives each one 4 seconds.
 * If during or after that time the client is disconnected, the scheduler moves on to the next 
 * connected client
 */
@Component
@Scope("singleton")
public class FairScheduler implements Runnable{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final int FAIR_SHARE_TIME_IN_MILLIS = 4000;
	public static final int TIME_INTERVAL_IN_MILLIS = 1000;
	private AtomicBoolean keepRunning;
	
	ClientService service;
	
	@Autowired
	public FairScheduler(ClientService service) {
		this.service = service;
		this.keepRunning = new AtomicBoolean(true);
	}
	@Override
	public void run() {
		logger.debug("Starting fair scheduler debug");
		while(keepRunning.get()){
			int timeSlice = TIME_INTERVAL_IN_MILLIS;
			Client client = service.getNext();
			while(timeSlice<=FAIR_SHARE_TIME_IN_MILLIS && client.isConnected() && keepRunning.get()){
				logger.debug("{}, Counter Value:{}", client.getName(), client.getAndIncCounter());
				timeSlice += TIME_INTERVAL_IN_MILLIS;
				try {
					Thread.sleep(TIME_INTERVAL_IN_MILLIS);
				} catch (InterruptedException e) {
					logger.warn("Thread slee was interrupted", e);
				}
				
			}
		}
	}
	
	public ClientService getClientService(){
		return service;
	}
	
	public void stop(){
		keepRunning.compareAndSet(true, false);
	}
	


}
