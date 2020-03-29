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

package org.openflexo.ta.obp2.model;

import org.openflexo.foundation.fml.FlexoRole;
import org.openflexo.foundation.fml.rt.FMLRTVirtualModelInstance;
import org.openflexo.foundation.fml.rt.FlexoConceptInstance;
import org.openflexo.foundation.fml.rt.VirtualModelInstance;

import plug.core.IConfiguration;
import plug.explorer.buchi.nested_dfs.Color;

/**
 * This class represents the {@link IConfiguration} implementation. It wraps IConfiguration FML concept
 * 
 * @author sylvain
 *
 */
public class FMLConfiguration extends VirtualModelInstanceWrapper implements IConfiguration<FMLConfiguration> {

	public FMLConfiguration(VirtualModelInstance<?, ?> base) {
		super(base);
	}

	private FMLRTVirtualModelInstance cloneVirtualModelInstance(VirtualModelInstance<?, ?> toBeCloned) {

		System.out.println("On cherche a cloner le VMI : " + toBeCloned);
		System.out.println("factory=" + toBeCloned.getFactory());
		System.out.println("resource=" + toBeCloned.getResource());

		FMLRTVirtualModelInstance clone = toBeCloned.getFactory().newInstance(FMLRTVirtualModelInstance.class);
		clone.setVirtualModel(toBeCloned.getVirtualModel());
		clone.setLocalFactory(toBeCloned.getFactory());
		for (FlexoRole flexoRole : toBeCloned.getVirtualModel().getAccessibleRoles()) {
			Object value = toBeCloned.getFlexoPropertyValue(flexoRole);
			System.out.println("role: " + flexoRole + " value=" + value);
			clone.setFlexoPropertyValue(flexoRole, value);
		}
		for (FlexoConceptInstance fci : toBeCloned.getFlexoConceptInstances()) {
			FlexoConceptInstance clonedFCI = cloneFlexoConceptInstance(fci);
			clone.addToFlexoConceptInstances(clonedFCI);
		}
		return clone;
	}

	private FlexoConceptInstance cloneFlexoConceptInstance(FlexoConceptInstance toBeCloned) {

		System.out.println("On cherche a cloner le FCI : " + toBeCloned);
		System.out.println("factory=" + toBeCloned.getFactory());
		System.out.println("vmi=" + toBeCloned.getVirtualModelInstance());
		System.out.println("resource=" + toBeCloned.getVirtualModelInstance().getResource());

		FlexoConceptInstance clone = toBeCloned.getFactory().newInstance(FlexoConceptInstance.class);
		clone.setFlexoConcept(toBeCloned.getFlexoConcept());
		for (FlexoRole flexoRole : toBeCloned.getFlexoConcept().getAccessibleRoles()) {
			Object value = toBeCloned.getFlexoPropertyValue(flexoRole);
			System.out.println("role: " + flexoRole + " value=" + value);
			clone.setFlexoPropertyValue(flexoRole, value);
		}
		for (FlexoConceptInstance fci : toBeCloned.getEmbeddedFlexoConceptInstances()) {
			FlexoConceptInstance clonedFCI = cloneFlexoConceptInstance(fci);
			clone.addToEmbeddedFlexoConceptInstances(clonedFCI);
		}
		return clone;
	}

	@Override
	public FMLConfiguration createCopy() {
		VirtualModelInstance<?, ?> baseCopy = cloneVirtualModelInstance(getBase());
		return new FMLConfiguration(baseCopy);
	}

	@Override
	public String toString() {
		return "FMLConfiguration:[" + getBase().toString() + "]";
	}

	private Color metadata = Color.WHITE;

	@Override
	public Object getMetadata() {
		return metadata;
	}

	@Override
	public void setMetadata(Object md) {
		metadata = (Color) md;
	}

}
