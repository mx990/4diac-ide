/*******************************************************************************
 * Copyright (c) 2022 Martin Erich Jobst
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
package org.eclipse.fordiac.ide.structuredtextalgorithm.ui;

import org.eclipse.fordiac.ide.structuredtextalgorithm.ui.builder.STAlgorithmResourceSorter;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader;

import com.google.inject.Binder;

@SuppressWarnings("restriction")
public class STAlgorithmSharedOverrideModule implements com.google.inject.Module {

	@Override
	public void configure(final Binder binder) {
		binder.bind(IResourceLoader.Sorter.class).to(STAlgorithmResourceSorter.class);
	}
}
