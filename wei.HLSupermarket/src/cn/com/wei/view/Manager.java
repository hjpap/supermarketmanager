package cn.com.wei.view;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.editor.Grounding;
import cn.com.wei.editor.Jinhuo;
import cn.com.wei.editor.QueryGonghuoshang;
import cn.com.wei.editor.QueryJinhuo;
import cn.com.wei.editor.QueryReturns;
import cn.com.wei.editor.QuerySaleBill;
import cn.com.wei.editor.QueryVip;
import cn.com.wei.editor.SetVip;
import cn.com.wei.editor.ShangpinXinxi;
import cn.com.wei.editor.Sunhao;
import cn.com.wei.input.Input;
import cn.com.wei.shell.Notice;

import org.eclipse.swt.widgets.Label;

public class Manager extends ViewPart {

	public static final String ID = "cn.com.wei.view.Manager"; //$NON-NLS-1$

	public Manager() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		ExpandBar expandBar = new ExpandBar(container, SWT.NONE);
		expandBar.setLocation(0, 0);
		expandBar.setSize(348, 710);
		expandBar.setBackground(SWTResourceManager.getColor(165, 42, 42));
		
		ExpandItem expandItem = new ExpandItem(expandBar, SWT.NONE);
		expandItem.setExpanded(true);
		expandItem.setText("\u8FDB\u8D27\u7BA1\u7406");
		
		Composite composite = new Composite(expandBar, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		expandItem.setControl(composite);
		expandItem.setHeight(155);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			//进货查询
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("进货记录");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, QueryJinhuo.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(185, 55, 143, 39);
		btnNewButton.setText("\u8FDB\u8D27\u8BB0\u5F55");
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		button.addSelectionListener(new SelectionAdapter() {
			//进货 按钮
			Input input =new Input();
			public void widgetSelected(SelectionEvent e) {
				
				input.setName("");
				input.setToolTipText("进货");
				
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, Jinhuo.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button.setBounds(185, 10, 143, 39);
		button.setText("\u8FDB\u8D27\u5165\u5E93");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			//供货商查询 按钮
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("供货商查询");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, QueryGonghuoshang.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(185, 100, 143, 39);
		btnNewButton_1.setText("\u4F9B\u8D27\u5546\u67E5\u8BE2");
		
		Label label = new Label(composite, SWT.NONE);
		label.setImage(SWTResourceManager.getImage(Manager.class, "/img/jhjh (1).png"));
		label.setBounds(39, 10, 112, 122);
		
		ExpandItem expandItem_3 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_3.setExpanded(true);
		expandItem_3.setText("\u5546\u54C1\u7BA1\u7406");
		
		Composite composite_3 = new Composite(expandBar, SWT.NONE);
		composite_3.setBackgroundMode(SWT.INHERIT_FORCE);
		composite_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		expandItem_3.setControl(composite_3);
		
		Button button_5 = new Button(composite_3, SWT.NONE);
		button_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		button_5.addSelectionListener(new SelectionAdapter() {
			//商品管理--商品信息
			Input input=new Input();
			
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("商品信息");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, ShangpinXinxi.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button_5.setText("\u5546\u54C1\u4FE1\u606F");
		button_5.setBounds(185, 10, 143, 39);
		
		Button button_6 = new Button(composite_3, SWT.NONE);
		button_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		button_6.addSelectionListener(new SelectionAdapter() {
			//损耗
			Input input =new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("商品损耗");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, Sunhao.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button_6.setText("\u635F\u8017");
		button_6.setBounds(185, 100, 143, 39);
		
		Button button_7 = new Button(composite_3, SWT.NONE);
		button_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		button_7.addSelectionListener(new SelectionAdapter() {
			//上架信息
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("上架信息");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, Grounding.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button_7.setText("\u4E0A\u67B6\u4FE1\u606F");
		button_7.setBounds(185, 55, 143, 39);
		
		Label label_2 = new Label(composite_3, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(Manager.class, "/img/spsp.png"));
		label_2.setBounds(33, 10, 116, 134);
		expandItem_3.setHeight(155);
		
		ExpandItem expandItem_1 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_1.setExpanded(true);
		expandItem_1.setText("\u9500\u552E\u7BA1\u7406");
		
		Composite composite_1 = new Composite(expandBar, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_FORCE);
		composite_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		expandItem_1.setControl(composite_1);
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		button_1.addSelectionListener(new SelectionAdapter() {
			//销售查询
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, QuerySaleBill.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setText("\u9500\u552E\u67E5\u8BE2");
		button_1.setBounds(185, 20, 143, 39);
		
		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		button_2.addSelectionListener(new SelectionAdapter() {
			//退货查询
			Input input =new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("退货查询");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, QueryReturns.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		button_2.setText("\u9000\u8D27\u67E5\u8BE2");
		button_2.setBounds(185, 65, 143, 39);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(Manager.class, "/img/jhjh (2).png"));
		label_1.setBounds(30, 0, 129, 119);
		expandItem_1.setHeight(120);
		
		ExpandItem expandItem_2 = new ExpandItem(expandBar, SWT.NONE);
		expandItem_2.setExpanded(true);
		expandItem_2.setText("\u4F1A\u5458\u7BA1\u7406");
		
		Composite composite_2 = new Composite(expandBar, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_FORCE);
		composite_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		expandItem_2.setControl(composite_2);
		
		Button button_3 = new Button(composite_2, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		button_3.addSelectionListener(new SelectionAdapter() {
			//会员信息
			Input input=new Input();
			
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("会员信息");
				
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, QueryVip.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_3.setBounds(185, 20, 143, 39);
		button_3.setText("\u4F1A\u5458\u4FE1\u606F");
		
		Button button_4 = new Button(composite_2, SWT.NONE);
		button_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.BOLD));
		button_4.addSelectionListener(new SelectionAdapter() {
			//会员设置
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("会员设置");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, SetVip.ID);
				} catch (PartInitException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_4.setBounds(185, 65, 143, 39);
		button_4.setText("\u4F1A\u5458\u8BBE\u7F6E");
		
		Label label_3 = new Label(composite_2, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(Manager.class, "/img/vip.png"));
		label_3.setBounds(40, 0, 114, 119);
		expandItem_2.setHeight(120);
		
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
