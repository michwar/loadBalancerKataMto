package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			Server lessLoadedSever = getLessLoadedServer(servers, vm);
			if (null != lessLoadedSever)
				lessLoadedSever.add(vm);
		}

	}

	private Server getLessLoadedServer(Server[] servers, Vm vm) {
		Server lessLoadedSever = null;
		for (Server server : servers) {
			if (!server.canFitVm(vm))
				continue;

			if (null == lessLoadedSever || server.currentLoad < lessLoadedSever.currentLoad) {
				lessLoadedSever = server;
			}
		}
		return lessLoadedSever;
	}

}
