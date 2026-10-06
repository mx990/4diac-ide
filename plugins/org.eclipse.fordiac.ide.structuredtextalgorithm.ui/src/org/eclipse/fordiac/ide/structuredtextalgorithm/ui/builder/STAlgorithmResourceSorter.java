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
package org.eclipse.fordiac.ide.structuredtextalgorithm.ui.builder;

import java.util.Collection;
import java.util.Comparator;
import java.util.Locale;

import org.eclipse.emf.common.util.URI;
import org.eclipse.fordiac.ide.model.typelibrary.TypeLibraryTags;
import org.eclipse.xtext.builder.resourceloader.IResourceLoader;

@SuppressWarnings("restriction")
public class STAlgorithmResourceSorter implements IResourceLoader.Sorter {

	@Override
	public Collection<URI> sort(final Collection<URI> uris) {
		return uris.stream().sorted(Comparator.comparingInt(STAlgorithmResourceSorter::priority)).toList();
	}

	private static int priority(final URI uri) {
		final String extension = uri.fileExtension();
		if (extension == null) {
			return 99;
		}

		return switch (extension.toUpperCase(Locale.ROOT)) {
		case TypeLibraryTags.GLOBAL_CONST_FILE_ENDING -> 0;
		case TypeLibraryTags.ATTRIBUTE_TYPE_FILE_ENDING -> 1;
		case TypeLibraryTags.DATA_TYPE_FILE_ENDING -> 2;
		case TypeLibraryTags.ADAPTER_TYPE_FILE_ENDING -> 3;
		case TypeLibraryTags.FB_TYPE_FILE_ENDING, //
				TypeLibraryTags.FC_TYPE_FILE_ENDING ->
			4;
		case TypeLibraryTags.SUBAPP_TYPE_FILE_ENDING -> 5;
		case TypeLibraryTags.RESOURCE_TYPE_FILE_ENDING, //
				TypeLibraryTags.DEVICE_TYPE_FILE_ENDING, //
				TypeLibraryTags.SEGMENT_TYPE_FILE_ENDING ->
			6;
		case TypeLibraryTags.SYSTEM_TYPE_FILE_ENDING -> 7;
		default -> 99;
		};
	}
}
