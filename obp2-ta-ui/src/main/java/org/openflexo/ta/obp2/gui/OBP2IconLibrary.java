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

package org.openflexo.ta.obp2.gui;

import java.util.logging.Logger;

import javax.swing.ImageIcon;

import org.openflexo.icon.ImageIconResource;
import org.openflexo.rm.ResourceLocator;
import org.openflexo.ta.obp2.model.OBP2Analysis;
import org.openflexo.ta.obp2.model.OBP2Object;

public class OBP2IconLibrary {

	private static final Logger logger = Logger.getLogger(OBP2IconLibrary.class.getPackage().getName());

	public static final ImageIconResource OBP2_TA_BIG_ICON = new ImageIconResource(
			ResourceLocator.locateResource("Icons/obp2-ta-32x32.png"));

	public static final ImageIconResource OBP2_TA_ICON = new ImageIconResource(ResourceLocator.locateResource("Icons/obp2-ta-16x16.png"));
	public static final ImageIconResource OBP2_ANALYSIS_ICON = new ImageIconResource(
			ResourceLocator.locateResource("Icons/obp2-ta-16x16.png"));
	// public static final ImageIconResource XX_LINE_ICON = new ImageIconResource(ResourceLocator.locateResource("Icons/OBP2XXX.png"));

	public static ImageIcon iconForObject(Class<? extends OBP2Object> objectClass) {
		if (OBP2Analysis.class.isAssignableFrom(objectClass)) {
			return OBP2_ANALYSIS_ICON;
		}
		/*else if (OBP2XXX.class.isAssignableFrom(objectClass)) {
			return XX_LINE_ICON;
		}*/
		logger.warning("No icon for " + objectClass);
		return null;
	}
}
