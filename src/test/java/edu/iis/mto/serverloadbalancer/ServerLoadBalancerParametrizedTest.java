package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ServerLoadBalancerParametrizedTest extends ServerLoadBalancerBaseTest {

	private int capacity;
	private double expectedLoad;
	private int vmSize;

	public ServerLoadBalancerParametrizedTest(int capacity, int vmSize, double expectedLoad) {
		this.capacity = capacity;
		this.vmSize = vmSize;
		this.expectedLoad = expectedLoad;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 1, 0, 0.0 }, { 1, 1, 100.0 }, { 2, 1, 50.0 }, { 3, 3, 100.0 },
				{ 4, 4, 100.0 }, { 5, 5, 100.0 }, { 3, 1, 33.33 } });
	}

	@Test
	public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm() {
		Server theServer = a(server().withCapacity(capacity));
		Vm theVm = a(vm().ofSize(vmSize));
		balance(aListOfServersWith(theServer), aListOfVmsWith(theVm));

		assertThat(theServer, hasLoadPercentageOf(expectedLoad));
		assertThat("the server should contain vm", theServer.contains(theVm));
	}

}
