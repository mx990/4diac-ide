/*******************************************************************************
 * Copyright (c) 2022 Primetals Technologies Austria GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Dunja Životin - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.fordiac.ide.model.search.types;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.fordiac.ide.model.libraryElement.INamedElement;

public class StructSearchResult {

	private final List<INamedElement> structSearchResults;

	public StructSearchResult() {
		this.structSearchResults = new ArrayList<>();
	}

	public List<INamedElement> getStructSearchResults() {
		return structSearchResults;
	}

	public void addResult(final INamedElement result) {
		structSearchResults.add(result);
	}

	public void addResults(final List<INamedElement> results) {
		structSearchResults.addAll(results);
	}

}