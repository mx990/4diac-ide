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
package org.eclipse.fordiac.ide.model.helpers;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.fordiac.ide.model.libraryElement.INamedElement;

public final class QualifiedNameHelper {

	public static Optional<INamedElement> findByQualifiedName(final INamedElement root, final String qualifiedName) {
		final int separator = qualifiedName.indexOf('.');
		if (separator >= 0) {
			final String head = qualifiedName.substring(0, separator);
			final String tail = qualifiedName.substring(separator + 1);
			return findBySimpleName(root, head).flatMap(element -> findByQualifiedName(element, tail));
		}
		return findBySimpleName(root, qualifiedName);
	}

	public static Optional<INamedElement> findBySimpleName(final INamedElement root, final String name) {
		final TreeIterator<EObject> contents = root.eAllContents();
		while (contents.hasNext()) {
			final EObject element = contents.next();
			if (element instanceof final INamedElement namedElement) {
				if (Objects.equals(namedElement.getName(), name)) {
					return Optional.of(namedElement);
				}
				contents.prune();
			}
		}
		return Optional.empty();
	}

	private QualifiedNameHelper() {
		throw new UnsupportedOperationException();
	}
}
