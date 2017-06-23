package com.bsys.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bsys.web.model.Client;

/**
 * 
 * @author amish
 * Keeps a list of all clients in a synchronized list.
 * This allows new clients to be added and making sure that these new clients
 * are seen by the fair scheduler right away.
 * Synchronized methods/block could be used instead of synchronized list. The over head will be on the developers then
 * to handle concurrency.
 * processorIndex var keeps track of the current element being processed by fair scheduler
 * 
 */
@Service
public class ClientService {
	List<Client> clients = Collections.synchronizedList(new ArrayList<Client>());
	private int clientCount = 0;
	private int processorIndex = 0;

	public ClientService() {
		this.addClient("Client A");
		this.addClient("Client B");
		this.addClient("Client C");
	}

	/**
	 * A copy is returned so original list is not modified.
	 * @return
	 */
	public List<Client> retrieveClients() {
		return Collections.unmodifiableList(clients);
	}

	public void addClient(String name) {
		clients.add(new Client(++clientCount, name));
	}

	public Client getNext() {
		if (processorIndex >= clients.size()) {
			processorIndex = 0;
		}
		return clients.get(processorIndex++);
	}

	public void toggleConnection(int id) {
		Client client = getClient(id);
		client.setConnected(!client.isConnected());
	}

	/**
	 * As per java spec the code is synchronized using the list as lock so while iterating
	 * the list cannot be changed.
	 * @param id
	 * @return
	 */
	public Client getClient(int id) {
		synchronized (clients) {
			for (Client c : clients) {
				if (c.getId() == id) {
					return c;
				}
			}
			return null;
		}
	}

}