package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;

public class Server {

	private static final double MAX_LOAD = 100.0d;
	public double currentLoad;
	public int capacity;

	public void add(Vm vm) {
		currentLoad = (double) vm.size / (double) capacity * MAX_LOAD;
	}

	public boolean contains(Vm vm) {
		return true;
	}

	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

}
