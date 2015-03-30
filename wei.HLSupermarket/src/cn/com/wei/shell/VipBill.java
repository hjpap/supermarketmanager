package cn.com.wei.shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import cn.com.wei.tools.JDBCTools;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class VipBill extends Shell {
	private static TableItem item;
	private Table table;
	private Text text;
	private DateTime dateTime_1;
	private DateTime dateTime;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void start(TableItem item1) {
		
		try {
			item=item1;
			Display display = Display.getDefault();
			VipBill shell = new VipBill(display);
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
	
	
	public VipBill(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(VipBill.class, "/img/22.png"));
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setText("\u4F1A\u5458\u6D88\u8D39\u660E\u7EC6");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 15, SWT.BOLD));
		label_4.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label_4.setBounds(75, 25, 148, 27);
		
		Label label = new Label(this, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(51, 204, 255));
		label.setBounds(0, 0, 671, 73);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 116, 671, 271);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(125);
		tableColumn.setText("\u8D26\u5355\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(89);
		tableColumn_1.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(113);
		tableColumn_2.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(62);
		tableColumn_3.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(58);
		tableColumn_4.setText("\u6298\u6263\u7387");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(55);
		tableColumn_5.setText("\u6570\u91CF");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(71);
		tableColumn_6.setText("\u5408\u8BA1\u4EF7\u683C");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.NONE);
		tableColumn_7.setWidth(85);
		tableColumn_7.setText("\u6D88\u8D39\u65E5\u671F");
		
		Label label_1 = new Label(this, SWT.NONE);
		label_1.setBounds(10, 87, 48, 17);
		label_1.setText("\u4F1A\u5458\u53F7\uFF1A");
		
		text = new Text(this, SWT.READ_ONLY);
		text.setBounds(64, 87, 115, 23);
		text.setText(item.getText(0));
		
		Label label_2 = new Label(this, SWT.NONE);
		label_2.setBounds(256, 87, 61, 17);
		label_2.setText("\u65F6\u95F4\u533A\u95F4\uFF1A");
		
		dateTime = new DateTime(this, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime.setBounds(318, 83, 115, 24);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setBounds(439, 87, 22, 17);
		label_3.setText("\u81F3");
		
		dateTime_1 = new DateTime(this, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime_1.setBounds(460, 83, 115, 24);
		
		Button button = new Button(this, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
				queryDate();
			}
		});
		button.setBounds(581, 79, 80, 27);
		button.setText("\u67E5\u8BE2");
		createContents();
		queryAll();//查看所有明细
	}

	/**
	 * Create contents of the shell.
	 */
	//日期查询
	public void queryDate(){
		JDBCTools jt=new JDBCTools();
		String sql="SELECT sale_mingxi.* FROM sale_bill,sale_mingxi WHERE  sale_bill.isvip='"+text.getText()+"' AND sale_bill.billcode=sale_mingxi.billcode AND sale_bill.DATE BETWEEN '"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"' AND '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-"+dateTime_1.getDay()+"'";
ResultSet rs=jt.query(sql);
		
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[8];
				temp[0]=rs.getString("billcode");
				temp[1]=rs.getString("code");
				temp[2]=rs.getString("name");
				temp[3]=rs.getString("retail");
				temp[4]=rs.getString("zhekou");
				temp[5]=rs.getString("count");
				temp[6]=rs.getString("total");
				temp[7]=rs.getString("date");
				item.setText(temp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
		
	}
	
	//查询所有明细
	public void queryAll(){
		JDBCTools jt=new JDBCTools();
		String sql="SELECT sale_mingxi.* FROM sale_bill,sale_mingxi WHERE  sale_bill.isvip='"+text.getText()+"' AND sale_bill.billcode=sale_mingxi.billcode";
		ResultSet rs=jt.query(sql);
		
		try {
			table.removeAll();
			while(rs.next()){
				TableItem item=new TableItem(table,SWT.None);
				String[] temp=new String[8];
				temp[0]=rs.getString("billcode");
				temp[1]=rs.getString("code");
				temp[2]=rs.getString("name");
				temp[3]=rs.getString("retail");
				temp[4]=rs.getString("zhekou");
				temp[5]=rs.getString("count");
				temp[6]=rs.getString("total");
				temp[7]=rs.getString("date");
				item.setText(temp);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jt.close(rs);
		
	}
	
	
	protected void createContents() {
		setText("\u4F1A\u5458\u6D88\u8D39\u660E\u7EC6");
		setSize(687, 422);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
