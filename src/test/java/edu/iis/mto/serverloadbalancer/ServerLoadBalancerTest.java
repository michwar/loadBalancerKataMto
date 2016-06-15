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
	public void balancingServerWithNoVm() {
		Server server = a(server().withCapacity(1));

		balancing(aServerListWith(server), anEmptyVmList());

		assertThat(server, hasCurrentLoadOf(0.0d));
	}

	private Matcher<? super Server> hasCurrentLoadOf(double expectedLoad) {
		return new CurrentLoadPercentageMatcher(expectedLoad);
	}

	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers, vms);
	}

	private Vm[] anEmptyVmList() {
		return new Vm[0];
	}

	private Server[] aServerListWith(Server... servers) {
		return null;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}

}
