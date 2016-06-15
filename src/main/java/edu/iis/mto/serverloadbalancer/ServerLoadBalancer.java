package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			Server lessLoadedServer = extracted(servers);
			lessLoadedServer.addVm(vm);
		}

	}

	private Server extracted(Server[] servers) {
		Server lessLoadedServer = null;
		for (Server server : servers)
			if (null == lessLoadedServer || lessLoadedServer.currentLoadPercentage > server.currentLoadPercentage)
				lessLoadedServer = server;
		return lessLoadedServer;
	}

}
