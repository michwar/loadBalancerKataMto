package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class ServerLoadPercentageMatcher extends TypeSafeMatcher<Server> {

	private static final double EPSILON = 0.01;
	private double expectedLoadPercentage;

	public ServerLoadPercentageMatcher(double expectedLoadPercentage) {
		this.expectedLoadPercentage = expectedLoadPercentage;
	}

	public void describeTo(Description description) {
		description.appendText("a server with load percentage of").appendValue(expectedLoadPercentage);
	}

	@Override
	protected void describeMismatchSafely(Server item, Description description) {
		description.appendText("a server with load percentage of").appendValue(item.currentLoadPercentage);
	}

	@Override
	protected boolean matchesSafely(Server item) {
		return expectedLoadPercentage == item.currentLoadPercentage
				|| Math.abs(expectedLoadPercentage - item.currentLoadPercentage) < EPSILON;
	}

}
