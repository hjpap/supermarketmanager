package cn.com.wei.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.editor.Employee;
import cn.com.wei.editor.Tongji;
import cn.com.wei.input.Input;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

public class Boss extends ViewPart {

	public static final String ID = "cn.com.wei.view.Boss"; //$NON-NLS-1$

	public Boss() {
		setTitleImage(SWTResourceManager.getImage(Boss.class, "/img/22.png"));
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		Button button = new Button(container, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button.addSelectionListener(new SelectionAdapter() {
			//员工
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, Employee.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(161, 186, 113, 52);
		button.setText("\u5458\u5DE5\u7BA1\u7406");
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button_1.addSelectionListener(new SelectionAdapter() {
			//营业统计
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, Tongji.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(161, 401, 113, 52);
		button_1.setText("\u8425\u4E1A\u7EDF\u8BA1");
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setImage(SWTResourceManager.getImage(Boss.class, "/img/employeeicon.png"));
		label.setBounds(27, 157, 128, 134);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(Boss.class, "/img/tongjiicon.png"));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_1.setBounds(27, 355, 128, 134);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
