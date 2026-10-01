/*******************************************************************************
 * Copyright (c) 2026 Martin Erich Jobst
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
package org.eclipse.fordiac.ide.model.typelibrary;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.fordiac.ide.model.libraryElement.ErrorFBType;
import org.eclipse.fordiac.ide.model.libraryElement.FBType;
import org.eclipse.fordiac.ide.model.libraryElement.LibraryElement;
import org.eclipse.fordiac.ide.model.libraryElement.LibraryElementFactory;
import org.eclipse.fordiac.ide.model.libraryElement.LibraryElementPackage;
import org.eclipse.fordiac.ide.test.model.typelibrary.FBTypeEntryMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@SuppressWarnings("static-method")
class TypeLibraryTest {

	@Test
	@Timeout(10)
	void testConcurrentModifyLookup() throws Throwable {
		final TypeLibrary library = TypeLibraryManager.INSTANCE.getTypeLibrary(null);
		final String name = "RaceType"; //$NON-NLS-1$
		final FBType type = createFBType(name);
		final FBTypeEntryMock entry = new FBTypeEntryMock(type, null, null);
		assertInstanceOf(ErrorFBType.class, lookupType(library, name));

		final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		final ScheduledFuture<?> lookupFuture = executor.scheduleWithFixedDelay(() -> lookupType(library, name), 0, 1,
				TimeUnit.NANOSECONDS);

		try {
			for (int attempt = 0; attempt < 1000; attempt++) {
				library.addTypeEntry(entry);
				assertSame(type, lookupType(library, name));

				library.removeTypeEntry(entry);
				assertInstanceOf(ErrorFBType.class, lookupType(library, name));
			}
		} finally {
			executor.shutdownNow();
		}

		assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS), "Timed out waiting for executor to terminate"); //$NON-NLS-1$
		if (lookupFuture.state() == Future.State.FAILED) {
			throw lookupFuture.exceptionNow();
		}
	}

	private static LibraryElement lookupType(final TypeLibrary library, final String name) {
		return library.createErrorTypeEntry(name, LibraryElementPackage.Literals.FB_TYPE).getType();
	}

	private static FBType createFBType(final String name) {
		final FBType type = LibraryElementFactory.eINSTANCE.createFBType();
		type.setName(name);
		return type;
	}
}
