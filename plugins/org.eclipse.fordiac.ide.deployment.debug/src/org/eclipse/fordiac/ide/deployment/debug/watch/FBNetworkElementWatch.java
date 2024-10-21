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
package org.eclipse.fordiac.ide.deployment.debug.watch;

import java.util.Objects;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.fordiac.ide.deployment.debug.DeploymentDebugDevice;
import org.eclipse.fordiac.ide.deployment.debug.DeploymentDebugElement;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetworkElement;
import org.eclipse.fordiac.ide.model.libraryElement.Resource;

public class FBNetworkElementWatch extends DeploymentDebugElement implements IWatch {

	private final String name;
	private final FBNetworkElement element;
	private final FBNetworkElementValue value;

	public FBNetworkElementWatch(final String name, final FBNetworkElement element,
			final DeploymentDebugDevice target) {
		super(target);
		this.name = name;
		this.element = element;
		value = new FBNetworkElementValue(element, target);
	}

	@Override
	public void addWatch() throws DebugException {
		for (final IWatch watch : value.getWatches()) {
			watch.addWatch();
		}
	}

	@Override
	public void removeWatch() throws DebugException {
		for (final IWatch watch : value.getWatches()) {
			watch.removeWatch();
		}
	}

	@Override
	public void updateValue(final DeploymentDebugWatchData watchData) {
		for (final IWatch watch : value.getWatches()) {
			watch.updateValue(watchData);
		}
	}

	@Override
	public boolean isAlive() {
		return value.getWatches().stream().allMatch(IWatch::isAlive);
	}

	@Override
	public IValue getValue() throws DebugException {
		return value;
	}

	@Override
	public String getReferenceTypeName() throws DebugException {
		return element.getTypeName();
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
	public String getName() {
		return name;
	}

	@Override
	public Resource getResource() {
		return element.getResource();
	}

	@Override
	public FBNetworkElement getWatchedElement() {
		return element;
	}

	@Override
	public DeploymentDebugDevice getDebugTarget() {
		return (DeploymentDebugDevice) super.getDebugTarget();
	}

	@Override
	public int hashCode() {
		return getQualifiedName().hashCode();
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
		final FBNetworkElementWatch other = (FBNetworkElementWatch) obj;
		return Objects.equals(getQualifiedName(), other.getQualifiedName());
	}
}
