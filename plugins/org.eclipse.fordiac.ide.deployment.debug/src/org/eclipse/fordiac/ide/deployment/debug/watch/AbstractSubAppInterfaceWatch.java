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

import java.util.LinkedHashSet;
import java.util.SequencedSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.fordiac.ide.deployment.debug.DeploymentDebugDevice;
import org.eclipse.fordiac.ide.model.eval.EvaluatorException;
import org.eclipse.fordiac.ide.model.eval.variable.Variable;
import org.eclipse.fordiac.ide.model.libraryElement.Connection;
import org.eclipse.fordiac.ide.model.libraryElement.IInterfaceElement;
import org.eclipse.fordiac.ide.model.libraryElement.SubApp;

public abstract class AbstractSubAppInterfaceWatch extends AbstractVirtualWatch {

	protected AbstractSubAppInterfaceWatch(final Variable<?> variable, final IInterfaceElement element,
			final DeploymentDebugDevice debugTarget) {
		super(variable, element, createWatches(element, debugTarget), debugTarget);
	}

	protected static SequencedSet<IVariableWatch> createWatches(final IInterfaceElement element,
			final DeploymentDebugDevice debugTarget) {
		return resolveSubappInterfaceConnections(element).distinct()
				.map(resolved -> createSubWatch(resolved, debugTarget))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}

	protected static IVariableWatch createSubWatch(final IInterfaceElement element,
			final DeploymentDebugDevice debugTarget) throws EvaluatorException, UnsupportedOperationException {
		return (IVariableWatch) IWatch.watchFor(element.getQualifiedName(), element, debugTarget);
	}

	protected static Stream<IInterfaceElement> resolveSubappInterfaceConnections(final IInterfaceElement element) {
		if (!(element.getFBNetworkElement() instanceof final SubApp subapp)) {
			return Stream.of(element);
		}
		subapp.loadSubAppNetwork(); // ensure network is loaded
		if (element.isIsInput()) {
			return element.getOutputConnections().stream().map(Connection::getDestination)
					.flatMap(AbstractSubAppInterfaceWatch::resolveSubappInterfaceConnections);
		}
		return element.getInputConnections().stream().map(Connection::getSource)
				.flatMap(AbstractSubAppInterfaceWatch::resolveSubappInterfaceConnections);
	}
}
