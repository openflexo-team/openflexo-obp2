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

import java.util.logging.Logger;

import org.openflexo.foundation.PamelaResourceModelFactory;
import org.openflexo.foundation.action.FlexoUndoManager;
import org.openflexo.foundation.resource.PamelaResourceImpl.IgnoreLoadingEdits;
import org.openflexo.pamela.ModelContextLibrary;
import org.openflexo.pamela.exceptions.ModelDefinitionException;
import org.openflexo.pamela.factory.EditingContext;
import org.openflexo.pamela.factory.ModelFactory;
import org.openflexo.ta.obp2.rm.OBP2AnalysisResource;

/**
 * A {@link ModelFactory} used to manage a OBP2Analysis<br>
 * One instance of this class should be used for each {@link OBP2AnalysisResource}
 * 
 * @author sylvain
 * 
 */
public class OBP2ModelFactory extends ModelFactory implements PamelaResourceModelFactory<OBP2AnalysisResource> {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(OBP2ModelFactory.class.getPackage().getName());

	private final OBP2AnalysisResource resource;
	private IgnoreLoadingEdits ignoreHandler = null;
	private FlexoUndoManager undoManager = null;

	public OBP2ModelFactory(OBP2AnalysisResource resource, EditingContext editingContext) throws ModelDefinitionException {
		super(ModelContextLibrary.getCompoundModelContext(OBP2Analysis.class));
		this.resource = resource;
		setEditingContext(editingContext);
	}

	@Override
	public OBP2AnalysisResource getResource() {
		return resource;
	}

	public OBP2Analysis makeXXText() {
		return newInstance(OBP2Analysis.class);
	}

	public OBP2XXX makeXXLine(String value, int index) {
		OBP2XXX returned = newInstance(OBP2XXX.class);
		returned.setValue(value);
		returned.setIndex(index);
		return returned;
	}

	@Override
	public synchronized void startDeserializing() {
		EditingContext editingContext = getResource().getServiceManager().getEditingContext();

		if (editingContext != null && editingContext.getUndoManager() instanceof FlexoUndoManager) {
			undoManager = (FlexoUndoManager) editingContext.getUndoManager();
			undoManager.addToIgnoreHandlers(ignoreHandler = new IgnoreLoadingEdits(resource));
			System.out.println("@@@@@@@@@@@@@@@@ START LOADING RESOURCE " + resource.getURI());
		}

	}

	@Override
	public synchronized void stopDeserializing() {
		if (ignoreHandler != null) {
			undoManager.removeFromIgnoreHandlers(ignoreHandler);
			System.out.println("@@@@@@@@@@@@@@@@ END LOADING RESOURCE " + resource.getURI());
		}

	}

}
