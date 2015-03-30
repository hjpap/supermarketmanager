package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AlterGoods extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Combo combo;
	private Combo combo_1;
	private Combo combo_2;
	private static TableItem item;
	MessageBox m= new MessageBox(getShell(),SWT.None);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void start(TableItem item1) {
		try {
			item=item1;
			Display display = Display.getDefault();
			AlterGoods shell = new AlterGoods(display);
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
	public AlterGoods(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(AlterGoods.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_8 = new Label(this, SWT.NONE);
		label_8.setText("\u4FEE\u6539\u5546\u54C1\u4FE1\u606F");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_8.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_8.setBounds(72, 22, 130, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 618, 73);
		
		Group group = new Group(this, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		group.setText("\u5546\u54C1\u4FE1\u606F");
		group.setBounds(40, 124, 537, 248);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(29, 42, 75, 27);
		label_1.setText("\u5546\u54C1\u7F16\u53F7\uFF1A");
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(29, 91, 75, 27);
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setText("\u5546\u54C1\u5355\u4F4D\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(29, 139, 75, 27);
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("\u5546\u54C1\u5206\u7C7B\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(29, 189, 75, 27);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setText("\u5546\u54C1\u8FDB\u4EF7\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_5.setBounds(275, 42, 75, 27);
		
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("\u96F6\u552E\u4EF7\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_6.setBounds(291, 91, 60, 27);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("\u4F9B\u8D27\u5546\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_7.setBounds(291, 139, 60, 27);
		
		text = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text.setBounds(110, 42, 145, 23);
		text.setText(item.getText(0));
		
		text_1 = new Text(group, SWT.BORDER);
		text_1.setBounds(110, 91, 145, 23);
		text_1.setText(item.getText(1));
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setBounds(356, 42, 145, 23);
		text_2.setText(item.getText(4));
		
		text_3 = new Text(group, SWT.BORDER);
		text_3.setBounds(356, 91, 145, 23);
		text_3.setText(item.getText(5));
		
		combo = new Combo(group, SWT.NONE);
		combo.setBounds(110, 141, 117, 25);
		combo.setText(item.getText(2));
		
		
		combo_1 = new Combo(group, SWT.NONE);
		combo_1.setBounds(110, 189, 117, 25);
		combo_1.setText(item.getText(3));
		
		
		combo_2 = new Combo(group, SWT.NONE);
		combo_2.setBounds(356, 139, 117, 25);
		combo_2.setText(item.getText(7));
		
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//添加单位
			public void widgetSelected(SelectionEvent e) {
				UpdateUnit.start();
				getUnit();
			}
		});
		button.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button.setBounds(236, 139, 41, 27);
		button.setText("+");
		
		Button button_1 = new Button(group, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//商品分类
			public void widgetSelected(SelectionEvent e) {
				UpdateKind.start();
				getKind();
			}
		});
		button_1.setText("+");
		button_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button_1.setBounds(236, 189, 41, 27);
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//添加供货商
			public void widgetSelected(SelectionEvent e) {
				AddGhs.start();
				getGhs();
			}
		});
		button_2.setText("+");
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		button_2.setBounds(479, 139, 41, 27);
		
		Button button_3 = new Button(this, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//确定修改
			public void widgetSelected(SelectionEvent e) {
				if(text_1.getText().isEmpty() || text_2.getText().isEmpty() || text_3.getText().isEmpty() || combo.getText().isEmpty() || combo_1.getText().isEmpty() || combo_2.getText().isEmpty()){
					
					m.setMessage("请将信息输入完整！");
					m.open();
				}else{
					updateSp();
					getShell().dispose();
				}
			}
		});
		button_3.setBounds(160, 378, 80, 27);
		button_3.setText("\u786E\u5B9A\u4FEE\u6539");
		
		Button button_4 = new Button(this, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_4.setBounds(391, 378, 80, 27);
		button_4.setText("\u53D6\u6D88\u4FEE\u6539");
		createContents();
		getKind();
		getUnit();
		getGhs();
	}

	/**
	 * Create contents of the shell.
	 */

	
	
	//修改商品信息    商品信息修改，库存中的商品信息修改
	public void updateSp(){
		JDBCTools jt=new JDBCTools();
		String sql="update goods set sp_name='"+text_1.getText()+"',sp_unit='"+combo.getText()+"',sp_kind='"+combo_1.getText()+"',sp_cost='"+text_2.getText()+"',sp_retail='"+text_3.getText()+"',sp_gonghuoshang='"+combo_2.getText()+"' where sp_code='"+text.getText()+"'";
		String sql1="update kucun set name='"+text_1.getText()+"',unit='"+combo.getText()+"',kind='"+combo_1.getText()+"',cost='"+text_2.getText()+"',retail='"+text_3.getText()+"',gonghuoshang='"+combo_2.getText()+"' where code='"+text.getText()+"'";
		jt.update(sql1);
		jt.update(sql);
		jt.close(null);
	}
	// 获取商品分类
	public void getKind() {
		JDBCTools jt = new JDBCTools();
		String sql = "select count(*) as number from goods_kind";
		String sql1 = "select * from goods_kind";

		ResultSet rs = jt.query(sql);
		ResultSet rs1 = jt.query(sql1);
		try {
			rs.absolute(1);
			int i = rs.getInt("number");
			String[] temp = new String[i];
			while (rs1.next()) {
				temp[i - 1] = rs1.getString("kind");
				i--;
			}
			combo_1.setItems(temp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		jt.close(rs1);
		jt.close(rs);

	}

	// 获取单位
	public void getUnit() {
		JDBCTools jt = new JDBCTools();
		String sql = "select count(*) as number from goods_unit";
		String sql1 = "select * from goods_unit";

		ResultSet rs = jt.query(sql);
		ResultSet rs1 = jt.query(sql1);
		try {
			rs.absolute(1);
			int i = rs.getInt("number");
			String[] temp = new String[i];
			while (rs1.next()) {
				temp[i - 1] = rs1.getString("unit");
				i--;
			}
			combo.setItems(temp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		jt.close(rs1);
		jt.close(rs);

	}

	// 获取供货商
	public void getGhs() {
		JDBCTools jt = new JDBCTools();
		String sql = "select count(*) as number from gonghuoshang";
		String sql1 = "select * from gonghuoshang";

		ResultSet rs = jt.query(sql);
		ResultSet rs1 = jt.query(sql1);
		try {
			rs.absolute(1);
			int i = rs.getInt("number");
			String[] temp = new String[i];
			while (rs1.next()) {
				temp[i - 1] = rs1.getString("name");
				i--;
			}
			combo_2.setItems(temp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		jt.close(rs1);
		jt.close(rs);

	}

	protected void createContents() {
		setText("\u4FEE\u6539\u5546\u54C1\u4FE1\u606F");
		setSize(634, 545);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
