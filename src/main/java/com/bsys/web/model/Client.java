package com.bsys.web.model;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import javax.validation.constraints.Size;

public class Client {
	private int id;

	@Size(min = 3, message = "Enter at least 3 Characters...")
	private String name;
	private AtomicInteger counter;
	private AtomicBoolean connected;

	public Client() {
		super();
		this.connected = new AtomicBoolean(false);
		this.counter = new AtomicInteger(0);
	}

	public Client(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.connected = new AtomicBoolean(true);
		this.counter = new AtomicInteger(1);
	}

	public void setId(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getAndIncCounter() {
		return counter.getAndIncrement();
	}

	public int getCounter(){
		return counter.get();
	}
	public boolean isConnected() {
		return connected.get();
	}

	public void setConnected(boolean connected) {
		this.connected.set(connected);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Client other = (Client) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("Todo [id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s]", id, name, counter, connected);
	}

}