package com.ssn.practica.work.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class SWTApplication {

	public static void main(String[] args) {
		SWTApplication app = new SWTApplication();
		app.run();
	}

	private void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Application");

		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);

		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("Administration");

		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);

		MenuItem item = new MenuItem(submenu, SWT.PUSH);
		item.addListener(SWT.Selection, e -> {
			new ArticlesDialog(display).open();
			;
		});
		item.setText("Articles");

		MenuItem item2 = new MenuItem(submenu, SWT.PUSH);
		item2.addListener(SWT.Selection, e -> {
			new StoresDialog2(display).open();
			;
		});
		item2.setText("Stores");

		MenuItem item3 = new MenuItem(submenu, SWT.PUSH);
		item3.addListener(SWT.Selection, e -> {
			new PriceDialog(display).open();
			;
		});
		item3.setText("Prices");

		shell.setSize(200, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}