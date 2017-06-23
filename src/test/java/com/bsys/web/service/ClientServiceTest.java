package com.bsys.web.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bsys.web.model.Client;

public class ClientServiceTest {

	@Test
	public void testClientServiceConstructor(){
		ClientService service = new ClientService();
		assertEquals("Should have 3 client initial clients", 3, service.retrieveClients().size());
	}
	
	@Test
	public void testAddClient(){
		ClientService service = new ClientService();
		assertEquals("Should have 3 client initial clients", 3, service.retrieveClients().size());
		service.addClient("Client D");
		assertEquals("Should have 3 client initial clients", 4, service.retrieveClients().size());
		for(Client client: service.retrieveClients()){
			if(client.getId()==4){
				assertEquals("Id 4 should have name Client D", "Client D", client.getName());				
			}
		}
	}
	
	@Test
	public void testToggleClient(){
		ClientService service = new ClientService();
		assertEquals("Should have 3 client initial clients", 3, service.retrieveClients().size());
		for(Client client: service.retrieveClients()){
			if(client.getId()==3){
				assertEquals("Connected should be true", true, client.isConnected());				
			}
		}
		
		service.toggleConnection(3);

		for(Client client: service.retrieveClients()){
			if(client.getId()==3){
				assertEquals("Connected should be false", false, client.isConnected());				
			}
		}
		
	}

}
