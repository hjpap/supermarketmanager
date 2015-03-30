package cn.com.wei.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.JDBCTools;

public class AlterLimits extends Shell {
	private Text text;
	private Text text_1;
	private static TableItem item;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		item=item1;
		try {
			Display display = Display.getDefault();
			AlterLimits shell = new AlterLimits(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public AlterLimits(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(AlterLimits.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u4FEE\u6539\u6743\u9650");
		label_4.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 15, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_4.setBounds(175, 22, 92, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 504, 73);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		label_1.setBounds(114, 102, 52, 23);
		label_1.setText("\u59D3\u540D\uFF1A");
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u7528\u6237\u540D\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		label_2.setBounds(114, 149, 65, 23);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("\u6743\u9650\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		label_3.setBounds(114, 199, 65, 23);
		
		text = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		text.setBounds(200, 99, 128, 26);
		text.setText(item.getText(3));
		
		text_1 = new Text(this, SWT.BORDER | SWT.READ_ONLY);
		text_1.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		text_1.setBounds(200, 146, 128, 26);
		text_1.setText(item.getText(1));
		
		final Combo combo = new Combo(this, SWT.NONE);
		combo.setItems(new String[] {"\u6536\u94F6\u5458", "\u7BA1\u7406\u5458", "\u7ECF\u7406"});
		combo.setFont(SWTResourceManager.getFont("Î¢ÈíÑÅºÚ", 11, SWT.NORMAL));
		combo.setBounds(200, 196, 128, 25);
		combo.setText(item.getText(2));
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//È·¶¨ÐÞ¸Ä
			public void widgetSelected(SelectionEvent e) {
				JDBCTools jt=new JDBCTools();
				String sql="update user_info set limits='"+combo.getText()+"' where user='"+text_1.getText()+"'";
				String sql1="update staff set limits='"+combo.getText()+"' where user_name='"+text_1.getText()+"'";
				jt.update(sql);
				jt.update(sql1);
				jt.close(null);
				getShell().dispose();
			}
		});
		button.setBounds(114, 245, 80, 27);
		button.setText("\u786E\u5B9A");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(258, 245, 80, 27);
		button_1.setText("\u53D6\u6D88");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u4FEE\u6539\u6743\u9650");
		setSize(460, 332);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
