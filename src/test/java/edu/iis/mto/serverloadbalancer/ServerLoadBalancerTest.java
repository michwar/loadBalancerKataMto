package edu.iis.mto.serverloadbalancer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
	@Test
	public void itCompiles() {
		assertThat(true, equalTo(true));
	}

	@Test
	public void balancingServerWithNoVms_serverStayEmpty() {
		Server theServer = a(ServerBuilder.server().withCapacity(1));

		balancing(aServerListWith(theServer), anEmptyListOfVms());

		assertThat(theServer, CurrentLoadPercentageMatcher.hasCurrentLoad(0.0d));
	}

	@Test
	public void balancingServerWithOneVms() {
		Server theServer = a(ServerBuilder.server().withCapacity(1));
		Vm theVm = a(vm.ofSize(1));

		balancing(aServerListWith(theServer), aVmsListWith(theVm));

		assertThat(theServer, CurrentLoadPercentageMatcher.hasCurrentLoad(100.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Server[] aServerListWith(Server... servers) {
		return servers;
	}

	private Vm[] anEmptyListOfVms() {
		return new Vm[0];
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}
}
