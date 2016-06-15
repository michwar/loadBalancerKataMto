package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CountOfVmMatcher extends TypeSafeMatcher<Server> {

	private int count;

	public CountOfVmMatcher(int count) {
		this.count = count;
	}

	public void describeTo(Description description) {
		description.appendText("a server with vms count of ").appendValue(count);
	}

	@Override
	protected boolean matchesSafely(Server item) {
		return false;
	}

}
