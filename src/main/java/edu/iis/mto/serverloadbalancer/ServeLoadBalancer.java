package edu.iis.mto.serverloadbalancer;

public class ServeLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		servers[0].currentLoadPercentage = 0.0;
	}

}
