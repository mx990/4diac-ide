/*******************************************************************************
 * Copyright (c) 2020 Johannes Kepler University
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Ernst Blecha
 *     - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.model.commands.create;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.fordiac.ide.model.FordiacKeywords;
import org.eclipse.fordiac.ide.model.commands.testinfra.CreateInterfaceElementCommandTestBase;
import org.eclipse.fordiac.ide.model.libraryElement.InterfaceList;
import org.eclipse.fordiac.ide.model.typelibrary.EventTypeLibrary;
import org.junit.jupiter.params.provider.Arguments;

//see org.eclipse.fordiac.ide.util.ColorHelperTest.java for information on implementing tests

public class CreateInterfaceElementCommandEventOutputsTest extends CreateInterfaceElementCommandTestBase {

	private static final String ELEMENT1_NAME = FordiacKeywords.EVENT_OUTPUT;
	private static final String ELEMENT2_NAME = "MyOutput"; //$NON-NLS-1$
	private static final String ELEMENT3_NAME = "EO2"; //$NON-NLS-1$

	private static State executeCommandOutputWithoutName(final State state) {
		state.setCommand(new CreateInterfaceElementCommand(EventTypeLibrary.getInstance().getType(null),
				getTypeInterfaceList(state), /* isInput */ false, /* index */ 0));

		return commandExecution(state);
	}

	private static void verifyInterfaceListWithName(final InterfaceList interfacelist, final String element, final TestFunction t) {
		t.test(interfacelist.getInputVars().isEmpty());
		t.test(interfacelist.getOutputVars().isEmpty());
		t.test(interfacelist.getEventInputs().isEmpty());
		t.test(!interfacelist.getEventOutputs().isEmpty());
		t.test(interfacelist.getInterfaceElement(element));
		t.test(interfacelist.getInterfaceElement(element).getTypeName(), EVENT_TYPE);
	}

	private static void verifyStateOutputWithoutName(final State state, final State oldState, final TestFunction t) {
		final InterfaceList interfacelist = getTypeInterfaceList(state);
		final InterfaceList oldInterfacelist = getTypeInterfaceList(oldState);

		verifyInterfaceListWithName(interfacelist, ELEMENT1_NAME, t);
		t.test(interfacelist.getEventOutputs().size(), oldInterfacelist.getEventOutputs().size() + 1);
	}

	private static State executeCommandOutputWithName(final State state) {
		state.setCommand(new CreateInterfaceElementCommand(EventTypeLibrary.getInstance().getType(null), ELEMENT2_NAME,
				getTypeInterfaceList(state), /* isInput */ false, /* index */ 1));

		return commandExecution(state);
	}

	private static void verifyStateOutputWithName(final State state, final State oldState, final TestFunction t) {
		final InterfaceList interfacelist = getTypeInterfaceList(state);
		final InterfaceList oldInterfacelist = getTypeInterfaceList(oldState);

		verifyInterfaceListWithName(interfacelist, ELEMENT2_NAME, t);
		t.test(interfacelist.getEventOutputs().size(), oldInterfacelist.getEventOutputs().size() + 1);

	}

	private static State executeCommandOutputWithNameNull(final State state) {
		state.setCommand(new CreateInterfaceElementCommand(EventTypeLibrary.getInstance().getType(null), null,
				getTypeInterfaceList(state), /* isInput */ false, /* index */ 1));

		final State result = commandExecution(state);

		tester.get().test(state.getCommand() instanceof CreateInterfaceElementCommand);
		final CreateInterfaceElementCommand c = ((CreateInterfaceElementCommand) state.getCommand());
		tester.get().test(c.getInterfaceList(), getTypeInterfaceList(state));
		tester.get().test(getTypeInterfaceList(state).getInterfaceElement(ELEMENT3_NAME), c.getCreatedElement());
		tester.get().test(c.getCreatedElement(), c.getCreatedElement());

		return result;
	}

	private static void verifyStateOutputWithNameNull(final State state, final State oldState, final TestFunction t) {
		final InterfaceList interfacelist = getTypeInterfaceList(state);
		final InterfaceList oldInterfacelist = getTypeInterfaceList(oldState);

		verifyInterfaceListWithName(interfacelist, ELEMENT3_NAME, t);
		t.test(interfacelist.getEventOutputs().size(), oldInterfacelist.getEventOutputs().size() + 1);

	}

	// parameter creation function
	public static Collection<Arguments> data() {
		final List<ExecutionDescription<?>> executionDescriptions = List.of( //
				new ExecutionDescription<>("Add Event Output without name", // //$NON-NLS-1$
						CreateInterfaceElementCommandEventOutputsTest::executeCommandOutputWithoutName, //
						CreateInterfaceElementCommandEventOutputsTest::verifyStateOutputWithoutName //
						), //
				new ExecutionDescription<>("Add Event Output with name \"" + ELEMENT2_NAME + "\"", // //$NON-NLS-1$ //$NON-NLS-2$
						CreateInterfaceElementCommandEventOutputsTest::executeCommandOutputWithName, //
						CreateInterfaceElementCommandEventOutputsTest::verifyStateOutputWithName //
						), //
				new ExecutionDescription<>("Add Event Output with null as name", // //$NON-NLS-1$
						CreateInterfaceElementCommandEventOutputsTest::executeCommandOutputWithNameNull, //
						CreateInterfaceElementCommandEventOutputsTest::verifyStateOutputWithNameNull //
						) //
				);

		final Collection<ExecutionDescription<State>> reordering = createReordering(
				(final State s) -> getTypeInterfaceList(s).getEventOutputs(), ELEMENT1_NAME, ELEMENT3_NAME, ELEMENT2_NAME);

		final Collection<ExecutionDescription<State>> updateFBandValidate = createUpdateAndValidate(
				(final State s, final State o, final TestFunction t) -> {
					final InterfaceList interfacelist = getInstanceInterfaceList(s);

					verifyInterfaceListWithName(interfacelist, ELEMENT1_NAME, t);
					verifyInterfaceListWithName(interfacelist, ELEMENT2_NAME, t);
					verifyInterfaceListWithName(interfacelist, ELEMENT3_NAME, t);
					t.test(interfacelist.getEventOutputs().size(), 3);
				});

		return createCommands(Stream.concat( //
				Stream.concat( //
						executionDescriptions.stream(), //
						reordering.stream()), //
				updateFBandValidate.stream() //
				).collect(Collectors.toList()));
	}

}
