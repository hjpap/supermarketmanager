package cn.com.wei.shell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.JDBCTools;

public class DeleteAllgong extends Shell {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			DeleteAllgong shell = new DeleteAllgong(display);
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
	public DeleteAllgong(Display display) {
		super(display, SWT.CLOSE | SWT.RESIZE | SWT.TITLE);
		setImage(SWTResourceManager.getImage(DeleteAllgong.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label = new Label(this, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("微软雅黑", 13, SWT.NORMAL));
		label.setBounds(86, 84, 261, 31);
		label.setText("\u786E\u5B9A\u5220\u9664\u5168\u90E8\u4F9B\u5E94\u5546\u4FE1\u606F\uFF1F");
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//确定删除
			public void widgetSelected(SelectionEvent e) {
				JDBCTools jt=new JDBCTools();
				String sql="delete from gonghuoshang";
				jt.update(sql);
				jt.close(null);
				getShell().dispose();
			}
		});
		button.setBounds(98, 160, 80, 27);
		button.setText("\u786E\u5B9A");
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//取消
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(237, 160, 80, 27);
		button_1.setText("\u53D6\u6D88");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u8B66\u544A");
		setSize(429, 286);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
