package edu.iis.mto.serverloadbalancer;

public class ServeLoadBalancer {

	public void balance(Server[] servers, Vm[] vms) {
		for (Vm vm : vms) {
			Server lessLoadedServer = null;
			for (Server server : servers) {
				if (!server.canFillVm(vm))
					continue;

				if (null == lessLoadedServer || lessLoadedServer.currentLoadPercentage > server.currentLoadPercentage)
					lessLoadedServer = server;
			}
			if (null != lessLoadedServer)
				lessLoadedServer.addVm(vm);
		}
	}

}
