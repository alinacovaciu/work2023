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
import minitema2.Price;

public class PriceDialog {
	private Display display;

	public PriceDialog(Display display) {
		this.display = display;

	}

	public void open() {

		Shell shell = new Shell(display);
		shell.setText("Price dialog");
		shell.setLayout(new GridLayout());
		Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);

		String[] titles = { "Name article", "Name store", "Price" };

		for (String title : titles) {
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(title);
		}

		DatabaseOperations db = new DatabaseOperations();
		List<Price> prices = db.getAllPrices();

		for (Price a : prices) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, a.getArticle().getName());
			item.setText(1, a.getStore().getName());
			item.setText(2, Integer.toString((a.getPrice())));

		}

		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}
		shell.pack();
		shell.open();

	}
}