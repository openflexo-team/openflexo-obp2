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

import java.util.List;

import org.openflexo.foundation.fml.FlexoRole;
import org.openflexo.foundation.fml.rt.FlexoConceptInstance;
import org.openflexo.foundation.fml.rt.VirtualModelInstance;
import org.openflexo.toolbox.StringUtils;

import plug.core.IConfiguration;
import plug.explorer.buchi.nested_dfs.Color;

/**
 * This class represents the {@link IConfiguration} implementation. It wraps IConfiguration FML concept
 * 
 * @author sylvain
 *
 */
public class FMLConfiguration extends VirtualModelInstanceWrapper implements IConfiguration<FMLConfiguration> {

	// private static List<FMLConfiguration> configs = new ArrayList<FMLConfiguration>();

	public FMLConfiguration(VirtualModelInstance<?, ?> base) {
		super(base);
		// configs.add(this);
		/*if (configs.size() == 4) {
			FMLConfiguration config0 = configs.get(0);
			FMLConfiguration config1 = configs.get(1);
			FMLConfiguration config2 = configs.get(2);
			FMLConfiguration config3 = configs.get(3);
		
			System.out.println("config0  HASH=" + Integer.toHexString(config0.hashCode()));
			System.out.println(config0.render());
		
			System.out.println("config1  HASH=" + Integer.toHexString(config1.hashCode()));
			System.out.println(config1.render());
		
			System.out.println("config2  HASH=" + Integer.toHexString(config2.hashCode()));
			System.out.println(config2.render());
		
			System.out.println("config3  HASH=" + Integer.toHexString(config3.hashCode()));
			System.out.println(config3.render());
		
			System.out.println("0 et 3 : EQUALS=" + config0.equals(config3));
		
			System.out.println("BON ON Y VA !!!!!!");
			System.out.println("2 et 3 : EQUALS=" + config2.equals(config3));
		
			// System.exit(-1);
		}*/
	}

	/*private FMLRTVirtualModelInstance cloneVirtualModelInstance(VirtualModelInstance<?, ?> toBeCloned) {
	
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
			FlexoConceptInstance clonedFCI = cloneFlexoConceptInstance(fci, toBeCloned.getFactory());
			clone.addToFlexoConceptInstances(clonedFCI);
		}
		return clone;
	}
	
	private FlexoConceptInstance cloneFlexoConceptInstance(FlexoConceptInstance toBeCloned,
			AbstractVirtualModelInstanceModelFactory<?> factory) {
	
		System.out.println("On cherche a cloner le FCI : " + toBeCloned);
		System.out.println("factory=" + toBeCloned.getFactory());
		System.out.println("vmi=" + toBeCloned.getVirtualModelInstance());
		System.out.println("resource=" + toBeCloned.getVirtualModelInstance().getResource());
	
		FlexoConceptInstance clone = factory.newInstance(FlexoConceptInstance.class);
		clone.setFlexoConcept(toBeCloned.getFlexoConcept());
		clone.setLocalFactory(factory);
		for (FlexoRole flexoRole : toBeCloned.getFlexoConcept().getAccessibleRoles()) {
			Object value = toBeCloned.getFlexoPropertyValue(flexoRole);
			System.out.println("role: " + flexoRole + " value=" + value);
			clone.setFlexoPropertyValue(flexoRole, value);
		}
		for (FlexoConceptInstance fci : toBeCloned.getEmbeddedFlexoConceptInstances()) {
			FlexoConceptInstance clonedFCI = cloneFlexoConceptInstance(fci, factory);
			clone.addToEmbeddedFlexoConceptInstances(clonedFCI);
		}
		return clone;
	}*/

	@Override
	public FMLConfiguration createCopy() {
		// VirtualModelInstance<?, ?> baseCopy = cloneVirtualModelInstance(getBase());
		VirtualModelInstance<?, ?> baseCopy = getBase().cloneUsingRoles(getBase().getFactory());
		FMLConfiguration returned = new FMLConfiguration(baseCopy);
		System.out.println("NEW FMLConfiguration " + Integer.toHexString(returned.hashCode()));
		System.out.println(render());
		return returned;
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

	@Override
	public int hashCode() {
		return getBase().hashCodeUsingRoles();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			System.out.println("Pas bon 1");
			return false;
		}
		if (getClass() != obj.getClass()) {
			System.out.println("Pas bon 2");
			return false;
		}
		FMLConfiguration other = (FMLConfiguration) obj;
		if (getBase() == null) {
			if (other.getBase() != null) {
				System.out.println("Pas bon 3");
				return false;
			}
		}
		else if (!getBase().equalsUsingRoles(other.getBase())) {
			System.out.println("Pas bon 4 pour " + getBase());
			return false;
		}
		return true;
	}

	public String render() {
		StringBuffer sb = new StringBuffer();
		String line = StringUtils.buildString('-', 80) + "\n";
		sb.append(line);
		sb.append("VirtualModelInstance : " + getBase().getUserFriendlyIdentifier() + "\n");
		sb.append("VirtualModel         : " + getBase().getVirtualModel().getName() + "\n");
		sb.append("Instances            : " + getBase().getFlexoConceptInstances().size() + "\n");
		sb.append(line);
		List<FlexoRole> roles = getBase().getVirtualModel().getAccessibleProperties(FlexoRole.class);
		if (roles.size() > 0) {
			for (FlexoRole<?> role : roles) {
				sb.append(role.getName() + " = " + getBase().getFlexoPropertyValue(role) + "\n");
			}
		}
		else {
			sb.append("No values" + "\n");
		}
		sb.append(line);
		for (FlexoConceptInstance fci : getBase().getFlexoConceptInstances()) {
			sb.append("FlexoConcept : " + fci.getFlexoConcept().getName() + "\n");
			List<FlexoRole> fciRoles = fci.getFlexoConcept().getAccessibleProperties(FlexoRole.class);
			for (FlexoRole<?> role : fciRoles) {
				sb.append("      " + role.getName() + " = " + fci.getFlexoPropertyValue(role) + "\n");
			}

		}
		sb.append(line);
		return sb.toString();
	}

}
