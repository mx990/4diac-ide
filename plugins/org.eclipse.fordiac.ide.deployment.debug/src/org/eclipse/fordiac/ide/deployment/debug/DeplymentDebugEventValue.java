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

import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.core.model.IVariable;
import org.eclipse.fordiac.ide.model.libraryElement.Event;

public class DeplymentDebugEventValue extends DeploymentDebugElement implements IValue {

	private final Event event;
	private final long count;

	public DeplymentDebugEventValue(final Event event, final long count, final IDeploymentDebugTarget debugTarget) {
		super(debugTarget);
		this.event = event;
		this.count = count;
	}

	public Event getEvent() {
		return event;
	}

	public long getCount() {
		return count;
	}

	@Override
	public String getReferenceTypeName() {
		return event.getTypeName();
	}

	@Override
	public String getValueString() {
		return Long.toString(count);
	}

	@Override
	public boolean isAllocated() {
		return true;
	}

	@Override
	public IVariable[] getVariables() {
		return new IVariable[0];
	}

	@Override
	public boolean hasVariables() {
		return false;
	}
}
