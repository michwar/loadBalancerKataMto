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

	@Test
	public void balancingOneServerWithOneCapacity_andOneVm() {
		Server server = a(server().withCapacity(1));

		Vm vm = a(vm().sizeOf(1));
		balancing(aServerListWith(server), aServerListWith(vm));

		assertThat(server, hasCurrentLoadOf(100.0d));
		assertThat("server should contains vm ", server.contains(vm));
	}

	private Vm[] aServerListWith(Vm... vms) {
		return vms;
	}

	private Vm a(VmBuilder builder) {
		return builder.build();
	}

	private VmBuilder vm() {
		return new VmBuilder();
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
		return servers;
	}

	private Server a(ServerBuilder builder) {
		return builder.build();
	}

	private ServerBuilder server() {
		return new ServerBuilder();
	}

}
