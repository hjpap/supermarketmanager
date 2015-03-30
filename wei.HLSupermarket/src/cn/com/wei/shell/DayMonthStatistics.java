package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.TableItem;

import cn.com.wei.tools.DateTools;
import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DayMonthStatistics extends Shell {
	private Table table;
	private Table table_1;
	private DateTime dateTime_1;
	private DateTime dateTime;
	private ProgressBar progressBar_1;
	private ProgressBar progressBar;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start() {
		try {
			Display display = Display.getDefault();
			DayMonthStatistics shell = new DayMonthStatistics(display);
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
	public DayMonthStatistics(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(DayMonthStatistics.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("\u62A5\u8868");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_5.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_5.setBounds(92, 20, 92, 36);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 717, 73);
		
		TabFolder tabFolder = new TabFolder(this, SWT.NONE);
		tabFolder.setBounds(0, 72, 628, 437);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("\u65E5\u7EDF\u8BA1");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 81, 626, 244);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(125);
		tableColumn.setText("\u65E5\u671F");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(122);
		tableColumn_1.setText("\u8FDB\u8D27\u91D1\u989D");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(123);
		tableColumn_2.setText("\u635F\u8017\u91D1\u989D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(119);
		tableColumn_3.setText("\u9500\u552E\u91D1\u989D");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(116);
		tableColumn_4.setText("\u9000\u8D27\u91D1\u989D");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_1.setBounds(29, 25, 41, 24);
		label_1.setText("\u65E5\u671F\uFF1A");
		
		dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		dateTime.setBounds(91, 25, 115, 24);
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			//日查询
			public void widgetSelected(SelectionEvent e) {
				day();
			}
		});
		button.setBounds(242, 23, 80, 27);
		button.setText("\u67E5\u8BE2");
		
		progressBar = new ProgressBar(composite, SWT.NONE);
		progressBar.setBounds(160, 349, 450, 27);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_3.setBounds(10, 352, 144, 24);
		label_3.setText("\u9500\u552E\u91D1\u989D\u5360\u5168\u6708\u6BD4\u4F8B\uFF1A");
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("\u6708\u7EDF\u8BA1");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem_1.setControl(composite_1);
		
		table_1 = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(0, 81, 626, 243);
		
		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(125);
		tableColumn_5.setText("\u65E5\u671F");
		
		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(122);
		tableColumn_6.setText("\u8FDB\u8D27\u91D1\u989D");
		
		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(123);
		tableColumn_7.setText("\u635F\u8017\u91D1\u989D");
		
		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(119);
		tableColumn_8.setText("\u9500\u552E\u91D1\u989D");
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(116);
		tableColumn_9.setText("\u9000\u8D27\u91D1\u989D");
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_2.setBounds(33, 25, 45, 20);
		label_2.setText("\u6708\u4EFD\uFF1A");
		
		dateTime_1 = new DateTime(composite_1, SWT.BORDER | SWT.SHORT);
		dateTime_1.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		dateTime_1.setBounds(96, 25, 116, 24);
		
		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			//月统计
			public void widgetSelected(SelectionEvent e) {
				month();
			}
		});
		button_1.setBounds(247, 25, 80, 27);
		button_1.setText("\u67E5\u8BE2");
		
		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setText("\u9500\u552E\u91D1\u989D\u5360\u8BE5\u5E74\u6BD4\u4F8B\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		label_4.setBounds(10, 354, 148, 24);
		
		progressBar_1 = new ProgressBar(composite_1, SWT.NONE);
		progressBar_1.setBounds(164, 351, 446, 27);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	//日统计
	public void day(){
		JDBCTools jt =new JDBCTools();
		String sql="select * FROM kucun where date='"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"'";//进货金额
		String sql1="select * FROM sunhao where date='"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"'";//损耗金额
		String sql2="select * FROM sale_bill  where date='"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"'";//销售金额
		String sql3="select * FROM returns_bill where date='"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"'";//退货金额
		String sql4="select * from sale_bill where date between '"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-1' and '"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-31'";
		ResultSet rs=jt.query(sql);
		ResultSet rs1=jt.query(sql1);
		ResultSet rs2=jt.query(sql2);
		ResultSet rs3=jt.query(sql3);
		ResultSet rs4=jt.query(sql4);
		try {
			double jinhuo=0,sunhao=0,xiaoshou=0,tuihuo=0,yue=0;
			
			while(rs.next()){
				jinhuo+=rs.getDouble("cost")*rs.getInt("jhcount");
			}
			while(rs1.next()){
				sunhao+=rs1.getDouble("total");
			}
			while(rs2.next()){
				xiaoshou+=rs2.getDouble("total");
			}
			while(rs3.next()){
				tuihuo+=rs3.getDouble("total");
			}
			while(rs4.next()){
				yue+=rs4.getDouble("total");
			}
			
			progressBar.setSelection((int)((xiaoshou/yue)*100));
			table.removeAll();
			TableItem item=new TableItem(table,SWT.None);
			String[] temp=new String[5];
			temp[0]=DateTools.getDateTime();
			temp[1]=String.valueOf(jinhuo);
			temp[2]=String.valueOf(sunhao);
			temp[3]=String.valueOf(xiaoshou);
			temp[4]=String.valueOf(tuihuo);
			item.setText(temp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs3);
		jt.close(rs2);
		jt.close(rs1);
		jt.close(rs);
		jt.close(rs4);
		
		//System.out.println(sql+"\n"+sql1+"\n"+sql2+"\n"+sql4+"\n");
	}
	
	//月统计
	public void month(){
		JDBCTools jt =new JDBCTools();
		String sql="select * FROM kucun where date between '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-1' and '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-31'";//进货金额
		String sql1="select * FROM sunhao where date between '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-1' and '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-31'";//损耗金额
		String sql2="select * FROM sale_bill  where date between '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-1' and '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-31'";//销售金额
		String sql3="select * FROM returns_bill where date between '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-1' and '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-31'";//退货金额
		String sql4="select * from sale_bill where date between '"+dateTime_1.getYear()+"-1-1' and '"+dateTime_1.getYear()+"-12-31'";
		ResultSet rs=jt.query(sql);
		ResultSet rs1=jt.query(sql1);
		ResultSet rs2=jt.query(sql2);
		ResultSet rs3=jt.query(sql3);
		ResultSet rs4=jt.query(sql4);
		try {
			double jinhuo=0,sunhao=0,xiaoshou=0,tuihuo=0,nian=0;
			
			while(rs.next()){
				jinhuo+=rs.getDouble("cost")*rs.getInt("jhcount");
			}
			while(rs1.next()){
				sunhao+=rs1.getDouble("total");
			}
			while(rs2.next()){
				xiaoshou+=rs2.getDouble("total");
			}
			while(rs3.next()){
				tuihuo+=rs3.getDouble("total");
			}
			while(rs4.next()){
				nian+=rs4.getDouble("total");
			}
			
			progressBar_1.setSelection((int)((xiaoshou/nian)*100));
			table_1.removeAll();
			TableItem item=new TableItem(table_1,SWT.None);
			String[] temp=new String[5];
			temp[0]=DateTools.getDateTime();
			temp[1]=String.valueOf(jinhuo);
			temp[2]=String.valueOf(sunhao);
			temp[3]=String.valueOf(xiaoshou);
			temp[4]=String.valueOf(tuihuo);
			item.setText(temp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs3);
		jt.close(rs2);
		jt.close(rs1);
		jt.close(rs);
		jt.close(rs4);
		
		System.out.println(sql+"\n"+sql1+"\n"+sql2+"\n"+sql4+"\n");
	}
	protected void createContents() {
		setText("\u65E5/\u6708\u62A5\u8868");
		setSize(641, 547);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
