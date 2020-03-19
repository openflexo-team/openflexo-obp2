package plug.explorer;

import org.openflexo.ta.obp2.model.test.TestConfiguration;
import org.openflexo.ta.obp2.model.test.TestTransitionRelation;

import plug.core.IStateSpaceManager;
import plug.events.ExecutionEndedEvent;

public class TestBFSExplorer extends BFSExplorer<TestConfiguration, Object> {

	public TestBFSExplorer(TestTransitionRelation runtime, IStateSpaceManager stateSpaceManager) {
		super(runtime, stateSpaceManager);
	}

	@Override
	public void execute() {

		System.out.println("Init");

		initializeExploration();

		System.out.println("BEGIN");

		while (!monitor.atEnd() && !atEnd()) {
			System.out.println("> un pas d'exploration");
			explorationStep();
		}

		System.out.println("monitor.atEnd()=" + monitor.atEnd());
		System.out.println("atEnd()=" + atEnd());

		System.out.println("END");

		announcer.announce(new ExecutionEndedEvent(this));
	}

	@Override
	void schedule(TestConfiguration conf) {
		System.out.println("Bon faudra regarder " + conf);
		super.schedule(conf);
	}
}
