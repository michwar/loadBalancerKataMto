package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Server {

	public static final double MAX_LOAD = 100.0d;
	public double currentLoad;
	public int capacity;
	private List<Vm> vms = new ArrayList<Vm>();

	public void add(Vm vm) {
		vms.add(vm);
		currentLoad = (double) vm.size / (double) capacity * MAX_LOAD;
	}

	public boolean contains(Vm vm) {
		return vms.contains(vm);
	}

	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

	public int countVms() {
		return vms.size();
	}

	public boolean canFitVm(Vm vm) {
		return MAX_LOAD >= currentLoad + ((double) vm.size / (double) capacity * 100.0d);
	}

}
