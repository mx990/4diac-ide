/*******************************************************************************
 * Copyright (c) 2015 - 2017 fortiss GmbH
 * 				 2019 - 2020 Johannes Kepler University Linz
 * 				 2020 Primetals Technologies Germany GmbH
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Monika Wenger, Alois Zoitl
 *     - initial API and implementation and/or initial documentation
 *   Alois Zoitl - added mulitline selection and code cleanup.
 *   Bianca Wiesmayr - extract Table creation, improve insertion
 *   Alois Zoitl - extracted helper for ComboCellEditors that unfold on activation
 *   Daniel Lindhuber - added copy and paste
 *   Bianca Wiesmayr - extracted super class for simple and basic FB, added context menu
 *   Daniel Lindhuber - changed type selection to search field
 *   Alexander Lumplecker
 *     - changed AddDeleteWidget to AddDeleteReorderListWidget
 *     - added ChangeVariableOrderCommand
 *   Sebastian Hollersbacher - change to nebula NatTable
 *******************************************************************************/
package org.eclipse.fordiac.ide.gef.properties;

import org.eclipse.fordiac.ide.gef.nat.VarDeclarationColumnProvider;
import org.eclipse.fordiac.ide.gef.nat.VarDeclarationListProvider;
import org.eclipse.fordiac.ide.model.commands.change.ChangeVariableOrderCommand;
import org.eclipse.fordiac.ide.model.commands.create.CreateInternalVariableCommand;
import org.eclipse.fordiac.ide.model.commands.delete.DeleteInternalVariableCommand;
import org.eclipse.fordiac.ide.model.commands.insert.InsertVariableCommand;
import org.eclipse.fordiac.ide.model.data.DataType;
import org.eclipse.fordiac.ide.model.libraryElement.BaseFBType;
import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration;
import org.eclipse.fordiac.ide.ui.widget.AddDeleteReorderListWidget;
import org.eclipse.fordiac.ide.ui.widget.I4diacNatTableUtil;
import org.eclipse.fordiac.ide.ui.widget.NatTableWidgetFactory;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.action.IAction;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.IConfigLabelAccumulator;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class InternalVarsSection extends AbstractSection implements I4diacNatTableUtil {

	IAction[] defaultCopyPasteCut = new IAction[3];
	private TabbedPropertySheetPage tabbedPropertySheetPage;

	private VarDeclarationListProvider provider;
	private NatTable table;

	@Override
	protected BaseFBType getType() {
		return (BaseFBType) type;
	}

	@Override
	public void createControls(final Composite parent, final TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		createInternalVarsControls(parent);
		this.tabbedPropertySheetPage = tabbedPropertySheetPage;
	}

	public void createInternalVarsControls(final Composite parent) {
		final Composite composite = getWidgetFactory().createComposite(parent);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final AddDeleteReorderListWidget buttons = new AddDeleteReorderListWidget();
		buttons.createControls(composite, getWidgetFactory());

		provider = new VarDeclarationListProvider(this, null);
		final DataLayer dataLayer = new DataLayer(provider);
		final IConfigLabelAccumulator dataLayerLabelAccumulator = dataLayer.getConfigLabelAccumulator();
		dataLayer.setConfigLabelAccumulator((configLabels, columnPosition, rowPosition) -> {
			if (dataLayerLabelAccumulator != null) {
				dataLayerLabelAccumulator.accumulateConfigLabels(configLabels, columnPosition, rowPosition);
			}
			if (columnPosition == VarDeclarationColumnProvider.TYPE) {
				configLabels.addLabel(NatTableWidgetFactory.PROPOSAL_CELL);
			}
			if (columnPosition == VarDeclarationColumnProvider.NAME
					|| columnPosition == VarDeclarationColumnProvider.COMMENT) {
				configLabels.addLabelOnTop(NatTableWidgetFactory.LEFT_ALIGNMENT);
			}
		});
		table = NatTableWidgetFactory.createRowNatTable(composite,
				dataLayer, new VarDeclarationColumnProvider(), IEditableRule.ALWAYS_EDITABLE, typeSelection, this);

		buttons.bindToTableViewer(table, this,
				ref -> new CreateInternalVariableCommand(getType(), getInsertionIndex(), getName(), getDataType()),
				ref -> new DeleteInternalVariableCommand(getType(), (VarDeclaration) ref),
				ref -> new ChangeVariableOrderCommand(getType().getInternalVars(), (VarDeclaration) ref, true),
				ref -> new ChangeVariableOrderCommand(getType().getInternalVars(), (VarDeclaration) ref, false));
	}

	private DataType getDataType() {
		final VarDeclaration varInternal = getLastSelectedVariable();
		return (null != varInternal) ? varInternal.getType() : null;
	}

	private String getName() {
		final VarDeclaration varInternal = getLastSelectedVariable();
		return (null != varInternal) ? varInternal.getName() : null;
	}

	private int getInsertionIndex() {
		final VarDeclaration varInternal = getLastSelectedVariable();
		if (null == varInternal) {
			return getType().getInternalVars().size();
		}
		return getType().getInternalVars().indexOf(varInternal) + 1;
	}

	private VarDeclaration getLastSelectedVariable() {
		final SelectionLayer selectionLayer = NatTableWidgetFactory.getSelectionLayer(table);
		if (selectionLayer != null) {
			final int[] rows = selectionLayer.getFullySelectedRowPositions();
			if (rows.length > 0) {
				final DataLayer dataLayer = (DataLayer) selectionLayer.getUnderlyingLayerByPosition(0, 0);
				final Object rowObject = ((ListDataProvider<?>) dataLayer.getDataProvider())
						.getRowObject(rows[rows.length - 1]);
				return ((VarDeclaration) rowObject);
			}
		}
		return null;
	}

	@Override
	public void aboutToBeShown() {
		// this can be removed once copy/paste for old tables is no longer used
		final IActionBars bars = getActionBars();
		if (bars != null) {
			defaultCopyPasteCut[0] = bars.getGlobalActionHandler(ActionFactory.COPY.getId());
			bars.setGlobalActionHandler(ActionFactory.COPY.getId(), null);
			defaultCopyPasteCut[1] = bars.getGlobalActionHandler(ActionFactory.PASTE.getId());
			bars.setGlobalActionHandler(ActionFactory.PASTE.getId(), null);
			defaultCopyPasteCut[2] = bars.getGlobalActionHandler(ActionFactory.CUT.getId());
			bars.setGlobalActionHandler(ActionFactory.CUT.getId(), null);
			bars.updateActionBars();
		}

		super.aboutToBeShown();
	}

	@Override
	public void aboutToBeHidden() {
		// this can be removed once copy/paste for old tables is no longer used
		final IActionBars bars = getActionBars();
		if (bars != null) {
			bars.setGlobalActionHandler(ActionFactory.COPY.getId(), defaultCopyPasteCut[0]);
			bars.setGlobalActionHandler(ActionFactory.PASTE.getId(), defaultCopyPasteCut[1]);
			bars.setGlobalActionHandler(ActionFactory.CUT.getId(), defaultCopyPasteCut[2]);
			bars.updateActionBars();
		}

		super.aboutToBeHidden();
	}

	private IActionBars getActionBars() {
		if (tabbedPropertySheetPage != null && tabbedPropertySheetPage.getSite() != null) {
			return tabbedPropertySheetPage.getSite().getActionBars();
		}
		return null;
	}

	@Override
	protected Object getInputType(final Object input) {
		return BaseFBFilter.getFBTypeFromSelectedElement(input);
	}

	@Override
	protected void setInputCode() {
		// nothing to do here
	}

	@Override
	public void refresh() {
		table.refresh();
	}

	@Override
	protected void setInputInit() {
		provider.setInput(getType());
		initTypeSelection(getDataTypeLib());
	}

	public Object getEntry(final int index) {
		return getType().getInternalVars().get(index);
	}

	@Override
	public void addEntry(final Object entry, final int index, final CompoundCommand cmd) {
		if (entry instanceof VarDeclaration) {
			final VarDeclaration varEntry = (VarDeclaration) entry;
			cmd.add(new InsertVariableCommand(getType().getInternalVars(), varEntry, index));
		}
	}

	@Override
	public Object removeEntry(final int index, final CompoundCommand cmd) {
		final VarDeclaration entry = (VarDeclaration) getEntry(index);
		cmd.add(new DeleteInternalVariableCommand(getType(), entry));
		return entry;
	}

	@Override
	public void executeCompoundCommand(final CompoundCommand cmd) {
		executeCommand(cmd);
		table.refresh();
	}

	@Override
	public boolean isEditable() {
		return true;
	}
}
