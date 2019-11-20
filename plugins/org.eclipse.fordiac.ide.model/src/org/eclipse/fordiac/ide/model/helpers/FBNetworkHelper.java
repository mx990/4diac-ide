/*******************************************************************************
 * Copyright (c) 2019 Johannes Kepler University Linz
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Alois Zoitl - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.model.helpers;

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.fordiac.ide.model.libraryElement.AdapterConnection;
import org.eclipse.fordiac.ide.model.libraryElement.Connection;
import org.eclipse.fordiac.ide.model.libraryElement.DataConnection;
import org.eclipse.fordiac.ide.model.libraryElement.EventConnection;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetwork;
import org.eclipse.fordiac.ide.model.libraryElement.FBNetworkElement;
import org.eclipse.fordiac.ide.model.libraryElement.IInterfaceElement;
import org.eclipse.fordiac.ide.model.libraryElement.InterfaceList;
import org.eclipse.fordiac.ide.model.libraryElement.LibraryElementFactory;
import org.eclipse.fordiac.ide.model.libraryElement.impl.FBImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.graphics.Point;

public final class FBNetworkHelper {

	/**
	 * Take the src FBNetwork and copy it into a new network.
	 *
	 * @param srcNetwork    the FBNetwork to copy
	 * @param destInterface if not null the interface of the component the new
	 *                      FBNetwork should be contained in
	 * @return the copied FBNetwork
	 */
	public static FBNetwork copyFBNetWork(FBNetwork srcNetwork, InterfaceList destInterface) {
		FBNetwork dstNetwork = LibraryElementFactory.eINSTANCE.createFBNetwork();
		dstNetwork.getNetworkElements().addAll(EcoreUtil.copyAll(srcNetwork.getNetworkElements()));
		createConnections(srcNetwork, dstNetwork, destInterface);
		return dstNetwork;
	}

	private static void createConnections(FBNetwork srcNetwork, FBNetwork dstNetwork, InterfaceList destInterface) {
		for (Connection connection : srcNetwork.getEventConnections()) {
			dstNetwork.getEventConnections()
					.add((EventConnection) createConnection(srcNetwork, destInterface, dstNetwork, connection));
		}

		for (Connection connection : srcNetwork.getDataConnections()) {
			dstNetwork.getDataConnections()
					.add((DataConnection) createConnection(srcNetwork, destInterface, dstNetwork, connection));
		}

		for (Connection connection : srcNetwork.getAdapterConnections()) {
			dstNetwork.getAdapterConnections()
					.add((AdapterConnection) createConnection(srcNetwork, destInterface, dstNetwork, connection));
		}
	}

	private static Connection createConnection(FBNetwork srcNetwork, InterfaceList destInterface, FBNetwork dstNetwork,
			Connection connection) {
		Connection newConn = EcoreUtil.copy(connection);
		newConn.setSource(getInterfaceElement(connection.getSource(), destInterface, dstNetwork, srcNetwork));
		newConn.setDestination(getInterfaceElement(connection.getDestination(), destInterface, dstNetwork, srcNetwork));
		return newConn;
	}

	private static IInterfaceElement getInterfaceElement(IInterfaceElement ie, InterfaceList typeInterface,
			FBNetwork dstNetwork, FBNetwork srcNetwork) {
		if (null == ie.getFBNetworkElement()) {
			return typeInterface.getInterfaceElement(ie.getName());
		}

		if (!srcNetwork.equals(ie.getFBNetworkElement().getFbNetwork())) {
			return typeInterface.getInterfaceElement(ie.getName());
		}
		FBNetworkElement element = dstNetwork.getElementNamed(ie.getFBNetworkElement().getName());
		if (null == element) {
			return null;
		}
		return element.getInterfaceElement(ie.getName());
	}

	/**
	 * methods for updating position of FBNetwork after creating/flattening
	 * subapp/...
	 */

	public static Point getTopLeftCornerOfFBNetwork(List<?> selection) {
		Assert.isNotNull(selection);
		Point pt = new Point(0, 0);
		Object fb = null;
		for (Object sel : selection) {
			fb = (sel instanceof EditPart) ? ((EditPart) sel).getModel() : sel;
			if (fb instanceof FBImpl) {
				pt.x = (pt.x == 0) ? ((FBNetworkElement) fb).getX() : Math.min(pt.x, ((FBNetworkElement) fb).getX());
				pt.y = (pt.y == 0) ? ((FBNetworkElement) fb).getY() : Math.min(pt.y, ((FBNetworkElement) fb).getY());
			}
		}
		return pt;
	}

	public static final int Y_OFFSET_FROM_TOP_LEFT_CORNER = 80; // from top-left corner of container
	public static final int X_OFFSET_FROM_TOP_LEFT_CORNER = 150;

	public static void moveFBNetworkByOffset(Iterable<FBNetworkElement> fbNetwork, int xOffset, int yOffset) {
		for (FBNetworkElement el : fbNetwork) {
			el.setX(el.getX() - xOffset);
			el.setY(el.getY() - yOffset);
		}
	}

	public static void removeXYOffsetForFBNetwork(List<FBNetworkElement> fbNetwork) {
		Point offset = getTopLeftCornerOfFBNetwork(fbNetwork);
		moveFBNetworkByOffset(fbNetwork, offset.x - X_OFFSET_FROM_TOP_LEFT_CORNER,
				offset.y - Y_OFFSET_FROM_TOP_LEFT_CORNER);
	}

	public static void moveFBNetworkByOffset(List<FBNetworkElement> fbNetwork, Point offset) {
		moveFBNetworkByOffset(fbNetwork, offset.x, offset.y);
	}

	private FBNetworkHelper() {
		throw new IllegalStateException("FBNetworkHelper is a utility class that can not be instantiated"); //$NON-NLS-1$
	}
}
