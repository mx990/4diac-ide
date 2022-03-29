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
package org.eclipse.fordiac.ide.structuredtextcore.converter;

import org.eclipse.fordiac.ide.model.value.StringValueConverter;
import org.eclipse.fordiac.ide.structuredtextcore.stcore.STString;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractLexerBasedConverter;
import org.eclipse.xtext.nodemodel.INode;

public class STStringValueConverter extends AbstractLexerBasedConverter<STString> {

	@Override
	public STString toValue(final String string, final INode node) throws ValueConverterException {
		try {
			final var value = StringValueConverter.INSTANCE.toValue(string);
			final boolean wide = string.charAt(0) == '"';
			return new STString(value, wide);
		} catch (final Exception e) {
			throw new ValueConverterException(e.getMessage(), node, (Exception) e.getCause());
		}
	}

	@Override
	protected String toEscapedString(final STString value) {
		return StringValueConverter.INSTANCE.toString(value.toString(), value.isWide());
	}
}
