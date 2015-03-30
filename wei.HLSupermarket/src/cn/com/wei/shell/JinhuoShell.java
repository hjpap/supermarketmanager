package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import cn.com.wei.tools.DateTools;
import cn.com.wei.tools.IsNumerberTools;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.DateTime;

public class JinhuoShell extends Shell {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Combo combo_1;
	private Combo combo;
	private Combo combo_2;
	private DateTime dateTime;
	private DateTime dateTime_1;
	
	private static TableItem item;
	private Text text_7;
	private static String billcode;
	private Text text_8;
	MessageBox m=new MessageBox(getShell(),SWT.ICON_INFORMATION);

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1,String code) {
		item=item1;
		billcode=code;
		
		try {
			Display display = Display.getDefault();
			JinhuoShell shell = new JinhuoShell(display);
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
	public JinhuoShell(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(JinhuoShell.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Group group = new Group(this, SWT.NONE);
		group.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		group.setText("\u5546\u54C1\u8BE6\u60C5");
		group.setBounds(10, 81, 574, 444);
		
		Label label_1 = new Label(group, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_1.setBounds(37, 41, 80, 24);
		label_1.setText("\u5546\u54C1\u7F16\u53F7\uFF1A");
		
		text = new Text(group, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text.setBounds(137, 38, 124, 29);
		text.setText(item.getText(0));
		
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_2.setBounds(37, 108, 80, 24);
		label_2.setText("\u5546\u54C1\u540D\u79F0\uFF1A");
		
		text_1 = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		text_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_1.setBounds(137, 105, 124, 29);
		text_1.setText(item.getText(1));
		
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_3.setBounds(37, 168, 80, 24);
		label_3.setText("\u5546\u54C1\u5355\u4F4D\uFF1A");
		
		combo = new Combo(group, SWT.READ_ONLY);
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo.setBounds(137, 165, 124, 29);
		
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_4.setBounds(37, 227, 80, 24);
		label_4.setText("\u5546\u54C1\u5206\u7C7B\uFF1A");
		
		combo_1 = new Combo(group, SWT.READ_ONLY);
		combo_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo_1.setBounds(137, 224, 124, 29);
		
		Label label_5 = new Label(group, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_5.setBounds(37, 285, 80, 24);
		label_5.setText("   \u4F9B\u8D27\u5546\uFF1A");
		
		combo_2 = new Combo(group, SWT.READ_ONLY);
		combo_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		combo_2.setBounds(137, 282, 124, 29);
		combo_2.setText(item.getText(6));
		Label label_6 = new Label(group, SWT.NONE);
		label_6.setText("  \u62A5\u8B66\u6570\uFF1A");
		label_6.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_6.setBounds(318, 41, 80, 24);
		
		text_2 = new Text(group, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_2.setBounds(405, 38, 124, 27);
		
		Label label_7 = new Label(group, SWT.NONE);
		label_7.setText("  \u8FDB\u8D27\u4EF7\uFF1A");
		label_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_7.setBounds(318, 108, 80, 24);
		
		text_3 = new Text(group, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_3.setBounds(405, 105, 124, 29);
		text_3.setText(item.getText(3));
		
		Label label_8 = new Label(group, SWT.NONE);
		label_8.setText("  \u96F6\u552E\u4EF7\uFF1A");
		label_8.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_8.setBounds(318, 168, 80, 24);
		
		text_4 = new Text(group, SWT.BORDER);
		text_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_4.setBounds(405, 163, 124, 29);
		text_4.setText(item.getText(4));
		
		Label label_9 = new Label(group, SWT.NONE);
		label_9.setText("\u8FDB\u8D27\u6570\u91CF\uFF1A");
		label_9.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_9.setBounds(181, 332, 80, 24);
		
		Button button = new Button(group, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//确定按钮
			public void widgetSelected(SelectionEvent e) {
				if(text.getText()!=""&&text_1.getText()!=""&&text_2.getText()!=""&&text_3.getText()!=""&&text_4.getText()!=""&&text_7.getText()!=""&&combo.getText()!=""&&combo_1.getText()!=""&&combo_2.getText()!=""){
					//把进货商品信息添加到库存
					addKucun();
					//把进货商品添加到商品信息表
					
					text.setText("");
					text_1.setText("");
					text_2.setText("");
					text_3.setText("");
					text_4.setText("");
					text_7.setText("");
					combo_1.select(-1);
					combo_2.select(-1);
					combo.select(-1);
					
					
				}else{
					m.setMessage("请输入完整信息!");
					m.open();
				}
			}
		});
		button.setBounds(163, 392, 93, 38);
		button.setText("\u786E\u5B9A");
		
		Button button_1 = new Button(group, SWT.NONE);
		
		getKind();
		getGhs();
		getUnit();
		
		button_1.addSelectionListener(new SelectionAdapter() {
			//取消
			public void widgetSelected(SelectionEvent e) {
				getShell().dispose();
			}
		});
		button_1.setBounds(302, 392, 96, 38);
		button_1.setText("\u53D6\u6D88");
		
		Button button_2 = new Button(group, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//添加单位
			public void widgetSelected(SelectionEvent e) {
				UpdateUnit.start();
			}
		});
		button_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button_2.setBounds(274, 166, 38, 27);
		button_2.setText("+");
		
		Button button_3 = new Button(group, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//添加分类
			public void widgetSelected(SelectionEvent e) {
				UpdateKind.start();
			}
		});
		button_3.setText("+");
		button_3.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button_3.setBounds(274, 224, 38, 27);
		
		Button button_4 = new Button(group, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			//添加供货商
			public void widgetSelected(SelectionEvent e) {
				AddGhs.start();
			}
		});
		button_4.setText("+");
		button_4.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		button_4.setBounds(274, 285, 38, 27);
		
		Label label_10 = new Label(group, SWT.NONE);
		label_10.setText("\u751F\u4EA7\u65E5\u671F\uFF1A");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_10.setBounds(318, 227, 80, 24);
		
		Label label_11 = new Label(group, SWT.NONE);
		label_11.setText("\u8FC7\u671F\u65E5\u671F\uFF1A");
		label_11.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		label_11.setBounds(318, 285, 80, 24);
		
		text_7 = new Text(group, SWT.BORDER);
		text_7.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		text_7.setBounds(267, 329, 124, 29);
		
		dateTime = new DateTime(group, SWT.BORDER | SWT.DROP_DOWN);
		dateTime.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		dateTime.setBounds(405, 224, 124, 29);
		
		dateTime_1 = new DateTime(group, SWT.BORDER | SWT.DROP_DOWN);
		dateTime_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		dateTime_1.setBounds(405, 282, 124, 29);
		
		Label label_13 = new Label(this, SWT.NONE);
		label_13.setText("\u8FDB\u8D27\u8BE6\u60C5");
		label_13.setFont(SWTResourceManager.getFont("微软雅黑", 19, SWT.BOLD));
		label_13.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_13.setBounds(57, 23, 108, 33);
		
		Label label_12 = new Label(this, SWT.NONE);
		label_12.setText("\u8FDB\u8D27\u6D41\u6C34\u53F7\uFF1A");
		label_12.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_12.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_12.setBounds(337, 52, 72, 17);
		
		text_8 = new Text(this, SWT.READ_ONLY);
		text_8.setText(billcode);
		text_8.setBackground(SWTResourceManager.getColor(51, 204, 255));
		text_8.setBounds(409, 52, 156, 23);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 594, 75);
		createContents();
		show();//显示进货的相关信息
	}

	/**
	 * Create contents of the shell.
	 */
	//进货商品添加到库存和商品信息
public void addKucun(){
		
		if(IsNumerberTools.isNumeric(text_2.getText())&&IsNumerberTools.isNumeric(text_3.getText())&&IsNumerberTools.isNumeric(text_4.getText())&&IsNumerberTools.isNumeric(text_7.getText())){
			//添加到库存
			JDBCTools jt=new JDBCTools();
			String sql="INSERT INTO kucun(billcode,CODE,NAME,unit,kind,gonghuoshang,cost,retail,prodate,baddate,DATE,dangerous,jhcount,kCOUNT,state,people) VALUES('"+text_8.getText()+"','"+text.getText()+"','"+text_1.getText()+"','"+combo.getText()+"','"+combo_1.getText()+"','"+combo_2.getText()+"',"+text_3.getText()+","+text_4.getText()+",'"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"','"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-"+dateTime_1.getDay()+"','"+DateTools.getDateTime()+"',"+text_2.getText()+","+text_7.getText()+","+text_7.getText()+",'正常','"+LoginShell.getLoginUsername()+"')";
			//System.out.println(sql);
		jt.update(sql);
		
		//添加到商品信息
		String sql1="select * from kucun where code='"+text.getText()+"'";
		ResultSet rs1=jt.query(sql1);
		int kucun=0;
		try {
			while(rs1.next()){
				kucun=kucun+rs1.getInt("kcount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql2="update goods set sp_cost='"+text_3.getText()+"',sp_retail='"+text_4.getText()+"',sp_gonghuoshang='"+combo_2.getText()+"',sp_kucun='"+kucun+"' where sp_code='"+text.getText()+"'";
		jt.update(sql2);
		
		jt.close(rs1);
		m.setMessage("进货成功！");
		m.open();
		}else{
			m.setMessage("请正确输入价格或数量！(应为数字)");
			m.open();
		}
		
	}
	
	
	//显示具体信息
	public void  show(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from goods where sp_code='"+text.getText()+"'";
		ResultSet rs=jt.query(sql);
		try {
			while(rs.next()){
				combo.setText(rs.getString("sp_unit"));
				combo_1.setText(rs.getString("sp_kind"));
				combo_2.setText(rs.getString("sp_gonghuoshang"));
				text_2.setText(rs.getString("sp_dangerous"));
				text_3.setText(rs.getString("sp_cost"));
				text_4.setText(rs.getString("sp_retail"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jt.close(rs);	
		
	}
	
	//获取分类
	public void getKind(){
		JDBCTools jt=new JDBCTools();
		String sql="select count(*) as number from goods_kind";
		String sql1="select * from goods_kind";
		
		ResultSet rs=jt.query(sql);
		ResultSet rs1=jt.query(sql1);
		try {
			rs.absolute(1);
			int i=rs.getInt("number");
			String[] temp=new String[i];
			while(rs1.next()){
				temp[i-1]=rs1.getString("kind");
				i--;
			}
			combo_1.setItems(temp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jt.close(rs1);
		jt.close(rs);	
		
	}
	
	//获取单位
	public void getUnit(){
		JDBCTools jt=new JDBCTools();
		String sql="select count(*) as number from goods_unit";
		String sql1="select * from goods_unit";
		
		ResultSet rs=jt.query(sql);
		ResultSet rs1=jt.query(sql1);
		try {
			rs.absolute(1);
			int i=rs.getInt("number");
			String[] temp=new String[i];
			while(rs1.next()){
				temp[i-1]=rs1.getString("unit");
				i--;
			}
			combo.setItems(temp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jt.close(rs1);
		jt.close(rs);	
		
	}
	
	//获取供货商
	public void getGhs(){
		JDBCTools jt=new JDBCTools();
		String sql="select count(*) as number from gonghuoshang";
		String sql1="select * from gonghuoshang";
		
		ResultSet rs=jt.query(sql);
		ResultSet rs1=jt.query(sql1);
		try {
			rs.absolute(1);
			int i=rs.getInt("number");
			String[] temp=new String[i];
			while(rs1.next()){
				temp[i-1]=rs1.getString("name");
				i--;
			}
			combo_2.setItems(temp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jt.close(rs1);
		jt.close(rs);	
		
	}
	
	
	//添加到进货表
	public void addJhb(){
		
	}
	protected void createContents() {
		setText("\u8FDB\u8D27\u8BE6\u60C5");
		setSize(610, 584);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
