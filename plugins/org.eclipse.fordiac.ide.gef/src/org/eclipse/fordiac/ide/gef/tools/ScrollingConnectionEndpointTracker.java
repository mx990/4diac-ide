/*******************************************************************************
 * Copyright (c) 2019, 2021 Johannes Kepler University Linz,
 *                          Primetals Technologies Austria
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Alois Zoitl - initial API and implementation and/or initial documentation
 *               - added the option to duplicate the connection when pressing
 *                 the ctrl key during dragging
 *               - keep connection draging within canvas bounds
 *******************************************************************************/
package org.eclipse.fordiac.ide.gef.tools;

import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.fordiac.ide.gef.figures.HideableConnection;
import org.eclipse.fordiac.ide.gef.router.MoveableRouter;
import org.eclipse.fordiac.ide.model.ui.editors.AdvancedScrollingGraphicalViewer;
import org.eclipse.fordiac.ide.ui.preferences.ConnectionPreferenceValues;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gef.tools.ConnectionEndpointTracker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;

public class ScrollingConnectionEndpointTracker extends ConnectionEndpointTracker {

	// Safety border around the canvas to ensure that during dragging connections the canvas is not growing
	private static final Insets CONNECTION_CANVAS_BORDER = new Insets(
			ConnectionPreferenceValues.HANDLE_SIZE,
			MoveableRouter.MIN_CONNECTION_FB_DISTANCE + HideableConnection.BEND_POINT_BEVEL_SIZE
			+ ConnectionPreferenceValues.HANDLE_SIZE,
			ConnectionPreferenceValues.HANDLE_SIZE,
			ConnectionPreferenceValues.HANDLE_SIZE);


	private static final class InlineConnectionDragCreationTool extends FordiacConnectionDragCreationTool {
		private final EditPart originalSource;

		private InlineConnectionDragCreationTool(final EditPart originalSource, final EditDomain editDomain,
				final EditPartViewer editPartViewer) {
			this.originalSource = originalSource;
			super.setEditDomain(editDomain);
			super.setViewer(editPartViewer);
			startDraggingFrom();
		}

		@Override
		protected Command getCommand() {
			if (getTargetEditPart() instanceof ConnectionEditPart) {
				// this is the initial command request update the source position
				setTargetEditPart(originalSource);
				setConnectionSource(originalSource);
			}
			return super.getCommand();
		}

		private void startDraggingFrom() {
			super.activate();
			super.setTargetEditPart(null);
			super.handleButtonDown(1); // emulate left mouse button click
			super.handleDragStarted();
		}
	}

	private InlineConnectionDragCreationTool conCreationTool = null;

	public ScrollingConnectionEndpointTracker(final ConnectionEditPart cep) {
		super(cep);
	}


	@Override
	public void mouseDrag(final MouseEvent me, final EditPartViewer viewer) {
		if (isActive() && (viewer instanceof AdvancedScrollingGraphicalViewer)) {
			final Point oldViewPort = ((AdvancedScrollingGraphicalViewer) viewer).getViewLocation();
			((AdvancedScrollingGraphicalViewer) viewer)
			.checkScrollPositionDuringDragBounded(me,
					new Point(
							MoveableRouter.MIN_CONNECTION_FB_DISTANCE + HideableConnection.BEND_POINT_BEVEL_SIZE
							+ ConnectionPreferenceValues.HANDLE_SIZE,
							ConnectionPreferenceValues.HANDLE_SIZE));
			final Dimension delta = oldViewPort.getDifference(((AdvancedScrollingGraphicalViewer) viewer).getViewLocation());
			// Compensate the moved scrolling in the start position for correct dropping of
			// moved parts
			setStartLocation(getStartLocation().getTranslated(delta));
			CanvasHelper.bindToContentPane(me, (AdvancedScrollingGraphicalViewer) viewer, getCanvasBorder());
		}
		if (null != conCreationTool) {
			getCurrentInput().setInput(me);
			conCreationTool.mouseDrag(me, viewer);
		} else if ((me.stateMask & SWT.MOD1) != 0) {
			startConnCreation();
		} else {
			super.mouseDrag(me, viewer);
		}
	}

	@SuppressWarnings("static-method")  // allow sub-classes to override the border calculation
	protected Insets getCanvasBorder() {
		return CONNECTION_CANVAS_BORDER;
	}

	@Override
	public void keyDown(final KeyEvent keyEvent, final EditPartViewer viewer) {
		if ((null == conCreationTool) && (keyEvent.keyCode == SWT.MOD1)) {
			// Ctrl or Command key was pressed and conn creation was not active
			startConnCreation();
		} else {
			super.keyDown(keyEvent, viewer);
		}
	}

	private void startConnCreation() {
		final EditPart target = (getCommandName().equals(RequestConstants.REQ_RECONNECT_SOURCE))
				? getConnectionEditPart().getTarget()
						: getConnectionEditPart().getSource();
		conCreationTool = new InlineConnectionDragCreationTool(target, getDomain(), getCurrentViewer());
		updateTarget(getStartLocation());
	}

	private void updateTarget(final Point p) {
		final ReconnectRequest request = (ReconnectRequest) getTargetRequest();
		request.setLocation(p);
		final EditPart target = (getCommandName().equals(RequestConstants.REQ_RECONNECT_SOURCE))
				? getConnectionEditPart().getSource()
						: getConnectionEditPart().getTarget();
		request.setTargetEditPart(target);
		getConnectionEditPart().showSourceFeedback(request);
		getConnectionEditPart().showTargetFeedback(request);
	}

	@Override
	public void keyUp(final KeyEvent keyEvent, final EditPartViewer viewer) {
		if ((null != conCreationTool) && (keyEvent.keyCode == SWT.MOD1)) {
			// Ctrl or Command key was released
			conCreationTool.deactivate();
			conCreationTool = null;
			updateTarget(getLocation());
		}
		super.keyUp(keyEvent, viewer);
	}

	@Override
	public void mouseMove(final MouseEvent me, final EditPartViewer viewer) {
		if (null != conCreationTool) {
			conCreationTool.mouseMove(me, viewer);
		} else {
			super.mouseMove(me, viewer);
		}
	}

	@Override
	public void mouseUp(final MouseEvent me, final EditPartViewer viewer) {
		if (null != conCreationTool) {
			conCreationTool.mouseUp(me, viewer);
			conCreationTool = null;
			handleFinished();
		} else {
			super.mouseUp(me, viewer);
		}
	}

	@Override
	public void setEditDomain(final EditDomain domain) {
		if (null != conCreationTool) {
			conCreationTool.setEditDomain(domain);
		}
		super.setEditDomain(domain);
	}

	@Override
	public void setViewer(final EditPartViewer viewer) {
		if (null != conCreationTool) {
			conCreationTool.setViewer(viewer);
		}
		super.setViewer(viewer);
	}

	@Override
	public void viewerEntered(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		if (null != conCreationTool) {
			conCreationTool.viewerEntered(mouseEvent, viewer);
		}
		super.viewerEntered(mouseEvent, viewer);
	}

	@Override
	public void viewerExited(final MouseEvent mouseEvent, final EditPartViewer viewer) {
		if (null != conCreationTool) {
			conCreationTool.viewerExited(mouseEvent, viewer);
		}
		super.viewerExited(mouseEvent, viewer);
	}

	@Override
	public void setProperties(final Map properties) {
		if (null != conCreationTool) {
			conCreationTool.setProperties(properties);
		}
		super.setProperties(properties);
	}

	@Override
	public void commitDrag() {
		if (null != conCreationTool) {
			conCreationTool.commitDrag();
		}
		super.commitDrag();
	}

}
