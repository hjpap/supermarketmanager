package cn.com.wei.editor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import cn.com.wei.tools.JDBCTools;

public class QueryReturns extends EditorPart {

	public static final String ID = "cn.com.wei.editor.QueryReturns"; //$NON-NLS-1$
	private Text text;
	private Table table;
	private Table table_1;
	private DateTime dateTime;
	private DateTime dateTime_1;
	private Text text_1;
	private Text text_2;
	private Text text_3;

	public QueryReturns() {
	}

	/**
	 * Create contents of the editor part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setBackgroundMode(SWT.INHERIT_FORCE);
		container.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		
		Label label_9 = new Label(container, SWT.NONE);
		label_9.setImage(SWTResourceManager.getImage(QueryReturns.class, "/img/sunhaoTitle.png"));
		label_9.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_9.setBounds(54, 20, 99, 94);
		
		Label label_10 = new Label(container, SWT.NONE);
		label_10.setText("\u9000\u8D27\u67E5\u8BE2");
		label_10.setFont(SWTResourceManager.getFont("微软雅黑", 20, SWT.BOLD));
		label_10.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label_10.setBounds(174, 48, 153, 42);
		
		Label label = new Label(container, SWT.NONE);
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		label.setBounds(0, 0, 914, 125);
		
		Group group = new Group(container, SWT.NONE);
		group.setText("\u67E5\u627E");
		group.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		group.setBackgroundMode(SWT.INHERIT_FORCE);
		group.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		group.setBounds(389, 148, 512, 127);
		
		Group group_1 = new Group(group, SWT.NONE);
		group_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		group_1.setBounds(373, 16, 129, 90);
		
		final Button button = new Button(group_1, SWT.RADIO);
		button.setText("\u6309\u9000\u8D27\u65E5\u671F\u67E5\u8BE2");
		button.setSelection(true);
		button.setBounds(10, 24, 110, 17);
		
		Button button_1 = new Button(group_1, SWT.RADIO);
		button_1.setText("\u6309\u9000\u8D27\u5355\u53F7\u67E5\u8BE2");
		button_1.setBounds(10, 47, 109, 17);
		
		final Composite composite = new Composite(group, SWT.NONE);
		composite.setBounds(10, 27, 357, 79);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("\u65E5\u671F\u533A\u95F4\uFF1A \u4ECE");
		label_1.setBounds(10, 10, 80, 17);
		
		dateTime = new DateTime(composite, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime.setBounds(96, 10, 134, 24);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setText("\u81F3");
		label_2.setBounds(72, 52, 18, 17);
		
		dateTime_1 = new DateTime(composite, SWT.BORDER | SWT.DROP_DOWN | SWT.LONG);
		dateTime_1.setBounds(96, 45, 134, 24);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			//按日期查
			public void widgetSelected(SelectionEvent e) {
				queryDate();
			}
		});
		button_2.setText("\u67E5\u627E");
		button_2.setBounds(239, 20, 108, 36);
		
		final Composite composite_1 = new Composite(group, SWT.NONE);
		composite_1.setBounds(10, 27, 357, 79);
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setText("\u9000\u8D27\u5355\u53F7\uFF1A");
		label_3.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.NORMAL));
		label_3.setBounds(10, 29, 61, 17);
		
		text = new Text(composite_1, SWT.BORDER);
		text.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
		text.setBounds(75, 24, 158, 25);
		
		Button button_3 = new Button(composite_1, SWT.NONE);
		button_3.addSelectionListener(new SelectionAdapter() {
			//按单号查
			public void widgetSelected(SelectionEvent e) {
				queryCode();
			}
		});
		button_3.setText("\u67E5\u627E");
		button_3.setBounds(239, 19, 108, 36);
		
		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setFont(SWTResourceManager.getFont("微软雅黑", 8, SWT.NORMAL));
		table.setBounds(10, 319, 393, 341);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(108);
		tableColumn.setText("\u9000\u8D27\u5355\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(76);
		tableColumn_1.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(63);
		tableColumn_2.setText("\u5408\u8BA1\u9000\u6B3E");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(59);
		tableColumn_3.setText("\u7ECF\u529E\u4EBA");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(80);
		tableColumn_4.setText("\u9500\u552E\u65E5\u671F");
		
		Menu menu = new Menu(table);
		table.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.addSelectionListener(new SelectionAdapter() {
			//明细
			public void widgetSelected(SelectionEvent e) {
				showMingxi();
			}
		});
		menuItem.setText("\u67E5\u770B\u660E\u7EC6");
		
		table_1 = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(409, 319, 492, 341);
		
		TableColumn tableColumn_5 = new TableColumn(table_1, SWT.NONE);
		tableColumn_5.setWidth(123);
		tableColumn_5.setText("\u9000\u8D27\u5355\u53F7");
		
		TableColumn tableColumn_6 = new TableColumn(table_1, SWT.NONE);
		tableColumn_6.setWidth(70);
		tableColumn_6.setText("\u5546\u54C1\u7F16\u53F7");
		
		TableColumn tableColumn_7 = new TableColumn(table_1, SWT.NONE);
		tableColumn_7.setWidth(80);
		tableColumn_7.setText("\u5546\u54C1\u540D\u79F0");
		
		TableColumn tableColumn_8 = new TableColumn(table_1, SWT.NONE);
		tableColumn_8.setWidth(52);
		tableColumn_8.setText("\u96F6\u552E\u4EF7");
		
		TableColumn tableColumn_9 = new TableColumn(table_1, SWT.NONE);
		tableColumn_9.setWidth(49);
		tableColumn_9.setText("\u6298\u6263\u7387");
		
		TableColumn tableColumn_10 = new TableColumn(table_1, SWT.NONE);
		tableColumn_10.setWidth(49);
		tableColumn_10.setText("\u6570\u91CF");
		
		TableColumn tableColumn_11 = new TableColumn(table_1, SWT.NONE);
		tableColumn_11.setWidth(62);
		tableColumn_11.setText("\u5408\u8BA1\u9000\u6B3E");
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("\u9000\u8D27\u5355\u53F7\uFF1A");
		label_4.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_4.setBounds(10, 291, 76, 22);
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setText("\u9000\u5355\u660E\u7EC6\uFF1A");
		label_5.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.BOLD));
		label_5.setBounds(414, 291, 76, 22);
		composite_1.setVisible(false);
		
		Group group_2 = new Group(container, SWT.NONE);
		group_2.setText("\u7EDF\u8BA1");
		group_2.setFont(SWTResourceManager.getFont("微软雅黑", 11, SWT.NORMAL));
		group_2.setBounds(10, 148, 373, 127);
		
		Label label_6 = new Label(group_2, SWT.NONE);
		label_6.setText("\u603B\u96F6\u552E\u4EF7\uFF1A");
		label_6.setBounds(21, 29, 61, 17);
		
		text_1 = new Text(group_2, SWT.BORDER);
		text_1.setBounds(92, 26, 152, 23);
		
		Label label_7 = new Label(group_2, SWT.NONE);
		label_7.setText("\u5408\u8BA1\u9000\u6B3E\uFF1A");
		label_7.setBounds(21, 65, 61, 17);
		
		text_2 = new Text(group_2, SWT.BORDER);
		text_2.setBounds(92, 59, 152, 23);
		
		Label label_8 = new Label(group_2, SWT.NONE);
		label_8.setText("\u8BB0\u5F55\u6761\u6570\uFF1A");
		label_8.setBounds(21, 100, 61, 17);
		
		text_3 = new Text(group_2, SWT.BORDER);
		text_3.setBounds(92, 94, 152, 23);
		
		Button button_4 = new Button(group_2, SWT.NONE);
		button_4.addSelectionListener(new SelectionAdapter() {
			//查看全部
			public void widgetSelected(SelectionEvent e) {
				tongji();
			}
		});
		button_4.setText("\u67E5\u770B\u5168\u90E8");
		button_4.setBounds(255, 46, 108, 36);
				button.addSelectionListener(new SelectionAdapter() {
					//按时间查询 radio按钮
					public void widgetSelected(SelectionEvent e) {
						if(button.getSelection()){
							composite.setVisible(true);
							composite_1.setVisible(false);
						}else{
							composite.setVisible(false);
							composite_1.setVisible(true);
						}

					}
				});
				
				

	}

	
	
	//按日期查询
		public void queryDate(){
			JDBCTools jt=new JDBCTools();
			String sql="SELECT * FROM returns_bill WHERE DATE BETWEEN '"+dateTime.getYear()+"-"+(dateTime.getMonth()+1)+"-"+dateTime.getDay()+"' AND '"+dateTime_1.getYear()+"-"+(dateTime_1.getMonth()+1)+"-"+dateTime_1.getDay()+"' ";
			
			ResultSet rs=jt.query(sql);
			
			try {
				table.removeAll();
				table_1.removeAll();
				double cost=0;
				double total=0;
				int count=0;
				while(rs.next()){
					TableItem item=new TableItem(table,SWT.None);
					String[] temp=new String[5];
					temp[0]=rs.getString("billcode");
					temp[1]=rs.getString("cost");
					temp[2]=rs.getString("total");
					temp[3]=rs.getString("agent");
					temp[4]=rs.getString("date");
					item.setText(temp);
					
					//统计
					cost=cost+rs.getDouble("cost");
					total=total+rs.getDouble("total");
					count++;
				}
				text_1.setText(Double.toString(cost));
				text_2.setText(Double.toString(total));
				text_3.setText(Integer.toString(count));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jt.close(rs);
		}
		
		
		//按单号查找
		public void queryCode(){
			JDBCTools jt=new JDBCTools();
			String sql="SELECT * FROM returns_bill WHERE billcode='"+text.getText()+"'";
			//System.out.println(sql);
			ResultSet rs=jt.query(sql);
			
			try {
				table_1.removeAll();
				table.removeAll();
				double cost=0;
				double total=0;
				int count=0;
				while(rs.next()){
					TableItem item=new TableItem(table,SWT.None);
					String[] temp=new String[5];
					temp[0]=rs.getString("billcode");
					temp[1]=rs.getString("cost");
					temp[2]=rs.getString("total");
					temp[3]=rs.getString("agent");
					temp[4]=rs.getString("date");
					item.setText(temp);
					
					//统计
					cost=cost+rs.getDouble("cost");
					total=total+rs.getDouble("total");
					count++;
				}
				text_1.setText(Double.toString(cost));
				text_2.setText(Double.toString(total));
				text_3.setText(Integer.toString(count));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jt.close(rs);
		}
		
		

		//查看明细
		public void showMingxi(){
			
			JDBCTools jt=new JDBCTools();
			String sql="select * from returns_mingxi where billcode='"+table.getSelection()[0].getText(0)+"'";
			ResultSet rs=jt.query(sql);
			try {
				table_1.removeAll();
				while(rs.next()){
					TableItem item=new TableItem(table_1,SWT.None);
					String[] temp=new String[7];
					temp[0]=rs.getString("billcode");
					temp[1]=rs.getString("code");
					temp[2]=rs.getString("name");
					temp[3]=rs.getString("retail");
					temp[4]=rs.getString("zhekou");
					temp[5]=rs.getString("count");
					temp[6]=rs.getString("total");
					item.setText(temp);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jt.close(rs);
		}

		
		//统计所有
		public void tongji(){
			JDBCTools jt=new JDBCTools();
			String sql="select * from returns_bill";
			ResultSet rs=jt.query(sql);
			
			try {
				table.removeAll();
				table_1.removeAll();
				double cost=0;
				double total=0;
				int count=0;
				while(rs.next()){
					TableItem item=new TableItem(table,SWT.None);
					String[] temp=new String[5];
					temp[0]=rs.getString("billcode");
					temp[1]=rs.getString("cost");
					temp[2]=rs.getString("total");
					temp[3]=rs.getString("agent");
					temp[4]=rs.getString("date");
					item.setText(temp);
					//统计
					cost=cost+rs.getDouble("cost");
					total=total+rs.getDouble("total");
					count++;
				}
				text_1.setText(Double.toString(cost));
				text_2.setText(Double.toString(total));
				text_3.setText(Integer.toString(count));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			jt.close(rs);
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
