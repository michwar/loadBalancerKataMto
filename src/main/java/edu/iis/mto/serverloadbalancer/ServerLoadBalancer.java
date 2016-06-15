package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			addVmToLessLoadedServer(servers, vm);
		}

	}

	private void addVmToLessLoadedServer(Server[] servers, Vm vm) {
		Server lessLoadedServer = getLessLoadedServer(servers, vm);
		if (null != lessLoadedServer)
			lessLoadedServer.addVm(vm);
	}

	private Server getLessLoadedServer(Server[] servers, Vm vm) {
		Server lessLoadedServer = null;
		for (Server server : servers) {
			if (!server.canFitVm(vm))
				continue;

			if (null == lessLoadedServer || lessLoadedServer.currentLoadPercentage > server.currentLoadPercentage)
				lessLoadedServer = server;
		}
		return lessLoadedServer;
	}

}
