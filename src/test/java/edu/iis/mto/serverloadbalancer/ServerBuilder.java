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
			int initVmSize = (int) (initLoad / (double) capacity * 100.0);
			Vm initVm = VmBuilder.vm().ofSize(initVmSize).build();
			server.addVm(initVm);
		}
		return server;
	}

	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoadOf(double initLoad) {
		this.initLoad = initLoad;
		return this;
	}
}
