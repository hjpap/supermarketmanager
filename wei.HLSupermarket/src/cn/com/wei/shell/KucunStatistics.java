package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class KucunStatistics extends Shell {
	private Table table;
	private Text text;
	private Combo combo;
	private Text countt;
	private Text shourut;
	private Text costt;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			KucunStatistics shell = new KucunStatistics(display);
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
	public KucunStatistics(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(KucunStatistics.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u5E93\u5B58\u7EDF\u8BA1");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_5.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_5.setBounds(70, 21, 92, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 772, 73);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
		table.setBounds(0, 167, 772, 464);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(74);
		tableColumn.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(75);
		tableColumn_1.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(76);
		tableColumn_2.setText("\u5546\u54C1\u5355\u4F4D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(78);
		tableColumn_3.setText("\u5546\u54C1\u5206\u7C7B");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(71);
		tableColumn_4.setText("\u8FDB\u4EF7");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(74);
		tableColumn_5.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(71);
		tableColumn_6.setText("\u5E93\u5B58");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(71);
		tableColumn_7.setText("\u62A5\u8B66\u6570");
		
		TableColumn tableColumn_8 = new TableColumn(table, SWT.NONE);
		tableColumn_8.setWidth(72);
		tableColumn_8.setText("\u4F9B\u8D27\u5546");
		
		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(79);
		tableColumn_9.setText("\u9884\u8BA1\u6536\u5165");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		lblNewLabel.setBounds(10, 82, 75, 19);
		lblNewLabel.setText("\u5546\u54C1\u7C7B\u522B\uFF1A");
		
		combo = new Combo(this, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			//按分类查询
			public void widgetSelected(SelectionEvent e) {
				kind();
			}
		});
		combo.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		combo.setBounds(91, 79, 128, 25);
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setText("\u5546\u54C1\u540D\u79F0\u6216\u7F16\u53F7\uFF1A");
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(260, 82, 120, 19);
		
		text = new Text(this, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		text.setBounds(386, 79, 128, 28);
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//按编号名称查询
			public void widgetSelected(SelectionEvent e) {
				if(text.getText().isEmpty()){
					MessageBox m=new MessageBox(getShell(),SWT.ICON_INFORMATION);
					m.setMessage("请输入相关信息！");
					m.open();
				}else{
					name();
				}
			}
		});
		button.setBounds(528, 80, 80, 27);
		button.setText("\u67E5\u8BE2");
		
		Group group = new Group(this, SWT.NONE);
		group.setBounds(10, 107, 745, 54);
		
		Label label_2 = new Label(group, SWT.NONE);
		label_2.setBounds(24, 20, 61, 17);
		label_2.setText("\u5546\u54C1\u6570\u91CF\uFF1A");
		
		Label label_3 = new Label(group, SWT.NONE);
		label_3.setBounds(499, 20, 42, 17);
		label_3.setText("\u6210\u672C\uFF1A");
		
		Label label_4 = new Label(group, SWT.NONE);
		label_4.setText("\u9884\u8BA1\u6536\u5165\uFF1A");
		label_4.setBounds(248, 20, 61, 17);
		
		countt = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		countt.setBounds(91, 17, 101, 23);
		
		shourut = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		shourut.setBounds(315, 17, 101, 23);
		
		costt = new Text(group, SWT.BORDER | SWT.READ_ONLY);
		costt.setBounds(545, 17, 101, 23);
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//查询全部
			public void widgetSelected(SelectionEvent e) {
				all();
			}
		});
		button_1.setBounds(641, 80, 80, 27);
		button_1.setText("\u67E5\u8BE2\u5168\u90E8");
		createContents();
		getKind();
		 all();
	}

	/**
	 * Create contents of the shell.
	 */
	//查询所有
	public void all(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from goods ";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			int count=0;
			double coming=0;
			double cost=0;
			while(rs.next()){
				count++;
				String[] temp=new String[10];
				TableItem item=new TableItem(table,SWT.None);
				
				temp[0]=rs.getString("sp_code");
				temp[1]=rs.getString("sp_name");
				temp[2]=rs.getString("sp_unit");
				temp[3]=rs.getString("sp_kind");
				temp[4]=rs.getString("sp_cost");
				temp[5]=rs.getString("sp_retail");
				temp[6]=rs.getString("sp_kucun");
				temp[7]=rs.getString("sp_dangerous");
				temp[8]=rs.getString("sp_gonghuoshang");
				coming+=rs.getDouble("sp_kucun")*rs.getDouble("sp_retail");
				cost+=rs.getDouble("sp_cost")*rs.getDouble("sp_kucun");
				temp[9]=String.valueOf(rs.getDouble("sp_kucun")*rs.getDouble("sp_retail"));
				
				item.setText(temp);
			}
			countt.setText(String.valueOf(count));
			shourut.setText(String.valueOf(coming));
			costt.setText(String.valueOf(cost));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	//按名称编号
	public void name(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from goods where sp_code='"+text.getText()+"' || sp_name='"+text.getText()+"'";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			int count=0;
			double coming=0;
			double cost=0;
			while(rs.next()){
				count++;
				String[] temp=new String[10];
				TableItem item=new TableItem(table,SWT.None);
				
				temp[0]=rs.getString("sp_code");
				temp[1]=rs.getString("sp_name");
				temp[2]=rs.getString("sp_unit");
				temp[3]=rs.getString("sp_kind");
				temp[4]=rs.getString("sp_cost");
				temp[5]=rs.getString("sp_retail");
				temp[6]=rs.getString("sp_kucun");
				temp[7]=rs.getString("sp_dangerous");
				temp[8]=rs.getString("sp_gonghuoshang");
				coming+=rs.getDouble("sp_kucun")*rs.getDouble("sp_retail");
				cost+=rs.getDouble("sp_cost")*rs.getDouble("sp_kucun");
				temp[9]=String.valueOf(rs.getDouble("sp_kucun")*rs.getDouble("sp_retail"));
				
				item.setText(temp);
			}
			countt.setText(String.valueOf(count));
			shourut.setText(String.valueOf(coming));
			costt.setText(String.valueOf(cost));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	
	//按分类查询
	public void kind(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from goods where sp_kind='"+combo.getText()+"'";
		ResultSet rs=jt.query(sql);
		try {
			table.removeAll();
			int count=0;
			double coming=0;
			double cost=0;
			while(rs.next()){
				count++;
				String[] temp=new String[10];
				TableItem item=new TableItem(table,SWT.None);
				
				temp[0]=rs.getString("sp_code");
				temp[1]=rs.getString("sp_name");
				temp[2]=rs.getString("sp_unit");
				temp[3]=rs.getString("sp_kind");
				temp[4]=rs.getString("sp_cost");
				temp[5]=rs.getString("sp_retail");
				temp[6]=rs.getString("sp_kucun");
				temp[7]=rs.getString("sp_dangerous");
				temp[8]=rs.getString("sp_gonghuoshang");
				coming+=rs.getDouble("sp_kucun")*rs.getDouble("sp_retail");
				cost+=rs.getDouble("sp_cost")*rs.getDouble("sp_kucun");
				temp[9]=String.valueOf(rs.getDouble("sp_kucun")*rs.getDouble("sp_retail"));
				
				item.setText(temp);
			}
			countt.setText(String.valueOf(count));
			shourut.setText(String.valueOf(coming));
			costt.setText(String.valueOf(cost));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
	}
	//获取商品分类
	public void getKind(){
		JDBCTools jt=new JDBCTools();
		String sql="select * from goods_kind";
		String sql1="select count(kind) as number from goods_kind";
		
		ResultSet rs1=jt.query(sql1);
		ResultSet rs=jt.query(sql);
		try {
			rs1.absolute(1);
			int i=rs1.getInt("number");
			String[] temp=new String[i];
			while(rs.next()){
				temp[i-1]=rs.getString("kind");
				i--;	
			}
			combo.setItems(temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
		jt.close(rs1);
	}
	
	
	protected void createContents() {
		setText("\u5E93\u5B58\u7EDF\u8BA1");
		setSize(781, 669);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
