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

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.openflexo.connie.type.CustomTypeFactory;
import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.VirtualModelInstanceType;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.ta.obp2.OBP2ModelSlot;
import org.openflexo.ta.obp2.OBP2TechnologyAdapter;

/**
 * Represent the type of an instance of a {@link VirtualModel} in the context of {@link OBP2ModelSlot}
 * 
 * @author sylvain
 * 
 */
public class OBP2VirtualModelInstanceType extends VirtualModelInstanceType {

	public static OBP2VirtualModelInstanceType UNDEFINED_VIRTUAL_MODEL_INSTANCE_TYPE = new OBP2VirtualModelInstanceType(
			(VirtualModel) null);

	private OBP2VirtualModelInstanceType(VirtualModel aVirtualModel) {
		super(aVirtualModel);
	}

	public OBP2VirtualModelInstanceType(String virtualModelURI, CustomTypeFactory<?> factory) {
		super(virtualModelURI, factory);
	}

	@Override
	public Class<?> getBaseClass() {
		return OBP2Analysis.class;
	}

	private static Map<VirtualModel, OBP2VirtualModelInstanceType> types = new HashMap<>();

	public static OBP2VirtualModelInstanceType getVirtualModelInstanceType(VirtualModel aVirtualModel) {
		if (aVirtualModel != null) {
			OBP2VirtualModelInstanceType returned = types.get(aVirtualModel);
			if (returned == null) {
				returned = new OBP2VirtualModelInstanceType(aVirtualModel);
				types.put(aVirtualModel, returned);
			}
			return returned;
		}
		// logger.warning("Trying to get a VirtualModelInstanceType for a null VirtualModel");
		return UNDEFINED_VIRTUAL_MODEL_INSTANCE_TYPE;
	}

	/**
	 * Factory for {@link VirtualModelInstanceType} instances
	 * 
	 * @author sylvain
	 * 
	 */
	public static class HbnVirtualModelInstanceTypeFactory
			extends AbstractVirtualModelInstanceTypeFactory<OBP2VirtualModelInstanceType, OBP2TechnologyAdapter> {

		public HbnVirtualModelInstanceTypeFactory(OBP2TechnologyAdapter technologyAdapter) {
			super(technologyAdapter);
		}

		@Override
		public Class<OBP2VirtualModelInstanceType> getCustomType() {
			return OBP2VirtualModelInstanceType.class;
		}

		@Override
		public OBP2VirtualModelInstanceType getType(String configuration, CustomTypeFactory<?> factory) {
			return new OBP2VirtualModelInstanceType(configuration, this);
		}

		@Override
		public OBP2VirtualModelInstanceType getType(VirtualModel virtualModel) {
			return getVirtualModelInstanceType(virtualModel);
		}

		@Override
		public VirtualModel resolveVirtualModel(OBP2VirtualModelInstanceType typeToResolve) {
			try {
				return getTechnologyAdapter().getTechnologyAdapterService().getServiceManager().getVirtualModelLibrary()
						.getVirtualModel(typeToResolve.getConceptURI());
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
			return null;
		}

	}

}
