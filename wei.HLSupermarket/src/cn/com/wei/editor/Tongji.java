package cn.com.wei.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.input.Input;
import cn.com.wei.shell.DayMonthStatistics;
import cn.com.wei.shell.KucunStatistics;

public class Tongji extends EditorPart {

	public static final String ID = "cn.com.wei.editor.Tongji"; //$NON-NLS-1$

	public Tongji() {
		setTitleImage(SWTResourceManager.getImage(Tongji.class, "/img/22.png"));
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_8 = new Label(container, SWT.NONE);
		label_8.setText("\u8425\u4E1A\u7EDF\u8BA1");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_8.setBounds(170, 40, 153, 42);
		
		Label label_7 = new Label(container, SWT.NONE);
		label_7.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_7.setImage(SWTResourceManager.getImage(Tongji.class, "/img/tongji.png"));
		label_7.setBounds(72, 23, 61, 72);
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 112);
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//日月报表
			public void widgetSelected(SelectionEvent e) {
				DayMonthStatistics.start();
			}
		});
		button.setBounds(668, 546, 80, 27);
		button.setText("\u65E5/\u6708\u62A5\u8868");
		
		Button button_1 = new Button(container, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//库存统计
			public void widgetSelected(SelectionEvent e) {
				KucunStatistics.start();
			}
		});
		button_1.setBounds(663, 281, 80, 27);
		button_1.setText("\u5E93\u5B58\u7EDF\u8BA1");
		
		Button button_2 = new Button(container, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//进货明细
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("进货明细查询");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, QueryJinhuo.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setBounds(382, 282, 80, 27);
		button_2.setText("\u8FDB\u8D27\u660E\u7EC6");
		
		Button button_3 = new Button(container, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//销售明细
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("销售明细查询");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, QuerySaleBill.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_3.setBounds(84, 285, 80, 27);
		button_3.setText("\u9500\u552E\u660E\u7EC6");
		
		Button button_4 = new Button(container, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			//退货明细
			Input input =new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("退货明细查询");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, QueryReturns.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_4.setBounds(84, 546, 80, 27);
		button_4.setText("\u9000\u8D27\u660E\u7EC6");
		
		Button button_5 = new Button(container, SWT.NONE);
		button_5.addSelectionListener(new SelectionAdapter() {
			//损耗
			Input input=new Input();
			public void widgetSelected(SelectionEvent e) {
				input.setName("");
				input.setToolTipText("损耗查询");
				try {
					getSite().getWorkbenchWindow().getActivePage().openEditor(input, Sunhao.ID);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_5.setBounds(382, 546, 80, 27);
		button_5.setText("\u635F\u8017");
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setImage(SWTResourceManager.getImage(Tongji.class, "/img/jinhuoTitle.png"));
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_1.setBounds(382, 186, 86, 90);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setImage(SWTResourceManager.getImage(Tongji.class, "/img/xiaoshouTitle.png"));
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_2.setBounds(84, 186, 98, 93);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(Tongji.class, "/img/sunhaoTitle.png"));
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_3.setBounds(84, 446, 99, 94);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(Tongji.class, "/img/sunhaoTitle.png"));
		label_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_4.setBounds(382, 446, 99, 94);
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setImage(SWTResourceManager.getImage(Tongji.class, "/img/cxTitle.png"));
		label_5.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_5.setBounds(663, 189, 85, 86);
		
		Label label_6 = new Label(container, SWT.NONE);
		label_6.setImage(SWTResourceManager.getImage(Tongji.class, "/img/huiytitile.png"));
		label_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		label_6.setBounds(657, 467, 107, 73);

	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		this.setSite(site);
		this.setInput(input);
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
}
