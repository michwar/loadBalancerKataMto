package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	public double currentLoad;
	public int capacity;

	public void add(Vm vm) {
		// TODO Auto-generated method stub

	}

	public boolean contains(Vm vm) {
		return true;
	}

	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

}
