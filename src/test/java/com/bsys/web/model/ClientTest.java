package com.bsys.web.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClientTest {

	@Test
	public void createEmptyContructorClientTest(){
		Client c = new Client();
		c.setName("Test");
		c.setId(0);
		
		assertEquals("Name must be Test", "Test", c.getName());
		assertEquals("Id must be 0", 0, c.getId());
		assertEquals("Counter must be 1", 1, c.getAndIncCounter());
		assertEquals("Must not be connected since empty constructor", false, c.isConnected());
	}

	@Test
	public void createNonEmptyContructorClientTest(){
		Client c = new Client(0, "Test");
		
		assertEquals("Name must be Test", "Test", c.getName());
		assertEquals("Id must be 0", 0, c.getId());
		assertEquals("Counter must be 1", 1, c.getAndIncCounter());
		assertEquals("Must be connected since non empty constructor", true, c.isConnected());
	}
	
}
