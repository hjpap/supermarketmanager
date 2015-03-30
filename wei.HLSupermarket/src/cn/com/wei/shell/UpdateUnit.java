package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class UpdateUnit extends Shell {
	private Table table;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			UpdateUnit shell = new UpdateUnit(display);
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
	public UpdateUnit(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(UpdateUnit.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setText("\u5546\u54C1\u5355\u4F4D");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_2.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_2.setBounds(144, 25, 92, 27);

		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 431, 76);

		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(114, 101, 148, 239);

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(143);
		tableColumn.setText("\u5546\u54C1\u5206\u7C7B");
		show();
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u6DFB\u52A0\u65B0\u5206\u7C7B\uFF1A");
		label_1.setBounds(80, 356, 80, 17);

		text = new Text(this, SWT.BORDER);
		text.setBounds(166, 356, 131, 23);

		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//添加
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()!=""){
					add();
					text.setText("");
				}
			}
		});
		button.setText("\u6DFB\u52A0");
		button.setBounds(58, 405, 80, 27);

		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			// 删除
			public void widgetSelected(SelectionEvent e) {
				delete();
			}
		});
		button_1.setText("\u5220\u9664");
		button_1.setBounds(156, 405, 80, 27);

		Button button_2 = new Button(this, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//取消
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_2.setText("\u53D6\u6D88");
		button_2.setBounds(256, 405, 80, 27);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	// 显示单位
	public void show() {
		JDBCTools jt = new JDBCTools();
		String sql = "select * from goods_unit";
		ResultSet rs = jt.query(sql);

		try {
			table.removeAll();
			while (rs.next()) {

				TableItem tableItem = new TableItem(table, SWT.None);
				tableItem.setText(rs.getString("unit"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);

	}

	
	
	// 添加
	public void add(){
		JDBCTools jt=new JDBCTools();
		String sql="insert goods_unit (unit) values('"+text.getText()+"')";
		jt.update(sql);
		jt.close(null);
		
		this.show();
		
	}
	
	
	// 多条删除
	public void delete() {
		JDBCTools jt = new JDBCTools();
		String s = "";

		int[] t = table.getSelectionIndices();

		TableItem[] items = table.getSelection();

		for (int i = 0; i < items.length; i++) {

			s = table.getSelection()[i].getText(0);

			String sql = "delete from goods_unit where unit='" + s + "'";

			jt.update(sql);

		}
		table.remove(t);
		jt.close(null);
	}

	protected void createContents() {
		setText("\u5546\u54C1\u5355\u4F4D");
		setSize(395, 522);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
