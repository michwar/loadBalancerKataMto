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

	@Test
	public void balancingServerWithEnoughRooms_andAllVm() {
		Server server = a(ServerBuilder.server().withCapacity(10));

		Vm vm1 = a(VmBuilder.vm().sizeOf(1));
		Vm vm2 = a(VmBuilder.vm().sizeOf(1));

		balancing(aServerListWith(server), aServerListWith(vm1, vm2));

		assertThat(server, CountOfVmMatcher.hasVmsCountOf(2));
		assertThat("server should contains vm 2", server.contains(vm1));
		assertThat("server should contains vm 1", server.contains(vm2));
	}

	@Test
	public void balancingLessLoadedServerwithVms() {
		Server server1 = a(ServerBuilder.server().withCapacity(100).withCurrentLoad(50.0d));
		Server server2 = a(ServerBuilder.server().withCapacity(100).withCurrentLoad(45.0d));

		Vm vm = a(VmBuilder.vm().sizeOf(10));

		balancing(aServerListWith(server1, server2), aServerListWith(vm));

		assertThat("server 2 should contains vm", server2.contains(vm));
		assertThat("server 1 should not contains vm", !server1.contains(vm));
	}

	@Test
	public void balancingServerWithNotEnoughCapacityForVm() {
		Server server = a(ServerBuilder.server().withCapacity(10).withCurrentLoad(90.0d));

		Vm vm = a(VmBuilder.vm().sizeOf(20));

		balancing(aServerListWith(server), aServerListWith(vm));

		assertThat("server should not contains vm", !server.contains(vm));

	}

	@Test
	public void balancingServersAndVms() {
		Server server1 = a(ServerBuilder.server().withCapacity(4));
		Server server2 = a(ServerBuilder.server().withCapacity(6));

		Vm vm1 = a(VmBuilder.vm().sizeOf(1));
		Vm vm2 = a(VmBuilder.vm().sizeOf(4));
		Vm vm3 = a(VmBuilder.vm().sizeOf(2));

		balancing(aServerListWith(server1, server2), aServerListWith(vm1, vm2, vm3));

		assertThat(server1, CurrentLoadPercentageMatcher.hasCurrentLoadOf(75.0d));
		assertThat(server2, CurrentLoadPercentageMatcher.hasCurrentLoadOf(66.66d));

		assertThat("server 1 should contains vm", server1.contains(vm1));
		assertThat("server 2 should contains vm", server2.contains(vm2));
		assertThat("server 2 should contains vm", server1.contains(vm3));

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
