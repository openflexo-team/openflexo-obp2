package org.openflexo.ta.obp2.model;
/**
 * 
 * Copyright (c) 2018, Openflexo
 * 
 * This file is part of OpenflexoTechnologyAdapter, a component of the software infrastructure 
 * developed at Openflexo.
 * 
 * 
 * Openflexo is dual-licensed under the European Union Public License (EUPL, either 
 * version 1.1 of the License, or any later version ), which is available at 
 * https://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 * and the GNU General Public License (GPL, either version 3 of the License, or any 
 * later version), which is available at http://www.gnu.org/licenses/gpl.html .
 * 
 * You can redistribute it and/or modify under the terms of either of these licenses
 * 
 * If you choose to redistribute it and/or modify under the terms of the GNU GPL, you
 * must include the following additional permission.
 *
 *          Additional permission under GNU GPL version 3 section 7
 *
 *          If you modify this Program, or any covered work, by linking or 
 *          combining it with software containing parts covered by the terms 
 *          of EPL 1.0, the licensors of this Program grant you additional permission
 *          to convey the resulting work. * 
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE. 
 *
 * See http://www.openflexo.org/license.html for details.
 * 
 * 
 * Please contact Openflexo (openflexo-contacts@openflexo.org)
 * or visit www.openflexo.org if you need additional information.
 * 
 */

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openflexo.ta.obp2.AbstractOBP2Test;
import org.openflexo.ta.obp2.OBP2TechnologyAdapter;
import org.openflexo.ta.obp2.model.test.TestConfiguration;
import org.openflexo.ta.obp2.model.test.TestTransitionRelation;
import org.openflexo.test.OrderedRunner;
import org.openflexo.test.TestOrder;

import announce4j.Announcer;
import plug.events.ExecutionEndedEvent;
import plug.explorer.BFSExplorer;
import plug.statespace.SimpleStateSpaceManager;

@RunWith(OrderedRunner.class)
public class TestSimpleBFSCheck extends AbstractOBP2Test {
	protected static final Logger logger = Logger.getLogger(TestSimpleBFSCheck.class.getPackage().getName());

	@Test
	@TestOrder(1)
	public void testInitializeServiceManager() throws Exception {
		instanciateTestServiceManager(OBP2TechnologyAdapter.class);
	}

	@Test
	@TestOrder(3)
	public void testCheck() {
		TestTransitionRelation transitionRelation = new TestTransitionRelation();
		BFSExplorer<TestConfiguration, Object> controller = new BFSExplorer<>(transitionRelation,
				new SimpleStateSpaceManager<TestConfiguration, Object>(true));
		controller.getAnnouncer().when(ExecutionEndedEvent.class, (Announcer announcer, ExecutionEndedEvent o) -> {
			System.out.println("Final size: " + o.getSource().getStateSpaceManager().size() + "\n");
			assertEquals(4, o.getSource().getStateSpaceManager().size());
		});

		controller.execute();
	}
}
