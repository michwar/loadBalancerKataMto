package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

	private static final double EPSILON = 0.01d;
	private double expectedLoad;

	public CurrentLoadPercentageMatcher(double expectedLoad) {
		this.expectedLoad = expectedLoad;
	}

	@Override
	protected void describeMismatchSafely(Server item, Description description) {
		description.appendText("a server with load percentage of ").appendValue(item.currentLoad);
	}

	public void describeTo(Description description) {
		description.appendText("a server with load percentage of ").appendValue(expectedLoad);
	}

	@Override
	protected boolean matchesSafely(Server item) {
		return expectedLoad == item.currentLoad || Math.abs((expectedLoad - item.currentLoad)) < EPSILON;
	}

}
