package org.openflexo.ta.obp2.model.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import plug.core.IFiredTransition;
import plug.core.ITransitionRelation;

public class TestTransitionRelation implements ITransitionRelation<TestConfiguration, TestTransitionTo> {

	private TestConfiguration initialConfiguration = TestConfiguration.getConfiguration(0);

	public TestTransitionRelation() {
	}

	@Override
	public IFiredTransition<TestConfiguration, ?> fireOneTransition(TestConfiguration source, TestTransitionTo transition) {
		return new TestFiredTransition(source, transition);
	}

	@Override
	public Collection<TestTransitionTo> fireableTransitionsFrom(TestConfiguration config) {

		switch (config.getI()) {
			case 0:
				return Arrays.asList(new TestTransitionTo(1), new TestTransitionTo(2));
			case 1:
				return Arrays.asList(new TestTransitionTo(3));
			case 2:
				return Arrays.asList(new TestTransitionTo(3));
			case 3:
				return Arrays.asList(new TestTransitionTo(0));
			default:
				return null;
		}
	}

	@Override
	public Set<TestConfiguration> initialConfigurations() {
		return Collections.singleton(initialConfiguration);
	}

}
