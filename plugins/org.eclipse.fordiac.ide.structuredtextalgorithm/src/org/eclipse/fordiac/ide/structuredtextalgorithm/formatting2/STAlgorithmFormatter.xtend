/*******************************************************************************
 * Copyright (c) 2022 Primetals Technologies Austria GmbH
 *               2022 Martin Erich Jobst
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *   Martin Melik Merkumians - initial API and implementation and/or initial documentation
 *   Martin Jobst - algorithm and method name format
 *******************************************************************************/
package org.eclipse.fordiac.ide.structuredtextalgorithm.formatting2

import com.google.inject.Inject
import org.eclipse.fordiac.ide.structuredtextalgorithm.services.STAlgorithmGrammarAccess
import org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STAlgorithm
import org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STAlgorithmSource
import org.eclipse.fordiac.ide.structuredtextalgorithm.stalgorithm.STMethod
import org.eclipse.fordiac.ide.structuredtextcore.formatting2.KeywordCaseTextReplacer
import org.eclipse.fordiac.ide.structuredtextcore.formatting2.STCoreFormatter
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.formatting2.IFormattableDocument

class STAlgorithmFormatter extends STCoreFormatter {

	@Inject extension STAlgorithmGrammarAccess

	def dispatch void format(STAlgorithmSource sTAlgorithmSource, extension IFormattableDocument document) {
		val regions = textRegionAccess.regionForRootEObject.allSemanticRegions;
		regions.forEach [
			it.append[autowrap]
		]

		sTAlgorithmSource.allSemanticRegions.filter [
			switch (element : grammarElement) {
				Keyword case element.value.matches("[_a-zA-Z]+"): true
				RuleCall case element.rule == numericRule: true
				RuleCall case element.rule == STNumericLiteralTypeRule: true
				RuleCall case element.rule == STDateLiteralTypeRule: true
				RuleCall case element.rule == STTimeLiteralTypeRule: true
				RuleCall case element.rule == orOperatorRule: true
				RuleCall case element.rule == xorOperatorRule: true
				RuleCall case element.rule == getUnaryOperatorRule: true
				default: false
			}
		].forEach [
			document.addReplacer(new KeywordCaseTextReplacer(document, it))
		]
		sTAlgorithmSource.allRegionsFor.keywords(STPrimaryExpressionAccess.leftParenthesisKeyword_0_0).forEach [
			append[noSpace]
		]
		sTAlgorithmSource.allRegionsFor.keywords(STPrimaryExpressionAccess.rightParenthesisKeyword_0_2).forEach [
			prepend[noSpace]
		]
		for (element : sTAlgorithmSource.elements) {
			element.format
		}
	}

	def dispatch void format(STAlgorithm sTAlgorithm, extension IFormattableDocument document) {
		sTAlgorithm.regionFor.keyword("ALGORITHM").prepend[noIndentation].append[oneSpace]
		sTAlgorithm.regionFor.assignment(STAlgorithmAccess.nameAssignment_1).append[setNewLines(1, 1, 2)]

		sTAlgorithm.body.varTempDeclarations.forEach[format]
		sTAlgorithm.body.varTempDeclarations.last?.append[setNewLines(1, 2, 2)]

		sTAlgorithm.body.statements.forEach[format]

		sTAlgorithm.regionFor.keyword("END_ALGORITHM").prepend[noIndentation].append[setNewLines(2, 2, 2)]
	}

	def dispatch void format(STMethod sTMethod, extension IFormattableDocument document) {
		sTMethod.regionFor.keyword("METHOD").prepend[noIndentation].append[oneSpace]
		if (sTMethod.returnType !== null) {
			sTMethod.regionFor.keyword(STMethodAccess.colonKeyword_2_0).surround[oneSpace]
			sTMethod.regionFor.assignment(STMethodAccess.returnTypeAssignment_2_1).append [
				setNewLines(1, 1, 2)
			]
		} else {
			sTMethod.regionFor.assignment(STMethodAccess.nameAssignment_1).append[setNewLines(1, 1, 2)]
		}

		sTMethod.body.varDeclarations.forEach[format]
		sTMethod.body.varDeclarations.last?.append[setNewLines(1, 2, 2)]

		sTMethod.body.statements.forEach[format]

		sTMethod.regionFor.keyword("END_METHOD").prepend[noIndentation].append[setNewLines(2, 2, 2)]
	}

}