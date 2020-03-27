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

package org.openflexo.ta.obp2.controller;

import java.util.logging.Logger;

import javax.swing.ImageIcon;

import org.openflexo.foundation.fml.FlexoRole;
import org.openflexo.foundation.fml.editionaction.EditionAction;
import org.openflexo.foundation.technologyadapter.TechnologyObject;
import org.openflexo.gina.utils.InspectorGroup;
import org.openflexo.icon.IconFactory;
import org.openflexo.icon.IconLibrary;
import org.openflexo.ta.obp2.OBP2TechnologyAdapter;
import org.openflexo.ta.obp2.fml.OBP2XXXRole;
import org.openflexo.ta.obp2.fml.editionaction.PerformBFSAnalysis;
import org.openflexo.ta.obp2.gui.OBP2IconLibrary;
import org.openflexo.ta.obp2.model.OBP2Analysis;
import org.openflexo.ta.obp2.model.OBP2Object;
import org.openflexo.ta.obp2.model.OBP2XXX;
import org.openflexo.ta.obp2.view.XXTextView;
import org.openflexo.view.EmptyPanel;
import org.openflexo.view.ModuleView;
import org.openflexo.view.controller.ControllerActionInitializer;
import org.openflexo.view.controller.FlexoController;
import org.openflexo.view.controller.TechnologyAdapterController;
import org.openflexo.view.controller.model.FlexoPerspective;

public class OBP2AdapterController extends TechnologyAdapterController<OBP2TechnologyAdapter> {

	static final Logger logger = Logger.getLogger(OBP2AdapterController.class.getPackage().getName());

	private InspectorGroup obp2InspectorGroup;

	@Override
	public Class<OBP2TechnologyAdapter> getTechnologyAdapterClass() {
		return OBP2TechnologyAdapter.class;
	}

	/**
	 * Initialize inspectors for supplied module using supplied {@link FlexoController}
	 * 
	 * @param controller
	 */
	@Override
	protected void initializeInspectors(FlexoController controller) {

		obp2InspectorGroup = controller.loadInspectorGroup("OBP2", getTechnologyAdapter().getLocales(),
				getFMLTechnologyAdapterInspectorGroup());
	}

	/**
	 * Return inspector group for this technology
	 * 
	 * @return
	 */
	@Override
	public InspectorGroup getTechnologyAdapterInspectorGroup() {
		return obp2InspectorGroup;
	}

	@Override
	protected void initializeActions(ControllerActionInitializer actionInitializer) {

		// You can initialize here actions specific to that technology
	}

	/**
	 * Return icon representing underlying technology, required size is 32x32
	 * 
	 * @return
	 */
	@Override
	public ImageIcon getTechnologyBigIcon() {
		return OBP2IconLibrary.OBP2_TA_BIG_ICON;
	}

	/**
	 * Return icon representing underlying technology
	 * 
	 * @return
	 */
	@Override
	public ImageIcon getTechnologyIcon() {
		return OBP2IconLibrary.OBP2_TA_ICON;
	}

	/**
	 * Return icon representing a model of underlying technology
	 * 
	 * @return
	 */
	@Override
	public ImageIcon getModelIcon() {
		return OBP2IconLibrary.OBP2_ANALYSIS_ICON;
	}

	/**
	 * Return icon representing a model of underlying technology
	 * 
	 * @return
	 */
	@Override
	public ImageIcon getMetaModelIcon() {
		return OBP2IconLibrary.OBP2_ANALYSIS_ICON;
	}

	/**
	 * Return icon representing supplied ontology object
	 * 
	 * @param object
	 * @return
	 */
	@Override
	public ImageIcon getIconForTechnologyObject(Class<? extends TechnologyObject<?>> objectClass) {
		if (OBP2Object.class.isAssignableFrom(objectClass)) {
			return OBP2IconLibrary.iconForObject((Class<? extends OBP2Object>) objectClass);
		}
		return null;
	}

	/**
	 * Return icon representing supplied pattern property
	 * 
	 * @param object
	 * @return
	 */
	@Override
	public ImageIcon getIconForFlexoRole(Class<? extends FlexoRole<?>> patternRoleClass) {
		if (OBP2XXXRole.class.isAssignableFrom(patternRoleClass)) {
			return getIconForTechnologyObject(OBP2XXX.class);
		}
		return null;
	}

	/**
	 * Return icon representing supplied edition action
	 * 
	 * @param object
	 * @return
	 */
	@Override
	public ImageIcon getIconForEditionAction(Class<? extends EditionAction> editionActionClass) {
		if (PerformBFSAnalysis.class.isAssignableFrom(editionActionClass)) {
			return IconFactory.getImageIcon(OBP2IconLibrary.OBP2_ANALYSIS_ICON, IconLibrary.DUPLICATE);
		}
		/*else if (AbstractSelectXXLine.class.isAssignableFrom(editionActionClass)) {
			return IconFactory.getImageIcon(getIconForTechnologyObject(OBP2XXX.class), IconLibrary.IMPORT);
		}*/
		return super.getIconForEditionAction(editionActionClass);
	}

	@Override
	public boolean hasModuleViewForObject(TechnologyObject<OBP2TechnologyAdapter> object, FlexoController controller) {
		return object instanceof OBP2Analysis;
	}

	@Override
	public String getWindowTitleforObject(TechnologyObject<OBP2TechnologyAdapter> object, FlexoController controller) {
		if (object instanceof OBP2Analysis) {
			return ((OBP2Analysis) object).getResource().getName();
		}
		return object.toString();
	}

	@Override
	public ModuleView<?> createModuleViewForObject(TechnologyObject<OBP2TechnologyAdapter> object, FlexoController controller,
			FlexoPerspective perspective) {
		if (object instanceof OBP2Analysis) {
			XXTextView returned = new XXTextView((OBP2Analysis) object, controller, perspective);
			return returned;
		}
		return new EmptyPanel<>(controller, perspective, object);
	}

}
