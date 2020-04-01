/**
 * 
 * Copyright (c) 2014, Openflexo
 * 
 * This file is part of Flexodiagram, a component of the software infrastructure 
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
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openflexo.connie.exception.InvalidBindingException;
import org.openflexo.connie.exception.NullReferenceException;
import org.openflexo.connie.exception.TypeMismatchException;
import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.FlexoProject;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.rt.FMLRTVirtualModelInstance;
import org.openflexo.foundation.fml.rt.action.CreateBasicVirtualModelInstance;
import org.openflexo.foundation.fml.rt.rm.FMLRTVirtualModelInstanceResource;
import org.openflexo.foundation.resource.FlexoResource;
import org.openflexo.foundation.resource.FlexoResourceCenterService;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.foundation.test.OpenflexoProjectAtRunTimeTestCase;
import org.openflexo.pamela.exceptions.ModelDefinitionException;
import org.openflexo.ta.obp2.model.OBP2Analysis;
import org.openflexo.ta.obp2.rm.OBP2AnalysisResource;
import org.openflexo.technologyadapter.diagram.DiagramTechnologyAdapter;
import org.openflexo.test.OrderedRunner;
import org.openflexo.test.TestOrder;

/**
 * This unit test is intented to test {@link OBP2Analysis} creation
 * 
 * @author sylvain
 * 
 */
@RunWith(OrderedRunner.class)
public class TestCreateOBP2Analysis extends OpenflexoProjectAtRunTimeTestCase {

	private static FMLRTVirtualModelInstance alice;
	private static FMLRTVirtualModelInstance aliceSemantics;
	private static FMLRTVirtualModelInstance aliceStateChartsLibrary;
	private static VirtualModel aliceExecutionModel;

	private static OBP2AnalysisResource obp2Resource;

	@Test
	@TestOrder(1)
	public void testCreateProject() throws ModelDefinitionException, IOException, ResourceLoadingCancelledException, FlexoException {
		instanciateTestServiceManager(OBP2TechnologyAdapter.class, DiagramTechnologyAdapter.class);
		System.out.println("SM=" + serviceManager);
		FlexoResourceCenterService rcService = serviceManager.getResourceCenterService();

		_editor = createStandaloneProject("TestCreateOBP2Analysis");
		_project = (FlexoProject<File>) _editor.getProject();
		System.out.println("Created _project " + _project.getProjectDirectory());
		assertTrue(_project.getProjectDirectory().exists());
	}

	@Test
	@TestOrder(2)
	public void instantiateStateChartsEditor() throws FileNotFoundException, ResourceLoadingCancelledException, FlexoException,
			TypeMismatchException, NullReferenceException, InvocationTargetException, InvalidBindingException {

		VirtualModel stateChartEditorVM = serviceManager.getVirtualModelLibrary()
				.getVirtualModel("http://openflexo.org/obp2/Tools/StateChartsEditor.fml");
		assertNotNull(stateChartEditorVM);

		CreateBasicVirtualModelInstance action = CreateBasicVirtualModelInstance.actionType
				.makeNewAction(_project.getVirtualModelInstanceRepository().getRootFolder(), null, _editor);
		action.setNewVirtualModelInstanceName("Alice");
		action.setNewVirtualModelInstanceTitle("Test creation of a new VMI");
		action.setVirtualModel(stateChartEditorVM);
		action.setCreationScheme(stateChartEditorVM.getCreationSchemes().get(0));
		action.doAction();
		assertTrue(action.hasActionExecutionSucceeded());
		alice = action.getNewVirtualModelInstance();
		assertNotNull(alice);

		OBP2Analysis obp2Analysis = alice.execute("modelChecking");
		assertNotNull(obp2Analysis);
		obp2Resource = (OBP2AnalysisResource) obp2Analysis.getResource();
		obp2Resource.save();
		System.out.println("obp2Resource=" + obp2Resource);
		System.out.println("obp2Resource.getURI()=" + obp2Resource.getURI());
		for (FlexoResource<?> flexoResource : obp2Resource.getContents()) {
			System.out.println(" > " + flexoResource.getURI() + " : " + flexoResource);
		}

		System.out.println("VirtualModel=" + obp2Analysis.getVirtualModel());
		assertNotNull(obp2Analysis.getVirtualModel());

	}

	@Test
	@TestOrder(3)
	public void testReloadProject() throws ResourceLoadingCancelledException, FlexoException, IOException, TypeMismatchException,
			NullReferenceException, InvocationTargetException, InvalidBindingException {

		instanciateTestServiceManager(OBP2TechnologyAdapter.class, DiagramTechnologyAdapter.class);

		// serviceManager.getResourceCenterService().addToResourceCenters(newResourceCenter = DirectoryResourceCenter
		// .instanciateNewDirectoryResourceCenter(newResourceCenter.getRootDirectory(), serviceManager.getResourceCenterService()));
		// newResourceCenter.performDirectoryWatchingNow();

		_editor = loadProject(_project.getProjectDirectory());
		_project = (FlexoProject<File>) _editor.getProject();
		assertNotNull(_editor);
		assertNotNull(_project);
		FMLRTVirtualModelInstanceResource newAliceResource = _project.getVirtualModelInstanceRepository()
				.getVirtualModelInstance(alice.getURI());
		assertNotNull(newAliceResource);

		System.out.println("VMI in " + newAliceResource.getIODelegate().getSerializationArtefact());

		OBP2Analysis obp2Analysis = alice.execute("modelChecking");
		assertNotNull(obp2Analysis);
		obp2Resource = (OBP2AnalysisResource) obp2Analysis.getResource();
		System.out.println("obp2Resource=" + obp2Resource);
		System.out.println("obp2Resource.getURI()=" + obp2Resource.getURI());
		for (FlexoResource<?> flexoResource : obp2Resource.getContents()) {
			System.out.println(" > " + flexoResource.getURI() + " : " + flexoResource);
		}

		System.out.println("VirtualModel=" + obp2Analysis.getVirtualModel());
		assertNotNull(obp2Analysis.getVirtualModel());

	}

}
