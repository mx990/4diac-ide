/*******************************************************************************
 * Copyright (c) 2017 fortiss GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Alois Zoitl
 *     - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.application.commands;

import org.eclipse.fordiac.ide.model.commands.create.CreateInterfaceElementCommand;
import org.eclipse.fordiac.ide.model.data.DataType;
import org.eclipse.fordiac.ide.model.libraryElement.IInterfaceElement;
import org.eclipse.fordiac.ide.model.libraryElement.InterfaceList;

public class CreateSubAppInterfaceElementCommand extends CreateInterfaceElementCommand {

	private CreateInterfaceElementCommand mirroredElement = null;

	public CreateSubAppInterfaceElementCommand(final IInterfaceElement copySrc, final boolean isInput,
			final InterfaceList targetInterfaceList,
			int index) {
		super(copySrc, isInput, targetInterfaceList, index);
	}

	public CreateSubAppInterfaceElementCommand(final DataType dataType, final String name,
			final InterfaceList interfaceList, final boolean isInput, final int index) {
		super(dataType, name, interfaceList, isInput, index);
	}

	public CreateSubAppInterfaceElementCommand(final DataType dataType, final InterfaceList interfaceList,
			final boolean isInput, final int index) {
		super(dataType, interfaceList, isInput, index);
	}

	public CreateSubAppInterfaceElementCommand(final DataType dataType, final String name,
			final InterfaceList interfaceList,
			final boolean isInput, final int arraySize, final String value, final int index) {
		super(dataType, name, interfaceList, isInput, arraySize, value, index);
	}

	public CreateInterfaceElementCommand getMirroredElement() {
		return mirroredElement;
	}

	@Override
	public void execute() {
		super.execute();
		if (getInterfaceList().getFBNetworkElement().isMapped()) {
			// the subapp is mapped so we need to created the interface element also in the
			// opposite entry
			mirroredElement = createMirroredInterfaceElement();
		}
	}

	@Override
	public void redo() {
		super.redo();
		if (null != mirroredElement) {
			mirroredElement.redo();
		}
	}

	@Override
	public void undo() {
		super.undo();
		if (null != mirroredElement) {
			mirroredElement.undo();
		}
	}

}
