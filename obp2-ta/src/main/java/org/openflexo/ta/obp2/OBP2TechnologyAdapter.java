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

import java.util.logging.Logger;

import org.openflexo.foundation.fml.annotations.DeclareModelSlots;
import org.openflexo.foundation.fml.annotations.DeclareResourceFactories;
import org.openflexo.foundation.resource.FlexoResourceCenter;
import org.openflexo.foundation.technologyadapter.TechnologyAdapter;
import org.openflexo.ta.obp2.fml.binding.OBP2BindingFactory;
import org.openflexo.ta.obp2.rm.OBP2AnalysisResourceFactory;
import org.openflexo.ta.obp2.rm.OBP2AnalysisResourceRepository;

/**
 * This class defines and implements an archetype of a technology adapter<br>
 * 
 * The idea is to demonstrate TechnologyAdapter API.
 * 
 * We offer the connection to a text file with a single role mapping a line in a text file
 * 
 * @author sylvain
 * 
 */
@DeclareModelSlots({ OBP2ModelSlot.class })
// You might declare your own types here
// @DeclareTechnologySpecificTypes({ YourCustomType.class })
@DeclareResourceFactories({ OBP2AnalysisResourceFactory.class })
public class OBP2TechnologyAdapter extends TechnologyAdapter<OBP2TechnologyAdapter> {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(OBP2TechnologyAdapter.class.getPackage().getName());

	private static final OBP2BindingFactory BINDING_FACTORY = new OBP2BindingFactory();

	@Override
	public String getName() {
		return "OBP2 technology adapter";
	}

	@Override
	protected String getLocalizationDirectory() {
		return "FlexoLocalization/OBP2TechnologyAdapter";
	}

	@Override
	public void ensureAllRepositoriesAreCreated(FlexoResourceCenter<?> rc) {
		super.ensureAllRepositoriesAreCreated(rc);
		getOBP2AnalysisResourceRepository(rc);

	}

	@Override
	public <I> boolean isIgnorable(final FlexoResourceCenter<I> resourceCenter, final I contents) {
		if (resourceCenter.isIgnorable(contents, this)) {
			return true;
		}

		// This allows to ignore all contained OBP2 resources, that will be explored from their container resource
		if (resourceCenter.isDirectory(contents)) {
			if (FlexoResourceCenter.isContainedInDirectoryWithSuffix(resourceCenter, contents, OBP2AnalysisResourceFactory.OBP2_SUFFIX)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public OBP2BindingFactory getTechnologyAdapterBindingFactory() {
		return BINDING_FACTORY;
	}

	@Override
	public String getIdentifier() {
		return "OBP2";
	}

	public OBP2AnalysisResourceFactory getOBP2AnalysisResourceFactory() {
		return getResourceFactory(OBP2AnalysisResourceFactory.class);
	}

	@SuppressWarnings("unchecked")
	public <I> OBP2AnalysisResourceRepository<I> getOBP2AnalysisResourceRepository(FlexoResourceCenter<I> resourceCenter) {
		OBP2AnalysisResourceRepository<I> returned = resourceCenter.retrieveRepository(OBP2AnalysisResourceRepository.class, this);
		if (returned == null) {
			returned = OBP2AnalysisResourceRepository.instanciateNewRepository(this, resourceCenter);
			resourceCenter.registerRepository(returned, OBP2AnalysisResourceRepository.class, this);
		}
		return returned;
	}

}
