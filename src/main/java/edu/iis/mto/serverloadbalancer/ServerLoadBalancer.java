package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			Server lessLoadedSever = getLessLoadedServer(servers);
			if (null != lessLoadedSever)
				lessLoadedSever.add(vm);
		}

	}

	private Server getLessLoadedServer(Server[] servers) {
		Server lessLoadedSever = null;
		for (Server server : servers) {
			if (null == lessLoadedSever || server.currentLoad < lessLoadedSever.currentLoad) {
				lessLoadedSever = server;
			}
		}
		return lessLoadedSever;
	}

}
