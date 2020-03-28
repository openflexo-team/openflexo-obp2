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

package org.openflexo.ta.obp2.fml.editionaction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.logging.Logger;

import org.openflexo.connie.exception.NullReferenceException;
import org.openflexo.connie.exception.TypeMismatchException;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.foundation.fml.rt.RunTimeEvaluationContext;
import org.openflexo.foundation.fml.rt.VirtualModelInstance;
import org.openflexo.pamela.annotations.ImplementationClass;
import org.openflexo.pamela.annotations.ModelEntity;
import org.openflexo.pamela.annotations.XMLElement;
import org.openflexo.ta.obp2.OBP2ModelSlot;
import org.openflexo.ta.obp2.model.FMLConfiguration;
import org.openflexo.ta.obp2.model.FMLTransitionRelation;
import org.openflexo.ta.obp2.model.OBP2Analysis;

import announce4j.Announcer;
import plug.events.ExecutionEndedEvent;
import plug.explorer.BFSExplorer;
import plug.statespace.SimpleStateSpaceManager;

@ModelEntity
@ImplementationClass(PerformBFSAnalysis.PerformBFSAnalysisImpl.class)
@XMLElement
@FML("PerformBFSAnalysis")
public interface PerformBFSAnalysis extends AbstractAnalysisAction<Object> {

	public static abstract class PerformBFSAnalysisImpl
			extends TechnologySpecificActionDefiningReceiverImpl<OBP2ModelSlot, OBP2Analysis, Object> implements PerformBFSAnalysis {

		private static final Logger logger = Logger.getLogger(PerformBFSAnalysis.class.getPackage().getName());

		@Override
		public Type getAssignableType() {
			return Object.class;
		}

		@Override
		public Object execute(RunTimeEvaluationContext evaluationContext) {

			OBP2Analysis analysis = getReceiver(evaluationContext);

			try {
				System.out.println("InferedModelSlot=" + getInferedModelSlot());
				System.out.println("getTransitionRelation()=" + getInferedModelSlot().getTransitionRelation());
				VirtualModelInstance<?, ?> transitionRelation = getInferedModelSlot().getTransitionRelation()
						.getBindingValue(evaluationContext);
				System.out.println("TransitionRelation=" + transitionRelation);
				System.out.println(transitionRelation.getFactory().stringRepresentation(transitionRelation));

				System.out.println("Tiens, faudrait executer le BFS !!!");

				FMLTransitionRelation fmlTransitionRelation = new FMLTransitionRelation(transitionRelation);
				BFSExplorer<FMLConfiguration, Object> controller = new BFSExplorer<>(fmlTransitionRelation,
						new SimpleStateSpaceManager<FMLConfiguration, Object>(true));
				controller.getAnnouncer().when(ExecutionEndedEvent.class, (Announcer announcer, ExecutionEndedEvent o) -> {
					System.out.println("Final size: " + o.getSource().getStateSpaceManager().size() + "\n");
				});
				controller.execute();

			} catch (TypeMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NullReferenceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;

		}

	}

}
