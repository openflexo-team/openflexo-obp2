/**
 * 
 * Copyright (c) 2014-2015, Openflexo
 * 
 * This file is part of Flexo-foundation, a component of the software infrastructure 
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

import org.openflexo.connie.DataBinding;
import org.openflexo.connie.exception.NullReferenceException;
import org.openflexo.connie.exception.TypeMismatchException;
import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.VirtualModelInstanceType;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.foundation.fml.rm.CompilationUnitResource;
import org.openflexo.foundation.fml.rt.RunTimeEvaluationContext;
import org.openflexo.foundation.fml.rt.VirtualModelInstance;
import org.openflexo.foundation.fml.rt.action.FlexoBehaviourAction;
import org.openflexo.foundation.fml.rt.editionaction.AbstractAddVirtualModelInstance;
import org.openflexo.pamela.annotations.ImplementationClass;
import org.openflexo.pamela.annotations.ModelEntity;
import org.openflexo.pamela.annotations.XMLElement;
import org.openflexo.ta.obp2.model.OBP2Analysis;
import org.openflexo.ta.obp2.model.action.CreateBasicOBP2Analysis;

/**
 * This action is used to explicitely instanciate a new {@link OBP2Analysis} in an other {@link OBP2Analysis} with some parameters
 * 
 * @author sylvain
 * 
 */
@ModelEntity
@ImplementationClass(AddOBP2Analysis.AddOBP2AnalysisImpl.class)
@XMLElement
@FML("AddOBP2Analysis")
public interface AddOBP2Analysis<VMI extends VirtualModelInstance<VMI, ?>> extends AbstractAddVirtualModelInstance<OBP2Analysis, VMI> {

	/**
	 * Return type of View, when {@link #getVirtualModelInstance()} is set and valid
	 * 
	 * @return
	 */
	@Override
	public CompilationUnitResource getOwnerVirtualModelResource();

	public static abstract class AddOBP2AnalysisImpl<VMI extends VirtualModelInstance<VMI, ?>>
			extends AbstractAddVirtualModelInstanceImpl<OBP2Analysis, VMI> implements AddOBP2Analysis<VMI> {

		static final Logger logger = Logger.getLogger(AddOBP2Analysis.class.getPackage().getName());

		@Override
		public Class<VMI> getVirtualModelInstanceClass() {
			return (Class) VirtualModelInstance.class;
		}

		@Override
		public OBP2Analysis execute(RunTimeEvaluationContext evaluationContext) throws FlexoException {
			System.out.println("Now create a OBP2Analysis");
			return super.execute(evaluationContext);
		}

		@Override
		public CompilationUnitResource getOwnerVirtualModelResource() {
			if (getReceiver().isSet() && getReceiver().isValid()) {
				Type type = getReceiver().getAnalyzedType();
				if (type instanceof VirtualModelInstanceType) {
					return ((VirtualModelInstanceType) type).getVirtualModel().getResource();
				}
			}
			return null;
		}

		@Override
		public void notifiedBindingChanged(DataBinding<?> dataBinding) {
			super.notifiedBindingChanged(dataBinding);
			if (dataBinding == getReceiver()) {
				getPropertyChangeSupport().firePropertyChange("ownerVirtualModelResource", null, getOwnerVirtualModelResource());
			}
		}

		@Override
		protected OBP2Analysis makeNewFlexoConceptInstance(RunTimeEvaluationContext evaluationContext) throws FlexoException {
			VirtualModelInstance<?, ?> container = getVirtualModelInstance(evaluationContext);
			logger.info("container: " + container);
			if (container == null) {
				logger.warning("null container");
				return null;
			}
			if (evaluationContext instanceof FlexoBehaviourAction) {
				String name = null;
				String title = null;
				try {
					name = getVirtualModelInstanceName().getBindingValue(evaluationContext);
					title = getVirtualModelInstanceTitle().getBindingValue(evaluationContext);
				} catch (TypeMismatchException e) {
					e.printStackTrace();
				} catch (NullReferenceException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}

				VirtualModel instantiatedVirtualModel = (VirtualModel) retrieveFlexoConcept(evaluationContext);

				CreateBasicOBP2Analysis createVMIAction = CreateBasicOBP2Analysis.actionType.makeNewEmbeddedAction(container, null,
						(FlexoBehaviourAction<?, ?, ?>) evaluationContext);
				createVMIAction.setSkipChoosePopup(true);
				createVMIAction.setNewVirtualModelInstanceName(name);
				createVMIAction.setNewVirtualModelInstanceTitle(title);
				createVMIAction.setVirtualModel(instantiatedVirtualModel);
				// He we just want to create a PLAIN and EMPTY FMLRTVirtualModelInstance,
				// eventual CreationScheme will be executed later
				// DONT UNCOMMENT THIS !!!!
				/*if (getCreationScheme() != null) {
					createVMIAction.setCreationScheme(getCreationScheme());
				}*/
				createVMIAction.doAction();
				return createVMIAction.getNewVirtualModelInstance();
			}

			logger.warning("Unexpected RunTimeEvaluationContext");
			return null;

		}

	}
}
