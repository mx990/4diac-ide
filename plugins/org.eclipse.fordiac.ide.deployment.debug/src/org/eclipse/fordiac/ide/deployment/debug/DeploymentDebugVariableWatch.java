/*******************************************************************************
 * Copyright (c) 2024 Martin Erich Jobst
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Martin Jobst - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.deployment.debug;

import java.text.MessageFormat;
import java.util.Objects;

import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugException;
import org.eclipse.fordiac.ide.deployment.devResponse.Data;
import org.eclipse.fordiac.ide.deployment.exceptions.DeploymentException;
import org.eclipse.fordiac.ide.deployment.interactors.IDeviceManagementExecutorService;
import org.eclipse.fordiac.ide.model.eval.EvaluatorException;
import org.eclipse.fordiac.ide.model.eval.variable.VariableOperations;
import org.eclipse.fordiac.ide.model.libraryElement.Resource;
import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration;

public class DeploymentDebugVariableWatch extends DeploymentDebugVariable implements IDeploymentDebugWatch {

	private final Resource resource;
	private final String resourceRelativeName;
	private final VarDeclaration varDeclaration;
	private long aliveCount;
	private boolean forced;

	public DeploymentDebugVariableWatch(final String name, final VarDeclaration varDeclaration,
			final DeploymentDebugDevice debugTarget) throws EvaluatorException {
		super(VariableOperations.newVariable(name, VariableOperations.evaluateResultType(varDeclaration)),
				varDeclaration.getQualifiedName(), debugTarget);
		resource = DeploymentDebugWatchUtils.getResource(varDeclaration);
		resourceRelativeName = DeploymentDebugWatchUtils.getResourceRelativeName(varDeclaration, resource);
		this.varDeclaration = varDeclaration;
	}

	@Override
	public void setValue(final String expression) throws DebugException {
		super.setValue(expression);
		writeWatch();
	}

	@Override
	protected void childValueChanged() throws DebugException {
		writeWatch();
	}

	@Override
	public void addWatch() throws DebugException {
		try {
			getDeviceManagementExecutorService().addWatch(resource, resourceRelativeName);
		} catch (final DeploymentException e) {
			throw new DebugException(Status.error(MessageFormat.format(Messages.DeploymentDebugVariableWatch_AddError,
					varDeclaration.getQualifiedName()), e));
		}
	}

	@Override
	public void removeWatch() throws DebugException {
		try {
			getDeviceManagementExecutorService().removeWatch(resource, resourceRelativeName);
		} catch (final DeploymentException e) {
			throw new DebugException(Status.error(MessageFormat
					.format(Messages.DeploymentDebugVariableWatch_RemoveError, varDeclaration.getQualifiedName()), e));
		}
	}

	protected void writeWatch() throws DebugException {
		try {
			getDeviceManagementExecutorService().writeFBParameter(resource, resourceRelativeName,
					getInternalVariable().toString(false));
		} catch (final DeploymentException e) {
			throw new DebugException(Status.error(MessageFormat.format(Messages.DeploymentDebugVariableWatch_WriteError,
					varDeclaration.getQualifiedName()), e));
		}
	}

	public void forceValue(final String value) throws DebugException {
		try {
			getDeviceManagementExecutorService().forceValue(resource, resourceRelativeName, value);
		} catch (final DeploymentException e) {
			throw new DebugException(Status.error(MessageFormat.format(Messages.DeploymentDebugVariableWatch_ForceError,
					varDeclaration.getQualifiedName()), e));
		}
	}

	public void clearForce() throws DebugException {
		try {
			getDeviceManagementExecutorService().clearForce(resource, resourceRelativeName);
		} catch (final DeploymentException e) {
			throw new DebugException(Status.error(MessageFormat.format(
					Messages.DeploymentDebugVariableWatch_ClearForceError, varDeclaration.getQualifiedName()), e));
		}
	}

	@Override
	public boolean isAlive() {
		return aliveCount == getDebugTarget().getVariableUpdateCount();
	}

	@Override
	public boolean hasValueChanged() {
		return false; // prevents annoying flickering in variables view
	}

	public boolean isForced() {
		return forced;
	}

	public void setForced(final boolean forced) {
		this.forced = forced;
	}

	@Override
	public void updateValue(final DeploymentDebugWatchData watchData) {
		final Data data = watchData.getLastData(resource, resourceRelativeName);
		if (data != null) {
			setForced(Boolean.parseBoolean(data.getForced()));
			updateValue(data.getValue());
		}
	}

	protected void updateValue(final String value) {
		try {
			getInternalVariable().setValue(value);
			aliveCount = getDebugTarget().getVariableUpdateCount();
		} catch (final Exception e) {
			// ignore (notice problem via stale alive count)
		}
	}

	@Override
	public Resource getResource() {
		return resource;
	}

	@Override
	public String getResourceRelativeName() {
		return resourceRelativeName;
	}

	@Override
	public VarDeclaration getWatchedElement() {
		return varDeclaration;
	}

	@Override
	public DeploymentDebugDevice getDebugTarget() {
		return (DeploymentDebugDevice) super.getDebugTarget();
	}

	protected IDeviceManagementExecutorService getDeviceManagementExecutorService() {
		return getDebugTarget().getDeviceManagementExecutorService();
	}

	@Override
	public int hashCode() {
		return getName().hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DeploymentDebugVariableWatch other = (DeploymentDebugVariableWatch) obj;
		return Objects.equals(getName(), other.getName());
	}
}
