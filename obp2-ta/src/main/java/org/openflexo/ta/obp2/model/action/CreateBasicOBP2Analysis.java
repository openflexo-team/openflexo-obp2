/**
 * 
 * Copyright (c) 2014, Openflexo
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

package org.openflexo.ta.obp2.model.action;

import java.util.Vector;
import java.util.logging.Logger;

import org.openflexo.foundation.FlexoEditor;
import org.openflexo.foundation.FlexoObject;
import org.openflexo.foundation.FlexoObject.FlexoObjectImpl;
import org.openflexo.foundation.action.FlexoActionFactory;
import org.openflexo.foundation.action.TechnologySpecificFlexoAction;
import org.openflexo.foundation.resource.RepositoryFolder;
import org.openflexo.ta.obp2.OBP2TechnologyAdapter;
import org.openflexo.ta.obp2.model.OBP2Analysis;

/**
 * This action is called to create a regular {@link OBP2Analysis} either as top level in a repository folder, or as a contained
 * {@link OBP2Analysis} in a container {@link OBP2Analysis}
 * 
 * @author sylvain
 *
 * @param <T>
 *            type of container (a repository folder or a container FMLRTVirtualModelInstance)
 */
public class CreateBasicOBP2Analysis extends AbstractCreateOBP2Analysis<CreateBasicOBP2Analysis>
		implements TechnologySpecificFlexoAction<OBP2TechnologyAdapter> {

	private static final Logger logger = Logger.getLogger(CreateBasicOBP2Analysis.class.getPackage().getName());

	public static FlexoActionFactory<CreateBasicOBP2Analysis, FlexoObject, FlexoObject> actionType = new FlexoActionFactory<CreateBasicOBP2Analysis, FlexoObject, FlexoObject>(
			"instantiate_obp2_analysis", FlexoActionFactory.newMenu, FlexoActionFactory.defaultGroup, FlexoActionFactory.ADD_ACTION_TYPE) {

		/**
		 * Factory method
		 */
		@Override
		public CreateBasicOBP2Analysis makeNewAction(FlexoObject focusedObject, Vector<FlexoObject> globalSelection, FlexoEditor editor) {
			return new CreateBasicOBP2Analysis(focusedObject, globalSelection, editor);
		}

		@Override
		public boolean isVisibleForSelection(FlexoObject container, Vector<FlexoObject> globalSelection) {
			if (container instanceof OBP2Analysis || container instanceof RepositoryFolder) {
				return true;
			}
			return false;
		}

		@Override
		public boolean isEnabledForSelection(FlexoObject container, Vector<FlexoObject> globalSelection) {
			return isVisibleForSelection(container, globalSelection);
		}

	};

	static {
		FlexoObjectImpl.addActionForClass(CreateBasicOBP2Analysis.actionType, RepositoryFolder.class);
		FlexoObjectImpl.addActionForClass(CreateBasicOBP2Analysis.actionType, OBP2Analysis.class);
	}

	protected CreateBasicOBP2Analysis(FlexoObject focusedObject, Vector<FlexoObject> globalSelection, FlexoEditor editor) {
		super(actionType, focusedObject, globalSelection, editor);
	}

	@Override
	public Class<? extends OBP2TechnologyAdapter> getTechnologyAdapterClass() {
		return OBP2TechnologyAdapter.class;
	}

}
