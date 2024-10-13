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
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDisconnect;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.fordiac.ide.debug.EvaluatorDebugVariable;
import org.eclipse.fordiac.ide.deployment.debug.breakpoint.DeploymentWatchpoint;
import org.eclipse.fordiac.ide.deployment.debug.watch.IWatch;
import org.eclipse.fordiac.ide.deployment.exceptions.DeploymentException;
import org.eclipse.fordiac.ide.model.eval.variable.Variable;
import org.eclipse.fordiac.ide.model.libraryElement.AutomationSystem;
import org.eclipse.fordiac.ide.model.libraryElement.Device;
import org.eclipse.fordiac.ide.model.libraryElement.INamedElement;

public class DeploymentDebugTarget extends DeploymentDebugElement implements IDeploymentDebugTarget {

	private final ILaunch launch;
	private final AutomationSystem system;
	private final boolean allowTerminate;
	private final Duration pollingInterval;

	private final DeploymentProcess process;
	private final DeploymentDebugThread thread;
	private final AtomicLong variableUpdateCount = new AtomicLong();
	private final Map<String, IWatch> watches = new ConcurrentSkipListMap<>();

	private boolean terminated;
	private boolean disconnected;

	public DeploymentDebugTarget(final AutomationSystem system, final Set<INamedElement> selection,
			final ILaunch launch, final boolean allowTerminate, final Duration pollingInterval)
			throws DeploymentException {
		super(null);
		this.launch = launch;
		this.system = system;
		this.allowTerminate = allowTerminate;
		this.pollingInterval = pollingInterval;
		process = new DeploymentProcess(system, selection, launch);
		process.getJob().addJobChangeListener(IJobChangeListener.onDone(this::deploymentDone));
		thread = new DeploymentDebugThread(this);
		launch.addDebugTarget(this);
		fireCreationEvent();
	}

	private void deploymentDone(final IJobChangeEvent event) {
		if (event.getResult().isOK()) {
			Job.create(MessageFormat.format(Messages.DeploymentDebugTarget_ConnectJobName, getName()), this::doConnect)
					.schedule();
		} else {
			terminated();
		}
	}

	protected void doConnect(final IProgressMonitor monitor) throws DebugException {
		try {
			for (final Device device : system.getSystemConfiguration().getDevices()) {
				new DeploymentDebugDevice(device, this, allowTerminate, pollingInterval).connect();
			}
		} finally {
			thread.fireSuspendEvent(DebugEvent.CLIENT_REQUEST);
		}
	}

	public void start() {
		process.start();
	}

	@Override
	public long getVariableUpdateCount() {
		return variableUpdateCount.get();
	}

	public long incrementVariableUpdateCount() {
		return variableUpdateCount.incrementAndGet();
	}

	@Override
	public EvaluatorDebugVariable createVariable(final Variable<?> variable, final String expression) {
		return new DeploymentDebugVariable(variable, expression, this);
	}

	@Override
	public Map<String, IWatch> getWatches() {
		return Collections.unmodifiableMap(watches);
	}

	@Override
	public boolean canResume() {
		return false;
	}

	@Override
	public boolean canSuspend() {
		return false;
	}

	@Override
	public boolean isSuspended() {
		return false;
	}

	@Override
	public void resume() throws DebugException {
		throw createUnsupportedOperationException();
	}

	@Override
	public void suspend() throws DebugException {
		throw createUnsupportedOperationException();
	}

	@Override
	public boolean supportsBreakpoint(final IBreakpoint breakpoint) {
		return breakpoint instanceof final DeploymentWatchpoint watchpoint && watchpoint.isRelevant(getSystem());
	}

	@Override
	public void breakpointAdded(final IBreakpoint breakpoint) {
		updateWatches(true);
	}

	@Override
	public void breakpointRemoved(final IBreakpoint breakpoint, final IMarkerDelta delta) {
		updateWatches(true);
	}

	@Override
	public void breakpointChanged(final IBreakpoint breakpoint, final IMarkerDelta delta) {
		updateWatches(false);
	}

	public void updateWatches(final boolean structureChanged) {
		if (structureChanged) {
			final Map<String, IWatch> combinedWatches = Stream.of(launch.getDebugTargets())
					.filter(DeploymentDebugDevice.class::isInstance).map(DeploymentDebugDevice.class::cast)
					.map(DeploymentDebugDevice::getWatches).map(Map::entrySet).flatMap(Set::stream)
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
			watches.keySet().retainAll(combinedWatches.keySet());
			watches.putAll(combinedWatches);
		}
		thread.getTopStackFrame().fireChangeEvent(DebugEvent.CONTENT);
	}

	@Override
	public String getName() {
		return system.getName();
	}

	@Override
	public IDeploymentDebugTarget getDebugTarget() {
		return this;
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@Override
	public AutomationSystem getSystem() {
		return system;
	}

	public Duration getPollingInterval() {
		return pollingInterval;
	}

	public boolean isAllowTerminate() {
		return allowTerminate;
	}

	@Override
	public boolean canTerminate() {
		return !isTerminated() && !isDisconnected() && allowTerminate;
	}

	@Override
	public boolean isTerminated() {
		return terminated;
	}

	@Override
	public void terminate() throws DebugException {
		terminated();
		launch.terminate();
	}

	protected void terminated() {
		terminated = true;
		fireTerminateEvent();
	}

	@Override
	public boolean canDisconnect() {
		return !isTerminated() && !isDisconnected();
	}

	@Override
	public void disconnect() throws DebugException {
		disconnected();
		if (launch instanceof final IDisconnect disconnect) {
			disconnect.disconnect();
		}
	}

	protected void disconnected() {
		disconnected = true;
		fireTerminateEvent();
	}

	@Override
	public boolean isDisconnected() {
		return disconnected;
	}

	@Override
	public boolean supportsStorageRetrieval() {
		return false;
	}

	@Override
	public IMemoryBlock getMemoryBlock(final long startAddress, final long length) throws DebugException {
		throw createUnsupportedOperationException();
	}

	@Override
	public DeploymentProcess getProcess() {
		return process;
	}

	@Override
	public IThread[] getThreads() {
		return hasThreads() ? new IThread[] { thread } : new IThread[0];
	}

	@Override
	public boolean hasThreads() {
		return process.isTerminated() && !isTerminated() && !isDisconnected();
	}
}
