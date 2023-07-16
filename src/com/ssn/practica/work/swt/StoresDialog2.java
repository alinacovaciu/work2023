package com.ssn.practica.work.swt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import minitema2.DatabaseOperations;
import minitema2.Store;

public class StoresDialog2 {
	private Display display;

	public StoresDialog2(Display display) {
		this.display = display;

	}

	public void open() {

		Shell shell = new Shell(display);
		shell.setText("Stores dialog");
		shell.setLayout(new GridLayout());
		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);

		String[] titles = { "Name" };
		for (String title : titles) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(title);
		}

		DatabaseOperations db = new DatabaseOperations();
		List<Store> stores = db.getAllStores();

		for (Store a : stores) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, a.getName());

		}

		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}
		shell.pack();
		shell.open();

	}
}