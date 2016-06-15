package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {

	private double expectedLoad;

	public CurrentLoadPercentageMatcher(double expectedLoad) {
		this.expectedLoad = expectedLoad;
	}

	public void describeTo(Description description) {

	}

	@Override
	protected boolean matchesSafely(Server item) {
		return expectedLoad == 0.0d;
	}

}
