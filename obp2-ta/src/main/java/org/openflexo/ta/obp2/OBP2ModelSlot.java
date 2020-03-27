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

package org.openflexo.ta.obp2;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.logging.Logger;

import org.openflexo.connie.DataBinding;
import org.openflexo.connie.DataBinding.BindingDefinitionType;
import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.fml.FlexoRole;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.annotations.DeclareActorReferences;
import org.openflexo.foundation.fml.annotations.DeclareEditionActions;
import org.openflexo.foundation.fml.annotations.DeclareFlexoRoles;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.foundation.fml.rt.VirtualModelInstance;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.foundation.technologyadapter.FreeModelSlot;
import org.openflexo.foundation.technologyadapter.ModelSlot;
import org.openflexo.pamela.annotations.Getter;
import org.openflexo.pamela.annotations.ImplementationClass;
import org.openflexo.pamela.annotations.ModelEntity;
import org.openflexo.pamela.annotations.PropertyIdentifier;
import org.openflexo.pamela.annotations.Setter;
import org.openflexo.pamela.annotations.XMLElement;
import org.openflexo.ta.obp2.fml.OBP2XXXActorReference;
import org.openflexo.ta.obp2.fml.OBP2XXXRole;
import org.openflexo.ta.obp2.fml.editionaction.PerformBFSAnalysis;
import org.openflexo.ta.obp2.model.OBP2Analysis;

import plug.core.ILanguagePlugin;

/**
 * Implementation of the {@link ModelSlot} class for the OBP2 technology adapter
 * 
 * @author sylvain
 * 
 */
@DeclareFlexoRoles({ OBP2XXXRole.class })
@DeclareEditionActions({ PerformBFSAnalysis.class })
// @DeclareFetchRequests({ SelectUniqueXXLine.class, SelectXXLine.class })
@DeclareActorReferences({ OBP2XXXActorReference.class })
@ModelEntity
@ImplementationClass(OBP2ModelSlot.OBP2ModelSlotImpl.class)
@XMLElement
@FML("OBP2ModelSlot")
public interface OBP2ModelSlot extends FreeModelSlot<OBP2Analysis> {

	@PropertyIdentifier(type = DataBinding.class)
	String TRANSITION_RELATION_KEY = "transitionRelation";

	@Getter(value = TRANSITION_RELATION_KEY)
	public DataBinding<VirtualModelInstance<?, ?>> getTransitionRelation();

	@Setter(TRANSITION_RELATION_KEY)
	public void setTransitionRelation(DataBinding<VirtualModelInstance<?, ?>> transitionRelation);

	public static abstract class OBP2ModelSlotImpl extends FreeModelSlotImpl<OBP2Analysis> implements OBP2ModelSlot {

		@SuppressWarnings("unused")
		private static final Logger logger = Logger.getLogger(OBP2ModelSlot.class.getPackage().getName());

		@Override
		public Class<OBP2TechnologyAdapter> getTechnologyAdapterClass() {
			return OBP2TechnologyAdapter.class;
		}

		@Override
		public <PR extends FlexoRole<?>> String defaultFlexoRoleName(Class<PR> patternRoleClass) {
			if (OBP2XXXRole.class.isAssignableFrom(patternRoleClass)) {
				return "line";
			}
			return null;
		}

		@Override
		public Type getType() {

			ILanguagePlugin p;

			return OBP2Analysis.class;
		}

		@Override
		public OBP2TechnologyAdapter getModelSlotTechnologyAdapter() {
			return (OBP2TechnologyAdapter) super.getModelSlotTechnologyAdapter();
		}

		private DataBinding<VirtualModelInstance<?, ?>> transitionRelation;

		@Override
		public DataBinding<VirtualModelInstance<?, ?>> getTransitionRelation() {
			if (transitionRelation == null) {
				transitionRelation = new DataBinding<>(this, VirtualModelInstance.class, BindingDefinitionType.GET);
				transitionRelation.setBindingName("transitionRelation");
				if (getTransitionRelationVirtualModel() != null) {
					transitionRelation.setDeclaredType(getTransitionRelationVirtualModel().getInstanceType());
				}
			}
			return transitionRelation;
		}

		@Override
		public void setTransitionRelation(DataBinding<VirtualModelInstance<?, ?>> transitionRelation) {
			if (transitionRelation != null) {
				transitionRelation.setOwner(this);
				transitionRelation.setBindingName("transitionRelation");
				if (getTransitionRelationVirtualModel() != null) {
					transitionRelation.setDeclaredType(getTransitionRelationVirtualModel().getInstanceType());
				}
				else {
					transitionRelation.setDeclaredType(VirtualModelInstance.class);
				}
				transitionRelation.setBindingDefinitionType(BindingDefinitionType.GET);
			}
			this.transitionRelation = transitionRelation;
		}

		private VirtualModel getTransitionRelationVirtualModel() {
			if (getVirtualModelLibrary() != null) {
				VirtualModel transitionRelationVM;
				try {
					return getVirtualModelLibrary().getVirtualModel("http://openflexo.org/obp2/ITransitionRelation.fml");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ResourceLoadingCancelledException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FlexoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

	}
}
