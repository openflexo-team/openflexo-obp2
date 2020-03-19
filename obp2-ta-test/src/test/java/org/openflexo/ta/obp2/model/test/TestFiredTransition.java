package org.openflexo.ta.obp2.model.test;

import java.util.Collection;
import java.util.Collections;

import plug.core.IFiredTransition;

public class TestFiredTransition implements IFiredTransition<TestConfiguration, Object> {

	private TestConfiguration source;
	private TestTransitionTo transition;
	private Collection<TestConfiguration> targets;

	private Object action;
	private Object payload;

	public TestFiredTransition(TestConfiguration source, TestTransitionTo transition) {
		super();
		this.source = source;
		this.transition = transition;
		targets = Collections.singletonList(TestConfiguration.getConfiguration(transition.getI()));

		action = new Object();
		payload = new Object();
	}

	@Override
	public Object getAction() {
		return action;
	}

	@Override
	public void setAction(Object arg0) {
	}

	@Override
	public Object getPayload() {
		return payload;
	}

	@Override
	public void setPayload(Object arg0) {
	}

	@Override
	public TestConfiguration getSource() {
		return source;
	}

	@Override
	public void setSource(TestConfiguration arg0) {
	}

	@Override
	public Collection<TestConfiguration> getTargets() {
		return targets;
	}

	@Override
	public void setTargets(Collection<TestConfiguration> arg0) {
	}
}
