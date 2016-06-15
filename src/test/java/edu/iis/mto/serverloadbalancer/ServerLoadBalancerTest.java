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
		Server server = a(ServerBuilder.server().withCapacity(1));

		balancing(aServerListWith(server), anEmptyVmList());

		assertThat(server, CurrentLoadPercentageMatcher.hasCurrentLoadOf(0.0d));
	}

	@Test
	public void balancingOneServerWithOneCapacity_andOneVm() {
		Server server = a(ServerBuilder.server().withCapacity(1));

		Vm vm = a(VmBuilder.vm().sizeOf(1));
		balancing(aServerListWith(server), aServerListWith(vm));

		assertThat(server, CurrentLoadPercentageMatcher.hasCurrentLoadOf(100.0d));
		assertThat("server should contains vm ", server.contains(vm));
	}

	@Test
	public void balancinsOneServerWithEnoughtCapcity_andOneVm() {
		Server server = a(ServerBuilder.server().withCapacity(10));

		Vm vm = a(VmBuilder.vm().sizeOf(1));
		balancing(aServerListWith(server), aServerListWith(vm));

		assertThat(server, CurrentLoadPercentageMatcher.hasCurrentLoadOf(10.0d));
		assertThat("server should contains vm ", server.contains(vm));
	}

	private Vm[] aServerListWith(Vm... vms) {
		return vms;
	}

	private Vm a(VmBuilder builder) {
		return builder.build();
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

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}

}
