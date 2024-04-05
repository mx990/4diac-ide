/*******************************************************************************
 * Copyright (c) 2022-2023 Martin Erich Jobst
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
package org.eclipse.fordiac.ide.model.eval.fb

import java.util.Map
import org.eclipse.fordiac.ide.model.eval.Evaluator
import org.eclipse.fordiac.ide.model.eval.EvaluatorFactory
import org.eclipse.fordiac.ide.model.eval.variable.FBVariable
import org.eclipse.fordiac.ide.model.eval.variable.Variable
import org.eclipse.fordiac.ide.model.libraryElement.Algorithm
import org.eclipse.fordiac.ide.model.libraryElement.BaseFBType
import org.eclipse.fordiac.ide.model.libraryElement.Method
import org.eclipse.fordiac.ide.model.libraryElement.FB
import org.eclipse.fordiac.ide.model.libraryElement.FBType
import org.eclipse.xtend.lib.annotations.Accessors

abstract class BaseFBEvaluator<T extends BaseFBType> extends FBEvaluator<T> {
	@Accessors final Map<Algorithm, Evaluator> algorithmEvaluators
	@Accessors final Map<FB, FBEvaluator<?>> internalFBEvaluators

	new(T type, Variable<?> context, Iterable<Variable<?>> variables, Evaluator parent) {
		super(type, context, variables, parent)
		algorithmEvaluators = type.algorithm.toInvertedMap [
			EvaluatorFactory.createEvaluator(it, eClass.instanceClass as Class<? extends Algorithm>, this.context,
				emptySet, this)
		]
		internalFBEvaluators = type.internalFbs.toInvertedMap [
			EvaluatorFactory.createEvaluator(it.type, it.type.eClass.instanceClass as Class<? extends FBType>, this.context,
				(this.context.members.get(name) as FBVariable).members.values, this) as FBEvaluator<?>
		]
	}

	override prepare() {
		algorithmEvaluators.values.forEach[prepare]
		internalFBEvaluators.values.forEach[prepare]
	}

	override getDependencies() {
		(super.dependencies + algorithmEvaluators.values.flatMap [
			dependencies
		] + type.methods.map [
			EvaluatorFactory.createEvaluator(it, eClass.instanceClass as Class<? extends Method>, this.context,
				emptySet, this)
		].flatMap [
			dependencies
		]).toSet
	}

	override getChildren() {
		(algorithmEvaluators.entrySet + internalFBEvaluators.entrySet).toMap([key], [value])
	}
}