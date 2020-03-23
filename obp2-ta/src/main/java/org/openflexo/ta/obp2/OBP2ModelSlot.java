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

import java.lang.reflect.Type;
import java.util.logging.Logger;

import org.openflexo.foundation.fml.FlexoRole;
import org.openflexo.foundation.fml.annotations.DeclareActorReferences;
import org.openflexo.foundation.fml.annotations.DeclareFlexoRoles;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.foundation.technologyadapter.FreeModelSlot;
import org.openflexo.foundation.technologyadapter.ModelSlot;
import org.openflexo.pamela.annotations.ImplementationClass;
import org.openflexo.pamela.annotations.ModelEntity;
import org.openflexo.pamela.annotations.XMLElement;
import org.openflexo.ta.obp2.fml.OBP2XXXActorReference;
import org.openflexo.ta.obp2.fml.OBP2XXXRole;
import org.openflexo.ta.obp2.model.OBP2Analysis;

import plug.core.ILanguagePlugin;

/**
 * Implementation of the {@link ModelSlot} class for the OBP2 technology adapter
 * 
 * @author sylvain
 * 
 */
@DeclareFlexoRoles({ OBP2XXXRole.class })
// @DeclareEditionActions({ AddXXLine.class })
// @DeclareFetchRequests({ SelectUniqueXXLine.class, SelectXXLine.class })
@DeclareActorReferences({ OBP2XXXActorReference.class })
@ModelEntity
@ImplementationClass(OBP2ModelSlot.OBP2ModelSlotImpl.class)
@XMLElement
@FML("OBP2ModelSlot")
public interface OBP2ModelSlot extends FreeModelSlot<OBP2Analysis> {

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

	}
}
