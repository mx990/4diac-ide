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
package org.eclipse.fordiac.ide.export.forte_ng.st

import java.util.Map
import org.eclipse.fordiac.ide.export.ExportException
import org.eclipse.fordiac.ide.model.libraryElement.ECTransition
import org.eclipse.fordiac.ide.model.libraryElement.FBType
import org.eclipse.fordiac.ide.structuredtextcore.stcore.STExpression
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

import static extension org.eclipse.emf.ecore.util.EcoreUtil.*
import static extension org.eclipse.fordiac.ide.structuredtextalgorithm.util.StructuredTextParseUtil.*

@FinalFieldsConstructor
class ECTransitionSupport extends StructuredTextSupport {
	final ECTransition transition

	STExpression parseResult

	override prepare(Map<?, ?> options) {
		if (parseResult === null && errors.empty) {
			parseResult = transition.conditionExpression.parse(
				switch (root : transition.rootContainer) { FBType: root },
				errors, warnings, infos
			)
		}
		return parseResult !== null
	}

	override generate(Map<?, ?> options) throws ExportException {
		prepare(options)
		parseResult?.generateExpression
	}

	override getDependencies(Map<?, ?> options) {
		prepare(options)
		parseResult?.containedDependencies ?: emptySet
	}
}
