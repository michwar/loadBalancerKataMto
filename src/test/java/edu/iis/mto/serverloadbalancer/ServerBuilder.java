package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server> {

	private int capacity;
	private double initLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);
		if (initLoad > 0) {
			int initVmSize = (int) (initLoad / (double) capacity * Server.MAX_LOAD);
			Vm initVm = new Vm(initVmSize);
			server.addVm(initVm);
		}
		return server;
	}

	public Builder<Server> initLoad(double initLoad) {
		this.initLoad = initLoad;
		return this;
	}

}
