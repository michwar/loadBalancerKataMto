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

	@Test
	public void balancingOneServerithOneVm_fillWholeServer() {
		Server server = a(server().withCapacity(1));

		Vm vm = a(vm().withSizeOf(1));

		balance(aServersListOf(server), aVmListOf(vm));

		assertThat(server, hasCurrentLoadPercentageOf(100.0));
		
		assertThat("a server should contain a vm ", server.contains(vm));

	}

	private Vm[] aVmListOf(Vm... vms) {
		return vms;
	}

	private VmBuilder vm() {
		return new VmBuilder();
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

	private ServerBuilder server() {
		return new ServerBuilder();
	}

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}
}
