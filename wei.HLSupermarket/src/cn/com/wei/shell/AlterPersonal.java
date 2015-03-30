package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.JDBCTools;

public class AlterPersonal extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_5;
	private Text yuan;
	private Text xin;
	private Text text_4;
	MessageBox m = new MessageBox(getShell(), SWT.ICON_INFORMATION);
	private Text text_8;
	private Text chongfuxin;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			AlterPersonal shell = new AlterPersonal(display);
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
	 * 
	 * @param display
	 */
	public AlterPersonal(Display display) {
		super(display, SWT.SHELL_TRIM);
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		setImage(SWTResourceManager
				.getImage(AlterPersonal.class, "/img/22.png"));

		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setBackgroundMode(SWT.INHERIT_FORCE);
		tabFolder.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		tabFolder.setBounds(10, 10, 414, 305);

		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u4E2A\u4EBA\u4FE1\u606F");

		Group group = new Group(tabFolder, SWT.NONE);
		tabItem.setControl(group);

		Label label = new Label(group, SWT.NONE);
		label.setBounds(49, 30, 41, 17);
		label.setText("\u59D3\u540D\uFF1A");

		text = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		text.setBounds(90, 24, 170, 23);

		Label label_1 = new Label(group, SWT.NONE);
		label_1.setBounds(49, 69, 36, 17);
		label_1.setText("\u6027\u522B\uFF1A");

		text_1 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		text_1.setBounds(90, 111, 170, 23);

		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBounds(49, 114, 41, 17);
		label_2.setText("\u5E74\u9F84\uFF1A");

		text_2 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		text_2.setBounds(90, 148, 170, 23);

		Label label_3 = new Label(group, SWT.NONE);
		label_3.setBounds(29, 151, 61, 17);
		label_3.setText("\u8054\u7CFB\u65B9\u5F0F\uFF1A");

		text_3 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		text_3.setBounds(90, 193, 170, 23);

		Label label_4 = new Label(group, SWT.NONE);
		label_4.setBounds(29, 196, 61, 17);
		label_4.setText("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");

		Button button_2 = new Button(group, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			// 取消 按钮
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_2.setBounds(301, 226, 80, 27);
		button_2.setText("\u5173\u95ED");

		Label label_8 = new Label(group, SWT.NONE);
		label_8.setBounds(49, 234, 41, 17);
		label_8.setText("\u4E1A\u7EE9\uFF1A");

		text_4 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_4.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		text_4.setBounds(90, 228, 170, 23);

		text_8 = new Text(group, SWT.BORDER);
		text_8.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		text_8.setBounds(90, 69, 73, 23);

		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u4FEE\u6539\u5BC6\u7801");

		Group group_1 = new Group(tabFolder, SWT.NONE);
		group_1.setBackgroundMode(SWT.INHERIT_FORCE);
		tabItem_1.setControl(group_1);

		text_5 = new Text(group_1, SWT.BORDER | SWT.READ_ONLY);
		text_5.setBounds(156, 28, 134, 23);
		text_5.setText(LoginShell.getLoginUsername());

		Label label_5 = new Label(group_1, SWT.NONE);
		label_5.setBounds(102, 31, 48, 17);
		label_5.setText("\u7528\u6237\u540D\uFF1A");

		Label label_6 = new Label(group_1, SWT.NONE);
		label_6.setBounds(102, 76, 48, 17);
		label_6.setText("\u539F\u5BC6\u7801\uFF1A");

		yuan = new Text(group_1, SWT.BORDER | SWT.PASSWORD);
		yuan.setBounds(156, 73, 134, 23);

		Label label_7 = new Label(group_1, SWT.NONE);
		label_7.setBounds(102, 124, 48, 17);
		label_7.setText("\u65B0\u5BC6\u7801\uFF1A");

		xin = new Text(group_1, SWT.BORDER | SWT.PASSWORD);
		xin.setBounds(156, 121, 134, 23);

		Button button_1 = new Button(group_1, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			// 修改密码
			public void widgetSelected(SelectionEvent e) {
				if (yuan.getText().equals("") || xin.getText().equals("")
						|| chongfuxin.getText().equals("")) {
					m.setMessage("请输入完整信息！");
					m.open();
				} else {
					if (chongfuxin.getText().equals(xin.getText())) {

						JDBCTools jt = new JDBCTools();
						String sql = "select count(*) as number from user_info where user='"
								+ LoginShell.getLoginUsername()
								+ "' and password='" + yuan.getText() + "'";
						ResultSet rs = jt.query(sql);
						try {
							rs.absolute(1);
							if (rs.getString("number").equals("1")) {

								String sql1 = "update user_info set password='"
										+ xin.getText() + "' where user='"
										+ LoginShell.getLoginUsername() + "'";
								jt.update(sql1);
								jt.close(rs);
								getShell().dispose();

							} else {
								m.setMessage("原密码输入错误，请重新输入！");
								m.open();
								yuan.setText("");
								xin.setText("");
								chongfuxin.setText("");
								yuan.setFocus();

							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						jt.close(rs);
					} else {
						m.setMessage("输入的新密码不相同!");
						m.open();
						yuan.setText("");
						xin.setText("");
						chongfuxin.setText("");
						yuan.setFocus();

					}

				}
			}
		});
		button_1.setBounds(102, 218, 80, 27);
		button_1.setText("\u786E\u5B9A\u4FEE\u6539");

		Button button_3 = new Button(group_1, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			// 取消按钮
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_3.setBounds(241, 218, 80, 27);
		button_3.setText("\u53D6\u6D88");

		Label label_9 = new Label(group_1, SWT.NONE);
		label_9.setBounds(78, 171, 72, 17);
		label_9.setText("\u786E\u8BA4\u65B0\u5BC6\u7801\uFF1A");

		chongfuxin = new Text(group_1, SWT.BORDER | SWT.PASSWORD);
		chongfuxin.setBounds(156, 168, 134, 23);
		createContents();

		{
			// 打开界面显示个人信息
			JDBCTools jt = new JDBCTools();
			String sql = "select * from staff where user_name='"
					+ LoginShell.getLoginUsername() + "'";
			ResultSet rs = jt.query(sql);
			try {
				if (rs.absolute(1)) {
					text.setText(rs.getString("name"));
					text_8.setText(rs.getString("sex"));
					text_1.setText(rs.getString("age"));
					text_2.setText(rs.getString("tel"));
					text_3.setText(rs.getString("idcard"));
					text_4.setText(rs.getString("income"));
					jt.close(rs);

				} else {
					text.setText("无");
					text_1.setText("无");
					text_2.setText("无");
					text_3.setText("无");
					text_4.setText("无");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("\u4E2A\u4EBA\u4FE1\u606F");
		setSize(450, 363);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
