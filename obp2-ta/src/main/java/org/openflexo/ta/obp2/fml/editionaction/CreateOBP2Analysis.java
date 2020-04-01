/*
 * Copyright (c) 2013-2017, Openflexo
 *
 * This file is part of Flexo-foundation, a component of the software infrastructure
 * developed at Openflexo.
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
 *           Additional permission under GNU GPL version 3 section 7
 *           If you modify this Program, or any covered work, by linking or
 *           combining it with software containing parts covered by the terms
 *           of EPL 1.0, the licensors of this Program grant you additional permission
 *           to convey the resulting work.
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

package org.openflexo.ta.obp2.fml.editionaction;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;

import org.openflexo.foundation.FlexoException;
import org.openflexo.foundation.fml.FlexoProperty;
import org.openflexo.foundation.fml.VirtualModel;
import org.openflexo.foundation.fml.annotations.FML;
import org.openflexo.foundation.fml.editionaction.AbstractCreateResource;
import org.openflexo.foundation.fml.editionaction.EditionAction;
import org.openflexo.foundation.fml.rt.RunTimeEvaluationContext;
import org.openflexo.foundation.fml.rt.action.CreationSchemeAction;
import org.openflexo.foundation.fml.rt.action.FlexoBehaviourAction;
import org.openflexo.foundation.resource.ResourceLoadingCancelledException;
import org.openflexo.pamela.annotations.ImplementationClass;
import org.openflexo.pamela.annotations.ModelEntity;
import org.openflexo.pamela.annotations.XMLElement;
import org.openflexo.pamela.exceptions.ModelDefinitionException;
import org.openflexo.ta.obp2.OBP2ModelSlot;
import org.openflexo.ta.obp2.OBP2TechnologyAdapter;
import org.openflexo.ta.obp2.model.OBP2Analysis;
import org.openflexo.ta.obp2.rm.OBP2AnalysisResource;
import org.openflexo.ta.obp2.rm.OBP2AnalysisResourceFactory;

/**
 * {@link EditionAction} used to create an empty {@link OBP2Analysis} resource
 * 
 * @author sylvain
 *
 */
@ModelEntity
@ImplementationClass(CreateOBP2Analysis.CreateOBP2AnalysisImpl.class)
@XMLElement
@FML("CreateOBP2Analysis")
public interface CreateOBP2Analysis extends AbstractCreateResource<OBP2ModelSlot, OBP2Analysis, OBP2TechnologyAdapter> {

	/*@PropertyIdentifier(type = String.class)
	public static final String CREATION_SCHEME_URI_KEY = "creationSchemeURI";
	@PropertyIdentifier(type = CreationScheme.class)
	public static final String CREATION_SCHEME_KEY = "creationScheme";
	@PropertyIdentifier(type = VirtualModel.class)
	public static final String VIRTUAL_MODEL_KEY = "virtualModel";
	@PropertyIdentifier(type = CreateSEResourceParameter.class, cardinality = Cardinality.LIST)
	public static final String PARAMETERS_KEY = "parameters";
	
	@PropertyIdentifier(type = DataBinding.class)
	String EXCEL_WORKBOOK = "excelWorkbook";
	
	public VirtualModelResource getVirtualModelResource();
	
	public void setVirtualModelResource(VirtualModelResource virtualModelResource);
	
	public VirtualModel getVirtualModel();
	
	public void setVirtualModel(VirtualModel virtualModel);
	
	@Getter(EXCEL_WORKBOOK)
	@XMLAttribute
	DataBinding<ExcelWorkbook> getExcelWorkbook();
	
	@Setter(EXCEL_WORKBOOK)
	void setExcelWorkbook(DataBinding<ExcelWorkbook> aConnection);
	
	@Getter(value = CREATION_SCHEME_URI_KEY)
	@XMLAttribute
	public String _getCreationSchemeURI();
	
	@Setter(CREATION_SCHEME_URI_KEY)
	public void _setCreationSchemeURI(String creationSchemeURI);
	
	public CreationScheme getCreationScheme();
	
	public void setCreationScheme(CreationScheme creationScheme);
	
	public List<CreationScheme> getAvailableCreationSchemes();
	
	@Getter(value = PARAMETERS_KEY, cardinality = Cardinality.LIST, inverse = CreateSEResourceParameter.OWNER_KEY)
	@XMLElement
	@Embedded
	@CloningStrategy(StrategyType.CLONE)
	public List<CreateSEResourceParameter> getParameters();
	
	@Setter(PARAMETERS_KEY)
	public void setParameters(List<CreateSEResourceParameter> parameters);
	
	@Adder(PARAMETERS_KEY)
	public void addToParameters(CreateSEResourceParameter aParameter);
	
	@Remover(PARAMETERS_KEY)
	public void removeFromParameters(CreateSEResourceParameter aParameter);
	
	*/

	public abstract Class<OBP2AnalysisResourceFactory> getResourceFactoryClass();

	public abstract String getSuffix();

	abstract class CreateOBP2AnalysisImpl extends AbstractCreateResourceImpl<OBP2ModelSlot, OBP2Analysis, OBP2TechnologyAdapter>
			implements CreateOBP2Analysis {

		/*private DataBinding<ExcelWorkbook> excelWorkbook;
		
		private VirtualModel virtualModel;
		private VirtualModelResource virtualModelResource;
		
		private CreationScheme creationScheme;
		private String _creationSchemeURI;
		private List<CreateSEResourceParameter> parameters = null;*/

		@Override
		public Type getAssignableType() {
			/*if (getVirtualModel() != null) {
				return getVirtualModel().getInstanceType();
			}*/
			return OBP2Analysis.class;
		}

		/*@Override
		public VirtualModelResource getVirtualModelResource() {
			if (virtualModelResource != null) {
				return virtualModelResource;
			}
			if (getVirtualModel() != null) {
				return getVirtualModel().getVirtualModelResource();
			}
			return virtualModelResource;
		}
		
		@Override
		public void setVirtualModelResource(VirtualModelResource virtualModelResource) {
			if ((virtualModelResource == null && getVirtualModelResource() != null)
					|| (virtualModelResource != null && !virtualModelResource.equals(getVirtualModelResource()))) {
				VirtualModelResource oldValue = getVirtualModelResource();
				this.virtualModelResource = virtualModelResource;
				setVirtualModel(virtualModelResource != null ? virtualModelResource.getVirtualModel() : null);
				getPropertyChangeSupport().firePropertyChange("virtualModelResource", oldValue, virtualModelResource);
			}
		}
		
		@Override
		public VirtualModel getVirtualModel() {
			if (getCreationScheme() != null) {
				return (VirtualModel) getCreationScheme().getFlexoConcept();
			}
			if (getAssignedFlexoProperty() instanceof SemanticsExcelModelSlot) {
				return ((SemanticsExcelModelSlot) getAssignedFlexoProperty()).getAccessedVirtualModel();
			}
			if (virtualModelResource != null) {
				return virtualModelResource.getVirtualModel();
			}
			return virtualModel;
		}
		
		@Override
		public void setVirtualModel(VirtualModel aVirtualModel) {
			if (this.virtualModel != aVirtualModel) {
				VirtualModel oldValue = this.virtualModel;
				this.virtualModel = aVirtualModel;
				if (aVirtualModel != null) {
					this.virtualModelResource = aVirtualModel.getVirtualModelResource();
					getPropertyChangeSupport().firePropertyChange("availableCreationSchemes", null, aVirtualModel.getCreationSchemes());
				}
				if (creationScheme == null || creationScheme.getFlexoConcept() != aVirtualModel) {
					if (aVirtualModel.getCreationSchemes().size() > 0) {
						setCreationScheme(aVirtualModel.getCreationSchemes().get(0));
					}
					else {
						setCreationScheme(null);
					}
				}
				getPropertyChangeSupport().firePropertyChange(CreateOBP2Analysis.VIRTUAL_MODEL_KEY, oldValue, aVirtualModel);
				getPropertyChangeSupport().firePropertyChange("availableCreationSchemes", null, getAvailableCreationSchemes());
			}
		}
		
		@Override
		public String _getCreationSchemeURI() {
			if (getCreationScheme() != null) {
				return getCreationScheme().getURI();
			}
			return _creationSchemeURI;
		}
		
		@Override
		public void _setCreationSchemeURI(String uri) {
			if (getVirtualModelLibrary() != null) {
				creationScheme = (CreationScheme) getVirtualModelLibrary().getFlexoBehaviour(uri, true);
			}
			_creationSchemeURI = uri;
		}
		
		@Override
		public CreationScheme getCreationScheme() {
		
			if (creationScheme == null && _creationSchemeURI != null && getVirtualModelLibrary() != null) {
				creationScheme = (CreationScheme) getVirtualModelLibrary().getFlexoBehaviour(_creationSchemeURI, true);
				updateParameters();
			}
			if (creationScheme == null && ((FlexoProperty<?>) getAssignedFlexoProperty()) instanceof FlexoConceptInstanceRole) {
				creationScheme = ((FlexoConceptInstanceRole) (FlexoProperty<?>) getAssignedFlexoProperty()).getCreationScheme();
				updateParameters();
			}
			return creationScheme;
		}
		
		@Override
		public void setCreationScheme(CreationScheme creationScheme) {
			if (this.creationScheme != creationScheme) {
				CreationScheme oldValue = this.creationScheme;
				this.creationScheme = creationScheme;
				if (creationScheme != null) {
					_creationSchemeURI = creationScheme.getURI();
				}
				else {
					_creationSchemeURI = null;
				}
				updateParameters();
				getPropertyChangeSupport().firePropertyChange(CREATION_SCHEME_KEY, oldValue, creationScheme);
				getPropertyChangeSupport().firePropertyChange(VIRTUAL_MODEL_KEY, null, getVirtualModel());
			}
		}
		
		@Override
		public List<CreationScheme> getAvailableCreationSchemes() {
			if (getVirtualModel() != null) {
				return getVirtualModel().getCreationSchemes();
			}
			return null;
		}
		
		@Override
		public List<CreateSEResourceParameter> getParameters() {
			// Comment this because of an infinite loop with updateParameters() method
			if (parameters == null) {
				parameters = new ArrayList<>();
				updateParameters();
			}
			return parameters;
		}
		
		@Override
		public void setParameters(List<CreateSEResourceParameter> parameters) {
			this.parameters = parameters;
		}
		
		@Override
		public void addToParameters(CreateSEResourceParameter parameter) {
			parameter.setOwner(this);
			if (parameters == null) {
				parameters = new ArrayList<>();
			}
			parameters.add(parameter);
		}
		
		@Override
		@Override
		public void removeFromParameters(CreateSEResourceParameter parameter) {
			parameter.setOwner(null);
			if (parameters == null) {
				parameters = new ArrayList<>();
			}
			parameters.remove(parameter);
		}
		
		public CreateSEResourceParameter getParameter(FlexoBehaviourParameter p) {
			for (CreateSEResourceParameter addEPParam : getParameters()) {
				if (addEPParam.getParam() == p) {
					return addEPParam;
				}
			}
			return null;
		}
		
		private void updateParameters() {
			if (parameters == null) {
				parameters = new ArrayList<>();
			}
			List<CreateSEResourceParameter> oldValue = new ArrayList<>(parameters);
			List<CreateSEResourceParameter> parametersToRemove = new ArrayList<>(parameters);
			if (creationScheme != null) {
				for (FlexoBehaviourParameter p : creationScheme.getParameters()) {
					CreateSEResourceParameter existingParam = getParameter(p);
					if (existingParam != null) {
						parametersToRemove.remove(existingParam);
					}
					else {
						if (getFMLModelFactory() != null) {
							CreateSEResourceParameter newParam = getFMLModelFactory().newInstance(CreateSEResourceParameter.class);
							newParam.setParam(p);
							addToParameters(newParam);
						}
					}
				}
			}
			for (CreateSEResourceParameter removeThis : parametersToRemove) {
				removeFromParameters(removeThis);
			}
			getPropertyChangeSupport().firePropertyChange(PARAMETERS_KEY, oldValue, parameters);
		}*/

		@Override
		public OBP2Analysis execute(RunTimeEvaluationContext evaluationContext) throws FlexoException {

			/*if (getCreationScheme() == null) {
				throw new InvalidArgumentException("No creation scheme defined");
			}*/

			try {
				// String resourceName = getResourceName(evaluationContext);
				// String resourceURI = getResourceURI(evaluationContext);
				// FlexoResourceCenter<?> rc = getResourceCenter(evaluationContext);

				System.out.println("Creating OBP2AnalysisResource");

				OBP2AnalysisResource newResource = createResource(
						getServiceManager().getTechnologyAdapterService().getTechnologyAdapter(OBP2TechnologyAdapter.class),
						getResourceFactoryClass(), evaluationContext, getSuffix(), true);
				OBP2Analysis data = newResource.getResourceData();

				VirtualModel OBP2AnalysisVirtualModel = getVirtualModelLibrary()
						.getVirtualModel("http://openflexo.org/obp2/API/OBP2AnalysisVirtualModel.fml");

				System.out.println("Hop on sette bien le VM: " + OBP2AnalysisVirtualModel);

				data.setVirtualModel(OBP2AnalysisVirtualModel);

				CreationSchemeAction creationSchemeAction = new CreationSchemeAction(OBP2AnalysisVirtualModel.getCreationSchemes().get(0),
						null, null, (FlexoBehaviourAction<?, ?, ?>) evaluationContext);
				creationSchemeAction.initWithFlexoConceptInstance(data);
				creationSchemeAction.doAction();

				FlexoProperty<OBP2Analysis> flexoProperty = getAssignedFlexoProperty();
				if (flexoProperty instanceof OBP2ModelSlot) {
					// Unused SemanticsExcelModelSlot seModelSlot = (SemanticsExcelModelSlot) flexoProperty;
					// try {
					/*if (getExcelWorkbook().isValid()) {
						ExcelWorkbook excelWorkbook = getExcelWorkbook().getBindingValue(evaluationContext);
						data.setExcelWorkbookResource(excelWorkbook.getResource());
						System.out.println("Setting excelWorkbook: " + excelWorkbook);
					}
					else {
						throw new InvalidArgumentException("No valid connection while creating new SEResource");
					}*/

					/*} catch (TypeMismatchException | NullReferenceException | InvocationTargetException e) {
						e.printStackTrace();
					}*/

					// Now we should execute CreationScheme
					/*System.out.println("Executing FML: " + getCreationScheme().getFMLRepresentation());
					CreationSchemeAction creationSchemeAction = new CreationSchemeAction(getCreationScheme(), null, null,
							(FlexoBehaviourAction<?, ?, ?>) evaluationContext);
					creationSchemeAction.initWithFlexoConceptInstance(data);
					for (CreateSEResourceParameter p : getParameters()) {
						// Unused FlexoBehaviourParameter param = p.getParam();
						Object value = p.evaluateParameterValue((FlexoBehaviourAction<?, ?, ?>) evaluationContext);
						// System.out.println("For parameter " + param + " value is " + value);
						if (value != null) {
							creationSchemeAction.setParameterValue(p.getParam(),
									p.evaluateParameterValue((FlexoBehaviourAction<?, ?, ?>) evaluationContext));
						}
					}*/

					// creationSchemeAction.doAction();

					// data.updateData();

					/*if (data.getVirtualModel().getFlexoBehaviours(SEInitializer.class).size() > 0) {
						SEInitializer initializer = data.getVirtualModel().getFlexoBehaviours(SEInitializer.class).get(0);
						SEInitializerAction action = new SEInitializerAction(initializer, data, null,
								(FlexoBehaviourAction<?, ?, ?>) evaluationContext);
						action.doAction();
					}*/

				}
				/*else {
					throw new InvalidArgumentException("SEResource creation must be affected to a SEModelSlot");
				}*/

				return data;
			} catch (ModelDefinitionException | FileNotFoundException | ResourceLoadingCancelledException e) {
				throw new FlexoException(e);
			}

		}

		/*@Override
		public DataBinding<ExcelWorkbook> getExcelWorkbook() {
			if (excelWorkbook == null) {
				excelWorkbook = new DataBinding<>(this, ExcelWorkbook.class, DataBinding.BindingDefinitionType.GET);
				excelWorkbook.setBindingName("excelWorkbook");
			}
			return excelWorkbook;
		}
		
		@Override
		public void setExcelWorkbook(DataBinding<ExcelWorkbook> aWorkbook) {
			if (aWorkbook != null) {
				aWorkbook.setOwner(this);
				aWorkbook.setDeclaredType(ExcelWorkbook.class);
				aWorkbook.setBindingDefinitionType(DataBinding.BindingDefinitionType.GET);
				aWorkbook.setBindingName("excelWorkbook");
			}
			this.excelWorkbook = aWorkbook;
		}*/

		@Override
		public Class<OBP2AnalysisResourceFactory> getResourceFactoryClass() {
			return OBP2AnalysisResourceFactory.class;
		}

		@Override
		public String getSuffix() {
			return OBP2AnalysisResourceFactory.OBP2_SUFFIX;
		}

	}

}
