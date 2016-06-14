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
		Vm theVm = a(VmBuilder.vm().ofSize(1));

		balancing(aServerListWith(theServer), aVmsListWith(theVm));

		assertThat(theServer, CurrentLoadPercentageMatcher.hasCurrentLoad(100.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));
	}

	@Test
	public void balancingServerWithTenSlotCapacity_andOneSlotVm() {
		Server theServer = a(ServerBuilder.server().withCapacity(10));
		Vm theVm = a(VmBuilder.vm().ofSize(1));

		balancing(aServerListWith(theServer), aVmsListWith(theVm));

		assertThat(theServer, CurrentLoadPercentageMatcher.hasCurrentLoad(10.0d));
		assertThat("server should contain the vm", theServer.contains(theVm));

	}

	@Test
	public void balancingTheServerWithEnoughRoom_fillServerWithAllVms() {
		Server theServer = a(ServerBuilder.server().withCapacity(100));
		Vm theFirstVm = a(VmBuilder.vm().ofSize(1));
		Vm theSecondVm = a(VmBuilder.vm().ofSize(1));

		balancing(aServerListWith(theServer), aVmsListWith(theFirstVm, theSecondVm));

		assertThat(theServer, hasVmsCount(2));
		assertThat("server should contain the first vm", theServer.contains(theFirstVm));
		assertThat("server should contain the second vm", theServer.contains(theSecondVm));

	}

	private Matcher<? super Server> hasVmsCount(int expectedCount) {
		return new ServerVmsCountMatcher(expectedCount);
	}

	private Vm[] aVmsListWith(Vm... vms) {
		return vms;
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

	private <T> T a(Builder<T> builder) {
		return builder.build();
	}

}
