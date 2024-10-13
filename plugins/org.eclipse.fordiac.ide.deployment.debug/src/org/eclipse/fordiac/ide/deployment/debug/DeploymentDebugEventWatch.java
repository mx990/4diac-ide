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
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.fordiac.ide.deployment.devResponse.Data;
import org.eclipse.fordiac.ide.deployment.exceptions.DeploymentException;
import org.eclipse.fordiac.ide.deployment.interactors.IDeviceManagementExecutorService;
import org.eclipse.fordiac.ide.model.libraryElement.Event;
import org.eclipse.fordiac.ide.model.libraryElement.Resource;

public class DeploymentDebugEventWatch extends DeploymentDebugElement implements IDeploymentDebugWatch {

	private final String name;
	private final Event event;
	private final Resource resource;
	private final String resourceRelativeName;
	private DeplymentDebugEventValue value;
	private long aliveCount;

	public DeploymentDebugEventWatch(final String name, final Event event, final DeploymentDebugDevice debugTarget) {
		super(debugTarget);
		this.name = name;
		this.event = event;
		resource = DeploymentDebugWatchUtils.getResource(event);
		resourceRelativeName = DeploymentDebugWatchUtils.getResourceRelativeName(event, resource);
		value = new DeplymentDebugEventValue(event, 0, debugTarget);
	}

	@Override
	public void addWatch() throws DebugException {
		try {
			getDeviceManagementExecutorService().addWatch(resource, resourceRelativeName);
		} catch (final DeploymentException e) {
			throw new DebugException(Status.error(
					MessageFormat.format(Messages.DeploymentDebugEventWatch_AddError, event.getQualifiedName()), e));
		}
	}

	@Override
	public void removeWatch() throws DebugException {
		try {
			getDeviceManagementExecutorService().removeWatch(resource, resourceRelativeName);
		} catch (final DeploymentException e) {
			throw new DebugException(Status.error(
					MessageFormat.format(Messages.DeploymentDebugEventWatch_RemoveError, event.getQualifiedName()), e));
		}
	}

	public void triggerEvent() throws DebugException {
		try {
			getDeviceManagementExecutorService().triggerEvent(resource, resourceRelativeName);
		} catch (final DeploymentException e) {
			throw new DebugException(Status.error(MessageFormat
					.format(Messages.DeploymentDebugEventWatch_TriggerEventError, event.getQualifiedName()), e));
		}
		fireChangeEvent(DebugEvent.CONTENT);
	}

	@Override
	public void updateValue(final DeploymentDebugWatchData watchData) {
		final Data data = watchData.getLastData(resource, resourceRelativeName);
		if (data != null) {
			updateValue(data.getValue());
		}
	}

	public void updateValue(final String value) {
		long newCount;
		try {
			newCount = Long.parseLong(value);
		} catch (final NumberFormatException e) {
			// ignore (notice problem via stale alive count)
			return;
		}
		aliveCount = getDebugTarget().getVariableUpdateCount();
		if (newCount != this.value.getCount()) {
			this.value = new DeplymentDebugEventValue(event, newCount, getDebugTarget());
		}
	}

	@Override
	public boolean isAlive() {
		return aliveCount == getDebugTarget().getVariableUpdateCount();
	}

	@Override
	public IValue getValue() {
		return value;
	}

	@Override
	public String getName() {
		return name;
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
	public Event getWatchedElement() {
		return event;
	}

	@Override
	public String getReferenceTypeName() {
		return event.getTypeName();
	}

	@Override
	public boolean hasValueChanged() {
		return false; // prevents annoying flickering in variables view
	}

	@Override
	public void setValue(final String expression) throws DebugException {
		throw createUnsupportedOperationException();
	}

	@Override
	public void setValue(final IValue value) throws DebugException {
		throw createUnsupportedOperationException();
	}

	@Override
	public boolean supportsValueModification() {
		return false;
	}

	@Override
	public boolean verifyValue(final String expression) {
		return false;
	}

	@Override
	public boolean verifyValue(final IValue value) {
		return false;
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
		return name.hashCode();
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DeploymentDebugEventWatch other = (DeploymentDebugEventWatch) obj;
		return Objects.equals(name, other.name);
	}
}
