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
import org.openflexo.foundation.action.FlexoActionFactory;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.rm.VirtualModelResource;
import org.openflexo.foundation.fml.rt.action.AbstractCreateVirtualModelInstance;
import org.openflexo.foundation.fml.rt.rm.AbstractVirtualModelInstanceResource;
import org.openflexo.foundation.resource.RepositoryFolder;
import org.openflexo.foundation.resource.SaveResourceException;
import org.openflexo.pamela.exceptions.ModelDefinitionException;
import org.openflexo.ta.obp2.OBP2TechnologyAdapter;
import org.openflexo.ta.obp2.model.OBP2Analysis;
import org.openflexo.ta.obp2.rm.OBP2AnalysisResource;
import org.openflexo.ta.obp2.rm.OBP2AnalysisResourceFactory;

/**
 * This action is called to create a regular {@link OBP2Analysis} either as top level in a repository folder, or as a contained
 * {@link OBP2Analysis} in a container {@link OBP2Analysis}
 * 
 * @author sylvain
 *
 */
public abstract class AbstractCreateOBP2Analysis<A extends AbstractCreateOBP2Analysis<A>>
		extends AbstractCreateVirtualModelInstance<A, FlexoObject, OBP2Analysis, OBP2TechnologyAdapter> {

	private static final Logger logger = Logger.getLogger(AbstractCreateOBP2Analysis.class.getPackage().getName());

	protected AbstractCreateOBP2Analysis(FlexoActionFactory<A, FlexoObject, FlexoObject> actionType, FlexoObject focusedObject,
			Vector<FlexoObject> globalSelection, FlexoEditor editor) {
		super(actionType, focusedObject, globalSelection, editor);
	}

	@Override
	public OBP2AnalysisResource makeVirtualModelInstanceResource() throws SaveResourceException {

		OBP2TechnologyAdapter obp2TechnologyAdapter = getServiceManager().getTechnologyAdapterService()
				.getTechnologyAdapter(OBP2TechnologyAdapter.class);
		OBP2AnalysisResourceFactory factory = obp2TechnologyAdapter.getOBP2AnalysisResourceFactory();

		OBP2AnalysisResource returned = null;
		try {
			if (getContainerVirtualModelInstance() != null) {
				returned = factory.makeContainedFMLRTVirtualModelInstanceResource(getNewVirtualModelInstanceName(),
						(VirtualModelResource) getVirtualModel().getResource(),
						(AbstractVirtualModelInstanceResource<?, ?>) getContainerVirtualModelInstance().getResource(),
						obp2TechnologyAdapter.getTechnologyContextManager(), true);
			}
			else if (getFolder() != null) {
				returned = factory.makeTopLevelFMLRTVirtualModelInstanceResource(getNewVirtualModelInstanceName(), null,
						// Let URI be automatically computed
						(VirtualModelResource) getVirtualModel().getResource(), getFolder(), true);
			}

			if (returned != null) {
				returned.getLoadedResourceData().setTitle(getNewVirtualModelInstanceTitle());
			}

			return returned;
		} catch (ModelDefinitionException e) {
			e.printStackTrace();
			return null;
		}

		// return FMLRTVirtualModelInstanceImpl.newVirtualModelInstance(getNewVirtualModelInstanceName(), getNewVirtualModelInstanceTitle(),
		// getVirtualModel(), getFocusedObject());
	}

	@Override
	public <I> RepositoryFolder<OBP2AnalysisResource, I> getFolder() {
		return (RepositoryFolder<OBP2AnalysisResource, I>) super.getFolder();
	}

	public boolean isVisible(VirtualModel virtualModel) {
		return true;
	}

}
