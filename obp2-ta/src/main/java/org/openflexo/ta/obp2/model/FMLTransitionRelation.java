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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openflexo.connie.exception.InvalidBindingException;
import org.openflexo.connie.exception.NullReferenceException;
import org.openflexo.connie.exception.TypeMismatchException;
import org.openflexo.foundation.fml.rt.VirtualModelInstance;

import plug.core.ITransitionRelation;

/**
 * This class represents the {@link ITransitionRelation} implementation wrapping a FML concept
 * 
 * @author sylvain
 *
 */
public class FMLTransitionRelation extends VirtualModelInstanceWrapper implements ITransitionRelation<FMLConfiguration, Object> {

	public FMLTransitionRelation(VirtualModelInstance<?, ?> base) {
		super(base);
	}

	@Override
	public Set<FMLConfiguration> initialConfigurations() {
		try {
			List<VirtualModelInstance<?, ?>> initialConfigurations = getBase().execute("this.initialConfigurations()");
			System.out.println("Initial configurations = " + initialConfigurations);
			Set<FMLConfiguration> returned = new HashSet<>();
			for (VirtualModelInstance<?, ?> virtualModelInstance : initialConfigurations) {
				returned.add(new FMLConfiguration(virtualModelInstance));
			}
			return returned;
		} catch (TypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidBindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Collection<Object> fireableTransitionsFrom(FMLConfiguration configuration) {
		try {
			System.out.println("Pour la configuration " + configuration);
			List<Object> fireableTransitions = getBase().execute("this.fireableTransitionsFrom({$configuration})", configuration.getBase());
			System.out.println("fireableTransitions=" + fireableTransitions);
			return fireableTransitions;
		} catch (TypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidBindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	@Override
	public FMLFiredTransition fireOneTransition(FMLConfiguration source, Object transition) {
		try {
			System.out.println("On veut executer la transition " + transition);
			FMLConfiguration target = source.createCopy();
			getBase().execute("this.fireOneTransition({$source},{$transition})", target.getBase(), transition);
			FMLFiredTransition firedTransition = new FMLFiredTransition(source, target);
			return firedTransition;
		} catch (TypeMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidBindingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
