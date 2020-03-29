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

import java.util.Collection;
import java.util.HashSet;

import plug.core.IFiredTransition;

/**
 * This class represents the {@link IFiredTransition} implementation.
 * 
 * @author sylvain
 *
 */
public class FMLFiredTransition implements IFiredTransition<FMLConfiguration, Object> {

	private FMLConfiguration source;
	private Collection<FMLConfiguration> targets;
	private Object action;
	private Object payload;

	public FMLFiredTransition(FMLConfiguration source, FMLConfiguration target) {
		super();
		this.source = source;
		targets = new HashSet<>();
		targets.add(target);
	}

	@Override
	public FMLConfiguration getSource() {
		return source;
	}

	@Override
	public void setSource(FMLConfiguration source) {
		this.source = source;
	}

	@Override
	public Collection<FMLConfiguration> getTargets() {
		return targets;
	}

	@Override
	public void setTargets(Collection<FMLConfiguration> targets) {
		this.targets = targets;
	}

	@Override
	public Object getAction() {
		return action;
	}

	@Override
	public void setAction(Object action) {
		this.action = action;
	}

	@Override
	public Object getPayload() {
		return payload;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = payload;
	}

}
