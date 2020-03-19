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

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.resource.FlexoResource;
import org.openflexo.foundation.resource.FlexoResourceCenter;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.foundation.test.OpenflexoProjectAtRunTimeTestCase;
import org.openflexo.ta.obp2.model.XXText;
import org.openflexo.ta.obp2.rm.XXTextResource;

public abstract class AbstractOBP2Test extends OpenflexoProjectAtRunTimeTestCase {
	protected static final Logger logger = Logger.getLogger(AbstractOBP2Test.class.getPackage().getName());

	protected XXTextResource getXXResource(String documentName, FlexoResourceCenter<?> resourceCenter) {

		String documentURI = resourceCenter.getDefaultBaseURI() + "/" + "OBP2" + "/" + documentName;
		System.out.println("Searching " + documentURI);

		XXTextResource documentResource = (XXTextResource) serviceManager.getResourceManager().getResource(documentURI, null, XXText.class);

		if (documentResource == null) {
			logger.warning("Cannot find document resource " + documentURI);
			for (FlexoResource<?> r : serviceManager.getResourceManager().getRegisteredResources()) {
				System.out.println("> " + r.getURI());
			}
		}

		assertNotNull(documentResource);

		return documentResource;
	}

	protected XXTextResource getXXResource(String documentName) {

		FlexoResourceCenter<?> resourceCenter = serviceManager.getResourceCenterService()
				.getFlexoResourceCenter("http://www.openflexo.org/test/xx");

		return getXXResource(documentName, resourceCenter);
	}

	protected XXText getXXText(String documentName) {

		XXTextResource documentResource = getXXResource(documentName);
		assertNotNull(documentResource);

		XXText document = null;
		try {
			document = documentResource.getResourceData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ResourceLoadingCancelledException e) {
			e.printStackTrace();
		} catch (FlexoException e) {
			e.printStackTrace();
		}
		assertNotNull(document);

		return document;
	}

}
