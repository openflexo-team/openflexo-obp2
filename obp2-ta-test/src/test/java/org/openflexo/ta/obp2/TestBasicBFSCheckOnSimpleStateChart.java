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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openflexo.connie.exception.InvalidBindingException;
import org.openflexo.connie.exception.NullReferenceException;
import org.openflexo.connie.exception.TypeMismatchException;
import org.openflexo.foundation.FlexoEditor;
import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.FlexoProject;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.rm.VirtualModelResource;
import org.openflexo.foundation.fml.rt.FMLRTVirtualModelInstance;
import org.openflexo.foundation.fml.rt.rm.FMLRTVirtualModelInstanceResource;
import org.openflexo.foundation.project.FlexoProjectResource;
import org.openflexo.foundation.resource.FlexoResource;
import org.openflexo.foundation.resource.FlexoResourceCenter;
import org.openflexo.foundation.resource.FlexoResourceCenterService;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.foundation.test.OpenflexoProjectAtRunTimeTestCase;
import org.openflexo.pamela.exceptions.ModelDefinitionException;
import org.openflexo.technologyadapter.diagram.DiagramTechnologyAdapter;
import org.openflexo.test.OrderedRunner;
import org.openflexo.test.TestOrder;

/**
 * This unit test is intented to test OBP2Analysis using a {@link OBP2ModelSlot}
 * 
 * @author sylvain
 * 
 */
@RunWith(OrderedRunner.class)
public class TestBasicBFSCheckOnSimpleStateChart extends OpenflexoProjectAtRunTimeTestCase {

	public static final String PROJECT_URI = "http://www.openflexo.org/obp2/test/TestBasicCheckOnSimpleStateChart.prj";

	private static FlexoEditor editor;
	private static FMLRTVirtualModelInstance alice;
	private static FMLRTVirtualModelInstance aliceSemantics;
	private static FMLRTVirtualModelInstance aliceStateChartsLibrary;
	private static VirtualModel aliceExecutionModel;

	@Test
	@TestOrder(1)
	public void testLoadProject() throws ModelDefinitionException, IOException, ResourceLoadingCancelledException, FlexoException {
		instanciateTestServiceManager(OBP2TechnologyAdapter.class, DiagramTechnologyAdapter.class);
		System.out.println("SM=" + serviceManager);
		FlexoResourceCenterService rcService = serviceManager.getResourceCenterService();
		for (FlexoResourceCenter<?> resourceCenter : rcService.getResourceCenters()) {
			System.out.println("> resourceCenter " + resourceCenter + " uri=" + resourceCenter.getDefaultBaseURI());
			for (FlexoResource<?> resource : resourceCenter.getAllResources()) {
				System.out.println(" > " + resource.getURI());
			}
		}

		FlexoProjectResource<?> projectResource = (FlexoProjectResource) serviceManager.getResourceManager().getResource(PROJECT_URI);

		System.out.println("projectResource=" + projectResource);

		File projectDirectory = ((File) projectResource.getIODelegate().getSerializationArtefact()).getParentFile();
		System.out.println("projectDirectory=" + projectDirectory);

		editor = loadProject(projectDirectory);
		FlexoProject<?> project = editor.getProject();

		System.out.println("Toutes les resources du projet:");
		for (FlexoResource<?> resource : project.getAllResources()) {
			System.out.println(" > " + resource.getURI());
		}

		FMLRTVirtualModelInstanceResource aliceResource = (FMLRTVirtualModelInstanceResource) project
				.getResource(PROJECT_URI + "/Alice.fml.rt");
		assertNotNull(aliceResource);

		FMLRTVirtualModelInstanceResource aliceSemanticsResource = (FMLRTVirtualModelInstanceResource) project
				.getResource(PROJECT_URI + "/AliceSemantics.fml.rt");
		assertNotNull(aliceSemanticsResource);

		FMLRTVirtualModelInstanceResource aliceSCLibraryResource = (FMLRTVirtualModelInstanceResource) project
				.getResource(PROJECT_URI + "/AliceStateChartsLibrary.fml.rt");
		assertNotNull(aliceSCLibraryResource);

		VirtualModelResource aliceExecutionModelResource = (VirtualModelResource) project
				.getResource(PROJECT_URI + "/AliceExecutionModel.fml");
		assertNotNull(aliceExecutionModelResource);

		alice = aliceResource.getResourceData();
		aliceSemantics = aliceSemanticsResource.getResourceData();
		aliceStateChartsLibrary = aliceSCLibraryResource.getResourceData();
		aliceExecutionModel = aliceExecutionModelResource.getResourceData();

	}

	@Test
	@TestOrder(2)
	public void testExecuteBFS() throws TypeMismatchException, NullReferenceException, InvocationTargetException, InvalidBindingException {
		alice.execute("this.performCheck()");
	}

	/*public static final String VIEWPOINT_NAME = "TestViewPoint";
	public static final String VIEWPOINT_URI = "http://openflexo.org/test/TestViewPoint";
	public static final String VIRTUAL_MODEL_NAME = "TestVirtualModel";
	
	private static VirtualModelResource newViewPointResource;
	private static VirtualModel newViewPoint;
	private static VirtualModel newVirtualModel;
	private static FlexoEditor editor;
	private static FlexoProject<File> project;
	private static OBP2Analysis newView;
	private static OBP2Analysis newVirtualModelInstance;
	
	private static DirectoryResourceCenter newResourceCenter;
	
	@Test
	@TestOrder(1)
	public void testCreateViewPoint() throws SaveResourceException, ModelDefinitionException, IOException {
		instanciateTestServiceManager(DiagramTechnologyAdapter.class);
	
		FMLTechnologyAdapter fmlTechnologyAdapter = serviceManager.getTechnologyAdapterService()
				.getTechnologyAdapter(FMLTechnologyAdapter.class);
		VirtualModelResourceFactory factory = fmlTechnologyAdapter.getVirtualModelResourceFactory();
	
		newResourceCenter = makeNewDirectoryResourceCenter(serviceManager);
	
		newViewPointResource = factory.makeTopLevelVirtualModelResource(VIEWPOINT_NAME, VIEWPOINT_URI,
				fmlTechnologyAdapter.getGlobalRepository(newResourceCenter).getRootFolder(), true);
		newViewPoint = newViewPointResource.getLoadedResourceData();
	
		// newViewPoint = ViewPointImpl.newViewPoint("TestViewPoint",
		// "http://openflexo.org/test/TestViewPoint",
		// resourceCenter.getDirectory(),
		// serviceManager.getViewPointLibrary(), resourceCenter);
		assertNotNull(newViewPoint);
		assertNotNull(newViewPoint.getResource());
		// assertTrue(((ViewPointResource)
		// newViewPoint.getResource()).getDirectory().exists());
		// assertTrue(((ViewPointResource)
		// newViewPoint.getResource()).getFile().exists());
		assertTrue(((VirtualModelResource) newViewPoint.getResource()).getDirectory() != null);
		assertTrue(((VirtualModelResource) newViewPoint.getResource()).getIODelegate().exists());
		CreateContainedVirtualModel action = CreateContainedVirtualModel.actionType.makeNewAction(newViewPoint, null, editor);
		action.setNewVirtualModelName("TestVirtualModel");
		action.doAction();
		assertTrue(action.hasActionExecutionSucceeded());
	}
	
	@Test
	@TestOrder(2)
	public void testCreateVirtualModel() throws SaveResourceException {
		CreateContainedVirtualModel action = CreateContainedVirtualModel.actionType.makeNewAction(newViewPoint, null, editor);
		action.setNewVirtualModelName(VIRTUAL_MODEL_NAME);
		action.doAction();
		assertTrue(action.hasActionExecutionSucceeded());
		newVirtualModel = action.getNewVirtualModel();
		// newVirtualModel =
		// VirtualModelImpl.newVirtualModel("TestVirtualModel", newViewPoint);
		// assertTrue(((VirtualModelResource)
		// newVirtualModel.getResource()).getDirectory().exists());
		// assertTrue(((VirtualModelResource)
		// newVirtualModel.getResource()).getFile().exists());
		assertTrue(ResourceLocator.retrieveResourceAsFile(((VirtualModelResource) newVirtualModel.getResource()).getDirectory()).exists());
		assertTrue(((VirtualModelResource) newVirtualModel.getResource()).getIODelegate().exists());
	
		// Now we create the diagram model slot
		CreateModelSlot createMS = CreateModelSlot.actionType.makeNewAction(newVirtualModel, null, editor);
		createMS.setTechnologyAdapter(serviceManager.getTechnologyAdapterService().getTechnologyAdapter(DiagramTechnologyAdapter.class));
		createMS.setModelSlotClass(TypedDiagramModelSlot.class);
		createMS.setModelSlotName("diagram");
		createMS.doAction();
	
		assertTrue(createMS.hasActionExecutionSucceeded());
	
		// VirtualModel should have only one TypedDiagramModelSlot
		assertEquals(1, newVirtualModel.getModelSlots(TypedDiagramModelSlot.class).size());
	
		TypedDiagramModelSlot diagramModelSlot = newVirtualModel.getModelSlots(TypedDiagramModelSlot.class).get(0);
		assertNotNull(diagramModelSlot);
	
		// We create a CreationScheme
		CreateFlexoBehaviour createCreationScheme = CreateFlexoBehaviour.actionType.makeNewAction(newVirtualModel, null, editor);
		createCreationScheme.setFlexoBehaviourName("create");
		createCreationScheme.setFlexoBehaviourClass(CreationScheme.class);
		createCreationScheme.doAction();
		assertTrue(createCreationScheme.hasActionExecutionSucceeded());
		CreationScheme creationScheme = (CreationScheme) createCreationScheme.getNewFlexoBehaviour();
	
		CreateEditionAction createDiagramAction = CreateEditionAction.actionType.makeNewAction(creationScheme.getControlGraph(), null,
				_editor);
		createDiagramAction.setEditionActionClass(CreateDiagram.class);
		createDiagramAction.setAssignation(new DataBinding<>("diagram"));
		createDiagramAction.doAction();
		assertTrue(createDiagramAction.hasActionExecutionSucceeded());
	
		CreateDiagram createDiagram = (CreateDiagram) createDiagramAction.getBaseEditionAction();
		createDiagram.setDiagramName(new DataBinding<>("'TestDiagram'"));
		createDiagram.setResourceCenter(new DataBinding<>("this.resourceCenter"));
	
		System.out.println("FML: " + newVirtualModel.getFMLRepresentation());
	}
	
	@Test
	@TestOrder(3)
	public void testCreateProject() {
		editor = createStandaloneProject("TestProject");
		project = (FlexoProject<File>) editor.getProject();
		System.out.println("Created project " + project.getProjectDirectory());
		assertTrue(project.getProjectDirectory().exists());
	}
	
	@Test
	@TestOrder(4)
	public void testCreateView() {
		CreateBasicOBP2Analysis action = CreateBasicOBP2Analysis.actionType
				.makeNewAction(project.getVirtualModelInstanceRepository().getRootFolder(), null, editor);
		action.setNewVirtualModelInstanceName("MyView");
		action.setNewVirtualModelInstanceTitle("Test creation of a new view");
		action.setVirtualModel(newViewPoint);
		action.doAction();
		assertTrue(action.hasActionExecutionSucceeded());
		newView = action.getNewVirtualModelInstance();
		assertNotNull(newView);
		assertNotNull(newView.getResource());
		try {
			newView.getResource().save();
		} catch (SaveResourceException e) {
			e.printStackTrace();
		}
		// assertTrue(((ViewResource)
		// newView.getResource()).getDirectory().exists());
		// assertTrue(((ViewResource)
		// newView.getResource()).getFile().exists());
		assertTrue(
				ResourceLocator.retrieveResourceAsFile(((OBP2AnalysisResource) newView.getResource()).getDirectory()) != null);
		assertTrue(((OBP2AnalysisResource) newView.getResource()).getIODelegate().exists());
	}
	
	@Test
	@TestOrder(5)
	public void testCreateVirtualModelInstance() {
		CreateBasicOBP2Analysis action = CreateBasicOBP2Analysis.actionType.makeNewAction(newView, null, editor);
		action.setNewVirtualModelInstanceName("MyVirtualModelInstance");
		action.setNewVirtualModelInstanceTitle("Test creation of a new OBP2Analysis");
		action.setVirtualModel(newVirtualModel);
		action.setCreationScheme(newVirtualModel.getCreationSchemes().get(0));
	
		action.doAction();
		assertTrue(action.hasActionExecutionSucceeded());
		newVirtualModelInstance = action.getNewVirtualModelInstance();
		assertNotNull(newVirtualModelInstance);
		assertNotNull(newVirtualModelInstance.getResource());
		assertTrue(
				ResourceLocator.retrieveResourceAsFile(((OBP2AnalysisResource) newView.getResource()).getDirectory()) != null);
		assertTrue(((OBP2AnalysisResource) newView.getResource()).getIODelegate().exists());
	
		assertEquals(1, newVirtualModelInstance.getModelSlotInstances().size());
	
		TypeAwareModelSlotInstance diagramMSInstance = (TypeAwareModelSlotInstance) newVirtualModelInstance
				.getModelSlotInstance(newVirtualModel.getModelSlots(TypedDiagramModelSlot.class).get(0));
		assertNotNull(diagramMSInstance);
	
	}
	
	@Test
	@TestOrder(6)
	public void testReloadProject() throws ResourceLoadingCancelledException, FlexoException, IOException {
	
		instanciateTestServiceManager(DiagramTechnologyAdapter.class);
	
		serviceManager.getResourceCenterService().addToResourceCenters(newResourceCenter = DirectoryResourceCenter
				.instanciateNewDirectoryResourceCenter(newResourceCenter.getRootDirectory(), serviceManager.getResourceCenterService()));
		newResourceCenter.performDirectoryWatchingNow();
	
		editor = loadProject(project.getProjectDirectory());
		project = (FlexoProject<File>) editor.getProject();
		assertNotNull(editor);
		assertNotNull(project);
		OBP2AnalysisResource newViewResource = project.getVirtualModelInstanceRepository()
				.getVirtualModelInstance(newView.getURI());
		assertNotNull(newViewResource);
	
		System.out.println("view in " + newViewResource.getIODelegate().getSerializationArtefact());
	
		VirtualModelRepository<?> vpRep = newResourceCenter.getVirtualModelRepository();
		for (VirtualModelResource r : vpRep.getAllResources()) {
			System.out.println("> " + r.getURI());
		}
	
		assertNull(newViewResource.getLoadedResourceData());
	
		newViewResource.loadResourceData();
		assertNotNull(newViewResource.getLoadedResourceData());
		newView = newViewResource.getLoadedResourceData();
	}*/

}
