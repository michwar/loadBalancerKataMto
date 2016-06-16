package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

public class Server {

	public static final double MAX_LOAD = 100.0;
	public double currentLoadPercentage;
	public int capacity;
	private List<Vm> vms = new ArrayList<Vm>();

	public boolean contains(Vm vm) {
		return vms.contains(vm);
	}

	public Server(int capacity) {
		super();
		this.capacity = capacity;
	}

	public int vmCount() {
		return vms.size();
	}

	public void addVm(Vm vm) {
		currentLoadPercentage += vmLoad(vm);
		vms.add(vm);
	}

	private double vmLoad(Vm vm) {
		return (double) vm.size / (double) capacity * MAX_LOAD;
	}

	public boolean canFillVm(Vm vm) {
		return MAX_LOAD >= currentLoadPercentage + vmLoad(vm);
	}

}
