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

package org.openflexo.ta.obp2.model;

import java.util.logging.Logger;

import org.openflexo.foundation.fml.rt.FMLRTVirtualModelInstanceRepository;
import org.openflexo.foundation.fml.rt.VirtualModelInstance;
import org.openflexo.pamela.annotations.ImplementationClass;
import org.openflexo.pamela.annotations.ModelEntity;
import org.openflexo.pamela.annotations.XMLElement;
import org.openflexo.ta.obp2.OBP2TechnologyAdapter;
import org.openflexo.ta.obp2.rm.OBP2AnalysisResourceFactory;

/**
 * Represents OBP2 analysis, as a {@link VirtualModelInstance}
 * 
 * @author sylvain
 * 
 */
@ModelEntity
@ImplementationClass(OBP2Analysis.OBP2AnalysisImpl.class)
@XMLElement
public interface OBP2Analysis extends VirtualModelInstance<OBP2Analysis, OBP2TechnologyAdapter> {

	@Override
	public FMLRTVirtualModelInstanceRepository<?> getVirtualModelInstanceRepository();

	public static abstract class OBP2AnalysisImpl extends VirtualModelInstanceImpl<OBP2Analysis, OBP2TechnologyAdapter>
			implements OBP2Analysis {

		private static final Logger logger = Logger.getLogger(OBP2Analysis.class.getPackage().getName());

		@Override
		public OBP2TechnologyAdapter getTechnologyAdapter() {
			if (getResource() != null) {
				return getResource().getTechnologyAdapter();
			}
			return null;
		}

		@Override
		public FMLRTVirtualModelInstanceRepository<?> getVirtualModelInstanceRepository() {
			if (getResource() != null) {
				return getResource().getResourceCenter().getVirtualModelInstanceRepository();
			}
			return null;
		}

		/**
		 * Returns URI for this {@link VirtualModelInstance}.<br>
		 * Note that if this {@link VirtualModelInstance} is contained in another {@link VirtualModelInstance}, URI is computed from URI of
		 * container VirtualModel
		 * 
		 * The convention for URI are following:
		 * <container_virtual_model_instance_uri>/<virtual_model_instance_name >#<flexo_concept_instance_id> <br>
		 * eg<br>
		 * http://www.mydomain.org/MyVirtuaModelInstance1/MyVirtualModelInstance2#ID
		 * 
		 * @return String representing unique URI of this object
		 */
		@Override
		public String getURI() {
			if (getContainerVirtualModelInstance() != null) {
				return getContainerVirtualModelInstance().getURI() + "/" + getName() + OBP2AnalysisResourceFactory.OBP2_SUFFIX;
			}
			if (getResource() != null) {
				return getResource().getURI();
			}
			return null;
		}

	}

}
