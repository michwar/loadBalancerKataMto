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
	public void balancingServerithNoVm() {
		Server server = a(server().withCapacity(1));

		balance(aServersListOf(server), anEmptyVmList());

		assertThat(server, hasCurrentLoadPercentageOf(0.0));
	}

	private Matcher<? super Server> hasCurrentLoadPercentageOf(double expectedLoadPercentage) {
		return new ServerLoadPercentageMatcher(expectedLoadPercentage);
	}

	private void balance(Server[] servers, Vm[] vms) {
		new ServeLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyVmList() {
		return new Vm[0];
	}

	private Server[] aServersListOf(Server... servers) {
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}

}
