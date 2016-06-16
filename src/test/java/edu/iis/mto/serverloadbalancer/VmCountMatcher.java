package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class VmCountMatcher extends TypeSafeMatcher<Server> {

	private int expectedCount;

	public VmCountMatcher(int expectedCount) {
		this.expectedCount = expectedCount;
	}

	public void describeTo(Description description) {
		description.appendText("a server should contain count vm of ").appendValue(expectedCount);
	}

	@Override
	protected void describeMismatchSafely(Server item, Description description) {
		description.appendText("a server should contain count vm of ").appendValue(item.vmCount());
	}

	@Override
	protected boolean matchesSafely(Server item) {
		return expectedCount == item.vmCount();
	}

	public static VmCountMatcher vmCountOf(int expectedCount) {
		return new VmCountMatcher(expectedCount);
	}

}
