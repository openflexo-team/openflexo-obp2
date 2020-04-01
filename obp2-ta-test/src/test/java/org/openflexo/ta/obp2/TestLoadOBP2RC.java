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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.rm.VirtualModelResource;
import org.openflexo.foundation.resource.FlexoResource;
import org.openflexo.foundation.resource.FlexoResourceCenter;
import org.openflexo.foundation.resource.FlexoResourceCenterService;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.foundation.test.OpenflexoTestCase;
import org.openflexo.pamela.exceptions.ModelDefinitionException;
import org.openflexo.pamela.validation.ValidationReport;
import org.openflexo.technologyadapter.diagram.DiagramTechnologyAdapter;
import org.openflexo.test.OrderedRunner;
import org.openflexo.test.TestOrder;

/**
 * This unit test is intented to test loading issues with {@link VirtualModel} with cross-references
 * 
 * @author sylvain
 * 
 */
@RunWith(OrderedRunner.class)
public class TestLoadOBP2RC extends OpenflexoTestCase {

	private static FlexoResourceCenter<?> modellerRC;
	private static FlexoResourceCenter<?> obp2RC;

	private static VirtualModelResource stateChartsVMResource;
	private static VirtualModelResource stateChartsModelVMResource;
	private static VirtualModelResource stateChartsDiagramVMResource;

	private static VirtualModelResource iTransitionRelationVMResource;
	private static VirtualModelResource obp2AnalysisVMResource;
	private static VirtualModelResource abstractAnalysisVMResource;
	private static VirtualModelResource bfsAnalysisVMResource;
	private static VirtualModelResource coreSemanticsVMResource;
	private static VirtualModelResource coreExecutionModelVMResource;
	private static VirtualModelResource stateChartsExecutionModelVMResource;
	private static VirtualModelResource stateChartsSemanticsVMResource;
	private static VirtualModelResource stateChartEditorVMResource;
	private static VirtualModelResource executableStateChartVMResource;

	@Test
	@TestOrder(1)
	public void testInstantiateServiceManager()
			throws ModelDefinitionException, IOException, ResourceLoadingCancelledException, FlexoException {

		instanciateTestServiceManager(OBP2TechnologyAdapter.class, DiagramTechnologyAdapter.class);
		System.out.println("SM=" + serviceManager);
		FlexoResourceCenterService rcService = serviceManager.getResourceCenterService();

		modellerRC = rcService.getFlexoResourceCenter("http://openflexo.org/modellers");
		assertNotNull(modellerRC);
		System.out.println("Found modellerRC");
		for (FlexoResource<?> resource : modellerRC.getAllResources()) {
			System.out.println(" > " + (resource.isLoaded() ? "*" : " ") + " " + resource.getURI());
		}

		stateChartsVMResource = (VirtualModelResource) modellerRC.getResource("http://openflexo.org/modellers/StateCharts.fml");
		assertNotNull(stateChartsVMResource);
		assertFalse(stateChartsVMResource.isLoaded());

		stateChartsModelVMResource = (VirtualModelResource) modellerRC
				.getResource("http://openflexo.org/modellers/StateCharts.fml/StateChartModel.fml");
		assertNotNull(stateChartsModelVMResource);
		assertFalse(stateChartsModelVMResource.isLoaded());

		stateChartsDiagramVMResource = (VirtualModelResource) modellerRC
				.getResource("http://openflexo.org/modellers/StateCharts.fml/StateChartDiagram.fml");
		assertNotNull(stateChartsDiagramVMResource);
		assertFalse(stateChartsDiagramVMResource.isLoaded());

		obp2RC = rcService.getFlexoResourceCenter("http://openflexo.org/obp2");
		assertNotNull(obp2RC);
		System.out.println("Found obp2RC");
		for (FlexoResource<?> resource : obp2RC.getAllResources()) {
			System.out.println(" > " + (resource.isLoaded() ? "*" : " ") + " " + resource.getURI());
		}

		iTransitionRelationVMResource = (VirtualModelResource) obp2RC.getResource("http://openflexo.org/obp2/ITransitionRelation.fml");
		assertNotNull(iTransitionRelationVMResource);
		assertFalse(iTransitionRelationVMResource.isLoaded());

		obp2AnalysisVMResource = (VirtualModelResource) obp2RC.getResource("http://openflexo.org/obp2/API/OBP2AnalysisVirtualModel.fml");
		assertNotNull(obp2AnalysisVMResource);
		assertFalse(obp2AnalysisVMResource.isLoaded());

		abstractAnalysisVMResource = (VirtualModelResource) obp2RC
				.getResource("http://openflexo.org/obp2/API/OBP2AnalysisVirtualModel.fml/AbstractAnalysis.fml");
		assertNotNull(abstractAnalysisVMResource);
		assertFalse(abstractAnalysisVMResource.isLoaded());

		bfsAnalysisVMResource = (VirtualModelResource) obp2RC
				.getResource("http://openflexo.org/obp2/API/OBP2AnalysisVirtualModel.fml/BFSAnalysis.fml");
		assertNotNull(bfsAnalysisVMResource);
		assertFalse(bfsAnalysisVMResource.isLoaded());

		coreSemanticsVMResource = (VirtualModelResource) obp2RC.getResource("http://openflexo.org/obp2/Semantics/Core/CoreSemantics.fml");
		assertNotNull(coreSemanticsVMResource);
		assertFalse(coreSemanticsVMResource.isLoaded());

		coreExecutionModelVMResource = (VirtualModelResource) obp2RC
				.getResource("http://openflexo.org/obp2/Semantics/Core/CoreExecutionModel.fml");
		assertNotNull(coreExecutionModelVMResource);
		assertFalse(coreExecutionModelVMResource.isLoaded());

		stateChartsExecutionModelVMResource = (VirtualModelResource) obp2RC
				.getResource("http://openflexo.org/obp2/resources/Semantics/StateCharts/StateChartsExecutionModel.fml");
		assertNotNull(stateChartsExecutionModelVMResource);
		assertFalse(stateChartsExecutionModelVMResource.isLoaded());

		stateChartsSemanticsVMResource = (VirtualModelResource) obp2RC
				.getResource("http://openflexo.org/obp2/Semantics/StateCharts/StateChartsSemantics.fml");
		assertNotNull(stateChartsSemanticsVMResource);
		assertFalse(stateChartsSemanticsVMResource.isLoaded());

		stateChartEditorVMResource = (VirtualModelResource) obp2RC.getResource("http://openflexo.org/obp2/Tools/StateChartsEditor.fml");
		assertNotNull(stateChartEditorVMResource);
		assertFalse(stateChartEditorVMResource.isLoaded());

		executableStateChartVMResource = (VirtualModelResource) obp2RC
				.getResource("http://openflexo.org/obp2/Tools/StateChartsEditor.fml/ExecutableStateChart.fml");
		assertNotNull(executableStateChartVMResource);
		assertFalse(executableStateChartVMResource.isLoaded());

	}

	@Test
	@TestOrder(2)
	public void testLoadStateChartsExecutionModel() throws FileNotFoundException, ResourceLoadingCancelledException, FlexoException {

		stateChartsExecutionModelVMResource.loadResourceData();

		System.out.println("Found modellerRC");
		for (FlexoResource<?> resource : modellerRC.getAllResources()) {
			int errors = 0;
			if (resource.isLoaded()) {
				if (resource.getResourceData() instanceof VirtualModel) {
					ValidationReport report = validate((VirtualModel) resource.getResourceData());
					errors = report.getErrorsCount();
				}
			}
			System.out.println(
					" > " + (resource.isLoaded() ? "*" + (errors == 0 ? "OK " : errors) + " " : "       ") + " " + resource.getURI());
		}

		System.out.println("Found obp2RC");
		for (FlexoResource<?> resource : obp2RC.getAllResources()) {
			int errors = 0;
			if (resource.isLoaded()) {
				if (resource.getResourceData() instanceof VirtualModel) {
					ValidationReport report = validate((VirtualModel) resource.getResourceData());
					errors = report.getErrorsCount();
				}
			}
			System.out.println(
					" > " + (resource.isLoaded() ? "* " + (errors == 0 ? "OK " : errors) + " " : "       ") + " " + resource.getURI());
		}

	}
}
