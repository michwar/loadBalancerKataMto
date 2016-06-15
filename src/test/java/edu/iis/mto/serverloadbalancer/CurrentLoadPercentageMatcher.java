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
		return doubleAreEqual(expectedLoad, item.currentLoad);
	}

	private boolean doubleAreEqual(double d1, double d2) {
		return d1 == d2 || Math.abs((d1 - d2)) < EPSILON;
	}

	public static CurrentLoadPercentageMatcher hasCurrentLoadOf(double expectedLoad) {
		return new CurrentLoadPercentageMatcher(expectedLoad);
	}
}
