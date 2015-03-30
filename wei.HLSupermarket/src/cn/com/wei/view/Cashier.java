package cn.com.wei.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.editor.Jiezhang;
import cn.com.wei.editor.Tuihuo;
import cn.com.wei.input.Input;
import cn.com.wei.shell.AlterPersonal;
import cn.com.wei.shell.LoginShell;

public class Cashier extends ViewPart {

	public static final String ID = "cn.com.wei.view.Cashier"; //$NON-NLS-1$

	public Cashier() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		Label label = new Label(container, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(Cashier.class, "/img/computer.png"));
		label.setBounds(10, 82, 128, 134);
		
		Button button = new Button(container, SWT.NONE);
		button.setFocus();
		button.addSelectionListener(new SelectionAdapter() {
			//打开结账--editor
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("结账");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, Jiezhang.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button.setBounds(155, 82, 138, 54);
		button.setText("\u7ED3       \u8D26");
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//交接班 按钮
			public void widgetSelected(SelectionEvent e) {
				IWorkbench iw =  PlatformUI.getWorkbench();//创建一个IWorkBench对象，通过该对象调用方法来重新启动该程序
				   iw.restart();//重新启动该程序

			}
		});
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button_1.setBounds(155, 339, 138, 54);
		button_1.setText("\u4EA4 \u63A5 \u73ED");
		
		Button button_2 = new Button(container, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//个人设置 按钮
			public void widgetSelected(SelectionEvent e) {
				AlterPersonal.start();
			}
		});
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button_2.setBounds(155, 468, 138, 54);
		button_2.setText("\u4E2A\u4EBA\u4FE1\u606F");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(Cashier.class, "/img/huanb.png"));
		label_1.setBounds(10, 295, 128, 134);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(Cashier.class, "/img/bebo_002.png"));
		label_2.setBounds(10, 424, 128, 134);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setBounds(30, 600, 93, 17);
		label_3.setText("\u5F53\u524D\u4F7F\u7528\u7528\u6237\uFF1A");
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setBounds(194, 600, 93, 17);

		createActions();
		initializeToolBar();
		initializeMenu();
		label_4.setText(LoginShell.getLoginUsername());
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setBounds(129, 600, 59, 17);
		label_5.setText(LoginShell.getLimits());
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			//退货 按钮
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				
				input.setName("");
				input.setToolTipText("退货");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, Tuihuo.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		btnNewButton.setBounds(155, 162, 138, 54);
		btnNewButton.setText("\u9000       \u8D27");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.BOLD));
		lblNewLabel.setText("\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014\u2014");
		lblNewLabel.setBounds(0, 260, 407, 17);
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
